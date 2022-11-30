package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;


/**
 * StudyModule Class
 * A study module groups courses or other study modules as submodules together into groups.
 * Study module completions can be included into a student’s university study record.
 * Study modules have information about e.g. code, study credits and grading.
 */
public class StudyModule extends DegreeModule {
    private HashMap<String, Course> courses = new HashMap<>();
    private HashMap<String, StudyModule> submodules = new HashMap<>();   
    private StudyModule parent;
    private boolean gradable;
    private String description;
    private String code;


    /**
     * A constructor for initializing the member variables.
     *
     * @param nameEn      name of the Module in English.
     * @param nameFi      name of the Module in Finnish.
     * @param id          id of the Module.
     * @param groupId     group id of the Module.
     * @param minCredits  minimum credits of the Module.
     * @param gradable    if the module is graded.
     * @param description description of the Module.
     * @param code        short code string of the module
     */
    public StudyModule(String nameEn, String nameFi, String id, String groupId, int minCredits,
                       boolean gradable, String description, String code) {
        super(nameEn, nameFi, id, groupId, minCredits);
        this.gradable = gradable;
        this.description = description;
        this.code = code;
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
     * Adds the param parent to the study module
     * Overwrites if same id is present.
     * @param parent to be added
     */
     public void setParent(StudyModule parent) {
        this.parent = parent;
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
    public StudyModule getSubModule(String id) {
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
    
    
    /**
     * Returns as all submodules and courses in the Module as Arraylist.
     * @return Arraylist of submodules and Courses
     */
    @Override
    public ArrayList<DegreeModule> getStudyModulesAndCourses(){
        ArrayList<DegreeModule> allSubelements = new ArrayList<>(courses.values());
        ArrayList<DegreeModule> submodulesList = new ArrayList<>(submodules.values());
        allSubelements.addAll(submodulesList);
        return allSubelements;
    }

    
    /**
     * Returns as all submodules and courses in the Module as Arraylist.
     * @return Arraylist of submodules and Courses
     */
    public static void printModuleDetailed(StudyModule printedModule, String tab){
        for(DegreeModule subModule : printedModule.getStudyModulesAndCourses()){
            if(subModule instanceof Course){
                System.out.println(tab + "Course " + subModule.getName());
            }
            else{
                if(subModule instanceof StudyModule){
                    System.out.println(tab + "Module " + subModule.getName());
                    printModuleDetailed((StudyModule)subModule, tab + "  ");
                }
                else{
                    System.out.println("WRONG :( The submodule is neither of Course or StudyModule!");
                }
            }
        }
    }
    
    
    /**
     * Returns is study modules gradable
     * @return true / false is study module gradable
     */
    public boolean isGradable() {
        return gradable;
    }

    
    /**
     * Returns description of study module
     * @return study module description as a string
     */
    public String getDescription() {
        return description;
    }

    
    /**
     * Returns code of study module
     * @return study module code as a string
     */
    public String getCode() {
        return code;
    }

    
    /**
     * Remove course from courses 
     */
    public void removeCourse(String id) {
        courses.remove(id);
    }
  
    
    /**
     * Returns a parent of one study module
     * @return parent StudyModule of StudyModule
     */
    public StudyModule getParent() {
        return parent;
    }
    
    
    /**
     * Returns a parent id of one study modules
     * @return parent StudyModule id
     */
    public String getParentID() {
        return parent.getId();
    }
}
