package todolist;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    public static Stage createAddDialog() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("title");
        window.setWidth(400);
        window.setHeight(200);
        window.initStyle(StageStyle.UTILITY);
        Label label = new Label("message");
        
        VBox root = new VBox();
        
        HBox descriptionHBox = new HBox();
        Label descriptionLabel = new Label("Description");
        // Adding the description text field
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter a description");
        descriptionHBox.getChildren().addAll(descriptionLabel, descriptionField);
        
        HBox middleHBox = new HBox();
        VBox leftVBox = new VBox();
        VBox rightVBox = new VBox();
        
        Label priorityLabel = new Label("Priority");
        Label dueLabel = new Label("Due Date");
        Label statusLabel = new Label("Status");
        Label dateLabel = new Label("Date Started");
        Button addButton = new Button("Add");
        Button cancelButton = new Button("Cancel");
        
        TextField priorityField = new TextField();
        priorityField.setPromptText("Enter a priority number");
        TextField dueField = new TextField();
        dueField.setPromptText("MM/DD/YYYY");
        TextField dateField = new TextField();
        dateField.setPromptText("MM/DD/YYYY");
        
        ObservableList<String> progressOptions =
        	FXCollections.observableArrayList(
    			"In Progress",
    			"Finished"
        	);
        final ComboBox progressComboBox = new ComboBox(progressOptions);
        
        HBox priorityHBox = new HBox();
        priorityHBox.getChildren().addAll(priorityLabel, priorityField);
        HBox dueHBox = new HBox();
        dueHBox.getChildren().addAll(dueLabel, dueField);
        HBox statusHBox = new HBox();
        statusHBox.getChildren().addAll(statusLabel, progressComboBox);
        HBox dateHBox = new HBox();
        dateHBox.getChildren().addAll(dateLabel, dateField);
        
        leftVBox.getChildren().addAll(priorityHBox, dueHBox, addButton);
        rightVBox.getChildren().addAll(statusHBox, dateHBox, cancelButton);
        
        middleHBox.getChildren().addAll(leftVBox, rightVBox);
        
        root.getChildren().addAll(descriptionHBox, middleHBox);
        // Set up the JavaFX button controls and listeners and the text fields
        // for the login info. The button listeners set the login values

        // AddEditDialog.display();
        
        window.setScene(new Scene(root, 400, 200));
        return window;
        //return login;
        
    }
} 