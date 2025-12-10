package cartes;

import java.util.Scanner;

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
        return "Si vous avez le Roi ou le Prince, vous devez jouer la Comtesse. Aucun effet.";
	}

	@Override
	public void appliquerValeur(Partie partie, Joueur moi, Joueur autre, Scanner scanner) {
		// TODO: gérer dans la méthode de jeu du joueur
		// aucun effet en dehors de ca
	}

}
