package GameLogic;

import Component.Cell2D;

public class Enemy {
	Cell2D position;
	int lifes;

	public Enemy(int x, int y, int lifes) {
		position = new Cell2D(x, y);
		this.lifes = lifes;
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

}
