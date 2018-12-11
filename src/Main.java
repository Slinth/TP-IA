import java.util.ArrayList;

public class Main {
	
	public static int algoA(Taquin tBase,Taquin tFinal) {
		System.out.println("\nRésolution en cours ... ");

		int nbDeplacements = 0;

		ArrayList<Taquin> ouvert = new ArrayList<Taquin>();
		ArrayList<Taquin> ferme = new ArrayList<Taquin>();

		ouvert.add(tBase);
		tBase.evaluation = 0;

		
		while(ouvert.size()!=0){
			
			//Récupération du taquin avyant la plus faible évaluation dans ouvert
			Taquin tMin = null;
			for ( Taquin t : ouvert) {
				if((tMin==null)||(t.evaluation<tMin.evaluation))
					tMin=t;
			}


			ArrayList<Taquin> fils = tMin.calculerFils();

			for (Taquin t : fils ) {
				
				if(t.egaux(tBase))return 1;
				
				int indexT_ouvert = ouvert.indexOf(t);
				int indexT_ferme = ferme.indexOf(t);

				if((indexT_ouvert==-1)&&(indexT_ferme==-1))ouvert.add(t);
				else if(indexT_ouvert!=-1){
					Taquin k = ouvert.get(indexT_ouvert);
					if(k.evaluation<t.evaluation){
						ouvert.add(t);
						ouvert.remove(k);
					}
				}else if(indexT_ferme!=-1){
					Taquin k = ferme.get(indexT_ferme);
					ferme.remove(k);
					ouvert.add(t);
				}
			}

		}

		return 0;
	}
	
	public static void main(String[] args) {
		Taquin tBase = new Taquin();
		Taquin tFinal = new Taquin();

		System.out.println("Debut : \n"+tBase);
		System.out.println("But : \n"+tFinal);
		
		System.out.println(algoA(tBase,tFinal));
	}
}