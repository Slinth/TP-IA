import java.util.ArrayList;
import java.util.Random;

public class Taquin {
	public Noeud[][] tab;
	
	public Taquin() {
		this.tab = new Noeud[3][3];
		this.initialiser();
	}
	
	public Taquin(Noeud[][] data) {
		this.tab = data;
	}
	
	public Taquin(Taquin t) {
		this.tab = new Noeud[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.tab[i][j] = t.tab[i][j];
			}
		}
	}
	
	
	/**
	 * Initialise le taquin (3x3) de depart de maniere aleatoire
	 */
	public void initialiser() {
		Random r = new Random();
		
		// Permet de savoir quels entiers ont deja ete generes
		ArrayList<Integer> l = new ArrayList<Integer>();
		
		
		// On genere un entier aleatoire entre 0 et 8 et on l'affecte pour chacune des cases (1 seule occurence par entier)
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int x = r.nextInt(9);
				
				while (l.contains(x)) {
					x = r.nextInt(9);
				}
				
				l.add(x);
				this.tab[i][j] = new Noeud(x, i, j);
			}
		}
	}
	
	
	/**
	 * Renvoie le Noeud representant la case contenant 0 (i.e. case vide)
	 * @return Noeud vide
	 */
	public Noeud getCaseVide() {
		int i = 0, j = 0;
		boolean trouve = false;
		Noeud res = new Noeud(0, -1, -1);
		
		while (!trouve && (i < 3)) {
			j = 0;
			while (!trouve && (j < 3)) {
				if (this.tab[i][j].getValeur() == 0) {
					trouve = true;
					res.setX(i);
					res.setY(j);
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
	 * @param i1 indice de la ligne de la case 1
	 * @param j1 indice de la colonne de la case 1
	 * @param i2 indice de la ligne de la case 2
	 * @param j2 indice de la colonne de la case 2
	 */
	public void inverserCases(int i1, int j1, int i2, int j2) {
		Noeud tmp = this.tab[i1][j1];
		this.tab[i1][j1] = this.tab[i2][j2];
		this.tab[i2][j2] = tmp;
	}
	
	
	/**
	 * Indique si le Taquin t est identique au Taquin this
	 * @param t Taquin a tester
	 * @return true si le Taquin t est identique au Taquin this, false sinon
	 */
	public boolean egaux(Taquin t) {
		return ((this.tab[0][0].equals(t.tab[0][0])) &&
				(this.tab[0][1].equals(t.tab[0][1])) &&
				(this.tab[0][2].equals(t.tab[0][2])) &&
				(this.tab[1][0].equals(t.tab[1][0])) &&
				(this.tab[1][1].equals(t.tab[1][1])) &&
				(this.tab[1][2].equals(t.tab[1][2])) &&
				(this.tab[2][0].equals(t.tab[2][0])) &&
				(this.tab[2][1].equals(t.tab[2][1])) &&
				(this.tab[2][2].equals(t.tab[2][2])));
	}
	
	
	/**
	 * Genere les Taquin possibles a partir du Taquin this (en fonction de la position de la case vide)
	 * @return liste des Taquin fils du Taquin this
	 */
	public ArrayList<Taquin> calculerFils() {
		ArrayList<Taquin> res = new ArrayList<Taquin>();
		Taquin tmp;
		Noeud vide = this.getCaseVide();
		int x = vide.getX();
		int y = vide.getY();
		
		// Si la case vide est sur la premiere ligne
		if (x == 0) {
			tmp = new Taquin(this);
			tmp.inverserCases(x, y, x+1, y);
			res.add(tmp);
		} else 
			// Si la case vide est sur la derniere ligne
			if (x == 2) {
				tmp = new Taquin(this);
				tmp.inverserCases(x, y, x-1, y);
				res.add(tmp);
			} else
				// Sinon
				if (x == 1) {
					tmp = new Taquin(this);
					tmp.inverserCases(x, y, x-1, y);
					res.add(tmp);
					tmp = new Taquin(this);
					tmp.inverserCases(x, y, x+1, y);
					res.add(tmp);
				}
			
		// Si la case vide est sur la premiere colonne
		if (y == 0) {
			tmp = new Taquin(this);
			tmp.inverserCases(x, y, x, y+1);
			res.add(tmp);
		} else
			// Si la case vide est sur la derniere colonne
			if (y == 2) {
				tmp = new Taquin(this);
				tmp.inverserCases(x, y, x, y-1);
				res.add(tmp);
			} else
				// Sinon
				if (y == 1) {
					tmp = new Taquin(this);
					tmp.inverserCases(x, y, x, y-1);
					res.add(tmp);
					tmp = new Taquin(this);
					tmp.inverserCases(x, y, x, y+1);
					res.add(tmp);
				}
		
		return res;
	}
	
	
	/**
	 * Indique si le Taquin this est gagnant
	 * @return TRUE si this est gagnant, FALSE sinon
	 */
	public boolean estGagnant() {
		return (this.tab[0][0].getValeur() == 1 &&
				this.tab[0][1].getValeur() == 2 &&
				this.tab[0][2].getValeur() == 3 &&
				this.tab[1][0].getValeur() == 4 &&
				this.tab[1][1].getValeur() == 5 &&
				this.tab[1][2].getValeur() == 6 &&
				this.tab[2][0].getValeur() == 7 &&
				this.tab[2][1].getValeur() == 8 &&
				this.tab[2][2].getValeur() == 0);
	}
	
	public String toString() {
		return (  "| " + this.tab[0][0] + "   " + this.tab[0][1] + "   "  + this.tab[0][2]  + " |\n"
				+ "| " + this.tab[1][0] + "   " + this.tab[1][1] + "   "  + this.tab[1][2]  + " |\n"
				+ "| " + this.tab[2][0] + "   " + this.tab[2][1] + "   "  + this.tab[2][2]) + " |";
	}
	
}
