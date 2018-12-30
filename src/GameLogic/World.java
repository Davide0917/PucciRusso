package GameLogic;

public class World {
	private int width;
	private int height;
	private GameObject[][] world;
	

	public World(int width, int height) {
		this.width = width;
		this.height = height;
		
		world = new GameObject[width][height];
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public GameObject[][] getWord() {
		return world;
	}

	public void setWord(GameObject[][] world) {
		this.world = world;
	}

	
	

}
