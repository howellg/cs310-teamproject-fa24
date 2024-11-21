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
        BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        PunchDAO punchDAO = daoFactory.getPunchDAO();
        ShiftDAO shiftDAO = daoFactory.getShiftDAO();
        ReportDAO reportDAO = daoFactory.getReportDAO();
        
        /* Create Badge end Employee Objects */
        
        Badge b = badgeDAO.find("D2CC71D4");
        Employee e = employeeDAO.find(b);
        
        /* PART ONE */
        
        /* Get Shift Object for Pay Period Starting 08-26-2018 (regular Shift 1 schedule) */
        
        LocalDate ts = LocalDate.of(2018, Month.AUGUST, 26);
        LocalDate begin = ts.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate end = begin.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        
        Shift s = shiftDAO.find(b, ts);
        
        /* Retrieve Punch List #1 */
        
        ArrayList<Punch> p1 = punchDAO.list(b, begin, end);
        
        for (Punch p : p1) {
            p.adjust(s);
        }
        
        /* Calculate Pay Period 08-26-2018 Absenteeism */
        
        BigDecimal percentage = DAOUtility.calculateAbsenteeism(p1, s);
        Absenteeism a1 = new Absenteeism(e, ts, percentage);
        System.err.println("Test big decimal: " +a1.toString());
        
        String x = reportDAO.getBadgeSummary(4);
        System.err.println(x);

    }

}
