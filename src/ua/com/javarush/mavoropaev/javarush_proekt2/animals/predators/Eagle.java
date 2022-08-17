package ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Predator;

public class Eagle extends Predator {
    public static final double WEIGHT = 6;
    public static final int MAX_COUNT_CELL = 20;
    public static final int MAX_SPEED = 3;
    public static final double MAX_EAT = 1;

    public Eagle(NameAnimals eagle, int x, int y) {
        super(eagle, x, y);
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
