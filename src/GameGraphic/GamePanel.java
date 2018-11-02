package GameGraphic;

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
		
		//Inserisco la coppia tag -> immagine
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
