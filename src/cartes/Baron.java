package cartes;

import java.util.Scanner;

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
		return "Comparer les mains"; 
	}

	@Override
	public void appliquerValeur(Partie partie, Joueur moi, Joueur autre, Scanner scanner) {
		for(Carte c : moi.main) {
			System.out.println("Ma main: " + c.getNom());
		}

		for(Carte c : autre.main) {
			System.out.println("Main de l'autre joueur: " + c.getNom());
		}
	}

}
