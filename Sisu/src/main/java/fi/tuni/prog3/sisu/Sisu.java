package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


/**
 * JavaFX Sisu
 */
public class Sisu extends Application {

    @Override
    public void start(Stage stage) {
        
        //Creating a new BorderPane.
        BorderPane root = new BorderPane();
        
        
        root.setLeft(setMenuPane());
        //Adding HBox to the center of the BorderPane.
        root.setCenter(getCenterVbox());
        // make right pane zero
        
        root.setRight(null);
        
        Scene scene = new Scene(root, 1000, 700);
        stage.setTitle("SisuGUI");
        
        stage.setScene(scene);
        String css = this.getClass().getResource("/style.css").toExternalForm();  
        System.out.println("css string = " + css);
        scene.getStylesheets().add(css);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    private VBox getCenterVbox() {
        //Creating an HBox.
        
        DummyModule degree = getDummyDegreeStructure();
        
        VBox centerVBox = new VBox(10);
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(handleModule(degree));
        
        
        
        centerVBox.getChildren().add(scrollPane);
        /*
        for(int i = 0; i<4; i++){
            TitledPane titledPane = new TitledPane();
            titledPane.setId("Level_1_N_" + i);
            VBox onePaneContent = new VBox();
            titledPane.setContent(onePaneContent);
            for(int j=0; j<2; j++){
                HBox box = new HBox();
                Label moduleName = new Label("Module/course " + i + ", " + j);
                Button addToMyCoursesBtn = new Button("pick the course");
                box.getChildren().addAll(moduleName, addToMyCoursesBtn);
                onePaneContent.getChildren().add(box);
            }
            centerVBox.getChildren().add(titledPane);
        }*/
        /*
        TitledPane titledPane1 = new TitledPane();
        HBox titledPaneBox1 = new HBox();
        HBox titledPaneBox2 = new HBox();
        centerVBox.getChildren().add(titledPane1);

*/
        //Adding two VBox to the HBox.
        //centerVBox.getChildren().addAll(setMenuPane(), getRightVBox());
        
        return centerVBox;
    }
    
    // make method for recursive creation of the titled panes
    
    private Node handleCourse(DummyCourse course){
        HBox box = new HBox();
        Label moduleName = new Label(course.getName());
        Button addToMyCoursesBtn = new Button("pick the course");
        box.getChildren().addAll(moduleName, addToMyCoursesBtn);
        return box;
    }
    
    
    //Create tree structure of the course list
    private Node handleModule(DummyModule module){
        if(module instanceof DummyCourse){
            HBox box = new HBox();
            Label moduleName = new Label(module.toString());
            Button addToMyCoursesBtn = new Button("pick that course");
            box.getChildren().addAll(moduleName, addToMyCoursesBtn);
            System.out.println("Handle a course");
            return box;
        }
        else{
            System.out.println("Hurai, not a course but a module!");
            DummyBlock moduleBlock = (DummyBlock)module;
            ArrayList<DummyModule> innerModules = moduleBlock.getModules();
            
            VBox onePaneContent = new VBox();
            TitledPane titledPane = new TitledPane(moduleBlock.toString(), onePaneContent);
            titledPane.setId(moduleBlock.getName());
            
            for(DummyModule innerModule : innerModules){
                onePaneContent.getChildren().add(handleModule(innerModule));
            }
            return titledPane;
        }
    }
    
    
    
    private BorderPane setMenuPane() {
        //Creating a BorderPane for the left side.
        BorderPane menuPane = new BorderPane();
        
        menuPane.setPrefWidth(250);
        menuPane.setStyle("-fx-background-color: #8fc6fd;");
        menuPane.setTop(headingPane());
        
        menuPane.setBottom(getQuitButton());
        menuPane.setAlignment(menuPane.getBottom(), Pos.BOTTOM_CENTER);
        
        menuPane.setMargin(menuPane.getTop(), new Insets(15, 10, 10, 10));
        menuPane.setMargin(menuPane.getBottom(), new Insets(10, 10, 10, 10));
        
        return menuPane;
    }
    private VBox headingPane(){
        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        // add plan name text field
        TextField text = new TextField("Your plan name");
        
        // add degree heading label
        Label degNameLabel = new Label("Here will be the name of chosen degree");
        degNameLabel.setId("degNameLabel");
        degNameLabel.setWrapText(true);
        degNameLabel.setTextAlignment(TextAlignment.CENTER);
        degNameLabel.setFont(new Font(20)); 
        
        box.getChildren().addAll(text, degNameLabel);
        return box;
    }
    
    
    private Button getQuitButton() {
        //Creating a button.
        Button button = new Button("Quit");
        
        //Adding an event to the button to terminate the application.
        button.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
        
        
        return button;
    }
    
    private DummyModule getDummyDegreeStructure(){
        DummyBlock degree = new DummyBlock("Degree_name");
        for(int i = 0; i<3; i++){
            DummyBlock module = new DummyBlock("Module lvl 1 - " + i);
            for(int k = 0; k<2; k++){
                DummyBlock module2 = new DummyBlock("Module lvl 2 - " + i + "_" + k);
                    for(int p = 0; p<5; p++){
                        DummyCourse course = new DummyCourse("Course " + i + "_" + k + "_" + p);
                        module2.addModule(course);
                    }
                module.addModule(module2);
            }
            degree.addModule(module);
        }
        return degree;
    }
}