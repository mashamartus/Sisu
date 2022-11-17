
package fi.tuni.prog3.sisu;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author mariia
 */
public class WelcomeWindow {
    public static int testMethod(){
        return 32;
    }
    
    EventHandler<ActionEvent> startPlanning = new EventHandler<ActionEvent>(){
        @Override 
        public void handle(ActionEvent btnPress) { 
            System.out.println("Start planning button pressed");
            Sisu.mainWindow.getRoot().requestFocus();
        }
    };
    
}
