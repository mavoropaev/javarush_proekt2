package ua.com.javarush.mavoropaev.javarush_proekt2;

import ua.com.javarush.mavoropaev.javarush_proekt2.map.GeneralMap;
import ua.com.javarush.mavoropaev.javarush_proekt2.dialog.Dialog;

public class Main {

    public static void main(String[] args){
        Dialog dialog = Dialog.getInstance();
        dialog.startDialog();

        GeneralMap generalMap = GeneralMap.getInstance();
        generalMap.startMap();
    }
}
