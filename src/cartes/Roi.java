package cartes;

import java.util.ArrayList;
import java.util.Scanner;

import backend.Joueur;
import backend.Partie;

public class Roi implements Carte {

	@Override
	public String getNom() {
		return "Roi";
	}

	@Override
	public int getPoints() {
		return 6;
	}

	@Override
	public String getEffet() {
		return "Échanger sa main avec un autre joueur";
	}

	@Override
	public void appliquerValeur(Partie partie, Joueur moi, Joueur cible) {
		if (cible.estProtege()) {return;}
		ArrayList<Carte> temp = moi.main;
		cible.main = moi.main;
		moi.main = temp;
	}

}
