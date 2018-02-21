package model;

import static java.lang.Math.sqrt;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class PlCircle extends Ellipse {

	private double oldX, oldY;
	private int RADIUS = 20;
	private boolean boolStep;
	private boolean isCanGo = true;
	private boolean isCanJump;
	private int intX, intY;
	private Color currColor;
	private int fill;

	private final Color COLORRED = Color.RED;
	private final Color COLORBLUE = Color.BLUE;

	public PlCircle() {
		setRadiusX(RADIUS);
		setRadiusY(RADIUS);
		setVisible(false);
	}
	
	public PlCircle(int x, int y, int fill) {

		setCenterX(getX(x, y));
		setCenterY(getY(x, y));
		setRadiusX(RADIUS);
		setRadiusY(RADIUS);
		setVisible(false);
		this.fill = fill;
		intX = x;
		intY = y;
		oldX = getX(x, y);
		oldY = getY(x, y);

		if (fill != 0) {
			if (fill == 1) {
				currColor = COLORRED;
				setFill(currColor);
				boolStep = true;
			}
			if (fill == -1) {
				currColor = COLORBLUE;
				setFill(currColor);
				boolStep = false;
			}
			setVisible(true);

		}
	}

	public void setFillInt(int fill) {
		this.fill = fill;
	}

	public int getFillInt() {
		return fill;
	}

	public Color getCurrColor() {
		return currColor;
	}

	private double getX(int x, int y) {
		return 300 + x * 25 + y * 2 * 25;
	}

	private double getY(int x, int y) {
		return 200 + x * sqrt(3) * 25;
	}

	public void abortMove() {
		relocate(oldX - RADIUS, oldY - RADIUS);
	}

	public boolean isCanJump() {
		return isCanJump;
	}

	public void setCanJump(boolean isCanJump) {
		this.isCanJump = isCanJump;
	}

	public boolean isCanGo() {
		return isCanGo;
	}

	public void setCanGo(boolean isCanGo) {
		this.isCanGo = isCanGo;
	}

	public boolean isBoolStep() {
		return boolStep;
	}

	public void setBoolStep(boolean boolStep) {
		this.boolStep = boolStep;
	}

	public void setIntX(int intX) {
		this.intX = intX;
	}

	public void setIntY(int intY) {
		this.intY = intY;
	}

	public int getIntY() {
		return intY;
	}

	public int getIntX() {
		return intX;
	}
}
