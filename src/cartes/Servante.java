package cartes;

import backend.Joueur;
import backend.Partie;

public class Servante implements Carte {

	@Override
	public String getNom() {
		return "Servante";
	}

	@Override
	public int getPoints() {
		return 4;
	}

	@Override
	public String getEffet() {
		return "Vous êtes protégé jusqu'à votre prochain tour.";
	}

	@Override
	public void appliquerValeur(Partie partie, Joueur moi, Joueur autre) {
		System.out.println("appliquer valeur");
		moi.setProtege(true);
	}
}
