package todolist;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        
        String blackBorderStyle = "-fx-border-color: black";
        
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
        
        VBox scrollVBox = new VBox();
        scrollVBox.setPrefWidth(550);
        scrollVBox.setPrefHeight(500);
        scrollVBox.setMaxHeight(500);
                
        for (int i = 1; i < 5; ++i) {
        	Task newTask = new Task();
        	ToDoItem newToDoItem = new ToDoItem(newTask);
        	newToDoItem.setPrefWidth(550);
        	newToDoItem.setMinWidth(550);
        	newToDoItem.setMaxWidth(550);
        	newToDoItem.setPrefHeight(125);
        	scrollVBox.getChildren().add(newToDoItem);
        }
        
        HBox titleBox = new HBox();
        Label titleLabel = new Label("To-Do Items");
        Region leftTitleRegion = new Region();
        leftTitleRegion.setMinWidth(160);
        leftTitleRegion.setMaxWidth(160);
        
        Region rightTitleRegion = new Region();
        titleBox.getChildren().addAll(leftTitleRegion, titleLabel, rightTitleRegion);
        titleBox.setPrefHeight(25);
        titleBox.setMinHeight(25);
        titleBox.setMaxHeight(25);
        
        titleBox.setAlignment(Pos.CENTER_LEFT);
        
        // Creates the bottom portion of the main UI
        HBox bottomSortBox = new HBox();
        bottomSortBox.setPrefHeight(50);
        bottomSortBox.setMaxHeight(50);
        bottomSortBox.setMinHeight(50);
        
        Label sortLabel = new Label("Sort By:");

        ObservableList<String> sortOptions =
        	FXCollections.observableArrayList(
    			"Priority",
    			"Due Date",
    			"Name"
        	);
        final ComboBox sortComboBox = new ComboBox(sortOptions);
        Region midSpaceRegion = new Region();
        Region leftSpaceRegion = new Region();
        Region rightSpaceRegion = new Region();
        Button leftArrow = new Button("<");
        Button rightArrow = new Button(">");
        Label pageLabel = new Label("Showing 1-4 of 13");
        
        leftSpaceRegion.setMinWidth(150);
        leftSpaceRegion.setMaxWidth(150);
        
        rightSpaceRegion.setMinWidth(20);
        rightSpaceRegion.setMaxWidth(20);
        
        bottomSortBox.getChildren().addAll(leftSpaceRegion, sortLabel, sortComboBox,
        		midSpaceRegion, leftArrow, pageLabel, rightArrow, rightSpaceRegion);
        bottomSortBox.setAlignment(Pos.CENTER_LEFT);
        bottomSortBox.setSpacing(10);
        bottomSortBox.setPadding(new Insets(5, 5, 5, 5));
        HBox.setHgrow(midSpaceRegion, Priority.ALWAYS);
        
        root.setTop(titleBox);
        root.setLeft(buttonBox);
        root.setCenter(scrollVBox);
        root.setBottom(bottomSortBox);
        
        scrollVBox.setStyle(blackBorderStyle);
        
        BorderPane.setAlignment(scrollVBox, Pos.TOP_CENTER);
        BorderPane.setAlignment(bottomSortBox, Pos.TOP_CENTER);
        
        Scene scene = new Scene(root, 710, 600);
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