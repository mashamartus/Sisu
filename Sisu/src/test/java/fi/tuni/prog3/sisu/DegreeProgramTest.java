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
public class DegreeProgramTest {
    
    public DegreeProgramTest() {
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
     * Test of addStudyModule method, of class DegreeProgram.
     */
    @Test
    public void testAddStudyModule() {
        System.out.println("addStudyModule");
        StudyModule studyModule = null;
        DegreeProgram instance = null;
        instance.addStudyModule(studyModule);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudyModule method, of class DegreeProgram.
     */
    @Test
    public void testGetStudyModule() {
        System.out.println("getStudyModule");
        String id = "";
        DegreeProgram instance = null;
        StudyModule expResult = null;
        StudyModule result = instance.getStudyModule(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudyModules method, of class DegreeProgram.
     */
    @Test
    public void testGetStudyModules() {
        System.out.println("getStudyModules");
        DegreeProgram instance = null;
        ArrayList<StudyModule> expResult = null;
        ArrayList<StudyModule> result = instance.getStudyModules();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
