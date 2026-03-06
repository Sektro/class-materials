package model.tourist;

import java.awt.Color;
import java.awt.Graphics2D;
import view.Camera;
import model.GameConstants;
import model.jeep.Jeep;

public class Tourist {
    //These could be dynamic later on
    private static final int OPTIMAL_ANIMAL_SIGHTINGS = 10;
    private static final long OPTIMAL_SAFARI_DURATION_MS = 90000;

    private double satisfaction;
    private TouristState state;
    private Jeep assignedJeep;
    private int animalsSpotted;
    private long safariStartTime;
    private long safariEndTime;
    private boolean safariCompleted;
    private int uniqueRoadSegmentsTraveled;
    //private Set<RoadSegment> visitedRoadSegments = new HashSet<>();
    
    public Tourist() {
        this.satisfaction = 100.0;
        this.state = TouristState.WAITING;
        this.animalsSpotted = 0;
        this.safariStartTime = 0;
        this.safariEndTime = 0;
        this.safariCompleted = false;
        this.uniqueRoadSegmentsTraveled = 0;
        this.assignedJeep = null;
    }

    public void updateSatisfaction(double change){
        this.satisfaction += change;
        if (satisfaction < 0) {
            satisfaction = 0;
        } else if (satisfaction > 100) {
            satisfaction = 100;
        }
    }

    public void setAssignedJeep(Jeep jeep) {
        this.assignedJeep = jeep;
    }

    public void setState(TouristState state) {
        this.state = state;
        
        // Track safari start time
        if (state == TouristState.ON_SAFARI) {
            this.safariStartTime = System.currentTimeMillis();
        }
        
        // Track safari end time
        if (state == TouristState.EXITING && this.safariStartTime > 0) {
            this.safariEndTime = System.currentTimeMillis();
            this.safariCompleted = true;
        }
    }

    /*public void visitRoadSegment(RoadSegment segment) {
        if (visitedRoadSegments == null) {
            visitedRoadSegments = new HashSet<>();
        }
        
        if (segment != null && !visitedRoadSegments.contains(segment)) {
            visitedRoadSegments.add(segment);
            uniqueRoadSegmentsTraveled = visitedRoadSegments.size();
        }
    } */

    public double getSatisfaction() {
        return satisfaction;
    }

    public TouristState getState() {
        return state;
    }

    public Jeep getAssignedJeep() {
        return assignedJeep;
    }

    public void spotAnimal() {
        animalsSpotted++;
    }

    //Current Formula: Satisfaction = (Animal Density + Safari Quality) / 2
    public void calculateSafariSatisfaction(int totalJeeps /*, int totalRoadSegments*/) {
        if (!safariCompleted) {
            return; // Only calculate if the safari is complete
        }
        
        // Calculate Animal Density
        double animalDensity = Math.min(100, ((double)animalsSpotted / OPTIMAL_ANIMAL_SIGHTINGS) * 100);
        
        // Calculate Safari Quality components
        double jeepFactor = Math.min(100, (totalJeeps / 5.0) * 100); // Assuming 5 jeeps is optimal
        
        //double roadVariety = Math.min(100, ((double)uniqueRoadSegmentsTraveled / totalRoadSegments) * 100);
        
        // Calculate duration factor - optimal is 100%, too short or too long reduces score
        long safariDuration = safariEndTime - safariStartTime;
        double durationRatio = (double)safariDuration / OPTIMAL_SAFARI_DURATION_MS;
        double durationFactor;
        
        if (durationRatio < 0.5) {
            // Too short: 0.5 ratio = 50% satisfaction
            durationFactor = durationRatio * 100;
        } else if (durationRatio <= 1.5) {
            // Good range: 0.5-1.5 ratio = 80-100% satisfaction
            durationFactor = 80 + ((durationRatio - 0.5) / 1.0) * 20;
            durationFactor = Math.min(100, durationFactor);
        } else {
            // Too long: satisfaction falls off after 1.5x optimal time
            durationFactor = Math.max(20, 100 - ((durationRatio - 1.5) * 40));
        }
        
        // Calculate Safari Quality
        double safariQuality = (jeepFactor + /*roadVariety*/ + durationFactor) / 2.0;
        
        // Calculate Overall Satisfaction
        double calculatedSatisfaction = (animalDensity + safariQuality) / 2.0;
        
        // Update the tourist's satisfaction to this calculated value
        setFinalSatisfaction(calculatedSatisfaction);
    }

    public void setFinalSatisfaction(double value) {
        this.satisfaction = Math.max(0, Math.min(100, value));
    }

    public boolean hasSafariCompleted() {
        return safariCompleted;
    }
}