package GameLogic;

import java.awt.Toolkit;

import javax.swing.JPanel;

public class TimerGraphic extends Thread{

	private JPanel panel;
	private int FPS = 60;
	
	public TimerGraphic(JPanel panel) {
		this.panel = panel;
	}
	
	@Override
	public void run() {
		while(true) {
			panel.repaint();
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
