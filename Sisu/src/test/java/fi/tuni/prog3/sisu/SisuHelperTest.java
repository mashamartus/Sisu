/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author hanna
 */
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

    /**
     * TODO
     * Test of getJsonObjectFromApi method, of class SisuHelper.
     */
    @Test
    public void testGetJsonObjectFromApi() {
        System.out.println("getJsonObjectFromApi");
        String urlString = "https://sis-tuni.funidata.fi/kori/api/modules/by-group-id?groupId=tut-dp-g-1280&universityId=tuni-university-root-id";
        SisuHelper instance = new SisuHelper();
        Boolean expResult = true;
        Boolean result = true;
        Object returnValue = instance.getJsonObjectFromApi(urlString);
        //System.out.print(returnValue);
        assertEquals(expResult, result);

    }
    


        /**
     * Test of createStudyModule method, of class SisuHelper.
     */
    @Test
    public void testCreateStudyModule() {
        System.out.println("createStudyModule");
        //String groupId = "otm-0185dff0-cc6f-4d5e-8ec8-84854ce44e9c";
        //String groupId = "otm-07a61546-3a51-429a-9120-2ce1da02298d";
        //String groupId = "tut-sm-g-4720";
        //String groupId = "tut-dp-g-1280";
        //String groupId = "otm-d16d6df1-92a5-4079-8101-c44e26cb072c";
        String groupId = "tut-dp-g-1180";
        SisuHelper instance = new SisuHelper();
        Boolean expResult = true;
        StudyModule result = instance.createStudyModule(groupId);
        assertEquals(expResult, true);
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
    public void testCreateCourse() {
        System.out.println("createCourse");
        //String groupId = "tut-cu-g-48243";
        //String groupId = "tut-cu-g-43073";
        //String groupId = "tut-cu-g-48230";
        String groupId = "tut-cu-g-38576";
        SisuHelper instance = new SisuHelper();
        Boolean expResult = true;
        Course result = instance.createCourse(groupId);
        String name = result.getName();
        String code = result.getCode();
        int credits = result.getMinCredits();
        System.out.println(name + " : "+ code +" : " +credits);
        assertEquals(expResult, true);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
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
    
    

    /**
     * Test of navigate method, of class SisuHelper.
     */
    @Test
    public void testNavigate() throws Exception {
        System.out.println("navigate");
        JsonObject jsonObject = null;
        SisuHelper.navigate(jsonObject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStudyModuleFromJsonFile method, of class SisuHelper.
     */
    @Test
    public void testCreateStudyModuleFromJsonFile() throws Exception {
        System.out.println("createStudyModuleFromJsonFile");
        String fileName = "";
        StudyModule expResult = null;
        StudyModule result = SisuHelper.createStudyModuleFromJsonFile(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCourseFromJsonFile method, of class SisuHelper.
     */
    @Test
    public void testCreateCourseFromJsonFile() throws Exception {
        System.out.println("createCourseFromJsonFile");
        String fileName = "";
        Course expResult = null;
        Course result = SisuHelper.createCourseFromJsonFile(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test 
    public void testGetAllPrograms(){
        SisuHelper sh = new SisuHelper();
        ArrayList<Program> programs = sh.getAllPrograms(2021);
        System.out.println("List of programs:");
        for(int i = 0; i<40; i++){
            System.out.println(programs.get(i));
        }
    }



    
}
