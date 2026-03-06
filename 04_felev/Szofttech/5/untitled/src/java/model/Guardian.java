package model;

import model.animal.*;

import java.util.Random;

public class Guardian {
    private static final int SALARY = 500;
    private static final double HIDE_CHANCE = 7.0;
    private static final double ACTION_RANGE = 2.0;

    private double x;
    private double y;
    private double dirX;
    private double dirY;
    private double speed;
    private boolean active = true;
    private Animal targetAnimal;
    private int monthlyCost = SALARY;

    public Guardian(double x, double y) {
        this.x = x;
        this.y = y;
        speed = 0.03;
        Random rand = new Random();
        this.dirX = rand.nextDouble() * 2 - 1;
        this.dirY = rand.nextDouble() * 2 - 1;
    }
}
