package ua.com.javarush.mavoropaev.javarush_proekt2;

public class Main {

    private static final int MAP_SIZE_X = 1;
    private static final int MAP_SIZE_Y = 1;

    public static void main(String[] args){
        GeneralMap generalMapGod = new GeneralMap(MAP_SIZE_X, MAP_SIZE_Y);
        GlobalStatistics globalStatistics = GlobalStatistics.getInstance();
        globalStatistics.initCellStatistics(MAP_SIZE_X, MAP_SIZE_Y);
        generalMapGod.start();
    }
}
