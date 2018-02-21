package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStore {
	private ObservableList<Person> data = FXCollections.observableArrayList();
	private int colPers;

	public DataStore() {
		data = FXCollections.observableArrayList(
				new Person("Федорович", "Артём", "Геннадьевич", "основной", "футбол", "защитник", 2, 2));
		colPers = 0;
	}

	public void addData(Person pers) {
		data.add(pers);
	}

	public int getColPers() {
		colPers = data.size();
		return colPers;
	}

	public ObservableList<Person> getDataList() {

		return data;
	}

	public void setAllData(ObservableList<Person> temp) {

		data.addAll(temp);
	}
}
