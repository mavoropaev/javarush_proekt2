package ua.com.javarush.mavoropaev.javarush_proekt2;


import java.util.ArrayList;

public class GlobalStatistics {
    private int sizeX;
    private int sizeY;
    CycleCounter cycleCounter  = CycleCounter.getInstance();
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

    public void setCellStatisticsBeginCycle(GeneralMap generalMap){
        for (int x = 0; x < cellStatistics.length; x++){
            for (int y = 0; y < cellStatistics[0].length; y++){
                for (NameItem name : generalMap.cellMap[x][y].listAnimals.keySet()) {
                    int countAnimalsOnType = generalMap.cellMap[x][y].countAnimalsOnType.get(name);
                    cellStatistics[x][y].setStatisticsBeginCycle(name, countAnimalsOnType);
                }
            }
        }
    }

    public void setCellStatisticsEndCycle(GeneralMap generalMap){
        for (int x = 0; x < cellStatistics.length; x++){
            for (int y = 0; y < cellStatistics[0].length; y++){
                for (NameItem name : generalMap.cellMap[x][y].listAnimals.keySet()) {
                    int countAnimalsOnType = generalMap.cellMap[x][y].countAnimalsOnType.get(name);
                    cellStatistics[x][y].setStatisticsEndCycle(name, countAnimalsOnType);
                }
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

    public void addStatisticsHaveBeenEaten(int x, int y, NameItem name){
        cellStatistics[x][y].addStatisticsHaveBeenEaten(name);
    }

    public int getStatisticsHaveBeenEaten(int x, int y, NameItem name){
        return cellStatistics[x][y].getStatisticsHaveBeenEaten(name);
    }

    public void getStatisticsAnimals(){

    }

    public void getStatisticsStep(){

    }

    public void printStatistics(int x, int y){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Cycle number : " + cycleCounter.getCycleCounter() + " : Positions : (" + x + ", " + y + ")");
        System.out.println("--------------------------------------------------------------------------------------------");
        String str       = "|       Name      | Start cycle | Death | Reproductions | Come | Leave | Eaten | End cycle |";
        System.out.println(str);
        System.out.println("--------------------------------------------------------------------------------------------");

        for (NameItem name : NameItem.values()) {
            int startCycle = cellStatistics[x][y].getStatisticsBeginCycle(name);
            int death = cellStatistics[x][y].getStatisticsDeath(name);
            int reproductions = cellStatistics[x][y].getStatisticsReproductions(name);
            int come = cellStatistics[x][y].getStatisticsCome(name);
            int leave = cellStatistics[x][y].getStatisticsLeave(name);
            int eaten = cellStatistics[x][y].getStatisticsHaveBeenEaten(name);
            int endCycle = cellStatistics[x][y].getStatisticsEndCycle(name);
            String strCycle = "| " + name + " | " + startCycle + " | " + death + " | " + reproductions +
                             " | " + come + " | " + leave + " | " + eaten + " | " + endCycle + " | ";
            System.out.println(strCycle);

        }
    }


}
