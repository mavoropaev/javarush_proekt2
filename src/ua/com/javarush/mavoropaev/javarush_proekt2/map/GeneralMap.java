package ua.com.javarush.mavoropaev.javarush_proekt2.map;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.CycleCounter;
import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameFoods;
import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Animal;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.TableEatProbability;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores.*;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators.*;
import ua.com.javarush.mavoropaev.javarush_proekt2.plants.Plants;
import ua.com.javarush.mavoropaev.javarush_proekt2.service.Parameters;
import ua.com.javarush.mavoropaev.javarush_proekt2.statistics.GlobalStatistics;

import java.util.*;

import static java.lang.Thread.sleep;

public class GeneralMap {
    private int sizeX;
    private int sizeY;

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
    Parameters parameters = Parameters.getInstance();

    private static volatile GeneralMap instance;
    public static GeneralMap getInstance(){
        GeneralMap localInstance = instance;
        if (localInstance == null){
            synchronized (GeneralMap.class){
                localInstance = instance;
                if (localInstance == null){
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

    public volatile List<Thread> threadList = new ArrayList<>(parameters.ITEM_COUNT);

    public class itemThread implements Runnable{
        private NameItem nameItem;

        public itemThread(NameItem nameItem){
            this.nameItem = nameItem;
        }

        @Override
        public void run() {

            for (int i = 0; i < parameters.getCountCycle(); i++) {
                while (i >= cycleCounter.getCycleCounter()){
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                restorePlants();
                checkDeathAnimals(nameItem);
                eatAllAnimals();
                reproductionAllAnimals();
                moveAllAnimals();

            }
        }
    }

    public class StatisticsThread implements Runnable{

        public StatisticsThread(){
        }

        @Override
        public void run() {
            for (int i = 0; i < parameters.getCountCycle(); i++) {
                synchronized (GlobalStatistics.class) {
                    newCycle();
                    globalStatistics.setCellStatisticsBeginCycle();
                }

                synchronized (GlobalStatistics.class) {
                    globalStatistics.setCellStatisticsEndCycle();
                    for (Integer x : parameters.getKeySet()) {
                        for (Integer y : parameters.getKeyValue(x)) {
                            globalStatistics.printStatistics(x, y);
                        }
                    }
                }
            }
        }
    }

    public void start(){
        tableEatProbability = new TableEatProbability();
        initCellMap();
        initPlantsMap();
        initAnimals();

        Thread statisticsThread = new Thread(new StatisticsThread());
        statisticsThread.start();

        for (NameItem name : NameItem.values()) {
            Thread itemThread = new Thread(new itemThread(name));
            threadList.add(itemThread);
            itemThread.start();
        }

    }

    public void newCycle(){
        cycleCounter.increaseCycleCounter();
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

    private void checkDeathAnimals(NameItem name){
        ArrayList<Animal> animalsList;

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                //for (NameItem name : cellMap[x][y].listAnimals.keySet()){
                    if (cellMap[x][y].listAnimals.containsKey(name)) {
                        animalsList = cellMap[x][y].listAnimals.get(name);

                        Iterator<Animal> animalsIterator = animalsList.iterator();
                        while (animalsIterator.hasNext()) {
                            Animal animal = animalsIterator.next();

                            if (animal.getCountCycleCheckDeath() < cycleCounter.getCycleCounter()) {
                                //checkStatistics1(x, y, name, animal);
                                animal.setCurrentWeightEat(animal.getCurrentWeightEat() - animal.getMaxWeightEat() * 0.25);
                                //checkStatistics2(name, animal);
                                if (animal.getCurrentWeightEat() <= 0 && animal.getMaxWeightEat() > 0) {
                                    animalsIterator.remove();
                                    cellMap[x][y].decrementCounterAnimalsOnType(name);
                                    globalStatistics.addStatisticsDeath(x, y, name);

                                }
                            }
                        }
                    }
                //}
            }
        }

    }

    private void checkStatistics2(NameItem name, Animal animal) {
        if (name == NameItem.BUFFALO) {
            System.out.println("animal.getCurrentWeightEatEndCycle() = " + animal.getCurrentWeightEat());
            System.out.println("--------------------------------------------------------");
        }
    }

    private void checkStatistics1(int x, int y, NameItem name, Animal animal) {
        if (name == NameItem.BUFFALO) {
            System.out.println(x +" : " + y +" -------------------------------------------------------");
            System.out.println("animal.getCurrentWeightEatStartCycle() = " + animal.getCurrentWeightEat());
            System.out.println("animal.getMaxWeightEat() * 0.25 = " + animal.getMaxWeightEat() * 0.25);
        }
    }

    private void reproductionAllAnimals(){
        ArrayList<Animal> animalsList;

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (NameItem name : cellMap[x][y].listAnimals.keySet()){

                    animalsList = cellMap[x][y].listAnimals.get(name);
                    int countAnimals = animalsList.size();
                    ArrayList<Animal> newAnimals = new ArrayList<>();

                    for (int iteration = 0; iteration < countAnimals; iteration++){
                        if (iteration < countAnimals - iteration - 1){
                            if (cycleCounter.getCycleCounter() - animalsList.get(iteration).getCountCycleReproduction() > animalsList.get(iteration).getPeriodReproductions() &&
                                    cycleCounter.getCycleCounter() - animalsList.get(countAnimals - iteration - 1).getCountCycleReproduction() > animalsList.get(countAnimals - iteration - 1).getPeriodReproductions() ){

                                for (int count = 0; count < animalsList.get(iteration).getAmountOfChildren(); count++) {
                                    Animal newAnimal = animalsList.get(iteration).newObject(name, StatusAnimals.NEWBORN, x, y);
                                    newAnimal.setCountCycleReproduction(cycleCounter.getCycleCounter());
                                    newAnimals.add(newAnimal);
                                }

                                animalsList.get(iteration).setCountCycleReproduction(cycleCounter.getCycleCounter());
                                animalsList.get(countAnimals - iteration - 1).setCountCycleReproduction(cycleCounter.getCycleCounter());
                            }
                        }
                        else if (iteration == countAnimals - iteration - 1) {
                            //animalsList.get(iteration).setCountCycleReproduction(cycleCounter.getCycleCounter());
                        }
                        else{
                            break;
                        }
                    }

                    ArrayList<Animal> newCellListAnimals = cellMap[x][y].listAnimals.get(name);

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
    }

    private void eatAllAnimals(){
        ArrayList<Animal> animalsList;

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (NameItem name : cellMap[x][y].listAnimals.keySet()){

                    animalsList = cellMap[x][y].listAnimals.get(name);

                    for (Animal animal : animalsList) {
                        if (animal.getCurrentWeightEat() < animal.getMaxWeightEat()) {
                            //NameItem nameFoods = getFoods(animal, x, y);
                            NameItem nameFoods = getFoodsRandom(name, x, y);

                            if (nameFoods != null && eatFoods(animal, nameFoods, x, y)) {
                                //System.out.println("Animal = " + animal.toString() + " ---> " + nameFoods + " : Ok");
                                String str = "The " + name + " ate the " + nameFoods;
                                globalStatistics.addStatisticsCell(x, y, name, str);
                                globalStatistics.addStatisticsHaveBeenEaten(x, y, nameFoods);

                            }
                            else{
                                //System.out.println("Animal = " + animal.toString() + " ---> " + nameFoods + " : Cancel");
                            }
                        }
                    }
                }
            }
        }
    }

    private NameItem getFoods(Animal animal, int x, int y){
        HashMap<NameItem, ArrayList<Animal>> listAllFoods = new HashMap<>();
        HashMap<NameItem, Double> listMassFactor = new HashMap<>();
        NameItem nameAnimal = animal.getName();
        NameItem nameItemFood;

        int numberAnimal = tableEatProbability.getNumberToAnimal(nameAnimal);
        int probabilityEat = 0;
        int countAnimalsOnTypeCell = 0;
        double massFactor;
        double weightAnimal;

        for (int numFood = 0; numFood < COUNT_TYPE_FOODS; numFood++) {
            probabilityEat = tableEatProbability.getProbability(numberAnimal, numFood);
            nameItemFood = tableEatProbability.getAnimalToNumber(numFood);
            if (probabilityEat > 0){
                if (nameItemFood != NameItem.PLANTS) {
                    countAnimalsOnTypeCell = cellMap[x][y].getCounterAnimalsOnType(nameItemFood);

                    if (countAnimalsOnTypeCell > 0) {
                        weightAnimal = cellMap[x][y].listAnimals.get(nameItemFood).get(0).getWeight();
                        massFactor = countAnimalsOnTypeCell * probabilityEat * weightAnimal;
                        listMassFactor.put(nameItemFood, massFactor);
                    }
                }
                else{
                    double countPlantsCell = plantsMap[x][y].getCurrentBiomassWeight();
                    if (countPlantsCell > 0){
                        double weightPlants = Plants.WEIGHT;
                        massFactor = countPlantsCell * probabilityEat * weightPlants;
                        listMassFactor.put(nameItemFood, massFactor);
                    }
                }
                //System.out.println(listMassFactor.toString());
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
        return nameItemMaxMassFactor;
    }

    //вариант случайного выбора еды
    private NameItem getFoodsRandom(NameItem nameAnimal, int x, int y){
        ArrayList<NameItem> listFood = new ArrayList<>();
        Random random = new Random();
        int numberAnimal = tableEatProbability.getNumberToAnimal(nameAnimal);

        for (NameItem name : NameItem.values()){
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
                }
                else{
                    if (plantsMap[x][y].getCurrentBiomassWeight() > 0){
                        listFood.add(name);
                    }
                }
            }
        }
        if (listFood.size() > 0){
            int numberFood = 0;
            while (numberFood == 0){
                numberFood = random.nextInt(listFood.size() + 1);
            }
            return listFood.get(numberFood - 1);
        }
        return null;
    }

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

                globalStatistics.addStatisticsWhoAteWho(x, y, nameAnimal, nameFood);

                cellMap[x][y].decrementCounterAnimalsOnType(nameFood);
                cellMap[x][y].listAnimals.get(nameFood).remove(0);
                animal.addCurrentWeightEat(weightEat);

            }
            else{
                double weightPlants = plantsMap[x][y].getWeight();
                plantsMap[x][y].subtractPlants();
                animal.addCurrentWeightEat(weightPlants);

                globalStatistics.addStatisticsWhoAteWho(x, y, nameAnimal, NameItem.PLANTS);

            }
            return true;
        }
        return false;
    }

    private void moveAllAnimals() {
        ArrayList<Animal> animalsList;

        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (NameItem name : cellMap[x][y].listAnimals.keySet()){

                    animalsList = cellMap[x][y].listAnimals.get(name);

                    Iterator<Animal> animalsIterator = animalsList.iterator();
                    while (animalsIterator.hasNext()){
                        Animal animal = animalsIterator.next();
                        //1 - вариант метод move объекта animal
                        if (animal.move(animal.getSpeed(), animal.getMaxPopulation())){
                            //printState();
                            animalsIterator.remove();
                            globalStatistics.addStatisticsLeave(x, y, name);
                        }
                        //2 - вариант метод move объекта generalMap
                        // if (animalMove(x, y, name, animal)){
                        //    animalsIterator.remove();
                        //}
                    }
                }
            }
        }
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
                    int countAnimalsOnTypeNewCell = cellMap[x][newYMap].getCounterAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell+1 > animal.getMaxPopulation()){
                        animal.setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    animal.yMap = newYMap;
                    cellMap[x][newYMap].increaseCounterAnimalsOnType(name);
                    cellMap[x][y].decrementCounterAnimalsOnType(name);
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
                    int countAnimalsOnTypeNewCell = cellMap[newXMap][y].getCounterAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell+1 > animal.getMaxPopulation()){
                        animal.setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    animal.xMap = newXMap;
                    cellMap[newXMap][y].increaseCounterAnimalsOnType(name);
                    cellMap[x][y].decrementCounterAnimalsOnType(name);
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
                    int countAnimalsOnTypeNewCell = cellMap[x][newYMap].getCounterAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell+1 > animal.getMaxPopulation()){
                        animal.setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    animal.yMap = newYMap;
                    cellMap[x][newYMap].increaseCounterAnimalsOnType(name);
                    cellMap[x][y].decrementCounterAnimalsOnType(name);
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
                    int countAnimalsOnTypeNewCell = cellMap[newXMap][y].getCounterAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell+1 > animal.getMaxPopulation()){
                        animal.setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    animal.xMap = newXMap;
                    cellMap[newXMap][y].increaseCounterAnimalsOnType(name);
                    cellMap[x][y].decrementCounterAnimalsOnType(name);
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
    }

    public void printState(){
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


}


