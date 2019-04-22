package todolist;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DisplayDialog {
	public static Stage createDisplayDialog(ArrayList<Task> taskList) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Display");
        window.setWidth(700);
        window.setHeight(400);
        window.initStyle(StageStyle.UTILITY);
        window.setResizable(false);
        
        ArrayList<TaskStrings> taskStringsList = new ArrayList<TaskStrings>();
        
        for (int i = 0; i < taskList.size(); ++i) {
        	taskStringsList.add(new TaskStrings(taskList.get(i)));
        }
        
        
        VBox root = new VBox();
        
        ScrollPane scrollPane = new ScrollPane();
        
        TableView taskTable = new TableView();
        
        TableColumn<Integer, TaskStrings> column1 = new TableColumn<>("Priority");
        column1.setCellValueFactory(new PropertyValueFactory<>("priority"));
        
        TableColumn<String, TaskStrings> column2 = new TableColumn<>("Description");
        column2.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        TableColumn<String, TaskStrings> column3 = new TableColumn<>("Due Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("dueDate"));

        TableColumn<String, TaskStrings> column4 = new TableColumn<>("Start/Finish Date");
        column4.setCellValueFactory(new PropertyValueFactory<>("statusDate"));

        TableColumn<String, TaskStrings> column5 = new TableColumn<>("Status");
        column5.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        taskTable.getColumns().addAll(column1, column2, column3, column5, column4);
        
        // Add items from ArrayList here!
        taskTable.setItems(FXCollections.observableArrayList(taskStringsList));
        
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(root);
        
        root.getChildren().add(taskTable);
        window.setScene(new Scene(root, 700, 400));
        return window;
	}

}
