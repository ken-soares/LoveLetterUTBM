package backend;

import java.util.ArrayList;
import java.util.Scanner;

import cartes.Carte;

public class Joueur {
	String nom;
	public ArrayList<Carte> main;
	boolean elimine;
	boolean protege;
	public ArrayList<Carte> defausse;
	public int points;
	
	public String choixCible;
	public String choixCarte;
	public String choixCarteDeviner;

	public Joueur cible = null;

	public Joueur(String nom) {
		main = new ArrayList<Carte>();
		defausse = new ArrayList<Carte>();
		this.nom = nom;
	}
	
	public String getNom() {
		return nom;
	}
	
	
	public boolean estProtege() {
		return this.protege;
	}
	
	public void setProtege(boolean val) {
		this.protege = val;
	}
	
	public void jouerCarte(Partie partie) {
		//TODO verifier le fonctionnement
		for (Joueur joueur : partie.joueurs) {
			if (joueur.getNom().equals(choixCible)) {
				cible = joueur;
			}
		}
		
		Carte carteAJouer = null;
	    int indexCarte = -1;
	    
	    for (int i = 0; i < main.size(); i++) {
	        Carte carte = main.get(i);
	        if (carte.getNom().equals(choixCarte)) {
	            carteAJouer = carte;
	            indexCarte = i;
	            break;
	        }
	    }
	    
	    if (carteAJouer == null) {
	        System.out.println("Erreur: Carte non trouvée dans la main!");
	        return;
	    }
	    
	    // Retirer la carte de la main et l'ajouter à la défausse
	    main.remove(indexCarte);
	    defausse.add(carteAJouer);
	    
	    // Appliquer l'effet de la carte
	    carteAJouer.appliquerValeur(partie, this, cible);
	    
	    // Réinitialiser les choix
	    choixCible = null;
	    choixCarte = null;
	    choixCarteDeviner = null;
	}
	
	public void eliminer() {
		if (main.size() == 1) {
			defausse.addLast(main.removeFirst());
		}
		this.elimine = true;
	}
	
	public boolean estElimine() {
		return this.elimine;
	}
	
	public void piocher(Paquet paquet) {
		this.main.addLast(paquet.piocher());
	}
}
