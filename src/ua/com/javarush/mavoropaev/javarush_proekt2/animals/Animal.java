package ua.com.javarush.mavoropaev.javarush_proekt2.animals;

import ua.com.javarush.mavoropaev.javarush_proekt2.Map;
import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;

public abstract class Animal {
    private NameAnimals name;
    private double weight;
    private int maxPopulation;
    private double speed;
    private double weightEat;


    public int xMap;
    public int yMap;

    public abstract void eat();
    public abstract void move();
    public abstract void reproduction();
    public abstract void dead();

    public void setName(NameAnimals name) {
        this.name = name;
    }

    public Animal(NameAnimals name, int xMap, int yMap) {
        this.name = name;
        this.xMap = xMap;
        this.yMap = yMap;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setMaxPopulation(int maxPopulation) {
        this.maxPopulation = maxPopulation;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setWeightEat(double weightEat) {
        this.weightEat = weightEat;
    }

    protected Animal() {
    }

    public NameAnimals getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public double getSpeed() {
        return speed;
    }

    public double getWeightEat() {
        return weightEat;
    }

}
