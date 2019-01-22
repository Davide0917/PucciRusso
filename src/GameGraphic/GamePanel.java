/*package GameGraphic;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import Component.Cell2D;
import GameLogic.Airplane;

public class GamePanel extends JPanel {

	// Reference alla risoluzione del panello
	private int ResolutionX, ResolutionY;

	// Reference al toolkit e alle immagini
	Toolkit tk;
	MediaTracker spriteTracker;
	Map<String, Image> sprites;
	

	// JUST FOR DEBUG
	Airplane plane;
	//GameEngine ge.
	
	public GamePanel() {
		super();
		
		//Debug
		plane = new Airplane(100, 100, 3, 7);

		initGUI();
		initEH();
	}
	
	public void paintComponent(Graphics g) {
		//Richiamo il costruttore di base
		super.paintComponent(g);
		
		g.drawImage(sprites.get("Sfondo"), 0, 0, ResolutionX, ResolutionY, null);
		
		Cell2D dims = GetScaledDims(sprites.get("Player").getWidth(null), sprites.get("Player").getHeight(null));
		g.drawImage(sprites.get("Player"), plane.getX(), plane.getY(), dims.getX(), dims.getY(), null);
	}

	public void initGUI() {
		//Creo gli oggetti per caricare e memorizzare le immagini
		this.setBackground(new Color(255, 255, 255));
		tk = Toolkit.getDefaultToolkit();
		spriteTracker = new MediaTracker(this);
		sprites = new HashMap<>();
		
		//Inserisco la coppia tag -> immagine ()
		sprites.put("Player", tk.getImage("resources/image/plane.png"));
		sprites.put("Sfondo", tk.getImage("resources/image/sfondo.png"));
		
		//Aggiungo le immagini al mediaTracker per tenerne traccia
		int index = 0;
		for (Map.Entry<String, Image> entry : sprites.entrySet()) {
			spriteTracker.addImage(entry.getValue(), index);
			index++;
		}
		
		try {
			//Lo spriteTracker interrompe il programma fino al caricamento delle immagini
			spriteTracker.waitForAll(); 
		} catch (InterruptedException ex) {
			System.out.println("Immagini non caricate!");
			System.out.println(ex);
		}
		
		System.out.println("Immagini caricate ...");
	}

	public void initEH() {
		// Aggiungo la gestione degli eventi da tastiera
		this.addKeyListener(new KeyAdapter() {
			// Evento pressione tastino
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP) plane.move(-1);
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) plane.move(1);
				repaint();
			}
		});
	}
	
	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);

		ResolutionY = (int) preferredSize.getHeight();
		ResolutionX = (int) preferredSize.getWidth();
	}

	public Cell2D GetScaledDims(int startWidth, int startHeigth) {
		//larghezzaR : 1920 = x : ResolutionX
		//x = (larghezzaR * ResolutionX) / 1920
		int x = (startWidth * ResolutionX) / 1920;
		int y = (startHeigth * ResolutionY) / 1080;
		return new Cell2D(x, y);
	}
}
*/
package GameGraphic;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.*;

import Component.Cell2D;
import GameLogic.Airplane;
import GameLogic.Enemy;
import GameLogic.GameEngine;
import GameLogic.Bullet;

enum GAMEMODE {
	OFFLINE
};

public class GamePanel extends JPanel {

	private GAMEMODE gamemode;

	// Reference alla risoluzione del panello
	static private int ResolutionX, ResolutionY;

	// Reference al toolkit e alle immagini
	Toolkit tk;
	MediaTracker spriteTracker;
	Map<String, Image> sprites;
	STATE state;

	// JUST FOR DEBUG
	// Airplane plane;
	// World è un oggetto gameEngine dove gira è presente tutta la logica del gioco
	GameEngine world;
	Cell2D dimShot;
	Cell2D dimEnemy;
	Cell2D dims;
	// Enemy nemico;
	protected int SpeedEnemy;
	protected int SpeedBullet;

	public GamePanel(GameEngine world) {
		super();

		gamemode = GAMEMODE.OFFLINE;
		this.world = world;

		initGUI();
		initEH();

		state = STATE.GAME;

	}

	public void paintComponent(Graphics g) {
		// Richiamo il costruttore di base
		super.paintComponent(g);

		g.drawImage(sprites.get("Sfondo"), 0, 0, world.width, world.height, null);

		dims = GetScaledDims(sprites.get("Player").getWidth(null), sprites.get("Player").getHeight(null));
		Cell2D dimLifes = GetScaledDims(sprites.get("Lifes").getWidth(null), sprites.get("Lifes").getHeight(null));
		dimShot = GetScaledDims(sprites.get("Shot").getWidth(null) / 7, sprites.get("Shot").getHeight(null) / 10);

		dimEnemy = GetScaledDims(sprites.get("Enemy").getWidth(null) * 2, sprites.get("Enemy").getHeight(null) * 2);

		if (world.p.getLifes() <= 0)
			setState(STATE.HOME);
		for (int i = 0; i < world.p.getLifes(); i++) {
			g.drawImage(sprites.get("Lifes"), (ResolutionX / 3) + (i * (dimLifes.getX() / 10)),
					(ResolutionY - (dimLifes.getY() / 4)), dimLifes.getX() / 2, dimLifes.getY() / 2, null);
		}
		// Lo sprite dei nemici viene ristampata fino a quando non è stata letta tutta
		// la linkedlist
		if (world.lsEnemy != null)
			for (int i = 0; i < world.lsEnemy.size(); i++)
				g.drawImage(sprites.get("Enemy"), world.lsEnemy.get(i).getX(), world.lsEnemy.get(i).getY(),
						dimEnemy.getX(), dimEnemy.getY(), null);
		else
			setState(STATE.HOME);

		if (world.llShot != null) {
			for (int i = 0; i < world.llShot.size(); i++)
				g.drawImage(sprites.get("Shot"), (world.llShot.get(i).getX()), (world.llShot.get(i).getY()),
						(dimShot.getX()), (dimShot.getY()), null);
		}

		g.drawImage(sprites.get("Player"), world.p.getX(), world.p.getY(), dims.getX(), dims.getY(), null);
		if (world.p.isPass() == true) {
			setGraficsPlayer();
			setGraficsEnemy();
		}
	}

	public void initGUI() {
		// Creo gli oggetti per caricare e memorizzare le immagini
		this.setBackground(new Color(255, 255, 255));
		tk = Toolkit.getDefaultToolkit();
		spriteTracker = new MediaTracker(this);
		sprites = new HashMap<>();
		world.p.setPass(true);
		// Inserisco la coppia tag -> immagine
		sprites.put("Player", tk.getImage("resources/image/plane.png"));
		sprites.put("Sfondo", tk.getImage("resources/image/sfondo.png"));
		sprites.put("Shot", tk.getImage("resources/image/shot.png"));
		sprites.put("Lifes", tk.getImage("resources/image/Cuore3.png"));

		sprites.put("Enemy", tk.getImage("resources/image/Enemy.png"));

		// Aggiungo le immagini al mediaTracker per tenerne traccia
		int index = 0;
		for (Map.Entry<String, Image> entry : sprites.entrySet()) {
			spriteTracker.addImage(entry.getValue(), index);
			index++;
		}

		try {
			// Lo spriteTracker interrompe il programma fino al caricamento delle immagini
			spriteTracker.waitForAll();
		} catch (InterruptedException ex) {
			System.out.println("Immagini non caricate!");
			System.out.println(ex);
		}

		System.out.println("Immagini caricate ...");
	}

	public void initEH() {

		// Aggiungo la gestione degli eventi da tastiera
		this.addKeyListener(new KeyAdapter() {
			// Evento pressione tastino
			@Override
			public void keyPressed(KeyEvent e) {

				// I limite del aereo non funge in risoluzioni più piccole (da sistemare )
				// Limito i movimenti dell'aereo in base alle dimensioni dello schermo
<<<<<<< HEAD
				if (e.getKeyCode() == KeyEvent.VK_UP && world.p.getY() > dims.getY() / 2)
					world.p.move(-1);

				else if (e.getKeyCode() == KeyEvent.VK_DOWN && world.p.getY() < ResolutionY - ResolutionY / 4)
=======
				if ((e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) && world.p.getY() > 0 - sprites.get("Enemy").getHeight(null) / 2)
					world.p.move(-1);
				else if ((e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) && world.p.getY() < ResolutionY
						- (sprites.get("Enemy").getHeight(null) + sprites.get("Enemy").getHeight(null) / 2))
>>>>>>> d516257af9854926d7ba4eb980c16f01cfd18099
					world.p.move(1);

				// da gestire il movimento e sparo sincro
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					state = STATE.PAUSE;
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					world.setShot(
							new Cell2D(world.p.getX() + (dims.getX() - dims.getX() / 7),
									(world.p.getY() + (dims.getY()) - dims.getY() / 6)),
							(world.width / 230), new Cell2D(dimShot.getX(), dimShot.getY()));
				}
				repaint();
			}
		});
	}

	@Override
	public void setPreferredSize(Dimension preferredSize) {
		super.setPreferredSize(preferredSize);

		ResolutionY = (int) preferredSize.getHeight();
		ResolutionX = (int) preferredSize.getWidth();
	}

	public Cell2D GetScaledDims(int startWidth, int startHeigth) {
		// larghezzaR : 1920 = x : ResolutionX
		// x = (larghezzaR * ResolutionX) / 1920
		int x = (startWidth * ResolutionX) / 1920;
		int y = (startHeigth * ResolutionY) / 1080;
		return new Cell2D(x, y);
	}

	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	public void setGraficsPlayer() {
		world.p.setGrafics(new Cell2D(dims.getX(), dims.getY()));
		world.p.setPass(false);
	}

	public void setGraficsEnemy() {
		world.setDimsEnemy(new Cell2D(dimEnemy.getX(), dimEnemy.getY()));

	}

}
