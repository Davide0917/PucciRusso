package GameLogic;

import Component.Cell2D;

public class GameObject {
	String type;
	Cell2D position;
	int life;
	int speed;
	
	public GameObject() {
		type = " ";
		position.setX(0);
		position.setY(0);
		life = 0;
		speed = 0;
		
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
	
	public void setLife(int x) {
		this.life = x;
	}
	
	public int getLifes() {
		return life;
	}
	
	public void setSpeed(int x) {
		this.speed = x;
	}
	
	public int getSpeed() {
		return speed;
	}
}
