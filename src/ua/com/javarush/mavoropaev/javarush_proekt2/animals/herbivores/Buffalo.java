package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators.Wolf;

public class Buffalo extends Herbivore {
    public static final double WEIGHT = 700;
    public static final int MAX_COUNT_CELL = 10;//10;
    public static final int MAX_SPEED = 3;
    public static final double MAX_EAT = 100;

    public Buffalo(NameItem buffalo, StatusAnimals statusAnimals, int x, int y) {
        super(buffalo, statusAnimals, x, y);
        super.setWeight(WEIGHT);
        super.setMaxWeightEat(MAX_EAT);
        super.setCurrentWeightEat(MAX_EAT);
        super.setStatusAnimals(statusAnimals);
        super.setLifespan(0);
    }

    @Override
    public Buffalo newObject(NameItem name, StatusAnimals statusAnimals, int x, int y){
        return new Buffalo(name, statusAnimals, x, y);
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
