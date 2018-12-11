import java.util.ArrayList;
import java.util.Random;

public class Taquin {
	public Integer[][] tab;
	
	public Taquin() {
		this.tab = new Integer[3][3];
		this.initialiser();
	}
	
	public Taquin(Integer[][] data) {
		this.tab = data;
	}
	
	public Taquin(Taquin t) {
		this.tab = new Integer[3][3];
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
				this.tab[i][j] = x;
			}
		}
	}
	
	
	/**
	 * Renvoie les indices de la case contenant 0 (i.e. case vide) sous forme d'un tableau de 2 entiers
	 * @return
	 */
	public int[] getCaseVide() {
		int i = 0, j = 0;
		boolean trouve = false;
		int[] res = new int[2];
		
		while (!trouve && (i < 3)) {
			j = 0;
			while (!trouve && (j < 3)) {
				if (this.tab[i][j] == 0) {
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
	 * @param i1 indice de la ligne de la case 1
	 * @param j1 indice de la colonne de la case 1
	 * @param i2 indice de la ligne de la case 2
	 * @param j2 indice de la colonne de la case 2
	 */
	public void inverserCases(int i1, int j1, int i2, int j2) {
		int tmp = this.tab[i1][j1];
		this.tab[i1][j1] = this.tab[i2][j2];
		this.tab[i2][j2] = tmp;
	}
	
	
	/**
	 * Indique si le Taquin t est identique au Taquin this
	 * @param t Taquin a tester
	 * @return true si le Taquin t est identique au Taquin this, false sinon
	 */
	public boolean egaux(Taquin t) {
		return ((this.tab[0][0] == t.tab[0][0]) &&
				(this.tab[0][1] == t.tab[0][1]) &&
				(this.tab[0][2] == t.tab[0][2]) &&
				(this.tab[1][0] == t.tab[1][0]) &&
				(this.tab[1][1] == t.tab[1][1]) &&
				(this.tab[1][2] == t.tab[1][2]) &&
				(this.tab[2][0] == t.tab[2][0]) &&
				(this.tab[2][1] == t.tab[2][1]) &&
				(this.tab[2][2] == t.tab[2][2]));
	}
	
	
	public ArrayList<Taquin> calculerFils() {
		ArrayList<Taquin> res = new ArrayList<Taquin>();
		int caseVide[] = this.getCaseVide();
		int x = caseVide[0];
		int y = caseVide[1];
		
		// Si la case vide est sur la premiere ligne
		if (x == 0) {
			
		} // Si la case vide est sur la derniere ligne
		else if (x == 2) {
			
		} // Sinon (cas normal)
		else {
			
		}
		return res;
	}
	
	
	public String toString() {
		return (  "| " + this.tab[0][0] + "   " + this.tab[0][1] + "   "  + this.tab[0][2]  + " |\n"
				+ "| " + this.tab[1][0] + "   " + this.tab[1][1] + "   "  + this.tab[1][2]  + " |\n"
				+ "| " + this.tab[2][0] + "   " + this.tab[2][1] + "   "  + this.tab[2][2]) + " |";
	}
	
}
