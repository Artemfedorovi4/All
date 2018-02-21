package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Person {

	private final SimpleStringProperty firstName;
	private final SimpleStringProperty lastName;
	private final SimpleStringProperty patronymic;
	private final SimpleStringProperty compoSition;
	private final SimpleStringProperty positKol;
	private final SimpleStringProperty kindOfSportKol;
	private final SimpleIntegerProperty sportTitleUp;
	private final SimpleIntegerProperty sportTitleDown;
	private final SimpleStringProperty fullName;
	private final SimpleStringProperty titleStr;

	public Person(String fName, String sName, String pName, String compos, String posit, String kind, int up,
			int down) {
		this.firstName = new SimpleStringProperty(fName);
		this.lastName = new SimpleStringProperty(sName);
		this.patronymic = new SimpleStringProperty(pName);
		this.fullName = new SimpleStringProperty(fName + " " + sName + " " + pName);
		this.compoSition = new SimpleStringProperty(compos);
		this.positKol = new SimpleStringProperty(posit);
		this.kindOfSportKol = new SimpleStringProperty(kind);
		this.sportTitleUp = new SimpleIntegerProperty(up);
		this.sportTitleDown = new SimpleIntegerProperty(down);
		this.titleStr = new SimpleStringProperty("");
	}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String value) {
		firstName.set(value);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String value) {
		lastName.set(value);
	}

	public String getPatronymic() {
		return patronymic.get();
	}

	public void setPatronymic(String value) {
		patronymic.set(value);
	}

	public String getCompoSition() {
		return compoSition.get();
	}

	public void setCompoSition(String value) {
		compoSition.set(value);
	}

	public String getPositKol() {
		return positKol.get();
	}

	public void setPositKol(String value) {
		positKol.set(value);
	}

	public String getKindOfSportKol() {
		return kindOfSportKol.get();
	}

	public void setKindOfSportKol(String value) {
		kindOfSportKol.set(value);
	}

	public Integer getSportTitleUp() {
		return sportTitleUp.get();
	}

	public void setSportTitleUp(int value) {
		sportTitleUp.set(value);
	}

	public Integer getSportTitleDown() {
		return sportTitleDown.get();
	}

	public void setSportTitleDown(int value) {
		sportTitleDown.set(value);
	}

	public String getTitleStr() {
		titleStr.set(sportTitleUp.get() + "-" + sportTitleDown.get());
		return titleStr.get();
	}

	public void setTitleStr(String value) {
		titleStr.set(value);
	}

	public Person() {
		this("", "", "", "", "", "", 0, 0);
	}

	public String getFullName() {
		String fio = lastName.get();
		fio += " " + firstName.get() + " ";
		fio += patronymic.get();
		return fio;
	}

	public void setFullName(String value) {
		fullName.set(value);
	}

}
