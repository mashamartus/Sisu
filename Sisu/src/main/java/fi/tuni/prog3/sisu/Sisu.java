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
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
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
        //root.setHgrow(root.getCenter(), Priority.ALWAYS);
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
        //centerVBox.setAlignment(Pos.TOP_CENTER);
        
        ScrollPane scrollPane = new ScrollPane();
        Node treeStructure = handleModule(degree);
        ((Region)treeStructure).setPadding(new Insets(0,0,0,0));
        scrollPane.setContent(treeStructure);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(0, 30, 0, 30));
        
        
        centerVBox.getChildren().add(scrollPane);
        
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
            return setSingleCourseBox(module);
        }
        else{
            DummyBlock moduleBlock = (DummyBlock)module;
            ArrayList<DummyModule> innerModules = moduleBlock.getModules();
            
            VBox onePaneContent = new VBox();
            onePaneContent.setSpacing(10);
            
            TitledPane titledPane = new TitledPane(moduleBlock.toString(), onePaneContent);
            titledPane.setId(moduleBlock.getName());
            titledPane.setPadding(new Insets(0,0,0,20));
            
            for(DummyModule innerModule : innerModules){
                onePaneContent.getChildren().add(handleModule(innerModule));
            }
            return titledPane;
        }
    }
    
    private HBox setSingleCourseBox(DummyModule module){
        
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);

        Label moduleName = new Label(module.toString());

        Region region = new Region();
        box.setHgrow(region, Priority.ALWAYS);

        VBox creditSection = new VBox();
        creditSection.setPadding(new Insets(0, 15, 0, 5));
        Label cr = new Label("cr");
        cr.setFont(new Font(8));
        Label creditLabel = new Label(""+((DummyCourse) module).getCredits());
        creditSection.getChildren().addAll(cr, creditLabel);


        Button addToMyCoursesBtn = new Button("Take course");
        addToMyCoursesBtn.setId(module.getName());

        box.getChildren().addAll(moduleName, region, creditSection, addToMyCoursesBtn);
        return box;
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