package model;

import controller.LivingManager;
import controller.TileManager;
import model.animal.Animal;
import model.plant.Plant;

public class PlacementValidator{
  private static final int MIN_DISTANCE = GameConstants.TILE_SIZE;

  public static boolean isValidPlacement(int x, int y, TileManager tileManager, LivingManager livingManager) {
    // Check if placement is on road
    if (isOnRoad(x, y, tileManager)) {
        return false;
    }

    // Check if placement is on water
    if (isOnWater(x, y, tileManager)) {
        return false;
    }

    // Check collision with other objects
    if (isCollidingWithOthers(x, y, livingManager)) {
        return false;
    }

    return true;
  }

  public static boolean isValidRoadPlacement(int x, int y, TileManager tileManager, LivingManager livingManager) {
    // Convert to tile coordinates
    int tileX = x / GameConstants.TILE_SIZE;
    int tileY = y / GameConstants.TILE_SIZE;
    
    // Check all four tiles in the 2x2 area starting from the top left
    for (int dy = 0; dy < 2; dy++) {
      for (int dx = 0; dx < 2; dx++) {
        int newX = tileX + dx;
        int newY = tileY + dy;
        
        if (!isValidRoadTile(newX, newY, tileManager, livingManager)) {
          return false;
        }
      }
    }
    
    return true;
  }

  private static boolean isValidRoadTile(int x, int y, TileManager tileManager, LivingManager livingManager) {
    // Check bounds
    if (x < 0 || x >= GameConstants.MAX_SCREEN_COLUMN ||
        y < 0 || y >= GameConstants.MAX_SCREEN_ROW) {
        return false;
    }
    
    // Check if tile is water or already road, entrance or exit
    if (tileManager.getMap()[y][x].getLand() == LandTypes.WATER ||
        tileManager.getMap()[y][x].getLand() == LandTypes.ROAD ||
        tileManager.getMap()[y][x].getLand() == LandTypes.ENTRANCE || tileManager.getMap()[y][x].getLand() == LandTypes.EXIT) {
        return false;
    }
    
    // Check for collisions with animals/plants
    return !isCollidingWithOthers(x * GameConstants.TILE_SIZE, y * GameConstants.TILE_SIZE, livingManager);
  }

  private static boolean isOnRoad(int x, int y, TileManager tileManager) {
    int tileX = x / GameConstants.TILE_SIZE;
    int tileY = y / GameConstants.TILE_SIZE;

    // Check if coordinates are within map bounds
    if (tileX < 0 || tileX >= GameConstants.MAX_SCREEN_COLUMN || 
        tileY < 0 || tileY >= GameConstants.MAX_SCREEN_ROW) {
        return true;
    }

    return tileManager.getMap()[tileY][tileX].getLand() == LandTypes.ROAD;
  }
  
  private static boolean isOnWater(int x, int y, TileManager tileManager) {
    int tileX = x / GameConstants.TILE_SIZE;
    int tileY = y / GameConstants.TILE_SIZE;

    // Check if coordinates are within map bounds
    if (tileX < 0 || tileX >= GameConstants.MAX_SCREEN_COLUMN || 
        tileY < 0 || tileY >= GameConstants.MAX_SCREEN_ROW) {
        return true;
    }

    return tileManager.getMap()[tileY][tileX].getLand() == LandTypes.WATER;
  }
  
  private static boolean isCollidingWithOthers(int x, int y, LivingManager livingManager) {
    for (Object obj : livingManager.getObjects()) {
        if (obj instanceof Animal) {
            Animal animal = (Animal) obj;
            double distance = calculateDistance(x, y, animal.getPosX(), animal.getPosY());
            if (distance < MIN_DISTANCE) {
                return true;
            }
        } else if (obj instanceof Plant) {
            Plant plant = (Plant) obj;
            double distance = calculateDistance(x, y, plant.getPosX(), plant.getPosY());
            if (distance < MIN_DISTANCE) {
                return true;
            }
        }
    }
    return false;
  }

  private static double calculateDistance(int x1, int y1, int x2, int y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
  }
}