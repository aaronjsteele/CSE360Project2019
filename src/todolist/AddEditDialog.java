package todolist;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddEditDialog {
    private static List<String> toDoItemInfo;

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("title");
        window.setWidth(300);
        window.setHeight(175);
        window.initStyle(StageStyle.UTILITY);
        Label label = new Label("message");
        
        
        VBox root = new VBox();
        root.getChildren().add(label);

        // Set up the JavaFX button controls and listeners and the text fields
        // for the login info. The button listeners set the login values

        // AddEditDialog.display();
        
        window.setScene(new Scene(root, 300, 175));
        window.showAndWait();
        //return login;
        
    }
} 