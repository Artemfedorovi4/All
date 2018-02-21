package view;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import model.Hexagon;
import model.PlCircle;

public class Map extends Pane {

	private Group titleGroup = new Group();
	private Group hexGroup = new Group();
	private Group circleGroup = new Group();
	private Group labelGroup = new Group();
	private Hexagon hex;
	private PlCircle pal;
	private PlCircle emptyPal = new PlCircle();
	private boolean stepPl;

	private Label redS;
	private Label blueS;
	private Label currSteper;

	private boolean isCanR = false;
	private boolean isCanB = false;

	private boolean firstClick = false;
	private int bufFill;
	private PlCircle bufCircle;

	private final Ellipse ellipseCopy = new Ellipse();
	private final Ellipse ellipseJump = new Ellipse();

	private int redScore = 1, blueScore = 1;

	private int rowSize = 5;
	private int columnSize = 5;
	private int mapFill[][] = new int[rowSize][columnSize];
	private Hexagon base[][] = new Hexagon[rowSize][columnSize];
	private PlCircle pl[][] = new PlCircle[rowSize][columnSize];
	private boolean isGameOver = false;

	public Map() {
		setVisible(false);
		for (int i = 0; i < columnSize; i++)
			for (int j = 0; j < rowSize; j++) {
				if (j == 0 && i == 0)
					mapFill[0][0] = 1;
				if (j == 4 && i == 4)
					mapFill[4][4] = -1;
				if (i != 0 && i != 4 && i != j)
					mapFill[i][j] = 0;
			}

		firstClick = false;
		loadTitle();
	}

	public void loadTitle() {
		titleGroup.getChildren().clear();
		hexGroup.getChildren().clear();
		for (int x = 0; x < columnSize; x++) {
			for (int y = 0; y < rowSize; y++) {
				hex = makeHexagon(x, y, 0);
				base[x][y] = hex;
				hexGroup.getChildren().addAll(hex);
			}
		}
		circleGroup.getChildren().clear();
		for (int x = 0; x < columnSize; x++) {
			for (int y = 0; y < rowSize; y++) {
				pal = makeCircle(x, y, mapFill[x][y]);
				pl[x][y] = emptyPal;
				pl[x][y] = pal;
				circleGroup.getChildren().addAll(pal);
			}
		}

		redS = new Label(redScore + "");
		blueS = new Label("" + blueScore);

		currSteper = new Label("ÕÎÄ ÑÈÍÈÕ");
		currSteper.setTextFill(Color.BLUE);
		redS.setTranslateX(100);
		redS.setTranslateY(60);
		redS.setVisible(true);
		redS.setScaleX(10);
		redS.setScaleY(10);
		redS.setTextFill(Color.RED);

		blueS.setTranslateX(800);
		blueS.setTranslateY(60);
		blueS.setVisible(true);
		blueS.setScaleX(10);
		blueS.setScaleY(10);
		blueS.setTextFill(Color.BLUE);

		currSteper.setTranslateX(400);
		currSteper.setTranslateY(60);
		currSteper.setVisible(true);
		currSteper.setScaleX(4);
		currSteper.setScaleY(4);

		ellipseJump.setVisible(false);
		ellipseJump.setRadiusX(117);
		ellipseJump.setRadiusY(117);
		ellipseJump.setFill(Color.TRANSPARENT);
		ellipseJump.setStroke(Color.WHITE);

		ellipseCopy.setVisible(false);
		ellipseCopy.setRadiusX(70);
		ellipseCopy.setRadiusY(70);
		ellipseCopy.setFill(Color.TRANSPARENT);
		ellipseCopy.setStroke(Color.WHITE);
		labelGroup.getChildren().clear();
		titleGroup.getChildren().clear();
		labelGroup.getChildren().addAll(redS, blueS, currSteper);
		titleGroup.getChildren().addAll(ellipseCopy, ellipseJump, hexGroup, circleGroup, labelGroup);//37
		this.getChildren().clear();
		this.getChildren().addAll(labelGroup, titleGroup);
	}

	public void updateTitle() {
		circleGroup.getChildren().clear();
		for (int x = 0; x < columnSize; x++) {
			for (int y = 0; y < rowSize; y++) {
				circleGroup.getChildren().addAll(pl[x][y]);
			}
		}

		labelGroup.getChildren().clear();

		redScore = 0;
		blueScore = 0;
		for (int x = 0; x < columnSize; x++) {
			for (int y = 0; y < rowSize; y++) {
				if (pl[x][y].getFillInt() == 1)
					redScore++;
				if (pl[x][y].getFillInt() == -1)
					blueScore++;
			}
		}
		redS.setText(redScore + "");
		Label strela = new Label();
		if (!stepPl) {
			strela.setText("<-");
			currSteper.setText(strela.getText() + "ÕÎÄ ÊÐÀÑÍÛÕ");
			currSteper.setTextFill(Color.RED);
		} else {
			strela.setText("->");
			currSteper.setText("ÕÎÄ ÑÈÍÈÕ" + strela.getText());
			currSteper.setTextFill(Color.BLUE);
		}

		blueS.setText("" + blueScore);
		labelGroup.getChildren().addAll(redS, blueS, currSteper);//49
	}

	private Hexagon makeHexagon(int x, int y, int fill) {
		Hexagon hex = new Hexagon(x, y, fill);

		hex.setOnMouseClicked(e -> {
			pal = bufCircle;
			PlCircle emptyCircle;
			int oldX, oldY;
			if (firstClick) {
				pal.setStroke(pal.getCurrColor());
				if (hex.getEventXY(e) && ellipseCopy.contains(e.getSceneX(), e.getSceneY())
						&& ellipseJump.contains(e.getSceneX(), e.getSceneY())) {
					System.out.println("CLONE");
					pal = makeCircle(hex.getIntX(), hex.getIntY(), bufFill);
					pl[hex.getIntX()][hex.getIntY()] = pal;
					titleGroup.getChildren().add(pal);
					firstClick = false;
					changeColor(pal);
					stepPl = !stepPl;
				}

				if (hex.getEventXY(e) && !ellipseCopy.contains(e.getSceneX(), e.getSceneY())
						&& ellipseJump.contains(e.getSceneX(), e.getSceneY())) {
					System.out.println("JUMP");
					oldX = pal.getIntX();
					oldY = pal.getIntY();
					pal = makeCircle(hex.getIntX(), hex.getIntY(), bufFill);
					pl[hex.getIntX()][hex.getIntY()] = pal;
					emptyCircle = makeCircle(oldX, oldY, 0);
					pl[oldX][oldY] = emptyCircle;
					firstClick = false;
					changeColor(pal);
					stepPl = !stepPl;
				}

				for (int colInd = 0; colInd < columnSize; colInd++) {
					for (int rowInd = 0; rowInd < rowSize; rowInd++) {
						if (pl[colInd][rowInd].getCurrColor() == Color.RED
								|| pl[colInd][rowInd].getCurrColor() == Color.BLUE) {
							pl[colInd][rowInd].setCanGo(isCanGoOnMap(pl[colInd][rowInd]));
							pl[colInd][rowInd].setCanJump(isCanJump(pl[colInd][rowInd]));
						}
					}
				}
				if (isGameOver()) {
					loadTitle();
					isGameOver = false;
				}
			}
		});
		return hex;

	}

	private boolean isGameOver() {

		for (int colInd = 0; colInd < columnSize; colInd++) {
			for (int rowInd = 0; rowInd < rowSize; rowInd++) {
				if (pl[colInd][rowInd].getCurrColor() == Color.RED || pl[colInd][rowInd].getCurrColor() == Color.BLUE) {
					pl[colInd][rowInd].setCanGo(isCanGoOnMap(pl[colInd][rowInd]));
					pl[colInd][rowInd].setCanJump(isCanJump(pl[colInd][rowInd]));
				}
			}
		}

		for (int i = 0; i < columnSize && !isGameOver; i++) {
			for (int j = 0; j < rowSize && !isGameOver; j++) {
				if (pl[i][j].getCurrColor() == Color.RED && !isCanR) {
					if (pl[i][j].isCanGo() || pl[i][j].isCanJump())
						isCanR = true;
				}
				if (pl[i][j].getCurrColor() == Color.BLUE && !isCanB) {
					if (pl[i][j].isCanGo() || pl[i][j].isCanJump())
						isCanB = true;
				}
			}
		}

		if (redScore == 0 || blueScore == 0) {
			if (redScore == 0)
				AlertWindow.newWindow(Color.BLUE);
			else
				AlertWindow.newWindow(Color.RED);
			isGameOver = true;
		}

		if (isCanR == false) {
			AlertWindow.newWindow(Color.BLUE);
			isGameOver = true;
		}

		if (isCanB == false) {
			AlertWindow.newWindow(Color.RED);
			isGameOver = true;
		}

		if (redScore + blueScore == rowSize * columnSize) {
			// System.out.println(redScore + " " + blueScore);
			if (redScore > blueScore) {
				AlertWindow.newWindow(Color.RED);
				isGameOver = true;
			}
			if (redScore < blueScore) {
				AlertWindow.newWindow(Color.BLUE);
				isGameOver = true;
			} else {
				AlertWindow.newWindow(Color.WHITE);
				isGameOver = true;
			}
		}
		return isGameOver;
	}

	private PlCircle makeCircle(int x, int y, int fill) {
		PlCircle ovnPl = new PlCircle(x, y, fill);

		ovnPl.setOnMouseClicked(e -> {
			if (stepPl == ovnPl.isBoolStep()) {

				// ovnPl.setStrokeWidth(3);
				bufCircle = ovnPl;
				ellipseJump.setCenterX(ovnPl.getCenterX());
				ellipseJump.setCenterY(ovnPl.getCenterY());

				ellipseCopy.setCenterX(ovnPl.getCenterX());
				ellipseCopy.setCenterY(ovnPl.getCenterY());

				bufFill = ovnPl.getFillInt();
				if (!firstClick) {
					resetColor();
					ovnPl.setStroke(Color.WHITE);
				} else {
					resetColor();
					ovnPl.setStroke(ovnPl.getCurrColor());
				}
				firstClick = !firstClick;
			}
		});
		return ovnPl;
	}

	private void resetColor() {
		for (int i = 0; i < columnSize; i++) {
			for (int j = 0; j < rowSize; j++) {
				pl[i][j].setStroke(pl[i][j].getCurrColor());
			}
		}
	}

	private boolean isCanJump(PlCircle circ) {
		int currX = circ.getIntX();
		int currY = circ.getIntY();
		int accesibleSector = 0;
		int zapSect = 0;
		boolean isCanJump = false;

		if (isExist(currX - 2, currY)) {
			accesibleSector++;
			// System.out.println("J1");
			if (isCanChange(pl[currX - 2][currY])) {
				zapSect++;
			}
		}

		if (isExist(currX - 2, currY + 1)) {
			accesibleSector++;
			// System.out.println("J2");
			if (isCanChange(pl[currX - 2][currY + 1])) {
				zapSect++;
			}
		}

		if (isExist(currX - 2, currY + 2)) {
			accesibleSector++;
			// System.out.println("J3");
			if (isCanChange(pl[currX - 2][currY + 2])) {
				zapSect++;
			}
		}

		if (isExist(currX - 1, currY + 2)) {
			accesibleSector++;
			// System.out.println("J4");
			if (isCanChange(pl[currX - 1][currY + 2])) {
				zapSect++;
			}
		}

		if (isExist(currX, currY + 2)) {
			accesibleSector++;
			// System.out.println("J5");
			if (isCanChange(pl[currX][currY + 2])) {
				zapSect++;
			}
		}

		if (isExist(currX + 1, currY + 1)) {
			accesibleSector++;
			// System.out.println("J6");
			if (isCanChange(pl[currX + 1][currY + 1])) {
				zapSect++;
			}
		}

		if (isExist(currX + 2, currY)) {
			accesibleSector++;
			// System.out.println("J7");
			if (isCanChange(pl[currX + 2][currY])) {
				zapSect++;
			}
		}

		if (isExist(currX + 2, currY - 1)) {
			accesibleSector++;
			// System.out.println("J8");
			if (isCanChange(pl[currX + 2][currY - 1])) {
				zapSect++;
			}
		}

		if (isExist(currX + 2, currY - 2)) {
			accesibleSector++;
			// System.out.println("J9");
			if (isCanChange(pl[currX + 2][currY - 2])) {
				zapSect++;
			}
		}

		if (isExist(currX + 1, currY - 2)) {
			accesibleSector++;
			// System.out.println("J10");
			if (isCanChange(pl[currX + 1][currY - 2])) {
				zapSect++;
			}
		}

		if (isExist(currX, currY - 2)) {
			accesibleSector++;
			// System.out.println("J11");
			if (isCanChange(pl[currX][currY - 2])) {
				zapSect++;
			}
		}

		if (isExist(currX - 1, currY - 1)) {
			accesibleSector++;
			// System.out.println("J12");
			if (isCanChange(pl[currX - 1][currY - 1])) {
				zapSect++;
			}
		}

		System.out.println(accesibleSector + " " + zapSect);
		if (accesibleSector == zapSect)
			isCanJump = false;

		return isCanJump;
	}

	private boolean isCanGoOnMap(PlCircle circ) {
		int currX = circ.getIntX();
		int currY = circ.getIntY();
		int accesibleSector = 0;
		int zapSect = 0;
		boolean isCanGo = true;

		if (isExist(currX - 1, currY)) {
			accesibleSector++;
			// System.out.println("G1");
			if (isCanChange(pl[currX - 1][currY])) {
				zapSect++;
			}
		}

		if (isExist(currX - 1, currY + 1)) {
			accesibleSector++;
			// System.out.println("G2");
			if (isCanChange(pl[currX - 1][currY + 1])) {
				zapSect++;
			}
		}

		if (isExist(currX, currY + 1)) {
			accesibleSector++;
			// System.out.println("G3");
			if (isCanChange(pl[currX][currY + 1])) {
				zapSect++;
			}
		}

		if (isExist(currX + 1, currY)) {
			accesibleSector++;
			// System.out.println("G4");
			if (isCanChange(pl[currX + 1][currY])) {
				zapSect++;
			}
		}

		if (isExist(currX + 1, currY - 1)) {
			accesibleSector++;
			// System.out.println("G5");
			if (isCanChange(pl[currX + 1][currY - 1])) {
				zapSect++;
			}
		}

		if (isExist(currX, currY - 1)) {
			accesibleSector++;
			// System.out.println("G6");
			if (isCanChange(pl[currX][currY - 1])) {
				zapSect++;
			}
		}
		// System.out.println(accesibleSector + " " + zapSect);
		if (accesibleSector == zapSect)
			isCanGo = false;
		return isCanGo;
	}

	private void changeColor(PlCircle circ) {
		int currX = circ.getIntX();
		int currY = circ.getIntY();

		Color currColor = circ.getCurrColor();

		if (isExist(currX - 1, currY)) {

			if (isCanChange(pl[currX - 1][currY])) {
				adsorb(pl[currX - 1][currY], currColor);
			}
		}

		if (isExist(currX - 1, currY + 1)) {

			if (isCanChange(pl[currX - 1][currY + 1])) {
				adsorb(pl[currX - 1][currY + 1], currColor);
			}
		}

		if (isExist(currX, currY + 1)) {

			if (isCanChange(pl[currX][currY + 1])) {
				adsorb(pl[currX][currY + 1], currColor);
			}
		}

		if (isExist(currX + 1, currY)) {

			if (isCanChange(pl[currX + 1][currY])) {
				adsorb(pl[currX + 1][currY], currColor);
			}
		}

		if (isExist(currX + 1, currY - 1)) {

			if (isCanChange(pl[currX + 1][currY - 1])) {

				adsorb(pl[currX + 1][currY - 1], currColor);
			}
		}

		if (isExist(currX, currY - 1)) {

			if (isCanChange(pl[currX][currY - 1])) {
				adsorb(pl[currX][currY - 1], currColor);
			}
		}
		updateTitle();
	}

	private boolean isExist(int x, int y) {
		return (x >= 0 && y >= 0) && (x <= 4 && y <= 4);
	}

	private boolean isCanChange(PlCircle changePl) {

		return changePl.isVisible();
	}

	private void adsorb(PlCircle circ, Color currColor) {

		if (currColor == Color.RED) {
			circ.setFillInt(1);
			circ.setBoolStep(true);
		}

		if (currColor == Color.BLUE) {
			circ.setFillInt(-1);
			circ.setBoolStep(false);
		}
		circ.setFill(currColor);
	}
}