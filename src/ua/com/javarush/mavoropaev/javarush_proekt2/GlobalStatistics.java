package ua.com.javarush.mavoropaev.javarush_proekt2;


import ua.com.javarush.mavoropaev.javarush_proekt2.animals.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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

    public void setCellStatisticsEndCycle(GeneralMap generalMap){
        for (int x = 0; x < cellStatistics.length; x++){
            for (int y = 0; y < cellStatistics[0].length; y++){
                for (NameItem name : generalMap.cellMap[x][y].listAnimals.keySet()) {
                    int countAnimalsOnType = generalMap.cellMap[x][y].countAnimalsOnType.get(name);
                    cellStatistics[x][y].setStatisticsEndCycle(name, countAnimalsOnType);
                }
                cellStatistics[x][y].setStatisticsEndCycle(NameItem.PLANTS, generalMap.plantsMap[x][y].getCurrentBiomassWeight());
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

    public void addStatisticsWhoAteWho(int x, int y, NameItem name, NameItem nameFood){
        cellStatistics[x][y].addStatisticsWhoAteWho(name, nameFood);
    }

    public ArrayList<NameItem> getStatisticsWhoAteWho(int x, int y, NameItem name){
        return cellStatistics[x][y].getStatisticsWhoAteWho(name);
    }





    public void getStatisticsAnimals(){

    }

    public void getStatisticsStep(){

    }

    public void printStatistics(int x, int y){
        String separator = "----------------------------------------------------------------------------------------------";
        System.out.println(separator);
        System.out.println("Cycle number : " + cycleCounter.getCycleCounter() + " : Positions : (" + x + ", " + y + ")");
        System.out.println(separator);
        String str       = "|       Name      | Start cycle | Death | Reproductions |  Come  | Leave | Eaten | End cycle |" +
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

            String strWhoAteWho = new String();
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
                strWhoAteWho = "|";
                for (NameItem nameItem : listAteCount.keySet()) {
                    strWhoAteWho += " | " + nameItem + " : " + listAteCount.get(nameItem) + " | ";
                }
            }


            String strCycle = "| " + strName + " | " + strStartCycle + " | " + strDeath + " | " + strReproductions +
                             " | " + strCome + " | " + strLeave + " | " + strEaten + " | " + strEndCycle + " | ";
            strCycle = strCycle + strWhoAteWho;
            System.out.println(strCycle);

        }
    }


}
