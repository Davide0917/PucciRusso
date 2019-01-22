package GameGraphic;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.JPanel;

import GameLogic.GameEngine;
import GameLogic.TimerEnemy;
import GameLogic.TimerGraphic;
import GameLogic.TimerLogic;
import GameLogic.World;

public class MyFrame {

	static boolean FULL_SCREEN = true;
	//Parametri per gestire fullscreeen e dimensioni
	static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	static int WIDTH = 800, HEIGHT = 600;

	static JFrame frame;
	static STATE state;
	static JPanel gamePanel, menuPanel, currentPanel;

	static GameEngine ge;
	static TimerLogic tl;
	static TimerGraphic tg;
	static TimerEnemy te;

	public static void main(String[] args) throws InterruptedException {

		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);

		if (!FULL_SCREEN) {
			frame.setUndecorated(false);
			frame.setResizable(false);
		} else {
			frame.setUndecorated(true);
			device.setFullScreenWindow(frame);
			WIDTH = device.getFullScreenWindow().getWidth();
			HEIGHT = device.getFullScreenWindow().getHeight();
		
		}
		menuPanel = new GameMenu();
		SwitchPanel(menuPanel);
		state = ((GameMenu) menuPanel).getState();

		while (state != STATE.EXIT) {
			while (state == STATE.GAME) {
				Thread.sleep(1000);
				state = ((GamePanel) gamePanel).getState();
			}

			if (state == STATE.HOME || state == STATE.GAMEOVER) {
				StopGame();
				SwitchPanel(menuPanel);
				((GameMenu) menuPanel).setState(STATE.HOME);
			}
			
			while (state == STATE.HOME || state == STATE.GAMEOVER) {
				Thread.sleep(1000);
				state = ((GameMenu) menuPanel).getState();
			}
			
			if (state == STATE.GAME)
				StartGame(GAMEMODE.OFFLINE);
			
			
			if (state == STATE.PAUSE) {
				SwitchPanel(menuPanel);
				PauseGame();
				((GameMenu) menuPanel).setState(STATE.PAUSE);
			}
			
			while (state == STATE.PAUSE) {
				Thread.sleep(1000);
				state = ((GameMenu) menuPanel).getState();
			}
			
			if (state == STATE.GAME) {
				ResumeGame();
				SwitchPanel(gamePanel);
				((GamePanel) gamePanel).setState(STATE.GAME);
			}
		}

		System.exit(0);
	}

	private static void StartGame(GAMEMODE gamemode) {
	
		if (tl == null || !tl.isAlive() || tg == null || tg.isAlive() || te == null || !te.isAlive() ) {
	
			/*if(ge != null)
				ge.Stop;
			*/
			ge = new GameEngine(WIDTH,HEIGHT);
			gamePanel = new GamePanel(ge);
			SwitchPanel(gamePanel);
	
			// Start threads
			tl = new TimerLogic(ge);
			tg = new TimerGraphic(gamePanel);
			te = new TimerEnemy(ge);
			
			tl.start();
			tg.start();
			te.start();
			
			state = STATE.GAME;
		} else {
			ResumeGame();
		}
	}

	private static void StopGame() {	
		if (tl != null && tl.isAlive())
			tl.stop();
		if (tg != null && tg.isAlive())
			tg.stop();	
		if (te != null && te.isAlive())
			te.stop();	
	
	}
	
	private static void PauseGame() {
		if (tl != null && tl.isAlive())
			tl.suspend();
		if (tg != null && tg.isAlive())
			tg.suspend();
		if (te != null && te.isAlive())
			te.suspend();
		
	}
	
	private static void ResumeGame() {
		if (tl != null) 
			tl.resume();
		if (tg != null) 
			tg.resume();
		if (te != null) 
			te.resume();
		
	}

	private static void SwitchPanel(JPanel panel) {
		if (currentPanel != null)
			frame.remove(currentPanel);
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		frame.add(panel);
		frame.pack();
		frame.setVisible(true);	

		panel.setFocusable(true);
		panel.requestFocusInWindow();
		frame.repaint();

		currentPanel = panel;
	}
}

