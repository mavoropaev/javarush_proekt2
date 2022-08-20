package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Goat extends Herbivore {
    public static final double WEIGHT = 60;
    public static final int MAX_COUNT_CELL = 3;//140;
    public static final int MAX_SPEED = 3;
    public static final double MAX_EAT = 10;

    public Goat(NameItem goat, StatusAnimals statusAnimals, int x, int y) {
        super(goat, statusAnimals, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
        super.setCurrentWeightEat(MAX_EAT);
        super.setStatusAnimals(statusAnimals);
        super.setLifespan(0);
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
