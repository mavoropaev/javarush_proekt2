package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.StatusAnimals;

public class Mouse extends Herbivore {
    public static final double WEIGHT = 0.05;
    public static final int MAX_COUNT_CELL = 10;//500;
    public static final int MAX_SPEED = 1;
    public static final double MAX_EAT = 0.01;

    public Mouse(NameItem mouse, StatusAnimals statusAnimals, int x, int y) {
        super(mouse, statusAnimals, x, y);
        super.setMaxWeightEat(MAX_EAT);
        super.setWeight(WEIGHT);
        super.setCurrentWeightEat(MAX_EAT);
        super.setStatusAnimals(statusAnimals);
        super.setLifespan(0);
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
    public int getSpeed() {
        return MAX_SPEED;
    }

    @Override
    public double getWeight() {
        return WEIGHT;
    }

}
