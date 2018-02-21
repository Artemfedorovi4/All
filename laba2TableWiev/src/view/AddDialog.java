package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;
import model.DataStore;

public class AddDialog {

	private static Person pers = new Person();

	public static void AddWindow(DataStore data) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		VBox verBox = new VBox();
		HBox horBox = new HBox();
		HBox hor = new HBox();

		Label label = new Label("Введите информацию для добавления: ");
		Label charect = new Label("Данные игрока: ");
		final TextField secondName = new TextField();
		secondName.setPromptText("Фамилия");

		final TextField firstName = new TextField();
		firstName.setPromptText("Имя");

		final TextField patrName = new TextField();
		patrName.setPromptText("Отчество");

		final TextField addSColumn = new TextField();
		addSColumn.setPromptText("Состав");

		final TextField addTColumn = new TextField();
		addTColumn.setPromptText("Позиция");

		final TextField addFoColumn = new TextField();
		addFoColumn.setPromptText("Титул(Верхний предел)");

		final TextField addFofColumn = new TextField();
		addFofColumn.setPromptText("Титул(Нижний предел)");

		final TextField addFiColumn = new TextField();
		addFiColumn.setPromptText("Вид спорта");

		Button add = new Button("Add");
		add.setOnAction(event -> {
			if (!secondName.getText().equals("") && !firstName.getText().equals("") && !patrName.getText().equals("")
					&& !addSColumn.getText().equals("") && !addTColumn.getText().equals("")
					&& !addFoColumn.getText().equals("") && !addFiColumn.getText().equals("")) {
				pers.setFirstName(firstName.getText());
				pers.setLastName(secondName.getText());
				pers.setPatronymic(patrName.getText());
				pers.setCompoSition(addSColumn.getText());
				pers.setPositKol(addTColumn.getText());
				pers.setKindOfSportKol(addFiColumn.getText());
				pers.setSportTitleUp(Integer.parseInt(addFoColumn.getText()));
				pers.setSportTitleDown(Integer.parseInt(addFofColumn.getText()));
				data.addData(pers);
				window.close();
			}
		});

		horBox.getChildren().addAll(secondName, firstName, patrName);
		hor.getChildren().addAll(addSColumn, addTColumn, addFoColumn, addFofColumn, addFiColumn);
		verBox.getChildren().addAll(label, horBox, charect, hor);
		verBox.getChildren().add(add);
		Scene scene = new Scene(verBox, 700, 300);
		window.setScene(scene);
		window.setTitle("Окно добавления информации!");
		window.showAndWait();
	}

}
