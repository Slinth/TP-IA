
public class Noeud{
	/**
	 *	Valeur de la piece	
	 */
	private int valeur;

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
	public Noeud(int _valeur){
		this.valeur = _valeur;
		this.evaluation = null;
	}
	/*
	public void evaluer(int heuristique,Taquin tDeb,Taquin tFin){
		
		//Récupération des coordonnées de destination de la pièce
		int xFin = tFin.getNoeud(this.valeur).getX();
		int yFin = tFin.getNoeud(this.valeur).getY();

		switch (heuristique) {
			//Valeur = 0
			case 1 :
				this.evaluation = 0;
				break;
			//Pieces mal placees
			case 2 : 
				this.evaluation = tDeb.getNbPieceMalPlacee(tFin);
				break;
			//Distance Manhattan
			case 3 :
				this.evaluation =  (xFin - this.x) + (yFin - this.y);
				break;
		}
	}
	*/

	public void setValeur(int _valeur){
		this.valeur = _valeur;
	}
	public int getValeur(){
		return this.valeur;
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
				(this.evaluation == n.evaluation));
	}
	
	public String toString(){
		return " "+this.valeur;
	}



}