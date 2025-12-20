package cartes;


import backend.Joueur;
import backend.Partie;

public class Comtesse implements Carte {

	@Override
	public String getNom() {
		return "Comtesse";
	}

	@Override
	public int getPoints() {
		return 7;
	}

	@Override
	public String getEffet() {
        return "Si vous avez le Roi ou le Prince, "
        		+ "vous devez jouer la Comtesse. Aucun effet.";
	}

	@Override
	public void appliquerValeur(Partie partie, Joueur moi, Joueur cible) {
		
	}

}
