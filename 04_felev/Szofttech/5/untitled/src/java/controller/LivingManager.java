package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.*;
import model.animal.*;
import model.plant.*;

public class LivingManager {
    private List<Object> objects;
    private final List<Animal> animals = new ArrayList<>();
    private final List<Guardian> guardians = new ArrayList<>();
    private final List<Hunter> hunters = new ArrayList<>();
    private final List<Plant> plants = new ArrayList<>();
    private TileManager tileManager;
    private final Random random = new Random();
    // Cache for valid positions used because of generating many animals in succession
    private List<int[]> cachedValidPositions = null;

    // generation
    private int plantTypeCounter = 0;

    public LivingManager(TileManager tileManager) {
        objects = new ArrayList<>();
        this.tileManager = tileManager;
    }

    public void generateAnimals() {
        Tile[][] map = tileManager.getMap();
        for (int i = 0; i < GameConstants.INITIAL_ANIMAL_COUNT; i++) {
            int[] validPositions = findValidPosition(map);
            int x = validPositions[0];
            int y = validPositions[1];

            Animal newAnimal;
            if (random.nextDouble() < GameConstants.HERBIVORE_RATIO) {
                HerbivoreType type = HerbivoreType.values()[random.nextInt(HerbivoreType.values().length)];
                newAnimal = new Herbivore(random.nextInt(60) + 1, random.nextDouble() * 5, random.nextDouble() * 5, x*GameConstants.TILE_SIZE, y*GameConstants.TILE_SIZE, type, tileManager);
            }
            else {
                CarnivoreType type = CarnivoreType.values()[random.nextInt(CarnivoreType.values().length)];
                newAnimal = new Carnivore(random.nextInt(60) + 1, random.nextDouble() * 5, random.nextDouble() * 5, x*GameConstants.TILE_SIZE, y*GameConstants.TILE_SIZE, type, tileManager);
            }
            
            // Add to both collections
            animals.add(newAnimal);
            objects.add(newAnimal);
        }
    }

    private int[] findValidPosition(Tile[][] map) {
        // Initialize cache
        if (cachedValidPositions == null  || cachedValidPositions.isEmpty()) {
            cachedValidPositions = new ArrayList<>();
            
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[0].length; x++) {
                    if (map[y][x].getLand() != LandTypes.WATER &&
                        map[y][x].getLand() != LandTypes.ROAD && 
                        map[y][x].getLand() != LandTypes.ENTRANCE && 
                        map[y][x].getLand() != LandTypes.EXIT 
                        && !isPositionOccupied(x, y)) {
                        cachedValidPositions.add(new int[]{x, y});
                    }
                }
            }
            
            if (cachedValidPositions.isEmpty()) {
                return new int[]{0, 0};
            }
        }
        
        // Return a random valid position
        int randomIndex = random.nextInt(cachedValidPositions.size());
        int[] validPosition = cachedValidPositions.get(randomIndex);
        cachedValidPositions.remove(randomIndex);

        return validPosition;
    }

    private boolean isPositionOccupied(int x, int y) {
        for (Plant plant : plants) {
            if (plant.getPosX() == x && plant.getPosY() == y) {
                return true;
            }
        }
        return false;
    }

    public void generatePlants(Tile[][] map) {
        NoiseGenerator noiseGenerator = new NoiseGenerator();
        double min = 2;
        double max = 0;
        double[][] mapNoise = new double[map.length][map[0].length];
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[0].length; ++j) {
                mapNoise[i][j] = Math.abs(noiseGenerator.noise(j,i));
                if (mapNoise[i][j] > max) {max = mapNoise[i][j];}
                if (mapNoise[i][j] < min) {min = mapNoise[i][j];}
            }
        }
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                if (mapNoise[i][j] > min + (max-min)/GameConstants.PLANT_FREQUENCY && map[i][j].getLand() != LandTypes.WATER && map[i][j].getLand() != LandTypes.ROAD)
                {
                    switch (plantTypeCounter) {
                        case 0:
                            Plant plant1 = new Plant(5, PlantType.PalmTree ,j*GameConstants.TILE_SIZE,i*GameConstants.TILE_SIZE);
                            objects.add(plant1);
                            break;
                        case 1:
                            Plant plant2 = new Plant(5, PlantType.BaobabTree ,j*GameConstants.TILE_SIZE,i*GameConstants.TILE_SIZE);
                            objects.add(plant2);
                            break;
                        case 2:
                            Plant plant3 = new Plant(5, PlantType.Bush ,j*GameConstants.TILE_SIZE,i*GameConstants.TILE_SIZE);
                            objects.add(plant3);
                            break;
                    }
                    ++plantTypeCounter;
                    if (plantTypeCounter > 2) {plantTypeCounter = 0;}
                }
            }
        }
    }

    public void updateAnimals(){
        for (Animal animal : animals) {
            animal.gainHungerAndThirst();
            animal.age();
            animal.move();

            // Problem was coordinates were in pixels, but tile coordinates were in tiles
            // Convert pixel coordinates to tile coordinates
            int tileX = (int) (animal.getPosX() / GameConstants.TILE_SIZE);
            int tileY = (int) (animal.getPosY() / GameConstants.TILE_SIZE);

            if (tileManager.getMap()[tileX][tileY].getLand() == LandTypes.WATER) {
                animal.drink();
            }
            else if (animal instanceof Herbivore) {
                for (Plant plant : plants) {
                    // Same here
                    int plantTileX = plant.getPosX() / GameConstants.TILE_SIZE;
                    int plantTileY = plant.getPosY() / GameConstants.TILE_SIZE;
                    
                    if (plantTileX == tileX && plantTileY == tileY) {
                        ((Herbivore) animal).eat();
                    }
                }
            }
            else if (animal instanceof Carnivore) {
                for (Animal prey : animals) {
                    // Same here
                    int preyTileX = prey.getPosX() / GameConstants.TILE_SIZE;
                    int preyTileY = prey.getPosY() / GameConstants.TILE_SIZE;
                    
                    if (prey instanceof Herbivore && preyTileX == tileX && preyTileY == tileY) {
                        ((Carnivore) animal).eatHerbivore();
                        prey.setIsAlive(false);
                    }
                }
            }
            for (Animal animal2 : animals) {
                if (animal instanceof Carnivore && animal2 instanceof Carnivore) {
                    // Check if they're in the same tile before breeding
                    int animal2TileX = animal2.getPosX() / GameConstants.TILE_SIZE;
                    int animal2TileY = animal2.getPosY() / GameConstants.TILE_SIZE;
                    
                    if (tileX == animal2TileX && tileY == animal2TileY) {
                        ((Carnivore) animal).breedCarnivore((Carnivore) animal, (Carnivore) animal2);
                    }
                }
                else if (animal instanceof Herbivore && animal2 instanceof Herbivore) {
                    // Check if they're in the same tile before breeding
                    int animal2TileX = animal2.getPosX() / GameConstants.TILE_SIZE;
                    int animal2TileY = animal2.getPosY() / GameConstants.TILE_SIZE;
                    
                    if (tileX == animal2TileX && tileY == animal2TileY) {
                        ((Herbivore) animal).breedHerbivore((Herbivore) animal, (Herbivore) animal2);
                    }
                }
            }
        }
        animals.removeIf(animal -> !animal.isAlive());
        objects.removeIf(object -> object instanceof Animal && !((Animal) object).isAlive());
    }

    public List<Object> getObjectsNearby(int tileX, int tileY, int radius) {
        List<Object> nearbyObjects = new ArrayList<>();
        
        for (Object obj : objects) {
            if (obj instanceof Animal) {
                Animal animal = (Animal) obj;
                int animalTileX = animal.getPosX() / GameConstants.TILE_SIZE;
                int animalTileY = animal.getPosY() / GameConstants.TILE_SIZE;
                
                // Check if within radius
                if (Math.abs(animalTileX - tileX) <= radius && 
                    Math.abs(animalTileY - tileY) <= radius) {
                    nearbyObjects.add(animal);
                }
            }
        }
        
        return nearbyObjects;
    }

    public Object getObjectAt(int x, int y) {
        // Calculate click radius (tolerance for clicking)
        int radius = GameConstants.TILE_SIZE / 2;
        
        for (Object obj : objects) {
            int objX = 0;
            int objY = 0;
            int objSize = GameConstants.TILE_SIZE / 2;
            
            if (obj instanceof Animal) {
                Animal animal = (Animal) obj;
                objX = animal.getPosX();
                objY = animal.getPosY();
            } else if (obj instanceof Plant) {
                Plant plant = (Plant) obj;
                objX = plant.getPosX();
                objY = plant.getPosY();
            } else {
                continue; // Skip non-sellable objects
            }
            
            // Check if click is within object bounds (+/- radius for easier clicking)
            if (x >= objX - radius && x <= objX + objSize + radius &&
                y >= objY - radius && y <= objY + objSize + radius) {
                return obj;
            }
        }
        
        return null;
    }

    public boolean removeObject(Object obj) {
       if(obj instanceof Animal) {
            removeAnimal((Animal) obj);
        } else if (obj instanceof Plant) {
            removePlant((Plant) obj);
        } else {
            return false; // Object type not supported for removal
        }
        
        objects.remove(obj);
        return true; // Successfully removed
    }

    public void addAnimal(Animal animal){
        objects.add(animal);
        animals.add(animal);
    }

    public void removeAnimal(Animal animal) {
        objects.remove(animal);
        animals.remove(animal);
    }

    private void removePlant(Plant plant) {
        objects.remove(plant);
        plants.remove(plant);
    }

    public List<Guardian> getGuardians() {
        return new ArrayList<Guardian>(guardians);
    }

    public List<Hunter> getHunters() {
        return new ArrayList<Hunter>(hunters);
    }

    public List<Animal> getAnimals() {
        return new ArrayList<Animal>(animals);
    }

    public List<Plant> getPlants() {
        return new ArrayList<Plant>(plants);
    }

    public void addPlant(Plant plant){
        objects.add(plant);
        plants.add(plant);
    }

    public List<Object> getObjects() {
        return objects;
    }
}
