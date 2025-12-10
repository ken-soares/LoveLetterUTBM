package cartes;

import java.util.Scanner;

import backend.Joueur;
import backend.Partie;

public interface Carte {
	String getNom();
	int getPoints();
	String getEffet();
	void appliquerValeur(Partie partie, Joueur moi, Joueur autre, Scanner scanner);
}
