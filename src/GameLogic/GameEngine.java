package GameLogic;

import java.util.LinkedList;

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
}
