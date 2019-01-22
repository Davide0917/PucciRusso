package GameLogic;

import java.awt.Rectangle;

import Component.Cell2D;

public class Enemy {
	protected Cell2D position;
	protected Cell2D Grafic;
	int lifes;
	protected int speed;
	int align_speed = 1;
	boolean aliveEnemy;

	public Enemy(int x, int y, int lifes, int speed, boolean aliveEnemy, int DimX, int DimY) {
		position = new Cell2D(x, y);
		Grafic = new Cell2D(DimX, DimY);
		this.lifes = lifes;
		this.speed = speed;
		this.aliveEnemy = aliveEnemy;
	}

	public Cell2D getPosition() {
		return position;
	}

	public void setPosition(Cell2D position) {
		this.position = position;
	}

	public Cell2D getGrafic() {
		return Grafic;
	}

	public void setGrafic(Cell2D grafic) {
		Grafic = grafic;
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

	public boolean isAliveEnemy() {
		return aliveEnemy;
	}

	public void setAliveEnemy(boolean aliveEnemy) {
		this.aliveEnemy = aliveEnemy;
	}
	public Rectangle getBoundsEnemy() {
        return new Rectangle(getX(), getY(), getGrafic().getX(), getGrafic().getY());
    }

}
