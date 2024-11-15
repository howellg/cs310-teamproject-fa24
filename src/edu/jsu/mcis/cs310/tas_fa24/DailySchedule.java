package edu.jsu.mcis.cs310.tas_fa24;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class DailySchedule {

    final private int id;
    final private LocalTime shiftStart;
    final private LocalTime shiftStop;
    final private LocalTime lunchStart;
    final private LocalTime lunchStop;
    final private int roundInterval;
    final private int gracePeriod;
    final private int dockPenalty;
    final private int lunchThreshold;
    private int shiftDuration;
    private int lunchDuration;

    public DailySchedule(int id, LocalTime shiftStart, LocalTime shiftStop, LocalTime lunchStart, LocalTime lunchStop, int roundInterval, int gracePeriod, int dockPenalty, int lunchThreshold) {

        this.id = id;
        this.shiftStart = shiftStart;
        this.shiftStop = shiftStop;
        this.lunchStart = lunchStart;
        this.lunchStop = lunchStop;
        this.roundInterval = roundInterval;
        this.gracePeriod = gracePeriod;
        this.dockPenalty = dockPenalty;
        this.lunchThreshold = lunchThreshold;

        this.shiftDuration = (int) ChronoUnit.MINUTES.between(shiftStart, shiftStop);
        if (this.shiftDuration < 0) {
            this.shiftDuration += 1440; //adjust for overnight shifts
        }

        this.lunchDuration = (int) ChronoUnit.MINUTES.between(lunchStart, lunchStop);
        if (this.lunchDuration < 0) {
            this.lunchDuration += 1440; //adjust for overnight lunches
        }
    }


    public int getId() {
        return id;
    }

    public LocalTime getShiftStart() {
        return shiftStart;
    }

    public LocalTime getShiftStop() {
        return shiftStop;
    }

    public LocalTime getLunchStart() {
        return lunchStart;
    }

    public LocalTime getLunchStop() {
        return lunchStop;
    }

    public int getRoundInterval() {
        return roundInterval;
    }

    public int getGracePeriod() {
        return gracePeriod;
    }

    public int getDockPenalty() {
        return dockPenalty;
    }

    public int getLunchThreshold() {
        return lunchThreshold;
    }

    public int getShiftDuration() {
        return shiftDuration;
    }

    public int getLunchDuration() {
        return lunchDuration;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Shift Start: ").append(shiftStart.toString()).append(", ");
        s.append("Shift Stop: ").append(shiftStop.toString()).append(", ");
        s.append("Lunch Start: ").append(lunchStart.toString()).append(", ");
        s.append("Lunch Stop: ").append(lunchStop.toString()).append(", ");
        s.append("Shift Duration: ").append(shiftDuration).append(" minutes, ");
        s.append("Lunch Duration: ").append(lunchDuration).append(" minutes");
        return s.toString();
    }
}
