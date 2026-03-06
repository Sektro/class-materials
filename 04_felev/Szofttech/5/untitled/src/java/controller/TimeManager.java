package controller;

import model.time.TimeListener;

import java.util.ArrayList;

public class TimeManager {
  // Time constants
  public static final int HOURS_PER_DAY = 24;
  public static final int DAYS_PER_MONTH = 30;
  public static final int MONTHS_PER_YEAR = 12;

  // Speed constants
  public static final int SPEED_PAUSED = 0; // Paused
  public static final int SPEED_HOUR = 1; // 1 min = 1 hour
  public static final int SPEED_DAY = 2; // 1 min = 1 day
  public static final int SPEED_WEEK = 3; // 1 min = 1 week 

  // Current time values
  private int hour = 6;     // Start at 6 AM
  private int day = 1;
  private int month = 1;
  private int year = 1;
  private int currentSpeed = SPEED_HOUR;
  private boolean paused = false;
    
  // Time tracking
  private long lastUpdateTime = 0;
  private double accumulatedTime = 0;
    
  // Listeners for time changes
  private ArrayList<TimeListener> listeners = new ArrayList<>();

  public TimeManager() {
    lastUpdateTime = System.currentTimeMillis();
  }

  // Update game time based on real-time elapsed and current speed setting
  public void update(){
    if(paused) return;

    long currentTime = System.currentTimeMillis();
    long elapsedTime = currentTime - lastUpdateTime;
    lastUpdateTime = currentTime;

    accumulatedTime += elapsedTime / 6000.0; // Convert to minutes

    int timeIncrement = 0;

    switch (currentSpeed) {
      case SPEED_HOUR:
        timeIncrement = (int) accumulatedTime;
        if(timeIncrement > 0) {
          accumulatedTime -= timeIncrement;
          advanceTime(timeIncrement);
        }
        break;
        
      case SPEED_DAY:
        timeIncrement = (int) accumulatedTime;
        if(timeIncrement > 0) {
          accumulatedTime -= timeIncrement;
          advanceTime(timeIncrement * HOURS_PER_DAY);
        }
        break;
        
      case SPEED_WEEK:
        timeIncrement = (int) accumulatedTime;
        if(timeIncrement > 0) {
          accumulatedTime -= timeIncrement;
          advanceTime(timeIncrement * HOURS_PER_DAY * 7); 
        }
        break;
    }
  }

  // Advances game time
  private void advanceTime(int hours){
    boolean hourChanged = false;
    boolean dayChanged = false;
    boolean monthChanged = false;
    boolean yearChanged = false;

    int initialHour = hour;
    int initialDay = day;
    int initialMonth = month;
    int initialYear = year;

    hour += hours;

    while(hour >= HOURS_PER_DAY) {
      hour -= HOURS_PER_DAY;
      day++;
      dayChanged = true;

      if(day > DAYS_PER_MONTH) {
        day = 1;
        month++;
        monthChanged = true;

        if(month > MONTHS_PER_YEAR) {
          month = 1;
          year++;
          yearChanged = true;
        }
      }
    }

    if(hour != initialHour) {
      hourChanged = true;
    } 
    if(day != initialDay) {
      dayChanged = true;
    }
    if(month != initialMonth) {
      monthChanged = true;
    }
    if(year != initialYear) {
      yearChanged = true;
    }

    notifyListeners(hourChanged, dayChanged, monthChanged, yearChanged);
  }

  // Notifies all registered listeners of time changes
  private void notifyListeners(boolean hourChanged, boolean dayChanged, boolean monthChanged, boolean yearChanged){
    for(TimeListener listener : listeners) {
      if(hourChanged) {
        listener.onHourChanged(hour);
      }
      if(dayChanged) {
        listener.onDayChanged(day);
      }
      if(monthChanged) {
        listener.onMonthChanged(month);
      }
      if(yearChanged) {
        listener.onYearChanged(year);
      }
    }
  }

  public void setSpeed(int speed){
    if(speed >= SPEED_PAUSED && speed <= SPEED_WEEK) {
      currentSpeed = speed;
      paused = (speed == SPEED_PAUSED);

      //Reset accumulated time when speed changes
      accumulatedTime = 0;
      lastUpdateTime = System.currentTimeMillis();
    }
  }

  public void togglePause(String state){
    if(state == "PAUSE"){
      paused = true;
    } else if(state == "RUNNING"){
      paused = false;
    } else if(state == "TOGGLE"){
      paused = !paused; 
    } else{
      return;
    }
    lastUpdateTime = System.currentTimeMillis();
  }

  public void addListener(TimeListener listener){
    if(!listeners.contains(listener)) {
      listeners.add(listener);
    }
  }

  public void removeListener(TimeListener listener){
    listeners.remove(listener);
  }

  public int getHour() { return hour; }
  public int getDay() { return day; }
  public int getMonth() { return month; }
  public int getYear() { return year; }
  public int getCurrentSpeed() { return currentSpeed; }
  public boolean isPaused() { return paused; }

  public String getFormattedTime(){
    String formattedHour = String.format("%02d", hour);
    String formattedDay = String.format("%02d", day);
    String formattedMonth = String.format("%02d", month);
    String formattedYear = String.format("%04d", year);

    return formattedHour + ":" + formattedDay + ":" + formattedMonth + ":" + formattedYear;
  }

  public String getFormattedDate(){
    String formattedDay = String.format("%02d", day);
    String formattedMonth = String.format("%02d", month);
    String formattedYear = String.format("%04d", year);

    return formattedDay + "/" + formattedMonth + "/" + formattedYear;
  }

  public boolean isDayTime(){
    return hour >= 6 && hour < 18; // Daytime is between 6 AM and 6 PM
  }
}
