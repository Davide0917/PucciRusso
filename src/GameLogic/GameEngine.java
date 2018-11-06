package GameLogic;

import java.util.LinkedList;
import java.util.Random;

public class GameEngine {
	Airplane p;
	LinkedList<Enemy> lsEnemy;

	public GameEngine() {
		p = new Airplane(-100, 100, 3, 7);
		lsEnemy = new LinkedList<>();

		// Non so se le posizioni dei nemici le inizializziamo tutte 0,0 nella lista
		// oppure le carichiamo tutte divere
		for (int i = 0; i < 10; i++)
			lsEnemy.add(new Enemy(0, 0, 1));
	}

	// Il metodo start viene chiamato quando l'aereo del giocatore si trova nel
	// range di tiro dei nemici facendo partire l'enemyShot dalle coordinate
	// dell'Enemy
	public void fireEnemy(int x, int y) {
		EnemyShot shot = new EnemyShot(x, y);
		int range = 20;
		int intervallo;

		// La coordinata y finale del proiettile nemico sarà un punto random nel range
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
}
