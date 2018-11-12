package GameLogic;

import Component.Cell2D;

public class MyShot {
	Cell2D position;
	int speed;

	public MyShot(int x, int y, int speed) {
		position = new Cell2D(x, y);
		this.speed = speed;
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}
	
	public void setY(int y) {
		position.setY(y);
	}

	public void scroll() {
		this.position.setX(position.getX() + speed);
	}
}
