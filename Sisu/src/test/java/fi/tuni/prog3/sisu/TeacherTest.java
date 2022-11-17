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
public class TeacherTest {
    
    public TeacherTest() {
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
     * Test of getJsonObjectFromApi method, of class Teacher.
     */
    @Test
    public void testGetJsonObjectFromApi() {
        System.out.println("getJsonObjectFromApi");
        String urlString = "";
        Teacher instance = new Teacher();
        JsonObject expResult = null;
        JsonObject result = instance.getJsonObjectFromApi(urlString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readFromFile method, of class Teacher.
     */
    @Test
    public void testReadFromFile() throws Exception {
        System.out.println("readFromFile");
        String fileName = "";
        Teacher instance = new Teacher();
        boolean expResult = false;
        boolean result = instance.readFromFile(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeToFile method, of class Teacher.
     */
    @Test
    public void testWriteToFile() throws Exception {
        System.out.println("writeToFile");
        String fileName = "";
        Teacher instance = new Teacher();
        boolean expResult = false;
        boolean result = instance.writeToFile(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
