/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fi.tuni.prog3.sisu;

/**
 * Dummy class for defining GUI functionality, is leaf of the course tree.
 * @author mariia
 */
public class DummyCourse extends DummyModule{

    public DummyCourse(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "DummyCourse{" + super.getName() +'}';
    }
    
    
}
