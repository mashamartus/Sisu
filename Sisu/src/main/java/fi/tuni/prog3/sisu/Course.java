package fi.tuni.prog3.sisu;


/**
 * Course Class for a single course data.
 * Implements the abstract DegreeModule class.
 * Describes a course: e.g. the code, name, awarded study credits, course description, and so on.
 */
public class Course extends DegreeModule {
    private boolean passed;
    private int grade;
    private final boolean gradable;
    private final String description;

    /**
     * A constructor for initializing the member variables.
     *
     * @param name       name of the Course.
     * @param id         id of the Course.
     * @param groupId    group id of Course.
     * @param minCredits minimum credits of the Course.
     * @param gradable   does the Course give grades.
     * @param description the course description.
     */
    public Course(String name, String id, String groupId, int minCredits, boolean gradable, String description) {
        super(name, id, groupId, minCredits);
        this.gradable = gradable;
        this.grade = 0;
        this.passed = false;
        this.description = description;
    }

    /**
     * Marks to course as passed (true) or not (false).
     * @param passed boolean
     */
    public void setPassed(boolean passed) {
        this.passed = passed;
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

    /**
     * Returns the course description.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }
}
