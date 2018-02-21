package application;

import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RequestWindow {

	public static void AddWindow(Request request) {

		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

		FlowPane root = new FlowPane();

		Label name = new Label("Name: " + request.getRequestTail());
		Label domain = new Label("Domain: " + request.getUrl());
		Label status = new Label("Status: " + request.getStatus());
		Label method = new Label("Method: " + request.getMethod());
		Label textArea = new Label("Full Request");

		name.setFont(Font.font("Cambria", 14));
		domain.setFont(Font.font("Cambria", 14));
		status.setFont(Font.font("Cambria", 14));
		method.setFont(Font.font("Cambria", 14));
		textArea.setFont(Font.font("Cambria", 18));
		
		TextArea ans = new TextArea(request.getAnswer());
		ans.setMinSize(600, 200);

		root.getChildren().addAll(name, domain, status, method, textArea, ans);
		root.setOrientation(Orientation.VERTICAL);
		Scene scene = new Scene(root, 700, 450);
		window.setScene(scene);
		window.setTitle("request details!".toUpperCase());
		window.showAndWait();
	}

}
