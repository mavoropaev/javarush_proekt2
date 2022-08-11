package ua.com.javarush.mavoropaev.javarush_proekt2;

public class Plants {
    private int xMap;
    private int yMap;
    private double minBiomassWeight;
    private double maxBiomassWeight;
    private double currentBiomassWeight;
    private double biomassGrowthPerCycle;

    public Plants(int xMap, int yMap) {
        this.xMap = xMap;
        this.yMap = yMap;
    }

    public void setxMap(int xMap) {
        this.xMap = xMap;
    }

    public void setyMap(int yMap) {
        this.yMap = yMap;
    }

    public void setMinBiomassWeight(double minBiomassWeight) {
        this.minBiomassWeight = minBiomassWeight;
    }

    public void setMaxBiomassWeight(double maxBiomassWeight) {
        this.maxBiomassWeight = maxBiomassWeight;
    }

    public void setCurrentBiomassWeight(double currentBiomassWeight) {
        this.currentBiomassWeight = currentBiomassWeight;
    }

    public void setBiomassGrowthPerCycle(double biomassGrowthPerCycle) {
        this.biomassGrowthPerCycle = biomassGrowthPerCycle;
    }

    public int getxMap() {
        return xMap;
    }

    public int getyMap() {
        return yMap;
    }

    public double getMinBiomassWeight() {
        return minBiomassWeight;
    }

    public double getMaxBiomassWeight() {
        return maxBiomassWeight;
    }

    public double getCurrentBiomassWeight() {
        return currentBiomassWeight;
    }

    public double getBiomassGrowthPerCycle() {
        return biomassGrowthPerCycle;
    }
}
