package fi.tuni.prog3.sisu;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    String leftPanelColor = "#567B91;";
    String rightPanelColor = "#99C4DE;";
    String courseBoxColor = "#C9EAFF;";
    String courseButtonColor = "#DEC199;";
    
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
        
        
        
        stage.show();
        System.out.println("method start worked till the end");
    }

    public static void main(String[] args) {
        launch();
    }
    
    private VBox getCenterVbox() {
        //Creating an HBox.
        
        //DummyModule degree = getDummyDegreeStructure();
        SisuHelper sh = new SisuHelper();
        DegreeModule degree = sh.createStudyModule("tut-dp-g-1280");
        VBox centerVBox = new VBox(10);
        //centerVBox.setAlignment(Pos.TOP_CENTER);
        
        ScrollPane scrollPane = new ScrollPane();
        Node treeStructure = handleModule(degree);
        ((Region)treeStructure).setPadding(new Insets(0,0,0,0));
        scrollPane.setContent(treeStructure);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(20, 30, 0, 30));
        scrollPane.setStyle("-fx-background-color: " + rightPanelColor);
        
        
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
    private Node handleModule(DegreeModule module){
        
        
        if(module instanceof Course){
            return setSingleCourseBox((Course)module);
        }
        else{
            StudyModule moduleBlock = (StudyModule)module;
            ArrayList<DegreeModule> innerModules = moduleBlock.getStudyModulesAndCourses();
            
            VBox onePaneContent = new VBox();
            onePaneContent.setSpacing(2);
            onePaneContent.setStyle("-fx-background-color: " + rightPanelColor);
            
            TitledPane titledPane = new TitledPane(moduleBlock.getName()+ " 0/60", onePaneContent);
            titledPane.setId(moduleBlock.getId());
            titledPane.setPadding(new Insets(0,0,0,20));
            titledPane.setStyle("-fx-background-color: " + rightPanelColor);
            
            for(DegreeModule innerModule : innerModules){
                onePaneContent.getChildren().add(handleModule(innerModule));
            }
            return titledPane;
        }
    }   
    
    
    
    
    /*
    //Create tree structure of the course list
    private Node handleModule(DummyModule module){
        if(module instanceof DummyCourse){
            return setSingleCourseBox(module);
        }
        else{
            DummyBlock moduleBlock = (DummyBlock)module;
            ArrayList<DummyModule> innerModules = moduleBlock.getModules();
            
            VBox onePaneContent = new VBox();
            onePaneContent.setSpacing(2);
            onePaneContent.setStyle("-fx-background-color: " + rightPanelColor);
            
            TitledPane titledPane = new TitledPane(moduleBlock.toString() + " 0/60", onePaneContent);
            titledPane.setId(moduleBlock.getName());
            titledPane.setPadding(new Insets(0,0,0,20));
            titledPane.setStyle("-fx-background-color: " + rightPanelColor);
            
            for(DummyModule innerModule : innerModules){
                onePaneContent.getChildren().add(handleModule(innerModule));
            }
            return titledPane;
        }
    }*/
    
    private HBox setSingleCourseBox(Course module){
        
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.setPrefHeight(45);
        box.setPadding(new Insets(0, 15, 0, 15));
        box.setSpacing(10);
        box.setStyle( "-fx-background-radius: 5 5 5 5; -fx-background-color: " + courseBoxColor);

        Label courseNameLabel = new Label(module.getName());

        Region region = new Region();
        box.setHgrow(region, Priority.ALWAYS);

        VBox creditSection = new VBox();
        creditSection.setAlignment(Pos.CENTER);
        creditSection.setPadding(new Insets(0, 15, 0, 5));
        Label cr = new Label("cr");
        cr.setStyle("-fx-font-size:10;");
        Label creditLabel = new Label("2");//+((DummyCourse) module).getCredits());
        creditSection.getChildren().addAll(cr, creditLabel);


        Button addToMyCoursesBtn = new Button("Take course");
        addToMyCoursesBtn.setPrefWidth(150);
        addToMyCoursesBtn.setId(module.getName());
        addToMyCoursesBtn.setOnAction(takeCourseEventHandler);
        addToMyCoursesBtn.setStyle( "-fx-background-color: " + courseButtonColor);
        
        

        box.getChildren().addAll(courseNameLabel, region, creditSection, 
                addToMyCoursesBtn);
        return box;
    }
    
    
    private BorderPane setMenuPane() {
        //Creating a BorderPane for the left side.
        BorderPane menuPane = new BorderPane();
        
        menuPane.setPrefWidth(300);
        menuPane.setStyle("-fx-background-color: " + leftPanelColor);
        menuPane.setTop(headingPane());
        
        menuPane.setCenter(progressPane());
        
        menuPane.setBottom(menuBottomPane());
        menuPane.setAlignment(menuPane.getBottom(), Pos.BOTTOM_CENTER);
        
        menuPane.setMargin(menuPane.getTop(), new Insets(15, 10, 10, 10));
        menuPane.setMargin(menuPane.getBottom(), new Insets(10, 10, 10, 10));
        
        return menuPane;
    }
    private VBox headingPane(){
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER_LEFT);
        // add plan name
        Label planName = new Label("Your plan name");
        planName.setStyle("-fx-font-size: 16pt ;");
        
        // add degree heading label
        Label degNameLabel = new Label("Here will be the name of chosen degree");
        degNameLabel.setId("degNameLabel");
        degNameLabel.setWrapText(true);
        degNameLabel.setStyle("-fx-font-size: 20pt ;");
        
        //add year
        Label yearLabel = new Label("2023-2025");
        
        box.getChildren().addAll(planName, addVRegion(10) ,degNameLabel, yearLabel);
        return box;
    }
    
    private VBox progressPane(){
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER); 
        
        Label progressText = new Label("You did");
        Label progressValue = new Label("35/180");
        
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Completed", 35),
                new PieChart.Data("Not yet", 145));
        PieChart chart = new PieChart(pieChartData);
        chart.setStartAngle(90);
        chart.setPrefHeight(250);
        chart.setLegendVisible(false);
        chart.setLabelLineLength(10);
        
        box.getChildren().addAll(progressText, progressValue, chart);
        return box;
    }
    
    private VBox menuBottomPane(){
        VBox box = new VBox();
        box.setAlignment(Pos.BOTTOM_LEFT);
        
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
        box.setStyle("-fx-background-color: " + leftPanelColor);
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
        box.setStyle("-fx-background-color: " + rightPanelColor);
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
            String courseId = ((Control)btnPress.getSource()).getId();
            Button courseBtn = (Button)btnPress.getSource();
            
            if(courseBtn.getText().equals("Take course")){
                //add course
                courseBtn.getParent().setStyle("-fx-background-color: ffffff");
                courseBtn.setText("Drop");
                courseBtn.setPrefWidth(70);
                updateBlockHeading((TitledPane)courseBtn.getParent().getParent().getParent().getParent());
                
                Button putGradeBtn = new Button("Grade");
                putGradeBtn.setPrefWidth(70);
                putGradeBtn.setId(courseBtn.getId() + "_grade");
                putGradeBtn.setOnAction(gradeCourseEventHandler);
                ((Pane)courseBtn.getParent()).getChildren().add(putGradeBtn);
                
            }
            else{
                //remove course
                courseBtn.getParent().setStyle("-fx-background-color: " + courseBoxColor);
                courseBtn.setText("Take course");
                courseBtn.setPrefWidth(150);
                ((Pane)courseBtn.getParent()).getChildren().remove(((Pane)courseBtn.getParent()).getChildren().get(4));
            }
        }
    };
    
    private void updateBlockHeading(TitledPane pane){
        String oldHeading = pane.getText();
        String moduleName = oldHeading.substring(0, oldHeading.lastIndexOf(" "));
        pane.setText(moduleName + " 5/60");
    }
    
    EventHandler<ActionEvent> gradeCourseEventHandler = new EventHandler<ActionEvent>(){
        @Override 
        public void handle(ActionEvent btnPress) { 
        }
    };
}