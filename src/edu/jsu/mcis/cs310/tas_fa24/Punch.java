package edu.jsu.mcis.cs310.tas_fa24;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Punch {
    private int id;  
    final private int terminalid;  
    final private Badge badge;  
    final private EventType punchType; 
    final private LocalDateTime originalTimestamp; 
    private LocalDateTime adjustedTimestamp;  
    private PunchAdjustmentType adjustmenttype;

    /**
     * New Punch 
     */
    public Punch(int terminalid, Badge badge, EventType punchType) {
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchType = punchType;
        this.originalTimestamp = LocalDateTime.now().withSecond(0).withNano(0);  
        this.adjustedTimestamp = null;  
    }
    
    /**
     * Existing punch
     */
    public Punch(int id, int terminalid, Badge badge, LocalDateTime originaltimestamp, EventType punchtype) {
        this.id = id;
        this.terminalid = terminalid;
        this.badge = badge;
        this.punchType = punchtype;
        this.originalTimestamp = originaltimestamp;
    }

    public int getId() {
        return this.id;
    }

    public int getTerminalId() {
        return this.terminalid;
    }

    public Badge getBadge() {
        return this.badge;
    }

    public EventType getPunchType() {
        return this.punchType;
    }
    public LocalDateTime getOriginalTimestamp(){
        return this.originalTimestamp;
    }
    
    public LocalDateTime getAdjustedTimestamp() {
        return this.adjustedTimestamp;
    }

    public PunchAdjustmentType getAdjustmentType() {
        return this.adjustmenttype;
    }

    public String printOriginal() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
        String formattedDate = originalTimestamp.format(dateFormatter);
        /**
         * All this is doing is casting the Day to uppercase. It defaults to Ull instead of UUU
         */
        return String.format("#%s %s: %s", badge.getId(), punchType, formattedDate.substring(0, 3).toUpperCase() + formattedDate.substring(3));
}

    public String printAdjusted() {
        if (adjustedTimestamp == null) {
            return "Adjusted timestamp not available.";
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss", Locale.ENGLISH);
        String formattedDate = adjustedTimestamp.format(dateFormatter);
        return String.format("#%s %s: %s (%s)", badge.getId(), punchType, formattedDate.substring(0, 3).toUpperCase() + formattedDate.substring(3), adjustmenttype);
    }
    
    public void adjust(Shift s){
        DailySchedule d = s.getDailySchedule(this.originalTimestamp.getDayOfWeek());
        LocalDateTime adjust = null;
        adjust = this.originalTimestamp;
       
        /**
         * shift-start rule
         */
        if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.originalTimestamp.toLocalTime().compareTo(d.getShiftStart())<0 && this.originalTimestamp.toLocalTime().plusMinutes(d.getRoundInterval()).compareTo(d.getShiftStart())>=0){
            adjust = adjust.withHour(d.getShiftStart().getHour());
            adjust = adjust.withMinute(d.getShiftStart().getMinute());
            adjust = adjust.withSecond(d.getShiftStart().getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.SHIFT_START;
        /**
         * shift-stop rule
         */
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.originalTimestamp.toLocalTime().compareTo(d.getShiftStop())>0 && this.originalTimestamp.toLocalTime().minusMinutes(d.getRoundInterval()).compareTo(d.getShiftStop())<=0){
            adjust = adjust.withHour(d.getShiftStop().getHour());
            adjust = adjust.withMinute(d.getShiftStop().getMinute());
            adjust = adjust.withSecond(d.getShiftStop().getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.SHIFT_STOP;
        /**
         * lunch-start rule
         */
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.punchType.toString().compareTo("CLOCK OUT") == 0 && this.originalTimestamp.toLocalTime().compareTo(d.getLunchStart())>0 && this.originalTimestamp.toLocalTime().compareTo(d.getLunchStop())<0){
            adjust = adjust.withHour(d.getLunchStart().getHour());
            adjust = adjust.withMinute(d.getLunchStart().getMinute());
            adjust = adjust.withSecond(d.getLunchStart().getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.LUNCH_START;
        /**
         * lunch-stop rule
         */
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.punchType.toString().compareTo("CLOCK IN") == 0 && this.originalTimestamp.toLocalTime().compareTo(d.getLunchStart())>0 && this.originalTimestamp.toLocalTime().compareTo(d.getLunchStop())<0){
            adjust = adjust.withHour(d.getLunchStop().getHour());
            adjust = adjust.withMinute(d.getLunchStop().getMinute());
            adjust = adjust.withSecond(d.getLunchStop().getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.LUNCH_STOP;
        /**
         * grace period(for shift-start) rule
         */
        
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.punchType.toString().compareTo("CLOCK IN") == 0 && this.originalTimestamp.toLocalTime().compareTo(d.getShiftStart())>0 && this.originalTimestamp.toLocalTime().minusMinutes(d.getGracePeriod()).compareTo(d.getShiftStart())<=0){
            adjust = adjust.withHour(d.getShiftStart().getHour());
            adjust = adjust.withMinute(d.getShiftStart().getMinute());
            adjust = adjust.withSecond(d.getShiftStart().getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.SHIFT_START;
        /**
         * grace period(for shift-stop) rule
         */
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.punchType.toString().compareTo("CLOCK OUT") == 0 && this.originalTimestamp.toLocalTime().compareTo(d.getShiftStop())<0 && this.originalTimestamp.toLocalTime().plusMinutes(d.getGracePeriod()).compareTo(d.getShiftStop())>=0){
            adjust = adjust.withHour(d.getShiftStop().getHour());
            adjust = adjust.withMinute(d.getShiftStop().getMinute());
            adjust = adjust.withSecond(d.getShiftStop().getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.SHIFT_STOP;
        /**
         * dock penalty for shift-start rule
         */
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.punchType.toString().compareTo("CLOCK IN") == 0 && this.originalTimestamp.toLocalTime().compareTo(d.getShiftStart())>0 && this.originalTimestamp.toLocalTime().minusMinutes(d.getGracePeriod()).compareTo(d.getShiftStart())>=0 && this.originalTimestamp.toLocalTime().minusMinutes(d.getDockPenalty()).compareTo(d.getShiftStart())<=0){
            adjust = adjust.withHour(d.getShiftStart().plusMinutes(d.getDockPenalty()).getHour());
            adjust = adjust.withMinute(d.getShiftStart().plusMinutes(d.getDockPenalty()).getMinute());
            adjust = adjust.withSecond(d.getShiftStart().plusMinutes(d.getDockPenalty()).getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.SHIFT_DOCK;
        /**
         * dock penalty for shift-stop rule
         */  
        }else if(this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SATURDAY)!=0 && this.originalTimestamp.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)!=0 && this.punchType.toString().compareTo("CLOCK OUT") == 0 && this.originalTimestamp.toLocalTime().compareTo(d.getShiftStop())<0 && this.originalTimestamp.toLocalTime().plusMinutes(d.getGracePeriod()).compareTo(d.getShiftStop())<=0 && this.originalTimestamp.toLocalTime().plusMinutes(d.getDockPenalty()).compareTo(d.getShiftStop())>=0){
            adjust = adjust.withHour(d.getShiftStop().minusMinutes(d.getDockPenalty()).getHour());
            adjust = adjust.withMinute(d.getShiftStop().minusMinutes(d.getDockPenalty()).getMinute());
            adjust = adjust.withSecond(d.getShiftStop().minusMinutes(d.getDockPenalty()).getSecond());
            adjust = adjust.withSecond(0);
            adjust = adjust.withNano(0);
            this.adjustmenttype = PunchAdjustmentType.SHIFT_DOCK;
        /**
         * interval rule
         */
        }else{
            if(((this.originalTimestamp.getMinute()%d.getRoundInterval())*60+this.originalTimestamp.getSecond())<=((d.getRoundInterval()/2)*60+29) && this.originalTimestamp.getMinute()%d.getRoundInterval()!=0){
                adjust = this.originalTimestamp.minusMinutes(this.originalTimestamp.getMinute()%d.getRoundInterval());
                adjust = adjust.withSecond(0);
                adjust = adjust.withNano(0);
                this.adjustmenttype = PunchAdjustmentType.INTERVAL_ROUND;
            }else if(((this.originalTimestamp.getMinute()%d.getRoundInterval())*60+this.originalTimestamp.getSecond())>((d.getRoundInterval()/2)*60+29) && this.originalTimestamp.getMinute()%d.getRoundInterval()!=0){
                adjust = this.originalTimestamp.plusMinutes(d.getRoundInterval()-this.originalTimestamp.getMinute()%d.getRoundInterval());
                adjust = adjust.withSecond(0);
                adjust = adjust.withNano(0);
                this.adjustmenttype = PunchAdjustmentType.INTERVAL_ROUND;
            /**
             * none rule
             */
            }else if(this.originalTimestamp.getMinute()%d.getRoundInterval()==0){
                adjust = adjust.withSecond(0);
                adjust = adjust.withNano(0);
                this.adjustmenttype = PunchAdjustmentType.NONE;
            }
        }
        
        this.adjustedTimestamp = adjust;
        
    }
    public String getFormattedOriginalTimestamp() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        return originalTimestamp.format(dateFormatter).substring(0, 3).toUpperCase() + originalTimestamp.format(dateFormatter).substring(3);
    }

    public String getFormattedAdjustedTimestamp() {
        if (adjustedTimestamp == null) {
            return null;
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
        return adjustedTimestamp.format(dateFormatter).substring(0, 3).toUpperCase() + adjustedTimestamp.format(dateFormatter).substring(3);
    }
}