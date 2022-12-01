package fi.tuni.prog3.sisu;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
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
    static String css;
    /**
     * It keeps track of all courses which was read from the degree. 
     */
    static HashMap<String, Course> allCourses = new HashMap<>();
    /**
     * It keeps all HBoxes for the single course. 
     * It is used to get the course box by course id
     */
    static HashMap<String, HBox> allCourseBoxes = new HashMap<>();
    
    //Student student = new Student("SomeId");
    
    /**
     * default construct for Sisu
     */
    public Sisu(){};
    
    @Override
    public void start(Stage stage) {
        
        theStage = stage;
        theStage.setTitle("Sisu - study planner");
        try{curStudent = new Student("Student1");}
            catch (FileNotFoundException e) {}
        
        css = this.getClass().getResource("/style.css").toExternalForm(); 
        
        //define welcome window
        WelcomeScreen ws = new WelcomeScreen();
        welcomeWindow = ws.setWelcomeWindow();
        welcomeWindow.getStylesheets().add(css);
                
        stage.setScene(welcomeWindow);
        //stage.setResizable(false);
        
        
        
        
        stage.show();
        System.out.println("method start worked till the end");
    }

    /**
     * program main
     * @param args input arguments
     */
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