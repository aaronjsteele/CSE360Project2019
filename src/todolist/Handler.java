package todolist;


import javafx.geometry.HPos;
import javafx.geometry.Insets;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import todolist.Main.ToDoItem;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;
public class Handler {
	public Handler() {
		
	}
	public static EventHandler loadHandler(VBox scrollVBox, Label pageLabel, IntHolder pageNum, ArrayList<Task> taskList) {
		EventHandler eventHandler = new EventHandler() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				scrollVBox.getChildren().clear();
				taskList.clear();
				ArrayList<Task> tempList = Task.loadFile();
				for(Task task : tempList) {
					taskList.add(task);
				}
				
				for(int index = 0; index < taskList.size() && index < 4; index++) {
					Task newTask = taskList.get(index);
					ToDoItem newToDoItem = new ToDoItem(newTask);
					scrollVBox.getChildren().add(newToDoItem);
				}
				pageNum.setNumber(0);
				int taskSize = taskList.size();
				String output = "Showing ";
				if(taskSize > 0) {
					output += "1-";
				}else {
					output += "0-";
				}
				if(taskSize >= 4) {
					output += "4 of ";
				}else {
					output += taskSize + " of ";
				}
				output += taskSize;
				pageLabel.setText(output);
			}
		};
		return eventHandler;
	}
	public static EventHandler leftHandler(VBox scrollVBox, Label pageLabel, IntHolder pageNum, ArrayList<Task> taskList) {
		EventHandler eventHandler = new EventHandler() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				int currentPage = pageNum.getNumber();
				if(currentPage != 0) {
					pageNum.setNumber(currentPage - 1);
					scrollVBox.getChildren().clear();
					for(int index = 0; index < 4; index++) {
						Task newTask = taskList.get(index + (4 * currentPage));
						ToDoItem newToDoItem = new ToDoItem(newTask);
						scrollVBox.getChildren().add(newToDoItem);
					}
					int taskSize = taskList.size();
					String output = "Showing ";
					output += (4 * currentPage) + 1;
					output += "4 of ";
					output += taskSize;
					pageLabel.setText(output);
				}
			}
			
		};
		return eventHandler;
	}
	public static EventHandler addHandler(Stage stage) {
		EventHandler eventHandler = new EventHandler() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				stage.show();
			}
			
		};
		return eventHandler;
	}
}
