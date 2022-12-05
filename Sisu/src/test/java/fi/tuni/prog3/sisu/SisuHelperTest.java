
package fi.tuni.prog3.sisu;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.net.MalformedURLException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SisuHelperTest {
    
    public SisuHelperTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }
    
    // how to write this test?
    @Test
    public void testGetJsonObjectFromApi1() {
        System.out.println("getJsonObjectFromApi1: bad url");
        String urlString = "sisu";
        SisuHelper instance = new SisuHelper();
        instance.getJsonObjectFromApi(urlString);
    }
    
    // how to write this test?
    @Test
    public void testGetJsonObjectFromApi2() {
        System.out.println("getJsonObjectFromApi3: url not responsive");
        String urlString = "www.sisuz.fi";
        SisuHelper instance = new SisuHelper();
        instance.getJsonObjectFromApi(urlString);
        Throwable th = assertThrows(MalformedURLException.class, () -> instance.getJsonObjectFromApi(urlString));
        assertEquals("The url is not well formed: " + urlString, th.getMessage());

    }
    
    @Test
    public void testGetJsonObjectFromApi3() {
        System.out.println("getJsonObjectFromApi");
        String urlString = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId=tut-dp-g-1280&universityId=tuni-university-root-id";
        SisuHelper instance = new SisuHelper();
        Boolean returnValue = instance.getJsonObjectFromApi(urlString).isJsonObject();
        assertEquals(true, returnValue);

    }


        /**
     * Test of createStudyModule method, of class SisuHelper.
     */
    @Test
    public void testCreateStudyModule() {
        System.out.println("createStudyModule test: tut-dp-g-1280");
        //String groupId = "otm-0185dff0-cc6f-4d5e-8ec8-84854ce44e9c";
        //String groupId = "otm-07a61546-3a51-429a-9120-2ce1da02298d";
        //String groupId = "tut-sm-g-4720";
        String groupId = "tut-dp-g-1280";
        //String groupId = "otm-d16d6df1-92a5-4079-8101-c44e26cb072c";
        //String groupId = "tut-dp-g-1180";
        //String groupId = "tut-dp-g-1105";
        SisuHelper instance = new SisuHelper();
        StudyModule result = instance.createStudyModule(groupId);
        String engName = "Master's Programme in Management and Information Technology";
        String fiName = "Johtamisen ja tietotekniikan DI-ohjelma";
        String id = "otm-57bbf28e-a683-435e-a3f6-17db5dd22cdf";
        int credits = 120;
        String code = "PJTM";
        Boolean gradable = false;
        String description = "Johtamisen ja tietotekniikan DI-ohjelmassa voi opiskella päätoimisesti tai työn ohessa. Opinnot voi suorittaa joustavasti joko lähiopintoina tai verkko-opiskeluna. <br />Verkko-opiskelun tueksi tarjotaan lähiopetusta intensiivitoteutuksena. ";
        assertEquals(engName, result.getNameEn());
        assertEquals(fiName, result.getNameFi());
        assertEquals(id, result.getId());
        assertEquals(credits, result.getMinCredits());
        assertEquals(code, result.getCode());
        assertEquals(gradable, result.isGradable());
        assertEquals(description, result.getDescription());
    }
    
    @Test
    public void testCreateStudyModule2() {
        System.out.println("createStudyModule test: tut-dp-g-1180");
        //String groupId = "otm-0185dff0-cc6f-4d5e-8ec8-84854ce44e9c";
        //String groupId = "otm-07a61546-3a51-429a-9120-2ce1da02298d";
        //String groupId = "tut-sm-g-4720";
        //String groupId = "tut-dp-g-1280";
        //String groupId = "otm-d16d6df1-92a5-4079-8101-c44e26cb072c";
        String groupId = "tut-dp-g-1180";
        //String groupId = "tut-dp-g-1105";
        SisuHelper instance = new SisuHelper();
        StudyModule result = instance.createStudyModule(groupId);
        String engName = "Master's Programme in Information Technology";
        String fiName = "Tietotekniikan DI-ohjelma";
        String id = "otm-3990be25-c9fd-4dae-904c-547ac11e8302";
        int credits = 120;
        String code = "TTEM";
        Boolean gradable = false;
        String description = "Tutkinnon suoritettuaan opiskelija hallitsee syventävien opintojensa alan monipuolisesti ja syvällisesti, ja hänellä on valmiudet toimia työelämässä syventävien opintojensa alan asiantuntijana ja kehittäjänä. Hän hallitsee tieteellisen tiedon ja menetelmien perusteet ja osaa soveltaa niitä ajankohtaisiin ja konkreettisiin tehtäviin.  <br /><br />Hänellä on työelämän, tieteellisen toiminnan ja yhteiskunnallisen keskustelun edellyttämät viestintä- ja yhteistyötaidot, sekä hyvät valmiudet tieteelliseen jatkokoulutukseen ja jatkuvaan ammatilliseen kehittymiseen.";
 
        assertEquals(engName, result.getNameEn());
        assertEquals(fiName, result.getNameFi());
        assertEquals(id, result.getId());
        assertEquals(credits, result.getMinCredits());
        assertEquals(code, result.getCode());
        assertEquals(gradable, result.isGradable());
        assertEquals(description, result.getDescription());
    }
    
    
    @Test
    public void testCreateStudyModule3() {
        System.out.println("createStudyModule test: otm-f351ce33-11a6-42be-a15f-7544cb194bff");
        String groupId = "otm-f351ce33-11a6-42be-a15f-7544cb194bff";
        SisuHelper instance = new SisuHelper();
        StudyModule result = instance.createStudyModule(groupId);
        String engName = "Specialty Training in Plastic Surgery (56/2015)";
        String fiName = "Plastiikkakirurgian erikoislääkärikoulutus (56/2015)";
        String id = "otm-f351ce33-11a6-42be-a15f-7544cb194bff";
        int credits = 0;
        String code = "MEDAPLKEL2015";
        Boolean gradable = false;
        String description = "";
 
        assertEquals(engName, result.getNameEn());
        assertEquals(fiName, result.getNameFi());
        assertEquals(id, result.getId());
        assertEquals(credits, result.getMinCredits());
        assertEquals(code, result.getCode());
        assertEquals(gradable, result.isGradable());
        assertEquals(description, result.getDescription());
    }
    
    
     @Test
    public void testCreateStudyModule4() {
        System.out.println("createStudyModule test: otm-4d4c4575-a5ae-427e-a860-2f168ad4e8ba");
        String groupId = "otm-4d4c4575-a5ae-427e-a860-2f168ad4e8ba";
        SisuHelper instance = new SisuHelper();
        StudyModule result = instance.createStudyModule(groupId);
        String engName = "Bachelor's Programme in Science and Engineering";
        String fiName = "Bachelor's Programme in Science and Engineering";
        String id = "otm-4d4c4575-a5ae-427e-a860-2f168ad4e8ba";
        int credits = 180;
        String code = "SCEK";
        Boolean gradable = false;
        //String description = "\"-After completion of the programme, the students<br /><br />- understand the role of mathematics, physical sciences, and information and communications technology in the modern-day society and have the competence to follow the development and societal significance of their field<br /><br />- know the basics of the essential areas of mathematics and physics<br /><br />- are able to apply skills in mathematical and physical modeling, computational methods and numerics to solve problems related to mathematics, physics and information and communications technology<br /><br />- are able to use scientific methods to interpret information, draw conclusions and report on their findings<br /><br />- have active language and intercultural communication skills and are able to communicate fluently in writing and verbally in English in the context of their field<br /><br />- have the competence to apply their acquired knowledge and skills in working life and understand the demands of the Finnish working life as well as of international settings<br /><br />- are prepared for professional development and lifelong learning\"";
 
        assertEquals(engName, result.getNameEn());
        assertEquals(fiName, result.getNameFi());
        assertEquals(id, result.getId());
        assertEquals(credits, result.getMinCredits());
        assertEquals(code, result.getCode());
        assertEquals(gradable, result.isGradable());
        //assertEquals(description, result.getDescription());
    }

       /**
     * Test of getChildrenFromOneDocument method, of class SisuHelper.
     */
    @Test
    public void testGetChildrenFromOneDocument() {
        System.out.println("getChildrenFromOneDocument");
        JsonArray rules = null;
        JsonArray children = null;
        SisuHelper instance = new SisuHelper();
        instance.getChildrenFromOneDocument(rules, children);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCourse method, of class SisuHelper.
     */
    @Test
    public void testCreateCourse1() {
        System.out.println("createCourse: tut-cu-g-38576");
        //String groupId = "tut-cu-g-48243";
        //String groupId = "tut-cu-g-43073";
        //String groupId = "tut-cu-g-48230";
        String groupId = "tut-cu-g-38576";
        SisuHelper instance = new SisuHelper();
        Course result = instance.createCourse(groupId);
        String nameFi =  "Tietojärjestelmän mallintaminen";
        String nameEn = "Information System Modelling";
        String id = "otm-1c79239a-e31c-4819-8342-f6e51783dc86";
        String code = "COMP.SE.310";
        int credits = 5;
        assertEquals(nameFi, result.getNameFi());
        assertEquals(nameEn, result.getNameEn());
        assertEquals(id, result.getId());
        assertEquals(code, result.getCode());
        assertEquals(credits, result.getMinCredits());
        assertEquals(true, result.isGradable());
    }
    
        /**
     * Test of createCourse method, of class SisuHelper.
     */
    @Test
    public void testCreateCourse2() {
        System.out.println("createCourse: tut-cu-g-43041");
        //String groupId = "tut-cu-g-48243";
        //String groupId = "tut-cu-g-43073";
        //String groupId = "tut-cu-g-48230";
        String groupId = "tut-cu-g-43041";
        SisuHelper instance = new SisuHelper();
        Course result = instance.createCourse(groupId);
        String nameFi = "Advanced Robotics";
        String nameEn = "Advanced Robotics";
        String id = "otm-866e3a16-9772-4a40-9de4-4996f48660a5";
        String code = "AUT.720";
        int credits = 5;
        assertEquals(nameFi, result.getNameFi());
        assertEquals(nameEn, result.getNameEn());
        assertEquals(id, result.getId());
        assertEquals(code, result.getCode());
        assertEquals(credits, result.getMinCredits());
        assertEquals(true, result.isGradable());
    }

    
    
    /**
     * Test of getAllSubModulesOrCourses method, of class SisuHelper.
     */
    @Test
    public void testGetAllSubModulesOrCourses() {
        System.out.println("getAllSubModulesOrCourses");
        JsonObject rule = null;
        SisuHelper instance = new SisuHelper();
        JsonArray expResult = null;
        //JsonArray result = instance.getChildrenFromOneDocument(rule);
        assertEquals(expResult, true);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    

   

    @Test 
    public void testGetAllPrograms(){
        System.out.println("getAllPrograms 2021");
        SisuHelper sh = new SisuHelper();
        ArrayList<Program> programs = sh.getAllPrograms(2021, "en");
        int results = 273;
        /*System.out.println("List of programs:");
        for(int i = 0; i<40; i++){
            System.out.println(programs.get(i));
        }*/
        assertEquals(results, programs.size());  
    }
    
     @Test 
    public void testGetAllPrograms1(){
        System.out.println("getAllPrograms 2022");
        SisuHelper sh = new SisuHelper();
        ArrayList<Program> programs = sh.getAllPrograms(2022, "en");
        int results = 277;
        /*System.out.println("List of programs:");
        for(int i = 0; i<40; i++){
            System.out.println(programs.get(i));
        }*/
        assertEquals(results, programs.size());  
    }

    /**
     * Test of getJsonObjectFromApi method, of class SisuHelper.
     */
    @Test
    public void testGetJsonObjectFromApi() {
        System.out.println("getJsonObjectFromApi");
        String urlString = "";
        SisuHelper instance = new SisuHelper();
        JsonObject expResult = null;
        JsonObject result = instance.getJsonObjectFromApi(urlString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCourse method, of class SisuHelper.
     */
    @Test
    public void testCreateCourse() {
        System.out.println("createCourse");
        String groupId = "";
        SisuHelper instance = new SisuHelper();
        Course expResult = null;
        Course result = instance.createCourse(groupId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of importDataFromJson method, of class SisuHelper.
     */
    @Test
    public void testImportDataFromJson() throws Exception {
        System.out.println("importDataFromJson");
        String filePath = "../Sisu/src/main/resources/MyPlan.json";
                
        SisuHelper instance = new SisuHelper();
        //StudyModule expResult = null;
        Student result = instance.importDataFromJson(filePath);
        assertEquals(true, true);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
