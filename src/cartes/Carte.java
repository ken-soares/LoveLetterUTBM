package cartes;

import backend.Joueur;
import backend.Partie;

public interface Carte {
	String getNom();
	int getPoints();
	String getEffet();
	void appliquerValeur(Partie partie, Joueur moi, Joueur cible);
}
