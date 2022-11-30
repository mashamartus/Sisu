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
    
  

   
    /**
    * A constructor for initializing the member variables.
    * @param studentId student's id number
    */
    public Student(String studentID) throws FileNotFoundException {
        this.studentID = studentID;
    }

    
    /**
    * Returns studentID
    * @return studnentID as a string
    */
    public String getStudentID() {
        return studentID;
    }

    
    /**
    * Returns start year
    * @return start year as integer value
    */
    public Integer getStartYear() {
        return startYear;
    }

    
    /**
    * Returns Program which student is participating
    * @return Program
    */
    public Program getProgram() {
        return program;
    }

    
    /**
    * Returns student's name
    * @return name as a string
    */
    public String getStudentName() {
        return name;
    }

    
    /**
    * Returns student's selected language
    * @return language as a string
    */
    public String getLanguage() {
        return language;
    }

    
    /**
    * Set student's name
    * @param String student name
    */
    public void setName(String studentName) {
        this.name = studentName;
    }

    
    /**
    * Set student's plan name
    * @param String student plan name
    */
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    
    /**
    * Set student's start year
    * @param int start year
    */
    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    
    /**
    * Set student's selected program
    * @param Program selected by student
    */
    public void setProgram(Program program) {
        this.program = program;
    }

    
    /**
    * Set student's selected language
    * @param String language
    */
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

    
    /**
    * Set course as taken in taken courses list
    * @param Course taken course
    */
    public void takeCourse(Course course){
        StudentCourse stdCourse = new StudentCourse(course);
        if(!takenCourses.containsKey(course.getId())){
            takenCourses.put(course.getId(), stdCourse);
        }
        else{
            System.out.println("WARNING! That course is shown as already taken!\n"+
                    "Probably there is duplicate course in the degree program!");
        }
    }
    
    
    /**
    * drop a course from taken courses list
    * @param Course course to be dropped
    */
    public void dropCourse(String id){
        if(takenCourses.get(id) != null){
            takenCourses.remove(id);
        }
        else{
            System.out.println("There was no such course to remove");
        }
    }
    
    
    /**
    * set course completed and give a grade
    * @param String courseId
    * @param int grade
    */
    public void gradeCourse(String courseId, int grade){
        StudentCourse course = takenCourses.get(courseId);
        course.setCompleted(true);
        course.setGrade(grade);
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
    /* 
    public HashMap<String, DegreeProgram> getDegreePrograms() {
        return degreePrograms;
    }*/

    
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
    public int getCompletedCredits(){
        int completedCr = 0;
        for(StudentCourse course : this.getTakenCourses()){
            if(course.isCompleted()){
                completedCr += course.getMinCredits();
            }
        }
        return completedCr;
    }
    
    public double getAverageGrade(){
        int sum = 0;
        int coursesAmount = 0;
        System.out.println("Taken courses");
        this.printTakenCourses();
        for(StudentCourse course : this.getTakenCourses()){
            if(course.isCompleted() && course.isGradable() && (course.getGrade() != -1)){
                coursesAmount += 1;
                sum += course.getGrade();
            }
        }
        if(coursesAmount == 0) return 0;
        return sum/coursesAmount;
    }
    
    /**
     * To implement! - Return sum of credits of all courses chosen by student.
     * @return Return sum of credits of all courses chosen by student
     */
    public int getPlannedCredits(){
        
        int plannedCr = 0;
        for(StudentCourse course : this.getTakenCourses()){
            plannedCr += course.getMinCredits();
        }
        return plannedCr;
    }
    
    /**
     * To implement (may be another way of getting all program credits is 
     * needed) - returns minimum amount of credits obligatory for student to 
     * complete to graduate from the program
     * @return credit amount required by student program 
     */
    public int getProgramCredits(){
        return this.program.getMinCredits();
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
    
    
    /**
     * Export student's completed courses to local workstation as json file
     * @throw IOException filepath is incorrect
     */
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
    
    public void printTakenCourses(){
        System.out.println("\nStudent taken courses:");
        for(StudentCourse course : takenCourses.values()){
            System.out.println("  " + course.getNameEn());
        }
    }
    
    /**
     * Returns whether student have course taken.
     * @param courseId id of the course to check
     * @return true if student have a course with specified id, false otherwise
     */
    public boolean isCourseTaken(String courseId){
        return takenCourses.containsKey(courseId);
    }
}
