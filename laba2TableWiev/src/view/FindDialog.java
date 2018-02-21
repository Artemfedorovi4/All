package view;

import javax.swing.ButtonGroup;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;
import model.DataStore;
import model.PagedTable;

public class FindDialog {

	private static Person pers;
	static VBox box = new VBox();

	public static void AddWindow(DataStore data) {
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		VBox verBox = new VBox();

		HBox horBox = new HBox();
		HBox hor = new HBox();

		DataStore findData = new DataStore();
		
		ObservableList<Person> tableCont = FXCollections.observableArrayList();

		PagedTable page = new PagedTable();
		page.crEmptyPage();
		box.getChildren().add(page.crEmptyPage());
		Label label = new Label("Введите информацию для поиска: ");
		Label charect = new Label("Данные игрока: ");

		final TextField secondName = new TextField();
		secondName.setPromptText("Фамилия");

		final TextField firstName = new TextField();
		firstName.setPromptText("Имя");

		final TextField patrName = new TextField();
		patrName.setPromptText("Отчество");
		
		final TextField addFiColumn = new TextField();
		addFiColumn.setPromptText("Вид спорта");
		
		final TextField composit = new TextField();
		composit.setPromptText("Состав");

		final TextField addTColumn = new TextField();
		addTColumn.setPromptText("Позиция");

		final TextField addFoColumn = new TextField();
		addFoColumn.setPromptText("Титул(Верхний предел)");

		final TextField addFofColumn = new TextField();
		addFofColumn.setPromptText("Титул(Нижний предел)");

		Button find = new Button("Find");

		find.setOnAction(event -> {
			int buf = 0;
			box.getChildren().clear();
			findData.getDataList().clear();
			for (int x = 0; x < data.getColPers(); x++) {
				Person pp = data.getDataList().get(x);
				if (!addFoColumn.getText().equals("") && !addFofColumn.getText().equals("")) {
					if (pp.getSportTitleUp().equals(Integer.parseInt(addFoColumn.getText() + ""))
							&& pp.getSportTitleDown().equals(Integer.parseInt(addFofColumn.getText() + ""))) {
						findData.getDataList().add(pp);
					}
				}
				if (pp.getFirstName().equals(firstName.getText()) || pp.getLastName().equals(secondName.getText())
						|| pp.getPatronymic().equals(patrName.getText())
						|| pp.getKindOfSportKol().equals(addFiColumn.getText())
						|| pp.getCompoSition().equals(composit.getText())
						|| pp.getPositKol().equals(addTColumn.getText())) {
					findData.getDataList().add(pp);
				}
			}
			box.getChildren().add(page.pageOut(findData));
			AlertMes mes = new AlertMes("найденых", findData.getColPers());
		});

		horBox.getChildren().addAll(secondName, firstName, patrName);
		hor.getChildren().addAll(composit, addTColumn, addFoColumn, addFofColumn, addFiColumn);
		verBox.getChildren().addAll(label, horBox, charect, hor);
		verBox.getChildren().addAll(find);
		verBox.getChildren().add(box);

		Scene scene = new Scene(verBox, 700, 400);
		window.setScene(scene);
		window.setTitle("Окно для поиска информации!");
		window.showAndWait();
	}
}
