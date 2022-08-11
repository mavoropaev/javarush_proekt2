package ua.com.javarush.mavoropaev.javarush_proekt2.animals;

public abstract class Animal {
    private String name;
    private double weight;
    private int maxPopulation;
    private double speed;
    private double weightEat;

    public abstract void eat();
    public abstract void move();
    public abstract void reproduction();
    public abstract void dead();

    public void setName(String name) {
        this.name = name;
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

    public String getName() {
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
