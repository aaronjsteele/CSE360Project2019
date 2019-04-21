package todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("/todolist/todolist.fxml"));
        primaryStage.setTitle("To-Do List Unlimited 2019");
        
        Button addButton = new Button("Add");
        Button printButton = new Button("Print");
        Button saveButton = new Button("Save");
        Button loadButton = new Button("Load");
        
        BorderPane root = new BorderPane();
        VBox buttonBox = new VBox();
        buttonBox.getChildren().add(addButton);
        buttonBox.getChildren().add(printButton);
        buttonBox.getChildren().add(saveButton);
        buttonBox.getChildren().add(loadButton);        
        buttonBox.setSpacing(10);
        
        ListView toDoItemList = new ListView();
        
        root.setLeft(buttonBox);
        root.setCenter(toDoItemList);
        
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        
    }
}