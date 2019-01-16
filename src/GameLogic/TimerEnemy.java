package GameLogic;

public class TimerEnemy extends Thread {
	
	private GameEngine ge;
	
	int FPS = 10;
	
	public TimerEnemy(GameEngine ge) {
		this.ge = ge;
	}
	public void run() {
		while(true) {
				ge.startEnemy();
			try {
				sleep(500 );
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	

}
