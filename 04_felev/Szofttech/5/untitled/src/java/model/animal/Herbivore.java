package model.animal;
import javax.swing.JButton;
import java.util.*;

import controller.LivingManager;
import controller.TileManager;
import model.*;
import model.plant.*;

public class Herbivore extends Animal {
    protected HerbivoreType herbivoreType;

    public Herbivore(int age, double hunger, double thirst, int x, int y, HerbivoreType herbivoreType, TileManager tileManager) {
        super(age, hunger, thirst, x, y, new LivingManager(tileManager), tileManager);
        this.herbivoreType = herbivoreType;
        this.btn = new JButton();
        this.btn.setText(this.herbivoreType.name());
        this.btn.setBounds(x, y, 80, 50);
    }

    public void eat() {
        List<Plant> plants = livingManager.getPlants();
        for (Plant plant : plants) {
            while (hunger >= 6) {
                if (herbivoreType.equals(HerbivoreType.Zebra)) {
                    if (plant.getPlantType().equals(PlantType.Bush)) {
                        hunger = hunger - plant.getNutrition();
                        if (hunger < 0) {
                            hunger = 0;
                        }
                    } else if (plant.getPlantType().equals(PlantType.PalmTree)) {
                        hunger = hunger - plant.getNutrition();
                        if (hunger < 0) {
                            hunger = 0;
                        }
                    } else if (plant.getPlantType().equals(PlantType.BaobabTree)) {
                        hunger = hunger - plant.getNutrition();
                        if (hunger < 0) {
                            hunger = 0;
                        }
                    }
                } else if (herbivoreType.equals(HerbivoreType.Elephant)) {
                    if (plant.getPlantType().equals(PlantType.Bush)) {
                        hunger = hunger - plant.getNutrition();
                        if (hunger < 0) {
                            hunger = 0;
                        }
                    } else if (plant.getPlantType().equals(PlantType.PalmTree)) {
                        hunger = hunger - plant.getNutrition();
                        if (hunger < 0) {
                            hunger = 0;
                        }
                    } else if (plant.getPlantType().equals(PlantType.BaobabTree)) {
                        hunger = hunger - plant.getNutrition();
                        if (hunger < 0) {
                            hunger = 0;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void drink() {
        int x = (int) (getPosX() / GameConstants.TILE_SIZE);
        int y = (int) (getPosY() / GameConstants.TILE_SIZE);
        Tile currentTile = tileManager.getMap()[x][y];
        if (currentTile.getLand() == LandTypes.WATER && thirst >= 6) {
            if (herbivoreType.equals(HerbivoreType.Zebra)) {
                do {
                    thirst = thirst - 6;
                    if (thirst < 0) {
                        thirst = 0;
                    }
                } while (thirst > 0);
            } else if (herbivoreType.equals(HerbivoreType.Elephant)) {
                do {
                    thirst = thirst -8;
                    if (thirst < 0) {
                        thirst = 0;
                    }
                } while (thirst > 0);
            }
        }
    }

    @Override
    protected void findFood() {
        Tile[][] map = tileManager.getMap();
        List<Plant> plants = livingManager.getPlants();
        int closestFoodX = -1;
        int closestFoodY = -1;
        double minDistance = Double.MAX_VALUE;

        for (Plant plant : plants) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    if (map[i][j] == map[plant.getPosX()][plant.getPosY()]) {
                        double distance = Math.sqrt(Math.pow(i - x, 2) + Math.pow(j - y, 2));
                        if (distance < minDistance) {
                            minDistance = distance;
                            closestFoodX = i;
                            closestFoodY = j;
                        }
                    }
                }
            }
        }
        if (closestFoodX != -1) {
            currentPath = PathFinder.findPath(x, y, closestFoodX, closestFoodY, map);
            pathIndex = 0;
        }
    }

    public void breedHerbivore(Herbivore animal1, Herbivore animal2) {
        Animal animal3;
        if (animal1.ageState == Age.ADULT && animal2.ageState == Age.ADULT) {
            if (animal1.hunger == 0 && animal2.hunger == 0 && animal1.distanceToAnimal(animal2) < 5) {
                if (animal1.herbivoreType.equals(HerbivoreType.Elephant) && animal2.herbivoreType.equals(HerbivoreType.Elephant)) {
                    animal3 = new Herbivore(1, 2, 2, animal1.getPosX(), animal1.getPosY() - 2, HerbivoreType.Elephant, tileManager);
                    livingManager.getAnimals().add(animal3);
                    livingManager.getObjects().add(animal3);
                } else if (animal1.herbivoreType.equals(HerbivoreType.Zebra) && animal2.herbivoreType.equals(HerbivoreType.Zebra)) {
                    animal3 = new Herbivore(1, 2, 2, animal1.getPosX(), animal1.getPosY() - 2, HerbivoreType.Zebra, tileManager);
                    livingManager.getAnimals().add(animal3);
                    livingManager.getObjects().add(animal3);
                }
            }
        }
    }

    public HerbivoreType getHerbivoreType() {
        return herbivoreType;
    }
}
