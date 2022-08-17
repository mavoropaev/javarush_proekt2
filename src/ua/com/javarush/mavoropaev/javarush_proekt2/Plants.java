package ua.com.javarush.mavoropaev.javarush_proekt2;

public class Plants {
    private int xMap;
    private int yMap;
    //private final double MAX_BIOMASS_WEIGHT = 200;
    public static final double WEIGHT = 1;
    public static final int MAX_COUNT_CELL = 200;
    public static final int MAX_SPEED = 0;
    public static final double MAX_EAT = 0;

    private double currentBiomassWeight;
    private double biomassGrowthPerCycle;

    public Plants(int xMap, int yMap) {
        this.xMap = xMap;
        this.yMap = yMap;
    }

    public void setXMap(int xMap) {
        this.xMap = xMap;
    }

    public void setYMap(int yMap) {
        this.yMap = yMap;
    }

    public void setCurrentBiomassWeight(double currentBiomassWeight) {
        this.currentBiomassWeight = currentBiomassWeight;
    }
    public void setInitBiomassWeight() {
        this.currentBiomassWeight = MAX_COUNT_CELL;
    }

    public void setBiomassGrowthPerCycle(double biomassGrowthPerCycle) {
        this.biomassGrowthPerCycle = biomassGrowthPerCycle;
    }

    public int getXMap() {
        return xMap;
    }

    public int getYMap() {
        return yMap;
    }


    public double getMaxBiomassWeight() {
        return MAX_COUNT_CELL;
    }

    public double getCurrentBiomassWeight() {
        return currentBiomassWeight;
    }

    public double getBiomassGrowthPerCycle() {
        return biomassGrowthPerCycle;
    }
}
