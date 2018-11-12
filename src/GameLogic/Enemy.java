package GameLogic;

import Component.Cell2D;

public class Enemy {
	Cell2D position;
	int lifes;
	int speed;

	public Enemy(int x, int y, int lifes, int speed) {
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

	public int getLifes() {
		return lifes;
	}

	public void setLifes(int lifes) {
		this.lifes = lifes;
	}

	public void scroll() {
		this.position.setX(this.getX() - speed);
	}

	// Metodo fire restituisce true quando il player è nel range di un nemico
	// facendolo quindi sparare
	public boolean fire(int y) {
		int range = 20;
		int rangetop = position.getY() + range, rangebottom = position.getY() - range;
		if (y >= rangebottom && y <= rangetop)
			return true;
		else
			return false;
	}

}
