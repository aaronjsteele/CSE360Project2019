package todolist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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

public class EditDialog {
    private static List<String> toDoItemInfo;

    public static Stage createEditDialog(Task task) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Edit");
        window.setWidth(540);
        window.setHeight(230);
        window.initStyle(StageStyle.UTILITY);
        window.setResizable(false);
        // hi there
        VBox root = new VBox();
        
        HBox descriptionHBox = new HBox();
        Label descriptionLabel = new Label("Description");
        descriptionLabel.setMinWidth(85);
        descriptionLabel.setMaxWidth(85);
        // Adding the description text field
        TextField descriptionField = new TextField(task.getDescription());
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
        priorityLabel.setMinWidth(85);
        priorityLabel.setMaxWidth(85);
        Label dueLabel = new Label("Due Date");
        dueLabel.setMinWidth(85);
        dueLabel.setMaxWidth(85);
        Label statusLabel = new Label("Status");
        statusLabel.setMinWidth(100);
        statusLabel.setMaxWidth(100);
        Label dateLabel = new Label("Date Started");
        dateLabel.setMinWidth(100);
        dateLabel.setMaxWidth(100);
        Button addButton = new Button("Edit");
        addButton.setStyle("-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
        addButton.setMinWidth(100);
        addButton.setMaxWidth(100);
        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle("-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
        cancelButton.setMinWidth(100);
        cancelButton.setMaxWidth(100);
        
        TextField priorityField = new TextField(""+task.getPriority());
        priorityField.setPromptText("Enter a priority #");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String dueDateString = dateFormat.format(task.getDueDate());
        TextField dueField = new TextField(dueDateString);
        dueField.setPromptText("MM/DD/YYYY");
        TextField dateField;
        if(!task.getStatus().equals("Incomplete")) {
        	String statusDateString = dateFormat.format(task.getStatusDate());
        	dateField = new TextField(statusDateString);
        }else {
        	dateField = new TextField();
        }
        dateField.setPromptText("MM/DD/YYYY");
        
        ObservableList<String> progressOptions =
        	FXCollections.observableArrayList(
    			"In Progress",
    			"Incomplete",
    			"Completed"
        	);
        final ComboBox progressComboBox = new ComboBox(progressOptions);
        progressComboBox.getSelectionModel().select(task.getStatus());
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
        if(task.getStatus().equals("Incomplete")) {
        	dateHBox.setVisible(false);
        }else if(task.getStatus().equals("In Progress")) {
        	dateLabel.setText("Date Started");
        }else {
        	dateLabel.setText("Date Finished");
        }
        // Adds the elements to the left and right VBoxes
        leftVBox.getChildren().addAll(priorityHBox, dueHBox);
        rightVBox.getChildren().addAll(statusHBox, dateHBox);
        
        buttonHBox.getChildren().addAll(addButton, cancelButton);
        buttonHBox.setPadding(new Insets(25, 10, 5, 10));
        buttonHBox.setSpacing(10);
        buttonHBox.setAlignment(Pos.CENTER);
        
        middleHBox.getChildren().addAll(leftVBox, rightVBox);
        
        root.getChildren().addAll(descriptionHBox, middleHBox, buttonHBox);
       
        Object[] inputs = {priorityField, dueField, progressComboBox, dateField, descriptionField};
        EventHandler editDialogHandler = Handler.editDialogHandler(Main.scrollVBox, Main.sortComboBox, Main.pageLabel, Main.pageNum, Main.taskList, task, inputs, window);
        addButton.setOnAction(editDialogHandler);
        EventHandler statusHandler = Handler.statusHandler(dateHBox, progressComboBox, dateLabel);
        progressComboBox.setOnAction(statusHandler);
        EventHandler cancelHandler = Handler.cancelHandler(window);
        cancelButton.setOnAction(cancelHandler);
        window.setScene(new Scene(root, 540, 230));
        return window;
    }
} 