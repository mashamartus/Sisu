/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 *An abstract class for storing information on Modules and Courses.
 */
public abstract class DegreeModule {


    private String name;
    private String nameFi;
    private String nameEn;
    private String id;
    private String groupId;
    private int minCredits;
    
    /**
     * A constructor for initializing the member variables.
     * @param name name of the Module or Course.
     * @param id id of the Module or Course.
     * @param groupId group id of the Module or Course.
     * @param minCredits minimum credits of the Module or Course.
     */
    public DegreeModule(String name, String nameEn, String nameFi, String id, String groupId, 
            int minCredits) {
        
        this.name = name;
        this.nameEn = nameEn;
        this.nameFi = nameFi;
        this.id = id;
        this.groupId = groupId;
        this.minCredits = minCredits;
    }

   
    
    /**
     * Returns the name of the Module or Course.
     * @return name of the Module or Course.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the nameFi of the Module or Course.
     * @return finnish name of the Module or Course.
     */
     public String getNameFi() {
        return nameFi;
    }

     /**
     * Returns the nameEn of the Module or Course.
     * @return english name of the Module or Course.
     */
    public String getNameEn() {
        return nameEn;
    }
    
    /**
     * Returns the id of the Module or Course.
     * @return id of the Module or Course.
     */
    public String getId() {
        return this.id;
    }
    
    /**
     * Returns the group id of the Module or Course.
     * @return group id of the Module or Course.
     */
    public String getGroupId() {
        return this.groupId;
    }
    
    /**
     * Returns the minimum credits of the Module or Course.
     * @return minimum credits of the Module or Course.
     */
    public int getMinCredits() {
        return this.minCredits;
    }
    /**
     * This method needed to make recursive algorithms on degreeModules work 
     * and should be overridden in StudyModule to return all children. 
     * @return null
     */
    public ArrayList<DegreeModule> getStudyModulesAndCourses(){
        return null;
    }
}
