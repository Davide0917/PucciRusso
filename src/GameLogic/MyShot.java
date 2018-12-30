package GameLogic;

import Component.Cell2D;

public class MyShot {
	Cell2D position;
	int speed;
	boolean fire = false;
	GameEngine word;
	
	
	public boolean isFire() {
		return fire;
	}

	public void setFire(boolean fire) {
		this.fire = fire;
		
	}

	public MyShot(int x, int y, int speed) {
		position = new Cell2D(x, y);
		this.speed = speed;
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
		this.position.setX(position.getX() + speed);

	}

}
