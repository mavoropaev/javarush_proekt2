package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;

public class WildBoar extends Herbivore {
    public static final double WEIGHT = 400;
    public static final int MAX_COUNT_CELL = 50;
    public static final int MAX_SPEED = 2;
    public static final double MAX_EAT = 50;

    public WildBoar(NameAnimals wildBoar, int x, int y) {
        super(wildBoar,x,y);
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
