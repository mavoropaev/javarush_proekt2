package ua.com.javarush.mavoropaev.javarush_proekt2;

import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Animal;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.TableEatProbability;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores.*;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators.*;

import java.util.*;

public class GeneralMap {
    private int sizeX;
    private int sizeY;
    private int countCycle;

    public final int COUNT_TYPE_FOODS = 16;
    public final int COUNT_DIRECTION = 4;
    public final int UP_DIR = 1;
    public final int RIGHT_DIR = 2;
    public final int DOWN_DIR = 3;
    public final int LEFT_DIR = 4;

    public Cell[][] cellMap;
    public Plants[][] plantsMap;
    TableEatProbability tableEatProbability;
    GlobalStatistics globalStatistics = GlobalStatistics.getInstance();
    CycleCounter cycleCounter  = CycleCounter.getInstance();

    public GeneralMap(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        cellMap = new Cell[sizeX][sizeY];
        plantsMap = new Plants[sizeX][sizeY];
    }

    public void start(){
        tableEatProbability = new TableEatProbability();
        initCellMap();
        initPlantsMap();
        initAnimals();

        for (int i = 0; i < 5; i++) {
            newCycle();

            globalStatistics.setCellStatisticsBeginCycle(this);

            checkDeathAnimals();
            restorePlants();
            eatAllAnimals();
            reproductionAllAnimals();
            moveAllAnimals();

            globalStatistics.setCellStatisticsEndCycle(this);
            globalStatistics.printStatistics(0, 0);

        }



    }

    public void newCycle(){
        //printState();
        cycleCounter.increaseCycleCounter();
    }

    public void printState1(){
        System.out.println("cycle = " + cycleCounter.getCycleCounter());
        System.out.println("-------------------------");
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                if (x == 0 && y == 0) {
                    for (NameItem name : cellMap[x][y].listAnimals.keySet()) {

                        System.out.println("Cycle : nameAnimal = " + name + " : (" + x + ";" + y + ")" +
                                cellMap[x][y].countAnimalsOnType.get(name));
                    }
                }
            }
        }
        System.out.println("-------------------------");
    }

    public void initCellMap(){
        for (int x = 0; x < sizeX; x++){
            for (int y = 0; y < sizeY; y++){
                cellMap[x][y] = new Cell(x, y);
            }
        }
    }
    public void initPlantsMap(){
        for (int x = 0; x < sizeX; x++){
            for (int y = 0; y < sizeY; y++){
                plantsMap[x][y] = new Plants(NameItem.PLANTS, x, y);
                plantsMap[x][y].setInitBiomassWeight();
            }
        }
    }
    public void restorePlants(){
        for (int x = 0; x < sizeX; x++){
            for (int y = 0; y < sizeY; y++){
                plantsMap[x][y].setInitBiomassWeight();
            }
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    private void checkDeathAnimals(){
        ArrayList<Animal> animalsList;
        //printState();

        //for(int i = 0; i < 4; i++) {
          //  newCycle();

            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    for (NameItem name : cellMap[x][y].listAnimals.keySet()){
                        animalsList = cellMap[x][y].listAnimals.get(name);

                        Iterator<Animal> animalsIterator = animalsList.iterator();
                        while (animalsIterator.hasNext()){
                            Animal animal = animalsIterator.next();

                            if (animal.getCountCycleCheckDeath() < cycleCounter.getCycleCounter()){
                                animal.setCurrentWeightEat(animal.getCurrentWeightEat() - animal.getMaxWeightEat() * 0.25);
                                if (animal.getCurrentWeightEat() <= 0 && animal.getMaxWeightEat() > 0){
                                    animalsIterator.remove();
                                    cellMap[x][y].removeAnimalsOnType(name);
                                    globalStatistics.addStatisticsDeath(x, y, name);

                                }
                            }
                        }
                    }
                }
            }
            //printState();
        //}

    }

    private void reproductionAllAnimals(){
        ArrayList<Animal> animalsList;
        //printState();

        //for(int i = 0; i < 3; i++) {
         //   newCycle();
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    for (NameItem name : cellMap[x][y].listAnimals.keySet()){
                        animalsList = cellMap[x][y].listAnimals.get(name);
                        int countAnimals = animalsList.size();
                        ArrayList<Animal> newAnimals = new ArrayList<>();

                        for (int iteration = 0; iteration < countAnimals; iteration++){
                            if (iteration < countAnimals - iteration - 1){
                                if (animalsList.get(iteration).getCountCycleReproduction() < cycleCounter.getCycleCounter() &&
                                        animalsList.get(countAnimals - iteration - 1).getCountCycleReproduction() < cycleCounter.getCycleCounter()){

                                        Animal newAnimal = animalsList.get(iteration).newObject(name, StatusAnimals.NEWBORN, x, y);
                                        newAnimal.setCountCycleReproduction(cycleCounter.getCycleCounter());
                                        newAnimals.add(newAnimal);

                                        animalsList.get(iteration).setCountCycleReproduction(cycleCounter.getCycleCounter());
                                        animalsList.get(countAnimals - iteration - 1).setCountCycleReproduction(cycleCounter.getCycleCounter());
                                }
                            }
                            else if (iteration == countAnimals - iteration - 1) {
                                animalsList.get(iteration).setCountCycleReproduction(cycleCounter.getCycleCounter());
                            }
                            else{
                                break;
                            }
                        }

                        ArrayList<Animal> newCellListAnimals = cellMap[x][y].listAnimals.get(name);

                        for (int num = 0; num < newAnimals.size(); num++){
                            if (newCellListAnimals.size() < newCellListAnimals.get(0).getMaxPopulation()) {
                                newCellListAnimals.add(newAnimals.get(num));
                                cellMap[x][y].addAnimalsOnType(name);
                                globalStatistics.addStatisticsReproductions(x, y, name);
                            }
                        }
                        cellMap[x][y].listAnimals.put(name, newCellListAnimals);
                    }
                }
            }
           // printState();
        //}

    }



    private void eatAllAnimals(){
        ArrayList<Animal> animalsList;
        //Random random = new Random();

        //for(int i = 0; i < 3; i++) {
           // newCycle();
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    for (NameItem name : cellMap[x][y].listAnimals.keySet()){
                        animalsList = cellMap[x][y].listAnimals.get(name);

                        Iterator<Animal> animalsIterator = animalsList.iterator();
                        while (animalsIterator.hasNext()){
                            Animal animal = animalsIterator.next();

                            if (animal.getCurrentWeightEat() < animal.getMaxWeightEat()) {
                                NameItem nameFoods = getFoods(animal, x, y);
                                if (eatFoods(animal, nameFoods, x, y)){
                                    String str = "The " + name + " ate the " + nameFoods;
                                    globalStatistics.addStatisticsCell(x, y, name, str);
                                    globalStatistics.addStatisticsHaveBeenEaten(x, y, nameFoods);
                                }
                            }
                            else{
                                if (animal.getMaxWeightEat() > 0) {
                                   // System.out.println("CurrentWeightEat = MaxWeightEat");
                                }
                            }
                        }
                    }
                }
            }
        //}
    }


    //private NameItem getFoods(NameItem nameAnimal, int x, int y){
    private NameItem getFoods(Animal animal, int x, int y){
        HashMap<NameItem, ArrayList<Animal>> listAllFoods = new HashMap<>();
        HashMap<NameItem, Double> listMassFactor = new HashMap<>();
        NameItem nameAnimal = animal.getName();
        ArrayList<Animal> listNameFoods;
        NameFoods nameFoods;
        NameItem nameItemFood;

        int numberAnimal = tableEatProbability.getNumberToAnimal(nameAnimal);
        int probabilityEat = 0;
        //int countAllAnimalsCell = 0;
        int countAnimalsOnTypeCell = 0;
        double massFactor;
        double weightAnimal;

        for (int numFood = 0; numFood < COUNT_TYPE_FOODS; numFood++) {
            probabilityEat = tableEatProbability.getProbability(numberAnimal, numFood);
            nameItemFood = tableEatProbability.getAnimalToNumber(numFood);
            if (probabilityEat > 0){
                if (nameItemFood != NameItem.PLANTS) {
                    countAnimalsOnTypeCell = cellMap[x][y].getCountAnimalsOnType(nameItemFood);
                    //countAllAnimalsCell += countAnimalsOnTypeCell;
                    if (countAnimalsOnTypeCell > 0) {
                        weightAnimal = cellMap[x][y].listAnimals.get(nameItemFood).get(0).getWeight();
                        massFactor = countAnimalsOnTypeCell * probabilityEat * weightAnimal;
                        listMassFactor.put(nameItemFood, massFactor);
                    }
                }
                else{
                    double countPlantsCell = plantsMap[x][y].getCurrentBiomassWeight();
                    if (countPlantsCell > 0){
                        double weightPlants = plantsMap[x][y].WEIGHT;
                        massFactor = countPlantsCell * probabilityEat * weightPlants;
                        listMassFactor.put(nameItemFood, massFactor);
                    }
                }
            }
        }

        double maxMassFactor = 0;
        NameItem nameItemMaxMassFactor = null;
        for (Map.Entry<NameItem, Double> pair : listMassFactor.entrySet()){
            NameItem key = pair.getKey();
            Double value = pair.getValue();
            if (value > maxMassFactor){
                maxMassFactor = value;
                nameItemMaxMassFactor = key;
            }
        }

        if (nameItemMaxMassFactor != NameItem.PLANTS) {
            countAnimalsOnTypeCell = cellMap[x][y].getCountAnimalsOnType(nameItemMaxMassFactor);
        }
        else{
            countAnimalsOnTypeCell = plantsMap[x][y].getCurrentBiomassWeight();
        }
       // System.out.println("animal = " + nameAnimal);
        //System.out.println("name foods = " + nameItemMaxMassFactor);
        //System.out.println("count items foods = " + countAnimalsOnTypeCell);

        return nameItemMaxMassFactor;
    }

    //private boolean eatFoods(NameItem nameAnimal, NameItem nameFood, int x, int y){
    private boolean eatFoods(Animal animal, NameItem nameFood, int x, int y){
        Random random = new Random();
        NameItem nameAnimal = animal.getName();
        int numberAnimal = tableEatProbability.getNumberToAnimal(nameAnimal);
        int numberFood = tableEatProbability.getNumberToAnimal(nameFood);
        int probabilityEat = tableEatProbability.getProbability(numberAnimal, numberFood);

        int chanceCatchingAnimal = 0;
        while (chanceCatchingAnimal == 0){
            chanceCatchingAnimal = random.nextInt(100);
        }
        if (chanceCatchingAnimal <= probabilityEat){
            //едим!!!!
            if (nameFood != NameItem.PLANTS) {
                double weightEat = cellMap[x][y].listAnimals.get(nameFood).get(0).getWeight();
                cellMap[x][y].removeAnimalsOnType(nameFood);
                cellMap[x][y].listAnimals.get(nameFood).remove(0);
                animal.addCurrentWeightEat(weightEat);
            }
            else{
                double weightPlants = plantsMap[x][y].getWeight();
                plantsMap[x][y].subtractPlants();
                animal.addCurrentWeightEat(weightPlants);
            }

            //System.out.println("numberAnimal = " + numberAnimal);
            //System.out.println("name = " + nameAnimal);
            //System.out.println("chanceCatchingAnimal = " + chanceCatchingAnimal);
            //System.out.println("eat...");
            //System.out.println("numberEat = " + numberFood);
            //System.out.println("name eat = " + tableEatProbability.getFoodToNumber(numberFood));

            return true;

        }
        //System.out.println("chanceCatchingAnimal = " + chanceCatchingAnimal);
        //System.out.println("didn't eat...");
        return false;
    }

    //вариант случайного выбора еды
    private NameItem getFoodsRandom(NameItem nameAnimal, int x, int y){
        Random random = new Random();
        int numberAnimal = tableEatProbability.getNumberToAnimal(nameAnimal);

        //step - 2 : eat - animal.eat()
        int probabilityEat = 0;
        int numberFood = 0;
        while (probabilityEat == 0) {
            numberFood = random.nextInt(COUNT_TYPE_FOODS);
            if (numberAnimal == numberFood) continue;
            probabilityEat = tableEatProbability.getProbability(numberAnimal, numberFood);
            if (probabilityEat > 0){
                //нужно проверить, что найдем такое животное(еду) в квадрате
                if (tableEatProbability.getFoodToNumber(numberFood) != NameFoods.PLANTS) {
                    if (cellMap[x][y].listAnimals.containsKey(tableEatProbability.getAnimalToNumber(numberFood))){
                        //еду нашли, осталось поймать...
                        break;
                    }
                }
            }
        }
        return null;
    }


    private void moveAllAnimals() {
        ArrayList<Animal> animalsList;
        //for(int i = 0; i < 10; i++) {
            //newCycle();
            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    for (NameItem name : cellMap[x][y].listAnimals.keySet()){
                        animalsList = cellMap[x][y].listAnimals.get(name);

                        Iterator<Animal> animalsIterator = animalsList.iterator();
                        while (animalsIterator.hasNext()){
                            Animal animal = animalsIterator.next();
                            //step - 1 : move - animal.move()
                            //1 - вариант метод move объекта animal
                            if (animal.move(this, animal.getSpeed(), animal.getMaxPopulation())){
                                //printState();
                                animalsIterator.remove();
                                globalStatistics.addStatisticsLeave(x, y, name);
                            }
                            //2 - вариант метод move объекта generalMap
                            // if (animalMove(x, y, name, animal)){
                            //    animalsIterator.remove();
                            //}
                        }
                        //}
                    }
                }
            }
        //}
    }

    private boolean animalMove(int x, int y, NameItem name, Animal animal) {
        Random random = new Random();
        if (animal.getCountCycleMove() < cycleCounter.getCycleCounter()) {
            if (animal.getSpeed() > 0) {
                int countStep = 0;
                while (countStep == 0){
                    countStep = random.nextInt(animal.getSpeed() + 1);
                }
                int direction = 0;
                while (direction == 0) {
                    direction = random.nextInt(COUNT_DIRECTION + 1);
                }

                if (direction == UP_DIR) {
                    int newYMap = animal.yMap + countStep;
                    if (newYMap >= sizeY) {
                        newYMap = sizeY - 1;
                    }
                    if (newYMap == y){
                        animal.setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    int countAnimalsOnTypeNewCell = cellMap[x][newYMap].getCountAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell+1 > animal.getMaxPopulation()){
                        animal.setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    animal.yMap = newYMap;
                    cellMap[x][newYMap].addAnimalsOnType(name);
                    cellMap[x][y].removeAnimalsOnType(name);
                }

                if (direction == RIGHT_DIR) {
                    int newXMap = animal.xMap + countStep;
                    if (newXMap >= sizeX) {
                        newXMap = sizeX - 1;
                    }
                    if (newXMap == x){
                        animal.setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    int countAnimalsOnTypeNewCell = cellMap[newXMap][y].getCountAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell+1 > animal.getMaxPopulation()){
                        animal.setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    animal.xMap = newXMap;
                    cellMap[newXMap][y].addAnimalsOnType(name);
                    cellMap[x][y].removeAnimalsOnType(name);
                }

                if (direction == DOWN_DIR) {
                    int newYMap = animal.yMap - countStep;
                    if (newYMap < 0) {
                        newYMap = 0;
                    }
                    if (newYMap == y){
                        animal.setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    int countAnimalsOnTypeNewCell = cellMap[x][newYMap].getCountAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell+1 > animal.getMaxPopulation()){
                        animal.setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    animal.yMap = newYMap;
                    cellMap[x][newYMap].addAnimalsOnType(name);
                    cellMap[x][y].removeAnimalsOnType(name);
                }

                if (direction == LEFT_DIR){
                    int newXMap = animal.xMap - countStep;
                    if (newXMap < 0) {
                        newXMap = 0;
                    }
                    if (newXMap == x){
                        animal.setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    int countAnimalsOnTypeNewCell = cellMap[newXMap][y].getCountAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell+1 > animal.getMaxPopulation()){
                        animal.setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    animal.xMap = newXMap;
                    cellMap[newXMap][y].addAnimalsOnType(name);
                    cellMap[x][y].removeAnimalsOnType(name);
                }

                animal.setCountCycleMove(cycleCounter.getCycleCounter());
                //animalsIterator.remove();

                int newX = animal.xMap;
                int newY = animal.yMap;
                ArrayList<Animal> newCellListAnimals;
                if (cellMap[newX][newY].listAnimals.containsKey(name)) {
                    newCellListAnimals = cellMap[newX][newY].listAnimals.get(name);
                }
                else{
                    newCellListAnimals = new ArrayList<>();
                }
                newCellListAnimals.add(animal);
                cellMap[newX][newY].listAnimals.put(name, newCellListAnimals);
                //printState();
                return true;
            }
        }
        return false;
    }


    public void initAnimals(){
        for (int x = 0; x < sizeX; x++){
            for (int y = 0; y < sizeY; y++){
                //for(NameAnimals name : NameAnimals.values()){
                 //возможно потом через рефлексию
                //}
                //пока вручную

                Random random = new Random();
                ArrayList<Animal> list;

                //WOLF
                int count = random.nextInt(Wolf.MAX_COUNT_CELL + 1);

                cellMap[x][y].countAnimalsOnType.put(NameItem.WOLF, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.WOLF)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.WOLF);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Wolf(NameItem.WOLF, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.WOLF, list);
                }

                //BOA_CONSTRICTOR
                count = random.nextInt(BoaConstrictor.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.BOA_CONSTRICTOR, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.BOA_CONSTRICTOR)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.BOA_CONSTRICTOR);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new BoaConstrictor(NameItem.BOA_CONSTRICTOR, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.BOA_CONSTRICTOR, list);
                }

                //FOX
                count = random.nextInt(Fox.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.FOX, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.FOX)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.FOX);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Fox(NameItem.FOX, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.FOX, list);
                }

                //BEAR
                count = random.nextInt(Bear.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.BEAR, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.BEAR)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.BEAR);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Bear(NameItem.BEAR, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.BEAR, list);
                }

                //EAGLE
                count = random.nextInt(Eagle.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.EAGLE, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.EAGLE)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.EAGLE);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Eagle(NameItem.EAGLE, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.EAGLE, list);
                }

                //HORSE
                count = random.nextInt(Horse.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.HORSE, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.HORSE)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.HORSE);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Horse(NameItem.HORSE, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.HORSE, list);
                }

                //DEER
                count = random.nextInt(Deer.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.DEER, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.DEER)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.DEER);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Deer(NameItem.DEER, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.DEER, list);
                }

                //RABBIT
                count = random.nextInt(Rabbit.MAX_COUNT_CELL) + 1;

                cellMap[x][y].countAnimalsOnType.put(NameItem.RABBIT, count);
                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.RABBIT)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.RABBIT);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Rabbit(NameItem.RABBIT, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.RABBIT, list);
                }

                //MOUSE
                count = random.nextInt(Mouse.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.MOUSE, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.MOUSE)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.MOUSE);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Mouse(NameItem.MOUSE, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.MOUSE, list);
                }

                //GOAT
                count = random.nextInt(Goat.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.GOAT, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.GOAT)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.GOAT);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Goat(NameItem.GOAT, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.GOAT, list);
                }

                //SHEEP
                count = random.nextInt(Sheep.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.SHEEP, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.SHEEP)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.SHEEP);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Sheep(NameItem.SHEEP, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.SHEEP, list);
                }

                //WILD_BOAR
                count = random.nextInt(WildBoar.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.WILD_BOAR, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.WILD_BOAR)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.WILD_BOAR);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new WildBoar(NameItem.WILD_BOAR, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.WILD_BOAR, list);
                }

                //BUFFALO
                count = random.nextInt(Buffalo.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.BUFFALO, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.BUFFALO)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.BUFFALO);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Buffalo(NameItem.BUFFALO, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.BUFFALO, list);
                }

                //DUCK
                count = random.nextInt(Duck.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.DUCK, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.DUCK)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.DUCK);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Duck(NameItem.DUCK, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.DUCK, list);
                }

                //CATERPILLAR
                count = random.nextInt(Caterpillar.MAX_COUNT_CELL + 1);
                cellMap[x][y].countAnimalsOnType.put(NameItem.CATERPILLAR, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameItem.CATERPILLAR)) {
                        list = cellMap[x][y].listAnimals.get(NameItem.CATERPILLAR);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Caterpillar(NameItem.CATERPILLAR, StatusAnimals.START, x, y));
                    cellMap[x][y].listAnimals.put(NameItem.CATERPILLAR, list);
                }


            }
        }
        /*
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (NameAnimals name : NameAnimals.values()) {
                    if (cellMap[x][y].listAnimals.containsKey(name)) {
                        System.out.println("Init : nameAnimal = " + name + " : (" + x + ";" + y + ")" +
                                cellMap[x][y].contAnimalsOnType.get(name));

                    }
                }
            }
        }

         */
    }









}


