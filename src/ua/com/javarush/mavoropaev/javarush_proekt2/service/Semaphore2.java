package ua.com.javarush.mavoropaev.javarush_proekt2.service;

public class Semaphore2 {
    private int semaphore = 0;

    public Semaphore2(){

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
