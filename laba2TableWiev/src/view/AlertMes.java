package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertMes {

	public AlertMes(String str,int text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");
		if (text != 0)
			alert.setContentText("���������� "+str+" �������: " + text);
		else
			alert.setContentText("�� ���������� ������� �� ���������...");
		alert.showAndWait();
	}
	
	public AlertMes(String text) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Look, an Information Dialog");
		alert.setContentText(text);
		alert.showAndWait();
	}
}
