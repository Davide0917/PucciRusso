package GameLogic;

import Component.Cell2D;

public class Shot {
	Cell2D position;

	public Shot(int x, int y) {
		position = new Cell2D(x, y);
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}

	public void move() {
		this.position.setX(position.getX() + 1);
	}
}
