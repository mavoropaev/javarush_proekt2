package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Sheep extends Herbivore {
    public static final double WEIGHT = 70;
    public static  final int MAX_COUNT_CELL = 140;//140;
    public static  final int MAX_SPEED = 3;
    public static  final double MAX_EAT = 15;
    public static final int PERIOD_REPRODUCTIONS = 1;
    public static final int AMOUNT_OF_CHILDREN = 1;

    public Sheep(NameItem sheep, StatusAnimals statusAnimals, int x, int y) {
        super(sheep, statusAnimals, x, y);
        setMaxWeightEat(MAX_EAT);
        setWeight(WEIGHT);
        setCurrentWeightEat(MAX_EAT);
        setStatusAnimals(statusAnimals);
        setLifeSpan(0);
        setPeriodReproductions(PERIOD_REPRODUCTIONS);
        setAmountOfChildren(AMOUNT_OF_CHILDREN);
    }

    @Override
    public Sheep newObject(NameItem name, StatusAnimals statusAnimals, int x, int y){
        return new Sheep(name, statusAnimals, x, y);
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
