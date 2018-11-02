package GameGraphic;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame {
	
	//Parametri per gestire fullscreeen e dimensioni
	static boolean FULL_SCREEN = true;
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	static int WIDTH = 800, HEIGHT = 600;

	static JFrame frame;
	static JPanel panel;

	public static void main(String[] args) {
		
		//Creo il frame
		frame = new JFrame("MyFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		
		//Setto il frame in fullscreen
		if (!FULL_SCREEN) {
			frame.setUndecorated(false);
			frame.setResizable(false);
		} else {
			frame.setUndecorated(true);
			device.setFullScreenWindow(frame);
			WIDTH = device.getFullScreenWindow().getWidth();
			HEIGHT = device.getFullScreenWindow().getHeight();
		}
		
		//Creo il pannello di gioco
		panel = new GamePanel();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		//Aggiungo il panello di gioco e lo rendo visibile
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		//Do il focus (trigger degli eventi) al panello
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		frame.repaint();
	}
}
