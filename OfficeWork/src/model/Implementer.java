package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Implementer {
	private final SimpleIntegerProperty idEmp;
	private final SimpleStringProperty secName;
	private final SimpleStringProperty position;
	private final SimpleIntegerProperty tel;
	private final SimpleStringProperty eMail;
	
	public Integer getIdEmp() {
		return idEmp.get();
	}
	
	public void setIdEmp(int value) {
		idEmp.set(value);
	}

	public String getSecName() {
		return secName.get();
	}
	
	public void setSecName(String value) {
		secName.set(value);
	}

	public String getPosition() {
		return position.get();
	}
	
	public void setPosition(String value) {
		position.set(value);
	}

	public Integer getTel() {
		return tel.get();
	}
	
	public void setTel(int value) {
		tel.set(value);
	}

	public String getEMail() {
		return eMail.get();
	}
	
	public Implementer(int idEmp, String secName, String position, int tel, String eMail) {
		this.idEmp = new SimpleIntegerProperty(idEmp);
		this.secName = new SimpleStringProperty(secName);
		this.position = new SimpleStringProperty(position);
		this.tel = new SimpleIntegerProperty(tel);
		this.eMail = new SimpleStringProperty(eMail);
	}

}
