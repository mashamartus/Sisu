
package fi.tuni.prog3.sisu;

import static fi.tuni.prog3.sisu.Sisu.allCourses;
import static fi.tuni.prog3.sisu.Sisu.css;
import static fi.tuni.prog3.sisu.Sisu.mainWindow;
import static fi.tuni.prog3.sisu.Sisu.theStage;
import static fi.tuni.prog3.sisu.Sisu.curStudent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Class contains method for arranging the welcome window of the program.
 * @author mariia
 */
public class WelcomeScreen {
    
    ArrayList<Program> programList;
    public WelcomeScreen() {
    }
    
    public Scene setWelcomeWindow(){
        
        BorderPane welcomeRoot = new BorderPane();
        welcomeRoot.setLeft(setWelcomeWindowLeft());
        welcomeRoot.setCenter(setWelcomeWindowRight());
        Scene welcomeWindow = new Scene(welcomeRoot, 600, 400);
        
        return welcomeWindow;
    }
        
    private VBox setWelcomeWindowLeft(){
        VBox box = new VBox();
        box.setStyle("-fx-background-color: " + Constants.leftPanelColor);
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
        box.setPadding(new Insets(0, 20, 0, 20));
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: " + Constants.rightPanelColor);
        Label heading = new Label("Create a new plan");
        heading.setFont(new Font(20));
        
        GridPane grid = new GridPane();
        grid.setVgap(8);
        
        grid.add(new Label("Student name"), 0, 0);
        TextField studentField = new TextField();
        studentField.setId("studentName");
        studentField.setText("Student1");
        //studentField.setPrefWidth(200);
        grid.add(studentField, 1, 0);
        
        Label planLabel = new Label("Name your plan");
        grid.add(planLabel, 0, 1);
        TextField planNameField = new TextField();
        planNameField.setId("planName");
        planNameField.setText("MyPlan");
        //planNameField.setPrefWidth(200);
        grid.add(planNameField, 1, 1);
        
        
        grid.setHgrow(planLabel, Priority.ALWAYS);
        grid.setHgrow(planNameField, Priority.ALWAYS);
        
        
        
        
        
        grid.add(new Label("Starting year"), 0, 2);
        ChoiceBox yearChoiceBox = new ChoiceBox();
        yearChoiceBox.getItems().addAll("2018","2019", "2020","2021","2022",
                "2023","2024","2025","2026");
        yearChoiceBox.setValue("2023");
        grid.add(yearChoiceBox, 1, 2);
        yearChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
              
                curStudent.setStartYear(Integer.valueOf(yearChoiceBox.getItems().get((Integer) number2).toString()));
            }
        });
        
        ColumnConstraints col = new ColumnConstraints();
        col.setHalignment(HPos.LEFT);
        grid.getColumnConstraints().add(col);
        
        Label programLabel = new Label("Choose your degree");
        ChoiceBox degreeChoiceBox = new ChoiceBox();
        degreeChoiceBox.setId("degreeChoiceBox");
        //degreeChoiceBox.getItems().add("Please select a year");
        
        SisuHelper sh = new SisuHelper();
        List<String> programNames = 
        sh.getAllPrograms(2022, "en").stream()
          .map(Program::getName)
          .collect(Collectors.toList());
        degreeChoiceBox.getItems().addAll(programNames);
        
        
        Button goButton = new Button();
        goButton.setText("Start planning");
        goButton.setOnAction(startPlanningEventHandler);
        goButton.setMinWidth(100);
        goButton.setMinHeight(40);
        
        double addSpace = 20;
        box.getChildren().addAll(heading, Sisu.addVRegion(addSpace), grid, 
                Sisu.addVRegion(addSpace),  programLabel,  
                degreeChoiceBox, Sisu.addVRegion(addSpace), goButton);
                
             
        return box;
    }
    
        
    EventHandler<ActionEvent> startPlanningEventHandler = new EventHandler<ActionEvent>(){
        @Override 
        public void handle(ActionEvent btnPress) { 
            System.out.println("Start planning button pressed");
            
            Stage stage = (Stage)((Node) btnPress.getTarget()).getScene().getWindow();
            Scene scene = ((Button)btnPress.getTarget()).getScene();
            curStudent.setName(((TextField)scene.lookup("#studentName")).getText());
            
            
            
            
            ChoiceBox degreeBox = (ChoiceBox)scene.lookup("#degreeChoiceBox");
            SisuHelper sh = new SisuHelper();
            String degreeName = degreeBox.getSelectionModel().getSelectedItem().toString();
            System.out.println(degreeName);
            Optional<Program> selProgramOpt = sh.getAllPrograms(2022, "en").stream()
                .filter(p -> p.getName().equals(degreeName))
                .findFirst();
            Program selProgram = selProgramOpt.get();
            curStudent.setProgram(selProgram);
            curStudent.setPlanName(((TextField)scene.lookup("#planName")).getText());
            
            System.out.println(curStudent);
            
            //define main window
            MainWindow mw = new MainWindow();
            mainWindow = mw.setMainWindow();

            StudyModule studyModule = sh.createStudyModule(selProgram.getId());
            StudyModule.printModuleDetailed(studyModule,"");
            ScrollPane treeNode = (ScrollPane)mainWindow.lookup("#courseTree");
            treeNode.setContent(mw.handleModule(studyModule));
            System.out.println("All courses:" + allCourses.keySet().toString());
            
            stage.setScene(Sisu.mainWindow);
            Sisu.mainWindow.getRoot().requestFocus();
            
            
            
            
        }
    };
    
}
