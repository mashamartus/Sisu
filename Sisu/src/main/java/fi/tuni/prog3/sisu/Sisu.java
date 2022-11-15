package fi.tuni.prog3.sisu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


/**
 * Sisu application class. Starts the Sisu application.
 * Try to run it, if no errors then all is well.
 */
public class Sisu extends Application {
    // private datafields


    @Override
    public void start(Stage stage) {
        stage.setTitle("SISU 2.0");
        stage.setMinWidth(400);
        stage.setMinHeight(400);
        initialScene(stage);
        stage.show();
    }


    /**
     * The initial window screen
     * Only goes to the student view for now.
     * @param stage stage to be set
     */
    private void initialScene(Stage stage) {
        Label label1 = new Label();
        label1.setText("Input Student ID: ");
        TextField textField1 = new TextField();

        // Button to the student view window.
        Button button1 = new Button();
        button1.setText("Enter SISU 2.0");
        button1.setOnAction(e -> studentScene(stage));

        GridPane gridPane = new GridPane();
        gridPane.add(label1, 0,0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(button1, 0,1);
        Scene initial = new Scene(gridPane);
        stage.setScene(initial);
    }


    /**
     * student view window
     *
     * NEEDS THE DEGREE PROGRAM AND ITS COURSES DATA FROM SISU API!
     * ALSO NEEDS PARSED DATA FROM STUDENT JSON FILE IF EXISTS (courses completed)!
     *
     * @param stage stage to be set
     */
    private void studentScene(Stage stage) {
        TabPane tabPane = new TabPane();
        tabPane.setMinHeight(500);
        tabPane.setMinWidth(500);

        Tab tab1 = new Tab("Student information" );
        tabPane.getTabs().add(tab1);
        Tab tab2 = new Tab("Degree structure" );
        tabPane.getTabs().add(tab2);

        BorderPane studentInfoPane = new BorderPane();
        tab1.setContent(studentInfoPane);
        Label infoBox = new Label("STUDENT INFORMATION TAB");
        studentInfoPane.setTop(infoBox);

        // On the left side is the scroll window for courses in the degree program.
        // studentInfoPane.setLeft();

        // On right side maybe a Searchfield? and checkbox window for completing courses.
        // studentInfoPane.setRight();

        Label degreeTabLabel = new Label("DEGREE STRUCTURE TAB");
        tab2.setContent(degreeTabLabel);
        Scene studentScene = new Scene(tabPane);
        stage.setScene(studentScene);
    }


    /**
     * Main method, launches the app.
     * @param args not used.
     *
     */
    public static void main(String[] args) {
        launch(args);
    }
}
