package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class AlertWindow {
	private static Label winLab = new Label("LOL");

	public static void newWindow(Color winColor) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("����������!");
		alert.setContentText("������� �� ��� ������ � ����...");
		if (winColor == Color.RED || winColor == Color.BLUE) {
			if (winColor == Color.RED) {
				alert.setHeaderText("������� �������");
				winLab.setText("������� �������");
				winLab.setTextFill(winColor);
			}
			if (winColor == Color.BLUE) {
				alert.setHeaderText("������� �����");
				winLab.setText("������� �����");
				winLab.setTextFill(winColor);
			}
		} else {
			alert.setHeaderText("�����");
			winLab.setText("�����");

			winLab.setTextFill(Color.BLACK);
		}
		alert.showAndWait();
	}
}