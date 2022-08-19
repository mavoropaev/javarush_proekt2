package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;

public class Duck extends Herbivore {
    public static final double WEIGHT = 1;
    public static final int MAX_COUNT_CELL = 3;//200;
    public static final int MAX_SPEED = 4;
    public static final double MAX_EAT = 0.15;

    public Duck(NameItem duck, int x, int y) {
        super(duck, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
    }

    @Override
    public Duck newObject(NameItem name, int x, int y){
        return new Duck(name, x, y);
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
