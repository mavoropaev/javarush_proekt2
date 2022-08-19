package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators.Wolf;

public class Buffalo extends Herbivore {
    public static final double WEIGHT = 700;
    public static final int MAX_COUNT_CELL = 3;//10;
    public static final int MAX_SPEED = 3;
    public static final double MAX_EAT = 100;

    public Buffalo(NameItem buffalo, int x, int y) {
        super(buffalo, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
    }

    @Override
    public Buffalo newObject(NameItem name, int x, int y){
        return new Buffalo(name, x, y);
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
