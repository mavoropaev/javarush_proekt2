package ua.com.javarush.mavoropaev.javarush_proekt2;

import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Animal;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Map {
    private int sizeX;
    private int sizeY;
    private int countCycle;

    public final int COUNT_DIRECTION = 4;
    public final int UP_DIR = 1;
    public final int RIGHT_DIR = 2;
    public final int DOWN_DIR = 3;
    public final int LEFT_DIR = 4;

    public Cell[][] cellMap;

    public void newCycle(){
        printState();
        countCycle++;
    }

    public void printState(){
        System.out.println("cycle = " + countCycle);
        System.out.println("-------------------------");
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                for (NameAnimals name : NameAnimals.values()) {
                    if (cellMap[x][y].listAnimals.containsKey(name)) {
                        System.out.println("Cycle : nameAnimal = " + name + " : (" + x + ";" + y + ")" +
                                cellMap[x][y].contAnimalsOnType.get(name));

                    }
                }
            }
        }
        System.out.println("-------------------------");
    }
    public int getCountCycle() {
        return countCycle;
    }

    public Map(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        cellMap = new Cell[sizeX][sizeY];
    }

    public void initCellMap(){
        for (int x = 0; x < sizeX; x++){
            for (int y = 0; y < sizeY; y++){
                cellMap[x][y] = new Cell(x, y);
            }
        }
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void start(){
        Random random = new Random();
        ArrayList<Animal> animalsList;

        initCellMap();
        initAnimals();

        for(int i = 0; i < 10; i++) {
            newCycle();

            for (int x = 0; x < sizeX; x++) {
                for (int y = 0; y < sizeY; y++) {
                    for (NameAnimals name : NameAnimals.values()) {
                        if (cellMap[x][y].listAnimals.containsKey(name)) {
                            animalsList = cellMap[x][y].listAnimals.get(name);

                            Iterator<Animal> animalsIterator = animalsList.iterator();
                            while (animalsIterator.hasNext()){
                                Animal animal = animalsIterator.next();
                                //step - 1 : move - animal.move()
                                if (animal.move(this, animal.getSpeed(), animal.getMaxPopulation())){
                                    printState();
                                    animalsIterator.remove();
                                }

                                // if (animalsMove(x, y, name, animal)){
                                //    animalsIterator.remove();
                                //}
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean animalsMove(int x, int y, NameAnimals name, Animal animal) {
        Random random = new Random();
        if (animal.getCountCycleMove() < getCountCycle()) {
            if (animal.getSpeed() > 0) {
                int countStep = 0;
                while (countStep == 0){
                    countStep = random.nextInt(animal.getSpeed());
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
                        animal.setCountCycleMove(getCountCycle());
                        return false;
                    }
                    int countAnimalsOnTypeNewCell = cellMap[x][newYMap].getAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell+1 > animal.getMaxPopulation()){
                        animal.setCountCycleMove(getCountCycle());
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
                        animal.setCountCycleMove(getCountCycle());
                        return false;
                    }
                    int countAnimalsOnTypeNewCell = cellMap[newXMap][y].getAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell+1 > animal.getMaxPopulation()){
                        animal.setCountCycleMove(getCountCycle());
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
                        animal.setCountCycleMove(getCountCycle());
                        return false;
                    }
                    int countAnimalsOnTypeNewCell = cellMap[x][newYMap].getAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell+1 > animal.getMaxPopulation()){
                        animal.setCountCycleMove(getCountCycle());
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
                        animal.setCountCycleMove(getCountCycle());
                        return false;
                    }
                    int countAnimalsOnTypeNewCell = cellMap[newXMap][y].getAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell+1 > animal.getMaxPopulation()){
                        animal.setCountCycleMove(getCountCycle());
                        return false;
                    }
                    animal.xMap = newXMap;
                    cellMap[newXMap][y].addAnimalsOnType(name);
                    cellMap[x][y].removeAnimalsOnType(name);
                }

                animal.setCountCycleMove(getCountCycle());
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
                printState();
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

                cellMap[x][y].contAnimalsOnType.put(NameAnimals.WOLF, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.WOLF)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.WOLF);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Wolf(NameAnimals.WOLF, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.WOLF, list);
                }
            /*
                //BOA_CONSTRICTOR
                count = random.nextInt(BoaConstrictor.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.BOA_CONSTRICTOR, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.BOA_CONSTRICTOR)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.BOA_CONSTRICTOR);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new BoaConstrictor(NameAnimals.BOA_CONSTRICTOR, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.BOA_CONSTRICTOR, list);
                }

                //FOX
                count = random.nextInt(Fox.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.FOX, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.FOX)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.FOX);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Fox(NameAnimals.FOX, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.FOX, list);
                }

                //BEAR
                count = random.nextInt(Bear.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.BEAR, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.BEAR)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.BEAR);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Bear(NameAnimals.BEAR, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.BEAR, list);
                }

                //EAGLE
                count = random.nextInt(Eagle.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.EAGLE, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.EAGLE)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.EAGLE);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Eagle(NameAnimals.EAGLE, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.EAGLE, list);
                }

                //HORSE
                count = random.nextInt(Horse.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.HORSE, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.HORSE)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.HORSE);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Horse(NameAnimals.HORSE, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.HORSE, list);
                }

                //DEER
                count = random.nextInt(Deer.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.DEER, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.DEER)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.DEER);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Deer(NameAnimals.DEER, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.DEER, list);
                }

                //RABBIT
                count = random.nextInt(Rabbit.MAX_COUNT_CELL);

                cellMap[x][y].contAnimalsOnType.put(NameAnimals.RABBIT, count);
                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.RABBIT)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.RABBIT);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Rabbit(NameAnimals.RABBIT, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.RABBIT, list);
                }

                //MOUSE
                count = random.nextInt(Mouse.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.MOUSE, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.MOUSE)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.MOUSE);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Mouse(NameAnimals.MOUSE, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.MOUSE, list);
                }

                //GOAT
                count = random.nextInt(Goat.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.GOAT, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.GOAT)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.GOAT);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Goat(NameAnimals.GOAT, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.GOAT, list);
                }

                //SHEEP
                count = random.nextInt(Sheep.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.SHEEP, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.SHEEP)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.SHEEP);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Sheep(NameAnimals.SHEEP, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.SHEEP, list);
                }

                //WILD_BOAR
                count = random.nextInt(WildBoar.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.WILD_BOAR, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.WILD_BOAR)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.WILD_BOAR);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new WildBoar(NameAnimals.WILD_BOAR, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.WILD_BOAR, list);
                }

                //BUFFALO
                count = random.nextInt(Buffalo.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.BUFFALO, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.BUFFALO)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.BUFFALO);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Buffalo(NameAnimals.BUFFALO, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.BUFFALO, list);
                }

                //DUCK
                count = random.nextInt(Duck.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.DUCK, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.DUCK)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.DUCK);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Duck(NameAnimals.DUCK, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.DUCK, list);
                }

                //CATERPILLAR
                count = random.nextInt(Caterpillar.MAX_COUNT_CELL);
                cellMap[x][y].contAnimalsOnType.put(NameAnimals.CATERPILLAR, count);

                for (int i = 0; i < count; i++){
                    if (cellMap[x][y].listAnimals.containsKey(NameAnimals.CATERPILLAR)) {
                        list = cellMap[x][y].listAnimals.get(NameAnimals.CATERPILLAR);
                    }
                    else{
                        list = new ArrayList<>();
                    }
                    list.add(new Caterpillar(NameAnimals.CATERPILLAR, x, y));
                    cellMap[x][y].listAnimals.put(NameAnimals.CATERPILLAR, list);
                }
            */

            }
        }
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
    }









}


