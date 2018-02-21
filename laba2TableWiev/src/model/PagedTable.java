package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PagedTable {

	TableView<Person> table;
	private DataStore data;
	private int rowsPerPage;
	int itemsPerPage = 1;
	int pageIndex = 0;
	int endPage = 0, begPage = 0;

	public PagedTable() {
		rowsPerPage = 10;
		table = new TableView<Person>();
		TableColumn<Person, String> firstNameCol = new TableColumn<>("ФИО");
		firstNameCol.setMinWidth(200);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));

		TableColumn<Person, String> compositKoly = new TableColumn<>("Состав");
		compositKoly.setMinWidth(100);
		compositKoly.setCellValueFactory(new PropertyValueFactory<Person, String>("compoSition"));

		TableColumn<Person, String> thirdCol = new TableColumn<>("Позиция");
		thirdCol.setMinWidth(80);
		thirdCol.setCellValueFactory(new PropertyValueFactory<>("positKol"));

		TableColumn<Person, String> fourthCol = new TableColumn<>("Вид спорта");
		fourthCol.setMinWidth(100);
		fourthCol.setCellValueFactory(new PropertyValueFactory<>("kindOfSportKol"));

		TableColumn<Person, String> fifthCol = new TableColumn<>("Титул");
		fifthCol.setMinWidth(20);
		fifthCol.setCellValueFactory(new PropertyValueFactory<>("titleStr"));

		table.getColumns().addAll(firstNameCol, compositKoly, thirdCol, fourthCol, fifthCol);
		table.getItems().clear();

	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public VBox crEmptyPage() {
		VBox box = new VBox(5);

		table = new TableView<Person>();
		TableColumn<Person, String> firstNameCol = new TableColumn<>("ФИО");
		firstNameCol.setMinWidth(200);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));

		TableColumn<Person, String> compositKoly = new TableColumn<>("Состав");
		compositKoly.setMinWidth(100);
		compositKoly.setCellValueFactory(new PropertyValueFactory<Person, String>("compoSition"));

		TableColumn<Person, String> thirdCol = new TableColumn<>("Позиция");
		thirdCol.setMinWidth(80);
		thirdCol.setCellValueFactory(new PropertyValueFactory<>("positKol"));

		TableColumn<Person, String> fourthCol = new TableColumn<>("Вид спорта");
		fourthCol.setMinWidth(100);
		fourthCol.setCellValueFactory(new PropertyValueFactory<>("kindOfSportKol"));

		TableColumn<Person, String> fifthCol = new TableColumn<>("Титул");
		fifthCol.setMinWidth(20);
		fifthCol.setCellValueFactory(new PropertyValueFactory<>("titleStr"));

		table.getColumns().addAll(firstNameCol, compositKoly, thirdCol, fourthCol, fifthCol);
		table.getItems().clear();

		box.getChildren().add(table);
		return box;
	}

	public VBox pageOut(DataStore data) {
		VBox vBox = new VBox();
		HBox hBox = new HBox();
		HBox hBox2 = new HBox();
		this.data = data;

		createPage(pageIndex, data);
		table.setItems(data.getDataList());
		createPage(pageIndex, data);
		Button nextP = new Button("Next");
		Button prevP = new Button("Prev");
		Button beginP = new Button("Begin");
		Button endP = new Button("End");
		Button changeP = new Button("OK");

		Label label = new Label();
		label.setText(begPage + "/" + endPage);

		nextP.setOnAction(event -> {
			if (pageIndex < endPage - 1) {
				pageIndex++;
				vBox.getChildren().remove(table);
				createPage(pageIndex, data);
				vBox.getChildren().add(table);
			}
			label.setText((pageIndex + 1) + "/" + endPage);
		});

		prevP.setOnAction(event -> {
			if (pageIndex - 1 <= endPage && pageIndex - 1 >= 0) {
				pageIndex--;
				vBox.getChildren().remove(table);
				createPage(pageIndex, data);
				vBox.getChildren().add(table);
			}
			label.setText((pageIndex + 1) + "/" + endPage);
		});

		beginP.setOnAction(event -> {
			vBox.getChildren().remove(table);
			goToBegin(data);
			vBox.getChildren().add(table);
			label.setText((pageIndex + 1) + "/" + endPage);
		});

		endP.setOnAction(event -> {
			vBox.getChildren().remove(table);
			goToEnd(data);
			vBox.getChildren().add(table);
			label.setText((pageIndex + 1) + "/" + endPage);
		});

		TextField rowsPerPage = new TextField();
		rowsPerPage.setPromptText("number of lines");
		rowsPerPage.setMinWidth(40);
		// setRowsPerPage(Integer.parseInt(rowsPerPage.getText()));

		TextField page = new TextField();
		page.setPromptText("Page");
		page.setMinWidth(30);

		changeP.setOnAction(event -> {
			if (!rowsPerPage.getText().equals("")) {
				setRowsPerPage(Integer.parseInt(rowsPerPage.getText()));
				vBox.getChildren().remove(table);
				pageIndex = begPage;
				createPage(pageIndex, data);
				vBox.getChildren().add(table);
				label.setText((pageIndex + 1) + "/" + endPage);
				rowsPerPage.clear();
			} else if (!page.getText().equals("")) {
				if(Integer.parseInt(page.getText())<=endPage&&Integer.parseInt(page.getText())>=1)
						pageIndex=Integer.parseInt(page.getText())-1;
						vBox.getChildren().remove(table);
						createPage(pageIndex, data);
						vBox.getChildren().add(table);
						label.setText((pageIndex + 1) + "/" + endPage);
						page.clear();
			}
		});

		hBox.setAlignment(Pos.CENTER);
		hBox2.setAlignment(Pos.CENTER);
		hBox.getChildren().addAll(beginP, prevP, nextP, endP);
		hBox2.getChildren().addAll(rowsPerPage, changeP, page);

		vBox.getChildren().addAll(hBox, hBox2);
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().add(label);
		vBox.getChildren().add(table);
		return vBox;
	}

	public VBox goToBegin(DataStore data) {
		VBox box = new VBox(5);

		int lastIndex = 0;
		int kolVol = data.getColPers();

		int displace = kolVol % rowsPerPage;

		if (displace > 0) {
			lastIndex = kolVol / rowsPerPage;
		} else {
			lastIndex = kolVol / rowsPerPage - 1;
		}

		endPage = lastIndex + 1;

		table = new TableView<Person>();
		TableColumn<Person, String> firstNameCol = new TableColumn<>("ФИО");
		firstNameCol.setMinWidth(200);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));

		TableColumn<Person, String> compositKoly = new TableColumn<>("Состав");
		compositKoly.setMinWidth(100);
		compositKoly.setCellValueFactory(new PropertyValueFactory<Person, String>("compoSition"));

		TableColumn<Person, String> thirdCol = new TableColumn<>("Позиция");
		thirdCol.setMinWidth(80);
		thirdCol.setCellValueFactory(new PropertyValueFactory<>("positKol"));

		TableColumn<Person, String> fourthCol = new TableColumn<>("Вид спорта");
		fourthCol.setMinWidth(100);
		fourthCol.setCellValueFactory(new PropertyValueFactory<>("kindOfSportKol"));

		TableColumn<Person, String> fifthCol = new TableColumn<>("Титул");
		fifthCol.setMinWidth(20);
		fifthCol.setCellValueFactory(new PropertyValueFactory<>("titleStr"));

		table.getColumns().addAll(firstNameCol, compositKoly, thirdCol, fourthCol, fifthCol);
		table.getItems().clear();
		pageIndex = 0;
		pageIndex = begPage;
		table.setItems(FXCollections.observableArrayList(
				data.getDataList().subList(pageIndex * rowsPerPage, pageIndex * rowsPerPage + rowsPerPage)));

		box.getChildren().add(table);
		return box;
	}

	public VBox goToEnd(DataStore data) {
		VBox box = new VBox(5);
		int lastIndex = 0;
		int kolVol = data.getColPers();

		int displace = kolVol % rowsPerPage;

		if (displace > 0) {
			lastIndex = kolVol / rowsPerPage;
		} else {
			lastIndex = kolVol / rowsPerPage - 1;
		}

		endPage = lastIndex + 1;

		table = new TableView<Person>();
		TableColumn<Person, String> firstNameCol = new TableColumn<>("ФИО");
		firstNameCol.setMinWidth(200);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));

		TableColumn<Person, String> compositKoly = new TableColumn<>("Состав");
		compositKoly.setMinWidth(100);
		compositKoly.setCellValueFactory(new PropertyValueFactory<Person, String>("compoSition"));

		TableColumn<Person, String> thirdCol = new TableColumn<>("Позиция");
		thirdCol.setMinWidth(80);
		thirdCol.setCellValueFactory(new PropertyValueFactory<>("positKol"));

		TableColumn<Person, String> fourthCol = new TableColumn<>("Вид спорта");
		fourthCol.setMinWidth(100);
		fourthCol.setCellValueFactory(new PropertyValueFactory<>("kindOfSportKol"));

		TableColumn<Person, String> fifthCol = new TableColumn<>("Титул");
		fifthCol.setMinWidth(20);
		fifthCol.setCellValueFactory(new PropertyValueFactory<>("titleStr"));

		table.getColumns().addAll(firstNameCol, compositKoly, thirdCol, fourthCol, fifthCol);
		table.getItems().clear();

		pageIndex = endPage - 1;
		table.setItems(FXCollections.observableArrayList(
				data.getDataList().subList(pageIndex * rowsPerPage, pageIndex * rowsPerPage + displace)));
		box.getChildren().add(table);
		return box;
	}

	public VBox createPage(int pageIndexes, DataStore data) {
		int lastIndex = 0;
		int kolVol = data.getColPers();
		int displace = kolVol % rowsPerPage;

		if (displace > 0) {
			lastIndex = kolVol / rowsPerPage;
		} else {
			lastIndex = kolVol / rowsPerPage - 1;
		}

		endPage = lastIndex + 1;

		VBox box = new VBox(5);
		table = new TableView<Person>();

		TableColumn<Person, String> firstNameCol = new TableColumn<>("ФИО");
		firstNameCol.setMinWidth(200);
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));

		TableColumn<Person, String> compositKoly = new TableColumn<>("Состав");
		compositKoly.setMinWidth(100);
		compositKoly.setCellValueFactory(new PropertyValueFactory<Person, String>("compoSition"));

		TableColumn<Person, String> thirdCol = new TableColumn<>("Позиция");
		thirdCol.setMinWidth(80);
		thirdCol.setCellValueFactory(new PropertyValueFactory<>("positKol"));

		TableColumn<Person, String> fourthCol = new TableColumn<>("Вид спорта");
		fourthCol.setMinWidth(100);
		fourthCol.setCellValueFactory(new PropertyValueFactory<>("kindOfSportKol"));

		TableColumn<Person, String> fifthCol = new TableColumn<>("Титул");
		fifthCol.setMinWidth(20);
		fifthCol.setCellValueFactory(new PropertyValueFactory<>("titleStr"));

		table.getColumns().addAll(firstNameCol, compositKoly, thirdCol, fourthCol, fifthCol);
		table.getItems().clear();
		if (lastIndex == pageIndexes) {

			table.setItems(FXCollections.observableArrayList(
					data.getDataList().subList(pageIndexes * rowsPerPage, pageIndexes * rowsPerPage + displace)));
		} else {
			table.setItems(FXCollections.observableArrayList(
					data.getDataList().subList(pageIndexes * rowsPerPage, pageIndexes * rowsPerPage + rowsPerPage)));
		}
		box.getChildren().add(table);

		return box;
	}
}
