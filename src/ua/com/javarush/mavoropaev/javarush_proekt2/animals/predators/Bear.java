package ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Predator;

public class Bear extends Predator {
    public static final double WEIGHT = 500;
    public static final int MAX_COUNT_CELL = 5;
    public static final int MAX_SPEED = 2;
    public static final double MAX_EAT = 80;

    public Bear(NameAnimals bear, int x, int y) {
        super(bear, x, y);
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
