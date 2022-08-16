package ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Predator;

public class Wolf extends Predator {
    public static final double WEIGHT = 50;
    public static final int MAX_COUNT_CELL = 2;
    public static final int MAX_SPEED = 3;
    public static final double MAX_EAT = 8;

    public Wolf(NameAnimals wolf, int x, int y) {
        super(wolf, x, y);
    }

    @Override
    public int getMaxPopulation() {
        return MAX_COUNT_CELL;
    }

    @Override
    public int getSpeed() {
        return MAX_SPEED;
    }
}
