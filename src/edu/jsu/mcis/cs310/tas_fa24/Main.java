package edu.jsu.mcis.cs310.tas_fa24;

import edu.jsu.mcis.cs310.tas_fa24.dao.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.*;
import java.util.*;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.math.RoundingMode;
import java.time.temporal.TemporalAdjusters;

public class Main {
    public static void main(String[] args) throws SQLException {
        
        // test database connectivity; get DAOs

        DAOFactory daoFactory = new DAOFactory("tas.jdbc");
        AbsenteeismDAO absenteeismDAO = daoFactory.getAbsenteeismDAO();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        PunchDAO punchDAO = daoFactory.getPunchDAO();

        /**  
         * Get Punch/Employee Objects
         */
        
        Punch p = punchDAO.find(4943);
        Employee e = employeeDAO.find(p.getBadge());
        Shift s = e.getShift();
        Badge b = e.getBadge();
        
        /**
         *  Get Pay Period Punch List 
         */
        
        LocalDate ts = p.getOriginalTimestamp().toLocalDate();
        LocalDate begin = ts.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate end = begin.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        
        ArrayList<Punch> punchlist = punchDAO.list(b, begin, end);
        
        /**
         *  Adjust Punch List
         */
        
        for (Punch punch : punchlist) {
            punch.adjust(s);
            //System.err.println(punch.printAdjusted());
        }
        
        /** 
         * Compute Pay Period Total Absenteeism
         */
        
        BigDecimal percentage = DAOUtility.calculateAbsenteeism(punchlist, s);
        
        /** 
         * Insert Absenteeism Into Database
         */
        
        Absenteeism a1 = new Absenteeism(e, ts, percentage);
        System.err.println("Test big decimal: " +a1.toString());

    }

}
