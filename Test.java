@SuppressWarnings("unchecked")
public class Test{
	public static void main(String[] args){
		ArbreBinaire <String> b = new ArbreBinaireChaine<String>("z", new ArbreBinaireChaine<String>("y"),new ArbreBinaireChaine("a"));
		if(!b.estVide()){
			ecrireArbre(b);
		}
	}
}