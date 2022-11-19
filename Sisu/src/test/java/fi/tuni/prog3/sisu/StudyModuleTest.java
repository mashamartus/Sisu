/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

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
public class StudyModuleTest {
    
    public StudyModuleTest() {
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
     * Test of addCourse method, of class StudyModule.
     */
    @Test
    public void testAddCourse() {
        System.out.println("addCourse");
        Course course = null;
        StudyModule instance = null;
        instance.addCourse(course);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCourse method, of class StudyModule.
     */
    @Test
    public void testGetCourse() {
        System.out.println("getCourse");
        String id = "";
        StudyModule instance = null;
        Course expResult = null;
        Course result = instance.getCourse(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCourses method, of class StudyModule.
     */
    @Test
    public void testGetCourses() {
        System.out.println("getCourses");
        StudyModule instance = null;
        ArrayList<Course> expResult = null;
        ArrayList<Course> result = instance.getCourses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCourse method, of class StudyModule.
     */
    @Test
    public void testRemoveCourse() {
        System.out.println("removeCourse");
        StudyModule instance = null;
        instance.removeCourse();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
        
    @Test
    public void testPrintModuleDetailed(){
        
        SisuHelper sh = new SisuHelper();
        StudyModule degree = sh.createStudyModule("tut-dp-g-1280");
        degree.printModuleDetailed(degree, "");
    }
    
}
