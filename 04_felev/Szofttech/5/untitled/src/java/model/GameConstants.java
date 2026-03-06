package model;

import java.awt.*;

public final class GameConstants {
    private GameConstants() {}

    // SCREEN SETTINGS
    public static final int ORIGINAL_TILE_SIZE = 40;  // in pixels (ex.: ...= 16 -> 16x16 tile)
    public static final int SCALE = 1; // multiplies size of tiles
    public static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;

    public static final int MAX_SCREEN_ROW = 100;
    public static final int MAX_SCREEN_COLUMN = 100;
    public static final int MAP_WIDTH = TILE_SIZE * MAX_SCREEN_COLUMN;
    public static final int MAP_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW;

    public static final int FPS = 60;

    // CAMERA
    public static final int CAMERA_SPEED = 30;
    public static final int CAMERA_WIDTH_TILE_NUM = 20;
    public static final int CAMERA_HEIGHT_TILE_NUM = 20;
    public static final int CAMERA_WIDTH = TILE_SIZE * CAMERA_WIDTH_TILE_NUM;
    public static final int CAMERA_HEIGHT = TILE_SIZE * CAMERA_HEIGHT_TILE_NUM;

    // MINIMAP
    public static final int MINIMAP_WIDTH = Math.min(250,CAMERA_WIDTH/4);
    public static final int MINIMAP_HEIGHT = Math.min(250,CAMERA_HEIGHT/4);
    public static final int MINIMAP_OUTLINE = 10;
    public static final double MINIMAP_TILE_SIZE = (double)TILE_SIZE * Math.min(((double)MINIMAP_WIDTH/(double)MAP_WIDTH),((double)MINIMAP_HEIGHT/(double)MAP_HEIGHT));
    public static final int MINIMAP_TILE_SIZE_INT = (int)MINIMAP_TILE_SIZE;
    public static final int MINIMAP_WIDTH_ACTUAL = MINIMAP_TILE_SIZE_INT * MAX_SCREEN_COLUMN;
    public static final int MINIMAP_HEIGHT_ACTUAL = MINIMAP_TILE_SIZE_INT * MAX_SCREEN_ROW;
    public static final int MINIMAP_X = CAMERA_WIDTH - MINIMAP_WIDTH_ACTUAL;
    public static final int MINIMAP_Y = CAMERA_HEIGHT - MINIMAP_HEIGHT_ACTUAL;
    public static final int MINIMAP_CAMERA_OUTLINE_WIDTH = MINIMAP_TILE_SIZE_INT * CAMERA_WIDTH_TILE_NUM;
    public static final int MINIMAP_CAMERA_OUTLINE_HEIGHT = MINIMAP_TILE_SIZE_INT * CAMERA_HEIGHT_TILE_NUM;

    // LAND
    public static final int STARTER_DIRT_FREQUENCY = 10;
    public static final int STARTER_GRASS_FREQUENCY = 2;
    public static final Color WATER_COLOR = Color.blue;
    public static final Color GRASS_COLOR = Color.green;
    public static final Color DIRT_COLOR = new Color(118, 113, 0);

    // ROAD
    public static final Color ROAD_COLOR = new Color(80, 80, 80); // Dark gray for asphalt

    // JEEP
    public static final Color JEEP_COLOR = new Color(150, 75, 0); // Brown color for the jeep
    public static final int JEEP_SPEED = 2; // Speed in pixels per update

    // SHOP
    public static final int SHOP_PANEL_WIDTH = 220;

    // PLANT
    public static final int PLANT_FREQUENCY = 2;

    //ANIMAL
    public static final double HERBIVORE_RATIO = 0.75;
    public static final int INITIAL_ANIMAL_COUNT = 20;
}
