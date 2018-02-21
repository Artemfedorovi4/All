package model;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static java.lang.Math.*;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Hexagon extends Pane {
	static final double RADIUS = 25;
	// private boolean isCl;
	private final Color COLORRED = Color.RED;
	private final Color COLORBLUE = Color.BLUE;
	private double mouseX, mouseY;
	private double oldX, oldY;

	private Group allHex = new Group();
	// private Group allBorders = new Group();
	private Polygon hex = new Polygon();
	private Polygon player = new Polygon();

	public Hexagon(int x, int y, int fill) {

		for (int i = 0; i < 6; i++) {
			hex.getPoints().add(RADIUS * sin(i * PI / 3));
			hex.getPoints().add(RADIUS * cos(i * PI / 3));

			player.getPoints().add(RADIUS * sin(i * PI / 3));
			player.getPoints().add(RADIUS * cos(i * PI / 3));
		}

		setVisible(true);

		hex.setTranslateX(getX(x, y));
		hex.setTranslateY(getY(x, y));
		hex.setFill(Color.DARKGRAY);
		move(x,y);
		if (fill != 0) {
			
			player.setOpacity(0.5);
			if (fill == 1) {
				player.setFill(COLORRED);
			}
			if (fill == 2) {
				player.setFill(COLORBLUE);
			}
			player.setVisible(true);
		}
		// hex.onMouseDraggedProperty().set(new EventHandler<MouseEvent>() {
		// @Override
		// public void handle(MouseEvent event) {
		// //double offsetX = event
		//
		// }
		// });
		allHex.getChildren().addAll(hex, player);
		// allBorders.getChildren().add(border);

		hex.setOnMouseEntered(event -> {
			hex.setFill(Color.CHARTREUSE);
			// border.setVisible(true);
		});

		hex.setOnMouseExited(event -> {
			hex.setFill(Color.DARKGRAY);
			// border.setVisible(false);
		});
		player.setOnMousePressed(event -> {
			mouseX = event.getSceneX();
			mouseY = event.getSceneY();
			//System.out.println("MuseX: " + mouseX + " MouseY: " + mouseY);
		});

		player.setOnMouseDragged(e -> {
			player.relocate(e.getSceneX() - mouseX + oldX-22, e.getSceneY() - mouseY + oldY-25);
			System.out.println("X: " + (e.getSceneX()) + "Y:" + (e.getSceneY()));
		});
	}

	public Group getGropHex() {
		return allHex;
	}

	private double getX(double x, double y) {
		return 300 + x * RADIUS + y * 2 * RADIUS;
	}

	private double getY(double x, double y) {
		return 200 + x * sqrt(3) * RADIUS;
	}

	public void move(int x, int y) {
		oldX = getX(x, y);
		oldY = getY(x, y);
		player.setScaleX(oldX);
		player.setScaleY(oldY);
	}
	
	public void abortMove(){
		player.relocate(oldX,oldY);
	}
}
