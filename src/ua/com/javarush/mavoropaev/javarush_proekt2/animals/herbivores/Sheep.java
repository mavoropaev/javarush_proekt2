package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;

public class Sheep extends Herbivore {
    public static final double WEIGHT = 70;
    public static  final int MAX_COUNT_CELL = 3;//140;
    public static  final int MAX_SPEED = 3;
    public static  final double MAX_EAT = 15;

    public Sheep(NameItem sheep, int x, int y) {

        super(sheep, x, y);
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
