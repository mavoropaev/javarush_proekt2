package ua.com.javarush.mavoropaev.javarush_proekt2.service;

public class CycleCounter {
    private static volatile CycleCounter instance;
    private int cycleCounter = 0;
    private int semaphore = 0;
    private int counter = 0;


    public static CycleCounter getInstance() {
        CycleCounter localInstance = instance;
        if (localInstance == null) {
            synchronized (CycleCounter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CycleCounter();
                }
            }
        }
        return localInstance;
    }

    private CycleCounter(){

    }

    public int getCycleCounter() {
        return cycleCounter;
    }

    public void increaseCycleCounter() {
        cycleCounter++;
    }

    public void reduceSemaphore(){
        this.counter--;
    }

    public void setSemaphoreOn(int counter){
        if (this.counter == 0) {
            this.counter = counter;
        }
    }

    private boolean semaphoreOne;
    private boolean semaphoreTwo;

    public void setSemaphoreOne(boolean semaphoreOne) {
        this.semaphoreOne = semaphoreOne;
    }

    public void setSemaphoreTwo(boolean semaphoreTwo) {
        this.semaphoreTwo = semaphoreTwo;
    }

    public boolean semaphoreOne(){
        if (this.counter == 0 && semaphoreTwo){
            semaphoreOne = true;
            semaphoreTwo = false;
            this.counter = Parameters.ITEM_COUNT;
            return true;
        }
        return false;
    }
    public boolean semaphoreTwo(){
        if (this.counter == 0 && semaphoreOne){
            semaphoreTwo = true;
            semaphoreOne = false;
            this.counter = Parameters.ITEM_COUNT;
            return true;
        }
        return false;
    }

    public boolean isSemaphoreOne() {
        return semaphoreOne;
    }

    public boolean isSemaphoreTwo() {
        return semaphoreTwo;
    }





}
