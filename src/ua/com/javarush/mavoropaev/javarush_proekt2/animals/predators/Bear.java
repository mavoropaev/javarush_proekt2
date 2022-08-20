package ua.com.javarush.mavoropaev.javarush_proekt2.animals.predators;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Predator;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores.WildBoar;

public class Bear extends Predator {
    public static final double WEIGHT = 500;
    public static final int MAX_COUNT_CELL = 2;//5;
    public static final int MAX_SPEED = 2;
    public static final double MAX_EAT = 80;

    public Bear(NameItem bear, StatusAnimals statusAnimals, int x, int y) {
        super(bear, statusAnimals, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
        super.setCurrentWeightEat(MAX_EAT);
        super.setStatusAnimals(statusAnimals);
        super.setLifespan(0);
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
    public int getSpeed() {
        return MAX_SPEED;
    }

    @Override
    public double getWeight() {
        return WEIGHT;
    }
}
