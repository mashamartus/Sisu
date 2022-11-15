package fi.tuni.prog3.sisu;


/**
 * Course Class for a single course data.
 * Implements the abstract DegreeModule class.
 */
public class Course extends DegreeModule {
    private boolean passed;
    private int grade;
    private final boolean gradable;

    /**
     * A constructor for initializing the member variables.
     *
     * @param gradable   does the Course give grades.
     * @param name       name of the Course.
     * @param id         id of the Course.
     * @param groupId    group id of Course.
     * @param minCredits minimum credits of the Course.
     */
    public Course(boolean gradable, String name, String id, String groupId, int minCredits) {
        super(name, id, groupId, minCredits);
        this.gradable = gradable;
        this.grade = 0;
        this.passed = false;
    }

    /**
     * Sets the course grade and marks it as passed.
     * if the course is not gradeble, mark it as passed and ignore the grade.
     * @param grade int grade between 1-5 accepted.
     * @throws IllegalArgumentException if the grade is invalid.
     */
    public void setGrade(int grade) throws IllegalArgumentException {
        if (!gradable) passed = true;
        else if (grade > 0 && grade <= 5) {
            passed = true;
            this.grade = grade;
        }
        else throw new IllegalArgumentException(String.format("Trying set course grade to: %d", grade));
    }

    /**
     * Returns the grade given to course
     * @return int grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * Returns if the course is passed.
     * @return true if it is else false
     */
    public boolean isPassed() {
        return passed;
    }

    /**
     * Returns if the course is gradable (= can have grade).
     * @return true if it is else false
     */
    public boolean isGradable() {
        return gradable;
    }
}
