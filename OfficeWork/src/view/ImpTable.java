package view;

import java.sql.SQLException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DataStore;
import model.Documents;
import model.Implementer;

public class ImpTable {

	public static void AddWindow(DataStore data) throws SQLException {
		
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		VBox verBox = new VBox();
		
		TableView<Implementer> table = new TableView<>();
		
		TableColumn<Implementer, Integer> id = new TableColumn<>("№ ");
		id.setMinWidth(50);
		id.setCellValueFactory(new PropertyValueFactory<>("idEmp"));
		
		TableColumn<Implementer, String> secName = new TableColumn<>("Фамилия");
		secName.setMinWidth(100);
		secName.setCellValueFactory(new PropertyValueFactory<>("secName"));
		
		TableColumn<Implementer, String> position = new TableColumn<>("Должность ");
		position.setMinWidth(50);
		position.setCellValueFactory(new PropertyValueFactory<>("position"));
		
		TableColumn<Implementer, Integer> tel = new TableColumn<>("Телефон");
		tel.setMinWidth(50);
		tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
		
		TableColumn<Implementer, String> eMail = new TableColumn<>("Мыло ");
		eMail.setMinWidth(50);
		eMail.setCellValueFactory(new PropertyValueFactory<>("eMail"));

		table.getColumns().addAll(id,secName,position,tel,eMail);
		table.setItems(data.getImpDatabaseData());
		verBox.getChildren().add(table);
		
		Scene scene = new Scene(verBox, 500, 150);
		window.setScene(scene);
		window.setTitle("Окно добавления информации!");
		window.showAndWait();
	}
}
