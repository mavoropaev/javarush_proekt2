package ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Predator;

public class BoaConstrictor extends Predator {
    public static final double WEIGHT = 15;
    public static final int MAX_COUNT_CELL = 30;
    public static final int MAX_SPEED = 1;
    public static final double MAX_EAT = 3;

    public BoaConstrictor(NameAnimals boaConstrictor, int x, int y) {
        super(boaConstrictor, x, y);
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
