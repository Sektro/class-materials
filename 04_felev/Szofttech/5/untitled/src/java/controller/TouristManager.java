package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import model.tourist.Tourist;
import model.tourist.TouristState;
import model.shop.Shop;

public class TouristManager {
    private List<Tourist> tourists;
    private Random random;
    private int spawnInterval;
    private int spawnCount;
    private Timer timer;
    private Shop shop;

    public TouristManager(Shop shop) {
        tourists = new ArrayList<>();
        random = new Random();
        spawnInterval = 60000;
        spawnCount = 4;
        this.shop = shop;     
        initialTourists();

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                spawnTourists();
            }
        }, 0, spawnInterval);
    }

    private void initialTourists(){
        tourists.clear();
        
        int initalToursitsCount = random.nextInt(0,15) + 1;
        for (int i = 0; i < initalToursitsCount; i++) {
            tourists.add(new Tourist());
        }

        shop.collectTicketRevenue(getTotalTouristCount());
    }

    private void spawnTourists(){
        for (int i = 0; i < spawnCount; i++){
            tourists.add(new Tourist());
        }

        shop.collectTicketRevenue(spawnCount);
    }
    
    public List<Tourist> getWaitingTourists() {
        List<Tourist> waitingTourists = new ArrayList<>();
        
        for (Tourist tourist : tourists) {
            if (tourist.getState() == TouristState.WAITING) {
                waitingTourists.add(tourist);
            }
        }
        
        return waitingTourists;
    }

    public double calculateParkSatisfaction() {
        int totalCompletedSafaris = 0;
        double totalSatisfaction = 0;
        
        // Only count tourists who completed a safari
        for (Tourist tourist : tourists) {
            if (tourist.hasSafariCompleted()) {
                totalSatisfaction += tourist.getSatisfaction();
                totalCompletedSafaris++;
            }
        }
        
        if (totalCompletedSafaris == 0) {
            return 0; // No safaris completed yet
        }
        
        return totalSatisfaction / totalCompletedSafaris;
    }

    public int getTouristsOnSafariCount() {
        int count = 0;
        for (Tourist tourist : tourists) {
            if (tourist.getState() == TouristState.ON_SAFARI) {
                count++;
            }
        }
        return count;
    }

    public List<Tourist> getTourists() {
        return tourists;
    }

    public int getTotalTouristCount() {
        return tourists.size();
    }
    
}