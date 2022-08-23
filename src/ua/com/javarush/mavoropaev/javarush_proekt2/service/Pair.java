package ua.com.javarush.mavoropaev.javarush_proekt2.service;

public class Pair {
    private int cellX;
    private int cellY;

    public Pair(int cellX, int cellY) {
        this.cellX = cellX;
        this.cellY = cellY;
    }

    public void setCellX(int cellX) {
        this.cellX = cellX;
    }

    public void setCellY(int cellY) {
        this.cellY = cellY;
    }

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }
}
