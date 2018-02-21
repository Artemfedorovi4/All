package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TabeledLog extends TableView<Request> {
	private ObservableList<Request> data = FXCollections.observableArrayList();
	
	public TabeledLog() {
		this.setMaxSize(600, 200);
		TableColumn<Request, String> firstNameCol = new TableColumn<>("name");
		firstNameCol.setMinWidth(200);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("requestTail"));

		TableColumn<Request, String> compositKoly = new TableColumn<>("method");
		compositKoly.setMinWidth(100);
		compositKoly.setCellValueFactory(new PropertyValueFactory<Request, String>("method"));

		TableColumn<Request, String> thirdCol = new TableColumn<>("status");
		thirdCol.setMinWidth(80);
		thirdCol.setCellValueFactory(new PropertyValueFactory<>("status"));

		TableColumn<Request, String> fourthCol = new TableColumn<>("domain");
		fourthCol.setMinWidth(200);
		fourthCol.setCellValueFactory(new PropertyValueFactory<>("url"));
		this.setOnMouseClicked(e->{
			if(e.getClickCount() == 2) {
				RequestWindow.AddWindow(this.getSelectionModel().getSelectedItem());
			}
		});
		
		this.getColumns().addAll(firstNameCol, compositKoly, thirdCol, fourthCol);
		this.setItems(data);
	}
	
	public void addTL(Request req) {
		data.add(req);
	}
}
