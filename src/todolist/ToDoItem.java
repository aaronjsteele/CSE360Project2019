package todolist;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ToDoItem  extends BorderPane{
	public ToDoItem(Task inputTask) {
		String toDoItemCssLayout = "-fx-border-color: red;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n" +
                "-fx-border-style: dashed;\n";
		
		
		Label priorityLabel = new Label("Priority #");
		Label descriptionLabel = new Label("Description: Sample");
		Label dueLabel = new Label("Due Date: 4/22/19");
		Label statusLabel = new Label("Status: Incomplete");
		Label completeLabel = new Label("Date Finished: 4/22/19");
		
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
		Button deleteButton = new Button("Delete");
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
