
package fi.tuni.prog3.sisu;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public final class StudentDataExport {
    
    ArrayList<Course> courses = new ArrayList <>();
    private Student student;
    public StudentDataExport(Student student) {
        this.student = student;
        courses = student.getTakenCourses();
        exportDataToWorkstation();
    }
    
    
    public void exportDataToWorkstation(){
        
        JsonObject json = new JsonObject(); 
        json.addProperty("name", student.getStudentName());
        json.addProperty("id", student.getStudentID());
        json.addProperty("degree program", student.getPlanName());
        json.addProperty("total credits", student.getPlannedCredits());
        JsonArray coursesCompleted = new JsonArray();
        for(int i = 0; i<courses.size(); i++){
            JsonObject oneCourse = new JsonObject();
            oneCourse.addProperty("name", courses.get(i).getName());
            oneCourse.addProperty("code", courses.get(i).getCode());
            oneCourse.addProperty("credits", courses.get(i).getMinCredits());
            oneCourse.addProperty("grade", courses.get(i).getGrade());
            coursesCompleted.add(oneCourse);
        }
        
        if(coursesCompleted.size()>0){
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
}
    
 
    


    

