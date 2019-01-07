import java.util.ArrayList;
import java.util.Random;

public class Taquin {
	public Integer[][] tab;
	public double g;
	public double h;
	public double f;
	public Taquin parent;
	
	public Taquin(int dim) {
		this.tab = new Integer[dim][dim];
		this.initialiser(dim);
		this.g = 0;
		this.h = 0;
		this.f = 0;
		this.parent = null;
	}
	
	public Taquin(Integer[][] data) {
		this.tab = data;
		this.g = 0;
		this.h= 0;
		this.f = 0;
		this.parent = null;
	}
	
	public Taquin(Taquin t) {
		int dim = t.tab.length;
		this.tab = new Integer[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				this.tab[i][j] = t.tab[i][j];
			}
		}
		this.g = t.g;
		this.h = t.h;
		this.f = t.f;
		this.parent = null;
	}
	
	
	/**
	 * Initialise le taquin (dim x dim) de depart de maniere aleatoire
	 */
	public void initialiser(int dim) {
		Random r = new Random();
		
		// Permet de savoir quels entiers ont deja ete generes
		ArrayList<Integer> l = new ArrayList<Integer>();
		
		int maxInt = dim * dim - 1;
		
		// On genere un entier aleatoire entre 0 et (dim x dim - 1) et on l'affecte pour chacune des cases (1 seule occurence par entier)
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				int x = r.nextInt(maxInt);
				
				// Si x deja distribue, generation d'une nouvelle valeur
				while (l.contains(x)) {
					x = r.nextInt(maxInt);
				}
				
				l.add(x);
				this.tab[i][j] = x;
			}
		}
	}
	
	
	/**
	 * Renvoie la position de la case contenant l'entier valeur (passe en argument)
	 * @return tableau de 2 entiers
	 */
	public int[] getPositionPiece(int valeur) {
		int i = 0, j = 0;
		boolean trouve = false;
		int[] res = new int[2];
		
		while (!trouve && (i < this.tab.length)) {
			j = 0;
			while (!trouve && (j < this.tab.length)) {
				if (this.tab[i][j] == valeur) {
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
	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof Taquin)) return false;
		if (this.getNbPieceMalPlacee((Taquin) o) == 0) return true;
		return false;
	}
	
	
	/**
	 * Genere les Taquin possibles a partir du Taquin this (en fonction de la position de la case vide)
	 * @return liste des Taquin fils du Taquin this
	 */
	public ArrayList<Taquin> calculerFils() {
		ArrayList<Taquin> res = new ArrayList<Taquin>();
		Taquin tmp;
		int pos[] = this.getPositionPiece(0);
		int x = pos[0];
		int y = pos[1];
		int xyMax = this.tab.length - 1;

		// Si la case vide est sur la premiere ligne
		if (x == 0) {
			tmp = new Taquin(this);
			tmp.inverserCases(x, y, x+1, y);
			res.add(tmp);
		} else 
			// Si la case vide est sur la derniere ligne
			if (x == xyMax) {
				tmp = new Taquin(this);
				tmp.inverserCases(x, y, x-1, y);
				res.add(tmp);
			} else{
				// Sinon
				//if (x == 1) {
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
			if (y == xyMax) {
				tmp = new Taquin(this);

				tmp.inverserCases(x, y, x, y-1);
				res.add(tmp);
			} else{
				// Sinon
				//if (y == 1) {
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
	 * Donne le nombre de pieces mal positionnees dans le Taquin this
	 * @return Nombre de pieces pas a leur place
	 */
	public int getNbPieceMalPlacee(Taquin t) {
		int cpt = 0;
		try{		
			for(int i = 0 ; i < this.tab.length ; i++){
				for(int j = 0 ; j < this.tab.length ; j++){
					if (!(this.tab[i][j].equals(t.tab[i][j]))) cpt++;
				}
			}
		}catch(NullPointerException e){
			throw new NullPointerException("Les deux taquins n'ont pas la même dimension");
		}

		return cpt;
	}
	
	/**
	 * Renvoie la somme des distances de Manhattan entre le Taquin this et le Taquin t
	 * @return Somme des distance de Manhattan
	 */
	public int getSommeManhattan(Taquin t) {
		int res = 0;
		
		for (int x = 0; x < this.tab.length; x++) {
			for (int y = 0; y < this.tab.length; y++) {
				int piece = this.tab[x][y];
				int posFin[] = t.getPositionPiece(piece);
				res += Math.abs((posFin[0] - x)) + Math.abs((posFin[1] - y));
			}
		}
		return res;
	}

	/**
	 * Evalue (en fonction de l'heuristique choisie) et renvoie la distance du Taquin courant au Taquin tFin
	 * @param tFin Taquin objectif
	 * @param heuristique Entier representant l'heuristique choisie
	 * @return Entier egal a la distance du Taquin courant au Taquin objectif
	 */
	public int evaluer(Taquin tFin, int heuristique) {
		switch (heuristique) {
		//Valeur = 0
		case 1 :
			return 0;
		//Pieces mal placees
		case 2 : 
			return this.getNbPieceMalPlacee(tFin);
		//Distance Manhattan
		case 3 :
			return this.getSommeManhattan(tFin);
		default :
			return 0;
		}
	}


	/**
	 * Renvoie une chaine de caracteres representant le Taquin courant
	 */
	public String toString(){
		String res = "";
		for(int i = 0 ; i < this.tab.length ; i++){
			for(int j = 0 ; j < this.tab.length ; j++){
				res += " | " + this.tab[i][j];
			}
			res += "\n";
		}
		return res;
	}
	

	/**
	 * Mélange le taquin
	 * @param nbMouv Nombre de déplacements a effectuer
	 */
	public void melange(int nbMouv){
		//Borne du tableau
		int borneMax = this.tab.length-1;
		
		//Position actuelle de la pièce
		int[] posPiece;
		
		//Nombre de déplacement possibles
		int nbTrue;

		//Position des nouvelles pièces après les déplacements
		int[] posPieceGauche = new int[2];
		int[] posPieceDroite = new int[2];
		int[] posPieceHaut = new int[2];
		int[] posPieceBas = new int[2];

		//Dernier déplacement (afin déviter les retour)
		int lastMove = -1;

		//Liste des déplacements possibles (0 : Gauche , 1 : Haut , 2 : Droite , 3 : Bas)
		ArrayList<Integer> possible = new ArrayList<Integer>();

		//Effectue les déplacements
		for(int i = 0 ; i < nbMouv ; i++){
			
			//Initialisation
			possible.clear();
			for (int j = 0 ; j < 4 ; j++ ){
				posPieceGauche[0] = 0;
				posPieceGauche[1] = 0;
				posPieceDroite[0] = 0;
				posPieceDroite[1] = 0;
				posPieceHaut[0] = 0;
				posPieceHaut[1] = 0;
				posPieceBas[0] = 0;
				posPieceBas[1] = 0;
			}


			//Récupération de la position actuelle
			posPiece = this.getPositionPiece(0);

			//Calcul des position des pièces après chaque déplacement probable
			posPieceGauche[0] = posPiece[0];
			posPieceGauche[1] = posPiece[1] - 1;

			posPieceDroite[0] = posPiece[0];
			posPieceDroite[1] = posPiece[1] + 1;

			posPieceHaut[0] = posPiece[0] - 1;
			posPieceHaut[1] = posPiece[1];

			posPieceBas[0] = posPiece[0] + 1;
			posPieceBas[1] = posPiece[1];

			//Vérification de la possibilité des déplacement (vérification que les positions ne sortent pas du tableau)
			if((posPieceGauche[0] <= borneMax)&&(posPieceGauche[1] <= borneMax)&&(posPieceGauche[0] >= 0)&&(posPieceGauche[1] >= 0)){
				if(lastMove!=2)possible.add(0);
			}
			if((posPieceHaut[0] <= borneMax)&&(posPieceHaut[1] <= borneMax)&&(posPieceHaut[0] >= 0)&&(posPieceHaut[1] >= 0)){
				if(lastMove!=3)possible.add(1);
			}
			if((posPieceDroite[0] <= borneMax)&&(posPieceDroite[1] <= borneMax)&&(posPieceDroite[0] >= 0)&&(posPieceDroite[1] >= 0)){
				if(lastMove!=0)possible.add(2);
			}
			if((posPieceBas[0] <= borneMax)&&(posPieceBas[1] <= borneMax)&&(posPieceBas[0] >= 0)&&(posPieceBas[1] >= 0)){
				if(lastMove!=1)possible.add(3);
			}

			//Choix aléatoire du déplacement parmis les déplacement possibles
			nbTrue = possible.size();
			Random r = new Random();
			int choix = r.nextInt(nbTrue);
			lastMove = possible.get(choix);

			switch (possible.get(choix)) {
				case 0 : 
					//Gauche
					this.inverserCases(posPiece[0],posPiece[1],posPieceGauche[0],posPieceGauche[1]);
					break;
				case 1 : 
					//Haut
					this.inverserCases(posPiece[0],posPiece[1],posPieceHaut[0],posPieceHaut[1]);
					break;
				case 2 : 
					//Droite
					this.inverserCases(posPiece[0],posPiece[1],posPieceDroite[0],posPieceDroite[1]);
					break;
				case 3 : 
					//Gauche
					this.inverserCases(posPiece[0],posPiece[1],posPieceBas[0],posPieceBas[1]);
					break;
			}
		}
	}
}
