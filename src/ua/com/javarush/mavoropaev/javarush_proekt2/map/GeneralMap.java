package ua.com.javarush.mavoropaev.javarush_proekt2.map;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.*;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Animal;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.TableEatProbability;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores.*;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators.*;
import ua.com.javarush.mavoropaev.javarush_proekt2.plants.Plants;
import ua.com.javarush.mavoropaev.javarush_proekt2.statistics.GlobalStatistics;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Phaser;

public class GeneralMap {
    private int sizeX;
    private int sizeY;

    public Cell[][] cellMap;
    public Plants[][] plantsMap;
    TableEatProbability tableEatProbability;
    GlobalStatistics globalStatistics = GlobalStatistics.getInstance();
    CycleCounter cycleCounter = CycleCounter.getInstance();
    Parameters parameters = Parameters.getInstance();

    private static volatile GeneralMap instance;

    public static GeneralMap getInstance() {
        GeneralMap localInstance = instance;
        if (localInstance == null) {
            synchronized (GeneralMap.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new GeneralMap();
                }
            }
        }
        return localInstance;
    }

    public GeneralMap() {
        this.sizeX = parameters.getMapSizeX();
        this.sizeY = parameters.getMapSizeY();
        cellMap = new Cell[sizeX][sizeY];
        plantsMap = new Plants[sizeX][sizeY];
    }

    public volatile List<Thread> threadList = new ArrayList<>(Parameters.ITEM_COUNT);

    public void startMap() {
        tableEatProbability = new TableEatProbability();
        initCellMap();
        initPlantsMap();
        initAnimals();

        Phaser phaser = new Phaser(0);
        new Thread(new StatisticsThread(phaser, "StatisticsThread")).start();

        for (NameItem name : NameItem.values()) {
            if (name.equals(NameItem.PLANTS)) continue;
            Thread itemThread = new Thread(new AnimalsThread(name, phaser, "AnimalsThread : " + name ));
            threadList.add(itemThread);
            itemThread.start();
        }
    }

    public class AnimalsThread implements Runnable {
        private NameItem nameItem;
        Phaser phaser;
        String name;

        public AnimalsThread(NameItem nameItem, Phaser p, String n) {
            this.nameItem = nameItem;
            this.phaser = p;
            this.name = n;
            phaser.register();
        }

        @Override
        public void run() {
            for (int i = 0; i < parameters.getCountCycle(); i++) {
                phaser.arriveAndAwaitAdvance(); //restorePlants

                checkDeathAnimals(nameItem);
                phaser.arriveAndAwaitAdvance(); //checkDeathAnimals

                eatAllAnimals(nameItem);
                phaser.arriveAndAwaitAdvance(); //eatAllAnimals

                reproductionAllAnimals(nameItem);
                phaser.arriveAndAwaitAdvance(); //reproductionAllAnimals

                moveAllAnimals(nameItem);
                phaser.arriveAndAwaitAdvance(); //moveAllAnimals

                phaser.arriveAndAwaitAdvance(); //globalStatistics.setCellStatisticsEndCycle
            }
            phaser.arriveAndDeregister();
        }
    }

    public class StatisticsThread implements Runnable {
        Phaser phaser;
        String name;

        public StatisticsThread(Phaser p, String n) {
            this.phaser = p;
            this.name = n;
            phaser.register();
        }

        @Override
        public void run() {
            for (int i = 0; i < parameters.getCountCycle(); i++) {
                restorePlants();
                globalStatistics.setCellStatisticsBeginCycle();
                cycleCounter.increaseCycleCounter();
                phaser.arriveAndAwaitAdvance(); //restorePlants

                phaser.arriveAndAwaitAdvance(); //checkDeathAnimals
                phaser.arriveAndAwaitAdvance(); //eatAllAnimals
                phaser.arriveAndAwaitAdvance(); //reproductionAllAnimals
                phaser.arriveAndAwaitAdvance(); //moveAllAnimals

                synchronized (GlobalStatistics.class) {
                    globalStatistics.setCellStatisticsEndCycle();
                    for (Integer x : parameters.getKeySet()) {
                        for (Integer y : parameters.getKeyValue(x)) {
                            globalStatistics.printStatistics(x, y);
                        }
                    }
                }
                phaser.arriveAndAwaitAdvance(); //globalStatistics.setCellStatisticsEndCycle
            }
            phaser.arriveAndDeregister();
        }
    }

    public int getSizeX() { return sizeX; }

    public int getSizeY() {
        return sizeY;
    }

    private void checkDeathAnimals(NameItem name) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (cellMap[x][y].listAnimals.containsKey(name)) {
                    final CopyOnWriteArrayList<Animal> animalsList;
                    final int xFinal = x;
                    final int yFinal = y;
                    animalsList = cellMap[xFinal][yFinal].listAnimals.get(name);

                    animalsList.forEach(animal -> {
                        if (animal.die()){
                            animalsList.remove(animal);
                            cellMap[xFinal][yFinal].decrementCounterAnimalsOnType(name);
                            globalStatistics.addStatisticsDeath(xFinal, yFinal, name);
                        }
                    });
                }
            }
        }
    }

    private void reproductionAllAnimals(NameItem name) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                final CopyOnWriteArrayList<Animal> animalsList;
                animalsList = cellMap[x][y].listAnimals.get(name);
                int countAnimals = animalsList.size();
                ArrayList<Animal> newAnimals = new ArrayList<>();

                for (int number = 0; number < countAnimals; number++) {
                    if (!reproductionPairs(name, x, y, animalsList, countAnimals, newAnimals, number)) break;
                }

                CopyOnWriteArrayList<Animal> newCellListAnimals = cellMap[x][y].listAnimals.get(name);

                for (Animal newAnimal : newAnimals) {
                    if (newCellListAnimals.size() < newCellListAnimals.get(0).getMaxPopulation()) {
                        newCellListAnimals.add(newAnimal);
                        cellMap[x][y].increaseCounterAnimalsOnType(name);
                        globalStatistics.addStatisticsReproductions(x, y, name);
                    }
                }
                cellMap[x][y].listAnimals.put(name, newCellListAnimals);

            }
        }
    }

    private boolean reproductionPairs(NameItem name, int x, int y, CopyOnWriteArrayList<Animal> animalsList, int countAnimals, ArrayList<Animal> newAnimals, int number) {
        if (number < countAnimals - number - 1) {
            if (checkPeriodReproduction(animalsList, countAnimals, number)) {

                for (int count = 0; count < animalsList.get(number).getAmountOfChildren(); count++) {
                    Animal newAnimal = animalsList.get(number).newObject(name, StatusAnimals.NEWBORN, x, y);
                    newAnimal.setCountCycleReproduction(cycleCounter.getCycleCounter());
                    newAnimals.add(newAnimal);
                }

                animalsList.get(number).setCountCycleReproduction(cycleCounter.getCycleCounter());
                animalsList.get(countAnimals - number - 1).setCountCycleReproduction(cycleCounter.getCycleCounter());
            }
       // } else if (iteration == countAnimals - iteration - 1) {
       //     animalsList.get(iteration).setCountCycleReproduction(cycleCounter.getCycleCounter());
        } else return false;
        return true;
    }

    private boolean checkPeriodReproduction(CopyOnWriteArrayList<Animal> animalsList, int countAnimals, int iteration) {
        return cycleCounter.getCycleCounter() - animalsList.get(iteration).getCountCycleReproduction() > animalsList.get(iteration).getPeriodReproductions() &&
                cycleCounter.getCycleCounter() - animalsList.get(countAnimals - iteration - 1).getCountCycleReproduction() > animalsList.get(countAnimals - iteration - 1).getPeriodReproductions();
    }

    private void eatAllAnimals(NameItem name) {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                CopyOnWriteArrayList<Animal> animalsList;

                if (cellMap[x][y].listAnimals.containsKey(name)) {
                    animalsList = cellMap[x][y].listAnimals.get(name);
                    final int xFinal = x;
                    final int yFinal = y;

                    animalsList.forEach(animal -> {
                        if (animal.getCurrentWeightEat() < animal.getMaxWeightEat()) {
                            NameItem nameFoods = getFoodsRandom(name, xFinal, yFinal);
                            if (nameFoods != null && eatFoods(animal, nameFoods, xFinal, yFinal)) {
                                globalStatistics.addStatisticsHaveBeenEaten(xFinal, yFinal, nameFoods);
                            }
                        }
                    });
                }
            }
        }
    }

    private NameItem getFoodsRandom(NameItem nameAnimal, int x, int y) {
        ArrayList<NameItem> listFood = new ArrayList<>();
        Random random = new Random();
        int numberAnimal = tableEatProbability.getNumberToAnimal(nameAnimal);

        for (NameItem name : NameItem.values()) {
            int numberFood = tableEatProbability.getNumberToAnimal(name);
            int probabilityEat = tableEatProbability.getProbability(numberAnimal, numberFood);
            if (probabilityEat > 0) {
                if (tableEatProbability.getFoodToNumber(numberFood) != NameFoods.PLANTS) {
                    NameItem nameItem = tableEatProbability.getAnimalToNumber(numberFood);
                    if (cellMap[x][y].listAnimals.containsKey(nameItem)) {
                        if (cellMap[x][y].listAnimals.get(nameItem).size() > 0) {
                            listFood.add(name);
                        }
                    }
                } else {
                    if (plantsMap[x][y].getCurrentBiomassWeight() > 0) {
                        listFood.add(name);
                    }
                }
            }
        }
        if (listFood.size() > 0) {
            int numberFood = 0;
            while (numberFood == 0) {
                numberFood = random.nextInt(listFood.size() + 1);
            }
            return listFood.get(numberFood - 1);
        }
        return null;
    }

    private boolean eatFoods(Animal animal, NameItem nameFood, int x, int y) {
        Random random = new Random();
        NameItem nameAnimal = animal.getName();
        int numberAnimal = tableEatProbability.getNumberToAnimal(nameAnimal);
        int numberFood = tableEatProbability.getNumberToAnimal(nameFood);
        int probabilityEat = tableEatProbability.getProbability(numberAnimal, numberFood);

        int chanceCatchingAnimal = 0;
        while (chanceCatchingAnimal == 0) {
            chanceCatchingAnimal = random.nextInt(100);
        }
        if (chanceCatchingAnimal <= probabilityEat) {
            //едим!!!!
            if (nameFood != NameItem.PLANTS) {
                if (cellMap[x][y].listAnimals.get(nameFood).size() > 0) {
                    synchronized (cellMap[x][y].listAnimals.get(nameFood).get(0).getClass()) {
                        if (cellMap[x][y].listAnimals.get(nameFood).size() > 0) {
                            double weightEat = cellMap[x][y].listAnimals.get(nameFood).get(0).getWeight();

                            globalStatistics.addStatisticsWhoAteWho(x, y, nameAnimal, nameFood);

                            cellMap[x][y].decrementCounterAnimalsOnType(nameFood);
                            cellMap[x][y].listAnimals.get(nameFood).remove(0);
                            animal.addCurrentWeightEat(weightEat);
                        }
                    }
                }
            } else {
                double weightPlants = plantsMap[x][y].getWeight();
                synchronized (Plants.class) {
                    plantsMap[x][y].subtractPlants();
                    animal.addCurrentWeightEat(weightPlants);

                    globalStatistics.addStatisticsWhoAteWho(x, y, nameAnimal, NameItem.PLANTS);
                }
            }
            return true;
        }
        return false;
    }

    private void moveAllAnimals(NameItem name) {

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                final CopyOnWriteArrayList<Animal> animalsList;
                final int xFinal = x;
                final int yFinal = y;
                animalsList = cellMap[x][y].listAnimals.get(name);

                animalsList.forEach(animal -> {
                    if (animal.move()) {
                        animalsList.remove(animal);
                        globalStatistics.addStatisticsLeave(xFinal, yFinal, name);
                    }
                });
            }
        }
    }

    private void initAnimals() {
        Random random = new Random();
        for (NameItem nameItem : NameItem.values()) {
            if (nameItem.equals(NameItem.PLANTS)) continue;

            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    int count = random.nextInt(getMaxPopulationCell(nameItem) + 1);
                    cellMap[x][y].countAnimalsOnType.put(nameItem, count);
                    CopyOnWriteArrayList<Animal> list = new CopyOnWriteArrayList<>();
                    for (int i = 0; i < count; i++) {
                        switch (nameItem) {
                            case BUFFALO -> list.add(new Buffalo(nameItem, StatusAnimals.START, x, y));
                            case FOX -> list.add(new Fox(nameItem, StatusAnimals.START, x, y));
                            case BEAR -> list.add(new Bear(nameItem, StatusAnimals.START, x, y));
                            case DEER -> list.add(new Deer(nameItem, StatusAnimals.START, x, y));
                            case DUCK -> list.add(new Duck(nameItem, StatusAnimals.START, x, y));
                            case GOAT -> list.add(new Goat(nameItem, StatusAnimals.START, x, y));
                            case WOLF -> list.add(new Wolf(nameItem, StatusAnimals.START, x, y));
                            case EAGLE -> list.add(new Eagle(nameItem, StatusAnimals.START, x, y));
                            case HORSE -> list.add(new Horse(nameItem, StatusAnimals.START, x, y));
                            case MOUSE -> list.add(new Mouse(nameItem, StatusAnimals.START, x, y));
                            case SHEEP -> list.add(new Sheep(nameItem, StatusAnimals.START, x, y));
                            case RABBIT -> list.add(new Rabbit(nameItem, StatusAnimals.START, x, y));
                            case WILD_BOAR -> list.add(new WildBoar(nameItem, StatusAnimals.START, x, y));
                            case CATERPILLAR -> list.add(new Caterpillar(nameItem, StatusAnimals.START, x, y));
                            case BOA_CONSTRICTOR -> list.add(new BoaConstrictor(nameItem, StatusAnimals.START, x, y));
                        }
                    }
                    cellMap[x][y].listAnimals.put(nameItem, list);
                }
            }
        }
    }

    private int getMaxPopulationCell(NameItem nameItem){
        switch (nameItem){
            case BUFFALO -> {return Buffalo.MAX_COUNT_CELL;}
            case FOX -> {return Fox.MAX_COUNT_CELL;}
            case BEAR -> {return Bear.MAX_COUNT_CELL;}
            case DEER -> {return Deer.MAX_COUNT_CELL;}
            case DUCK -> {return Duck.MAX_COUNT_CELL;}
            case GOAT -> {return Goat.MAX_COUNT_CELL;}
            case WOLF -> {return Wolf.MAX_COUNT_CELL;}
            case EAGLE -> {return Eagle.MAX_COUNT_CELL;}
            case HORSE -> {return Horse.MAX_COUNT_CELL;}
            case MOUSE -> {return Mouse.MAX_COUNT_CELL;}
            case SHEEP -> {return Sheep.MAX_COUNT_CELL;}
            case RABBIT -> {return Rabbit.MAX_COUNT_CELL;}
            case WILD_BOAR -> {return WildBoar.MAX_COUNT_CELL;}
            case CATERPILLAR -> {return Caterpillar.MAX_COUNT_CELL;}
            case BOA_CONSTRICTOR -> {return BoaConstrictor.MAX_COUNT_CELL;}
        }
        return 0;
    }

    private void initCellMap() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                cellMap[x][y] = new Cell(x, y);
            }
        }
    }

    private void initPlantsMap() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                plantsMap[x][y] = new Plants(NameItem.PLANTS, x, y);
                plantsMap[x][y].setInitBiomassWeight();
            }
        }
    }

    private void restorePlants() {
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                plantsMap[x][y].setInitBiomassWeight();
            }
        }
    }
}


