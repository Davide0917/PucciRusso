package GameLogic;

import Component.Cell2D;

public class EnemyShot extends GameObject {

	// Le coordinate iniziali del proiettile dovranno essere le stesse del nemico
	// che l'ha sparato
	public EnemyShot(int x, int y, int speed) {
		super(x, y, speed);
	}

	public void scroll() {
		position.setX(getX() - speed);
	}

	public void move(int dir) {
		position.setY(getY() * (dir + 1));
	}

}
