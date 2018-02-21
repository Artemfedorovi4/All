package model;

import java.sql.*;

import controller.JDBCconnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStore {
	private ObservableList<Documents> data = FXCollections.observableArrayList();
	private ObservableList<Implementer> impData = FXCollections.observableArrayList();

	
	public DataStore() {
	}

	public void addData(Documents doc) {
		data.add(doc);
	}
	
	public void addEmpData(Implementer imp) {
		impData.add(imp);
	}

	public ObservableList<Documents> getDataList() {
		return data;
	}
	
	public ObservableList<Implementer> getImpDataList() {
		return impData;
	}

	public void setAllData(ObservableList<Documents> temp) {
		data.clear();
		data.addAll(temp);
	}
	
	public void setAllImpData(ObservableList<Implementer> temp) {
		data.clear();
		impData.addAll(temp);
	}

	public ObservableList<Documents> getDatabaseData() throws SQLException {
		data.clear();
		JDBCconnection db = new JDBCconnection();
		Connection conn = db.getConnetcion("jdbc:mysql://127.0.0.1:3306/officework?autoReconnect=true&useSSL=false",
				"root", "root");
		String query = "select * from officework.doc";
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(query);

		while (rs.next()) {
			System.out.println(rs.getString("datere") + " ");
			data.add(new Documents(rs.getInt("iddoc"), rs.getString("datecr"), rs.getString("datere"),
					rs.getInt("taskid"), rs.getString("doctype"), rs.getInt("idemp")));
		}

		stm.close();
		conn.close();
		return data;
	}
	
	public ObservableList<Implementer> getImpDatabaseData() throws SQLException {

		JDBCconnection db = new JDBCconnection();
		Connection conn = db.getConnetcion("jdbc:mysql://127.0.0.1:3306/officework?autoReconnect=true&useSSL=false",
				"root", "root");
		String query = "select * from officework.emp";
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(query);

		while (rs.next()) {
			System.out.println(rs.getString("secname") + " ");
			impData.add(new Implementer(rs.getInt("idemp"), rs.getString("secname"), rs.getString("pos"),
					rs.getInt("tel"), rs.getString("email")));
		}
		stm.close();
		conn.close();
		return impData;
	}

	public void addToDB(String dateCreate, String dateRelease, int taskIdent, String documType, int identEmp)
			throws SQLException {

		JDBCconnection db = new JDBCconnection();
		Connection conn = db.getConnetcion("jdbc:mysql://127.0.0.1:3306/officework?autoReconnect=true&useSSL=false",
				"root", "root");

		Statement stm = conn.createStatement();
		if (!stm.execute("insert into doc (datecr, datere, taskid, doctype, idemp) values (\"" + dateCreate + "\",\""
				+ dateRelease + "\"," + taskIdent + ",\"" + documType + "\"," + identEmp + ");"))
		updateDocTable();
	}

	public void updateDocTable() throws SQLException {
		JDBCconnection db = new JDBCconnection();
		Connection conn = db.getConnetcion("jdbc:mysql://127.0.0.1:3306/officework?autoReconnect=true&useSSL=false",
				"root", "root");
		String query = "select * from officework.doc";
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(query);
		data.clear();
		while (rs.next()) {
			System.out.println(rs.getString("datere") + " ");
			data.add(new Documents(rs.getInt("iddoc"), rs.getString("datecr"), rs.getString("datere"),
					rs.getInt("taskid"), rs.getString("doctype"), rs.getInt("idemp")));
		}
		stm.close();
		conn.close();
	}

	public void updateDataDoc(Documents doc) throws SQLException {

		JDBCconnection db = new JDBCconnection();
		Connection conn = db.getConnetcion("jdbc:mysql://127.0.0.1:3306/officework?autoReconnect=true&useSSL=false",
				"root", "root");

		// UPDATE `officework`.`doc` SET `datere`='2016-03-15' WHERE `iddoc`='2'
		// and`taskid`='2';
		String date = doc.getDateDoc();
		Statement stm = conn.createStatement();
		if (stm.execute("UPDATE officework.doc set datere=\"" + date + "\", taskid=" + doc.getTaskId()
				+ ", idemp=" + doc.getIdEmp() + " where iddoc=" + doc.getIdDoc() + ";")) {
			System.out.println("UPDATE doc set datere=\"" + doc.getDateRegistrate() + "\" AND taskid='" + doc.getTaskId()
			+ "' AND idemp='" + doc.getIdEmp() + "' where iddoc='" + doc.getIdDoc() + "';");
			updateDocTable();
		}
		stm.close();
		conn.close();
	}
}
