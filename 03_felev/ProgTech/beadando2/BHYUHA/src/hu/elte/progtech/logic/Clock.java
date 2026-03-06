package hu.elte.progtech.logic;

import java.util.Random;

public class Clock {
    private int targetNum;
/*
    public void setTime(int time) {
        this.time = time;
    }
    tesztelésre!!!
 */
    private int time;

    public Clock(int targetNum) {
        this.targetNum = targetNum;
        /* previous solution
        switch (target) {
            case "0,0" :
                targetNum = 1;
                break;
            case "0,2" :
                targetNum = 2;
                break;
            case "0,4" :
                targetNum = 3;
                break;
            case "2,0" :
                targetNum = 4;
                break;
            case "2,2" :
                targetNum = 5;
                break;
            case "2,4" :
                targetNum = 6;
                break;
            case "4,0" :
                targetNum = 7;
                break;
            case "4,2" :
                targetNum = 8;
                break;
            case "4,4" :
                targetNum = 9;
                break;
            default:
                targetNum = 0;
        }
         */
        Random random = new Random();
        time = random.nextInt(12) + 1;
    }

    public void addTime() {
        if (time < 12) {++time;}
        else {time = 1;}
    }


    public int getTime() {return time;}
    public int getTargetNum() {return targetNum;}
}
