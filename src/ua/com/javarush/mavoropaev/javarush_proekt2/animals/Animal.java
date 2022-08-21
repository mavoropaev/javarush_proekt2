package ua.com.javarush.mavoropaev.javarush_proekt2.animals;

import ua.com.javarush.mavoropaev.javarush_proekt2.CycleCounter;
import ua.com.javarush.mavoropaev.javarush_proekt2.GeneralMap;
import ua.com.javarush.mavoropaev.javarush_proekt2.GlobalStatistics;
import ua.com.javarush.mavoropaev.javarush_proekt2.NameItem;

import java.util.ArrayList;
import java.util.Random;

public abstract class Animal {
    private NameItem name;
    private double weight;
    private int maxPopulation;
    private int speed;
    private double maxWeightEat;
    private double currentWeightEat;
    private StatusAnimals statusAnimals;
    private int lifeSpan;

    private int countCycleMove;
    private int countCycleEat;
    private int countCycleReproduction;
    private int countCycleCheckDeath;

    public final int COUNT_DIRECTION = 4;
    public final int UP_DIR = 1;
    public final int RIGHT_DIR = 2;
    public final int DOWN_DIR = 3;
    public final int LEFT_DIR = 4;
    public int xMap;
    public int yMap;

    GlobalStatistics globalStatistics = GlobalStatistics.getInstance();
    CycleCounter cycleCounter  = CycleCounter.getInstance();

    public abstract void eat();
    public abstract void reproduction();
    public abstract void dead();

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

    public Animal(NameItem name, StatusAnimals statusAnimals, int xMap, int yMap) {
        this.name = name;
        this.xMap = xMap;
        this.yMap = yMap;
        this.statusAnimals = statusAnimals;
    }

    public abstract Animal newObject(NameItem wolf, StatusAnimals statusAnimals, int x, int y);

    public void setCurrentWeightEat(double currentWeightEat) {
        this.currentWeightEat = (double) Math.round(currentWeightEat * 10000) / 10000;
    }
    public double getCurrentWeightEat() {
        return currentWeightEat;
    }
    public void addCurrentWeightEat(double weight) {
        currentWeightEat += weight;
        currentWeightEat = Math.min(currentWeightEat, maxWeightEat);
    }

    public void setName(NameItem name) {
        this.name = name;
    }
    public NameItem getName() {
        return name;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    public double getWeight() {
        return weight;
    }

    public void setMaxPopulation(int maxPopulation) {
        this.maxPopulation = maxPopulation;
    }
    public int getMaxPopulation() {
        return maxPopulation;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getSpeed() {
        return speed;
    }

    public void setMaxWeightEat(double maxWeightEat) {
        this.maxWeightEat = maxWeightEat;
    }
    public double getMaxWeightEat() {
        return maxWeightEat;
    }

    public void setCountCycleCheckDeath(int countCycleCheckDeath) {
        this.countCycleCheckDeath = countCycleCheckDeath;
    }
    public int getCountCycleCheckDeath() {
        return countCycleCheckDeath;
    }

    public StatusAnimals getStatusAnimals() {
        return statusAnimals;
    }
    public void setStatusAnimals(StatusAnimals statusAnimals) {
        this.statusAnimals = statusAnimals;
    }

    public void setLifeSpan(int lifeSpan) {
        this.lifeSpan = lifeSpan;
    }
    public void increaseLifespan() {
        this.lifeSpan += 1;
    }
    public int getLifeSpan() {
        return lifeSpan;
    }


   public boolean move(GeneralMap generalMap, int maxCountStep, int maxPopulation) {
        //step - 1 : move - animal.move()
        Random random = new Random();

        if (getCountCycleMove() < cycleCounter.getCycleCounter()) {
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
                    if (newYMap >= generalMap.getSizeY()) {
                        newYMap = generalMap.getSizeY() - 1;
                    }
                    if (newYMap == yMap) {
                        setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }

                    int countAnimalsOnTypeNewCell = generalMap.cellMap[xMap][newYMap].getCountAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell + 1 > maxPopulation) {
                        setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }

                    generalMap.cellMap[xMap][newYMap].addAnimalsOnType(name);
                    generalMap.cellMap[xMap][yMap].removeAnimalsOnType(name);
                    yMap = newYMap;
                }

                if (direction == RIGHT_DIR) {
                    int newXMap = xMap + countStep;
                    if (newXMap >= generalMap.getSizeX()) {
                        newXMap = generalMap.getSizeX() - 1;
                    }
                    if (newXMap == xMap) {
                        setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }

                    int countAnimalsOnTypeNewCell = generalMap.cellMap[newXMap][yMap].getCountAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell + 1 > maxPopulation) {
                        setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }

                    generalMap.cellMap[newXMap][yMap].addAnimalsOnType(name);
                    generalMap.cellMap[xMap][yMap].removeAnimalsOnType(name);
                    xMap = newXMap;
                }

                if (direction == DOWN_DIR) {
                    int newYMap = yMap - countStep;
                    if (newYMap < 0) {
                        newYMap = 0;
                    }
                    if (newYMap == yMap) {
                        setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }

                    int countAnimalsOnTypeNewCell = generalMap.cellMap[xMap][newYMap].getCountAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell + 1 > maxPopulation) {
                        setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }

                    generalMap.cellMap[xMap][newYMap].addAnimalsOnType(name);
                    generalMap.cellMap[xMap][yMap].removeAnimalsOnType(name);
                    yMap = newYMap;
                }

                if (direction == LEFT_DIR) {
                    int newXMap = xMap - countStep;
                    if (newXMap < 0) {
                        newXMap = 0;
                    }
                    if (newXMap == xMap) {
                        setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }

                    int countAnimalsOnTypeNewCell = generalMap.cellMap[newXMap][yMap].getCountAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell + 1 > maxPopulation) {
                        setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }

                    generalMap.cellMap[newXMap][yMap].addAnimalsOnType(name);
                    generalMap.cellMap[xMap][yMap].removeAnimalsOnType(name);
                    xMap = newXMap;
                }

                setCountCycleMove(cycleCounter.getCycleCounter());

                int newX = xMap;
                int newY = yMap;
                ArrayList<Animal> newCellListAnimals;
                if (generalMap.cellMap[newX][newY].listAnimals.containsKey(name)) {
                    newCellListAnimals = generalMap.cellMap[newX][newY].listAnimals.get(name);
                } else {
                    newCellListAnimals = new ArrayList<>();
                }
                newCellListAnimals.add(this);
                generalMap.cellMap[newX][newY].listAnimals.put(name, newCellListAnimals);
                globalStatistics.addStatisticsCome(newX, newY, name);

                return true;
            }
        }
        return false;
    }

}
