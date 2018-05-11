import PaD.*;
@SuppressWarnings("unchecked")

public class ArbreBinaireChaine<T> implements ArbreBinaire<T> {
	private T valeur ;
	private ArbreBinaire<T> sag, sad ;
	// Constructeurs.
	public ArbreBinaireChaine ( T valeur , ArbreBinaire <T > sag ,
		ArbreBinaire <T > sad ) {
		this.valeur = valeur ;
		this.sag = sag ;
		this.sad = sad ;
	}
	public ArbreBinaireChaine ( T valeur ) {
		this.valeur = valeur ;
		this.sag = ArbreBinaireChaine.arbreVide;
		this.sad = ArbreBinaireChaine.arbreVide;
	}
	// Constante représentant l’arbre vide
	public static final ArbreBinaire arbreVide = new ArbreBinaireChaine() ;
		// Constructeur privé pour la constante arbreVide
		private ArbreBinaireChaine () {
		this.valeur = null ;
		this.sag = ArbreBinaireChaine.arbreVide ;
		this.sad = ArbreBinaireChaine.arbreVide ;
	}
	// Fonctions de l’interface.
	public T valeur () {
		return this.valeur ;
	}
	public ArbreBinaire<T> sag() {
		return this.sag;
	}
	public ArbreBinaire<T> sad() {
		return this.sad;
	}
	public boolean estVide() {
		// Puisqu’il n’y a qu’un seul objet arbreVide, on peut tester l’égalité des références directement.
		return this == ArbreBinaireChaine.arbreVide;
	}
	public void ecrireArbre() {
		ecrireArbre( this );
		System.out.print ( "\n" ) ;
	}
	private void ecrireArbre(ArbreBinaire<T> a) {
		// Condition d’arrêt : on n’affiche rien si l’arbre est vide.
		if (!a.estVide() ) {
		// Sinon, on affiche la racine puis les sous-arbres grâce à des appels récursifs.
		System.out.print( "(" ) ;
		System.out.print(a.valeur().toString()) ; // Racine
		ecrireArbre(a.sag()) ;
		// S-A gauche
		ecrireArbre (a.sad()) ;
		// S-A droit
		System.out.print(")");
		}
	}
	public int hauteur(){
		if(this.estVide()) return 0;
		return 1+Math.max(this.sag().hauteur(),this.sad().hauteur());
	}
	public boolean miroir ( ArbreBinaire b ) {
		if ( this.estVide() && b.estVide () ) {
			return true ; // Tous les deux vides -> OK
		} else if ( this.estVide() || b.estVide () ) {
			return false ; // Un des deux vides uniquement -> pas OK
		} else if (! this.valeur().equals(b.valeur() ) ) {
			return false ; // Racines différentes -> pas OK
		} else if (! this.sag().miroir(b.sad() ) ) {
			return false ; // Sous arbres non miroirs -> pas OK
		} else if (! this.sad().miroir(b.sag() ) ) {
			return false ; // Autres sous arbres non miroirs -> pas OK
		}
		// Si on a passé tous les tests, les deux arbres sont miroirs l’un de l’autre.
		return true ;
	}
	public ArbreBinaire genereMiroir(){
		if(estVide()){
			return this;
		}
		return new ArbreBinaireChaine(valeur(),sad().genereMiroir(),sag().genereMiroir());
	}
	public boolean estFeuille(){
		return !this.estVide() && this.sag().estVide() && this.sad().estVide();
	}

}