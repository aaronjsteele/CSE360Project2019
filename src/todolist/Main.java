package todolist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
        
        AnchorPane anchorCenter = new AnchorPane();
        
        ScrollPane scrollWindow = new ScrollPane();
        VBox scrollVBox = new VBox();
       
        scrollWindow.setContent(scrollVBox);
        
        scrollWindow.setPrefWidth(400);
        scrollWindow.setMinWidth(300);
        scrollVBox.setMinWidth(Control.USE_PREF_SIZE);
                
        for (int i = 1; i < 4; ++i) {
        	Task newTask = new Task();
        	ToDoItem newToDoItem = new ToDoItem(newTask);
        	newToDoItem.setPrefWidth(500);
        	scrollVBox.getChildren().add(newToDoItem);
        }
        
        anchorCenter.getChildren().add(scrollWindow);
        AnchorPane.setTopAnchor(scrollWindow, 0.0);
        AnchorPane.setBottomAnchor(scrollWindow, 0.0);
        AnchorPane.setLeftAnchor(scrollWindow, 0.0);
        AnchorPane.setRightAnchor(scrollWindow, 0.0);
        
        root.setLeft(buttonBox);
        root.setCenter(anchorCenter);
        
        Scene scene = new Scene(root, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static class ToDoItem extends BorderPane {
    	public ToDoItem(Task inputTask) {
    		String toDoItemCssLayout = "-fx-border-color: red;\n" +
                    "-fx-border-insets: 5;\n" +
                    "-fx-border-width: 3;\n" +
                    "-fx-border-style: dashed;\n";
    		
    		Label priorityLabel = new Label("Priority #");
    		GridPane textGrid = new GridPane();
    		textGrid.setPrefWidth(400);
    		
    		VBox leftTextBox = new VBox();
    		leftTextBox.getChildren().add(new Label("test 1"));
    		leftTextBox.getChildren().add(new Label("test 2"));
    		
    		VBox rightTextBox = new VBox();
    		rightTextBox.getChildren().add(new Label("test 3"));
    		rightTextBox.getChildren().add(new Label("test 4"));
    		
    		textGrid.add(leftTextBox, 0, 0);
    		textGrid.add(rightTextBox, 1, 0);
    		textGrid.setAlignment(Pos.CENTER);
    		
    		HBox bottomButtonBox = new HBox();
    		Button editButton = new Button("Edit");
    		Button deleteButton = new Button("Delete");
    		editButton.setMinWidth(80.0);
    		editButton.setPrefWidth(80.0);
    		editButton.setMaxWidth(80.0);
    		bottomButtonBox.getChildren().add(editButton);
    		bottomButtonBox.getChildren().add(deleteButton);
    		
    		bottomButtonBox.setHgrow(deleteButton, Priority.ALWAYS);
    		setTop(priorityLabel);
    		setCenter(textGrid);
    		setBottom(bottomButtonBox);
    		
    		setPrefHeight(50);
    		setStyle(toDoItemCssLayout);
    	}
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }
}