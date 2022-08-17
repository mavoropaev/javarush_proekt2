package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;

public class Caterpillar extends Herbivore {
    public static final double WEIGHT = 0.01;
    public static  final int MAX_COUNT_CELL = 1000;
    public static  final int MAX_SPEED = 0;
    public static  final double MAX_EAT = 0;

    public Caterpillar(NameAnimals caterpillar, int x, int y) {
        super(caterpillar, x, y);
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
