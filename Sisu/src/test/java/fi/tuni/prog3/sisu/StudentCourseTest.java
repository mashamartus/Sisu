/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package fi.tuni.prog3.sisu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mariia
 */
public class StudentCourseTest {
    
    public StudentCourseTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testSetCompleted() {
        System.out.println("setCompleted");
        boolean completed = false;
        StudentCourse instance = null;
        instance.setCompleted(completed);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetGrade() {
        System.out.println("setGrade");
        int grade = 0;
        StudentCourse instance = null;
        instance.setGrade(grade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetGrade() {
        System.out.println("getGrade");
        StudentCourse instance = null;
        int expResult = 0;
        int result = instance.getGrade();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testIsCompleted() {
        System.out.println("isCompleted");
        StudentCourse instance = null;
        boolean expResult = false;
        boolean result = instance.isCompleted();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    
}
