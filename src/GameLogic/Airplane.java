package GameLogic;

import Component.Cell2D;

public class Airplane {
	Cell2D position;
	int lifes;
	int speed;

	public Airplane(int x, int y, int lifes, int speed) {
		position = new Cell2D(x, y);
		this.lifes = lifes;
		this.speed = speed;
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}

	public void move(int dir) {
		position.setY(position.getY() + dir * speed);
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

}
