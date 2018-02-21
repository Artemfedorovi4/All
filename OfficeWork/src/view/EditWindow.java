package view;

import java.sql.SQLException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DataStore;
import model.Documents;

public class EditWindow {

	public static void AddWindow(DataStore data, Documents doc) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		// VBox verBox = new VBox();
		// VBox vbox = new VBox();

		// HBox hbox = new HBox();

		GridPane root = new GridPane();

		root.setPadding(new Insets(20));

		root.setHgap(25);
		root.setVgap(15);
		// Label label = new Label("Введите информацию для добавления: ");

		Label docId = new Label("ИД документа");
		root.add(docId, 1, 1);

		Label doci = new Label("" + doc.getIdDoc());
		root.add(doci, 2, 1);

		Label dateCreate = new Label("Дата создания");
		root.add(dateCreate, 1, 2);

		Label dateRegis = new Label("Дата регистрации");
		root.add(dateRegis, 1, 3);

		Label idTasks = new Label("ИД задачи");
		root.add(idTasks, 1, 4);

		Label typeOfDoc = new Label("Тип документа");
		root.add(typeOfDoc, 1, 5);

		Label idEmployer = new Label("ИД Исполнителя");
		root.add(idEmployer, 1, 6);
		// vbox.setMinHeight(100);
		// vbox.getChildren().addAll(docId,dateCreate,dateRegis,idTasks, typeOfDoc,
		// idEmployer);

		final Label datecr = new Label();
		datecr.setText("" + doc.getDateCreate());
		root.add(datecr, 2, 2);

		final TextField datere = new TextField();
		datere.setText("" + doc.getDateRegistrate());
		root.add(datere, 2, 3);

		final TextField taskId = new TextField();
		taskId.setText("" + doc.getTaskId());
		root.add(taskId, 2, 4);

		final Label docType = new Label();
		docType.setText("" + doc.getDocType());
		root.add(docType, 2, 5);
		
		final TextField idEmp = new TextField();
		idEmp.setText("" + doc.getIdEmp());
		root.add(idEmp, 2, 6);

		Button ok = new Button("Edit");
		root.add(ok, 1, 7);
		
		ok.setOnAction(event -> {
			
			System.out.println(datere.getText());
			
			doc.setDateRegistrate(datere.getText());
			doc.setTaskId(Integer.parseInt(taskId.getText()));
			doc.setIdEmp(Integer.parseInt(idEmp.getText()));
			
			try {
				data.updateDataDoc(doc);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		Scene scene = new Scene(root, 500, 400);
		window.setScene(scene);
		window.setTitle("Окно добавления информации!");
		window.showAndWait();
	}
}
