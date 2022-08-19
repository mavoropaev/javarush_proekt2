package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;

public class Horse extends Herbivore {
    public static final double WEIGHT = 400;
    public static final int MAX_COUNT_CELL = 3;//20;
    public static final int MAX_SPEED = 4;
    public static final double MAX_EAT = 60;

    public Horse(NameItem horse, int x, int y) {
        super(horse, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
    }

    @Override
    public Horse newObject(NameItem name, int x, int y){
        return new Horse(name, x, y);
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
