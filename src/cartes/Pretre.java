package cartes;

import java.util.Scanner;

import backend.Joueur;
import backend.Partie;

public class Pretre implements Carte {

	@Override
	public void appliquerValeur(Partie partie, Joueur moi, Joueur autre, Scanner scanner) {
		for(Carte c : autre.main) {
			System.out.println("Main de l'autre joueur: " + c.getNom());
		}
	}

	@Override
	public String getNom() {
		return "Pretre";
	}

	@Override
	public int getPoints() {
		return 2;
	}

	@Override
	public String getEffet() {
		return "Regarder la main d’un autre joueur";
	}

}
