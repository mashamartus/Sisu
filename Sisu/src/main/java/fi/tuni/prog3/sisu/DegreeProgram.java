package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * A class for whole DegreeProgram. Keeps store of
 * every study module that belongs to a degree.
 */
public class DegreeProgram extends DegreeModule {
    // Datastructure type can be changed!
    private HashMap<String, StudyModule> studyModules = new HashMap<>();

    /**
     * A constructor for initializing the member variables.
     *
     * @param name       name of the DegreeProgram.
     * @param id         id of the DegreeProgram.
     * @param groupId    group id of the DegreeProgram.
     * @param minCredits minimum credits of the DegreeProgram.
     */
    public DegreeProgram(String name, String id, String groupId, int minCredits) {
        super(name, id, groupId, minCredits);
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
}
