package cartes;

import backend.Joueur;
import backend.Partie;

public class Baron implements Carte {

	@Override
	public String getNom() {
		return "Baron";
	}

	@Override
	public int getPoints() {
		return 3;
	}

	@Override
	public String getEffet() {
		return "le joueur choisit un adversaire. "
				+ "Les deux se montrent leur carte : celui dont "
				+ "la valeur de la carte est la plus faible est "
				+ "éliminé de la manche. Si leurs valeurs sont égales,"
				+ " alors personne n'est éliminé."; 
	}

	@Override
	public void appliquerValeur(Partie partie, Joueur moi, Joueur cible) {
		if (cible.estProtege()) {return;}
		if (moi.main.getFirst().getPoints() > cible.main.getFirst().getPoints()) {
			cible.eliminer();
		}
		else if (moi.main.getFirst().getPoints() < cible.main.getFirst().getPoints()) {
			moi.eliminer();
		}
	}


}
