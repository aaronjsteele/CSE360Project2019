package todolist;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToDoItem  extends BorderPane{
	public ToDoItem(Task inputTask) {
		String toDoItemCssLayout = 
				"-fx-background-color: #ecebe9,\n" + 
									"rgba(0,0,0,0.05),\n" + 
									"linear-gradient(#dcca8a, #c7a740),\n" + 
									"linear-gradient(#f9f2d6 0%, #f4e5bc 20%, #e6c75d 80%, #e2c045 100%),\n" + 
									"linear-gradient(#f6ebbe, #e6c34d);\n" +
			    "-fx-background-insets: 0,1,2,3;\n" +
			    "-fx-background-radius: 3,2,2,2;\n" +
			    "-fx-padding: 12 30 12 30;\n" +
			    "-fx-text-fill: white;\n" +
			    "-fx-font-size: 12px;";
		
		
		Label priorityLabel = new Label("Priority #" + inputTask.getPriority());
		priorityLabel.setStyle("-fx-text-fill: #654b00;\n" + 
			   	"-fx-font-weight: bold;\n" + 
				"-fx-font-size: 14px;");
		Label descriptionLabel = new Label("Description: " + inputTask.getDescription());
		descriptionLabel.setStyle("-fx-text-fill: #654b00;\n" + 
			   	"-fx-font-weight: bold;\n" + 
				"-fx-font-size: 14px;");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String dueDateString = dateFormat.format(inputTask.getDueDate());
		Label dueLabel = new Label("Due Date: " + dueDateString);
		dueLabel.setStyle("-fx-text-fill: #654b00;\n" + 
			   	"-fx-font-weight: bold;\n" + 
				"-fx-font-size: 14px;");
		String status = inputTask.getStatus();
		Label statusLabel = new Label("Status: " + status);
		statusLabel.setStyle("-fx-text-fill: #654b00;\n" + 
			   	"-fx-font-weight: bold;\n" + 
				"-fx-font-size: 14px;");
		Date statusDate = inputTask.getStatusDate();
		Label completeLabel;
		if(status.equals("Complete")) {
			String statusDateString = dateFormat.format(statusDate);
			completeLabel = new Label("Date Finished: " + statusDateString);
			completeLabel.setStyle("-fx-text-fill: #654b00;\n" + 
				   	"-fx-font-weight: bold;\n" + 
					"-fx-font-size: 14px;");
		}else if(status.equals("In Progress")) {
			String statusDateString = dateFormat.format(statusDate);
			completeLabel = new Label("Date Started: " + statusDateString);
			completeLabel.setStyle("-fx-text-fill: #654b00;\n" + 
				   	"-fx-font-weight: bold;\n" + 
					"-fx-font-size: 14px;");
		}else {
			completeLabel = new Label("");
			completeLabel.setStyle("-fx-text-fill: #654b00;\n" + 
				   	"-fx-font-weight: bold;\n" + 
					"-fx-font-size: 14px;");
		}
		
		HBox midHBox = new HBox();
		
		VBox leftTextBox = new VBox();
		leftTextBox.getChildren().addAll(priorityLabel, descriptionLabel, dueLabel);
		leftTextBox.setPadding(new Insets(10,5,5,15));
		
		VBox rightTextBox = new VBox();
		rightTextBox.getChildren().addAll(statusLabel, completeLabel);
		rightTextBox.setPadding(new Insets(10,15,5,5));
		
		Region midRegion = new Region();
		
		midHBox.getChildren().addAll(leftTextBox, midRegion, rightTextBox);
		HBox.setHgrow(midRegion, Priority.ALWAYS);
		
		HBox bottomButtonBox = new HBox();
		Button editButton = new Button("Edit");
		editButton.setStyle("-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
		Stage editStage = EditDialog.createEditDialog(inputTask);
		EventHandler editHandler = Handler.editHandler(editStage);
		editButton.setOnAction(editHandler);
		Button deleteButton = new Button("Delete");
		deleteButton.setStyle("-fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );");
		EventHandler deleteHandler = Handler.deleteHandler(inputTask);
		deleteButton.setOnAction(deleteHandler);
		deleteButton.setPrefWidth(80.0);
		editButton.setPrefWidth(80.0);
		bottomButtonBox.getChildren().addAll(editButton, deleteButton);
		bottomButtonBox.setSpacing(10);
		bottomButtonBox.setPadding(new Insets(5, 10, 10, 15));

		setCenter(midHBox);
		setBottom(bottomButtonBox);

		setStyle(toDoItemCssLayout);
		
		setMinWidth(550);
    	setMaxWidth(550);
    	setMinHeight(125);
    	setMaxHeight(125);
	}
}
