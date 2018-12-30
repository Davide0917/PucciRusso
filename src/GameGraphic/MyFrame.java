package GameGraphic;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GameLogic.GameEngine;
import GameLogic.TimerGraphic;
import GameLogic.TimerLogic;

public class MyFrame {
	
	//Parametri per gestire fullscreeen e dimensioni
	static boolean FULL_SCREEN = true;
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	static int WIDTH = 1200, HEIGHT = 900;

	static JFrame frame;
	static JPanel panel;
	static TimerLogic Tl;
	static TimerGraphic Tg;
	static GameEngine ge;

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
		
		//Chiamo l'avvio dei threads
		ge = new GameEngine();
		
		//Creo il pannello di gioco
		panel = new GamePanel(ge);
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		//Aggiungo il panello di gioco e lo rendo visibile
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);

		//Do il focus (trigger degli eventi) al panello
		panel.setFocusable(true);
		panel.requestFocusInWindow();
		frame.repaint();
		Go();		
	}
	public static void Go() {
		System.out.println("Avvio Timer Logic e Graphic");
		Tl = new TimerLogic(ge);
		Tg = new TimerGraphic(panel);
		Tl.start();
		Tg.start();
	}
	
}
