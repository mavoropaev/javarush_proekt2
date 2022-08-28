package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Mouse extends Herbivore {
    public static final double WEIGHT = 0.05;
    public static final int MAX_COUNT_CELL = 500;//500;
    public static final int MAX_SPEED = 1;
    public static final double MAX_EAT = 0.01;
    public static final int PERIOD_REPRODUCTIONS = 5;
    public static final int AMOUNT_OF_CHILDREN = 1;

    public Mouse(NameItem mouse, StatusAnimals statusAnimals, int x, int y) {
        super(mouse, statusAnimals, x, y);
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
    public Mouse newObject(NameItem name, StatusAnimals statusAnimals, int x, int y){
        return new Mouse(name, statusAnimals, x, y);
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
