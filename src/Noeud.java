
public class Noeud{
	/**
	 *	Valeur de la pièce	
	 */
	private int valeur;

	/**
	 *	Numéro de colonne de la pièce
	 */
	private int x;

	/**
	 *	Numéro de ligne de la pièce
	 */
	private int y;


	/**
	 *	Valeur de la fonction d'évaluation
	 *	<p>
	 * 	L'évaluation dépends de l'heuristique choisie
	 *	<p>
	 */
	private int evaluation;


	/**
	 *	Constructeur du Noeud
	 */
	public Noeud(int _valeur,int _x,int _y){
		this.valeur = _valeur;
		this.x = _x;
		this.y = _y;
		this.evaluation = NULL;
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



}