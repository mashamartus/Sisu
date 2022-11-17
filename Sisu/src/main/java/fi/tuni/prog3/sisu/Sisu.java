package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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

    static Stage theStage;
    static Scene welcomeWindow;
    static Scene mainWindow;
    String leftPanelColor = "-fx-background-color: #8fc6fd;";
    String rightPanelColor = "-fx-background-color: #ffffff;";
    //Student student = new Student("SomeId");
    
    @Override
    public void start(Stage stage) {
        
        theStage = stage;
        
        String css = this.getClass().getResource("/style.css").toExternalForm(); 
        
        //define welcome window
        BorderPane welcomeRoot = new BorderPane();
        welcomeRoot.setLeft(setWelcomeWindowLeft());
        welcomeRoot.setCenter(setWelcomeWindowRight());
        welcomeWindow = new Scene(welcomeRoot, 600, 400);
        
        welcomeWindow.getStylesheets().add(css);
        //stage.setScene(welcomeWindow);
        //stage.setResizable(false);
        
        
        //define main window
        BorderPane root = new BorderPane();
        
        root.setLeft(setMenuPane());
        root.setCenter(getCenterVbox());
        
        mainWindow = new Scene(root, 1000, 700);
        stage.setTitle("Sisu - study planner");
        stage.setScene(mainWindow);
         
        mainWindow.getStylesheets().add(css);
        
        
        
        //stage.show();
        System.out.println("method start worked till the end");
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
        scrollPane.setPadding(new Insets(20, 30, 0, 30));
        scrollPane.setStyle(rightPanelColor);
        
        
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
            onePaneContent.setStyle(rightPanelColor);
            
            TitledPane titledPane = new TitledPane(moduleBlock.toString(), onePaneContent);
            titledPane.setId(moduleBlock.getName());
            titledPane.setPadding(new Insets(0,0,0,20));
            titledPane.setStyle(rightPanelColor);
            
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
        addToMyCoursesBtn.setOnAction(takeCourseEventHandler);

        box.getChildren().addAll(moduleName, region, creditSection, addToMyCoursesBtn);
        return box;
    }
    
    
    private BorderPane setMenuPane() {
        //Creating a BorderPane for the left side.
        BorderPane menuPane = new BorderPane();
        
        menuPane.setPrefWidth(250);
        menuPane.setStyle(leftPanelColor);
        menuPane.setTop(headingPane());
        
        menuPane.setCenter(progressPane());
        
        menuPane.setBottom(menuBottomPane());
        menuPane.setAlignment(menuPane.getBottom(), Pos.BOTTOM_CENTER);
        
        menuPane.setMargin(menuPane.getTop(), new Insets(15, 10, 10, 10));
        menuPane.setMargin(menuPane.getBottom(), new Insets(10, 10, 10, 10));
        
        return menuPane;
    }
    private VBox headingPane(){
        VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        box.setStyle(rightPanelColor);
        // add plan name text field
        TextField text = new TextField("Your plan name");
        
        // add degree heading label
        Label degNameLabel = new Label("Here will be the name of chosen degree");
        //degNameLabel.setStyle("-fx-background-color: transparent");
        degNameLabel.setId("degNameLabel");
        degNameLabel.setWrapText(true);
        degNameLabel.setTextAlignment(TextAlignment.CENTER);
        degNameLabel.setFont(new Font(20)); 
        
        box.getChildren().addAll(text, degNameLabel);
        return box;
    }
    
    private VBox progressPane(){
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        
        Label progressText = new Label("You did");
        Label progressValue = new Label("35/180");
        box.getChildren().addAll(progressText, progressValue);
        return box;
    }
    
    private VBox menuBottomPane(){
        VBox box = new VBox();
        box.setAlignment(Pos.BOTTOM_CENTER);
        
        Button writeDataBtn = new Button("Write to file");
        
        box.getChildren().addAll(writeDataBtn, addVRegion(15), getQuitButton());
        return box;
    }
    private Button getQuitButton(){
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
    
    private VBox setWelcomeWindowLeft(){
        VBox box = new VBox();
        box.setStyle(leftPanelColor);
        box.setAlignment(Pos.CENTER);
        box.setPrefWidth(220);
        box.setPrefHeight(100);
        Button continueFromPastBtn = new Button();
        continueFromPastBtn.setText("Continue from previous session");
        continueFromPastBtn.setFont(new Font(16));
        continueFromPastBtn.setTextAlignment(TextAlignment.CENTER);
        continueFromPastBtn.wrapTextProperty().setValue(true);
        continueFromPastBtn.setPrefWidth(150);
        box.getChildren().add(continueFromPastBtn);
        return box;
    }
    
    private VBox setWelcomeWindowRight(){
        VBox box = new VBox();
        box.setSpacing(1);
        box.setAlignment(Pos.CENTER);
        box.setStyle(rightPanelColor);
        Label heading = new Label("Create a new plan");
        heading.setFont(new Font(20));
        
        Label planNameLabel = new Label("Name your plan");
        TextField planNameField = new TextField();
        planNameField.setPrefWidth(200);
        planNameField.setMaxWidth(200);
        
        Label programLabel = new Label("Choose your degree");
        ChoiceBox degreeChoiceBox = new ChoiceBox();
        degreeChoiceBox.getItems().addAll("MSc of computer science", 
                "MSc in Building Technology", "BSc of historical dancing");
        
        Button goButton = new Button();
        goButton.setText("Start planning");
        goButton.setOnAction(startPlanningEventHandler);
        goButton.setMinWidth(100);
        goButton.setMinHeight(40);
        
        double addSpace = 20;
        
        box.getChildren().addAll(heading, addVRegion(addSpace), planNameLabel, 
                planNameField, addVRegion(addSpace),  programLabel,  
                degreeChoiceBox, addVRegion(addSpace), goButton);
             
        return box;
    }
    
    private Region addVRegion(double spacing){
        Region space = new Region();
        space.setPrefHeight(spacing);
        return space;
    }
    
    
    
    
    
    //Event handlers:
    
    EventHandler<ActionEvent> startPlanningEventHandler = new EventHandler<ActionEvent>(){
        @Override 
        public void handle(ActionEvent btnPress) { 
            System.out.println("Start planning button pressed");
            theStage.setScene(mainWindow);
            Sisu.mainWindow.getRoot().requestFocus();
        }
    };
    
    EventHandler<ActionEvent> takeCourseEventHandler = new EventHandler<ActionEvent>(){
        @Override 
        public void handle(ActionEvent btnPress) { 
            System.out.println("Take course button pressed");
            System.out.println(btnPress.getTarget());
            String courseId = "Got_this_id_from_course_button";//checkId((Button) btnPress.getSource());
            //student.takeCourse(courseId);
        }
    };

}