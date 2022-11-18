/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import com.google.gson.JsonObject;
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
     * Test of createDegreeProgram method, of class SisuHelper.
     */
    @Test
    public void testCreateDegreeProgram() {
        System.out.println("createDegreeProgram");
        String groupId = "tut-dp-g-1280";
        SisuHelper instance = new SisuHelper();
        Boolean expResult = true;
        DegreeProgram result = instance.createDegreeProgram(groupId);
        assertEquals(expResult, true);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of createDegreeProgramFromExample method, of class SisuHelper.
     */
    @Test
    public void testCreateDegreeProgramFromExample() throws Exception {
        System.out.println("createDegreeProgramFromExample");
        Boolean expResult = true;
        DegreeProgram result = SisuHelper.createDegreeProgramFromExample();
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


    
}
