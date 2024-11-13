package edu.jsu.mcis.cs310.tas_fa24;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class Shift {
    
    final private int id;
    final private String description;
    final private LocalTime shiftstart;
    final private LocalTime shiftstop;
    final private int roundinterval;
    final private int graceperiod;
    final private int dockpenalty;
    final private LocalTime lunchstart;
    final private LocalTime lunchstop;
    final private int lunchthreshold;
    final private int lunchduration;
    final private int shiftduration;

    public Shift(HashMap<String, String> shiftData) {
        this.id = Integer.parseInt(shiftData.get("id"));
        this.description = shiftData.get("description");
        this.shiftstart = LocalTime.parse(shiftData.get("shiftstart"));
        this.shiftstop = LocalTime.parse(shiftData.get("shiftstop"));
        this.lunchstart = LocalTime.parse(shiftData.get("lunchstart"));
        this.lunchstop = LocalTime.parse(shiftData.get("lunchstop"));
    
        this.lunchduration = (int) ChronoUnit.MINUTES.between(lunchstart, lunchstop);
        long duration = ChronoUnit.MINUTES.between(shiftstart, shiftstop);
        if (duration < 0) { /**If the stop time is on the next day*/
            duration += 1440; /**Adds 24 hours*/
        }
        this.shiftduration = (int) duration;
        
        this.roundinterval = Integer.parseInt(shiftData.get("roundinterval"));
        this.graceperiod = Integer.parseInt(shiftData.get("graceperiod"));
        this.dockpenalty = Integer.parseInt(shiftData.get("dockpenalty"));
        this.lunchthreshold = Integer.parseInt(shiftData.get("lunchthreshold"));
    }
    public int getId(){
        return id;
    }
    public String getDescription(){
        return description;
    }
    public LocalTime getShiftStart(){
        return shiftstart;
    }
    public LocalTime getShiftStop(){
        return shiftstop;
    }
    public int getRoundInterval() {
        return roundinterval;
    }
    public int getGracePeriod() {
        return graceperiod;
    }
    public int getDockPenalty() {
        return dockpenalty;
    }
    public LocalTime getLunchStart(){
        return lunchstart;
    }
    public LocalTime getLunchStop(){
        return lunchstop;
    }
    public int getLunchThreshold() {
        return lunchthreshold;
    }
    public int getLunchDuration() {
        return lunchduration;
    }
   
        @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(description).append(": ");
        s.append(shiftstart.toString()).append(" - ").append(shiftstop.toString()).append(" ");
        s.append("(").append(shiftduration).append(" minutes)").append("; ");
        s.append("Lunch: ").append(lunchstart.toString()).append(" - ").append(lunchstop.toString()).append(" ");
        s.append("(").append(lunchduration).append(" minutes)");
        return s.toString();
    }
}
