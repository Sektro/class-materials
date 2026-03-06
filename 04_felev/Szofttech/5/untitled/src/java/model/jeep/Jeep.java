package model.jeep;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.GameConstants;
import controller.LivingManager;
import model.animal.Animal;
import model.road.*;
import model.tourist.*;

public class Jeep{
    private static final int SIZE = GameConstants.TILE_SIZE / 2;
    private static final int SPEED = GameConstants.JEEP_SPEED;
    private static final int MAX_PASSENGERS = 4; 

    private double x;
    private double y;
    private boolean isMoving;
    private RoadSegment currentRoadSegment;
    private RoadSegment targetRoadSegment;
    private RoadConnection currentConnection;
    private Set<RoadSegment> roadPath;
    private JeepState state;
    private ArrayList<Tourist> passengers;
    private Direction direction;
    private Point targetPoint;
    private Point targetTilePoint;

    public Jeep(int startingX, int startingY) {
        this.x = startingX * GameConstants.TILE_SIZE +  (GameConstants.TILE_SIZE / 2);
        this.y = startingY * GameConstants.TILE_SIZE +  (GameConstants.TILE_SIZE / 2);
        this.isMoving = false;
        this.roadPath = new HashSet<>();
        this.state = JeepState.IDLE;
        this.passengers = new ArrayList<>();
        this.direction = Direction.DOWN; 
        this.targetTilePoint = new Point(startingX, startingY);
    }

    public void update(RoadNetwork roadNetwork) {
        if(state == JeepState.IDLE){
            isMoving = false;
            return;
        }

        if(targetTilePoint != null && isMoving){
            //Check if we reached the target point(pixel check)
            if(isAtTargetPixelPoint()){
                isMoving = false;

                this.x = targetTilePoint.x * GameConstants.TILE_SIZE + (GameConstants.TILE_SIZE / 2.0);
                this.y = targetTilePoint.y * GameConstants.TILE_SIZE + (GameConstants.TILE_SIZE / 2.0);

                currentRoadSegment = targetRoadSegment;
                targetRoadSegment = null;

                    /*// Track road segments for tourists
                    if (state == JeepState.TOURING && hasPassangers()) {
                        for (Tourist tourist : passengers) {
                            tourist.visitRoadSegment(currentRoadSegment);
                        }
                    }*/

                if(state == JeepState.TOURING){
                    findNextDestination(roadNetwork);
                } else if(state == JeepState.RETURNING){
                    // Check if we reached the entrance point
                    if(currentRoadSegment == roadNetwork.getEntrance()){
                        state = JeepState.IDLE;
                        return;
                    } else{
                        // Find the next destination on the way back
                        findNextDestination(roadNetwork);
                    }
                }
            } else{
                // Move towards the target point then if we didn't reach it yet
                moveTowardsTargetPixelPoint(roadNetwork);
            }
        } else if(targetTilePoint == null && state == JeepState.TOURING){
            isMoving = false;
            //Jeep could be stuck for whatever reason(maybe further implementation)
            if(state == JeepState.TOURING){
                findNextDestination(roadNetwork);
            }
        } else{
            //If not moving and has a target (e.g. just departed), then start moving
            if(targetTilePoint != null && !isMoving && state != JeepState.IDLE){
                if(!isAtTargetPixelPoint()){
                    isMoving = true;
                } else{
                    if(state == JeepState.TOURING){
                        findNextDestination(roadNetwork);
                    }
                }
            } else {
                isMoving = false;
            }
        }
        
    }

    private void moveTowardsTargetPixelPoint(RoadNetwork roadNetwork) {
        if(targetTilePoint == null) return;

        // Calculate target PIXEL coordinates (center of the target tile)
        double targetPixelX = targetTilePoint.x * GameConstants.TILE_SIZE + (GameConstants.TILE_SIZE / 2.0);
        double targetPixelY = targetTilePoint.y * GameConstants.TILE_SIZE + (GameConstants.TILE_SIZE / 2.0);

        // Calculate vector towards target pixel
        double dx = targetPixelX - this.x;
        double dy = targetPixelY - this.y;

        // Calculate distance
        double distance = Math.sqrt(dx * dx + dy * dy);

    
        if (distance > 0) { // Avoid division by zero
            // Normalize vector
            double normX = dx / distance;
            double normY = dy / distance;

            // Move by SPEED pixels in the normalized direction
            this.x += normX * SPEED;
            this.y += normY * SPEED;

            // Update direction based on movement vector
            if (Math.abs(normX) > Math.abs(normY)) {
                direction = (normX > 0) ? Direction.RIGHT : Direction.LEFT;
            } else {
                direction = (normY > 0) ? Direction.DOWN : Direction.UP;
            }
        }
    }

    private boolean isAtTargetPixelPoint() {
        if(targetTilePoint == null) return false;

        // Calculate target PIXEL coordinates (center of the target tile)
        double targetPixelX = targetTilePoint.x * GameConstants.TILE_SIZE + (GameConstants.TILE_SIZE / 2.0);
        double targetPixelY = targetTilePoint.y * GameConstants.TILE_SIZE + (GameConstants.TILE_SIZE / 2.0);

        // Check if the distance to the target pixel is less than the speed (or a small threshold)
        double dx = targetPixelX - this.x;
        double dy = targetPixelY - this.y;
        double distanceSquared = dx * dx + dy * dy;
        double threshold = SPEED;
        double thresholdSquared = threshold * threshold;

        return distanceSquared < thresholdSquared;
    }

    private void findNextDestination(RoadNetwork roadNetwork){
        if(currentRoadSegment == null || currentRoadSegment.getConnections().isEmpty()){
            return;
        }

        ArrayList<RoadConnection> connections = new ArrayList<>(currentRoadSegment.getConnections());
        
        RoadConnection nextConnection = connections.get((int)(Math.random() * connections.size()));

        if(nextConnection != null){
            setTargetRoadSegment(nextConnection.getTo());
            isMoving = true;
        } else{
            isMoving = false;
            targetTilePoint = null;
        }
    }

    public boolean addPassenger(Tourist tourist) {
        if (passengers.size() < MAX_PASSENGERS) {
            passengers.add(tourist);
            tourist.setState(TouristState.ON_SAFARI);
            tourist.setAssignedJeep(this);
            return true;
        }
        return false; // Jeep is full
    }

    public void disembarkPassengers(int totalJeeps){
        for (Tourist tourist : passengers) {
            tourist.setState(TouristState.EXITING);
            tourist.calculateSafariSatisfaction(totalJeeps /*, totalRoadSegments */);
            tourist.setAssignedJeep(null);
        }
        passengers.clear();
    }

    public void checkForAnimalsNearby(LivingManager livingManager) {
        if (!hasPassengers() || state != JeepState.TOURING) {
            return; 
        }
        
        int tileX = getTileX();
        int tileY = getTileY();
        int detectionRadius = 15;
        
        List<Object> nearbyObjects = livingManager.getObjectsNearby(tileX, tileY, detectionRadius);
        
        for (Object obj : nearbyObjects) {
            if (obj instanceof Animal) {
                //Only one animal sighting for now
                for (Tourist tourist : passengers) {
                    tourist.spotAnimal();
                }
                break;
            }
        }
    }

    public boolean hasPassengers(){
        return !passengers.isEmpty();  
    }

    public boolean isFull(){
        return passengers.size() >= MAX_PASSENGERS;
    }

    public int getPassengerCount(){
        return passengers.size();
    }

    public ArrayList<Tourist> getPassengers(){
        return passengers;
    }

    public int getMaxPassengers(){
        return MAX_PASSENGERS;
    }   

    public double getPixelX() {
        return x;
    }
    
    public double getPixelY() {
        return y;
    }

    public int getTileX(){
        return (int)(x / GameConstants.TILE_SIZE);
    }

    public int getTileY(){
        return (int)(y / GameConstants.TILE_SIZE);
    }

    public JeepState getState() {
        return state;
    }

    public Direction getDirection() {
        return direction;
    }

    public RoadSegment getCurrentRoadSegment() {
        return currentRoadSegment;
    }

    public RoadSegment getTargetRoadSegment() {
        return targetRoadSegment;
    }

    public Point getTargetTilePoint() {
        return targetTilePoint;
    }

    public void setPixelX(double x) {
        this.x = x;
    }

    public void setPixelY(double y) {
        this.y = y;
    }

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public void setTargetRoadSegment(RoadSegment targetRoadSegment) {
        if(targetRoadSegment == null){
            this.targetRoadSegment = null;
            this.targetTilePoint = null;
            return;
        }
        this.targetRoadSegment = targetRoadSegment;

        if(targetRoadSegment.getRoad() != null && targetRoadSegment.getRoad().length > 0){
            Point targetTile = targetRoadSegment.getRoad()[0];
            this.targetTilePoint = targetTile;
        } else {
            this.targetTilePoint = null;
        }

    }

    public void setTargetTilePoint(Point targetTilePoint) {
        this.targetTilePoint = targetTilePoint;
    }

    public void setCurrentRoadSegment(RoadSegment roadSegment) {
        this.currentRoadSegment = roadSegment;
    }

    public void setState(JeepState state) {
        this.state = state;
    }
}