package GameLogic;

import java.awt.Toolkit;

import javax.swing.JPanel;

public class TimerGraphic extends Thread{

	private JPanel gamePanel;
	private int FPS = 60;
	
	public TimerGraphic(JPanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void run() {
		while(true) {
			gamePanel.repaint();
			Toolkit.getDefaultToolkit().sync();
			try {
				sleep(500 / FPS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
