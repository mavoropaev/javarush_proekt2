package ua.com.javarush.mavoropaev.javarush_proekt2.animals;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;

public class Predator extends Animal {
    public Predator(NameItem predator, StatusAnimals statusAnimals, int x, int y) {

        super(predator, statusAnimals, x, y);
    }

    @Override
    public Predator newObject(NameItem predator, StatusAnimals statusAnimals, int x, int y){
        return new Predator(predator, statusAnimals, x, y);
    }

    @Override
    public void eat() {

    }

    @Override
    public boolean move() {
        return super.move();
    }

    @Override
    public void reproduction() {

    }

    @Override
    public void dead() {

    }
}

