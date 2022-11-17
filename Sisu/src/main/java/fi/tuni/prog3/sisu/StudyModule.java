package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;


/**
 * StudyModule Class
 * A study module, which usually consists of courses or submodules.
 * Study module completions can be included into a student’s university study record.
 * Study modules have information about e.g. study credits, grading and responsible persons.
 */
public class StudyModule extends DegreeModule {
    // Datastructure type can be changed!
    private HashMap<String, Course> courses = new HashMap<>();
    private HashMap<String, StudyModule> submodules = new HashMap<>();
    private boolean gradable;
    private String description;


    /**
     * A constructor for initializing the member variables.
     *
     * @param name        name of the Module.
     * @param id          id of the Module.
     * @param groupId     group id of the Module.
     * @param minCredits  minimum credits of the Module.
     * @param gradable    if the module is graded.
     * @param description description of the course.
     */
    public StudyModule(String name, String id, String groupId, int minCredits, boolean gradable, String description) {
        super(name, id, groupId, minCredits);
        this.gradable = gradable;
        this.description = description;
    }


    /**
     * Adds the param StudyModule to the submodules
     * @param studyModule studyModule to add.
     */
    public void addStudyModule(StudyModule studyModule) {
        submodules.put(studyModule.getId(), studyModule);
    }


    /**
     * Adds the param course to the list of courses
     * Overwrites if same id is present.
     * @param course to be added.
     */
    public void addCourse(Course course) {
        courses.put(course.getId(), course);
    }


    /**
     * Finds and returns a Course from stored courses, if it exists.
     * @param id the id of the Course to be found.
     * @return the found Course.
     * @throws NoSuchElementException if the Course is not found.
     */
    public Course getCourse(String id) {
        Course course = courses.get(id);
        if (course != null) {
            return course;
        }
        else throw new NoSuchElementException(String.format("Course id: %s, not found", id));
    }


    /**
     * Finds and returns a StudyModule from submodules, if it exists.
     * @param id the id of the StudyModule to be found.
     * @return the found StudyModule.
     * @throws NoSuchElementException if the StudyModule is not found.
     */
    public StudyModule getSubModules(String id) {
        StudyModule studyModule = submodules.get(id);
        if (studyModule != null) {
            return studyModule;
        }
        else throw new NoSuchElementException(String.format("StudyModule id: %s, not found", id));
    }


    /**
     * Returns as all courses in the Module as Arraylist.
     * @return Arraylist of Courses
     */
    public ArrayList<Course> getCourses() {
        return new ArrayList<>(courses.values());
    }


    /**
     * Returns as all submodules in the Module as Arraylist.
     * @return Arraylist of Courses
     */
    public ArrayList<StudyModule> getStudyModules() {
        return new ArrayList<>(submodules.values());
    }

    // TODO
    public void removeCourse() {

    }

    public boolean isGradable() {
        return gradable;
    }

    public String getDescription() {
        return description;
    }

}
