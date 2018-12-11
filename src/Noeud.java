
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
	public String toString(){
		return "[" + this.x + " , " + this.y + "]" + this.valeur + " " + this.evaluation;
	}



}