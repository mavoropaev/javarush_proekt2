package ua.com.javarush.mavoropaev.javarush_proekt2;

import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cell {
    private int x;
    private int y;

    //public ArrayList<Animal> animals = new ArrayList<>();
    public HashMap<NameAnimals, List<Animal>> listAnimals = new HashMap<>();

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


}
