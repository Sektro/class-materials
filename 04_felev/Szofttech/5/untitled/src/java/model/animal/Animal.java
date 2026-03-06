package model.animal;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

import controller.LivingManager;
import controller.TileManager;
import model.*;

public abstract class Animal{
    protected int age;// 1 - 5 (ezek legyenek state-ként megoldva)
    protected Age ageState;
    protected double hunger;
    protected double thirst;
    private boolean isAlive;
    protected int x;
    protected int y;
    protected JButton btn;
    protected LivingManager livingManager;
    protected TileManager tileManager;
    public List<int[]> currentPath;
    public int pathIndex;
    private boolean hasTrackingChip;

    public Animal(int age, double hunger, double thirst, int x, int y, LivingManager livingManager, TileManager tileManager) {
        this.age = age;
        this.hunger = hunger;
        this.thirst = thirst;
        this.isAlive = true;
        this.x = x;
        this.y = y;
        this.livingManager = new LivingManager(tileManager);
        this.tileManager = tileManager;
        this.currentPath = new ArrayList<>();
        this.checkAge();
        this.hasTrackingChip = false;
    }

    public void checkAge() {
        if (age >= 1 && age <= 3) {
            ageState = Age.BABY;
        } else if (age >= 4 && age <= 12) {
            ageState = Age.CHILD;
        } else if (age >= 13 && age <= 17) {
            ageState = Age.TEENAGER;
        } else if (age >= 18 && age <= 60) {
            ageState = Age.ADULT;
        } else if (age >= 61) {
            ageState = Age.OLD;
        }
    }

    public void gainHungerAndThirst() {
        if (ageState == Age.BABY || ageState == Age.CHILD) {
            hunger += 0.8;
            thirst += 0.8;
        }
        else if (ageState == Age.TEENAGER || ageState == Age.ADULT) {
            hunger += 1;
            thirst += 1;
        }
        else if (ageState == Age.OLD) {
            hunger += 1.4;
            thirst += 1.4;
        }
    }

    public void age() {
        List<Animal> animals = livingManager.getAnimals();
        for (Animal animal : animals) {
            animal.age++;
            if (animal.hunger > 10 || thirst > 10 || age >= 90) {
                animal.isAlive = false;
                animals.remove(animal);
            }
        }
    }

    public void move() {
        if (!currentPath.isEmpty() && pathIndex < currentPath.size()) {
            int[] nextStep = currentPath.get(pathIndex++);
            this.x = nextStep[0];
            this.y = nextStep[1];
            if (pathIndex >= currentPath.size()) {
                currentPath.clear();
            }
            else {
                if (thirst >= 6) {
                    findWater();
                } else if (hunger >= 6) {
                    findFood();
                } else {
                    wandering();
                }
            }
        }
    }

    protected void findWater() {
        Tile[][] map = tileManager.getMap();
        int closestWaterX = -1;
        int closestWaterY = -1;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].getLand() == LandTypes.WATER) {
                    double distance = Math.sqrt(Math.pow(i - x, 2) + Math.pow(j - y, 2));
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestWaterX = i;
                        closestWaterY = j;
                    }
                }
            }
        }
        if (closestWaterX != -1) {
            currentPath = PathFinder.findPath(x, y, closestWaterX, closestWaterY, map);
            pathIndex = 0;
        }
    }

    protected void wandering() {
        int randX = ((int)(4 * Math.random())) - 2;
        int randY = ((int)(4 * Math.random())) - 2;
        int newX = x + randX;
        int newY = y + randY;
        Tile[][] map = tileManager.getMap();

        if(newX >= 0 && newY >= 0 && newX < map.length &&newY < map[0].length && map[newX][newY].getLand() != LandTypes.WATER && map[newX][newY].getLand() != LandTypes.ROAD) {
            x = newX;
            y = newY;
        }
    }

    public int getPosX(){
        return x;
    }

    public int getPosY(){
        return y;
    }

    public boolean hasTrackingChip() {
        return hasTrackingChip;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setHasTrackingChip(boolean hasTrackingChip) {
        this.hasTrackingChip = hasTrackingChip;
    }

    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public JButton img(){
        return btn;
    }

    public double distanceToAnimal(Animal animal) {
        int dx = (animal.getPosX() - x);
        int dy = (animal.getPosY() - y);
        return Math.sqrt(dx * dx + dy * dy);
    }

    public abstract void drink();
    protected abstract void findFood();
}

