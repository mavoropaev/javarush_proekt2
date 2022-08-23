package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Goat extends Herbivore {
    public static final double WEIGHT = 60;
    public static final int MAX_COUNT_CELL = 140;//140;
    public static final int MAX_SPEED = 3;
    public static final double MAX_EAT = 10;
    public static final int PERIOD_REPRODUCTIONS = 1;
    public static final int AMOUNT_OF_CHILDREN = 1;

    public Goat(NameItem goat, StatusAnimals statusAnimals, int x, int y) {
        super(goat, statusAnimals, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
        super.setCurrentWeightEat(MAX_EAT);
        super.setStatusAnimals(statusAnimals);
        super.setLifeSpan(0);
        super.setPeriodReproductions(PERIOD_REPRODUCTIONS);
        super.setAmountOfChildren(AMOUNT_OF_CHILDREN);
    }

    @Override
    public Goat newObject(NameItem name, StatusAnimals statusAnimals, int x, int y){

        return new Goat(name, statusAnimals, x, y);
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
