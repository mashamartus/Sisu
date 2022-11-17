/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import com.google.gson.JsonObject;
import java.util.HashMap;
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
public class StudentTest {
    
    public StudentTest() {
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
     * Test of getJsonObjectFromApi method, of class Student.
     */
   /* @Test
    public void testGetJsonObjectFromApi() {
        System.out.println("getJsonObjectFromApi");
        String urlString = "";
        Student instance = null;
        JsonObject expResult = null;
        JsonObject result = instance.getJsonObjectFromApi(urlString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of readFromFile method, of class Student.
     */
    @Test
    public void testReadFromFile() throws Exception {
        System.out.println("readFromFile");
        String fileName = "";
        Student instance = null;
        boolean expResult = false;
        boolean result = instance.readFromFile(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeToFile method, of class Student.
     */
    @Test
    public void testWriteToFile() throws Exception {
        System.out.println("writeToFile");
        String fileName = "";
        Student instance = null;
        boolean expResult = false;
        boolean result = instance.writeToFile(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudentID method, of class Student.
     */
    @Test
    public void testGetStudentID() {
        System.out.println("getStudentID");
        Student instance = null;
        String expResult = "";
        String result = instance.getStudentID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDegreePrograms method, of class Student.
     */
    @Test
    public void testGetDegreePrograms() {
        System.out.println("getDegreePrograms");
        Student instance = null;
        HashMap<String, DegreeProgram> expResult = null;
        HashMap<String, DegreeProgram> result = instance.getDegreePrograms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompletedCredits method, of class Student.
     */
    @Test
    public void testGetCompletedCredits() {
        System.out.println("getCompletedCredits");
        Student instance = null;
        double expResult = 0.0;
        double result = instance.getCompletedCredits();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlannedCredits method, of class Student.
     */
    @Test
    public void testGetPlannedCredits() {
        System.out.println("getPlannedCredits");
        Student instance = null;
        double expResult = 0.0;
        double result = instance.getPlannedCredits();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProgramCredits method, of class Student.
     */
    @Test
    public void testGetProgramCredits() {
        System.out.println("getProgramCredits");
        Student instance = null;
        double expResult = 0.0;
        double result = instance.getProgramCredits();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of takeCourse method, of class Student.
     */
    @Test
    public void testTakeCourse() {
        System.out.println("takeCourse");
        String courseId = "";
        Student instance = null;
        boolean expResult = false;
        boolean result = instance.takeCourse(courseId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
