package GameLogic;

public class TimerEnemy extends Thread {
	
	private GameEngine ge;
	
	int FPSmin = 5;
	int FPSmax = 30;
	
	public TimerEnemy(GameEngine ge) {
		this.ge = ge;
	}
	public void run() {
		while(true) {
			
				ge.CreateEnemy();
			
			try {
				sleep((ge.randInt(FPSmin, FPSmax))*100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
	}
}

