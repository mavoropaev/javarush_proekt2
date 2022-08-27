package ua.com.javarush.mavoropaev.javarush_proekt2.service;

public class CycleCounter {
    private static volatile CycleCounter instance;
    private int cycleCounter = 0;
    private int semaphore = 0;


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

    public void setSemaphore(int semaphore) {
        this.semaphore = semaphore;
    }

    public int getSemaphore() {
        return semaphore;
    }

    public void reduceSemaphore(){
        this.semaphore--;
    }

    public void setSemaphoreOn(){
        if (this.semaphore == 0) {
            this.semaphore = Parameters.ITEM_COUNT;
        }
    }

    public boolean isSemaphoreOff(){
        if (this.semaphore == 0) return true;
        return false;
    }



}
