package GameLogic;

import java.awt.Rectangle;

import Component.Cell2D;

<<<<<<< HEAD
public class Airplane {
	protected Cell2D position;
	protected Cell2D Grafics;
	int lifes;
	protected int speed;
	boolean pass;

	public Airplane(int x, int y, int lifes, int speed, boolean pass) {
		position = new Cell2D(x, y);
		this.lifes = lifes;
		this.speed = speed;
		this.pass = pass;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
=======
public class Airplane extends GameObject{
	int lifes;

	public Airplane(int x, int y, int lifes, int speed) {
		super(x,y,speed);
		this.lifes = lifes;
>>>>>>> d516257af9854926d7ba4eb980c16f01cfd18099
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

<<<<<<< HEAD
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Bullet Shoot(Cell2D Position, int speedBullet, Cell2D Grafic) {
=======
	public Bullet Shoot(Cell2D Position) {
>>>>>>> d516257af9854926d7ba4eb980c16f01cfd18099
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
