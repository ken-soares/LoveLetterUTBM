package cartes;

import backend.Joueur;
import backend.Partie;

public class Garde implements Carte {
	

	@Override
	public String getNom() {
		return "Garde";
	}

	@Override
	public int getPoints() {
		return 1;
	}

	@Override
	public String getEffet() {
		return "Deviner la carte d’un autre joueur";
	}

	@Override
	public void appliquerValeur(Partie partie, Joueur moi, Joueur cible) {
		if (cible.estProtege()) {return;}
		if(moi.choixCarteDeviner.equals(cible.main.getFirst().getNom())) {
			System.out.println("Bien deviné");
			cible.eliminer();
		}
		
		System.out.println("Raté");
	}

}
