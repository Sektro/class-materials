package model;

import controller.LivingManager;
import model.animal.Animal;

import java.util.Random;

public class Hunter {
    private static final double DETECTION_RANGE  = 4.0;
    private static final double VISION_RANGE = 5.0;
    private static final double ACTION_RANGE = 3.0;

    private double x;
    private double y;
    private double dirX;
    private double dirY;
    private double speed;
    private boolean active = true;
    private boolean visible = false;
    private Animal targetAnimal;
    private HunterType type;

    public Hunter(double x, double y) {
        this.x = x;
        this.y = y;
        speed = 0.04;
        Random rand = new Random();
        this.dirX = rand.nextDouble() * 2 - 1;
        this.dirY = rand.nextDouble() * 2 - 1;
        this.type = rand.nextBoolean() ? HunterType.SHOOTER : HunterType.TRAPPER;
    }

    private double distanceTo(Animal animal) {
        int dx = (int)(animal.getPosX() - x);
        int dy = (int)(animal.getPosY() - y);
        return Math.sqrt(dx * dx + dy * dy);
    }

    private Animal findTargetAnimal(LivingManager livingManager) {
        double minDistance = Double.MAX_VALUE;
        Animal target = null;

        for (Animal animal : livingManager.getAnimals()) {
            double distance = distanceTo(animal);
            if (distance < minDistance && distance < 10) {
                minDistance = distance;
                target = animal;
            }
        }
        return target;
    }

    private void moveTowardsTargetAnimal(Animal target) {
        int dx = Integer.compare(target.getPosX(), (int)x);
        int dy = Integer.compare(target.getPosY(), (int)y);
        x += dx;
        y += dy;
    }

    private void tryCaptureAnimal(Animal target, LivingManager livingManager) {
        if (distanceTo(target) <= 2) {
            livingManager.removeAnimal(target);

            if (Math.random() < 0.5) {
                active = false;
            }
        }
    }
}
