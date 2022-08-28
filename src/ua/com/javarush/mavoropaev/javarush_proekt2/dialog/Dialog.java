package ua.com.javarush.mavoropaev.javarush_proekt2.dialog;

import ua.com.javarush.mavoropaev.javarush_proekt2.service.Parameters;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Dialog {
    private static volatile Dialog instance;
    public static Dialog getInstance(){
        Dialog localInstance = instance;
        if (instance == null){
            synchronized (Dialog.class){
                localInstance = instance;
                if (localInstance == null){
                    instance = localInstance = new Dialog();
                }
            }
        }
        return localInstance;
    }
    private Dialog(){
    }

    Parameters parameters = Parameters.getInstance();

    public void startDialog(){
        choiceMainMenu();
    }

    public void choiceMainMenu(){
        System.out.println("----->>>    The island program is running    <<<-----");
        System.out.println("----->>>    Set basic options:    <<<-----");

        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print(" - Island size - length: -> ");
                parameters.setMapSizeX(scanner.nextInt());
                System.out.print(" - Island size - width: -> ");
                parameters.setMapSizeY(scanner.nextInt());
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Input error! Data entered incorrectly!");
                System.out.println("Retype.");
            }
        }

        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.print(" - Count cycle : -> ");
                parameters.setCountCycle(scanner.nextInt());
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Input error! Data entered incorrectly!");
                System.out.println("Retype.");
            }
        }

        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Enter cell coordinates to display statistics");
                System.out.print(" - Next cell X: -> ");
                int cellX = scanner.nextInt();
                System.out.print(" - Next cell Y -> ");
                int cellY = scanner.nextInt();
                if (!checkCellXY(cellX, cellY)){
                    System.out.println("X or Y not valid! Retype.");
                    continue;
                }
                parameters.addCellXY(cellX, cellY);
                System.out.println("Add new cell? (y/n)");
                String yesNo = scanner.next();

                if (!yesNo.equals("y")){
                    break;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Input error! Data entered incorrectly!");
                System.out.println("Retype.");
            }
        }
    }

    public boolean checkCellXY(int x, int y){
        return x >= 0 && x < parameters.getMapSizeX() && y >= 0 && y <= parameters.getMapSizeY();
    }
}
