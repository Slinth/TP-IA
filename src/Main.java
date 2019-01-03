import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {
	
	public static ArrayList<Taquin> AStar(Taquin debut, Taquin fin, int heuristique) {
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
		
		// Loop until you find the end
		while (!ouvert.isEmpty() && (!trouve)) {			
			// Get the current node (with the lowest f value)
			Taquin courant = ouvert.poll();
			
			//System.out.println(courant + "\n-----------------");
			
			// Add the current node to closed list
			ferme.add(courant);
						
			// Found the goal
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
			
			// Generate children
			ArrayList<Taquin> lesFils = courant.calculerFils();
			
			// Loop through children
			for (Taquin fils : lesFils) {
				
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
				
				if (!(ferme.contains(fils) && (tmpF >= fils.f))) {
					if (!ouvert.contains(fils)) {
						fils.parent = courant;
						fils.g = tmpG;
						fils.f = tmpF;
						fils.h = tmpH;
						
						ouvert.add(fils);
					}
				}
			}
		}
		
		return chemin;
	}
	
	public static void afficherChemin(ArrayList<Taquin> res) {
		for (int i = res.size() - 1; i >= 0; i--){
			System.out.println(res.get(i) + "\n----------------");
		}
	}
	
	public static void main(String[] args) {
//		Taquin tBase = new Taquin();
//		Taquin tFinal = new Taquin();

		
		Integer data[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
		Integer data2[][] = {{4, 1, 3}, {0, 2, 5}, {7, 8, 6}};
		Taquin t1 = new Taquin(data);
		Taquin t2 = new Taquin(data2);

		System.out.println("Debut : \n"+t1);
		System.out.println("But : \n"+t2+"\n");
		
		ArrayList<Taquin> res = AStar(t1, t2, 2);
				
		System.out.println("\n--------------------\nPATH :");
		afficherChemin(res);
		
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