package GameLogic;

import Component.Cell2D;

public class EnemyShot {
	Cell2D position;

	// Le coordinate iniziali del proiettile dovranno essere le stesse del nemico
	// che l'ha sparato
	public EnemyShot(int x, int y) {
		position = new Cell2D(x, y);
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}

	public void scroll() {
		int speed = 10;
		position.setX(getX() - speed);
	}

	public void move(int dir) {
		position.setY(getY() * (dir + 1));
	}

}
