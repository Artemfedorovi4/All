package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DataStore;
import model.Person;

public class DelDialog {
	
	public static void AddWindow(DataStore data) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		VBox verBox = new VBox();
		HBox horBox = new HBox();
		HBox hor = new HBox();

		DataStore findData = new DataStore();

		Label charect = new Label("Данные игрока: ");

		final TextField secondName = new TextField();
		secondName.setPromptText("Фамилия");

		final TextField firstName = new TextField();
		firstName.setPromptText("Имя");

		final TextField patrName = new TextField();
		patrName.setPromptText("Отчество");

		final TextField composit = new TextField();
		composit.setPromptText("Состав");

		final TextField addFiColumn = new TextField();
		addFiColumn.setPromptText("Вид спорта");

		final TextField addTColumn = new TextField();
		addTColumn.setPromptText("Позиция");

		final TextField addFoColumn = new TextField();
		addFoColumn.setPromptText("Титул(Верхний предел)");

		final TextField addFofColumn = new TextField();
		addFofColumn.setPromptText("Титул(Нижний предел)");

		Button findDel = new Button("Delete");
		findDel.setOnAction(event -> {
			int buf = 0;
			
			for (int x = 0; x < data.getColPers(); x++) {
				Person pp=data.getDataList().get(x);
				if (pp.getFirstName().equals(firstName.getText()) || pp.getLastName().equals(secondName.getText())
						|| pp.getPatronymic().equals(patrName.getText())
						|| pp.getKindOfSportKol().equals(addFiColumn.getText())
						|| pp.getCompoSition().equals(composit.getText())
						|| pp.getPositKol().equals(addTColumn.getText())
						|| pp.getSportTitleUp().equals(addFoColumn.getText())
						|| pp.getSportTitleDown().equals(addFofColumn.getText())) {
					buf++;
					findData.getDataList().add(pp);
				}
			}
			data.getDataList().removeAll(findData.getDataList());
			AlertMes mes = new AlertMes("удалённых", buf);
		});
		horBox.getChildren().addAll(secondName, firstName, patrName);
		hor.getChildren().addAll(composit, addTColumn, addFoColumn, addFofColumn, addFiColumn);
		verBox.getChildren().addAll(horBox, charect, hor);
		verBox.getChildren().addAll(findDel);
		Scene scene = new Scene(verBox, 700, 300);
		window.setScene(scene);
		window.setTitle("Окно для поиска информации!");
		window.showAndWait();
	}
}
