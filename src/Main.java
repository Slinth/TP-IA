import java.util.ArrayList;

public class Main {
	

	/**
	 * 
	 * @return 1 : Taquin final obtenu
	 * @return 0 : Algorithme termine sans obtention du taquin final
	 */
	public static int algoA(Taquin tBase,Taquin tFinal) {
		System.out.println("\nRésolution en cours ... ");

		int nbDeplacements = 0;

		ArrayList<Taquin> ouvert = new ArrayList<Taquin>();
		ArrayList<Taquin> ferme = new ArrayList<Taquin>();

		ouvert.add(tBase);

		tBase.evaluation = 0;
		System.out.println("Ajout dans Ouvert : \n"+tBase);
		
		while(ouvert.size()!=0){
			System.out.println("-------------------");
			System.out.println("Iter : " + nbDeplacements);
			//Récupération du taquin avyant la plus faible évaluation dans ouvert
			Taquin tMin = null;
			for ( Taquin t : ouvert) {
				if(tMin==null){
					tMin=t;
					System.out.println("tMin\n"+tMin);
				}
				else if((t.evaluation>0)&&(t.evaluation<tMin.evaluation)){
					tMin=t;
					System.out.println("tMin\n"+tMin);
				}
			}
			System.out.println("\n");

			ferme.add(tMin);

			//System.out.println("tMin:\n"+tMin+"\n");


			ArrayList<Taquin> fils = tMin.calculerFils();

			try{
				for (Taquin t : fils ) {
					t.evaluer(tFinal,2);
					t.evaluation += nbDeplacements;
					if(t.equals(tFinal))return 1;
					
					int indexT_ouvert = ouvert.indexOf(t);
					int indexT_ferme = ferme.indexOf(t);

					if((indexT_ouvert==-1)&&(indexT_ferme==-1)){
						ouvert.add(t);
					}
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
					
					//System.out.println(nbDeplacements+"\n"+t);
					//System.out.println("\neval :"+t.evaluation);
				}
				nbDeplacements ++;
				Thread.sleep(2000);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}

		return 0;
	}
	
	public static void main(String[] args) {
		Taquin tBase = new Taquin();
		Taquin tFinal = new Taquin();

		tBase.evaluer(tFinal,2);

		System.out.println("Debut : \n"+tBase);
		System.out.println("But : \n"+tFinal+"\n");
		
		System.out.println(algoA(tBase,tFinal));
	}
}