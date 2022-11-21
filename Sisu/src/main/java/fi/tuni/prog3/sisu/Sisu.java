package fi.tuni.prog3.sisu;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


/**
 * JavaFX Sisu
 */
public class Sisu extends Application {

    static Stage theStage;
    static Scene welcomeWindow;
    static Scene mainWindow;
    static Student curStudent;
    
    //Student student = new Student("SomeId");
    
    @Override
    public void start(Stage stage) {
        
        theStage = stage;
        theStage.setTitle("Sisu - study planner");
        try{curStudent = new Student("Student1");}
            catch (FileNotFoundException e) {}
        
        String css = this.getClass().getResource("/style.css").toExternalForm(); 
        
        //define welcome window
        WelcomeScreen ws = new WelcomeScreen();
        welcomeWindow = ws.setWelcomeWindow();
        welcomeWindow.getStylesheets().add(css);
                
        stage.setScene(welcomeWindow);
        //stage.setResizable(false);
        
        //define main window
        
        MainWindow mw = new MainWindow();
        mainWindow = mw.setMainWindow();
        
        //stage.setScene(mainWindow);
         
        mainWindow.getStylesheets().add(css);
        
        
        
        stage.show();
        System.out.println("method start worked till the end");
    }

    public static void main(String[] args) {
        launch();
    }
 


    /**
     * Utility function - create an instance of region with defined 
     * vertical space.
     * @param spacing height of the region 
     * @return region of defined vertical height
     */
    public static Region addVRegion(double spacing){
        Region space = new Region();
        space.setPrefHeight(spacing);
        return space;
    }
 
}