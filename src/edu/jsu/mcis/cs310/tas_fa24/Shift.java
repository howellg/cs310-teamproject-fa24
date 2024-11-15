package edu.jsu.mcis.cs310.tas_fa24;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

public class Shift {

    private final int id;
    private final String description;
    private final DailySchedule defaultSchedule;
    private HashMap<DayOfWeek, DailySchedule> dailySchedules;

    public Shift(int id, String description, DailySchedule defaultSchedule) {
        this.id = id;
        this.description = description;
        this.defaultSchedule = defaultSchedule;

        this.dailySchedules = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY) {
                dailySchedules.put(day, defaultSchedule);
            }
        }
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public DailySchedule getDefaultSchedule() {
        return defaultSchedule;
    }

    public LocalTime getShiftStart() {
        return defaultSchedule.getShiftStart();
    }

    public LocalTime getShiftStop() {
        return defaultSchedule.getShiftStop();
    }

    public int getRoundInterval() {
        return defaultSchedule.getRoundInterval();
    }

    public int getGracePeriod() {
        return defaultSchedule.getGracePeriod();
    }

    public int getDockPenalty() {
        return defaultSchedule.getDockPenalty();
    }

    public LocalTime getLunchStart() {
        return defaultSchedule.getLunchStart();
    }

    public LocalTime getLunchStop() {
        return defaultSchedule.getLunchStop();
    }

    public int getLunchThreshold() {
        return defaultSchedule.getLunchThreshold();
    }

    public int getShiftDuration() {
        return defaultSchedule.getShiftDuration();
    }

    public int getLunchDuration() {
        return defaultSchedule.getLunchDuration();
    }
    
    public DailySchedule getDailySchedule(DayOfWeek day) {
        return dailySchedules.getOrDefault(day, defaultSchedule);
    }

    public void setDailySchedule(DayOfWeek day, DailySchedule schedule) {
        dailySchedules.put(day, schedule);
    }
    public Map<DayOfWeek, DailySchedule> getDailySchedules() {
        return dailySchedules;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(description).append(": ");
        s.append(defaultSchedule.getShiftStart().toString()).append(" - ")
         .append(defaultSchedule.getShiftStop().toString()).append(" ");
        s.append("(").append(defaultSchedule.getShiftDuration()).append(" minutes)").append("; ");
        s.append("Lunch: ").append(defaultSchedule.getLunchStart().toString()).append(" - ")
         .append(defaultSchedule.getLunchStop().toString()).append(" ");
        s.append("(").append(defaultSchedule.getLunchDuration()).append(" minutes)");
        return s.toString();
    }
}
