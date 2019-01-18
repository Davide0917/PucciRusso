package GameLogic;

import Component.Cell2D;

public class Bullet extends GameObject {
	boolean fire = false;

	public Bullet(int x, int y, int speed) {
		super(x, y, speed);
	}

	public boolean isFire() {
		return fire;
	}

	public void setFire(boolean fire) {
		this.fire = fire;

	}

	public void scroll() {
		// System.out.println(getX());
		this.position.setX(position.getX() + speed);

	}

	public void reset() {
		this.position.setX(0);
	}

}
