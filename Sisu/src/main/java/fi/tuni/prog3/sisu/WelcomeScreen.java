
package fi.tuni.prog3.sisu;

import static fi.tuni.prog3.sisu.Sisu.mainWindow;
import static fi.tuni.prog3.sisu.Sisu.theStage;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 * Class contains method for arranging the welcome window of the program.
 * @author mariia
 */
public class WelcomeScreen {
    

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
        box.setSpacing(1);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: " + Constants.rightPanelColor);
        Label heading = new Label("Create a new plan");
        heading.setFont(new Font(20));
        
        GridPane grid = new GridPane();
        
        grid.add(new Label("Student name"), 0, 0);
        grid.add(new Label("Name your plan"), 0, 1);
        
        
        TextField studentField = new TextField();
        //studentField.setPrefWidth(200);
        grid.add(studentField, 1, 0);
        
        //Label planNameLabel = new Label("Name your plan");
        TextField planNameField = new TextField();
        //planNameField.setPrefWidth(200);
        grid.add(planNameField, 1, 1);
        
        
        Label programLabel = new Label("Choose your degree");
        ChoiceBox degreeChoiceBox = new ChoiceBox();
        
        SisuHelper sh = new SisuHelper();
        List<String> programNames = 
        sh.getAllPrograms(2022).stream()
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
                
//        box.getChildren().addAll(heading, Sisu.addVRegion(addSpace), planNameLabel, 
//                planNameField, Sisu.addVRegion(addSpace),  programLabel,  
//                degreeChoiceBox, Sisu.addVRegion(addSpace), goButton);
             
        return box;
    }
    
        
    EventHandler<ActionEvent> startPlanningEventHandler = new EventHandler<ActionEvent>(){
        @Override 
        public void handle(ActionEvent btnPress) { 
            System.out.println("Start planning button pressed");
            
            Stage stage = (Stage)((Node) btnPress.getTarget()).getScene().getWindow();
            stage.setScene(Sisu.mainWindow);
            Sisu.mainWindow.getRoot().requestFocus();
            
        }
    };
    
}
