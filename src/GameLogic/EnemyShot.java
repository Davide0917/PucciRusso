package GameLogic;

import java.util.Random;

import Component.Cell2D;

public class EnemyShot {
	Cell2D position;

	// Le coordinate iniziali del proiettile dovranno essere le stesse del nemico
	// che l'ha sparato
	public EnemyShot(int x, int y) {
		position = new Cell2D(x, y);
	}

	public int getX() {
		return position.getX();
	}

	public int getY() {
		return position.getY();
	}

	// Il metodo start viene chiamato quando l'aereo del giocatore si trova nel
	// range di tiro dei nemici
	public void start() {

		// La variabile y indica la coordinata y di partenza del proiettile che è la
		// stessa del nemico
		int y = getY();
		int range = 20;
		int x = getX();
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
		for (int i = getX(); i >= 0; i = i - 10) {
			this.position.setX(i);
			cont = cont + 10;
			if (cont >= intervallo) {
				this.position.setY(getY() + (1 * dir));
				cont = 0;
			}
		}

	}

	// funzione che restituisce un intero random compreso fra due estremi
	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randoNum = rand.nextInt((max - min) + 1) + min;
		return randoNum;
	}
}
