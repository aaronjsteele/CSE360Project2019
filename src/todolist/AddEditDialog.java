package todolist;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        window.setTitle("Add/Edit");
        window.setWidth(500);
        window.setHeight(200);
        window.initStyle(StageStyle.UTILITY);
        window.setResizable(false);

        VBox root = new VBox();
        
        HBox descriptionHBox = new HBox();
        Label descriptionLabel = new Label("Description");
        descriptionLabel.setMinWidth(65);
        descriptionLabel.setMaxWidth(65);
        // Adding the description text field
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Enter a description");
        descriptionField.setPrefWidth(395);
        descriptionHBox.getChildren().addAll(descriptionLabel, descriptionField);
        descriptionHBox.setPadding(new Insets(5, 10, 5, 10));
        descriptionHBox.setSpacing(10);
        descriptionHBox.setAlignment(Pos.CENTER_LEFT);
        
        HBox middleHBox = new HBox();
        VBox leftVBox = new VBox();
        VBox rightVBox = new VBox();
        HBox buttonHBox = new HBox();
        
        Label priorityLabel = new Label("Priority");
        priorityLabel.setMinWidth(65);
        priorityLabel.setMaxWidth(65);
        Label dueLabel = new Label("Due Date");
        dueLabel.setMinWidth(65);
        dueLabel.setMaxWidth(65);
        Label statusLabel = new Label("Status");
        statusLabel.setMinWidth(67);
        statusLabel.setMaxWidth(67);
        Label dateLabel = new Label("Date Started");
        Button addButton = new Button("Add");
        addButton.setMinWidth(100);
        addButton.setMaxWidth(100);
        Button cancelButton = new Button("Cancel");
        cancelButton.setMinWidth(100);
        cancelButton.setMaxWidth(100);
        
        TextField priorityField = new TextField();
        priorityField.setPromptText("Enter a priority number");
        TextField dueField = new TextField();
        dueField.setPromptText("MM/DD/YYYY");
        TextField dateField = new TextField();
        dateField.setPromptText("MM/DD/YYYY");
        
        ObservableList<String> progressOptions =
        	FXCollections.observableArrayList(
    			"In Progress",
    			"Incomplete",
    			"Completed"
        	);
        final ComboBox progressComboBox = new ComboBox(progressOptions);
        progressComboBox.getSelectionModel().selectFirst();
        HBox.setHgrow(progressComboBox, Priority.ALWAYS);
        
        // Creates the HBoxes containing labels and text fields
        HBox priorityHBox = new HBox();
        priorityHBox.getChildren().addAll(priorityLabel, priorityField);
        priorityHBox.setPadding(new Insets(5, 10, 5, 10));
        priorityHBox.setSpacing(10);
        priorityHBox.setAlignment(Pos.CENTER_LEFT);
        HBox dueHBox = new HBox();
        dueHBox.getChildren().addAll(dueLabel, dueField);
        dueHBox.setPadding(new Insets(5, 10, 5, 10));
        dueHBox.setSpacing(10);
        dueHBox.setAlignment(Pos.CENTER_LEFT);
        HBox statusHBox = new HBox();
        statusHBox.getChildren().addAll(statusLabel, progressComboBox);
        statusHBox.setPadding(new Insets(5, 10, 5, 10));
        statusHBox.setSpacing(10);
        statusHBox.setAlignment(Pos.CENTER_LEFT);
        HBox dateHBox = new HBox();
        dateHBox.getChildren().addAll(dateLabel, dateField);
        dateHBox.setPadding(new Insets(5, 10, 5, 10));
        dateHBox.setSpacing(10);
        dateHBox.setAlignment(Pos.CENTER_LEFT);
        
        // Adds the elements to the left and right VBoxes
        leftVBox.getChildren().addAll(priorityHBox, dueHBox);
        rightVBox.getChildren().addAll(statusHBox, dateHBox);
        
        buttonHBox.getChildren().addAll(addButton, cancelButton);
        buttonHBox.setPadding(new Insets(25, 10, 5, 10));
        buttonHBox.setSpacing(10);
        buttonHBox.setAlignment(Pos.CENTER);
        
        middleHBox.getChildren().addAll(leftVBox, rightVBox);
        
        root.getChildren().addAll(descriptionHBox, middleHBox, buttonHBox);
        
        
        window.setScene(new Scene(root, 500, 180));
        return window;
    }
} 