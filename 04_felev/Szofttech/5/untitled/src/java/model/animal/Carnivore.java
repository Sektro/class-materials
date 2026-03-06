package model.animal;

import javax.swing.JButton;
import java.util.*;

import controller.LivingManager;
import controller.TileManager;
import model.*;
import model.GameConstants;

public class Carnivore extends Animal {
    protected CarnivoreType carnivoreType;

    public Carnivore(int age, double hunger, double thirst, int x, int y, CarnivoreType carnivoreType, TileManager tileManager) {
        super(age, hunger, thirst, x, y, new LivingManager(tileManager), tileManager);
        this.carnivoreType = carnivoreType;
        this.btn = new JButton();
        this.btn.setText(this.carnivoreType.name());
        this.btn.setBounds(x, y, 80, 50);
    }

    public void eatHerbivore() {
        List<Animal> animals = livingManager.getAnimals();
        for (Animal herbivore : animals) {
            if (carnivoreType.equals(CarnivoreType.Lion)) {
                if (herbivore instanceof Herbivore) {
                    do {
                        hunger = hunger -6;
                        if (hunger < 0) {
                            hunger = 0;
                        }
                    } while (hunger > 0);
                }
            }
            else if (carnivoreType.equals(CarnivoreType.Hyena)) {
                if (herbivore instanceof Herbivore) {
                    do {
                        hunger = hunger - 4;
                        if (hunger < 0) {
                            hunger = 0;
                        }
                    } while (hunger > 0);
                }
            }
        }
    }

    @Override
    public void drink() {
        int x = (int) (this.x / GameConstants.TILE_SIZE);
        int y = (int) (this.y / GameConstants.TILE_SIZE);
        Tile currentTile = tileManager.getMap()[x][y];
        if (currentTile.getLand() == LandTypes.WATER && thirst >= 6) {
            if (carnivoreType.equals(CarnivoreType.Lion)) {
                do {
                    thirst = thirst - 6;
                    if (thirst < 0) {
                        thirst = 0;
                    }
                } while (thirst > 0);
            } else if (carnivoreType.equals(CarnivoreType.Hyena)) {
                do {
                    thirst = thirst - 4;
                    if (thirst < 0) {
                        thirst = 0;
                    }
                } while (thirst > 0);
            }
        }
    }

    public void breedCarnivore(Carnivore animal1, Carnivore animal2) {
        Carnivore animal3;
        if (animal1.ageState == Age.ADULT && animal2.ageState == Age.ADULT) {
            if (animal1.hunger == 0 && animal2.hunger == 0 && animal1.distanceToAnimal(animal2) < 5) {
                if (animal1.carnivoreType.equals(CarnivoreType.Lion) && animal2.carnivoreType.equals(CarnivoreType.Lion)) {
                    animal3 = new Carnivore(1, 2, 2, animal1.x, animal1.y - 2, CarnivoreType.Lion, tileManager);
                    livingManager.getAnimals().add(animal3);
                    livingManager.getObjects().add(animal3);
                } else if (animal1.carnivoreType.equals(CarnivoreType.Hyena) && animal2.carnivoreType.equals(CarnivoreType.Hyena)) {
                    animal3 = new Carnivore(1, 2, 2, animal1.x, animal1.y - 2, CarnivoreType.Hyena, tileManager);
                    livingManager.getAnimals().add(animal3);
                    livingManager.getObjects().add(animal3);
                }
            }
        }
    }

    @Override
    protected void findFood() {
        List<Animal> animals = livingManager.getAnimals();
        Animal closest = null;
        double minDistance = Double.MAX_VALUE;
        for (Animal animal : animals) {
            if (animal instanceof Herbivore) {
                double distance = distanceToAnimal(animal);
                if (distance < minDistance) {
                    minDistance = distance;
                    closest = animal;
                }
            }
        }
        if (closest != null) {
            Tile[][] map = tileManager.getMap();
            currentPath = PathFinder.findPath(x, y, closest.getPosX(), closest.getPosY(), map);
            pathIndex = 0;
        }
    }

    public CarnivoreType getCarnivoreType() {
        return carnivoreType;
    }
}