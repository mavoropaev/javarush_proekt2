package ua.com.javarush.mavoropaev.javarush_proekt2.animals;

import ua.com.javarush.mavoropaev.javarush_proekt2.GeneralMap;
import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;

public class Predator extends Animal {
    public Predator(NameAnimals predator, int x, int y) {
        super(predator, x, y);
    }

    @Override
    public void eat() {

    }

    @Override
    public boolean move(GeneralMap generalMap, int countStep, int maxPopulation) {
        return super.move(generalMap, countStep, maxPopulation);
    }

    @Override
    public void reproduction() {

    }

    @Override
    public void dead() {

    }
}

