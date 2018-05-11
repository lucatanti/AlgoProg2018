public interface ArbreBinaire <T > {
	public T valeur();
	public ArbreBinaire<T> sag();
	public ArbreBinaire<T> sad();
	public boolean estVide();
	public void ecrireArbre();
	public int hauteur();
	public boolean miroir(ArbreBinaire b);
	public ArbreBinaire genereMiroir();
	public boolean estFeuille();
}