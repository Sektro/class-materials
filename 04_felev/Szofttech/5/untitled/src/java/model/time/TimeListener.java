package model.time;

//Listening for game time changes
public interface TimeListener {
  void onHourChanged(int newHour); //hour change
  void onDayChanged(int newDay); //day change
  void onMonthChanged(int newMonth); //month change
  void onYearChanged(int newYear); //year change
}
