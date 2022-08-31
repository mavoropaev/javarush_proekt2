package ua.com.javarush.mavoropaev.javarush_proekt2.map;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Animal;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cell {
    private int x;
    private int y;

    public HashMap<NameItem, CopyOnWriteArrayList<Animal>> listAnimals = new HashMap<>();
    public HashMap<NameItem, Integer> countAnimalsOnType = new HashMap<>();

    public Cell() {
    }

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void increaseCounterAnimalsOnType(NameItem name){
        if (countAnimalsOnType.containsKey(name)){
            int count = countAnimalsOnType.get(name);
            count++;
            countAnimalsOnType.put(name, count);
        }
    }
    public void decrementCounterAnimalsOnType(NameItem name){
        if (countAnimalsOnType.containsKey(name)){
            int count = countAnimalsOnType.get(name);
            count--;
            countAnimalsOnType.put(name, count);
        }
    }
    public int getCounterAnimalsOnType(NameItem name){
        if (countAnimalsOnType.containsKey(name)){
            return countAnimalsOnType.get(name);
        }
        return 0;
    }



}
