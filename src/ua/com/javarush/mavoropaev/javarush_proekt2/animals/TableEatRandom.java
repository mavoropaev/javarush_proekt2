package ua.com.javarush.mavoropaev.javarush_proekt2.animals;

import ua.com.javarush.mavoropaev.javarush_proekt2.NameAnimals;
import ua.com.javarush.mavoropaev.javarush_proekt2.NameFoods;

import java.util.HashMap;

public class TableEatRandom {
    private static final HashMap<NameAnimals, Integer> ANIMAL_TO_NUMBER = new HashMap<>();
    private static final HashMap<Integer, NameAnimals> NUMBER_TO_ANIMAL = new HashMap<>();
    private static final HashMap<NameFoods, Integer> FOOD_TO_NUMBER = new HashMap<>();
    private static final HashMap<Integer, NameFoods> NUMBER_TO_FOOD = new HashMap<>();
    private final int COUNT_TYPE_EAT = 16;
    public int[][] tableEatRandom = new int[15][16];

    public TableEatRandom() {
        initNameToNumber();
        initTableEatRandom();
    }
    private void initNameToNumber(){
        int number = 0;
        for(NameAnimals name : NameAnimals.values()){
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

    public int getNumberToAnimal(NameAnimals nameAnimal){
        if (ANIMAL_TO_NUMBER.containsKey(nameAnimal)) {
            return (ANIMAL_TO_NUMBER.get(nameAnimal));
        }
        return 0;
    }

    public int getNumberToFood(NameFoods nameFood){
        if (ANIMAL_TO_NUMBER.containsKey(nameFood)) {
            return (ANIMAL_TO_NUMBER.get(nameFood));
        }
        return 0;
    }

    public NameFoods getFoodToNumber(int numberName){
        if (NUMBER_TO_FOOD.containsKey(numberName)) {
            return (NUMBER_TO_FOOD.get(numberName));
        }
        return null;
    }
    public NameAnimals getAnimalToNumber(int numberName){
        if (NUMBER_TO_ANIMAL.containsKey(numberName)) {
            return (NUMBER_TO_ANIMAL.get(numberName));
        }
        return null;
    }

    private void initTableEatRandom(){
        tableEatRandom[0]  = new int[]{0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0, 0};
        tableEatRandom[1]  = new int[]{0,  0, 15, 0, 0,  0,  0, 20, 40,  0,  0,  0,  0, 10,  0, 0};
        tableEatRandom[2]  = new int[]{0,  0,  0, 0, 0,  0,  0, 70, 90,  0,  0,  0,  0, 60, 40, 0};
        tableEatRandom[3]  = new int[]{0, 80,  0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10,  0, 0};
        tableEatRandom[4]  = new int[]{0,  0, 10, 0, 0,  0,  0, 90, 90,  0,  0,  0,  0, 80,  0, 0};
        tableEatRandom[5]  = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatRandom[6]  = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatRandom[7]  = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatRandom[8]  = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 90, 100};
        tableEatRandom[9]  = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatRandom[10] = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatRandom[11] = new int[]{0,  0,  0, 0, 0,  0,  0,  0, 50,  0,  0,  0,  0,  0, 90, 100};
        tableEatRandom[12] = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        tableEatRandom[13] = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 90, 100};
        tableEatRandom[14] = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 100};
        //tableEatRandom[15] = new int[]{0,  0,  0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 0};
    }

    public int getTableEatRandom(int number1, int number2){
        return tableEatRandom[number1][number2];
    }




}
