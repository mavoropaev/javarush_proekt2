package ua.com.javarush.mavoropaev.javarush_proekt2.statistics;

import ua.com.javarush.mavoropaev.javarush_proekt2.map.GeneralMap;
import ua.com.javarush.mavoropaev.javarush_proekt2.plants.Plants;
import ua.com.javarush.mavoropaev.javarush_proekt2.service.CycleCounter;
import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.service.Parameters;

import java.util.ArrayList;
import java.util.HashMap;

public class GlobalStatistics {
    CycleCounter cycleCounter  = CycleCounter.getInstance();
    Parameters parameters = Parameters.getInstance();
    public HashMap<NameItem, Integer> countAnimalsAllMap = new HashMap<>();

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
        initCellStatistics();
    }

    public CellStatistics[][] cellStatistics;

    public void initCellStatistics(){
        int mapSizeX = parameters.getMapSizeX();
        int mapSizeY = parameters.getMapSizeY();
        cellStatistics = new CellStatistics[mapSizeX][mapSizeY];

        for (int x = 0; x < mapSizeX; x++){
            for (int y = 0; y < mapSizeY; y++){
                cellStatistics[x][y] = new CellStatistics();
            }
        }
    }

    public void setCellStatisticsBeginCycle(){
        GeneralMap generalMap = GeneralMap.getInstance();
        for (int x = 0; x < cellStatistics.length; x++){
            for (int y = 0; y < cellStatistics[0].length; y++){
                for (NameItem name : generalMap.cellMap[x][y].listAnimals.keySet()) {
                    int countAnimalsOnType = generalMap.cellMap[x][y].countAnimalsOnType.get(name);
                    cellStatistics[x][y].setStatisticsBeginCycle(name, countAnimalsOnType);
                    cellStatistics[x][y].resetStatisticsDeath(name);
                    cellStatistics[x][y].resetStatisticsReproductions(name);
                    cellStatistics[x][y].resetStatisticsCome(name);
                    cellStatistics[x][y].resetStatisticsLeave(name);
                    cellStatistics[x][y].resetStatisticsHaveBeenEaten(name);
                }
                cellStatistics[x][y].resetStatisticsWhoAteWho();
                cellStatistics[x][y].setStatisticsBeginCycle(NameItem.PLANTS, Plants.MAX_COUNT_CELL);
                cellStatistics[x][y].resetStatisticsHaveBeenEaten(NameItem.PLANTS);
            }
        }
    }

    public void setCellStatisticsEndCycle(){
        GeneralMap generalMap = GeneralMap.getInstance();
        int count = 0;
        countAnimalsAllMap.clear();
        for (int x = 0; x < cellStatistics.length; x++){
            for (int y = 0; y < cellStatistics[0].length; y++){
                for (NameItem name : generalMap.cellMap[x][y].listAnimals.keySet()) {
                    int countAnimalsOnType = generalMap.cellMap[x][y].countAnimalsOnType.get(name);
                    cellStatistics[x][y].setStatisticsEndCycle(name, countAnimalsOnType);

                    count = countAnimalsAllMap.getOrDefault(name, 0);
                    count += countAnimalsOnType;
                    countAnimalsAllMap.put(name, count);
                }
                cellStatistics[x][y].setStatisticsEndCycle(NameItem.PLANTS, generalMap.plantsMap[x][y].getCurrentBiomassWeight());

                if (countAnimalsAllMap.containsKey(NameItem.PLANTS)){
                    count = countAnimalsAllMap.get(NameItem.PLANTS);
                }
                count += generalMap.plantsMap[x][y].getCurrentBiomassWeight();
                countAnimalsAllMap.put(NameItem.PLANTS, count);
            }
        }
    }

    public void addStatisticsDeath(int x, int y, NameItem name){
        cellStatistics[x][y].addStatisticsDeath(name);
    }

   // public int getStatisticsDeath(int x, int y, NameItem name){
   //     return cellStatistics[x][y].getStatisticsDeath(name);
   // }

    public void addStatisticsReproductions(int x, int y, NameItem name){
        cellStatistics[x][y].addStatisticsReproductions(name);
    }

    //public int getStatisticsReproductions(int x, int y, NameItem name){
    //    return cellStatistics[x][y].getStatisticsReproductions(name);
   // }

    public void addStatisticsCome(int x, int y, NameItem name){
        cellStatistics[x][y].addStatisticsCome(name);
    }

    //public int getStatisticsCome(int x, int y, NameItem name){
    //    return cellStatistics[x][y].getStatisticsCome(name);
   // }

    public void addStatisticsLeave(int x, int y, NameItem name){
        cellStatistics[x][y].addStatisticsLeave(name);
    }

   // public int getStatisticsLeave(int x, int y, NameItem name){
    //    return cellStatistics[x][y].getStatisticsLeave(name);
    //}

    public void addStatisticsHaveBeenEaten(int x, int y, NameItem name){
        cellStatistics[x][y].addStatisticsHaveBeenEaten(name);
    }

   // public int getStatisticsHaveBeenEaten(int x, int y, NameItem name){
   //     return cellStatistics[x][y].getStatisticsHaveBeenEaten(name);
   // }

    public void addStatisticsWhoAteWho(int x, int y, NameItem name, NameItem nameFood){
        cellStatistics[x][y].addStatisticsWhoAteWho(name, nameFood);
    }

   // public ArrayList<NameItem> getStatisticsWhoAteWho(int x, int y, NameItem name){
    //    return cellStatistics[x][y].getStatisticsWhoAteWho(name);
   // }

    public void printStatistics(int x, int y){
        String separator = "-------------------------------------------------------------------------------------------------------------------";
        System.out.println(separator);
        System.out.println("Cycle number : " + cycleCounter.getCycleCounter() + " : Positions : (" + x + ", " + y + ")");
        System.out.println(separator);
        String str       = "|       Name      | Start cycle | Death | Reproductions |  Come  | Leave | Eaten | End cycle |  All map  |" +
                "  List of animals eaten |";
        System.out.println(str);
        System.out.println(separator);

        for (NameItem name : NameItem.values()) {
            String strName = String.format("%-15s", name);
            int startCycle = cellStatistics[x][y].getStatisticsBeginCycle(name);
            String strStartCycle = String.format("%11d", startCycle);
            int death = cellStatistics[x][y].getStatisticsDeath(name);
            String strDeath = String.format("%5d", death);
            int reproductions = cellStatistics[x][y].getStatisticsReproductions(name);
            String strReproductions = String.format("%13d", reproductions);
            int come = cellStatistics[x][y].getStatisticsCome(name);
            String strCome = String.format("%6d", come);
            int leave = cellStatistics[x][y].getStatisticsLeave(name);
            String strLeave = String.format("%5d", leave);
            int eaten = cellStatistics[x][y].getStatisticsHaveBeenEaten(name);
            String strEaten = String.format("%5d", eaten);
            int endCycle = cellStatistics[x][y].getStatisticsEndCycle(name);
            String strEndCycle = String.format("%9d", endCycle);
            int endCycleAllMap = countAnimalsAllMap.get(name);
            String strEndCycleAllMap = String.format("%9d", endCycleAllMap);

            StringBuilder strWhoAteWho = new StringBuilder();
            if (cellStatistics[x][y].getStatisticsWhoAteWho(name) != null) {
                ArrayList<NameItem> listNameItem = cellStatistics[x][y].getStatisticsWhoAteWho(name);
                HashMap<NameItem, Integer> listAteCount = new HashMap<>();

                for (NameItem nameItem : listNameItem) {
                    int count = 0;
                    if (listAteCount.containsKey(nameItem)) {
                        count = listAteCount.get(nameItem);
                    }
                    count++;
                    listAteCount.put(nameItem, count);
                }
                strWhoAteWho = new StringBuilder("|");
                for (NameItem nameItem : listAteCount.keySet()) {
                    strWhoAteWho.append(" | ")
                            .append(nameItem)
                            .append(" : ")
                            .append(listAteCount.get(nameItem))
                            .append(" | ");
                }
            }

            String strCycle = "| " + strName + " | " + strStartCycle + " | " + strDeath + " | " + strReproductions +
                             " | " + strCome + " | " + strLeave + " | " + strEaten + " | " + strEndCycle + " | " + strEndCycleAllMap + " | ";
            strCycle = strCycle + strWhoAteWho;
            System.out.println(strCycle);

        }
    }

}
