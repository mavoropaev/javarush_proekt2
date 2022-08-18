package ua.com.javarush.mavoropaev.javarush_proekt2;

import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Animal;

import java.util.ArrayList;
import java.util.HashMap;

public class Cell {
    private int x;
    private int y;

    public HashMap<NameItem, ArrayList<Animal>> listAnimals = new HashMap<>();
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

    public void addAnimalsOnType(NameItem name){
        if (countAnimalsOnType.containsKey(name)){
            int count = countAnimalsOnType.get(name);
            count++;
            countAnimalsOnType.put(name, count);
        }
    }
    public void removeAnimalsOnType(NameItem name){
        if (countAnimalsOnType.containsKey(name)){
            int count = countAnimalsOnType.get(name);
            count--;
            countAnimalsOnType.put(name, count);
        }
    }
    public int getCountAnimalsOnType(NameItem name){
        if (countAnimalsOnType.containsKey(name)){
            return countAnimalsOnType.get(name);
        }
        return 0;
    }



}
