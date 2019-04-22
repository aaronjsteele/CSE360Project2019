package todolist;

import javafx.geometry.HPos;
import javafx.geometry.Insets;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
        
        ArrayList<Task> taskList = new ArrayList<Task>();
        
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
        buttonBox.setMinHeight(500);
        buttonBox.setMaxHeight(500);
        
        VBox scrollVBox = new VBox();
        scrollVBox.setFillWidth(true);
        scrollVBox.setMinHeight(500);
        scrollVBox.setMaxHeight(500);
        
        EventHandler loadHandler = Handler.loadHandler(scrollVBox, taskList);
        loadButton.setOnAction(loadHandler);

        HBox titleBox = new HBox();
        Label titleLabel = new Label("To-Do Items");
        Region leftTitleRegion = new Region();
        leftTitleRegion.setMinWidth(160);
        leftTitleRegion.setMaxWidth(160);
        
        Region rightTitleRegion = new Region();
        titleBox.getChildren().addAll(leftTitleRegion, titleLabel, rightTitleRegion);
        titleBox.setPrefHeight(50);
        titleBox.setMinHeight(50);
        titleBox.setMaxHeight(50);
        
        titleBox.setAlignment(Pos.CENTER_LEFT);
        

        // Creates the bottom portion of the main UI
        HBox bottomSortBox = new HBox();
        bottomSortBox.setPrefHeight(40);
        bottomSortBox.setMaxHeight(40);
        bottomSortBox.setMinHeight(40);
        
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
        
        titleBox.setStyle(blackBorderStyle);
        bottomSortBox.setStyle(blackBorderStyle);
        scrollVBox.setStyle(blackBorderStyle);
        buttonBox.setStyle(blackBorderStyle);
        
        BorderPane.setAlignment(scrollVBox, Pos.TOP_CENTER);
        BorderPane.setAlignment(bottomSortBox, Pos.TOP_CENTER);
        
        Scene scene = new Scene(root, 700, 580);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }
}