package view;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.DataStore;
import model.Documents;

public class MainTable {

	TableView<Documents> table;
	Group group = new Group();
	DataStore data = new DataStore();
	DataStore dataTemp = new DataStore();
	CheckBox ch = new CheckBox("Показать прошедшие задачи");

	public MainTable() throws SQLException {

		VBox vbox = new VBox();
		HBox hbox = new HBox();

		Button addDoc = new Button("Добавить документ");
		Button showEmpTable = new Button("Показать таблицу исполнителей");
		Button editRow = new Button("Редактировать");

		Date date = new Date();
		System.out.println(date.toString());

		ch.setOnAction(e -> {
			try {
				generateTempData(data);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		addDoc.setOnAction(e -> {
			AddWindow.AddWindow(data);
		});

		editRow.setOnAction(e -> {
			Documents doc = table.getSelectionModel().getSelectedItem();
			if (doc != null) {
				EditWindow.AddWindow(data, doc);
			}
		});

		showEmpTable.setOnAction(e -> {
			try {
				ImpTable.AddWindow(data);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		table = new TableView<Documents>();
		table.setEditable(true);
		TableColumn<Documents, Integer> id = new TableColumn<>("№ ");
		id.setMinWidth(50);
		id.setCellValueFactory(new PropertyValueFactory<>("idDoc"));

		TableColumn<Documents, String> dateCreate = new TableColumn<>("Дата создания ");
		dateCreate.setMinWidth(100);
		dateCreate.setCellValueFactory(new PropertyValueFactory<>("dateCreate"));

		TableColumn<Documents, String> dateRegistrate = new TableColumn<>("Дедлайн ");
		dateRegistrate.setMinWidth(100);
		dateRegistrate.setCellValueFactory(new PropertyValueFactory<>("dateRegistrate"));

		TableColumn<Documents, Integer> taskId = new TableColumn<>("№ задачи");
		taskId.setMinWidth(50);
		taskId.setCellValueFactory(new PropertyValueFactory<>("taskId"));

		TableColumn<Documents, String> docType = new TableColumn<>("Тип дкумента ");
		docType.setMinWidth(100);
		docType.setCellValueFactory(new PropertyValueFactory<>("docType"));

		TableColumn<Documents, Integer> idEmp = new TableColumn<>("№ исп.");
		idEmp.setMinWidth(50);
		idEmp.setCellValueFactory(new PropertyValueFactory<>("idEmp"));

		table.getColumns().addAll(id, dateCreate, dateRegistrate, taskId, docType, idEmp);
		table.setMaxHeight(350);

		DataStore tempData = new DataStore();

		table.setItems(data.getDatabaseData());
		table.setVisible(true);
		hbox.getChildren().addAll(addDoc, editRow, showEmpTable, ch);
		vbox.getChildren().addAll(table, hbox);
		group.getChildren().add(vbox);
	}

	public void generateTempData(DataStore data) throws ParseException, SQLException {
		String s = "2017.11.03";
		SimpleDateFormat format = new SimpleDateFormat();
		format.applyPattern("yyyy.mm.dd");
		Date mainDate = format.parse(s);
		Date docDate;
		String dates;
		ObservableList<Documents> mainData = data.getDataList();
		System.out.println(data.getDataList().size());
		if (ch.isSelected()) {
			dataTemp.getDataList().clear();
			System.out.println(data.getDataList().size());
			for (int i = 0; i < data.getDataList().size()-1; i++) {
				dates = data.getDataList().get(i).getDateRegistrate();
				
				docDate = format
						.parse(dates.substring(0, 4) + "." + dates.substring(5, 7) + "." + dates.substring(8, 10));
				if (mainDate.after(docDate))//Сравнение дат
					dataTemp.addData(data.getDataList().get(i));
				//System.out.println(data.getDataList().size());
			}
			if (dataTemp != null) {
				table.setItems(dataTemp.getDataList());
			}
		}
		if (ch.isSelected() == false) {
			table.setItems(mainData);
		}
	}

	public Group getGroup() {
		return group;
	}
}
