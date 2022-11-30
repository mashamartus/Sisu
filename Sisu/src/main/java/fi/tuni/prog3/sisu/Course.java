package fi.tuni.prog3.sisu;

import java.util.ArrayList;


/**
 * Course Class for a single course data.
 * Implements the abstract DegreeModule class.
 * Describes a course: e.g. the code, name, awarded study credits, course description, and so on.
 */
public class Course extends DegreeModule {
    private boolean isPassed;
    //private int grade;
    private final boolean gradable;
    private final String description;
    private final String code;
    private StudyModule parent;


    /**
     * A constructor for initializing the member variables.
     *
     * @param nameEn name of the course in English
     * @param nameFi name of the course in Finnish
     * @param id          id of the Course.
     * @param groupId     group id of Course.
     * @param minCredits  minimum credits of the Course.
     * @param gradable    does the Course give grades.
     * @param description the course description.
     * @param code        the short string course identifier.
     */
    public Course(String nameEn, String nameFi, String id, String groupId, int minCredits,
                  boolean gradable, String description, String code) {
        super(nameEn, nameFi, id, groupId, minCredits);
        this.gradable = gradable;
        //this.grade = 0;
        this.isPassed = false;
        this.description = description;
        this.code = code;
    }

    /**
     * Marks to course as passed (true) or not (false).
     * @param isPassed boolean
     */
    public void setPassed(boolean isPassed) {
        this.isPassed = isPassed;
    }

    
    public void setParent(StudyModule parent) {
        this.parent = parent;
    }

    /**
     * Returns if the course is passed.
     * @return true if it is else false
     */
    public boolean isPassed() {
        return isPassed;
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

    /**
     * Returns the short course identifier code.
     * @return the code
     */
    public String getCode() {
        return code;
    }
    
    
    /**
     * Returns the parent of the course
     * @return the parent (StudyModule) of the course
     */
    public StudyModule getParent() {
        return parent;
    }
    
    
    /**
     * Returns the parent's id of the course
     * @return the parent's (StudyModule) id of the course
     */
    public String getParentID() {
        return parent.getId();
    }
   

}
