package cartes;

import java.util.Scanner;

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
	public void appliquerValeur(Partie partie, Joueur moi, Joueur autre, Scanner scanner) {
		System.out.println("Entrer nom de la carte du joueur adverse:");
		String input = scanner.nextLine();
		for(Carte el: autre.main) {
			if(el.getNom().equals(input)) {
				System.out.println("Bien deviné");
				autre.eliminer();
				return;
			}
		}
		
		System.out.println("Raté");
	}

}
