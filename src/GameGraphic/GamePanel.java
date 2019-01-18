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
	private int ResolutionX, ResolutionY;
	private int DimPlaneX, DimPlaneY;
	// Reference al toolkit e alle immagini
	Toolkit tk;
	MediaTracker spriteTracker;
	Map<String, Image> sprites;
	STATE state;

	// JUST FOR DEBUG
	// Airplane plane;
	// World è un oggetto gameEngine dove gira è presente tutta la logica del gioco
	GameEngine world;
	// Enemy nemico;

	public GamePanel(GameEngine world) {
		super();

		gamemode = GAMEMODE.OFFLINE;

		// Debug
		// plane = new Airplane(-50, 100, 5, 7);
		// nemico = new Enemy(500, 100, 1, 2);

		this.world = world;
		// MShot = new MyShot(0, 0, 20);
		// MShot = new MyShot(0, 0, 20);
		initGUI();
		initEH();

		state = STATE.GAME;
	}

	public void paintComponent(Graphics g) {
		// Richiamo il costruttore di base
		super.paintComponent(g);

		g.drawImage(sprites.get("Sfondo"), 0, 0, ResolutionX, ResolutionY, null);

		Cell2D dims = GetScaledDims(sprites.get("Player").getWidth(null), sprites.get("Player").getHeight(null));
		Cell2D dimLifes = GetScaledDims(sprites.get("Lifes").getWidth(null), sprites.get("Lifes").getHeight(null));
		Cell2D dimShot = GetScaledDims(sprites.get("Shot").getWidth(null), sprites.get("Shot").getHeight(null));

		Cell2D dimEnemy = GetScaledDims(sprites.get("Enemy").getWidth(null), sprites.get("Enemy").getHeight(null));

		DimPlaneX = dims.getX();
		DimPlaneY = dims.getY();

		g.drawImage(sprites.get("Player"), world.p.getX(), world.p.getY(), dims.getX(), dims.getY(), null);
		for (int i = 0; i < world.p.getLifes(); i++) {
			g.drawImage(sprites.get("Lifes"), (ResolutionX / 3) + (i * (dimLifes.getX() / 10)),
					(ResolutionY - (dimLifes.getY() / 4)), dimLifes.getX() / 2, dimLifes.getY() / 2, null);
		}
		// Lo sprite dei nemici viene ristampata fino a quando non è stata letta tutta
		// la linkedlist
		if (world.getIndex() < 10)
			g.drawImage(sprites.get("Enemy"), world.lsEnemy.get(world.getIndex()).getX(),
					world.lsEnemy.get(world.getIndex()).getY(), dimEnemy.getX() * 2, dimEnemy.getY() * 2, null);
		else
			setState(STATE.HOME);

		
		if (world.llShot!=null) {
			for(int i = 0; i < world.llShot.size(); i ++)
			g.drawImage(sprites.get("Shot"),(world.llShot.get(i).getX() + (dims.getX() / 2)), (world.llShot.get(i).getY() + (dims.getY() / 3)),
		  (dimShot.getX() / 7), (dimShot.getY() / 10), null); }
		 

	}

	public void initGUI() {
		// Creo gli oggetti per caricare e memorizzare le immagini
		this.setBackground(new Color(255, 255, 255));
		tk = Toolkit.getDefaultToolkit();
		spriteTracker = new MediaTracker(this);
		sprites = new HashMap<>();

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
				if (e.getKeyCode() == KeyEvent.VK_UP && world.p.getY() > 0 - sprites.get("Enemy").getHeight(null) / 2)
					world.p.move(-1);
				else if (e.getKeyCode() == KeyEvent.VK_DOWN && world.p.getY() < ResolutionY
						- (sprites.get("Enemy").getHeight(null) + sprites.get("Enemy").getHeight(null) / 2))
					world.p.move(1);

				//da gestire il movimento e sparo sincro 
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					world.setShot(new Cell2D(world.p.getX(), world.p.getY()));
				}	
				
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
					state = STATE.PAUSE;
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
}
