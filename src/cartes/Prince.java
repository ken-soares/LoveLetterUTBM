package cartes;

import java.util.Scanner;

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
		return "Forcer un joueur à défausser sa carte";
	}

	@Override
	public void appliquerValeur(Partie partie, Joueur moi, Joueur autre, Scanner scanner) {
		System.out.println("Choisir une carte a défausser (1-" + autre.main.size() + ")");
		int input = scanner.nextInt();
		scanner.nextLine();
		if(input < 1 || input > autre.main.size()) {
			System.out.println("Crash: valeur hors de la range des valeurs de la main");
			return;
		}
		autre.main.remove(input - 1);
	}
	
}
