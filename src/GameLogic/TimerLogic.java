package GameLogic;



public class TimerLogic extends Thread {

	private GameEngine ge;
	
	
	public int FPS = 60;

	public TimerLogic(GameEngine ge) {
		this.ge = ge;
		
	}
	
	@Override
	public void run() {
		while(true) {
				ge.FixedUpdate("Enemy");
				
				if(ge.s.isFire()==true)
					
				ge.FixedUpdate("Shot");
			try {
				sleep(500 / FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	
}
