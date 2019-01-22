package GameLogic;

import java.awt.List;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;

import javax.xml.crypto.dsig.spec.DigestMethodParameterSpec;

import Component.Cell2D;
import GameGraphic.GamePanel;

public class GameEngine {
	public Airplane p;
	public LinkedList<Enemy> lsEnemy;
	public LinkedList<Bullet> llShot;
<<<<<<< HEAD
	
	
=======
	public int shot_timer;
>>>>>>> d516257af9854926d7ba4eb980c16f01cfd18099
	int index;
	// Dal Frame mi prendo le dimensioni dello schermo
	public int width, height;
	int Px, Py;
	private Cell2D dims;
	int SpeedPlayer;
	private Cell2D dimsEnemy;
		
		
	public GameEngine(int width, int height) {
		
		this.width = width;
		this.height = height;
		
		Px = width/20;
		Py = height/3;
		SpeedPlayer = height/100;
		
		
		p = new Airplane(Px, Py, 3, SpeedPlayer, true);
		lsEnemy = new LinkedList<>();
		llShot = new LinkedList<>();

		// Carichiamo i nemici nella linkedlist tutti in posizioni casuali vicino alla y
		// dell'areoplano ma non riesco
		// a mappare bene la grandezza del mondo

	}

	public void CreateEnemy() {
		Enemy lE = new Enemy(width, randInt(p.getY() - height / 4, p.getY() + height / 4), 1,height / 280, true,
				getDimsEnemy().getX(), getDimsEnemy().getY()) ;
		if (lE != null)
			lsEnemy.add(lE); 		
	}
	
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randoNum = rand.nextInt((max - min) + 1) + min;
		if (randoNum < 0)
			return 0;
		return randoNum;
	}
	
	public Cell2D getDimsEnemy() {
		return dimsEnemy;
	}
	
	public void setDimsEnemy(Cell2D dimsEnemy) {
		this.dimsEnemy = dimsEnemy;
	}

	public void FixedUpdate(String tag) { //Richiamato dal thread 

		ClearEnemyObjects();
		checkCollisions();
		if (tag == "Enemy") {
			for (int i = 0; i < lsEnemy.size(); i++) {
				lsEnemy.get(i).scroll();
				if ((lsEnemy.get(i).getX() < -width/3)) {
					lsEnemy.get(i).setAliveEnemy(false);
				}
			}
			// dir serve per sapere se il nemico deve scendere o salire
			for (int i = 0; i < lsEnemy.size(); i++) {
				if ((lsEnemy.get(i).getY() > p.getY()) && (lsEnemy.get(i).getX()) > width / 5)
					// richiamo la funzione align che scrolla in verticale
					lsEnemy.get(i).align(-1);
				else if ((lsEnemy.get(i).getY() < p.getY() && (lsEnemy.get(i).getX()) > width / 5))
					// richiamo la funzione align che scrolla in verticale
					lsEnemy.get(i).align(1);
			}
		}
		if (tag == "Shot") {
			shot_timer++;
			// se lo sparo supera lo schermo scompare e se ne crea un altro pronto ad essere
			for (int i = 0; i < llShot.size(); i++) {
				llShot.get(i).scroll();
				if (llShot.get(i).getX() > width - llShot.get(i).getSpeed()) {
					llShot.get(i).setAliveBullet(false);
				}
			}

		}
	}

<<<<<<< HEAD
	public void setShot(Cell2D Position, int SpeedShot, Cell2D Grafic) {
		Bullet B = p.Shoot(Position, SpeedShot, Grafic);
		if (B != null) {
=======
	public void setShot(Cell2D Position) {
		Bullet B = p.Shoot(Position);
		if (B != null && shot_timer > 30) {
>>>>>>> d516257af9854926d7ba4eb980c16f01cfd18099
			llShot.add(B);
			shot_timer = 0;
		}
	}
	

	public void ClearEnemyObjects() {
		synchronized (lsEnemy) {
			for (int i = 0; i < lsEnemy.size(); i++) {
				if (lsEnemy.get(i).isAliveEnemy() == false) {
					lsEnemy.remove(i);
				}
			}
		}
		synchronized (llShot) {
			for (int i = 0; i < llShot.size(); i++) {
				if (llShot.get(i).isAliveBullet() == false) {
					llShot.remove(i);
				}
			}
		}
	}

	public int getIndex() {
		return index;
	}

	public void checkCollisions() {

		for(int i = 0; i < lsEnemy.size(); i ++) {
			
			Rectangle r3 = lsEnemy.get(i).getBoundsEnemy();

			for (int j = 0; j < llShot.size(); j++) {

				Rectangle r2 = llShot.get(j).getBoundsBullet();
				if (r3.intersects(r2) && (llShot.get(j).getX() < width-llShot.get(j).getSpeed())) {
					System.out.println(r2);
					System.out.println(r3);
					lsEnemy.get(i).setAliveEnemy(false);
					llShot.get(j).setAliveBullet(false);
				}
			}
			
			Rectangle r1 = p.getBoundsPlayer();
				if(r3.intersects(r1)) {
					lsEnemy.get(i).setAliveEnemy(false);
					p.lifes--;
				}
					
		}
	}
     
	public void setDimsPlayer(Cell2D dims) {
		this.dims = dims;
	}

	public Cell2D getDims() {
		return dims;
	}
}
