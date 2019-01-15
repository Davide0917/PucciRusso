package GameLogic;

import Component.Cell2D;

public class Enemy {
	Cell2D position;
	int lifes;
	int speed;
	int align_speed = 1;

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

	public int getSpeed() {
		return speed;
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

	public void align(int dir) {
		this.position.setY(this.getY() + (align_speed * dir));
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
