package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;


public class AlertWindow {
	private static Label winLab = new Label("LOL");

	public static void newWindow(Color winColor) {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Ïîçäğàâëÿş!");
		alert.setContentText("Íàæìèòå ÎÊ äëÿ âûõîäà â ìåíş...");
		if (winColor == Color.RED || winColor == Color.BLUE) {
			if (winColor == Color.RED) {
				alert.setHeaderText("ÏÎÁÅÄÈË ÊĞÀÑÍÛÉ");
				winLab.setText("ÏÎÁÅÄÈË ÊĞÀÑÍÛÉ");
				winLab.setTextFill(winColor);
			}
			if (winColor == Color.BLUE) {
				alert.setHeaderText("ÏÎÁÅÄÈË ÑÈÍÈÉ");
				winLab.setText("ÏÎÁÅÄÈË ÑÈÍÈÉ");
				winLab.setTextFill(winColor);
			}
		} else {
			alert.setHeaderText("ÍÈ×Üß");
			winLab.setText("ÍÈ×Üß");

			winLab.setTextFill(Color.BLACK);
		}
		alert.showAndWait();
	}
}