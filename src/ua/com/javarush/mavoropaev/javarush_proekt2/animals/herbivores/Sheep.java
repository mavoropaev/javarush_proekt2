package ua.com.javarush.mavoropaev.javarush_proekt2.animals.herbivores;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Herbivore;

public class Sheep extends Herbivore {
    public static final double WEIGHT = 70;
    public static  final int MAX_COUNT_CELL = 140;
    public static  final int MAX_SPEED = 3;
    public static  final double MAX_EAT = 15;

    public Sheep(NameAnimals sheep, int x, int y) {
        super(sheep, x, y);
    }

}
