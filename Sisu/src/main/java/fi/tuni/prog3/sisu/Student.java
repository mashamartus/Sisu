package fi.tuni.prog3.sisu;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class concerning student plan data. During the planning 
 * it keep track of the user's actions like including  
 * the course into his plan, completing and putting grades or excluding it.
 * The class also have a methods to compute parameters about the plan 
 * concerning his degree program and picked courses. 
 * ({@link Student.getCompletedCredits getCompletedCredits}, 
 * {@link Student.getPlannedCredits getPlannedCredits} etc.)
 * It can use a plan already stored on user's pc. 
 * 
 * 
 */
public class Student implements iReadAndWriteToFile {
    private String studentID;
    private String name;
    private String planName;
    private Integer startYear;
    private Program program;
    private String language;
    private HashMap<String, DegreeProgram> degreePrograms = new HashMap<>();
    private HashMap<String, StudentCourse> takenCourses = new HashMap<>();
  

    public Student(String studentID) throws FileNotFoundException {
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public Program getProgram() {
        return program;
    }

    public String getStudentName() {
        return name;
    }

    public String getLanguage() {
        return language;
    }

    
    
    public void setName(String studentName) {
        this.name = studentName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public void setLanguage(String language) {
        if(language.equals("en") || language.equals("fi")){
            this.language = language;
        }
        else{
            System.out.println("Language name should be en or fi, but given " + 
                    language + "!\nLanguage is set as en");
            this.language = "en";
        }
    }
    
    /**
     * Return the name the user marked his study plan.
     * @return plan name
     */
    public String getPlanName() {
        return planName;
    }

    public void takeCourse(Course course){
        StudentCourse stdCourse = new StudentCourse(course);
        takenCourses.put(course.getId(), stdCourse);
    }
    
    /**
     * Return all courses the student take.
     * @return courses that student include in this plan.
     */
    public ArrayList<StudentCourse> getTakenCourses() {
        ArrayList<StudentCourse> courses = new ArrayList<>();
        courses.addAll(takenCourses.values());
        return courses;
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
        
        for(Course course : this.getTakenCourses()){
            
        }
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
        return  180;
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
    
    public void exportDataToWorkstation(){
        JsonObject json = new JsonObject(); 
        json.addProperty("name", getStudentName());
        json.addProperty("id", getStudentID());
        json.addProperty("degree program", getPlanName());
        json.addProperty("total credits", getPlannedCredits());
        JsonArray coursesCompleted = new JsonArray();
        ArrayList<StudentCourse> courses = new ArrayList<>();
        if(!getTakenCourses().isEmpty()){
             courses = getTakenCourses();
             for(int i = 0; i<courses.size(); i++){
                 JsonObject oneCourse = new JsonObject();
                oneCourse.addProperty("name", courses.get(i).getName());
                oneCourse.addProperty("code", courses.get(i).getCode());
                oneCourse.addProperty("credits", courses.get(i).getMinCredits());
                oneCourse.addProperty("grade", courses.get(i).getGrade());
                coursesCompleted.add(oneCourse);
             }
             json.add("courses", coursesCompleted.getAsJsonObject());
        }

        String jsonString = json.toString();

            try (PrintWriter file = new PrintWriter( new FileWriter("src/main/resources/studentCourses.json"))) {
                file.write(jsonString);
                file.close();
            }catch (IOException ex) {
                System.out.println("error: " + ex.toString());
        }
    }

    @Override
    public String toString() {
        return "Student{" + "studentID=" + studentID + ", planName=" + planName + 
                ", startYear=" + startYear + ", program=" + program + ", takenCourses=" + takenCourses + '}';
    }
    
    
}
