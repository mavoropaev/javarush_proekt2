package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;

public class Goat extends Herbivore {
    public static final double WEIGHT = 60;
    public static final int MAX_COUNT_CELL = 140;
    public static final int MAX_SPEED = 3;
    public static final double MAX_EAT = 10;

    public Goat(NameAnimals goat, int x, int y) {
        super(goat, x, y);
    }

    @Override
    public int getMaxPopulation() {
        return MAX_COUNT_CELL;
    }

    @Override
    public int getSpeed() {
        return MAX_SPEED;
    }

    @Override
    public double getWeight() {
        return WEIGHT;
    }

}
