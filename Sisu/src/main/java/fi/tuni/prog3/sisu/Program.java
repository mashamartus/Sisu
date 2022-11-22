
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 * Class represent a program object and contains just basic data about it 
 * and id to find it in Sisu.
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

    public void addYear(int year){
        if(!years.contains(year)){
            years.add(year);
        }
        else{
            System.out.println("The year " + year + 
                    " is already exists for the program");
        }
    }
    
    public String getName() {
        return name;
    }
    
    public String getNameEn() {
        return nameEn;
    }
    
    public String getNameFi() {
        return nameFi;
    }

    public String getId() {
        return id;
    }

    public int getMinCredits() {
        return minCredits;
    }

    public ArrayList<Integer> getYears() {
        ArrayList<Integer> res = new ArrayList<>(years);
        return res;
    }

    @Override
    public String toString() {
        return "Program{" + "name=" + name + ", id=" + id + ", minCredits=" + minCredits + ", years=" + years + '}';
    }
    
    
    
}
