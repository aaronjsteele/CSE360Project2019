package todolist;


import javafx.geometry.HPos;
import javafx.geometry.Insets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import todolist.ToDoItem;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class Handler {
	public Handler() {
		
	}
	public static EventHandler sortHandler(VBox scrollVBox, ComboBox sortComboBox, Label pageLabel, IntHolder pageNum, ArrayList<Task> taskList) {
		EventHandler eventHandler = new EventHandler() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				//stage.showAndWait();
				pageNum.setNumber(0);
				String sortBy = (String)sortComboBox.getValue();
				Task.sort(taskList, sortBy);
				scrollVBox.getChildren().clear();
				for(int index = 0; index < taskList.size() && index < 4; index++) {
					Task newTask = taskList.get(index);
					ToDoItem newToDoItem = new ToDoItem(newTask);
					scrollVBox.getChildren().add(newToDoItem);
				}
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
	public static EventHandler leftHandler(VBox scrollVBox,  Label pageLabel, IntHolder pageNum, ArrayList<Task> taskList) {
		EventHandler eventHandler = new EventHandler() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				int currentPage = pageNum.getNumber();
				if(currentPage != 0) {
					pageNum.setNumber(currentPage - 1);
					currentPage--;
					scrollVBox.getChildren().clear();
					for(int index = 0; index < 4; index++) {
						Task newTask = taskList.get(index + (4 * currentPage));
						ToDoItem newToDoItem = new ToDoItem(newTask);
						scrollVBox.getChildren().add(newToDoItem);
					}
					int taskSize = taskList.size();
					String output = "Showing ";
					output += ((4 * currentPage) + 1) + "-";
					output += ((4 * currentPage) + 4) +" of ";
					output += taskSize;
					pageLabel.setText(output);
				}
			}
			
		};
		return eventHandler;
	}
	public static EventHandler rightHandler(VBox scrollVBox, Label pageLabel, IntHolder pageNum, ArrayList<Task> taskList) {
		EventHandler eventHandler = new EventHandler() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				int currentPage = pageNum.getNumber();
				if((currentPage + 1) * 4 < taskList.size()) {
					pageNum.setNumber(currentPage + 1);
					currentPage++;
					scrollVBox.getChildren().clear();
					for(int index = 0; index < 4 && index < taskList.size() - currentPage * 4; index++) {
						Task newTask = taskList.get(index + (4 * currentPage));
						ToDoItem newToDoItem = new ToDoItem(newTask);
						scrollVBox.getChildren().add(newToDoItem);
					}
					int taskSize = taskList.size();
					String output = "Showing ";
					output += ((4 * currentPage) + 1) + "-";
					if(taskSize - (4*(currentPage+1)) >= 0) {
						output += ((4 * currentPage) + 4) +" of ";
					}else {
						output += taskSize + " of ";
					}
					output += taskSize;
					pageLabel.setText(output);
				}
			}
			
		};
		return eventHandler;
	}
	public static EventHandler addHandler() {
		EventHandler eventHandler = new EventHandler() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Stage stage = AddDialog.createAddDialog();
				stage.showAndWait();
			}
			
		};
		return eventHandler;
	}
	public static EventHandler addDialogHandler(VBox scrollVBox, ComboBox sortComboBox,Label pageLabel, IntHolder pageNum, ArrayList<Task> taskList, Object[] inputs, Stage stage) {
		EventHandler eventHandler = new EventHandler() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				TextField priorityField = (TextField)inputs[0];
				TextField dueField = (TextField)inputs[1];
				ComboBox progressComboBox = (ComboBox)inputs[2];
				TextField dateField = (TextField)inputs[3];
				TextField descriptionField = (TextField)inputs[4];
				
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				String dueDateString = dueField.getText();
				String statusDateString = dateField.getText();
				String status = (String)progressComboBox.getValue();
				String description = descriptionField.getText();
				boolean exists = false;
				for(Task task: Main.taskList) {
					if(task.getDescription().equals(description)) {
						exists = true;
					}
				}
				if(exists) {
					Stage errorWindow = ErrorDialog.createErrorDialog("Please enter a unique description");
					errorWindow.showAndWait();
				}else {
					int priority = -1;
					Integer prior = tryParse(priorityField.getText());
					if(prior != null) {
						priority = prior.intValue();
					}
					if(prior != null && priority > 0) {
						pageNum.setNumber(0);
						scrollVBox.getChildren().clear();
						try {
							Date dueDate = dateFormat.parse(dueDateString);
							Date statusDate = null;
							if(status != "Incomplete") {
								statusDate = dateFormat.parse(statusDateString);
							}
							Task.addTask(taskList, priority, description, dueDate, statusDate, status);
							String sortBy = (String)sortComboBox.getValue();
							Task.sort(taskList, sortBy);
							for(int index = 0; index < taskList.size() && index < 4; index++) {
								Task newTask = taskList.get(index);
								ToDoItem newToDoItem = new ToDoItem(newTask);
								scrollVBox.getChildren().add(newToDoItem);
							}
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
							stage.close();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Something went horribly wrong");
						}
					}else {
						Stage errorWindow = ErrorDialog.createErrorDialog("Please enter a valid positive integer for the priority");
						errorWindow.showAndWait();
					}
				}
			}
			
		};
		return eventHandler;
	}
	public static EventHandler editHandler(Stage stage) {
		EventHandler eventHandler = new EventHandler() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				stage.showAndWait();
			}
			
		};
		return eventHandler;
	}
	public static EventHandler displayAllHandler(ArrayList<Task> taskList) {
		EventHandler eventHandler = new EventHandler() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Stage stage = DisplayDialog.createDisplayDialog(taskList);
				stage.showAndWait();
			}
			
		};
		return eventHandler;
	}
	public static Integer tryParse(String text) {
		try {
			return Integer.parseInt(text);
		}catch(NumberFormatException e){
			return null;
		}
	}
	public static EventHandler editDialogHandler(VBox scrollVBox, ComboBox sortComboBox,Label pageLabel, IntHolder pageNum, ArrayList<Task> taskList, Task task, Object[] inputs, Stage stage) {
		EventHandler eventHandler = new EventHandler() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				TextField priorityField = (TextField)inputs[0];
				TextField dueField = (TextField)inputs[1];
				ComboBox progressComboBox = (ComboBox)inputs[2];
				TextField dateField = (TextField)inputs[3];
				TextField descriptionField = (TextField)inputs[4];
				SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
				String dueDateString = dueField.getText();
				String statusDateString = dateField.getText();
				String status = (String)progressComboBox.getValue();
				String description = descriptionField.getText();
				boolean exists = false;
				for(Task iteratetask: Main.taskList) {
					if(iteratetask.getDescription().equals(description)&& iteratetask != task) {
						exists = true;
					}
				}
				if(exists) {
					Stage errorWindow = ErrorDialog.createErrorDialog("Please enter a unique description");
					errorWindow.showAndWait();
				}else {
					int priority = -1;
					Integer prior = tryParse(priorityField.getText());
					if(prior != null) {
						priority = prior.intValue();
					}
					if(prior != null && priority > 0) {
						pageNum.setNumber(0);
						scrollVBox.getChildren().clear();
						try {
							Date dueDate = dateFormat.parse(dueDateString);
							Date statusDate = null;
							if(status != "Incomplete") {
								statusDate = dateFormat.parse(statusDateString);
							}
							Task.editTask(task, Main.taskList, priority, description, dueDate, statusDate, status);
							String sortBy = (String)sortComboBox.getValue();
							Task.sort(taskList, sortBy);
							for(int index = 0; index < taskList.size() && index < 4; index++) {
								Task newTask = taskList.get(index);
								ToDoItem newToDoItem = new ToDoItem(newTask);
								scrollVBox.getChildren().add(newToDoItem);
							}
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
							stage.close();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							System.out.println("Something went horribly wrong");
						}
					}else {
						Stage errorWindow = ErrorDialog.createErrorDialog("Please enter a valid positive integer for the priority");
						errorWindow.showAndWait();
					}
				}
			}
			
		};
		return eventHandler;
	}
	public static EventHandler deleteHandler(Task task) {
		EventHandler eventHandler = new EventHandler() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Main.pageNum.setNumber(0);
				Main.scrollVBox.getChildren().clear();
				Task.deleteTask(task, Main.taskList);
				String sortBy = (String)Main.sortComboBox.getValue();
				Task.sort(Main.taskList, sortBy);
				for(int index = 0; index < Main.taskList.size() && index < 4; index++) {
					Task newTask = Main.taskList.get(index);
					ToDoItem newToDoItem = new ToDoItem(newTask);
					Main.scrollVBox.getChildren().add(newToDoItem);
				}
				int taskSize = Main.taskList.size();
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
				Main.pageLabel.setText(output);
			}
			
		};
		return eventHandler;
	}
	public static EventHandler statusHandler(HBox dateHBox, ComboBox progressComboBox, Label dateLabel) {
		EventHandler eventHandler = new EventHandler() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				String status = (String)progressComboBox.getValue();
				if(status.equals("Incomplete")) {
					dateHBox.setVisible(false);
				}else {
					dateHBox.setVisible(true);
					if(status.equals("In Progress")) {
						dateLabel.setText("Date Started");
					}else {
						dateLabel.setText("Date Finished");
					}
				}
			}
			
		};
		return eventHandler;
	}
	public static EventHandler cancelHandler(Stage stage) {
		EventHandler eventHandler = new EventHandler() {
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				stage.close();
			}
		};
		return eventHandler;
	}

	public static EventHandler printHandler(ArrayList<Task> taskList) {
		EventHandler eventHandler = new EventHandler() {
			
			@Override
			public void handle(Event event) {
				Task.savePrintFile(taskList);
			}
		};
		return eventHandler;
	}
	public static EventHandler saveHandler(ArrayList<Task> taskList) {
		EventHandler eventHandler = new EventHandler() {
			
			@Override
			public void handle(Event event) {
				Task.saveFile(taskList);
			}
		};
		return eventHandler;
	}
	public static EventHandler resetHandler() {
		EventHandler eventHandler = new EventHandler() {

			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				Main.scrollVBox.getChildren().clear();
				Main.pageNum.setNumber(0);
				Main.pageLabel.setText("Showing 0-0 of 0");
				Main.taskList.clear();
			}
			
		};
		return eventHandler;
	}
}
