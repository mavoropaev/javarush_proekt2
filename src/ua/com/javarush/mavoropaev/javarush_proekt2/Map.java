package ua.com.javarush.mavoropaev.javarush_proekt2;

import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Animal;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores.*;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private int sizeX;
    private int sizeY;
    private int countCycle;

    public int getCountCycle() {
        return countCycle;
    }

    public Cell[][] cellMap;

    public Map(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        cellMap = new Cell[sizeX][sizeY];
        initCellMap();
    }

    public void initCellMap(){
        for (int x = 0; x < sizeX; x++){
            for (int y = 0; y < sizeY; y++){
                cellMap[x][y].setX(x);
                cellMap[x][y].setY(y);
            }
        }
    }
    public void initAnimals(){
        for (int x = 0; x < sizeX; x++){
            for (int y = 0; y < sizeY; y++){
                for(NameAnimals name : NameAnimals.values()){
                 //возможно потом через рефлексию
                }
                //пока вручную
                //WOLF
                Random random = new Random();
                int count = random.nextInt(Wolf.MAX_COUNT_CELL);
                List<Animal> list;
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
                //BOA_CONSTRICTOR
                count = random.nextInt(BoaConstrictor.MAX_COUNT_CELL);
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


            }
        }
    }

    public void newCycle(){
        countCycle++;
    }







}


