package ua.com.javarush.mavoropaev.javarush_proekt2.statistics;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;

import java.util.ArrayList;
import java.util.HashMap;

public class CellStatistics {

    public HashMap<NameItem, ArrayList<String>> listStatisticsString = new HashMap<>();
    public HashMap<NameItem, Integer> listStatisticsBeginCycle = new HashMap<>();
    public HashMap<NameItem, Integer> listStatisticsDeath = new HashMap<>();
    public HashMap<NameItem, Integer> listStatisticsReproductions = new HashMap<>();
    public HashMap<NameItem, Integer> listStatisticsCome = new HashMap<>();
    public HashMap<NameItem, Integer> listStatisticsLeave = new HashMap<>();
    public HashMap<NameItem, Integer> listStatisticsHaveBeenEaten = new HashMap<>();
    public HashMap<NameItem, Integer> listStatisticsEndCycle = new HashMap<>();
    public HashMap<NameItem, ArrayList<NameItem>> listStatisticsWhoAteWho;

    public CellStatistics() {
        listStatisticsWhoAteWho = new HashMap<>();
    }

    public void addStatisticsCell(NameItem name, String string){
        ArrayList<String> listString;
        if (listStatisticsString.containsKey(name)) {
            listString = listStatisticsString.get(name);
        }
        else{
            listString = new ArrayList<>();
        }
        listString.add(string);
        listStatisticsString.put(name, listString);
    }

    public ArrayList<String> getStatisticsCell(NameItem name){
        if (listStatisticsString.containsKey(name)){
            return listStatisticsString.get(name);
        }
        return null;
    }

    public void setStatisticsBeginCycle(NameItem name, int count){
        listStatisticsBeginCycle.put(name, count);
    }

    public int getStatisticsBeginCycle(NameItem name){
        if (listStatisticsBeginCycle.containsKey(name)){
            return listStatisticsBeginCycle.get(name);
        }
        return 0;
    }

    public void setStatisticsEndCycle(NameItem name, int count){
        listStatisticsEndCycle.put(name, count);
    }

    public int getStatisticsEndCycle(NameItem name){
        if (listStatisticsEndCycle.containsKey(name)){
            return listStatisticsEndCycle.get(name);
        }
        return 0;
    }

    public void addStatisticsDeath(NameItem name){
        int countDeath = 0;
        if (listStatisticsDeath.containsKey(name)) {
            countDeath = listStatisticsDeath.get(name);
        }
        countDeath++;
        listStatisticsDeath.put(name, countDeath);
    }

    public void resetStatisticsDeath(NameItem name){
        int countDeath = 0;
        listStatisticsDeath.put(name, countDeath);
    }

    public int getStatisticsDeath(NameItem name){
        if (listStatisticsDeath.containsKey(name)){
            return listStatisticsDeath.get(name);
        }
        return 0;
    }

    public void addStatisticsReproductions(NameItem name){
        int countReproductions = 0;
        if (listStatisticsReproductions.containsKey(name)){
            countReproductions = listStatisticsReproductions.get(name);
        }
        countReproductions++;
        listStatisticsReproductions.put(name, countReproductions);
    }

    public void resetStatisticsReproductions(NameItem name){
        int countReproductions = 0;
        listStatisticsReproductions.put(name, countReproductions);
    }

    public int getStatisticsReproductions(NameItem name){
        if (listStatisticsReproductions.containsKey(name)){
            return listStatisticsReproductions.get(name);
        }
        return 0;
    }

    public void addStatisticsCome(NameItem name){
        int countCome = 0;
        if (listStatisticsCome.containsKey(name)){
            countCome = listStatisticsCome.get(name);
        }
        countCome++;
        listStatisticsCome.put(name, countCome);
    }

    public void resetStatisticsCome(NameItem name){
        int countCome = 0;
        listStatisticsCome.put(name, countCome);
    }

    public int getStatisticsCome(NameItem name){
        if (listStatisticsCome.containsKey(name)){
            return listStatisticsCome.get(name);
        }
        return 0;
    }

    public void addStatisticsLeave(NameItem name){
        int countLeave = 0;
        if (listStatisticsLeave.containsKey(name)){
            countLeave = listStatisticsLeave.get(name);
        }
        countLeave++;
        listStatisticsLeave.put(name, countLeave);
    }

    public void resetStatisticsLeave(NameItem name){
        int countLeave = 0;
        listStatisticsLeave.put(name, countLeave);
    }

    public int getStatisticsLeave(NameItem name){
        if (listStatisticsLeave.containsKey(name)){
            return listStatisticsLeave.get(name);
        }
        return 0;
    }

    public void addStatisticsHaveBeenEaten(NameItem name){
        int countLeave = 0;
        if (listStatisticsHaveBeenEaten.containsKey(name)){
            countLeave = listStatisticsHaveBeenEaten.get(name);
        }
        countLeave++;
        listStatisticsHaveBeenEaten.put(name, countLeave);
    }

    public void resetStatisticsHaveBeenEaten(NameItem name){
        int countLeave = 0;
        listStatisticsHaveBeenEaten.put(name, countLeave);
    }

    public int getStatisticsHaveBeenEaten(NameItem name){
        if (listStatisticsHaveBeenEaten.containsKey(name)){
            return listStatisticsHaveBeenEaten.get(name);
        }
        return 0;
    }

    public void addStatisticsWhoAteWho(NameItem name, NameItem nameFood){
        ArrayList<NameItem> listNameItem = new ArrayList<>();
        if (listStatisticsWhoAteWho.containsKey(name)){
            listNameItem = listStatisticsWhoAteWho.get(name);
        }
        listNameItem.add(nameFood);
        listStatisticsWhoAteWho.put(name, listNameItem);
    }

    public void resetStatisticsWhoAteWho(){
        listStatisticsWhoAteWho.clear();
    }

    public ArrayList<NameItem> getStatisticsWhoAteWho(NameItem name){
        if (listStatisticsWhoAteWho.containsKey(name)){
            return listStatisticsWhoAteWho.get(name);
        }
        return null;
    }
}
