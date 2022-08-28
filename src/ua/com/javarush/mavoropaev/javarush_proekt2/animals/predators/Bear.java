package ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Predator;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Bear extends Predator {
    public static final double WEIGHT = 500;
    public static final int MAX_COUNT_CELL = 5;//5;
    public static final int MAX_SPEED = 2;
    public static final double MAX_EAT = 80;
    public static final int PERIOD_REPRODUCTIONS = 1;
    public static final int AMOUNT_OF_CHILDREN = 1;

    public Bear(NameItem bear, StatusAnimals statusAnimals, int x, int y) {
        super(bear, statusAnimals, x, y);
        setMaxWeightEat(MAX_EAT);
        setWeight(WEIGHT);
        setCurrentWeightEat(MAX_EAT);
        setMaxPopulation(MAX_COUNT_CELL);
        setMaxStep(MAX_SPEED);
        setStatusAnimals(statusAnimals);
        setLifeSpan(0);
        setPeriodReproductions(PERIOD_REPRODUCTIONS);
        setAmountOfChildren(AMOUNT_OF_CHILDREN);
    }

    @Override
    public Bear newObject(NameItem name, StatusAnimals statusAnimals, int x, int y){
        return new Bear(name, statusAnimals, x, y);
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
