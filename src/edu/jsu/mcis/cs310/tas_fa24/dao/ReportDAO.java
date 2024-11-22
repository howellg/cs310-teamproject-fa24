package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import com.github.cliftonlabs.json_simple.*;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class ReportDAO {
    
    private static final String QUERY_FIND = "SELECT * FROM employee WHERE departmentid = ? ORDER BY lastname";
    private static final String QUERY_FIND1 = "SELECT * FROM employee ORDER BY lastname, firstname, middlename";

    private final DAOFactory daoFactory;

    ReportDAO(DAOFactory daoFactory){
        this.daoFactory = daoFactory;
    }
    
    public String getBadgeSummary(Integer id) throws SQLException{
        JsonArray j = new JsonArray();
        PreparedStatement ps = null;
        ResultSet rs = null;
        DepartmentDAO departmentDAO = daoFactory.getDepartmentDAO();
        
        if(id!=null){
            try{
                Connection conn = daoFactory.getConnection();
            
                if (conn.isValid(0)){
                    ps = conn.prepareStatement(QUERY_FIND);
                    ps.setInt(1, id);
                
                    boolean hasresults = ps.execute();
                
                    if (hasresults){
                        rs = ps.getResultSet();
                    
                        while (rs.next()){
                            String lastname = rs.getString("lastname");
                            String firstname = rs.getString("firstname");
                            String middlename = rs.getString("middlename");
                            String badgeid = rs.getString("badgeid");
                            int departmentid = rs.getInt("departmentid");
                            int employeetypeid = rs.getInt("employeetypeid");
                        
                            Department department = departmentDAO.find(departmentid);
                            String department_description = department.getDescription();
                        
                            JsonObject jsonObject = new JsonObject();
                            jsonObject.put("badgeid", badgeid);
                            jsonObject.put("name", lastname+", "+firstname+ " "+ middlename);
                            jsonObject.put("department", department_description);
                            if(employeetypeid==0){
                                jsonObject.put("type", "Temporary Employee");
                            }else{
                                jsonObject.put("type", "Full-Time Employee");
                            }
                            j.add(jsonObject);
                        }
                    }
                }
            }
            catch (Exception e) { e.printStackTrace(); } 
        
            finally {
                if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
                if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }  
            
            }
        }else{
            try{
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)){
                ps = conn.prepareStatement(QUERY_FIND1);
                
                boolean hasresults = ps.execute();
                
                if (hasresults){
                    rs = ps.getResultSet();
                    
                    while (rs.next()){
                        String lastname = rs.getString("lastname");
                        String firstname = rs.getString("firstname");
                        String middlename = rs.getString("middlename");
                        String badgeid = rs.getString("badgeid");
                        int departmentid = rs.getInt("departmentid");
                        int employeetypeid = rs.getInt("employeetypeid");
                        
                        Department department = departmentDAO.find(departmentid);
                        String department_description = department.getDescription();
                        
                        JsonObject jsonObject = new JsonObject();
                        jsonObject.put("badgeid", badgeid);
                        jsonObject.put("name", lastname+", "+firstname+ " "+ middlename);
                        jsonObject.put("department", department_description);
                        if(employeetypeid==0){
                            jsonObject.put("type", "Temporary Employee");
                        }else{
                            jsonObject.put("type", "Full-Time Employee");
                        }
                        j.add(jsonObject);  
                        }
                    }
                }
            }
            catch (Exception e) { e.printStackTrace(); } 
        
            finally {
                if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
                if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }  
            
            }
        }
        return Jsoner.serialize(j);
    }
    
    public String getWhosInWhosOut(LocalDateTime ts, Integer departmentId) throws SQLException {
        List<EmployeeStatus> employeeStatuses = new ArrayList<>();
        List<Employee> employees = getEmployees(departmentId);
        
        for (Employee employee : employees) {
            EmployeeStatus status = getEmployeeStatus(employee, ts);
            employeeStatuses.add(status);
        }
        
        List<EmployeeStatus> inList = new ArrayList<>();
        List<EmployeeStatus> outList = new ArrayList<>();

        //separate in/out lists
        for (EmployeeStatus status : employeeStatuses) {
            if (status.isIn()) {
                inList.add(status);
            } else {
                outList.add(status);
            }
        }

        //comparator to sort by employee type order, last name, then first name
        Comparator<EmployeeStatus> comparator = Comparator.comparingInt(EmployeeStatus::getEmployeeTypeOrder).thenComparing(EmployeeStatus::getLastName).thenComparing(EmployeeStatus::getFirstName);

        //sort both lists using the comparator
        inList.sort(comparator);
        outList.sort(comparator);

        JsonArray jsonArray = new JsonArray();

        //add in employees
        for (EmployeeStatus status : inList) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("firstname", status.getFirstName());
            jsonObject.put("lastname", status.getLastName());
            jsonObject.put("badgeid", status.getBadgeId());
            jsonObject.put("employeetype", status.getEmployeeType());
            jsonObject.put("shift", status.getShiftDescription());
            jsonObject.put("status", status.isIn() ? "In" : "Out");
            
            if (status.isIn()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
                String formattedArrival = status.getArrivalTime().format(formatter).toUpperCase();
                jsonObject.put("arrived", formattedArrival);
            }
            jsonArray.add(jsonObject);
        }

        //add out employees
        for (EmployeeStatus status : outList) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("firstname", status.getFirstName());
            jsonObject.put("lastname", status.getLastName());
            jsonObject.put("badgeid", status.getBadgeId());
            jsonObject.put("employeetype", status.getEmployeeType());
            jsonObject.put("shift", status.getShiftDescription());
            jsonObject.put("status", status.isIn() ? "In" : "Out");
            
            if (status.isIn()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MM/dd/yyyy HH:mm:ss");
                String formattedArrival = status.getArrivalTime().format(formatter).toUpperCase();
                jsonObject.put("arrived", formattedArrival);
            }
            jsonArray.add(jsonObject);
        }

        return Jsoner.serialize(jsonArray);
    }

    //get employees by their department id
    private List<Employee> getEmployees(Integer departmentId) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = daoFactory.getConnection();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();

        String query = (departmentId != null) ? QUERY_FIND : QUERY_FIND1;

        try {
            ps = conn.prepareStatement(query);
            if (departmentId != null) {
                ps.setInt(1, departmentId);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Employee employee = employeeDAO.find(id);
                employees.add(employee);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return employees;
    }

    //gets the employee's status on specific timestamp
    private EmployeeStatus getEmployeeStatus(Employee employee, LocalDateTime ts) throws SQLException {
        PunchDAO punchDAO = daoFactory.getPunchDAO();
        Badge badge = employee.getBadge();
        Punch lastPunch = null;
        String query = "SELECT * FROM event WHERE badgeid = ? AND timestamp <= ? ORDER BY timestamp DESC LIMIT 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = daoFactory.getConnection();

        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, badge.getId());
            ps.setTimestamp(2, Timestamp.valueOf(ts));
            rs = ps.executeQuery();

            if (rs.next()) {
                int punchId = rs.getInt("id");
                lastPunch = punchDAO.find(punchId);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        boolean isIn = false;
        LocalDateTime arrivalTime = null;

        //check if in or out
        if (lastPunch != null) {
            if (lastPunch.getPunchType() == EventType.CLOCK_IN) {
                isIn = true;
                arrivalTime = lastPunch.getOriginalTimestamp();
            } else if (lastPunch.getPunchType() == EventType.CLOCK_OUT) {
                isIn = false;
            }
        } else {
            isIn = false; //out
        }

        return new EmployeeStatus(employee, isIn, arrivalTime);
    }
    

}
