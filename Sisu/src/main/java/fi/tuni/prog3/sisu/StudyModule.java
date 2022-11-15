package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class StudyModule extends DegreeModule {
    // Datastructure type can be changed!
    private HashMap<String, Course> courses = new HashMap<>();


    /**
     * A constructor for initializing the member variables.
     *
     * @param name       name of the Module.
     * @param id         id of the Module.
     * @param groupId    group id of the Module.
     * @param minCredits minimum credits of the Module.
     */
    public StudyModule(String name, String id, String groupId, int minCredits) {
        super(name, id, groupId, minCredits);
    }

    /**
     * Adds the param course to the list of courses
     * Overwrites if same id is present maybe need to change this?
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
     * Returns as all courses in the Module as Arraylist (so they can be sorted if needed).
     * @return Arraylist of Courses
     */
    public ArrayList<Course> getCourses() {
        return new ArrayList<>(courses.values());
    }

    // TODO
    public void removeCourse() {

    }
}
