package ua.com.javarush.mavoropaev.javarush_proekt2.animals;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameItem;
import ua.com.javarush.mavoropaev.javarush_proekt2.service.NameFoods;

import java.util.HashMap;

public class TableEatProbability {
    private static final HashMap<NameItem, Integer> ANIMAL_TO_NUMBER = new HashMap<>();
    private static final HashMap<Integer, NameItem> NUMBER_TO_ANIMAL = new HashMap<>();
    private static final HashMap<NameFoods, Integer> FOOD_TO_NUMBER = new HashMap<>();
    private static final HashMap<Integer, NameFoods> NUMBER_TO_FOOD = new HashMap<>();
    public int[][] tableEatProbability = new int[16][16];

    public TableEatProbability() {
        initNameToNumber();
        initTableEatProbability();
    }
    private void initNameToNumber(){
        int number = 0;
        for(NameItem name : NameItem.values()){
            ANIMAL_TO_NUMBER.put(name, number);
            NUMBER_TO_ANIMAL.put(number, name);
            number++;
        }
        number = 0;
        for(NameFoods name : NameFoods.values()){
            FOOD_TO_NUMBER.put(name, number);
            NUMBER_TO_FOOD.put(number, name);
            number++;
        }
    }

    public int getNumberToAnimal(NameItem nameAnimal){
        if (ANIMAL_TO_NUMBER.containsKey(nameAnimal)) {
            return (ANIMAL_TO_NUMBER.get(nameAnimal));
        }
        return -1;
    }

    public int getNumberToFood(NameFoods nameFood){
        if (ANIMAL_TO_NUMBER.containsKey(nameFood)) {
            return (ANIMAL_TO_NUMBER.get(nameFood));
        }
        return -1;
    }

    public NameFoods getFoodToNumber(int numberName){
        if (NUMBER_TO_FOOD.containsKey(numberName)) {
            return (NUMBER_TO_FOOD.get(numberName));
        }
        return null;
    }
    public NameItem getAnimalToNumber(int numberName){
        if (NUMBER_TO_ANIMAL.containsKey(numberName)) {
            return (NUMBER_TO_ANIMAL.get(numberName));
        }
        return null;
    }

    private void initTableEatProbability(){
        tableEatProbability[0]  = new int[]{0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0, 0};
        tableEatProbability[1]  = new int[]{0,  0, 15, 0, 0,  0,  0, 20, 40,  0,  0,  0,  0, 10,  0, 0};
        tableEatProbability[2]  = new int[]{0,  0,  0, 0, 0,  0,  0, 70, 90,  0,  0,  0,  0, 60, 40, 0};
        tableEatProbability[3]  = new int[]{0, 80,  0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10,  0, 0};
        tableEatProbability[4]  = new int[]{0,  0, 10, 0, 0,  0,  0, 90, 90,  0,  0,  0,  0, 80,  0, 0};
        tableEatProbability[5]  = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatProbability[6]  = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatProbability[7]  = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatProbability[8]  = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 90, 100};
        tableEatProbability[9]  = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatProbability[10] = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatProbability[11] = new int[]{0,  0,  0, 0, 0,  0,  0,  0, 50,  0,  0,  0,  0,  0, 90, 100};
        tableEatProbability[12] = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatProbability[13] = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 90, 100};
        tableEatProbability[14] = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatProbability[15] = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 0};
    }

    public int getProbability(int number1, int number2){
        return tableEatProbability[number1][number2];
    }

}
