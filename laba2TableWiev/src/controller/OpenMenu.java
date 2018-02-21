package controller;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Person;
import model.SaxParser;

public class OpenMenu {

	private static ObservableList<Person> data = FXCollections.observableArrayList();
	private ObservableList<Person> data2 = FXCollections.observableArrayList();

	public OpenMenu(){
		super();
	}

	public OpenMenu(Stage stage) {
		FileChooser fileChooser = new FileChooser();

		fileChooser.setTitle("Open XML file");
		fileChooser.setInitialDirectory(new File("."));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML file", "*.xml"));
		File file = fileChooser.showOpenDialog(stage);
		SaxParser saxPar = new SaxParser(file);
		data = saxPar.getData();

	}

	public ObservableList<Person> getData() {
		return data;
	}

	public ObservableList<Person> getHiddenData() {
		File file2 = new File("E:\\Programs\\javawork\\test200.xml");
		SaxParser saxPar = new SaxParser(file2);
		data2 = saxPar.getData();
		return data2;
	}
}
