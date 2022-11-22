
package fi.tuni.prog3.sisu;

/**
 * Class represent a course with regard to the student. 
 * It extends class {@see fi.tuni.prog3.sisu.Course}, adding there 
 * student grade and completion status.
 * @author mariia
 */
public class StudentCourse extends Course{
    private int grade;
    private boolean completed;

    public StudentCourse(Course course) {
        super(course.getName(), course.getNameEn(), course.getNameFi(), course.getId(), "", course.getMinCredits(), 
                course.isGradable(), course.getDescription(), course.getCode());
    }

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
     * @return int grade
     */
    public int getGrade() {
        return grade;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    
    
}
