
public class Noeud{
	/**
	 *	Valeur de la piece	
	 */
	private int valeur;

	/**
	 *	Numero de colonne de la piece
	 */
	private int x;

	/**
	 *	Numero de ligne de la piece
	 */
	private int y;


	/**
	 *	Valeur de la fonction d'evaluation
	 *	<p>
	 * 	L'evaluation depend de l'heuristique choisie
	 *	<p>
	 */
	private Integer evaluation;


	/**
	 *	Constructeur du Noeud
	 */
	public Noeud(int _valeur,int _x,int _y){
		this.valeur = _valeur;
		this.x = _x;
		this.y = _y;
		this.evaluation = null;
	}

	public void evaluer(int heuristique, int xFin, int yFin,Taquin t){
		switch (heuristique) {
			//Valeur = 0
			case 1 :
				this.evaluation = 0;
				break;
			//Pieces mal placees
			case 2 : 
				this.evaluation = t.getNbPieceMalPlacee();
				break;
			//Distance Manhattan
			case 3 :
				this.evaluation =  (xFin - this.x) + (yFin - this.y);
				break;
		}
	}

	public void setValeur(int _valeur){
		this.valeur = _valeur;
	}
	public int getValeur(){
		return this.valeur;
	}

	public void setX(int _x){
		this.x = _x;
	}
	public int getX(){
		return this.x;
	}

	public void setY(int _y){
		this.y = _y;
	}
	public int getY(){
		return this.y;
	}
	
	public void setEvaluation(int _evaluation){
		this.evaluation = _evaluation;
	}
	public int getEvaluation(){
		return this.evaluation;
	}
	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		
		if (!(o instanceof Noeud)) {
			return false;
		}
		
		Noeud n = (Noeud) o;
		return ((this.valeur == n.valeur) && 
				(this.x == n.x) && (this.y == n.y) && 
				(this.evaluation == n.evaluation));
	}
	
	public String toString(){
		return "[" + this.x + ", " + this.y + "] (" + this.valeur + ") " + this.evaluation;
	}



}