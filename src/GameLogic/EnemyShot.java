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

	// Il metodo start viene chiamato quando l'aereo del giocatore si trova nel
	// range di tiro dei nemici
	public void start(int y, boolean fire) {
		position.setX(position.getX());
	}
}
