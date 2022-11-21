
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 * Class represent a program object and contains just basic data about it 
 * and id to find it in Sisu.
 * @author mariia
 */
public class Program {
    private String name;
    private String id;
    private int minCredits;
    private ArrayList<Integer> years;

    public Program(String name, String id, int minCredits) {
        this.name = name;
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
