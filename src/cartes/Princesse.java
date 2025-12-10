package cartes;

import java.util.Scanner;

import backend.Joueur;
import backend.Partie;

public class Princesse implements Carte {

	@Override
	public String getNom() {
		return "Princesse";
	}

	@Override
	public int getPoints() {
		return 8;
	}

	@Override
	public String getEffet() {
		return "Perdre si vous la défaussez";
	}

	@Override
	public void appliquerValeur(Partie partie, Joueur moi, Joueur autre, Scanner scanner) {
		moi.eliminer();
	}

}
