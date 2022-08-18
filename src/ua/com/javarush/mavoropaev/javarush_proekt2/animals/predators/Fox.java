package ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Predator;

public class Fox extends Predator {
    public static final double WEIGHT = 8;
    public static final int MAX_COUNT_CELL = 3;//30;
    public static final int MAX_SPEED = 2;
    public static final double MAX_EAT = 2;

    public Fox(NameItem fox, int x, int y) {

        super(fox, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
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
