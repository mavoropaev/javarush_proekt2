package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Caterpillar extends Herbivore {
    public static final double WEIGHT = 0.01;
    public static  final int MAX_COUNT_CELL = 1000;//1000;
    public static  final int MAX_SPEED = 0;
    public static  final double MAX_EAT = 0;

    public Caterpillar(NameItem caterpillar, StatusAnimals statusAnimals, int x, int y) {
        super(caterpillar, statusAnimals, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
        super.setCurrentWeightEat(MAX_EAT);
        super.setStatusAnimals(statusAnimals);
        super.setLifeSpan(0);
    }

    @Override
    public Caterpillar newObject(NameItem name, StatusAnimals statusAnimals, int x, int y){

        return new Caterpillar(name, statusAnimals, x, y);
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
