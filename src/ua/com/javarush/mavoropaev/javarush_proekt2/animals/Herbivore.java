package ua.com.javarush.mavoropaev.javarush_proekt2.animals;

import ua.com.javarush.mavoropaev.javarush_proekt2.map.GeneralMap;
import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;

public class Herbivore extends Animal {
    public Herbivore(NameItem herbivore, StatusAnimals statusAnimals, int x, int y) {
        super(herbivore, statusAnimals, x, y);
    }

    @Override
    public Herbivore newObject(NameItem herbivore, StatusAnimals statusAnimals, int x, int y){
        return new Herbivore(herbivore, statusAnimals, x, y);
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
