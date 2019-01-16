package GameLogic;

import Component.Cell2D;

public class GameObject {
	Cell2D position;
	int speed;
	
	public GameObject(int x, int y, int speed) {
		this.position = new Cell2D(x, y);
		this.speed = speed;
	}
	
	public void setX(int x) {
		position.setX(x);
	}
	
	public void setY(int y) {
		position.setY(y);
	}
	
	public int getX() {
		return position.getX();
	}
	
	public int getY() {
		return position.getY();
	}
	
	public void setSpeed(int x) {
		this.speed = x;
	}
	
	public int getSpeed() {
		return speed;
	}
}
