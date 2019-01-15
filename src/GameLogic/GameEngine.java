package GameLogic;

import java.util.LinkedList;
import java.util.Random;

public class GameEngine {
	public Airplane p;
	public MyShot s;
	public LinkedList<Enemy> lsEnemy;
	int index;
	public Enemy e;
	// Dal Frame mi prendo le dimensioni dello schermo
	public int width, height;

	public GameEngine(int x, int y) {
		p = new Airplane(0, 0, 3, 10);
		s = new MyShot(100, 100, 20);
		lsEnemy = new LinkedList<>();
		width = x;
		height = y;
		// Carichiamo i nemici nella linkedlist tutti in posizioni casuali vicino alla y
		// dell'areoplano ma non riesco
		// a mappare bene la grandezza del mondo
		for (int i = 0; i < 10; i++)
			lsEnemy.add(new Enemy(width, randInt(p.getY() - 100, p.getY() + 100), 1, 2));
	}

	// Fa nascere i nemici in posizioni random
	public void startEnemy() {

	}

	// Il metodo enemyFire viene chiamato quando l'aereo del giocatore si trova nel
	// range di tiro dei nemici facendo partire l'enemyShot dalle coordinate
	// dell'Enemy
	public void enemyFire(int x, int y) {
		EnemyShot shot = new EnemyShot(x, y, 10);
		int range = 20;
		int intervallo;

		// La coordinata y finale del proiettile nemico sar� un punto random nel range
		// di tiro dello stesso
		int obiettivoY = randInt(y - range, y + range);

		// La variabile dir mi serve per sapere se la coordinata y del proiettile deve
		// aumentare, diminuire o restare invariata
		int dir;
		if (obiettivoY > y)
			dir = 1;
		else if (obiettivoY < y)
			dir = -1;
		else
			dir = 0;

		// La variabile intervallo serve per sapere dopo quante x deve aumentare o
		// diminuire la y per creare traiettoria obliqua
		intervallo = x / obiettivoY;

		int cont = 0;
		shot.scroll();
		cont = cont + 10;
		if (cont >= intervallo) {
			shot.move(dir);
			cont = 0;
		}
	}

	// funzione che restituisce un intero random compreso fra due estremi
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randoNum = rand.nextInt((max - min) + 1) + min;
		return randoNum;
	}

	public void FixedUpdate(String tag) {

		if (tag == "Enemy") {
			lsEnemy.get(index).scroll();

			// dir serve per sapere se il nemico deve scendere o salire
			if (lsEnemy.get(index).getY() > p.getY())
				// richiamo la funzione align che scrolla in verticale
				lsEnemy.get(index).align(-1);
			else
				// richiamo la funzione align che scrolla in verticale
				lsEnemy.get(index).align(1);

		}
		if (lsEnemy.get(index).getX() < 0 - width * 1 / 3 && index < 10) {
			index++;
		}
	}

	public int getIndex() {
		return index;
	}

}
