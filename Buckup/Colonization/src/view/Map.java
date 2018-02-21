package view;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import model.Hexagon;

import static java.lang.Math.*;

import java.util.Vector;

public class Map extends Pane {

	private Group titleGroup = new Group();
	Hexagon hex;
	private int rawSize = 5;
	private int columnSize = 5;
	private int mapFill[][] = new int[rawSize][columnSize];

	public Map() {
		setVisible(false);

		// double RADIUS = 30;

		for (int i = 0; i < columnSize; i++)
			for (int j = 0; j < rawSize; j++) {
				if (j == 0 && i == 0)
					mapFill[0][0] = 1;
				if (j == 4 && i == 4)
					mapFill[4][4] = 2;
				if (i != 0 && i != 4 && i != j)
					mapFill[i][j] = 0;
			}

		getChildren().addAll(UpdateTitle());
		System.out.print(titleGroup.getChildren().size());
	}

	public Group UpdateTitle() {
		titleGroup.getChildren().clear();
		for (int x = 0; x < columnSize; x++) {
			for (int y = 0; y < rawSize; y++) {
				hex = new Hexagon(x, y, 0);
				titleGroup.getChildren().addAll(hex.getGropHex());
			}
		}
		return titleGroup;
	}
}