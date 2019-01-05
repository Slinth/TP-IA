import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {
	/**
	 * Algorihtme a* pour taquin 3x3
	 */
	public static ArrayList<Taquin> AStar(Taquin debut, Taquin fin, int heuristique) {
		int iter =0;
		boolean trouve = false;
		ArrayList<Taquin> chemin = new ArrayList<Taquin>();
		
		// File de priorite triant les taquins par valeur de f croissante
		PriorityQueue<Taquin> ouvert = new PriorityQueue<Taquin>(
        		new Comparator<Taquin>() {
                    @Override
                    public int compare(Taquin t1, Taquin t2) {
                        return Double.compare(t1.f, t2.f);
                    }
        });
		
		// Set de Taquin pour eviter les doublons
		Set<Taquin> ferme = new HashSet<Taquin>();
		
		ouvert.add(debut);
		// Boucle tant qu'on a pas trouve le taquin final et que ouvert n'est pas vide
		while (!ouvert.isEmpty() && (!trouve)) {			
			// Recupere le taquin courant (avec la valeur de f minimale)
			Taquin courant = ouvert.poll();

			//System.out.println("\nDEBUG COURRANT : \n" + courant);
			
			//System.out.println(courant + "\n-----------------");
			
			// Ajoute le taquin courant dans la liste ferme
			ferme.add(courant);
						
			// Taquin final trouve
			if (courant.equals(fin)) {
				System.out.println("TERMINE !");
				trouve = true;
				
				// Backtrack pour trouver le chemin
				while (courant != null) {
					chemin.add(courant);
					courant = courant.parent;
				}
				return chemin;
			}
			// Generation des fils a partir du taquin courant
			ArrayList<Taquin> lesFils = courant.calculerFils();
			// Pour tous les fils generes
			for (Taquin fils : lesFils) {
				iter ++;
				System.out.println(iter);

				
				/* VERSION 1 [MARCHE PAS]
				// Child is on the closedList
				if (ferme.contains(fils)) {
					continue;
				}
				
				// Create the f, g, and h values
				fils.g = courant.g + 1;
				fils.h = fils.evaluer(fin, heuristique);
				fils.f = fils.g + fils.h;
				
				// Child is already in openList
				if (ouvert.contains(fils)) {
					int index = ouvert.indexOf(fils);
					Taquin tmp = ouvert.get(index);
					if (tmp.equals(fils) && (tmp.g < fils.g)) {
						continue;
					}
				}
				
				// Add the child to the open list
				if (!ouvert.contains(fils)) {
					ouvert.add(fils);
				}
				*/
				
				
				
				/* VERSION 2 [MARCHE PEUT ETRE] */
				
				// Calcul des valeurs de g, h et f du prochain taquin potentiel
				double tmpG = courant.g + 1;
				double tmpH = fils.evaluer(fin, heuristique);
                double tmpF = courant.g + tmpH;
                
				/*
				if (ferme.contains(fils) && (tmpF >= fils.f)) {
					continue;
				} else if(!ouvert.contains(fils) || (tmpF < fils.f)) {
					fils.parent = courant;
					fils.g = tmpG;
					fils.f = tmpF;
					fils.h = tmpH;
					
					// Pour eviter les doublons dans la file de priorite
					if (ouvert.contains(fils)) {
						ouvert.remove(fils);
					}
					ouvert.add(fils);
				}*/
				
                // Si fils pas dans la liste ferme et si nouvelle valeur de f superieure a valeur courante
				if (!(ferme.contains(fils) && (tmpF >= fils.f))) {
					// Si fils n'est pas dans la liste ouvert
					if (!ouvert.contains(fils)) {
						// Affectation des nouvelles valeurs
						fils.parent = courant;
						fils.g = tmpG;
						fils.f = tmpF;
						fils.h = tmpH;
						
						// Ajout de fils a ouvert
						ouvert.add(fils);
					}
				}
			}
		}
		
		return chemin;
	}
	
	public static ArrayList<Taquin> UniformCostSearch(Taquin debut, Taquin fin, int heuristique){

		Taquin courant = debut;

		boolean trouve = false;
		boolean fini = false;
		ArrayList<Taquin> visite = new ArrayList<Taquin>();
		ArrayList<Taquin> chemin = new ArrayList<Taquin>();
		chemin.add(debut);
		int fTotal =0;
		//Tq taquin fin n'est pas atteint et qu'il reste des possibilités
		while((!trouve)&&(!fini)){
			//Sélection du fils du courant le plus intéressant
			ArrayList<Taquin> fils = courant.calculerFils();

			// minF : minimum des evaluation / f : evaluation actuelle
			Integer minF = null;
			for ( Taquin tmp : fils ) {
				if(!visite.contains(tmp)){
					int f = tmp.evaluer(fin,heuristique);
					if((minF==null)||(minF > f)){
						minF = f;
						courant = tmp;
					}
				}
			}

			visite.add(courant);

			if(minF == null){
				//Remonte au debut
				courant = debut;

				//Vérification que début a encore des fils exploitable
				ArrayList<Taquin> debFils = courant.calculerFils();
				
				for ( Taquin tmp : visite ) {
					if(debFils.contains(tmp))debFils.remove(tmp);
				}

				//Si plus de fils : fin de l'algo
				if(debFils.size()<1){
					fini = true;
					System.out.println("Toutes les possibilites ont ete exploitees sans trouver de solutions");
					System.out.println(visite.size()+" solutions testees");
				}
			}

			int fC = courant.evaluer(fin,heuristique);
			fTotal += fC;

			chemin.add(courant);


			//Verification de la fin du parcours
			if (courant.equals(fin)) {
				System.out.println("TERMINE !" +fTotal);
				trouve = true;
				return chemin;
			}
		}

		return null;

	}
	/**
	 * Affiche une ArrayList de Taquin representant le chemin d'un taquin a un autre
	 * @param res ArrayList<Taquin>
	 */
	public static void afficherChemin(ArrayList<Taquin> res) {
		for (int i = res.size() - 1; i >= 0; i--){
			System.out.println(res.get(i) + "\n----------------");
		}
	}
	
	public static void main(String[] args) {
//		Taquin tBase = new Taquin();
//		Taquin tFinal = new Taquin();

		//Taquin 3x3
		//Integer data[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
		//Integer data2[][] = {{4, 1, 3}, {0, 2, 5}, {7, 8, 6}};

		//Taquin 4x4
		Integer data[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
		Integer data2[][] = {{2, 6, 10, 14},{1, 5, 9, 13},{3, 7, 11, 15},{0, 12, 8, 4}};
		 
		Taquin t1 = new Taquin(data);
		Taquin t2 = new Taquin(data2);

		//Taquin aléatoire
		//Taquin t2 = new Taquin(4);

		System.out.println("Debut : \n"+t1);
		System.out.println("But : \n"+t2+"\n");
		
		ArrayList<Taquin> res = AStar(t1, t2, 2);
		if(res!=null){		
			System.out.println("\n--------------------\nA*\nPATH :");
			afficherChemin(res);
		}
		
		// res = UniformCostSearch(t1, t2, 2);
		// if(res!=null){		
		// 	System.out.println("\n--------------------\nUniformCostSearch\nPATH :");
		// 	afficherChemin(res);
		// }
		
		/*	TESTS MANUELS
		Taquin tBase = new Taquin();
		Taquin tFinal = new Taquin();

		System.out.println("Debut : \n"+tBase);
		System.out.println("But : \n"+tFinal);
		
		tBase.evaluer(tFinal, 2);
		System.out.println(tBase.evaluation);
		
		System.out.println(tBase.equals(tFinal));
		System.out.println(tBase.equals(tBase));
		
		System.out.println("------------------");
		
		ArrayList<Taquin> l = new ArrayList<Taquin>();
		Integer data[][] = {{5, 8, 4}, {1, 2, 0}, {3, 7, 6}};
		Integer data2[][] = {{6, 8, 7}, {3, 0, 1}, {2, 5, 4}};
		Integer data3[][] = {{1, 6, 3}, {4, 7, 2}, {0, 5, 8}};
		
		Taquin t = new Taquin(data2);

		l.add(new Taquin(data));
		l.add(t);
		l.add(new Taquin(data3));
		
		System.out.println(l.indexOf(t));
		System.out.println(l.get(l.indexOf(t)));
		System.out.println(l.contains(t));
		System.out.println(algoA(tBase,tFinal));
		System.out.println(tBase.getNbPieceMalPlacee(tFinal));
		 */
	}
}