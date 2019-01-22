package GameLogic;

import java.awt.Rectangle;

import Component.Cell2D;

public class Airplane extends GameObject{
	int lifes;
	Cell2D Grafics = new Cell2D(0,0);

	public Airplane(int x, int y, int lifes, int speed) {
		super(x,y,speed);
		this.lifes = lifes;
	}

	public void move(int dir) {
		this.position.setY(this.getY() + dir * speed);
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
	public Bullet Shoot(Cell2D Position, int speedBullet, Cell2D Grafic) {

		//va messo un controllo 
			Bullet B = new Bullet(Position.getX(), Position.getY(), speedBullet, true, Grafic.getX(), Grafic.getY());
			return B;
		
	}
	public Rectangle getBoundsPlayer() {
        return new Rectangle(getX(), getY(), getGrafics().getX(), getGrafics().getY());
    }

	public Cell2D getGrafics() {
		return Grafics;
	}

	public void setGrafics(Cell2D grafics) {
		Grafics = grafics;
	}

}
