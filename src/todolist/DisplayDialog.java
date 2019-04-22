package todolist;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.ScrollPane;
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
        window.setWidth(400);
        window.setHeight(600);
        window.initStyle(StageStyle.UTILITY);
        window.setResizable(false);
        
        VBox root = new VBox();
        
        ScrollPane scrollPane = new ScrollPane();
        
        TableView taskTable = new TableView();
        
        TableColumn<Integer, Task> column1 = new TableColumn<>("Priority");
        column1.setCellValueFactory(new PropertyValueFactory<>("priority"));
        
        TableColumn<String, Task> column2 = new TableColumn<>("Description");
        column1.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        TableColumn<Date, Task> column3 = new TableColumn<>("Due Date");
        column1.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        
        TableColumn<Date, Task> column4 = new TableColumn<>("Start/Finish Date");
        column1.setCellValueFactory(new PropertyValueFactory<>("statusDate"));
        TableColumn<String, Task> column5 = new TableColumn<>("Status");
        column1.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        taskTable.getColumns().addAll(column1, column2, column3, column5, column4);
        
        // Add items from ArrayList here!
        taskTable.setItems(FXCollections.observableArrayList(taskList));
        
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(root);
        
        root.getChildren().add(taskTable);
        window.setScene(new Scene(root, 400, 600));
        return window;
	}

}
