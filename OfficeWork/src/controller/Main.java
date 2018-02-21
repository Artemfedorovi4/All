package controller;

import java.sql.*;
import java.text.ParseException;

import javafx.application.Application;
import javafx.stage.Stage;
import model.DataStore;
import view.MainTable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	ResultSet rs;
	MainTable app;
	DataStore data;

	@Override
	public void start(Stage primaryStage) throws SQLException, ParseException {
		MainTable app = new MainTable();
		
		Scene scene = new Scene(app.getGroup(), 550, 600);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}