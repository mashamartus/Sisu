package fi.tuni.prog3.sisu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * 
 * (MMa: correct me if our concept is another one) 
 * Class provide functionality for the user course planning. 
 * It can read already saved on pc study plan. Then during the planning 
 * it keep track of the user's actions like including or excluding 
 * the course into/from his plan and putting grades. 
 * The class also have a methods to compute parameters about the plan 
 * concerning his degree program and picked courses 
 * ({@link Student.getCompletedCredits getCompletedCredits}, 
 * {@link Student.getPlannedCredits getPlannedCredits} etc.)
 * 
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
    
    /**
     * To implement! Method make changes in database when user add a course 
     * to his study plan. 
     * @param courseId (to be decided about the format!) Identificator of 
     * the course student decide to take.
     * @return (in the end maybe we don't need a return) 
     * was modifications to the database successfull or not. 
     * For example if that ID don't exist in the degree 
     * (although that shouldn't happen, in theory:)
     */
    public boolean takeCourse(String courseId){
        return true;
    }
}
