package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * DegreeProgram Class.
 * The root level of a degree structure.
 * Describes e.g. the name and extent (study credits) of the degree.
 */
public class DegreeProgram extends DegreeModule {
    // Datastructure type can be changed!
    private HashMap<String, StudyModule> studyModules = new HashMap<>();
    private final String description;
    private final String code;

    /**
     * A constructor for initializing the member variables.
     *
     * @param name        name of the DegreeProgram.
     * @param id          id of the DegreeProgram.
     * @param groupId     group id of the DegreeProgram.
     * @param minCredits  minimum credits of the DegreeProgram.
     * @param description description of the DegreeProgram.
     * @param code        short identifier code.
     */
    public DegreeProgram(String name, String id, String groupId, int minCredits, String description, String code) {
        super(name, id, groupId, minCredits);
        this.code = code;
        this.description = description;
    }

    /**
     * Adds a new StudyModule to the Degree.
     * @param studyModule to be added.
     *
     */
    public void addStudyModule(StudyModule studyModule) {
        studyModules.put(studyModule.getId(), studyModule);
    }

    /**
     * Finds and returns a StudyModule from stored modules, if it exists.
     * @param id the id of the StudyModule to be found.
     * @return the found StudyModule.
     * @throws NoSuchElementException if Module not found.
     */
    public StudyModule getStudyModule(String id) {
       StudyModule studyModule = studyModules.get(id);
       if (studyModule != null) {
           return studyModule;
       }
       else throw new NoSuchElementException(String.format("StudyModule id: %s, not found", id));
    }

    /**
     * Returns as all modules as Arraylist (so they can be sorted if needed).
     * @return Arraylist of StudyModules
     */
    public ArrayList<StudyModule> getStudyModules() {
        return new ArrayList<>(studyModules.values());
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }
}
