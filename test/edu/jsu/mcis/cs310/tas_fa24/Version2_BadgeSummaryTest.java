package edu.jsu.mcis.cs310.tas_fa24;

import edu.jsu.mcis.cs310.tas_fa24.dao.DAOFactory;
import edu.jsu.mcis.cs310.tas_fa24.dao.ReportDAO;
import org.junit.*;
import static org.junit.Assert.*;
import com.github.cliftonlabs.json_simple.*;

public class Version2_BadgeSummaryTest {

    private ReportDAO reportDAO;

    @Before
    public void setup() {

        DAOFactory daoFactory = new DAOFactory("tas.jdbc");
        reportDAO = daoFactory.getReportDAO();

    }
    
    @Test
    public void testBadgeSummaryAll() {
        
        JsonArray jsonExpected = null, jsonActual = null;
        
        try {
        
            String jsonExpectedString = "[{\"badgeid\":\"9186E711\",\"name\":\"Adams, Cruz C\",\"department\":\"Cleaning\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"D928AFBA\",\"name\":\"Ali, James E\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"4382D92D\",\"name\":\"Alvarez, Laurie J\",\"department\":\"Office\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"95497F63\",\"name\":\"Andrews, Charles E\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"2305D772\",\"name\":\"Antonio, Derrick B\",\"department\":\"Shipping\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"8DB842EF\",\"name\":\"Arney, Veronica C\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"88D12DCC\",\"name\":\"Begaye, Laura A\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"2986FF85\",\"name\":\"Black, Francis R\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"DFE4EB13\",\"name\":\"Black, Jose S\",\"department\":\"Cleaning\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"67637925\",\"name\":\"Brenner, Amy B\",\"department\":\"Press\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"E8A58074\",\"name\":\"Brown, Maria C\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"D2C39273\",\"name\":\"Buck, Ernest E\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"C0063C4C\",\"name\":\"Cain, Kenneth M\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"021890C0\",\"name\":\"Chapell, George R\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"12565C60\",\"name\":\"Chapman, Joshua E\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"BEAFDB2F\",\"name\":\"Clark, Joy R\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"CEA28723\",\"name\":\"Claude, Lillian A\",\"department\":\"Assembly\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"55B74EB5\",\"name\":\"Coleman, Marian E\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"87176FD7\",\"name\":\"Cordero, Alvina P\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"0FFA272B\",\"name\":\"Corwin, John L\",\"department\":\"Press\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"AC239E44\",\"name\":\"Cusson, Peter H\",\"department\":\"Maintenance\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"BD9BB983\",\"name\":\"Dahl, Tamara G\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"C457EFF7\",\"name\":\"Dam, Patricia T\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"4FA55999\",\"name\":\"Davie, Lori W\",\"department\":\"Shipping\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"CF1D8750\",\"name\":\"Dearman, Sarah R\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"E70AD3D2\",\"name\":\"Decker, Betty L\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"8709982E\",\"name\":\"Dent, Judy E\",\"department\":\"Assembly\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"C6C1C0F2\",\"name\":\"Dickman, Freda C\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"E06BE060\",\"name\":\"Dixon, Mary A\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"229324A4\",\"name\":\"Donaldson, Kathleen C\",\"department\":\"Press\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"2A5620A0\",\"name\":\"Eaton, Curtis M\",\"department\":\"Cleaning\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"EC531DE6\",\"name\":\"Elliott, Nancy L\",\"department\":\"Shipping\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"6AA1785E\",\"name\":\"Ellis, Gloria L\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"D4A2072B\",\"name\":\"Ellis, Misty F\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"935B799E\",\"name\":\"Fisher, Chad A\",\"department\":\"Tool and Die\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"124A2DED\",\"name\":\"Ford, Nicholas R\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"DF19620C\",\"name\":\"Forte, Genoveva G\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"9E06A774\",\"name\":\"Foster, Amanda J\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"CBDE17A7\",\"name\":\"Fox, Nichole J\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"6C0D1549\",\"name\":\"Franklin, Ronald W\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"639D4185\",\"name\":\"Gaines, Lee E\",\"department\":\"Assembly\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"DFD9BB5C\",\"name\":\"Gallegos, Phillip M\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"73591F00\",\"name\":\"Gauna, Christine M\",\"department\":\"Shipping\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"0886BF12\",\"name\":\"Gibson, Theresa E\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"29C3C7D4\",\"name\":\"Gomez, Rose M\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"3DA8B226\",\"name\":\"Hamm, Doris R\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"BE51FA92\",\"name\":\"Harris, Andrea D\",\"department\":\"Assembly\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"08D745A6\",\"name\":\"Harris, Christine R\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"4E6E296E\",\"name\":\"Hart, Jessie B\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"9BD0258A\",\"name\":\"Hearn, Edith M\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"CEC9A3DA\",\"name\":\"Hein, Dustin L\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"CF697DE6\",\"name\":\"Hickmon, Brad M\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"C278A564\",\"name\":\"Hill, Rose R\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"2CD387C2\",\"name\":\"Horner, Nicholas M\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"3C03B8D7\",\"name\":\"Howard, Wm N\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"55BAF4B1\",\"name\":\"Hoyt, Larry E\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"1C920A23\",\"name\":\"Hutchison, Claire T\",\"department\":\"Press\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"DFDFE648\",\"name\":\"Jinks, James R\",\"department\":\"Assembly\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"8C0644BA\",\"name\":\"Jones, Debra T\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"E216D413\",\"name\":\"Jones, Mitchell C\",\"department\":\"Shipping\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"76118CDC\",\"name\":\"Kaiser, Fay H\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"8A90B9A3\",\"name\":\"Kawamoto, Thomas S\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"0B8C3085\",\"name\":\"King, Harry L\",\"department\":\"Assembly\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"C47A78C1\",\"name\":\"King, Norman R\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"99F0C0FA\",\"name\":\"Kite, Ernest A\",\"department\":\"Assembly\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"4C459F1E\",\"name\":\"Knaus, Robert B\",\"department\":\"Assembly\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"76E920D9\",\"name\":\"Lambert, Dorothy G\",\"department\":\"Assembly\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"B4BBABEC\",\"name\":\"Lawless, Brian L\",\"department\":\"Shipping\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"B09A75D7\",\"name\":\"Lawrence, Fredrick C\",\"department\":\"Cleaning\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"D2CC71D4\",\"name\":\"Lawson, Matthew J\",\"department\":\"Assembly\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"C1E4758D\",\"name\":\"Leist, Rodney J\",\"department\":\"Warehouse\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"9BFCB537\",\"name\":\"Lilly, James M\",\"department\":\"Shipping\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"08D01475\",\"name\":\"Littell, Amie D\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"1D52BFA2\",\"name\":\"Lowery, Tracy A\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"8D6362AD\",\"name\":\"McGruder, Patricia W\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"29C03912\",\"name\":\"McKain, Ethel H\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"8D9E5710\",\"name\":\"Melia, Ali J\",\"department\":\"Assembly\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"C8E646D8\",\"name\":\"Merrick, Thomas L\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"9973DBF1\",\"name\":\"Miller, Alison M\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"FF591F68\",\"name\":\"Miller, Robert K\",\"department\":\"Shipping\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"CEDB6920\",\"name\":\"Mills, Mildred K\",\"department\":\"Office\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"F1EE0555\",\"name\":\"Montgomery, Dorothy A\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"3437588A\",\"name\":\"Mullen, Margaret M\",\"department\":\"Shipping\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"31A25435\",\"name\":\"Munday, Paul J\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"D1D2C387\",\"name\":\"Omara, Sherri S\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"45E5059F\",\"name\":\"Osborne, Marcus K\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"2A7F5D99\",\"name\":\"Palmer, Raymond J\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"3E9E0E87\",\"name\":\"Patterson, Mauricio N\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"82A8539F\",\"name\":\"Patterson, Rosalee T\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"618072EA\",\"name\":\"Perales, Earl M\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"6CA0FF4A\",\"name\":\"Pierce, Elaine J\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"922370AA\",\"name\":\"Price, Anna V\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"8C4CE4AC\",\"name\":\"Rhoades, Kathryn R\",\"department\":\"Shipping\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"DD6E2C0C\",\"name\":\"Rhodes, Jeffery B\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"9E0476B5\",\"name\":\"Rivers, Sarah R\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"408B195F\",\"name\":\"Robinson, Lawrence D\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"9D527CFB\",\"name\":\"Rodriquez, Jarvis B\",\"department\":\"Shipping\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"76B87761\",\"name\":\"Rohrbach, Diana T\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"CEBCC740\",\"name\":\"Ryan, Salvador F\",\"department\":\"Shipping\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"398B1563\",\"name\":\"Sanchez, Juan T\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"1B2052DE\",\"name\":\"Sanchez, Katherine H\",\"department\":\"Press\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"D4F37E6F\",\"name\":\"Sanchez, Raymond V\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"860CBBEE\",\"name\":\"Simmons, Willie J\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"4E04B5FE\",\"name\":\"Smith, Mary J\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"3282F212\",\"name\":\"Smith, Patrick R\",\"department\":\"Maintenance\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"AB8204A4\",\"name\":\"Snively, Georgine R\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"690538BA\",\"name\":\"Snow, Bonita G\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"CB99D1E8\",\"name\":\"Speier, Bobbie J\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"58EB7EA1\",\"name\":\"Stalker, Karen C\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"4DAC9951\",\"name\":\"Stein, Richard M\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"8001201A\",\"name\":\"Stevens, Michael E\",\"department\":\"Press\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"B5A117E9\",\"name\":\"Sullivan, Mary C\",\"department\":\"Assembly\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"BAD139D6\",\"name\":\"Sutherland, Sandra R\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"B7A6171D\",\"name\":\"Taylor, Elisa G\",\"department\":\"Shipping\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"ADD650A8\",\"name\":\"Taylor, Jennifer T\",\"department\":\"Office\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"93313988\",\"name\":\"Taylor, Steven M\",\"department\":\"Hafting\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"E6386C7C\",\"name\":\"Teel, Kelly K\",\"department\":\"Assembly\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"07901755\",\"name\":\"Terrell, Kenneth R\",\"department\":\"Tool and Die\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"8E5F0240\",\"name\":\"Thomas, Basil P\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"7CB9642F\",\"name\":\"Treat, Scotty L\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"0D87987C\",\"name\":\"Trice, Carol L\",\"department\":\"Press\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"FCE87D9F\",\"name\":\"Tucker, Janice W\",\"department\":\"Hafting\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"E77BFAEA\",\"name\":\"Weedman, Micheal S\",\"department\":\"Maintenance\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"C4F37EFF\",\"name\":\"Welch, Travis C\",\"department\":\"Office\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"2A972897\",\"name\":\"White, Margaret M\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"E880B82A\",\"name\":\"Williams, Tyson E\",\"department\":\"Shipping\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"28DC3FB8\",\"name\":\"Woods, Matthew S\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"E215F3DB\",\"name\":\"Wright, Ann T\",\"department\":\"Cleaning\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"B6902696\",\"name\":\"Wright, Nadine P\",\"department\":\"Shipping\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"A5F194EB\",\"name\":\"Ybarra, Maria J\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"}]";
            jsonExpected = (JsonArray)Jsoner.deserialize(jsonExpectedString);
            
            /* Get "Badge Summary" Report (Grinding Dept) */

            String jsonActualString = reportDAO.getBadgeSummary(null);
            jsonActual = (JsonArray)Jsoner.deserialize(jsonActualString);
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        /* Compare to Expected Values */
        
        assertNotNull(jsonExpected);
        assertNotNull(jsonActual);
        assertEquals(jsonExpected, jsonActual);

    }

    @Test
    public void testBadgeSummaryByDepartment1() {
        
        JsonArray jsonExpected = null, jsonActual = null;
        
        try {
        
            String jsonExpectedString = "[{\"badgeid\":\"88D12DCC\",\"name\":\"Begaye, Laura A\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"2986FF85\",\"name\":\"Black, Francis R\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"D2C39273\",\"name\":\"Buck, Ernest E\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"87176FD7\",\"name\":\"Cordero, Alvina P\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"D4A2072B\",\"name\":\"Ellis, Misty F\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"DF19620C\",\"name\":\"Forte, Genoveva G\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"9E06A774\",\"name\":\"Foster, Amanda J\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"CBDE17A7\",\"name\":\"Fox, Nichole J\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"CEC9A3DA\",\"name\":\"Hein, Dustin L\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"CF697DE6\",\"name\":\"Hickmon, Brad M\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"C47A78C1\",\"name\":\"King, Norman R\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"08D01475\",\"name\":\"Littell, Amie D\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"29C03912\",\"name\":\"McKain, Ethel H\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"31A25435\",\"name\":\"Munday, Paul J\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"2A7F5D99\",\"name\":\"Palmer, Raymond J\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"922370AA\",\"name\":\"Price, Anna V\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"DD6E2C0C\",\"name\":\"Rhodes, Jeffery B\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"AB8204A4\",\"name\":\"Snively, Georgine R\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"690538BA\",\"name\":\"Snow, Bonita G\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"8E5F0240\",\"name\":\"Thomas, Basil P\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"7CB9642F\",\"name\":\"Treat, Scotty L\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"2A972897\",\"name\":\"White, Margaret M\",\"department\":\"Grinding\",\"type\":\"Temporary Employee\"},{\"badgeid\":\"28DC3FB8\",\"name\":\"Woods, Matthew S\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"},{\"badgeid\":\"A5F194EB\",\"name\":\"Ybarra, Maria J\",\"department\":\"Grinding\",\"type\":\"Full-Time Employee\"}]";
            jsonExpected = (JsonArray)Jsoner.deserialize(jsonExpectedString);
            
            /* Get "Badge Summary" Report (Grinding Dept) */

            String jsonActualString = reportDAO.getBadgeSummary(4);
            jsonActual = (JsonArray)Jsoner.deserialize(jsonActualString);
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        /* Compare to Expected Values */
        
        assertNotNull(jsonExpected);
        assertNotNull(jsonActual);
        assertEquals(jsonExpected, jsonActual);

    }
    @Test
    public void testBadgeSummaryNoMatchingDepartment() {
    
        JsonArray jsonActual = null;
            try {
                String jsonActualString = reportDAO.getBadgeSummary(99);
                jsonActual = (JsonArray)Jsoner.deserialize(jsonActualString);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        
        assertNotNull(jsonActual);
        assertTrue(jsonActual.isEmpty());

    }
    
    @Test
    public void testBadgeSummaryMaintenanceDepartment() {

        JsonArray jsonActual = null;
        try {
            String jsonActualString = reportDAO.getBadgeSummary(5);
            jsonActual = (JsonArray)Jsoner.deserialize(jsonActualString);
        }
        catch (Exception e) {
           e.printStackTrace();
        }

        assertNotNull(jsonActual);
        assertFalse(jsonActual.isEmpty());
    }
    @Test
    public void testBadgeSummaryInvalidDepartmentID() {

        JsonArray jsonActual = null;
        try {
            String jsonActualString = reportDAO.getBadgeSummary(Integer.valueOf("InvalidID"));
            jsonActual = (JsonArray)Jsoner.deserialize(jsonActualString);
        }
        catch (Exception e) {
            assertNull(jsonActual);
        }
    }
}
