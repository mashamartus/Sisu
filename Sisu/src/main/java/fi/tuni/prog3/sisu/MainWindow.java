
package fi.tuni.prog3.sisu;

import static fi.tuni.prog3.sisu.Sisu.addVRegion;
import static fi.tuni.prog3.sisu.Sisu.mainWindow;
import static fi.tuni.prog3.sisu.Sisu.theStage;
import static fi.tuni.prog3.sisu.Sisu.welcomeWindow;
import static fi.tuni.prog3.sisu.Sisu.curStudent;
import static fi.tuni.prog3.sisu.Sisu.allCourses;
import static fi.tuni.prog3.sisu.Sisu.allCourseBoxes;
import static fi.tuni.prog3.sisu.Sisu.css;
import static fi.tuni.prog3.sisu.Sisu.mainWindow;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Class contains methods for arranging the main window of the program.
 * @author mariia
 */
public class MainWindow {
    
    public Scene setMainWindow(){
        
        BorderPane root = new BorderPane();
        
        root.setLeft(setMenuPane());
        root.setCenter(getCenterVbox());
        mainWindow = new Scene(root, 1300, 700);
        mainWindow.getStylesheets().add(css);
        System.out.println("MainWindow is set\n");
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
        Label planName = new Label(curStudent.getStudentName());
        planName.setStyle("-fx-font-size: 16pt ;");
        
        // add degree heading label
        Label degNameLabel = new Label(curStudent.getProgram().getName());
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
        
        GridPane grid = new GridPane();
        grid.add(new Label("Planned"), 0, 0);
        grid.add(new Label("Completed"), 0, 1);
        grid.add(new Label("Av.grade"), 0, 2);
        Label planned = new Label("xx/xxx");
        planned.setId("planned");
        grid.add(planned, 1, 0);
        Label completed = new Label("xx/xxx");
        completed.setId("completed");
        grid.add(completed, 1, 1);
        Label avGrade = new Label("xx.xx");
        avGrade.setId("avGrade");
        grid.add(avGrade, 1, 2);
        
        
        
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Planned", 0),
                new PieChart.Data("Not yet", curStudent.getProgramCredits()));
        PieChart chart = new PieChart(pieChartData);
        chart.setId("pieChart");
        chart.setStartAngle(90);
        chart.setPrefHeight(250);
        chart.setLegendVisible(false);
        chart.setLabelLineLength(10);
        
        box.getChildren().addAll(grid, chart);
        System.out.println("ProgressPane is set");
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
        centerVBox.setStyle("-fx-background-color: " + Constants.rightPanelColor);
        
        HBox headingBox = new HBox();
        headingBox.setStyle("-fx-background-color: transparent");
        headingBox.setPadding(new Insets(25, 50, 0, 100));
        headingBox.setSpacing(100);
        ChoiceBox languageChoiceBox = new ChoiceBox();
        languageChoiceBox.getItems().addAll("en", "fi");
        languageChoiceBox.setValue("en");
                // add a listener
        languageChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue ov, Number value, Number new_value)
            {
                // set the text for the label to the selected item
                if(new_value.toString().equals("en")){}
            }
        });
        
        
        ChoiceBox showTakenCoursesChoice = new ChoiceBox();
        showTakenCoursesChoice.getItems().addAll("Show all courses", 
                "Show taken courses");
        showTakenCoursesChoice.setValue("Show all courses");
        headingBox.getChildren().addAll(showTakenCoursesChoice, languageChoiceBox);
        
                
        
        
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setId("courseTree");
        Node treeStructure = new Region();
        ((Region)treeStructure).setPadding(new Insets(0,0,0,0));
        scrollPane.setContent(treeStructure);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(20, 30, 0, 30));
        scrollPane.setStyle("-fx-background-color: transparent");
        
        centerVBox.getChildren().addAll(headingBox, scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        
        return centerVBox;
    }
    
   
     //Create tree structure of the course list
    public Node handleModule(DegreeModule module){
        
        if(module instanceof Course){
            Course course = (Course)module;
            allCourses.put(course.getId(), course);
            HBox courseBox = setSingleCourseBox(course);
            allCourseBoxes.put(course.getId(), courseBox);
            return courseBox;
        }
        else{
            StudyModule moduleBlock = (StudyModule)module;
            ArrayList<DegreeModule> innerModules = moduleBlock.getStudyModulesAndCourses();
            
            VBox onePaneContent = new VBox();
            onePaneContent.setSpacing(2);
            onePaneContent.setStyle("-fx-background-color: "+
                    Constants.rightPanelColor);
            
            TitledPane titledPane = new TitledPane(moduleBlock.getName()+ 
                    " 0/60", onePaneContent);
            titledPane.setId(moduleBlock.getId());
            titledPane.setPadding(new Insets(0,0,0,20));
            titledPane.setStyle("-fx-background-color: " + 
                    Constants.rightPanelColor);
            
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
        addToMyCoursesBtn.setId(module.getId());
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
            final Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(theStage.getOwner());
            VBox dialogVbox = new VBox(20);
            dialogVbox.getChildren().add(new Text("Grade for course"));
            TextField gradeField = new TextField();
            Button gradeButton = new Button("Input grade");
            gradeButton.setId(((Button)btnPress.getSource()).getId() + "_btn");
            gradeButton.setOnAction(inputGradeEventHandler);
            dialogVbox.getChildren().addAll(gradeField, gradeButton);
            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        }
    
    };
    
    EventHandler<ActionEvent> inputGradeEventHandler = new EventHandler<ActionEvent>(){
        @Override 
        public void handle(ActionEvent btnPress) { 
            Button putGradeBtn = (Button)btnPress.getSource();
            String grade = ((TextInputControl)((Pane)putGradeBtn.getParent()).getChildren().get(1)).getText();
            String courseId = putGradeBtn.getId().split("_grade_btn")[0];
            curStudent.gradeCourse(courseId, Integer.parseInt(grade));
            ((Stage)putGradeBtn.getScene().getWindow()).close();
            System.out.println("Average grade is " + curStudent.getAverageGrade());
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
                curStudent.takeCourse(allCourses.get(courseId));
                curStudent.printTakenCourses();
                System.out.println(curStudent.getPlannedCredits() + "/" + curStudent.getProgramCredits());
                //updateProgress();
                
                //modify button layout
                courseBtn.getParent().setStyle("-fx-background-color: ffffff");
                courseBtn.setText("Drop");
                courseBtn.setPrefWidth(70);
                updateBlockHeading((TitledPane)courseBtn.getParent().getParent().getParent().getParent());
                
                Button putGradeBtn = new Button("Grade");
                putGradeBtn.setPrefWidth(70);
                putGradeBtn.setStyle("-fx-background-color: " + Constants.courseButtonColor);
                putGradeBtn.setId(courseBtn.getId() + "_grade");
                putGradeBtn.setOnAction(gradeCourseEventHandler);
                ((Pane)courseBtn.getParent()).getChildren().add(putGradeBtn);
                
            }
            else{
                //remove course
                curStudent.dropCourse(courseId);
                curStudent.printTakenCourses();
                System.out.println(curStudent.getPlannedCredits() + "/" + curStudent.getProgramCredits());
                //updateProgress();
                
                //modify button layout
                courseBtn.getParent().setStyle("-fx-background-color: " + Constants.courseBoxColor);
                courseBtn.setText("Take course");
                courseBtn.setPrefWidth(150);
                ((Pane)courseBtn.getParent()).getChildren().remove(((Pane)courseBtn.getParent()).getChildren().get(4));
            }
        }
    };       
    
    
    //it gives an error beacause it can't find objects with lookup function
    public static void updateProgress(){
        
        Node node = mainWindow.lookup("pieChart");
        System.out.println(node);
        PieChart pieChart = (PieChart)mainWindow.lookup("pieChart");
        System.out.println(pieChart);
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Planned", curStudent.getPlannedCredits()),
                new PieChart.Data("Not yet", curStudent.getProgramCredits()));
        pieChart.setData(pieChartData);
        
        Label planned = (Label)mainWindow.lookup("planned");
        planned.setText(curStudent.getPlannedCredits() + "/" + curStudent.getProgramCredits());
        Label completed = (Label)mainWindow.lookup("completed");
        completed.setText(curStudent.getCompletedCredits() + "/" + curStudent.getProgramCredits());
        Label avGrade = (Label)mainWindow.lookup("avGrade");
        avGrade.setText(curStudent.getAverageGrade() + "");
        
    }
    
}
