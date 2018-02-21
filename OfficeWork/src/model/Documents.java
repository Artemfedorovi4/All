package model;

import java.sql.Date;

import javax.swing.text.html.HTMLDocument.HTMLReader.ParagraphAction;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Documents {
	private final SimpleIntegerProperty idDoc;
	private final SimpleStringProperty dateCreate;
	private final SimpleStringProperty dateRegistrate;
	private final SimpleIntegerProperty taskId;
	private final SimpleStringProperty docType;
	private final SimpleIntegerProperty idEmp;

	public int getIdDoc() {
		return idDoc.get();
	}

	public void setIdDoc(int value) {
		idDoc.set(value);
	}

	public String getDateCreate() {
		return dateCreate.get();
	}
	
	public void setDateCreate(String value) {
		dateCreate.set(value);
	}

	public String getDateRegistrate() {
		return dateRegistrate.get();
	}
	
	public void setDateRegistrate(String value) {
		dateRegistrate.set(value);
	}

	public int getTaskId() {
		return taskId.get();
	}
	
	public void setTaskId(int value) {
		taskId.set(value);
	}

	public String getDocType() {
		return docType.get();
	}
	
	public void setDocType(String type) {
		docType.set(type);
	}

	public int getIdEmp() {
		return idEmp.get();
	}
	
	public void setIdEmp(int emp) {
		idEmp.set(emp);
	}
	
	public String getDateDoc() {
		
		String dat = getDateRegistrate();
		
		System.out.println(dat.substring(0, 4)+"-"+ dat.substring(5,7)+"-"+dat.substring(8,10));
		String res = dat.substring(0, 4)+"-"+ dat.substring(5,7)+"-"+dat.substring(8,10);
		
		return res;
	}

	public Documents(int id, String dateCreate, String dateRegistrate, int taskId, String docType, int idEmp) {
		
		this.idDoc = new SimpleIntegerProperty(id);
		this.dateCreate = new SimpleStringProperty(dateCreate);
		this.dateRegistrate = new SimpleStringProperty(dateRegistrate);
		this.taskId = new SimpleIntegerProperty(taskId);
		this.docType = new SimpleStringProperty(docType);
		this.idEmp = new SimpleIntegerProperty(idEmp);
		
	}
}
