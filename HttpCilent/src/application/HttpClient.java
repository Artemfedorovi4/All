package application;

import java.io.IOException;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class HttpClient {

	int width = 650;
	int height = 450;
	String str = "Enter requests";
	VBox root = new VBox();
	boolean loop = true;
	boolean isClosed = false;
	Scene scene;
	HttpConnection con;

	public HttpClient() {
		con = new HttpConnection();
		scene = new Scene(root, width, height);
		startPr();
	}

	public Scene getScene() {
		return scene;
	}

	private void startPr() {
		TextArea requestArea = new TextArea();
		Label firLabel = new Label("Request area");
		Label lab = new Label("Hello, enter url");
		Label serverResp = new Label("Server response");
		serverResp.setFont(Font.font("Cambria", 28));
		HBox hBox = new HBox();
		ComboBox methodBox = new ComboBox();
		methodBox.getItems().addAll("GET", "HEAD", "POST");
		methodBox.setPromptText("Method");

		requestArea.setPromptText(str);
		requestArea.setMaxWidth(300);
		requestArea.setMaxHeight(100);
		// requestArea.setEditable(false);
		requestArea.setVisible(false);

		TextField url = new TextField();
		url.setPromptText("Enter url");

		Button sendReq = new Button("Send Request");
		Button makeReq = new Button("Make Request");
		makeReq.setVisible(false);

		methodBox.setOnAction(e -> {
			if (methodBox.getSelectionModel().getSelectedItem() == "POST") {
				requestArea.setText("POST *** HTTP/1.0\r\n" + "Host: *** \r\n"
						+ "User-Agent: Mozilla/5.0 (Windows NT 6.1; rv:18.0) Gecko/20100101 Firefox/18.0\r\n"
						+ "Accept: */*");
				requestArea.setVisible(true);
				makeReq.setVisible(true);
			} else {
				requestArea.setVisible(false);
				makeReq.setVisible(false);
			}
		});

		TabeledLog log = new TabeledLog();

		TextArea answerArea = new TextArea();
		answerArea.setMaxSize(600, 400);
		answerArea.setWrapText(true);
		hBox.getChildren().addAll(methodBox, requestArea, makeReq);
		root.getChildren().addAll(lab, url, firLabel, hBox, sendReq, serverResp, log);

		makeReq.setOnMouseReleased(e -> {
			if (methodBox.getValue() != null) {
				con.prepareReq(methodBox.getValue().toString().toUpperCase(), url.getText().toString());
				requestArea.setText(con.getTextRequest());
			}
		});

		sendReq.setOnMouseClicked(e -> {
			if (!url.getText().equals("")) {
				if (methodBox.getValue() != null) {
					if(methodBox.getValue()=="POST")
						con.setRequest(requestArea.getText().toString());
					else
					con.prepareReq(methodBox.getValue().toString().toUpperCase(), url.getText().toString());
					try {
						con.connect();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					log.addTL(con.getRequest());
					answerArea.setText(con.getSB());
				} else {
					System.out.println("Red");
				}
			}
		});
	}
}