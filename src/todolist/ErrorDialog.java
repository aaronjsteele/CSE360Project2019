package todolist;

import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ErrorDialog {
	public static Stage createErrorDialog(String errorMessage) {
		Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Error");
        window.setWidth(450);
        window.setHeight(130);
        window.initStyle(StageStyle.UTILITY);
        window.setResizable(false);
        
        Label errorLabel = new Label(errorMessage);
        Button confirmButton = new Button("OK");
        EventHandler confirmHandler = Handler.cancelHandler(window);
        confirmButton.setOnAction(confirmHandler);
        
        
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10,10,10,10));
        root.setSpacing(10);
        root.getChildren().addAll(errorLabel, confirmButton);
        
        window.setScene(new Scene(root, 450, 130));
        return window;
	}
}
