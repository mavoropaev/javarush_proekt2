package ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Predator;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Eagle extends Predator {
    public static final double WEIGHT = 6;
    public static final int MAX_COUNT_CELL = 20;//20;
    public static final int MAX_SPEED = 3;
    public static final double MAX_EAT = 1;

    public Eagle(NameItem eagle, StatusAnimals statusAnimals, int x, int y) {
        super(eagle, statusAnimals, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
        super.setCurrentWeightEat(MAX_EAT);
        super.setStatusAnimals(statusAnimals);
        super.setLifeSpan(0);
    }

    @Override
    public Eagle newObject(NameItem name, StatusAnimals statusAnimals, int x, int y){
        return new Eagle(name, statusAnimals, x, y);
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
