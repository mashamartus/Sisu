
package fi.tuni.prog3.sisu;

import static fi.tuni.prog3.sisu.Sisu.addVRegion;
import static fi.tuni.prog3.sisu.Sisu.mainWindow;
import static fi.tuni.prog3.sisu.Sisu.theStage;
import static fi.tuni.prog3.sisu.Sisu.welcomeWindow;
import java.util.ArrayList;
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
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Class contains methods for arranging the main window of the program.
 * @author mariia
 */
public class MainWindow {
    
    public Scene setMainWindow(){
        
        BorderPane root = new BorderPane();
        
        root.setLeft(setMenuPane());
        root.setCenter(getCenterVbox());
        mainWindow = new Scene(root, 1000, 700);
        return mainWindow;
    }
    
    private BorderPane setMenuPane() {
        //Creating a BorderPane for the left side.
        BorderPane menuPane = new BorderPane();
        
        menuPane.setPrefWidth(300);
        menuPane.setStyle("-fx-background-color: " + Constants.leftPanelColor);
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
        
        Button backToWelcomeScreen = new Button("Back");
        backToWelcomeScreen.setOnAction(goToWelcomeScreen);
        
        box.getChildren().addAll(writeDataBtn, addVRegion(15), 
                backToWelcomeScreen, addVRegion(15), getQuitButton());
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
    
    EventHandler<ActionEvent> goToWelcomeScreen = new EventHandler<ActionEvent>(){
        @Override 
        public void handle(ActionEvent btnPress) { 
            System.out.println("Back button pressed");
            theStage.setScene(welcomeWindow);
            
        }
    };    
    
    
   
    private VBox getCenterVbox() {
        //Creating an HBox.
        
        //DummyModule degree = getDummyDegreeStructure();
        VBox centerVBox = new VBox(10);
        //centerVBox.setAlignment(Pos.TOP_CENTER);
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setId("courseTree");
        Node treeStructure = new Region();//handleModule(degree); //
        ((Region)treeStructure).setPadding(new Insets(0,0,0,0));
        scrollPane.setContent(treeStructure);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(20, 30, 0, 30));
        scrollPane.setStyle("-fx-background-color: " + Constants.rightPanelColor);
        
        centerVBox.getChildren().add(scrollPane);
        
        return centerVBox;
    }
    
   
     //Create tree structure of the course list
    public Node handleModule(DegreeModule module){
        
        if(module instanceof Course){
            return setSingleCourseBox((Course)module);
        }
        else{
            StudyModule moduleBlock = (StudyModule)module;
            ArrayList<DegreeModule> innerModules = moduleBlock.getStudyModulesAndCourses();
            
            VBox onePaneContent = new VBox();
            onePaneContent.setSpacing(2);
            onePaneContent.setStyle("-fx-background-color: " + Constants.rightPanelColor);
            
            TitledPane titledPane = new TitledPane(moduleBlock.getName()+ " 0/60", onePaneContent);
            titledPane.setId(moduleBlock.getId());
            titledPane.setPadding(new Insets(0,0,0,20));
            titledPane.setStyle("-fx-background-color: " + Constants.rightPanelColor);
            
            for(DegreeModule innerModule : innerModules){
                onePaneContent.getChildren().add(handleModule(innerModule));
            }
            return titledPane;
        }
    }   
    
    
    private HBox setSingleCourseBox(Course module){
        
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.setPrefHeight(45);
        box.setPadding(new Insets(0, 15, 0, 15));
        box.setSpacing(10);
        box.setStyle( "-fx-background-radius: 5 5 5 5; -fx-background-color: " + Constants.courseBoxColor);

        Label courseNameLabel = new Label(module.getName());

        Region region = new Region();
        box.setHgrow(region, Priority.ALWAYS);

        VBox creditSection = new VBox();
        creditSection.setAlignment(Pos.CENTER);
        creditSection.setPadding(new Insets(0, 15, 0, 5));
        Label cr = new Label("cr");
        cr.setStyle("-fx-font-size:10;");
        Label creditLabel = new Label(Integer.toString(module.getMinCredits()));
        creditSection.getChildren().addAll(cr, creditLabel);


        Button addToMyCoursesBtn = new Button("Take course");
        addToMyCoursesBtn.setPrefWidth(150);
        addToMyCoursesBtn.setId(module.getName());
        addToMyCoursesBtn.setOnAction(takeCourseEventHandler);
        addToMyCoursesBtn.setStyle( "-fx-background-color: " + Constants.courseButtonColor);
        
        

        box.getChildren().addAll(courseNameLabel, region, creditSection, 
                addToMyCoursesBtn);
        return box;
    }
    
        
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
    
    
    //Event handlers:

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
                courseBtn.getParent().setStyle("-fx-background-color: " + Constants.courseBoxColor);
                courseBtn.setText("Take course");
                courseBtn.setPrefWidth(150);
                ((Pane)courseBtn.getParent()).getChildren().remove(((Pane)courseBtn.getParent()).getChildren().get(4));
            }
        }
    };        
    
}
