package GameLogic;

import Component.Cell2D;

public class MyShot {
	Cell2D position;

	public MyShot(int x, int y) {
		position = new Cell2D(x, y);
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}

	public void scroll() {
		this.position.setX(position.getX() + 1);
	}
}
