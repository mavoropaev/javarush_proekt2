package ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Predator;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Wolf extends Predator {
    public static final double WEIGHT = 50;
    public static final int MAX_COUNT_CELL = 3;//30;
    public static final int MAX_SPEED = 3;
    public static final double MAX_EAT = 8;


    public Wolf(NameItem wolf, StatusAnimals statusAnimals, int x, int y) {
        super(wolf, statusAnimals, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
        super.setCurrentWeightEat(MAX_EAT);
        super.setStatusAnimals(statusAnimals);
        super.setLifespan(0);
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
    public int getSpeed() {
        return MAX_SPEED;
    }

    @Override
    public double getWeight() {
        return WEIGHT;
    }
}
