package ua.com.javarush.mavoropaev.javarush_proekt2.animals;

import ua.com.javarush.mavoropaev.javarush_proekt2.GeneralMap;
import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;

import java.util.ArrayList;
import java.util.Random;

public abstract class Animal {
    private NameAnimals name;
    private double weight;
    private int maxPopulation;
    private int speed;
    private double weightEat;
    private int countCycleMove;
    private int countCycleEat;
    private int countCycleReproduction;

    public final int COUNT_DIRECTION = 4;
    public final int UP_DIR = 1;
    public final int RIGHT_DIR = 2;
    public final int DOWN_DIR = 3;
    public final int LEFT_DIR = 4;

    public void setCountCycleReproduction(int countCycleReproduction) {
        this.countCycleReproduction = countCycleReproduction;
    }

    public int getCountCycleReproduction() {
        return countCycleReproduction;
    }

    public void setCountCycleEat(int countCycleEat) {
        this.countCycleEat = countCycleEat;
    }

    public int getCountCycleEat() {
        return countCycleEat;
    }

    public void setCountCycleMove(int countCycleMove) {
        this.countCycleMove = countCycleMove;
    }

    public int getCountCycleMove() {
        return countCycleMove;
    }

    public int xMap;
    public int yMap;

    public abstract void eat();

    public abstract void reproduction();
    public abstract void dead();

    public void setName(NameAnimals name) {
        this.name = name;
    }

    public Animal(NameAnimals name, int xMap, int yMap) {
        this.name = name;
        this.xMap = xMap;
        this.yMap = yMap;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setMaxPopulation(int maxPopulation) {
        this.maxPopulation = maxPopulation;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setWeightEat(double weightEat) {
        this.weightEat = weightEat;
    }

    protected Animal() {
    }

    public NameAnimals getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public int getSpeed() {
        return speed;
    }

    public double getWeightEat() {
        return weightEat;
    }

    public boolean move(GeneralMap generalMapGod, int maxCountStep, int maxPopulation) {
        //step - 1 : move - animal.move()
        Random random = new Random();

        if (getCountCycleMove() < generalMapGod.getCountCycle()) {
            if (maxCountStep > 0) {
                int countStep = 0;
                while (countStep == 0){
                    countStep = random.nextInt(maxCountStep + 1);
                }
                int direction = 0;
                while (direction == 0) {
                    direction = random.nextInt(COUNT_DIRECTION + 1);
                }

                if (direction == UP_DIR) {
                    int newYMap = yMap + countStep;
                    if (newYMap >= generalMapGod.getSizeY()) {
                        newYMap = generalMapGod.getSizeY() - 1;
                    }
                    if (newYMap == yMap) {
                        setCountCycleMove(generalMapGod.getCountCycle());
                        return false;
                    }

                    int countAnimalsOnTypeNewCell = generalMapGod.cellMap[xMap][newYMap].getCountAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell + 1 > maxPopulation) {
                        setCountCycleMove(generalMapGod.getCountCycle());
                        return false;
                    }

                    generalMapGod.cellMap[xMap][newYMap].addAnimalsOnType(name);
                    generalMapGod.cellMap[xMap][yMap].removeAnimalsOnType(name);
                    yMap = newYMap;
                }

                if (direction == RIGHT_DIR) {
                    int newXMap = xMap + countStep;
                    if (newXMap >= generalMapGod.getSizeX()) {
                        newXMap = generalMapGod.getSizeX() - 1;
                    }
                    if (newXMap == xMap) {
                        setCountCycleMove(generalMapGod.getCountCycle());
                        return false;
                    }

                    int countAnimalsOnTypeNewCell = generalMapGod.cellMap[newXMap][yMap].getCountAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell + 1 > maxPopulation) {
                        setCountCycleMove(generalMapGod.getCountCycle());
                        return false;
                    }

                    generalMapGod.cellMap[newXMap][yMap].addAnimalsOnType(name);
                    generalMapGod.cellMap[xMap][yMap].removeAnimalsOnType(name);
                    xMap = newXMap;
                }

                if (direction == DOWN_DIR) {
                    int newYMap = yMap - countStep;
                    if (newYMap < 0) {
                        newYMap = 0;
                    }
                    if (newYMap == yMap) {
                        setCountCycleMove(generalMapGod.getCountCycle());
                        return false;
                    }

                    int countAnimalsOnTypeNewCell = generalMapGod.cellMap[xMap][newYMap].getCountAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell + 1 > maxPopulation) {
                        setCountCycleMove(generalMapGod.getCountCycle());
                        return false;
                    }

                    generalMapGod.cellMap[xMap][newYMap].addAnimalsOnType(name);
                    generalMapGod.cellMap[xMap][yMap].removeAnimalsOnType(name);
                    yMap = newYMap;
                }

                if (direction == LEFT_DIR) {
                    int newXMap = xMap - countStep;
                    if (newXMap < 0) {
                        newXMap = 0;
                    }
                    if (newXMap == xMap) {
                        setCountCycleMove(generalMapGod.getCountCycle());
                        return false;
                    }

                    int countAnimalsOnTypeNewCell = generalMapGod.cellMap[newXMap][yMap].getCountAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell + 1 > maxPopulation) {
                        setCountCycleMove(generalMapGod.getCountCycle());
                        return false;
                    }

                    generalMapGod.cellMap[newXMap][yMap].addAnimalsOnType(name);
                    generalMapGod.cellMap[xMap][yMap].removeAnimalsOnType(name);
                    xMap = newXMap;
                }

                setCountCycleMove(generalMapGod.getCountCycle());

                int newX = xMap;
                int newY = yMap;
                ArrayList<Animal> newCellListAnimals;
                if (generalMapGod.cellMap[newX][newY].listAnimals.containsKey(name)) {
                    newCellListAnimals = generalMapGod.cellMap[newX][newY].listAnimals.get(name);
                } else {
                    newCellListAnimals = new ArrayList<>();
                }
                newCellListAnimals.add(this);
                generalMapGod.cellMap[newX][newY].listAnimals.put(name, newCellListAnimals);
                return true;
            }
        }
        return false;
    }

}
