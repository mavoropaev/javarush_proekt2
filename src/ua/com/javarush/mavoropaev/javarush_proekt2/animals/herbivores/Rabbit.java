package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Rabbit extends Herbivore {
    public static final double WEIGHT = 2;
    public static final int MAX_COUNT_CELL = 150;//150;
    public static final int MAX_SPEED = 2;
    public static final double MAX_EAT = 0.45;
    public static final int PERIOD_REPRODUCTIONS = 1;
    public static final int AMOUNT_OF_CHILDREN = 1;

    public Rabbit(NameItem rabbit, StatusAnimals statusAnimals, int x, int y) {
        super(rabbit, statusAnimals, x, y);
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
    public Rabbit newObject(NameItem name, StatusAnimals statusAnimals, int x, int y){
        return new Rabbit(name, statusAnimals, x, y);
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
