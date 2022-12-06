
package fi.tuni.prog3.sisu;

import static fi.tuni.prog3.sisu.Sisu.allCourses;
import static fi.tuni.prog3.sisu.Sisu.css;
import static fi.tuni.prog3.sisu.Sisu.mainWindow;
import static fi.tuni.prog3.sisu.Sisu.theStage;
import static fi.tuni.prog3.sisu.Sisu.curStudent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
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
    
    /**
     * empty constructor for welcomeScreen
     */
    public WelcomeScreen() {
    }
    
    /**
     * Method sets the gui for welcome window
     * @return welcomeWindow
     */
    public Scene setWelcomeWindow(){
        
        BorderPane welcomeRoot = new BorderPane();
        welcomeRoot.setLeft(setWelcomeWindowLeft());
        welcomeRoot.setCenter(setWelcomeWindowRight());
        Scene welcomeWindow = new Scene(welcomeRoot, 600, 400);
        
        return welcomeWindow;
    }
        
    private VBox setWelcomeWindowLeft(){
        VBox box = new VBox();
        box.setSpacing(15);
        box.setStyle("-fx-background-color: " + Constants.leftPanelColor);
        box.setAlignment(Pos.CENTER);
        box.setPrefWidth(220);
        box.setPrefHeight(100);
        
        
        ChoiceBox<String> planChoiceBox = new ChoiceBox<>();
        planChoiceBox.setStyle("-fx-background-color: " + Constants.rightPanelColor);
        planChoiceBox.setValue("Select plan");
        planChoiceBox.setValue("2023");
        planChoiceBox.setId("existingPlansNames");
        updateExistingPlansList(planChoiceBox);
        
        Button continueFromPastBtn = new Button();
        continueFromPastBtn.setText("Use saved plan");
        continueFromPastBtn.setMinWidth(100);
        continueFromPastBtn.setMinHeight(40);
        continueFromPastBtn.setFont(new Font(16));
        continueFromPastBtn.setTextAlignment(TextAlignment.CENTER);
        continueFromPastBtn.wrapTextProperty().setValue(true);
        continueFromPastBtn.setPrefWidth(150);
        continueFromPastBtn.setOnAction(restoreSessionEventHandler);
        box.getChildren().addAll(planChoiceBox, continueFromPastBtn);
        return box;
    }
    
    static void updateExistingPlansList(ChoiceBox<String> plans){
        plans.getItems().clear();
        
        File folder = new File("src/main/resources");
        ArrayList<String> filesNames = new ArrayList<>();
        filesNames.add("Select plan");
        for(File file : folder.listFiles()){
          if (file.isFile() && file.getName().contains(".json")) {
            filesNames.add(file.getName().split(".json")[0]);
          } 
        }
        plans.getItems().addAll(filesNames);
    }
   
    /**
     * 
     * @return 
     */
    
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
        ChoiceBox<String> yearChoiceBox = new ChoiceBox<>();
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
        ChoiceBox<String> degreeChoiceBox = new ChoiceBox<>();
        degreeChoiceBox.setId("degreeChoiceBox");
        //degreeChoiceBox.getItems().add("Please select a year");
        
        SisuHelper sh = new SisuHelper();
        
        List<String> programNames = 
        sh.getAllPrograms(2022, "en").stream()
          .map(Program::getName)
          .collect(Collectors.toList());
        Collections.sort(programNames);
        degreeChoiceBox.getItems().addAll(programNames);
        
        
        Button goButton = new Button();
        goButton.setText("Create new plan");
        goButton.setOnAction(startPlanningEventHandler);
        goButton.setMinWidth(100);
        goButton.setMinHeight(40);
        
        double addSpace = 20;
        box.getChildren().addAll(heading, Sisu.addVRegion(addSpace), grid, 
                Sisu.addVRegion(addSpace),  programLabel,  
                degreeChoiceBox, Sisu.addVRegion(addSpace), goButton);
                
        return box;
    }
    
        
    final private EventHandler<ActionEvent> startPlanningEventHandler = new EventHandler<ActionEvent>(){
        @Override 
        public void handle(ActionEvent btnPress) { 
            System.out.println("Start planning button pressed");
            
            
            Stage stage = (Stage)((Node) btnPress.getTarget()).getScene().getWindow();
            Scene scene = ((Node)btnPress.getTarget()).getScene();
            try{
                curStudent = new Student(((TextInputControl)scene.lookup("#studentName")).getText());
            }
            catch(Exception e){
                System.out.println("failed to create new student");
                System.out.println(e.getMessage());
            }
            curStudent.setName(((TextInputControl)scene.lookup("#studentName")).getText());
            
            ChoiceBox<String> degreeBox = (ChoiceBox<String>)scene.lookup("#degreeChoiceBox");
            SisuHelper sh = new SisuHelper();
            String degreeName = degreeBox.getSelectionModel().getSelectedItem();
            System.out.println(degreeName);
            Optional<Program> selProgramOpt = sh.getAllPrograms(2022, "en").stream()
                .filter(p -> p.getName().equals(degreeName))
                .findFirst();
            Program selProgram = selProgramOpt.get();
            curStudent.setProgram(selProgram);
            curStudent.setPlanName(((TextInputControl)scene.lookup("#planName")).getText());
            
            System.out.println(curStudent);
            
            //define main window
            MainWindow mw = new MainWindow();
            mainWindow = mw.setMainWindow();

            StudyModule studyModule = sh.createStudyModule(selProgram.getId());
            //StudyModule.printModuleDetailed(studyModule,"");
            ScrollPane treeNode = (ScrollPane)mainWindow.lookup("#courseTree");
            treeNode.setContent(mw.handleModule(studyModule));
            
            stage.setScene(mainWindow);
            stage.setMaximized(true);
        }
    };
    
    /**
     * Actions when user wants to continue his previous session.
     * Get data from the chosen previous session. 
     * If file not found gives alert info window and do nothing.
     */
    public final EventHandler<ActionEvent> restoreSessionEventHandler = new EventHandler<ActionEvent>(){
        @Override 
        public void handle(ActionEvent btnPress) { 
            System.out.println("Starting from saved session");
            Scene scene = ((Node)btnPress.getTarget()).getScene();
            ChoiceBox<String> choiceBox = (ChoiceBox<String>)scene.lookup("#existingPlansNames");
            String fileName = choiceBox.getValue();
            SisuHelper sh = new SisuHelper();
            //load the student
            try{
                curStudent = sh.importDataFromJson(fileName);
            }
            catch(FileNotFoundException e){
                System.out.println("ERROR: " + e.getMessage());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Warning");
                alert.setHeaderText("Warning");
                alert.setContentText("File " + fileName + "doesn't exist");
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        System.out.println("Pressed OK.");
                    }
                });
                return;
            }
            
            curStudent.printStudent();
            //define main window
            MainWindow mw = new MainWindow();
            mainWindow = mw.setMainWindow();
            
            // load course tree
            StudyModule studyModule = sh.createStudyModule(curStudent.getProgram().getId());
            ScrollPane treeNode = (ScrollPane)mainWindow.lookup("#courseTree");
            treeNode.setContent(mw.handleModule(studyModule));
            
            theStage.setScene(mainWindow);
            theStage.setMaximized(true);
            
            
            
        }
    };
    
}
