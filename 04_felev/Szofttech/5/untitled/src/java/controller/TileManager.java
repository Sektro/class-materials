package controller;

import java.awt.Point;

import model.GameConstants;
import model.NoiseGenerator;
import model.Tile;
import model.road.RoadNetwork;
import model.road.RoadSegment;
import model.shop.Shop;

public class TileManager {
    public Tile[][] getMap() {
        return map;
    }

    private final Tile[][] map = new Tile[GameConstants.MAX_SCREEN_ROW][GameConstants.MAX_SCREEN_COLUMN];
    private final NoiseGenerator noiseGenerator = new NoiseGenerator();
    private double min = 2;
    private double max = 0;
    private final RoadNetwork roadNetwork;
    private final JeepManager jeepManager;
    private final TouristManager touristManager;
    private final LivingManager livingManager;
    private final TimeManager timeManager;
    private final Shop shop;

    public TileManager() {
        shop = new Shop(25000);
        initMap();
        roadNetwork = new RoadNetwork();
        setupEntranceAndExitToMap();
        touristManager = new TouristManager(shop);
        livingManager = new LivingManager(this);
        jeepManager = new JeepManager(roadNetwork, touristManager, livingManager);
        timeManager = new TimeManager();

        livingManager.generatePlants(map);
        livingManager.generateAnimals();
    }

    private void initMap() {
        // GENERATING PERLIN NOISE
        double[][] mapNoise = new double[GameConstants.MAX_SCREEN_ROW][GameConstants.MAX_SCREEN_COLUMN];
        for (int i = 0; i < GameConstants.MAX_SCREEN_ROW; ++i) {
            for (int j = 0; j < GameConstants.MAX_SCREEN_COLUMN; ++j) {
                mapNoise[i][j] = Math.abs(noiseGenerator.noise(j,i));
                if (mapNoise[i][j] > max) {max = mapNoise[i][j];}
                if (mapNoise[i][j] < min) {min = mapNoise[i][j];}
            }
        }
        // TURNING PERLIN NOISE INTO ACTUAL MAP
        for (int i = 0; i < GameConstants.MAX_SCREEN_ROW; ++i) {
            for (int j = 0; j < GameConstants.MAX_SCREEN_COLUMN; ++j) {
                double tileValue = mapNoise[i][j];
                if (tileValue < min + (max-min)/GameConstants.STARTER_DIRT_FREQUENCY) {map[i][j] = new Tile(i,j,GameConstants.TILE_SIZE,GameConstants.TILE_SIZE,2);}
                else if (tileValue >= min + (max-min)/GameConstants.STARTER_DIRT_FREQUENCY && tileValue < min + (max-min)/GameConstants.STARTER_GRASS_FREQUENCY) {map[i][j] = new Tile(i,j,GameConstants.TILE_SIZE,GameConstants.TILE_SIZE,1);}
                else {map[i][j] = new Tile(i,j,GameConstants.TILE_SIZE,GameConstants.TILE_SIZE,0);}
            }
        }
    }

    private void setupEntranceAndExitToMap() {
        RoadSegment entrance = roadNetwork.getEntrance();
        RoadSegment exit = roadNetwork.getExit();

        if (entrance !=null) {
            createRoadSegmentOnMap(entrance, 4);
        }

        if(exit != null) {
            createRoadSegmentOnMap(exit, 5);
        }
        
    }

    private void createRoadSegmentOnMap(RoadSegment roadSegment, int type) {
        Point[] points = roadSegment.getRoad();
        for (Point point : points) {
            int x = (int) point.getX();
            int y = (int) point.getY();
            if (x >= 0 && x < GameConstants.MAX_SCREEN_ROW && y >= 0 && y < GameConstants.MAX_SCREEN_COLUMN) {
                map[y][x] = new Tile(y, x, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE, type);
            }
        }
    }

    public void addNewRoadSegmentToMap() {
        RoadSegment newRoadSegment = roadNetwork.getLastAddedRoadSegment();
        if (newRoadSegment != null) {
            createRoadSegmentOnMap(newRoadSegment, 3);
        }
    }

    public void addJeep() {
        jeepManager.addJeep();
    }

    public void updateJeeps() {
        //jeepManager.updateJeeps();
    }

    public void updateLivings() {
        livingManager.updateAnimals();
    }

    public TouristManager getTouristManager() {
        return touristManager;
    }
    
    public JeepManager getJeepManager() {
        return jeepManager;
    }

    public RoadNetwork getRoadNetwork() {
        return roadNetwork;
    }

    public LivingManager getLivingManager() {
        return livingManager;
    }

    public Shop getShop() {
        return shop;
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }
}
