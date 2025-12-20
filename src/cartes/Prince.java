package cartes;


import backend.Joueur;
import backend.Partie;

public class Prince implements Carte{

	@Override
	public String getNom() {
		return "Prince";
	}

	@Override
	public int getPoints() {
		return 5;
	}

	@Override
	public String getEffet() {
		return "le joueur choisit un joueur (lui compris)"
				+ " qui défausse sa carte et en pioche une"
				+ " autre immédiatement.";
	}

	@Override
	public void appliquerValeur(Partie partie, Joueur moi, Joueur cible) {
		if (cible.estProtege()) {return;}
		cible.defausse.addLast(cible.main.removeFirst());
		cible.piocher(partie.paquet);
	}
	
}
