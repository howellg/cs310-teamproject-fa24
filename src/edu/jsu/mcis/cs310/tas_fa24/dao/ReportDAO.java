package edu.jsu.mcis.cs310.tas_fa24.dao;

import edu.jsu.mcis.cs310.tas_fa24.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import com.github.cliftonlabs.json_simple.*;

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
    
}
