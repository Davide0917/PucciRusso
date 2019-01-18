package GameLogic;

import Component.Cell2D;

public class Airplane extends GameObject{
	int lifes;

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

	public Bullet Shoot(Cell2D Position) {
		//va messo un controllo 
			Bullet B = new Bullet(Position.getX(), Position.getY(), 7);
			return B;
		
	}

}
