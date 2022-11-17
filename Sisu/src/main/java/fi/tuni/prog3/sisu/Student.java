package fi.tuni.prog3.sisu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * TODO
 * Student class
 */
public class Student implements iReadAndWriteToFile {
    private String studentID;
    private HashMap<String, DegreeProgram> degreePrograms = new HashMap<>();

    public Student(String studentID) throws FileNotFoundException {
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public HashMap<String, DegreeProgram> getDegreePrograms() {
        return degreePrograms;
    }

    @Override
    public boolean readFromFile(String fileName) throws FileNotFoundException {
        return false;
    }

    @Override
    public boolean writeToFile(String fileName) throws IOException {
        return false;
    }
    
    /**
     * To implement! - Return the amount of credits completed by student.
     * @return return sum of credits of all completed courses
     */
    public double getCompletedCredits(){
        return 35;
    }
    
    /**
     * To implement! - Return sum of credits of all courses chosen by student.
     * @return Return sum of credits of all courses chosen by student
     */
    public double getPlannedCredits(){
        return 135;
    }
    
    /**
     * To implement (may be another way of getting all program credits is 
     * needed) - returns minimum amount of credits obligatory for student to 
     * complete to graduate from the program
     * @return credit amount required by student program 
     */
    public double getProgramCredits(){
        return 180;
    }
}
