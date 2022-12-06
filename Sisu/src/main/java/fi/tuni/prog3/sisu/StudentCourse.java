package fi.tuni.prog3.sisu;

/**
 * Class represent a course with regard to the student. 
 * It extends class {@see fi.tuni.prog3.sisu.Course}, adding there 
 * student grade and completion status.
 * @author mariia
 */
public class StudentCourse extends Course{
    private String grade = "";
    private boolean completed = false;

    
    /**
     * student takes the course
     * @param course course data
     */
    public StudentCourse(Course course) {
        super(course.getNameEn(),course.getNameFi(), course.getId(), "", course.getMinCredits(), 
                course.isGradable(), course.getDescription(), course.getCode());
    }

    
    /**
     * Sets the course completed
     * @param completed is the course completed
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    
    /**
     * Sets the course grade.
     * @param grade int grade between 0-5 accepted.
     * @throws IllegalArgumentException if the grade is invalid.
     */
    public void setGrade(String grade) throws IllegalArgumentException {
        if (isValidGrade(grade)) {
            this.grade = grade;
        }
        else throw new IllegalArgumentException(String.format("Trying set course grade to: %s", grade));
    }
    
    public static boolean isValidGrade(String grade){
        return grade.equals("0") || grade.equals("1") || grade.equals("2") || 
                grade.equals("3") || grade.equals("4") || grade.equals("5") ||
                grade.equals("Pass");
    }
    
    /**
     * Returns the grade given to course.
     * @return grade
     */
    public String getGrade() {
        return grade;
    }
    
    
    /**
     * Returns is course completed
     * @return true or false values
     */
    public boolean isCompleted() {
        return completed;
    }
    
    
    
}
