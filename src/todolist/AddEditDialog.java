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
        window.setTitle("Add/Edit");
        window.setWidth(500);
        window.setHeight(200);
        window.initStyle(StageStyle.UTILITY);

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
        
        HBox middleHBox = new HBox();
        VBox leftVBox = new VBox();
        VBox rightVBox = new VBox();
        
        Label priorityLabel = new Label("Priority");
        priorityLabel.setMinWidth(65);
        priorityLabel.setMaxWidth(65);
        Label dueLabel = new Label("Due Date");
        dueLabel.setMinWidth(65);
        dueLabel.setMaxWidth(65);
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
        progressComboBox.getSelectionModel().selectFirst();
        
        HBox priorityHBox = new HBox();
        priorityHBox.getChildren().addAll(priorityLabel, priorityField);
        priorityHBox.setPadding(new Insets(5, 10, 5, 10));
        priorityHBox.setSpacing(10);
        HBox dueHBox = new HBox();
        dueHBox.getChildren().addAll(dueLabel, dueField);
        dueHBox.setPadding(new Insets(5, 10, 5, 10));
        dueHBox.setSpacing(10);
        HBox statusHBox = new HBox();
        statusHBox.getChildren().addAll(statusLabel, progressComboBox);
        statusHBox.setPadding(new Insets(5, 10, 5, 10));
        statusHBox.setSpacing(10);
        HBox dateHBox = new HBox();
        dateHBox.getChildren().addAll(dateLabel, dateField);
        dateHBox.setPadding(new Insets(5, 10, 5, 10));
        dateHBox.setSpacing(10);
        
        leftVBox.getChildren().addAll(priorityHBox, dueHBox, addButton);
        rightVBox.getChildren().addAll(statusHBox, dateHBox, cancelButton);
        
        middleHBox.getChildren().addAll(leftVBox, rightVBox);
        
        root.getChildren().addAll(descriptionHBox, middleHBox);
        
        window.setScene(new Scene(root, 500, 200));
        return window;
        //return login;
        
    }
} 