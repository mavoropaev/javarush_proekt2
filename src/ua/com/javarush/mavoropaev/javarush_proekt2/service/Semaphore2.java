package ua.com.javarush.mavoropaev.javarush_proekt2.service;

public class Semaphore2 {
    private int counter = 0;
    private boolean semaphoreOne;
    private boolean semaphoreTwo;

    public Semaphore2(){

    }

    public void semaphoreOn(int counter){
        if (this.counter == 0) {
            this.counter = counter;
        }
    }

    public void reduceSemaphore(){
        this.counter--;
    }

    public void setSemaphoreOne(boolean semaphoreOne) {
        this.semaphoreOne = semaphoreOne;
    }

    public void setSemaphoreTwo(boolean semaphoreTwo) {
        this.semaphoreTwo = semaphoreTwo;
    }

    public boolean switchSemaphoreOne(){
        if (this.counter == 0 && semaphoreTwo){
            semaphoreOne = true;
            semaphoreTwo = false;
            this.counter = Parameters.ITEM_COUNT;
            return true;
        }
        return false;

    }
    public boolean switchSemaphoreTwo(){
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
