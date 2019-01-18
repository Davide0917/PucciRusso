package GameLogic;

import Component.Cell2D;

public class Bullet {
	Cell2D position;
	int speed;
	boolean fire = false;
	
	
	public Bullet(int x, int y, int speed) {
		position = new Cell2D(x, y);
		this.speed = speed;
	}
	
	public boolean isFire() {
		return fire;
	}

	public void setFire(boolean fire) {
		this.fire = fire;
		
	}

	public int getX() {
		return position.getX();
	}

	public void setX(int y) {
		position.setY(y);
	}

	public int getY() {
		return position.getY();
	}

	public void setY(int y) {
		position.setY(y);
	}

	public void scroll() {
		//System.out.println(getX());
		this.position.setX(position.getX() + speed);

	}
	public void reset() {
		this.position.setX(0);
	}

}
