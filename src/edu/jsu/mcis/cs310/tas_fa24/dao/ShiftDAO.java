package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.*;
import java.sql.*;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;

public class ShiftDAO {

    private final DAOFactory daoFactory;

    public ShiftDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Shift find(int id) throws SQLException {
        Shift shift = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = daoFactory.getConnection();

            String query = "SELECT * FROM shift WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                int shiftId = rs.getInt("id");
                String description = rs.getString("description");
                int dailyScheduleId = rs.getInt("dailyscheduleid");

                DailySchedule defaultSchedule = getDailyScheduleById(dailyScheduleId, conn);

                shift = new Shift(shiftId, description, defaultSchedule);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return shift;
    }

    public Shift find(Badge badge) throws SQLException {
        Shift shift = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = daoFactory.getConnection();

            String query = "SELECT shiftid FROM employee WHERE badgeid = ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, badge.getId());
            rs = ps.executeQuery();

            if (rs.next()) {
                int shiftId = rs.getInt("shiftid");

                shift = find(shiftId);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return shift;
    }
   
    public Shift find(Badge badge, LocalDate date) throws SQLException {
        Shift shift = find(badge); // Get the default shift for the employee
        if (shift == null) {
            return null;
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = daoFactory.getConnection();

            LocalDate payPeriodStart = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
            LocalDate payPeriodEnd = payPeriodStart.plusDays(6);

            applyOverrides(conn, shift, badge, payPeriodStart, payPeriodEnd);
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return shift;
    }


    private DailySchedule getDailyScheduleById(int id, Connection conn) throws SQLException {
        DailySchedule schedule = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM dailyschedule WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                int scheduleId = rs.getInt("id");
                LocalTime shiftStart = rs.getTime("shiftstart").toLocalTime();
                LocalTime shiftStop = rs.getTime("shiftstop").toLocalTime();
                LocalTime lunchStart = rs.getTime("lunchstart").toLocalTime();
                LocalTime lunchStop = rs.getTime("lunchstop").toLocalTime();
                int roundInterval = rs.getInt("roundinterval");
                int gracePeriod = rs.getInt("graceperiod");
                int dockPenalty = rs.getInt("dockpenalty");
                int lunchThreshold = rs.getInt("lunchthreshold");

                schedule = new DailySchedule(scheduleId, shiftStart, shiftStop,
                                             lunchStart, lunchStop, roundInterval,
                                             gracePeriod, dockPenalty, lunchThreshold);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return schedule;
    }


    private void applyOverrides(Connection conn, Shift shift, Badge badge,
                                LocalDate payPeriodStart, LocalDate payPeriodEnd) throws SQLException {

        //overrides for all employees
        applyOverridesFor(conn, shift, null, payPeriodStart, payPeriodEnd, true);
        //overrides for the specific employee
        applyOverridesFor(conn, shift, badge.getId(), payPeriodStart, payPeriodEnd, true);
        //temporary overrides for all employees
        applyOverridesFor(conn, shift, null, payPeriodStart, payPeriodEnd, false);
        //temporary overrides for the specific employee
        applyOverridesFor(conn, shift, badge.getId(), payPeriodStart, payPeriodEnd, false);
    }
    
    private void applyOverridesFor(Connection conn, Shift shift, String badgeId,
                                   LocalDate startDate, LocalDate endDate, boolean recurring) throws SQLException {

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            StringBuilder query = new StringBuilder("SELECT * FROM scheduleoverride WHERE ");
            if (badgeId == null) {
                query.append("badgeid IS NULL AND ");
            } else {
                query.append("badgeid = ? AND ");
            }

            if (recurring) {
                query.append("end IS NULL AND start <= ?");
            } else {
                query.append("end IS NOT NULL AND start <= ? AND end >= ?");
            }

            ps = conn.prepareStatement(query.toString());
            int paramIndex = 1;

            if (badgeId != null) {
                ps.setString(paramIndex++, badgeId);
            }

            ps.setTimestamp(paramIndex++, Timestamp.valueOf(startDate.atStartOfDay()));
            if (!recurring) {
                ps.setTimestamp(paramIndex++, Timestamp.valueOf(endDate.atStartOfDay()));
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                int dailyScheduleId = rs.getInt("dailyscheduleid");
                int dayOfWeekInt = rs.getInt("day");
                DayOfWeek dayOfWeek = DayOfWeek.of(dayOfWeekInt);

                DailySchedule overrideSchedule = getDailyScheduleById(dailyScheduleId, conn);
                shift.setDailySchedule(dayOfWeek, overrideSchedule);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    }
}
