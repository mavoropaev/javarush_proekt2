package ua.com.javarush.mavoropaev.javarush_proekt2.animals;

import ua.com.javarush.mavoropaev.javarush_proekt2.Map;
import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Animal;

public class Herbivore extends Animal {
    public Herbivore(NameAnimals herbivore, int x, int y) {
        super(herbivore, x, y);
    }

    @Override
    public void eat() {

    }

    @Override
    public boolean move(Map mapGod, int countStep, int maxPopulation) {
        return super.move(mapGod, countStep, maxPopulation);
    }

    @Override
    public void reproduction() {

    }

    @Override
    public void dead() {

    }
}
