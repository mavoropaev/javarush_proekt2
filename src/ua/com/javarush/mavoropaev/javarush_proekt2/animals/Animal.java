package ua.com.javarush.mavoropaev.javarush_proekt2.animals;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.CycleCounter;
import ua.com.javarush.mavoropaev.javarush_proekt2.map.GeneralMap;
import ua.com.javarush.mavoropaev.javarush_proekt2.statistics.GlobalStatistics;
import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Animal {
    private NameItem name;
    private double weight;
    private int maxPopulation;
    private int maxStep;
    private double maxWeightEat;
    private double currentWeightEat;
    private StatusAnimals statusAnimals;
    private int periodReproductions;
    private int amountOfChildren;

    private int countCycleMove;
    private int countCycleEat;
    private int countCycleReproduction;
    private int countCycleCheckDeath;

    public final int COUNT_DIRECTION = 4;
    public final int UP_DIR = 1;
    public final int RIGHT_DIR = 2;
    public final int DOWN_DIR = 3;
    public final int LEFT_DIR = 4;
    private int xMap;
    private int yMap;

    public Animal(NameItem name, StatusAnimals statusAnimals, int xMap, int yMap) {
        this.name = name;
        this.xMap = xMap;
        this.yMap = yMap;
        this.statusAnimals = statusAnimals;
    }

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



    public abstract Animal newObject(NameItem name, StatusAnimals statusAnimals, int x, int y);

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

    public void setMaxStep(int maxStep) {
        this.maxStep = maxStep;
    }
    public int getMaxStep() {
        return maxStep;
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

    public void setPeriodReproductions(int periodReproductions) {
        this.periodReproductions = periodReproductions;
    }

    public void setAmountOfChildren(int amountOfChildren) {
        this.amountOfChildren = amountOfChildren;
    }

    public int getPeriodReproductions() {
        return periodReproductions;
    }

    public int getAmountOfChildren() {
        return amountOfChildren;
    }

    public boolean die(){
        CycleCounter cycleCounter = CycleCounter.getInstance();
        if (countCycleCheckDeath < cycleCounter.getCycleCounter()) {
            setCurrentWeightEat(getCurrentWeightEat() - getMaxWeightEat() * 0.25);
            return getCurrentWeightEat() <= 0 && getMaxWeightEat() > 0;
        }
        return false;
    }

    public boolean move() {
        synchronized (this.getClass()) {
            GeneralMap generalMap = GeneralMap.getInstance();
            GlobalStatistics globalStatistics = GlobalStatistics.getInstance();
            CycleCounter cycleCounter = CycleCounter.getInstance();

            if (getCountCycleMove() < cycleCounter.getCycleCounter()) {
                Random random = new Random();
                int newXMap = 0;
                int newYMap = 0;

                if (maxStep > 0) {
                    int countStep = 0;
                    while (countStep == 0) {
                        countStep = random.nextInt(maxStep + 1);
                    }
                    int direction = 0;
                    while (direction == 0) {
                        direction = random.nextInt(COUNT_DIRECTION + 1);
                    }

                    if (direction == UP_DIR) {
                        newYMap = yMap + countStep;
                        if (newYMap >= generalMap.getSizeY()) {
                            newYMap = generalMap.getSizeY() - 1;
                        }
                        if (newYMap == yMap) {
                            setCountCycleMove(cycleCounter.getCycleCounter());
                            return false;
                        }
                        newXMap = xMap;
                    }

                    if (direction == RIGHT_DIR) {
                        newXMap = xMap + countStep;
                        if (newXMap >= generalMap.getSizeX()) {
                            newXMap = generalMap.getSizeX() - 1;
                        }
                        if (newXMap == xMap) {
                            setCountCycleMove(cycleCounter.getCycleCounter());
                            return false;
                        }
                        newYMap = yMap;
                    }

                    if (direction == DOWN_DIR) {
                        newYMap = yMap - countStep;
                        if (newYMap < 0) {
                            newYMap = 0;
                        }
                        if (newYMap == yMap) {
                            setCountCycleMove(cycleCounter.getCycleCounter());
                            return false;
                        }
                        newXMap = xMap;
                    }

                    if (direction == LEFT_DIR) {
                        newXMap = xMap - countStep;
                        if (newXMap < 0) {
                            newXMap = 0;
                        }
                        if (newXMap == xMap) {
                            setCountCycleMove(cycleCounter.getCycleCounter());
                            return false;
                        }
                        newYMap = yMap;
                    }

                    int countAnimalsOnTypeNewCell = generalMap.cellMap[newXMap][newYMap].getCounterAnimalsOnType(name);
                    if (countAnimalsOnTypeNewCell + 1 > maxPopulation) {
                        setCountCycleMove(cycleCounter.getCycleCounter());
                        return false;
                    }
                    generalMap.cellMap[newXMap][newYMap].increaseCounterAnimalsOnType(name);
                    generalMap.cellMap[xMap][yMap].decrementCounterAnimalsOnType(name);

                    setCountCycleMove(cycleCounter.getCycleCounter());

                    CopyOnWriteArrayList<Animal> newCellListAnimals;
                    if (generalMap.cellMap[newXMap][newYMap].listAnimals.containsKey(name)) {
                        newCellListAnimals = generalMap.cellMap[newXMap][newYMap].listAnimals.get(name);
                    } else {
                        newCellListAnimals = new CopyOnWriteArrayList<>();
                    }
                    newCellListAnimals.add(this);
                    generalMap.cellMap[newXMap][newYMap].listAnimals.put(name, newCellListAnimals);
                    globalStatistics.addStatisticsCome(newXMap, newYMap, name);
                    xMap = newXMap;
                    yMap = newYMap;

                    return true;
                }
            }
        }
        return false;
    }
}
