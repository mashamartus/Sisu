/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.sisu;

/**
 *
 * @author mariia
 */
public class StudentCourse extends Course{
    private String grade;
    private boolean completed;

    public StudentCourse(Course course) {
        super(course.getName(), course.getId(), "", course.getMinCredits(), course.isGradable(), course.getDescription(), course.getCode());
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
/*
    public String getGrade() {
        return grade;
    }*/
    
    

    public boolean isCompleted() {
        return completed;
    }
    
    
    
}
