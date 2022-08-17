package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;

public class Rabbit extends Herbivore {
    public static final double WEIGHT = 2;
    public static final int MAX_COUNT_CELL = 150;
    public static final int MAX_SPEED = 2;
    public static final double MAX_EAT = 0.45;

    public Rabbit(NameAnimals rabbit, int x, int y) {
        super(rabbit, x, y);
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
