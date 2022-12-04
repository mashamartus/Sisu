package fi.tuni.prog3.sisu;

/**
 * Class represent a course with regard to the student. 
 * It extends class {@see fi.tuni.prog3.sisu.Course}, adding there 
 * student grade and completion status.
 * @author mariia
 */
public class StudentCourse extends Course{
    private int grade = -1;
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
    public void setGrade(int grade) throws IllegalArgumentException {
        if (grade >= 0 && grade <= 5) {
            this.grade = grade;
        }
        else throw new IllegalArgumentException(String.format("Trying set course grade to: %d", grade));
    }

    
    /**
     * Returns the grade given to course.
     * @return grade
     */
    public int getGrade() {
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
