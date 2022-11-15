package fi.tuni.prog3.sisu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        
        Scene scene = new Scene(root, 800, 500);                      
        stage.setScene(scene);
        stage.setTitle("SisuGUI");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
    private VBox getCenterVbox() {
        //Creating an HBox.
        VBox centerVBox = new VBox(10);
        
        //Adding two VBox to the HBox.
        //centerVBox.getChildren().addAll(setMenuPane(), getRightVBox());
        
        return centerVBox;
    }
    
    private BorderPane setMenuPane() {
        //Creating a BorderPane for the left side.
        BorderPane menuPane = new BorderPane();
        
        menuPane.setPrefWidth(200);
        menuPane.setStyle("-fx-background-color: #8fc6fd;");
        menuPane.setTop(headingPane());
        
        menuPane.setBottom(getQuitButton());
        menuPane.setAlignment(menuPane.getBottom(), Pos.BOTTOM_CENTER);
        
        menuPane.setMargin(menuPane.getTop(), new Insets(10, 10, 10, 10));
        menuPane.setMargin(menuPane.getBottom(), new Insets(10, 10, 10, 10));
        
        return menuPane;
    }
    private VBox headingPane(){
        VBox box = new VBox(10);
        // add plan name text field
        TextField text = new TextField("Your plan name");
        
        // add degree heading label
        Label degNameLabel = new Label("Put the name of chosen studying degree here");
        degNameLabel.setId("degNameLabel");
        degNameLabel.setWrapText(true);
        
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
}