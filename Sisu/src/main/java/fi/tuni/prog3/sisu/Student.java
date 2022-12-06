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
 * ({link Student.getCompletedCredits getCompletedCredits}, 
 * {link Student.getPlannedCredits getPlannedCredits} etc.)
 * It can use a plan already stored on user's pc. 
 * 
 * 
 */
public class Student {
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
    * @param studentID student's id number
    * @throws FileNotFoundException if student not found
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
    * @param studentName inputted student name
    */
    public void setName(String studentName) {
        this.name = studentName;
    }

    /**
    * Set student's plan name
    * @param planName inputted plan name
    */
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    
    /**
    * Set student's start year
    * @param startYear selected start year
    */
    public void setStartYear(Integer startYear) {
        this.startYear = startYear;
    }

    
    /**
    * Set student's selected program
    * @param program selected by student
    */
    public void setProgram(Program program) {
        this.program = program;
    }

    
    /**
    * Set student's selected language
    * @param language selected by student
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
    * Method make changes in database when user add a course 
     * to his study plan. Set course as taken in taken courses list.
    * @param course taken course object
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
    * @param id course id to be dropped
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
    * @param courseId course id
    * @param grade course grade
    */
    public void gradeCourse(String courseId, String grade){
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

    
    public boolean readFromFile(String fileName) throws FileNotFoundException {
        return false;
    }

    
    /**
     * Return the amount of credits completed by student.
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
    
    
    /**
     * Return the the average grade of the student.
     * @return average grade as double value
     */
    public double getAverageGrade(){
        int sum = 0;
        int coursesAmount = 0;
        System.out.println("Taken courses");
        for(StudentCourse course : this.getTakenCourses()){
            if(course.isCompleted() && course.isGradable() && (!course.getGrade().equals(""))){
                coursesAmount += 1;
                sum += Integer.parseInt(course.getGrade());
            }
        }
        if(coursesAmount == 0) return 0;
        return sum/coursesAmount;
    }
    
    /**
     * Return sum of credits of all courses chosen by student.
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
     * Returns minimum amount of credits obligatory for student to 
     * complete to graduate from the program
     * @return credit amount required by student program 
     */
    public int getProgramCredits(){
        return this.program.getMinCredits();
    }

    
    
    /**
     * Export student's completed courses to local workstation as json file
     * IOException filepath is incorrect
     */
    public void exportDataToWorkstation(String filename){
        JsonObject json = new JsonObject(); 
        json.addProperty("name", getStudentName());
        json.addProperty("id", getStudentID());
        json.addProperty("plan name", getPlanName());
        json.addProperty("start year", getStartYear());
        json.addProperty("degree program eng", getProgram().getNameEn());
        json.addProperty("degree program fi", getProgram().getNameFi());
        json.addProperty("degree program id", getProgram().getId());
        json.addProperty("total program credits", getProgram().getMinCredits());
        json.addProperty("total planned credits", getPlannedCredits());
        JsonArray coursesCompleted = new JsonArray();
        if(!getTakenCourses().isEmpty()){
             for(int i = 0; i<getTakenCourses().size(); i++){
                 JsonObject oneCourse = new JsonObject();
                 oneCourse.addProperty("nameEn", getTakenCourses().get(i).getNameEn());
                 oneCourse.addProperty("nameFi", getTakenCourses().get(i).getNameFi());
                 oneCourse.addProperty("id", getTakenCourses().get(i).getId());
                 oneCourse.addProperty("groupId", getTakenCourses().get(i).getGroupId());
                 oneCourse.addProperty("code", getTakenCourses().get(i).getCode());
                 oneCourse.addProperty("credits", getTakenCourses().get(i).getMinCredits());
                 oneCourse.addProperty("gradable", getTakenCourses().get(i).isGradable());
                 oneCourse.addProperty("grade", getTakenCourses().get(i).getGrade());
                 oneCourse.addProperty("description", getTakenCourses().get(i).getDescription());
                coursesCompleted.add(oneCourse);
             }
             json.add("courses", coursesCompleted);
        }

        String jsonString = json.toString();
            
            try (PrintWriter file = new PrintWriter( new FileWriter("src/main/resources/"+filename+".json"))) {
                file.write(jsonString);
                file.close();
                System.out.println("Writing to file should be ok");
            }catch (IOException ex) {
                System.out.println("error: " + ex.toString());
        }
    }

    @Override
    public String toString() {
        String text = "Student: " + "studentID=" + studentID + ", \nplanName=" + planName + 
                ", \nstartYear=" + startYear + ", \nprogram=" + program + ", \ntakenCourses=" + takenCourses + '}';
        
        return text;
    }
    
    public void printStudent(){
        System.out.println("Student:");
        System.out.println("id: " + studentID);
        System.out.println("Plan name: " + planName);
        System.out.println("Program: " + program);
        for(StudentCourse course : takenCourses.values()){
            System.out.println("   Course: " + course.getNameEn());
            System.out.println("      grade: " + course.getGrade() + " completed: " + course.isCompleted());
        }
        System.out.println();
    }
    
    /**
     * printTakenCourses
     */
    public void printTakenCourses(){
        System.out.println("\nStudent taken courses:");
        for(StudentCourse course : takenCourses.values()){
            System.out.println("  " + course.getNameEn() + "ID: " + course.getId());
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
