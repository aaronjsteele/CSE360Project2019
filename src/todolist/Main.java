package todolist;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
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
        
        addButton.setMaxWidth(Double.MAX_VALUE);
        printButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setMaxWidth(Double.MAX_VALUE);
        loadButton.setMaxWidth(Double.MAX_VALUE);
        
        BorderPane root = new BorderPane();
        VBox buttonBox = new VBox();
        buttonBox.setPrefWidth(150);   
        buttonBox.setSpacing(10);
        buttonBox.setPadding(new Insets(10, 10, 10, 10)); 
        buttonBox.getChildren().addAll(addButton, printButton, saveButton, loadButton);
        
        
        AnchorPane anchorCenter = new AnchorPane();
        
        ScrollPane scrollWindow = new ScrollPane();
        VBox scrollVBox = new VBox();
       
        scrollWindow.setContent(scrollVBox);
        
        scrollWindow.setPrefWidth(550);
        scrollVBox.setFillWidth(true);
                
        for (int i = 1; i < 7; ++i) {
        	Task newTask = new Task();
        	ToDoItem newToDoItem = new ToDoItem(newTask);
        	newToDoItem.setPrefWidth(550);
        	newToDoItem.setMinWidth(550);
        	newToDoItem.setMaxWidth(550);
        	newToDoItem.setPrefHeight(125);
        	scrollVBox.getChildren().add(newToDoItem);
        }
        
        anchorCenter.getChildren().add(scrollWindow);
        AnchorPane.setTopAnchor(scrollWindow, 0.0);
        AnchorPane.setBottomAnchor(scrollWindow, 0.0);
        AnchorPane.setLeftAnchor(scrollWindow, 0.0);
        AnchorPane.setRightAnchor(scrollWindow, 0.0);
        
        VBox titleBox = new VBox();
        Label titleLabel = new Label("To-Do Items");
        
        root.setLeft(buttonBox);
        root.setCenter(anchorCenter);
        
        Scene scene = new Scene(root, 710, 650);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static class ToDoItem extends BorderPane {
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
    		
    		setPrefHeight(50);
    		setStyle(toDoItemCssLayout);
    	}
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }
}