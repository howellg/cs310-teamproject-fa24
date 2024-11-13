package edu.jsu.mcis.cs310.tas_fa24;

import edu.jsu.mcis.cs310.tas_fa24.dao.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;
import com.github.cliftonlabs.json_simple.*;
import java.time.LocalDate;

public class JSONTest {

    private DAOFactory daoFactory;

    @Before
    public void setup() {

        daoFactory = new DAOFactory("tas.jdbc");

    }

    @Test
    public void testJSONShift1Weekday() {

        try {

            BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
            PunchDAO punchDAO = daoFactory.getPunchDAO();
            ShiftDAO shiftDAO = daoFactory.getShiftDAO();

            /* Expected JSON Data */
            String expectedJSON = "[{\"originaltimestamp\":\"FRI 09\\/07\\/2018 06:50:35\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"FRI 09\\/07\\/2018 07:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"3634\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"FRI 09\\/07\\/2018 12:03:54\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"FRI 09\\/07\\/2018 12:00:00\",\"adjustmenttype\":\"Lunch Start\",\"terminalid\":\"104\",\"id\":\"3687\",\"punchtype\":\"CLOCK OUT\"},{\"originaltimestamp\":\"FRI 09\\/07\\/2018 12:23:41\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"FRI 09\\/07\\/2018 12:30:00\",\"adjustmenttype\":\"Lunch Stop\",\"terminalid\":\"104\",\"id\":\"3688\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"FRI 09\\/07\\/2018 15:34:13\",\"badgeid\":\"28DC3FB8\",\"adjustedtimestamp\":\"FRI 09\\/07\\/2018 15:30:00\",\"adjustmenttype\":\"Shift Stop\",\"terminalid\":\"104\",\"id\":\"3716\",\"punchtype\":\"CLOCK OUT\"}]";

            ArrayList<HashMap<String, String>> expected = (ArrayList) Jsoner.deserialize(expectedJSON);

            /* Get Punch/Badge/Shift Objects */
            Punch p = punchDAO.find(3634);
            Badge b = badgeDAO.find(p.getBadge().getId());
            Shift s = shiftDAO.find(b);

            /* Get/Adjust Daily Punch List */
            ArrayList<Punch> dailypunchlist = punchDAO.list(b, p.getOriginalTimestamp().toLocalDate());

            for (Punch punch : dailypunchlist) {
                punch.adjust(s);
            }

            /* JSON Conversion */
            String actualJSON = DAOUtility.getPunchListAsJSON(dailypunchlist);

            ArrayList<HashMap<String, String>> actual = (ArrayList) Jsoner.deserialize(actualJSON);

            /* Compare to Expected JSON */
            assertEquals(expected, actual);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testJSONShift1Weekend() {

        try {

            BadgeDAO badgeDAO = daoFactory.getBadgeDAO();

            PunchDAO punchDAO = daoFactory.getPunchDAO();
            ShiftDAO shiftDAO = daoFactory.getShiftDAO();

            /* Expected JSON Data */
            String expectedJSON = "[{\"originaltimestamp\":\"SAT 08\\/11\\/2018 05:54:58\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"SAT 08\\/11\\/2018 06:00:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"1087\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"SAT 08\\/11\\/2018 12:04:02\",\"badgeid\":\"F1EE0555\",\"adjustedtimestamp\":\"SAT 08\\/11\\/2018 12:00:00\",\"adjustmenttype\":\"Interval Round\",\"terminalid\":\"105\",\"id\":\"1162\",\"punchtype\":\"CLOCK OUT\"}]";

            ArrayList<HashMap<String, String>> expected = (ArrayList) Jsoner.deserialize(expectedJSON);

            /* Get Punch/Badge/Shift Objects */
            Punch p = punchDAO.find(1087);
            Badge b = badgeDAO.find(p.getBadge().getId());
            Shift s = shiftDAO.find(b);

            /* Get/Adjust Daily Punch List */
            ArrayList<Punch> dailypunchlist = punchDAO.list(b, p.getOriginalTimestamp().toLocalDate());

            for (Punch punch : dailypunchlist) {
                punch.adjust(s);
            }

            /* JSON Conversion */
            String actualJSON = DAOUtility.getPunchListAsJSON(dailypunchlist);

            ArrayList<HashMap<String, String>> actual = (ArrayList) Jsoner.deserialize(actualJSON);

            /* Compare to Expected JSON */
            assertEquals(expected, actual);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testJSONShift2Weekday() {

        try {

            BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
            PunchDAO punchDAO = daoFactory.getPunchDAO();
            ShiftDAO shiftDAO = daoFactory.getShiftDAO();

            /* Expected JSON Data */
            String expectedJSON = "[{\"originaltimestamp\":\"TUE 09\\/18\\/2018 11:59:33\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"TUE 09\\/18\\/2018 12:00:00\",\"adjustmenttype\":\"Shift Start\",\"terminalid\":\"104\",\"id\":\"4943\",\"punchtype\":\"CLOCK IN\"},{\"originaltimestamp\":\"TUE 09\\/18\\/2018 21:30:27\",\"badgeid\":\"08D01475\",\"adjustedtimestamp\":\"TUE 09\\/18\\/2018 21:30:00\",\"adjustmenttype\":\"None\",\"terminalid\":\"104\",\"id\":\"5004\",\"punchtype\":\"CLOCK OUT\"}]";

            ArrayList<HashMap<String, String>> expected = (ArrayList) Jsoner.deserialize(expectedJSON);

            /* Get Punch/Badge/Shift Objects */
            Punch p = punchDAO.find(4943);
            Badge b = badgeDAO.find(p.getBadge().getId());
            Shift s = shiftDAO.find(b);

            /* Get/Adjust Daily Punch List */
            ArrayList<Punch> dailypunchlist = punchDAO.list(b, p.getOriginalTimestamp().toLocalDate());

            for (Punch punch : dailypunchlist) {
                punch.adjust(s);
            }

            /* JSON Conversion */
            String actualJSON = DAOUtility.getPunchListAsJSON(dailypunchlist);

            ArrayList<HashMap<String, String>> actual = (ArrayList) Jsoner.deserialize(actualJSON);

            /* Compare to Expected JSON */
            assertEquals(expected, actual);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testJSONSinglePunch() {
        try {
            BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
            PunchDAO punchDAO = daoFactory.getPunchDAO();
            ShiftDAO shiftDAO = daoFactory.getShiftDAO();

            String expectedJSON = "[{\"originaltimestamp\":\"TUE 09\\/18\\/2018 21:30:40\",\"badgeid\":\"CBDE17A7\",\"adjustedtimestamp\":\"TUE 09\\/18\\/2018 21:30:00\",\"adjustmenttype\":\"None\",\"terminalid\":\"104\",\"id\":\"5005\",\"punchtype\":\"CLOCK OUT\"}]";
            ArrayList<HashMap<String, String>> expected = (ArrayList) Jsoner.deserialize(expectedJSON);

            Punch p = punchDAO.find(5005);
            Badge b = badgeDAO.find(p.getBadge().getId());
            Shift s = shiftDAO.find(b);

            ArrayList<Punch> dailypunchlist = punchDAO.list(b, p.getOriginalTimestamp().toLocalDate());
            
            Iterator<Punch> iterator = dailypunchlist.iterator();
            while (iterator.hasNext()) {
                Punch punch = iterator.next();
                if (punch.getId() != 5005) {
                    iterator.remove();
}
            }
            for (Punch punch : dailypunchlist) {
                punch.adjust(s);
            }

            String actualJSON = DAOUtility.getPunchListAsJSON(dailypunchlist);
            ArrayList<HashMap<String, String>> actual = (ArrayList) Jsoner.deserialize(actualJSON);
            assertEquals(expected, actual);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testJSONNoPunches() {
        try {
            BadgeDAO badgeDAO = daoFactory.getBadgeDAO();
            PunchDAO punchDAO = daoFactory.getPunchDAO();

            String expectedJSON = "[]";
            ArrayList<HashMap<String, String>> expected = (ArrayList) Jsoner.deserialize(expectedJSON);
            Badge b = badgeDAO.find("12565C60");
            LocalDate noPunchDate = LocalDate.of(2018, 9, 22);
            ArrayList<Punch> dailypunchlist = punchDAO.list(b, noPunchDate);
            String actualJSON = DAOUtility.getPunchListAsJSON(dailypunchlist);

            ArrayList<HashMap<String, String>> actual = (ArrayList) Jsoner.deserialize(actualJSON);
            assertEquals(expected, actual);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
