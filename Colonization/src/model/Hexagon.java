package model;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Hexagon extends Polygon {
	static final int RADIUS = 25;

	private double centerX;
	private double centerY;
	private int posX;
	private int posY;

	public Hexagon(int x, int y) {
		this(x, y, 0);
	}

	public Hexagon(int x, int y, int fill) {
		posX = x;
		posY = y;
		for (int i = 0; i < 6; i++) {
			getPoints().add(RADIUS * sin(i * PI / 3));
			getPoints().add(RADIUS * cos(i * PI / 3));
		}
		
		centerX = getX(x, y);
		centerY = getY(x, y);
		setTranslateX(getX(x, y));
		setTranslateY(getY(x, y));
		
		setVisible(true);
		
		setFill(Color.DARKGRAY);
		
		setOnMouseEntered(event -> {
			setFill(Color.CHARTREUSE);

		});

		setOnMouseExited(event -> {
			setFill(Color.DARKGRAY);
		});
		
	}
	
	public int getIntX() {
		return posX;
	}

	public int getIntY() {
		return posY;
	}

	public double getCenterX() {
		return centerX;
	}

	public double getCenterY() {
		return centerY;
	}

	public boolean getEventXY(MouseEvent e) {
		if(contains(e.getX(),e.getY())) {
			return true;
		}
		else return false;
	}
	
	public double getX(int x, int y) {
		return 300 + x * RADIUS + y * 2 * RADIUS;
	}

	public double getY(int x, int y) {
		return 200 + x * sqrt(3) * RADIUS;
	}
}