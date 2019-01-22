package GameLogic;

import Component.Cell2D;

public class EnemyShot {
	Cell2D position;
	int speed;

	// Le coordinate iniziali del proiettile dovranno essere le stesse del nemico
	// che l'ha sparato
	public EnemyShot(int x, int y, int speed) {
		position = new Cell2D(x, y);
		this.speed = speed;
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}

	public void scroll() {
		position.setX(getX() - speed);
	}

	public void move(int dir) {
		position.setY(getY() * (dir + 1));
	}

}
