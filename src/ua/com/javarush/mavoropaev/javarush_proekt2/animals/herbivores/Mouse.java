package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;

public class Mouse extends Herbivore {
    public static final double WEIGHT = 0.05;
    public static final int MAX_COUNT_CELL = 500;
    public static final int MAX_SPEED = 1;
    public static final double MAX_EAT = 0.01;

    public Mouse(NameAnimals mouse, int x, int y) {
        super(mouse, x, y);
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
