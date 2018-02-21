package view;

import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DataStore;
import model.Documents;

public class AddWindow {

	private static Documents doc;
	
	public static void AddWindow(DataStore data) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		VBox verBox = new VBox();
		HBox horBox = new HBox();
		HBox hor = new HBox();

		Label label = new Label("Введите информацию для добавления: ");
		final TextField datecr = new TextField();
		datecr.setPromptText("Дата создания");

		final TextField datere = new TextField();
		datere.setPromptText("Дата регистрации");

		final TextField taskId = new TextField();
		taskId.setPromptText("ИД задачи");

		final TextField docType = new TextField();
		docType.setPromptText("Тип документа");

		final TextField idEmp = new TextField();
		idEmp.setPromptText("ИД Исполнителя");

		Button add = new Button("Add");
		add.setOnAction(event -> {
			if (!datecr.getText().equals("") && !datere.getText().equals("") && !taskId.getText().equals("")
					&& !docType.getText().equals("") && !idEmp.getText().equals("")
					) {
				String dateCreate = datecr.getText();
				String dateRelease = datere.getText();
				int taskIdent = Integer.parseInt(taskId.getText());
				String documType = docType.getText();
				int identEmp = Integer.parseInt(idEmp.getText());
				
				try {
					data.addToDB(dateCreate, dateRelease, taskIdent, documType, identEmp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				data.addData(doc);
				window.close();
			}
		});

		horBox.getChildren().addAll(datecr, datere, taskId);
		hor.getChildren().addAll(docType, idEmp);
		verBox.getChildren().addAll(label, horBox, hor);
		verBox.getChildren().add(add);
		Scene scene = new Scene(verBox, 500, 150);
		window.setScene(scene);
		window.setTitle("Окно добавления информации!");
		window.showAndWait();
	}
}
