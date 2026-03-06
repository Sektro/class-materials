package view;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import controller.KeyHandler;
import controller.LivingManager;
import controller.TileManager;
import model.*;
import model.animal.Carnivore;
import model.animal.Herbivore;
import model.jeep.Jeep;
import model.jeep.JeepState;
import model.plant.PlantType;
import model.shop.Shop;
import controller.TimeManager;
import model.animal.Animal;
import model.plant.Plant;
import controller.TouristManager;

import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel  {

    // alapok
    private final TileManager tileManager = new TileManager();
    private final LivingManager livingManager = tileManager.getLivingManager();
    private final TimeManager timeManager = tileManager.getTimeManager();

    // render
    private final KeyHandler cameraKey = new KeyHandler();
    private final Camera cam = new Camera(GameConstants.MAP_HEIGHT/2,GameConstants.MAP_WIDTH/2, GameConstants.CAMERA_WIDTH, GameConstants.CAMERA_HEIGHT);
    private final BufferedImage waterSprite;
    private final BufferedImage grassSprite;
    private final BufferedImage dirtSprite;

    // timer
    private Timer gameTimer;
    private int gameAction = 0;

    private String placementMode = null;
    private final Shop shop = tileManager.getShop();
    private ShopPanel shopPanel;
    private boolean sellMode = false;

    private TouristManager touristManager = tileManager.getTouristManager();

    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(GameConstants.CAMERA_WIDTH,GameConstants.CAMERA_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // improved rendering performance
        this.addKeyListener(cameraKey);
        this.setFocusable(true); // will receive key input

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // Exit placement or sell mode on right-click
                    placementMode = null;
                    sellMode = false;
                    setCursor(Cursor.getDefaultCursor());
                }

                int worldX = e.getX() + cam.getX() - (GameConstants.CAMERA_WIDTH/2);
                int worldY = e.getY() + cam.getY() - (GameConstants.CAMERA_HEIGHT/2);


                if(placementMode != null && placementMode.equals("Jeep")) {
                    //Jeep
                    if(placementMode.equals("Jeep")) {
                       Point entranceLocation = tileManager.getJeepManager().getEntrancePoint();

                       if(entranceLocation != null) {
                          tileManager.getJeepManager().addJeep();
                          shop.deductMoney(placementMode);
                          updateShopPanel();
                        }
                    } else {
                        JOptionPane.showMessageDialog(GamePanel.this,
                            "No entrance found for Jeep.",
                            "Invalid Placement",
                            JOptionPane.WARNING_MESSAGE
                        );
                    }
                } else if (placementMode != null && placementMode.equals("Road")) {

                    if(e.getButton() == MouseEvent.BUTTON1) {
                        if (PlacementValidator.isValidRoadPlacement(worldX, worldY, tileManager, livingManager) && tileManager.getRoadNetwork().buildRoad(worldX, worldY)) {
                            tileManager.addNewRoadSegmentToMap();
                            shop.deductMoney(placementMode);
                            updateShopPanel();
                            placementMode = null;
                            setCursor(Cursor.getDefaultCursor());
                        } else {
                            JOptionPane.showMessageDialog(GamePanel.this,
                                "Cannot place Road there....",
                                "Invalid Placement",
                                JOptionPane.WARNING_MESSAGE);
                        }
                        requestFocusInWindow();
                    }
                } else if(placementMode != null && placementMode.equals("Tracking Chip")){
                    Object clickedObject = livingManager.getObjectAt(worldX, worldY);
            
                    if (clickedObject instanceof Animal) {
                        Animal animal = (Animal) clickedObject;
                        
                        if (!animal.hasTrackingChip()) {
                            boolean success = shop.purchaseTrackingChip(animal);
                            
                            if (success) {
                                updateShopPanel();
                            } else {
                                JOptionPane.showMessageDialog(GamePanel.this,
                                    "Not enough money for chip!",
                                    "Failed purchase",
                                    JOptionPane.WARNING_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(GamePanel.this,
                                "Animal is already chipped!",
                                "Failed purchase",
                                JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(GamePanel.this,
                            "Can only place chip in animal!",
                            "Invalid target",
                            JOptionPane.WARNING_MESSAGE);
                    }
                } else if (PlacementValidator.isValidPlacement(worldX, worldY, tileManager, livingManager)) {
                    Object newItem = shop.createPurchasedItem(placementMode, worldX, worldY);
                    if (newItem != null) {
                        if (newItem instanceof Animal) {
                            tileManager.getLivingManager().addAnimal((Animal)newItem);
                            shop.deductMoney(placementMode);
                            updateShopPanel();
                        } else if (newItem instanceof Plant) {
                            tileManager.getLivingManager().addPlant((Plant)newItem);
                            shop.deductMoney(placementMode);
                            updateShopPanel();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(GamePanel.this,
                        "Cannot place object here. Make sure it's not on road, water or too close to other objects.",
                        "Invalid Placement",
                        JOptionPane.WARNING_MESSAGE);
                }

                if (sellMode && e.getButton() == MouseEvent.BUTTON1) {
                    // Find object at click location
                    Object clickedObject = livingManager.getObjectAt(worldX, worldY);
                    
                    if (clickedObject != null) {
                        // Confirm sale
                        int salePrice = shop.sellItem(clickedObject);
                        
                        if (salePrice > 0) {
                            // Remove the object from the game
                            livingManager.removeObject(clickedObject);
                        
                            // Update shop panel money display
                            updateShopPanel();
                        }
                    } else {
                        JOptionPane.showMessageDialog(GamePanel.this,
                            "No sellable object at this location",
                            "Sale Failed",
                            JOptionPane.WARNING_MESSAGE);
                    }
                }

                requestFocusInWindow();
            }
        });

        // sprite-ok
        waterSprite = ImageIO.read(new File("src/main/resources/water.png"));
        grassSprite = ImageIO.read(new File("src/main/resources/grass.png"));
        dirtSprite = ImageIO.read(new File("src/main/resources/dirt.png"));

        startGame();
    }


    public void setShopPanel(ShopPanel shopPanel) {
        this.shopPanel = shopPanel;
    }

    private void updateShopPanel(){
        if (shopPanel != null) {
            shopPanel.updateMoneyLabel();
        }
    }

    public void setSellMode(boolean sellMode) {
        this.sellMode = sellMode;
        this.placementMode = null; // Exit placement mode if we're in it
        
        if (sellMode) {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        } else {
            setCursor(Cursor.getDefaultCursor());
        }
    }


    private void startGame() {
        gameTimer = new Timer(10, e -> gameLoop());
    }
    public void startTimer() {
        gameTimer.start();
    }
    public void stopTimer() {
        gameTimer.stop();
    }

    private void gameLoop() {
        update();
        timeManager.update();
        repaint();
        if (gameAction == 50) {
            gameAction = 0;
        }
        else {
            ++gameAction;
        }
    }
    private void update() {
        // CAMERA
        cam.update(cameraKey);

        tileManager.updateJeeps();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        try {
            renderMap(g2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        renderLiving(g2);

        // Render Jeeps (all of them)
        ArrayList<Jeep> jeeps = tileManager.getJeepManager().getJeeps();
        if (jeeps != null && !jeeps.isEmpty()) {
            for (Jeep jeep : jeeps) {
                renderJeep(g2, jeep);
            }
        }

        renderMiniMap(g2);

        // Render Jeeps on minimap
        if (jeeps != null && !jeeps.isEmpty()) {
            for (Jeep jeep : jeeps) {
                renderJeepOnMinimap(g2, jeep);
            }
        }

        renderStatistics(g2);

        if (gameAction == 50) {
            renderGameAction(g2);
        }

        g2.dispose();
    }
    private void renderGameAction(Graphics2D g2) {
        //Animal
        tileManager.updateLivings();
    }
    private void renderMap(Graphics2D g2) throws IOException {
        BufferedImage spriteImage = null;
        Tile[][] map = tileManager.getMap();
        for (int i = 0; i < GameConstants.MAX_SCREEN_ROW; ++i) {
            for (int j = 0; j < GameConstants.MAX_SCREEN_COLUMN; ++j) {
                if (!cam.isInView(map[i][j]))
                    continue;
                if (map[i][j].getLand() == LandTypes.WATER) {
                    spriteImage = waterSprite;
                }
                else if (map[i][j].getLand() == LandTypes.GRASS) {
                    spriteImage = grassSprite;
                }
                else if (map[i][j].getLand() == LandTypes.DIRT) {
                    spriteImage = dirtSprite;
                }
                else if(map[i][j].getLand() == LandTypes.ENTRANCE) {
                    g2.setColor(new Color(0, 150, 0));
                }
                else if(map[i][j].getLand() == LandTypes.EXIT) {
                    g2.setColor(new Color(150, 0, 0));
                }
                else  {
                    spriteImage = ImageIO.read(new File("src/main/resources/dirt.png"));
                }

                if(map[i][j].getLand() == LandTypes.ENTRANCE || map[i][j].getLand() == LandTypes.EXIT) {
                    g2.fillRect(j*GameConstants.TILE_SIZE - cam.getX() + (GameConstants.CAMERA_WIDTH/2),i*GameConstants.TILE_SIZE - cam.getY() + (GameConstants.CAMERA_HEIGHT/2), GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);

                    if(map[i][j].getLand() == LandTypes.ENTRANCE) {
                       drawEntrance(g2, i, j);
                    }
                    else if(map[i][j].getLand() == LandTypes.EXIT) {
                        drawExit(g2, i, j);
                    }
                }
                else{
                    g2.drawImage(spriteImage,j*GameConstants.TILE_SIZE - cam.getX() + (GameConstants.CAMERA_WIDTH/2),i*GameConstants.TILE_SIZE - cam.getY() + (GameConstants.CAMERA_HEIGHT/2), null);
                }
                
            }
        }
    }

    private void drawEntrance(Graphics2D g2, int x, int y) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 10));
        g2.drawString("IN", y*GameConstants.TILE_SIZE - cam.getX() + (GameConstants.CAMERA_WIDTH/2), x*GameConstants.TILE_SIZE - cam.getY() + (GameConstants.CAMERA_HEIGHT/2));
    }

    private void drawExit(Graphics2D g2, int x, int y) {
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 10));
        g2.drawString("OUT", y*GameConstants.TILE_SIZE - cam.getX() + (GameConstants.CAMERA_WIDTH/2), x*GameConstants.TILE_SIZE - cam.getY() + (GameConstants.CAMERA_HEIGHT/2));
    }

    private void renderMiniMap(Graphics2D g2) {
        Tile[][] map = tileManager.getMap();

        // Draw minimap background
        g2.setColor(Color.black);
        g2.fillRect(
            GameConstants.MINIMAP_X - GameConstants.MINIMAP_OUTLINE,
            GameConstants.MINIMAP_Y - GameConstants.MINIMAP_OUTLINE,
            GameConstants.MINIMAP_WIDTH + GameConstants.MINIMAP_OUTLINE * 2,
            GameConstants.MINIMAP_HEIGHT + GameConstants.MINIMAP_OUTLINE * 2
        );

        // Draw minimap tiles
        for (int i = 0; i < GameConstants.MAX_SCREEN_ROW; ++i) {
            for (int j = 0; j < GameConstants.MAX_SCREEN_COLUMN; ++j) {
                if (map[i][j].getLand() == LandTypes.WATER) {
                    g2.setColor(GameConstants.WATER_COLOR);
                } else if (map[i][j].getLand() == LandTypes.GRASS) {
                    g2.setColor(GameConstants.GRASS_COLOR);
                } else if (map[i][j].getLand() == LandTypes.DIRT) {
                    g2.setColor(GameConstants.DIRT_COLOR);
                } else if (map[i][j].getLand() == LandTypes.ROAD) {
                    g2.setColor(GameConstants.ROAD_COLOR);
                } else if (map[i][j].getLand() == LandTypes.ENTRANCE) {
                    g2.setColor(new Color(0, 150, 0));
                } else if (map[i][j].getLand() == LandTypes.EXIT) {
                    g2.setColor(new Color(150, 0, 0));
                } else {
                    g2.setColor(Color.black);
                }
                g2.fillRect(
                    GameConstants.MINIMAP_X + j * GameConstants.MINIMAP_TILE_SIZE_INT,
                    GameConstants.MINIMAP_Y + i * GameConstants.MINIMAP_TILE_SIZE_INT,
                    GameConstants.MINIMAP_TILE_SIZE_INT,
                    GameConstants.MINIMAP_TILE_SIZE_INT
                );
            }
        }

        // Draw camera outline on minimap
        g2.setColor(Color.white);
        g2.drawRect(
            GameConstants.MINIMAP_X + (int) (((double) (cam.getX() - (GameConstants.CAMERA_WIDTH / 2)) / GameConstants.MAP_WIDTH) * GameConstants.MINIMAP_WIDTH_ACTUAL),
            GameConstants.MINIMAP_Y + (int) (((double) (cam.getY() - (GameConstants.CAMERA_HEIGHT / 2)) / GameConstants.MAP_HEIGHT) * GameConstants.MINIMAP_HEIGHT_ACTUAL),
            GameConstants.MINIMAP_CAMERA_OUTLINE_WIDTH,
            GameConstants.MINIMAP_CAMERA_OUTLINE_HEIGHT
        );
    }
    private void renderLiving(Graphics2D g2){
        List<Object> objects = tileManager.getLivingManager().getObjects();
        for (Object obj : objects) {
            if (obj instanceof Plant) {
                renderPlant(g2,(Plant) obj);
            }
            else if (obj instanceof Carnivore) {
                renderCarnivore(g2,(Carnivore) obj);
            }
            else if (obj instanceof Herbivore) {
                renderHerbivore(g2,(Herbivore) obj);
            }
        }
    }
    public void renderPlant(Graphics2D g2, Plant plant){
        int screenX = plant.getPosX() - cam.getX() + (GameConstants.CAMERA_WIDTH/2);
        int screenY = plant.getPosY() - cam.getY() + (GameConstants.CAMERA_HEIGHT/2);

        int size = GameConstants.TILE_SIZE / 2;

        if (plant.getPlantType().equals(PlantType.PalmTree)) {
            int pix = GameConstants.TILE_SIZE / 5;

            g2.setColor(new Color(139,69,19));
            g2.fillRect(screenX + pix * 2, screenY + pix, pix, pix);
            g2.fillRect(screenX + pix * 2, screenY + pix * 2, pix, pix);
            g2.fillRect(screenX + pix * 2, screenY + pix * 3, pix, pix);
            g2.fillRect(screenX + pix * 2, screenY + pix * 4, pix, pix);


            g2.setColor(new Color(0, 100, 0));
            g2.fillRect(screenX + pix * 2, screenY, pix, pix);
            g2.fillRect(screenX + pix, screenY, pix, pix);
            g2.fillRect(screenX + pix * 3, screenY, pix, pix);
            g2.fillRect(screenX, screenY + pix, pix, pix);
            g2.fillRect(screenX + pix * 4, screenY + pix, pix, pix);
        }

        else if (plant.getPlantType().equals(PlantType.BaobabTree)) {
            int pix = GameConstants.TILE_SIZE / 5;

            g2.setColor(new Color(139,69,19));
            g2.fillRect(screenX + pix * 2, screenY + pix * 4, pix * 3, pix * 3);

            g2.setColor(new Color(0, 100, 0));
            g2.fillRect(screenX + pix, screenY + pix, pix * 5, pix * 4);
            g2.fillRect(screenX + pix * 2, screenY, pix * 3, pix);
            g2.fillRect(screenX, screenY  + pix * 2, pix, pix * 2);
            g2.fillRect(screenX + pix * 6, screenY + pix * 2, pix, pix * 2);
        }

        else if (plant.getPlantType().equals(PlantType.Bush)) {
            int pix = GameConstants.TILE_SIZE / 5;

            g2.setColor(new Color(0, 100, 0));
            g2.fillRect(screenX, screenY, pix * 5, pix * 4);

            g2.setColor(new Color(30, 120, 30));
            g2.fillRect(screenX + pix * 2, screenY + pix * 2, pix, pix);
            g2.fillRect(screenX + pix, screenY + pix, pix, pix);
            g2.fillRect(screenX + pix, screenY + pix * 3, pix, pix);
            g2.fillRect(screenX + pix * 3, screenY + pix * 3, pix, pix);
            g2.fillRect(screenX, screenY + pix * 3, pix, pix);

            g2.setColor(new Color(139,69,19));
            g2.fillRect(screenX + pix * 2, screenY + pix * 4, pix, pix);
        }
    }
    public void renderCarnivore(Graphics2D g2, Carnivore carnivore){
        int screenX = carnivore.getPosX() - cam.getX() + (GameConstants.CAMERA_WIDTH/2);
        int screenY = carnivore.getPosY() - cam.getY() + (GameConstants.CAMERA_HEIGHT/2);

        int size = GameConstants.TILE_SIZE / 2;
        g2.setColor(new Color(100, 0, 0));
        g2.fillRect(screenX, screenY, size, size);
    }
    public void renderHerbivore(Graphics2D g2, Herbivore herbivore){
        int screenX = herbivore.getPosX() - cam.getX() + (GameConstants.CAMERA_WIDTH/2);
        int screenY = herbivore.getPosY() - cam.getY() + (GameConstants.CAMERA_HEIGHT/2);

        int size = GameConstants.TILE_SIZE / 2;
        g2.setColor(new Color(0, 0, 100));
        g2.fillRect(screenX, screenY, size, size);
    }

    public void renderJeep(Graphics2D g2, Jeep jeep) {
        int screenX = (int)jeep.getPixelX() - cam.getX() + (GameConstants.CAMERA_WIDTH/2);
        int screenY = (int)jeep.getPixelY() - cam.getY() + (GameConstants.CAMERA_HEIGHT/2);

        // Calculate size and offset for centering the sprite
        int jeepSpriteSize = GameConstants.TILE_SIZE / 2; 
        int spriteOffsetX = jeepSpriteSize / 2;
        int spriteOffsetY = jeepSpriteSize / 2;

        // Adjust screen coordinates to draw from top-left of the sprite
        int drawX = screenX - spriteOffsetX;
        int drawY = screenY - spriteOffsetY;


        // Only render if visible on screen (check based on drawX/Y and sprite size)
        if (drawX + jeepSpriteSize >= 0 && drawX <= GameConstants.CAMERA_WIDTH &&
                drawY + jeepSpriteSize >= 0 && drawY <= GameConstants.CAMERA_HEIGHT) {

            // Draw jeep body - color based on state
            Color jeepColor;
            if (jeep.getState() == JeepState.IDLE) {
                jeepColor = GameConstants.JEEP_COLOR;
            } else if (jeep.getState() == JeepState.TOURING) {
                jeepColor = new Color(0, 150, 0); // Green for touring
            } else { // RETURNING
                jeepColor = new Color(150, 0, 0); // Red for returning
            }
            g2.setColor(jeepColor);
            g2.fillRect(drawX, drawY, jeepSpriteSize, jeepSpriteSize); // Use calculated draw coords

            // Draw direction indicator (adjust offsets based on drawX/Y)
            g2.setColor(Color.YELLOW);
            int indicatorSize = 4; 
            int indicatorOffset = jeepSpriteSize / 4; 
            switch (jeep.getDirection()) {
                case UP:
                    g2.fillRect(drawX + indicatorOffset - indicatorSize/2, drawY, indicatorSize, indicatorSize);
                    break;
                case DOWN:
                    g2.fillRect(drawX + indicatorOffset - indicatorSize/2, drawY + jeepSpriteSize - indicatorSize, indicatorSize, indicatorSize);
                    break;
                case LEFT:
                    g2.fillRect(drawX, drawY + indicatorOffset - indicatorSize/2, indicatorSize, indicatorSize);
                    break;
                case RIGHT:
                    g2.fillRect(drawX + jeepSpriteSize - indicatorSize, drawY + indicatorOffset - indicatorSize/2, indicatorSize, indicatorSize);
                    break;
            }

            // Draw windows (adjust offsets)
            g2.setColor(Color.LIGHT_GRAY);
            int windowSize = jeepSpriteSize / 3; // Relative to jeep sprite
            g2.fillRect(drawX + jeepSpriteSize / 6, drawY + jeepSpriteSize / 6, windowSize, windowSize); // Placement

            // Draw wheels (adjust offsets)
            g2.setColor(Color.BLACK);
            int wheelSize = jeepSpriteSize / 4; 
            g2.fillOval(drawX, drawY + jeepSpriteSize - wheelSize, wheelSize, wheelSize); // Bottom-left wheel
            g2.fillOval(drawX + jeepSpriteSize - wheelSize, drawY + jeepSpriteSize - wheelSize, wheelSize, wheelSize); // Bottom-right wheel
        }
    }

    public void renderJeepOnMinimap(Graphics2D g2, Jeep jeep) {
        int tileX = jeep.getTileX();
        int tileY = jeep.getTileY();

        // Calculate minimap pixel position based on tile coordinates
        int minimapPixelX = GameConstants.MINIMAP_X + tileX * GameConstants.MINIMAP_TILE_SIZE_INT;
        int minimapPixelY = GameConstants.MINIMAP_Y + tileY * GameConstants.MINIMAP_TILE_SIZE_INT;

        // Draw a small dot/square
        g2.setColor(Color.RED); 
        int dotSize = Math.max(1, GameConstants.MINIMAP_TILE_SIZE_INT / 2); 
        g2.fillRect(minimapPixelX, minimapPixelY, dotSize, dotSize);
    }

    public void setPlacementMode(String itemName) {
        placementMode = itemName;
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    public Shop getShop(){
        return shop;
    }

    public TimeManager getTimeManager() {
        return timeManager;
    }

    private void renderStatistics(Graphics2D g2) {
        int statsPanelX = 10;
        int statsPanelY = 10;
        int statsPanelWidth = 250;
        int statsPanelHeight = 100;
        
        //Transparent background
        g2.setColor(new Color(0, 0, 0, 160));
        g2.fillRoundRect(statsPanelX, statsPanelY, statsPanelWidth, statsPanelHeight, 10, 10);
        g2.setColor(Color.WHITE);
        g2.drawRoundRect(statsPanelX, statsPanelY, statsPanelWidth, statsPanelHeight, 10, 10);
        
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Park Satisfaction
        double parkSatisfaction = touristManager.calculateParkSatisfaction();
        
        // Satisfction levels (red->yelloe->green scle)
        Color satisfactionColor;
        if (parkSatisfaction < 40) {
            satisfactionColor = new Color(255, 50, 50); // Piros
        } else if (parkSatisfaction < 70) {
            satisfactionColor = new Color(255, 255, 50); // Sárga
        } else {
            satisfactionColor = new Color(50, 255, 50); // Zöld
        }
        
        g2.drawString("Park Satisfaction:", statsPanelX + 10, statsPanelY + 25);
        g2.setColor(satisfactionColor);
        g2.drawString(String.format("%.1f%%", parkSatisfaction), statsPanelX + 170, statsPanelY + 25);
        
        // Tourists count
        g2.setColor(Color.WHITE);
        g2.drawString("Tourists:", statsPanelX + 10, statsPanelY + 50);
        g2.drawString(String.valueOf(touristManager.getTotalTouristCount()), statsPanelX + 170, statsPanelY + 50);
        
        g2.drawString("Tourists on Tour:", statsPanelX + 10, statsPanelY + 75);
        g2.drawString(String.valueOf(touristManager.getTouristsOnSafariCount()), statsPanelX + 170, statsPanelY + 75);
    }

    public void togglePause(String state) {
        timeManager.togglePause(state);
    }
}
