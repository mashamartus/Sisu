/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

    /**
     * Test of getStartYear method, of class Student.
     */
    @Test
    public void testGetStartYear() {
        System.out.println("getStartYear");
        Student instance = null;
        Integer expResult = null;
        Integer result = instance.getStartYear();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProgram method, of class Student.
     */
    @Test
    public void testGetProgram() {
        System.out.println("getProgram");
        Student instance = null;
        Program expResult = null;
        Program result = instance.getProgram();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudentName method, of class Student.
     */
    @Test
    public void testGetStudentName() {
        System.out.println("getStudentName");
        Student instance = null;
        String expResult = "";
        String result = instance.getStudentName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Student.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String studentName = "";
        Student instance = null;
        instance.setName(studentName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlanName method, of class Student.
     */
    @Test
    public void testSetPlanName() {
        System.out.println("setPlanName");
        String planName = "";
        Student instance = null;
        instance.setPlanName(planName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStartYear method, of class Student.
     */
    @Test
    public void testSetStartYear() {
        System.out.println("setStartYear");
        Integer startYear = null;
        Student instance = null;
        instance.setStartYear(startYear);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProgram method, of class Student.
     */
    @Test
    public void testSetProgram() {
        System.out.println("setProgram");
        Program program = null;
        Student instance = null;
        instance.setProgram(program);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlanName method, of class Student.
     */
    @Test
    public void testGetPlanName() {
        System.out.println("getPlanName");
        Student instance = null;
        String expResult = "";
        String result = instance.getPlanName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of takeCourse method, of class Student.
     */
    @Test
    public void testTakeCourse_Course() {
        System.out.println("takeCourse");
        Course course = null;
        Student instance = null;
        instance.takeCourse(course);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTakenCourses method, of class Student.
     */
    @Test
    public void testGetTakenCourses() {
        System.out.println("getTakenCourses");
        Student instance = null;
        ArrayList<StudentCourse> expResult = null;
        ArrayList<StudentCourse> result = instance.getTakenCourses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDegreePrograms method, of class Student.
     */
   /* @Test
    public void testGetDegreePrograms() {
        System.out.println("getDegreePrograms");
        Student instance = null;
        HashMap<String, DegreeProgram> expResult = null;
        HashMap<String, DegreeProgram> result = instance.getDegreePrograms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of takeCourse method, of class Student.
     */
    @Test
    public void testTakeCourse_String() {
        System.out.println("takeCourse");
        String courseId = "";
        Student instance = null;
        boolean expResult = false;
        boolean result = instance.takeCourse(courseId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportDataToWorkstation method, of class Student.
     */
    @Test
    public void testExportDataToWorkstation() throws FileNotFoundException {
        
        System.out.println("exportDataToWorkstation");
        
        Program program = new Program("Master's Programme in Management and Information Technology","Master's Programme in Management and Information Technology","Johtamisen ja tietotekniikan DI-ohjelma", "tut-dp-g-1280", 120);
        
        Student instance = new Student("H1234");
        instance.setName("Maija");
        instance.setPlanName("My Plan");
        instance.setStartYear(2021);
        instance.setProgram(program);

        instance.exportDataToWorkstation();
        
    }

    /**
     * Test of toString method, of class Student.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Student instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
