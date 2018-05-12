import PaD.*;
import java.awt.*;
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

			Dessinable traiDroit=new Ligne(xd,yd,xd+25,yd+50);
			pad.ajouter(traiDroit);
			dessinerArbre(b.sag(),pad,"g") ;
			// S-A gauche
			dessinerArbre(b.sad(),pad,"d") ;
			// S-A droit
		}
	}

	public static void dessinerArbre2(ArbreBinaire b, PlancheADessin pad, double x, double y){
		 if (!b.estVide() ) {

			Dessinable titre=new Texte(x, y,b.valeur().toString());
			pad.ajouter(titre);
			if(b.sad()!=null){
				dessinerArbre2(b.sad(),pad,x+b.hauteur()*20,y+b.hauteur()*20);
				if(b.sad().sad()!=null){
					Dessinable traiDroit=new Ligne(x+4,y+20,x+b.hauteur()*20,y+b.hauteur()*20);
					pad.ajouter(traiDroit);
				}
			}
			if(b.sag()!=null){
				dessinerArbre2(b.sag(),pad,x-b.hauteur()*20,y+b.hauteur()*20);
				if(b.sag().sag()!=null){
					Dessinable traiDroit=new Ligne(x,y+20,x-b.hauteur()*20,y+b.hauteur()*20);
					pad.ajouter(traiDroit);
				}
			}
		}
	}
	public static boolean recherche(ArbreBinaire<Integer> b, int a, TextArea t){
		if(!b.estVide()){
			if(a<b.valeur()){
				t.appendText(a+"est plus petit que"+b.valeur()+". On continue donc dans le sous arbre gauche\n");
				return recherche(b.sag(),a, t);
			}
			else if(a>b.valeur()){
				t.appendText(a+"est plus grand que"+b.valeur()+". On continue donc dans le sous arbre droit\n");
				return recherche(b.sad(),a, t );
			}
			else{
				t.appendText(a+" est dans l'arbre\n");
				return true;
			}
		}
		else {
			t.appendText(a+" n'est pas dans l'arbre\n");
			return false;
		}
	}
// 	public static ArbreBinaire<Integer> suppress(ArbreBinaire<Integer> b, int a, TextArea t){
// 		if(!b.estVide()){
// 			if(a==b.valeur()){
// 				if(b.estFeuille()){
// 					return new ArbreBinaireChaine();
// 				}
// 				else{
// 					b.valeur()=b.sag().valeur();
// 					b.sag()=new ArbreBinaireChaine();
// 					return b;
// 				}
// 			}
// 			if(a<b.valeur()){
// 			ArbreBinaire<Integer> nouvelArbre=suppress(b.sag(),a,t);
// 			return new ArbreBinaireChaine<Integer>(b.valeur(),nouvelArbre,b.sad());
// 		}
// 			if(a>b.valeur()){
// 			ArbreBinaire<Integer> nouvelArbre=suppress(b.sad(),a,t);
// 			return new ArbreBinaireChaine<Integer>(b.valeur(),b.sag(),nouvelArbre);
// 		}
// 	}
// 	return b;
// }
	public static ArbreBinaire<Integer> ajouter(ArbreBinaire<Integer> b, int a, TextArea t){

		if(b.estVide()){
			t.appendText("L'emplacement est vide, on ajoute "+a+" ici\n");
			return new ArbreBinaireChaine<Integer>(a);

		}
		else{
				if(a<b.valeur()){
					t.appendText(a+"est plus petit que "+b.valeur()+". On continue donc dans le sous arbre gauche\n");
				 	ArbreBinaire<Integer> nouvelArbre=ajouter(b.sag(),a,t);
				 	return new ArbreBinaireChaine<Integer>(b.valeur(),nouvelArbre,b.sad());
				 }
				if(a>b.valeur()){
					t.appendText(a+"est plus grand que "+b.valeur()+". On continue donc dans le sous arbre droit\n");
					ArbreBinaire<Integer> nouvelArbre=ajouter(b.sad(),a,t);
					return new ArbreBinaireChaine<Integer>(b.valeur(),b.sag(),nouvelArbre);
				}

		}
		t.appendText(a+"est déjà présent dans l'abre on ne l'ajoute donc pas");
		return b;
	}


	public static void main(String[] args){
		Frame w = new Frame("Arbre Binaire");
		w.setLayout(new FlowLayout ());
		TextArea t = new TextArea();
		w.add(t);
		w.show();
		w.pack();
		PlancheADessin  pad =new PlancheADessin ();
		ArbreBinaire <Integer> b = new ArbreBinaireChaine<Integer>(10, new ArbreBinaireChaine<Integer>(8,new ArbreBinaireChaine<Integer>(2),new ArbreBinaireChaine<Integer>(9,new ArbreBinaireChaine<Integer>(3),new ArbreBinaireChaine<Integer>(10))),new ArbreBinaireChaine<Integer>(12, new ArbreBinaireChaine<Integer>(4, new ArbreBinaireChaine<Integer>(2),new ArbreBinaireChaine<Integer>(5)),new ArbreBinaireChaine<Integer>(15)));
		dessinerArbre2(ajouter(b,11,t),pad,xd,yd);
		// recherche(b,11,t);
		}
}
