
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 * Class represent a program object and contains just basic data about it 
 * and id to find it in Sisu.
 * It is used mainly on the welcome screen and to provide id 
 * for the chosen program
 * @author mariia
 */
public class Program {
    private String name;
    private String nameEn;
    private String nameFi;
    private String id;
    private int minCredits;
    private ArrayList<Integer> years;

    
    /**
     * Create the instance of Program.
     * @param name name of the program on the language of the program
     * @param nameEn English name of the program
     * @param nameFi Finnish name of the program
     * @param id by this groupId the program is known in Sisu system
     * @param minCredits credits required by program
     */
    public Program(String name, String nameEn, String nameFi, String id, int minCredits) {
        this.name = name;
        this.nameEn = nameEn;
        this.nameFi = nameFi;
        this.id = id;
        this.minCredits = minCredits;
        this.years = new ArrayList<>();
    }

    
    /**
     * Adds the param year to the list of years
     * @param year as a int value
     */
    public void addYear(int year){
        if(!years.contains(year)){
            years.add(year);
        }
        else{
            System.out.println("The year " + year + 
                    " is already exists for the program");
        }
    }
    
    
    /**
     * CAN THIS BE REMOVED?
     * @return name 
     */
    public String getName() {
        return name;
    }
    
    
    /**
     * Returns program's name in English
     * @return program's name in English as string
     */
    public String getNameEn() {
        return nameEn;
    }
    
    
    /**
     * Returns program's name in Finnish
     * @return program's name in Finnish as string
     */
    public String getNameFi() {
        return nameFi;
    }

    
    /**
     * Returns program's id
     * @return program's id as string
     */
    public String getId() {
        return id;
    }

    
    /**
     * Returns program's minumum required credits
     * @return program's minumum required credits as int value
     */
    public int getMinCredits() {
        return minCredits;
    }

    
    /**
     * Returns time period program is valid
     * @return Arraylist of years when program is valid 
     */
    public ArrayList<Integer> getYears() {
        ArrayList<Integer> res = new ArrayList<>(years);
        return res;
    }

    
    /**
    * Returns program information 
    * @return program information as a string
    */
    @Override
    public String toString() {
        return "Program{" + "name=" + name + ", id=" + id + ", minCredits=" + minCredits + ", years=" + years + '}';
    }
    
    
    
}
