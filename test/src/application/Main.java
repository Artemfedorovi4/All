package application;
	
import java.awt.Checkbox;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;

public class Main extends Application {
	
	
	public void attent(String error){
		Alert dil = new Alert(AlertType.INFORMATION);
		dil.setTitle("Info");
		dil.setHeaderText(error);
		dil.showAndWait();
	}
	private TableView<ColCell> table = new TableView<ColCell>();
	 private final ObservableList<ColCell> data =
	            FXCollections.observableArrayList(
	            new ColCell("Jacob"),
	            new ColCell("Isabella"),
	            new ColCell("Ethan"),
	            new ColCell("Emma"),
	            new ColCell("Michael"));
	
	@Override
	public void start(Stage primaryStage) {
			
		String error = "Неверный ввод";
		GridPane root = new GridPane();// создание сетки GridPane
		
		Scene scene = new Scene(root, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	public static class ColCell {
		private final SimpleStringProperty firstColumn;
	    private final SimpleStringProperty lastColumn;
	 
        private ColCell(String fColl) {
           this.firstColumn = new SimpleStringProperty(fColl);
          this.lastColumn = new SimpleStringProperty("");
       }
	
       public String getFirst() {
           return firstColumn.get();
       }

       public void setfirstColumn(String fColl) {
           firstColumn.set(fColl);
       }
       public String getSecond() {
           return lastColumn.get();
       }

       public void setsecondColumn(String sColl) {
    	   lastColumn.set(sColl);
       }
   }
}