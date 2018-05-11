import PaD.*;
@SuppressWarnings("unchecked")

public class ArbreBinairePaD{
	private static int xd=500;
	private static int yd=10;
	private static int xg=500;
	private static int yg=10;
	public static void dessinerArbre(ArbreBinaire b, PlancheADessin pad, String pos){

		//Object valeur=b.valeur();
		if (!b.estVide() ) {
			if(pos.equals("d")){
				Dessinable titre=new Texte(xd, yd,b.valeur().toString());
				xd=xd+100;
				yd=yd+100;
				pad.ajouter(titre);
			}
			if(pos.equals("g")){
				Dessinable titre=new Texte(xg, yg,b.valeur().toString());
				xg=xg-100;
				yg=yg+100;
				pad.ajouter(titre);
			}
			else{
				Dessinable titre=new Texte(xd, yd,b.valeur().toString());
				xd=xd+100;
				yd=yd+100;
				xg=xg-100;
				yg=yg+100;
				pad.ajouter(titre);
			}
			//Object valeur=b.valeur();

			// Dessinable traiDroit=new Ligne(xd,yd,xd+25,yd+50);
			// pad.ajouter(traiDroit);
			dessinerArbre(b.sag(),pad,"g") ;
			// S-A gauche
			dessinerArbre(b.sad(),pad,"d") ;
			// S-A droit
		}
	}
	public static void main(String[] args){
		PlancheADessin  pad =new PlancheADessin ();
		ArbreBinaire <Integer> b = new ArbreBinaireChaine<Integer>(2, new ArbreBinaireChaine<Integer>(1,new ArbreBinaireChaine<Integer>(4), new ArbreBinaireChaine<Integer>(6)),new ArbreBinaireChaine<Integer>(3));
		if(!b.estVide()){
			dessinerArbre(b, pad,"e");
		}
	}
}
