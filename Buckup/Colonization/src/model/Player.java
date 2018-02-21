package model;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Player {

	private Hexagon player;
	private double mouseX, mouseY;
	private double oldX, oldY;

	public Player(int x, int y, int fill){

		player.setOnMousePressed(event -> {
			mouseX = event.getSceneX();
			mouseY = event.getSceneY();
			// System.out.println("MuseX: " + mouseX + " MouseY: " + mouseY);
		});

		player.setOnMouseDragged(e -> {
			player.relocate(e.getSceneX() - mouseX + oldX - 22, e.getSceneY() - mouseY + oldY - 25);
			System.out.println("X: " + (e.getSceneX()) + "Y:" + (e.getSceneY()));
		});

	}

	public void move(int x, int y) {
		oldX = player.getX(x, y);
		oldY = player.getY(x, y);
		relocate(oldX, oldY);
	}

	public void abortMove() {
		player.relocate(oldX, oldY);
	}

}
