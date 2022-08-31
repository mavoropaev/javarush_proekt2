package ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Predator;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Eagle extends Predator {
    public static final double WEIGHT = 6;
    public static final int MAX_COUNT_CELL = 20;//20;
    public static final int MAX_SPEED = 3;
    public static final double MAX_EAT = 1;
    public static final int PERIOD_REPRODUCTIONS = 3;
    public static final int AMOUNT_OF_CHILDREN = 1;

    public Eagle(NameItem eagle, StatusAnimals statusAnimals, int x, int y) {
        super(eagle, statusAnimals, x, y);
        setMaxWeightEat(MAX_EAT);
        setWeight(WEIGHT);
        setCurrentWeightEat(MAX_EAT);
        setMaxPopulation(MAX_COUNT_CELL);
        setMaxStep(MAX_SPEED);
        setStatusAnimals(statusAnimals);
        setPeriodReproductions(PERIOD_REPRODUCTIONS);
        setAmountOfChildren(AMOUNT_OF_CHILDREN);
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
    public int getMaxStep() {
        return MAX_SPEED;
    }

    @Override
    public double getWeight() {
        return WEIGHT;
    }

}
