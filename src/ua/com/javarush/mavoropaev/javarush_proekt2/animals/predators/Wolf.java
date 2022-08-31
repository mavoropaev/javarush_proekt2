package ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Predator;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Wolf extends Predator {
    public static final double WEIGHT = 50;
    public static final int MAX_COUNT_CELL = 30;//30;
    public static final int MAX_SPEED = 3;
    public static final double MAX_EAT = 8;
    public static final int PERIOD_REPRODUCTIONS = 2;
    public static final int AMOUNT_OF_CHILDREN = 1;


    public Wolf(NameItem wolf, StatusAnimals statusAnimals, int x, int y) {
        super(wolf, statusAnimals, x, y);
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
    public Wolf newObject(NameItem name, StatusAnimals statusAnimals, int x, int y){
        return new Wolf(name, statusAnimals, x, y);
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
