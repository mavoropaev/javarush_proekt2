package ua.com.javarush.mavoropaev.javarush_proekt2.service;

import java.util.*;

public class Parameters {
    public final int ITEM_COUNT = 16;
    private int mapSizeX;
    private int mapSizeY;
    private int countCycle;
    private TreeMap<Integer, TreeSet<Integer>> cellXY = new TreeMap<>();

    private static volatile Parameters instance;
    public static Parameters getInstance(){
        Parameters localInstance = instance;
        if (localInstance == null){
            synchronized (Parameters.class){
                localInstance = instance;
                if (localInstance == null){
                    instance = localInstance = new Parameters();
                }
            }
        }
        return localInstance;
    }
    private Parameters(){

    }

    public int getMapSizeX() {
        return mapSizeX;
    }

    public int getMapSizeY() {
        return mapSizeY;
    }

    public void setMapSizeX(int mapSizeX) {
        this.mapSizeX = mapSizeX;
    }

    public void setMapSizeY(int mapSizeY) {
        this.mapSizeY = mapSizeY;
    }

    public void setCountCycle(int countCycle) {
        this.countCycle = countCycle;
    }

    public int getCountCycle() {
        return countCycle;
    }

    public void addCellXY(int cellX, int cellY){
        if (cellXY.containsKey(cellX)){
            cellXY.get(cellX).add(cellY);
        }
        else {
            TreeSet<Integer> setY = new TreeSet<>();
            setY.add(cellY);
            cellXY.put(cellX, setY);
        }
    }

    public TreeSet<Integer> getKeySet(){
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (Integer key : cellXY.keySet()){
            treeSet.add(key);
        }
        return treeSet;
    }

    public TreeSet<Integer> getKeyValue(int key){
        return cellXY.get(key);
    }

}
