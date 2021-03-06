import java.util.ArrayList;
import java.util.Random;

public class TaquinN {
	public Noeud[][] tab;

	public TaquinN() {
		this.tab = new Noeud[3][3];
		this.initialiser();
	}

	public TaquinN(Noeud[][] data) {
		this.tab = data;
	}

	public TaquinN(TaquinN t) {
		this.tab = new Noeud[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.tab[i][j] = t.tab[i][j];
			}
		}
	}

	/**
	 * Initialise le TaquinN (3x3) de depart de maniere aleatoire
	 */
	public void initialiser() {
		Random r = new Random();

		// Permet de savoir quels entiers ont deja ete generes
		ArrayList<Integer> l = new ArrayList<Integer>();

		// On genere un entier aleatoire entre 0 et 8 et on l'affecte pour chacune des
		// cases (1 seule occurence par entier)
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int x = r.nextInt(9);

				while (l.contains(x)) {
					x = r.nextInt(9);
				}

				l.add(x);
				this.tab[i][j] = new Noeud(x);
			}
		}
	}

	/**
	 * Renvoie le Noeud representant la case contenant l'entier valeur (passe en
	 * argument)
	 * 
	 * @return Noeud
	 */
	public int[] getPositionNoeud(int valeur) {
		int i = 0, j = 0;
		boolean trouve = false;
		int[] res = new int[2];

		while (!trouve && (i < 3)) {
			j = 0;
			while (!trouve && (j < 3)) {
				if (this.tab[i][j].getValeur() == valeur) {
					trouve = true;
					res[0] = i;
					res[1] = j;
				} else {
					j++;
				}
			}
			i++;
		}
		return res;
	}

	/**
	 * Inverse les cases (i1,j1) et (i2,j2)
	 * 
	 * @param i1
	 *            indice de la ligne de la case 1
	 * @param j1
	 *            indice de la colonne de la case 1
	 * @param i2
	 *            indice de la ligne de la case 2
	 * @param j2
	 *            indice de la colonne de la case 2
	 */
	public void inverserCases(int i1, int j1, int i2, int j2) {
		Noeud tmp = this.tab[i1][j1];
		this.tab[i1][j1] = this.tab[i2][j2];
		this.tab[i2][j2] = tmp;
	}

	/**
	 * Indique si le TaquinN t est identique au TaquinN this
	 * 
	 * @param t
	 *            TaquinN a tester
	 * @return true si le TaquinN t est identique au TaquinN this, false sinon
	 */
	public boolean egaux(TaquinN t) {
		if (this.getNbPieceMalPlacee(t) == 0)
			return true;
		return false;
	}

	/**
	 * Genere les TaquinN possibles a partir du TaquinN this (en fonction de la
	 * position de la case vide)
	 * 
	 * @return liste des TaquinN fils du TaquinN this
	 */
	public ArrayList<TaquinN> calculerFils() {
		ArrayList<TaquinN> res = new ArrayList<TaquinN>();
		TaquinN tmp;
		int pos[] = this.getPositionNoeud(0);
		int x = pos[0];
		int y = pos[1];

		// Si la case vide est sur la premiere ligne
		if (x == 0) {
			tmp = new TaquinN(this);
			tmp.inverserCases(x, y, x + 1, y);
			res.add(tmp);
		} else
		// Si la case vide est sur la derniere ligne
		if (x == 2) {
			tmp = new TaquinN(this);
			tmp.inverserCases(x, y, x - 1, y);
			res.add(tmp);
		} else
		// Sinon
		if (x == 1) {
			tmp = new TaquinN(this);
			tmp.inverserCases(x, y, x - 1, y);
			res.add(tmp);
			tmp = new TaquinN(this);
			tmp.inverserCases(x, y, x + 1, y);
			res.add(tmp);
		}

		// Si la case vide est sur la premiere colonne
		if (y == 0) {
			tmp = new TaquinN(this);
			tmp.inverserCases(x, y, x, y + 1);
			res.add(tmp);
		} else
		// Si la case vide est sur la derniere colonne
		if (y == 2) {
			tmp = new TaquinN(this);
			tmp.inverserCases(x, y, x, y - 1);
			res.add(tmp);
		} else
		// Sinon
		if (y == 1) {
			tmp = new TaquinN(this);
			tmp.inverserCases(x, y, x, y - 1);
			res.add(tmp);
			tmp = new TaquinN(this);
			tmp.inverserCases(x, y, x, y + 1);
			res.add(tmp);
		}

		return res;
	}

	/**
	 * Donne le nombre de pieces mal positionnees dans le TaquinN this
	 * 
	 * @return Nombre de pieces pas a leur place
	 */
	public int getNbPieceMalPlacee(TaquinN t) {
		int cpt = 0;

		if (!(this.tab[0][0].equals(t.tab[0][0])))
			cpt++;
		if (!(this.tab[0][1].equals(t.tab[0][1])))
			cpt++;
		if (!(this.tab[0][2].equals(t.tab[0][2])))
			cpt++;
		if (!(this.tab[1][0].equals(t.tab[1][0])))
			cpt++;
		if (!(this.tab[1][1].equals(t.tab[1][1])))
			cpt++;
		if (!(this.tab[1][2].equals(t.tab[1][2])))
			cpt++;
		if (!(this.tab[2][0].equals(t.tab[2][0])))
			cpt++;
		if (!(this.tab[2][1].equals(t.tab[2][1])))
			cpt++;
		if (!(this.tab[2][2].equals(t.tab[2][2])))
			cpt++;

		return cpt;
	}

	public void evaluer(TaquinN tFin, int heuristique) {
		for (int i = 0; i < this.tab.length; i++) {
			for (Noeud n : this.tab[i]) {
				evaluerNoeud(n, tFin, heuristique);
			}
		}
	}

	public void evaluerNoeud(Noeud n, TaquinN tFin, int heuristique) {
		switch (heuristique) {
		// Valeur = 0
		case 1:
			n.setEvaluation(0);
			break;
		// Pieces mal placees
		case 2:
			n.setEvaluation(this.getNbPieceMalPlacee(tFin));
			break;
		// Distance Manhattan
		case 3:
			int pos[] = this.getPositionNoeud(n.getValeur());
			int posFin[] = tFin.getPositionNoeud(n.getValeur());

			int x = pos[0];
			int y = pos[1];
			int xFin = posFin[0];
			int yFin = posFin[1];
			n.setEvaluation((xFin - x) + (yFin - y));
			break;
		}
	}

	public String toString() {
		return ("| " + this.tab[0][0] + "   " + this.tab[0][1] + "   " + this.tab[0][2] + " |\n" + "| " + this.tab[1][0]
				+ "   " + this.tab[1][1] + "   " + this.tab[1][2] + " |\n" + "| " + this.tab[2][0] + "   "
				+ this.tab[2][1] + "   " + this.tab[2][2]) + " |";
	}

}
