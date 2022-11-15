
package fi.tuni.prog3.sisu;

import java.util.ArrayList;

/**
 * Subclass of DummyModule which can contain other DummyBlocks inside
 * @author mariia
 */
public class DummyBlock extends DummyModule{
    
    protected ArrayList<DummyModule> modules;

    public DummyBlock(String name) {
        super(name);
        this.modules = new ArrayList<>();
    }

    
    public boolean addModule (DummyModule module){
        modules.add(module);
        return true;
    }
    
    public ArrayList<DummyModule> getModules(){
        ArrayList<DummyModule> res = modules;
        return res;
    }

    @Override
    public String toString() {
        return "DummyBlock{" + super.getName() + ", " + modules.size() +  " inner modules}";
    }
    
    

}
