package ua.com.javarush.mavoropaev.javarush_proekt2;

import ua.com.javarush.mavoropaev.javarush_proekt2.map.GeneralMap;
import ua.com.javarush.mavoropaev.javarush_proekt2.service.Dialog;
import ua.com.javarush.mavoropaev.javarush_proekt2.service.Parameters;
import ua.com.javarush.mavoropaev.javarush_proekt2.statistics.GlobalStatistics;

public class Main {

    public static void main(String[] args){
        Parameters parameters = Parameters.getInstance();
        Dialog dialog = Dialog.getInstance();

        dialog.startDialog();

        int mapSizeX = parameters.getMapSizeX();
        int mapSizeY = parameters.getMapSizeY();

        GeneralMap generalMapGod = new GeneralMap(mapSizeX, mapSizeY);
        GlobalStatistics globalStatistics = GlobalStatistics.getInstance();
        globalStatistics.initCellStatistics(mapSizeX, mapSizeY);
        generalMapGod.start();
    }
}
