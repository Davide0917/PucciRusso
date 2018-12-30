package GameLogic;

import java.util.LinkedList;
import java.util.Random;

public class GameEngine {
	public Airplane p;
	public MyShot s;
	public Enemy e;
	public LinkedList<Enemy> lsEnemy;

	public GameEngine() {
		p = new Airplane(0, 0, 5, 7);
		e = new Enemy(2000, p.getY(), 1, 2);
		s = new MyShot(0, 100, 5);
		lsEnemy = new LinkedList<>();

		// Non so se le posizioni dei nemici le inizializziamo tutte 0,0 nella lista
		// oppure le carichiamo tutte divere
		for (int i = 0; i < 10; i++)
			lsEnemy.add(new Enemy(2000, 500, 1, 10));
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

	public void FixedUpdate(String tag) {
		if (tag == "Enemy")
			e.scroll();
	}

	// Metodo da sistemaree era solo per capire il procedimento
	public void PlayerFire() {
		if (s.getX() <= 1800) {
			s.scroll();

		} else {
			s.setFire(false);
			s.reset();
		}
		if (((s.getY() >= (e.getY() - 150)) && (s.getY() <= e.getY() + 150)) && (((s.getX() >= (e.getX() - 150)) && (s.getX() <= e.getX() + 150)))) {
			e.setLifes(e.getLifes() - 1);
			s.reset();
			s.setFire(false);
			System.out.println("Colpitoooooo");
		}
	}

}
