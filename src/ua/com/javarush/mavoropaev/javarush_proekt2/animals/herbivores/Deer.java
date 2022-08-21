package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Deer extends Herbivore {
    public static final double WEIGHT = 300;
    public static final int MAX_COUNT_CELL = 20;//20;
    public static final int MAX_SPEED = 4;
    public static final double MAX_EAT = 50;

    public Deer(NameItem deer, StatusAnimals statusAnimals, int x, int y) {
        super(deer, statusAnimals, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
        super.setCurrentWeightEat(MAX_EAT);
        super.setStatusAnimals(statusAnimals);
        super.setLifeSpan(0);
    }

    @Override
    public Deer newObject(NameItem name, StatusAnimals statusAnimals, int x, int y){
        return new Deer(name, statusAnimals, x, y);
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
