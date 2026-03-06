package controller;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import model.GameConstants;
import model.jeep.Jeep;
import model.jeep.JeepState;
import model.road.RoadConnection;
import model.road.RoadNetwork;
import model.road.RoadSegment;
import model.tourist.Tourist;
import model.tourist.TouristState;

public class JeepManager{
    private ArrayList<Jeep> jeeps;
    private RoadNetwork roadNetwork;
    private Map<Jeep, ArrayList<Tourist>> jeepAssignments;
    private TouristManager touristManager;
    private LivingManager livingManager;
    private Point entrancePoint;
    private long lastDepartureTime = 0;
    private static final long DEPARTURE_INTERVAL = 5000; // 5 seconds

    public JeepManager(RoadNetwork roadNetwork, TouristManager touristManager, LivingManager livingManager) {
        this.jeeps = new ArrayList<>();
        this.roadNetwork = roadNetwork;
        this.jeepAssignments = new HashMap<>();
        this.entrancePoint = roadNetwork.getEntrance().getRoad()[1];
        this.touristManager = touristManager;
        this.livingManager = livingManager;
    }

    public void addJeep() {
        Jeep jeep = new Jeep((int)entrancePoint.getX(), (int)entrancePoint.getY());
        jeep.setCurrentRoadSegment(roadNetwork.getEntrance());
        jeeps.add(jeep);
        jeepAssignments.put(jeep, new ArrayList<>());
    }

    public void updateJeeps(){
        long currentTime = System.currentTimeMillis();

        for(Jeep jeep : jeeps){
            if(jeep.getState() == JeepState.IDLE && !jeep.isFull()){
                assignTouristsToJeep(jeep);
            }
        }

        if(currentTime - lastDepartureTime > DEPARTURE_INTERVAL){
            for (Jeep jeep : jeeps) {
                if (jeep.getState() == JeepState.IDLE && jeep.hasPassengers()) {
                    jeep.setState(JeepState.TOURING);

                    boolean success = setNextDestination(jeep);

                    if(success) {
                        lastDepartureTime = currentTime;
                        break;
                    } else {
                        returnPassengersToWaiting(jeep);
                        jeep.setState(JeepState.IDLE);
                    }

                }
            }
        }

        for (Jeep jeep: jeeps){
            if(jeep.getState() != JeepState.IDLE){
                jeep.update(roadNetwork);

                jeep.checkForAnimalsNearby(livingManager);

                if(jeep.getCurrentRoadSegment() == roadNetwork.getExit() && jeep.getState() == JeepState.TOURING && jeep.hasPassengers()){

                    int totalJeeps = jeeps.size();
                    //int totalRoadSegments = roadNetwork.getRoadSegments().size();

                    jeep.disembarkPassengers(totalJeeps /*, totalRoadSegments */);
                    jeep.setState(JeepState.RETURNING);
                    setReturnPath(jeep);
                }

                if(jeep.getCurrentRoadSegment() == roadNetwork.getEntrance() && jeep.getState() == JeepState.RETURNING){
                    jeep.setState(JeepState.IDLE);
                    resetJeepPosition(jeep);
                }
            }
        }
    }

    private void assignTouristsToJeep(Jeep jeep){
        ArrayList<Tourist> waitingTourists = new ArrayList<>(touristManager.getWaitingTourists());

        if(waitingTourists.isEmpty()){
            return;
        }

        int availableSeats = jeep.getMaxPassengers() - jeep.getPassengerCount();
        int assignedCount = 0;

        for (Tourist tourist : waitingTourists){
            if(assignedCount >= availableSeats) break;

            if(tourist.getState() == TouristState.WAITING){
                if(jeep.addPassenger(tourist)){
                    assignedCount++;
                }
            }
        }
    }

    private void returnPassengersToWaiting(Jeep jeep){
        for (Tourist tourist : jeep.getPassengers()) {
            tourist.setState(TouristState.WAITING);
            tourist.setAssignedJeep(null);
        }
        jeep.getPassengers().clear();
    }

    private boolean setNextDestination(Jeep jeep){
        RoadSegment currentRoadSegment = jeep.getCurrentRoadSegment();

        if(currentRoadSegment != null && !currentRoadSegment.getConnections().isEmpty()){
            ArrayList<RoadConnection> connections = new ArrayList<>(currentRoadSegment.getConnections());

            RoadConnection nextConnection = connections.get((int)new Random().nextInt(connections.size()));

            if(nextConnection != null && nextConnection.getTo() != null){
                jeep.setTargetRoadSegment(nextConnection.getTo());
                return jeep.getTargetTilePoint() != null;
            }
        }

        return false;
    }

    private void setReturnPath(Jeep jeep){
        RoadSegment entranceSegment = roadNetwork.getEntrance();

        if (entranceSegment != null) {
            jeep.setTargetRoadSegment(entranceSegment);
        } else {
            jeep.setTargetRoadSegment(null); 
        }
    }

    private void resetJeepPosition(Jeep jeep){
        RoadSegment entranceSegment = roadNetwork.getEntrance();

        if (entranceSegment != null && entranceSegment.getRoad() != null && entranceSegment.getRoad().length > 0) {
            Point entranceTile = entranceSegment.getRoad()[0]; 

            double pixelX = entranceTile.x * GameConstants.TILE_SIZE + (GameConstants.TILE_SIZE / 2.0);
            double pixelY = entranceTile.y * GameConstants.TILE_SIZE + (GameConstants.TILE_SIZE / 2.0);

            jeep.setPixelX(pixelX); 
            jeep.setPixelY(pixelY); 

            jeep.setTargetTilePoint(entranceTile); 
        } 
    }

    public Point getEntrancePoint() {
        return entrancePoint;
    }

    public ArrayList<Jeep> getJeeps() {
        return jeeps;
    }
}