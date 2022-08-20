package ua.com.javarush.mavoropaev.javarush_proekt2;


import java.util.ArrayList;

public class GlobalStatistics {
    private int sizeX;
    private int sizeY;
    private static volatile GlobalStatistics instance;

    public static GlobalStatistics getInstance() {
        GlobalStatistics localInstance = instance;
        if (localInstance == null) {
            synchronized (GlobalStatistics.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new GlobalStatistics();
                }
            }
        }
        return localInstance;
    }
    private GlobalStatistics(){

    }

    public CellStatistics[][] cellStatistics;

    public void initCellStatistics(int sizeX, int sizeY){
        cellStatistics = new CellStatistics[sizeX][sizeY];
        for (int x = 0; x < sizeX; x++){
            for (int y = 0; y < sizeY; y++){
                cellStatistics[x][y] = new CellStatistics();
            }
        }
    }
    public void addStatisticsCell(int x, int y, NameItem name, String string){
        cellStatistics[x][y].addStatisticsCell(name, string);
    }

    public ArrayList<String> getStatisticsCell(int x, int y, NameItem name){
        return cellStatistics[x][y].getStatisticsCell(name);
    }

    public void addStatisticsDeath(int x, int y, NameItem name){
        cellStatistics[x][y].addStatisticsDeath(name);
    }

    public int getStatisticsDeath(int x, int y, NameItem name){
        return cellStatistics[x][y].getStatisticsDeath(name);
    }

    public void addStatisticsReproductions(int x, int y, NameItem name){
        cellStatistics[x][y].addStatisticsReproductions(name);
    }

    public int getStatisticsReproductions(int x, int y, NameItem name){
        return cellStatistics[x][y].getStatisticsReproductions(name);
    }

    public void addStatisticsCome(int x, int y, NameItem name){
        cellStatistics[x][y].addStatisticsCome(name);
    }

    public int getStatisticsCome(int x, int y, NameItem name){
        return cellStatistics[x][y].getStatisticsCome(name);
    }

    public void addStatisticsLeave(int x, int y, NameItem name){
        cellStatistics[x][y].addStatisticsLeave(name);
    }

    public int getStatisticsLeave(int x, int y, NameItem name){
        return cellStatistics[x][y].getStatisticsLeave(name);
    }


    public void getStatisticsAnimals(){

    }

    public void getStatisticsStep(){

    }


}
