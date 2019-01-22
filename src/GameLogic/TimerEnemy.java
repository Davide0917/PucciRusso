package GameLogic;

import GameGraphic.GamePanel;

public class TimerEnemy extends Thread {
	
	private GameEngine ge;
	
	int FPSmin = 25;
	int FPSmax = 55;
	
	public TimerEnemy(GameEngine ge) {
		this.ge = ge;
	}
	public void run() {
		while(true) {
			
				ge.CreateEnemy();
			
			try {
				int i = GameEngine.randInt(FPSmin, FPSmax);
				sleep(i*100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
	}
}

