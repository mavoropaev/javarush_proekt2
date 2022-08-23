package ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Predator;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class BoaConstrictor extends Predator {
    public static final double WEIGHT = 15;
    public static final int MAX_COUNT_CELL = 30;//30;
    public static final int MAX_SPEED = 1;
    public static final double MAX_EAT = 3;
    public static final int PERIOD_REPRODUCTIONS = 1;
    public static final int AMOUNT_OF_CHILDREN = 1;

    public BoaConstrictor(NameItem boaConstrictor, StatusAnimals statusAnimals, int x, int y) {
        super(boaConstrictor, statusAnimals, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
        super.setCurrentWeightEat(MAX_EAT);
        super.setStatusAnimals(statusAnimals);
        super.setLifeSpan(0);
        super.setPeriodReproductions(PERIOD_REPRODUCTIONS);
        super.setAmountOfChildren(AMOUNT_OF_CHILDREN);
    }

    @Override
    public BoaConstrictor newObject(NameItem name, StatusAnimals statusAnimals, int x, int y){
        return new BoaConstrictor(name, statusAnimals, x, y);
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
