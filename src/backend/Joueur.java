package backend;

import java.util.ArrayList;
import java.util.Scanner;

import cartes.Carte;

public class Joueur {
	String nom;
	public ArrayList<Carte> main;
	boolean elimine;
	boolean protege;
	ArrayList<Carte> defausse;
	public int points;
	private Scanner scanner;
	
	public Joueur(String nom, Scanner scanner) {
		this.scanner = scanner;
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
	
	
	public void jouer(Partie partie, Paquet paquet) {
		this.protege = false;
	    // On pioche au début du tour
	    main.addLast(paquet.piocher());
	    
	    for(Carte c: main) {
	    	System.out.println("Carte disponible: " + c.getNom());
	    }

	    boolean aComtesse = false;
	    boolean aRoiOuPrince = false;
	    int indexComtesse = -1;

	    // Détecter Comtesse + Roi/Prince
	    for (int i = 0; i < main.size(); i++) {
	        String nom = main.get(i).getNom();

	        if (nom.equals("Comtesse")) {
	            aComtesse = true;
	            indexComtesse = i;
	        }
	        if (nom.equals("Roi") || nom.equals("Prince")) {
	            aRoiOuPrince = true;
	        }
	    }

	    // Obligation de jouer Comtesse
	    if (aComtesse && aRoiOuPrince) {
	        Carte comtesse = main.remove(indexComtesse);
	        defausse.addLast(comtesse);
	        System.out.println(getNom() + " est obligé(e) de jouer la Comtesse !");
	        return;
	    }

	    // Sinon choix normal

	    System.out.println("Choisir une carte à jouer (1-" + main.size() + ")");
	    int choix = scanner.nextInt();

	    if(choix > main.size() || choix < 1)
	    	choix = 1;

	    scanner.nextLine(); // vider le retour ligne

	    // Correction de l'index
	    choix = choix - 1;

	    Carte cj = main.get(choix);

	    // Cartes qui nécessitent une cible
	    if (cj.getPoints() != 8 && cj.getPoints() != 7 && cj.getPoints() != 4) {
	        
	        System.out.println("Choisir un joueur cible non protégé:");
	        String input = scanner.nextLine();

	        Joueur cible = null;
	        for (Joueur j : partie.joueurs) {
	            if (j.getNom().equals(input) && j.estProtege() == false) {
	                cible = j;
	                break;
	            }
	        }

	        if (cible == null) {
	            System.out.println("Cible introuvable, prise du premier joueur par défaut.");
	            for(Joueur j: partie.joueurs) {
	            	if(!j.estProtege()) {
	            		cible = j;
	            		break;
	            	}
	            }
	        }

	        // Appliquer effet
	        cj.appliquerValeur(partie, this, cible, scanner);
	    }

	    // Défausser la carte jouée
	    defausse.addLast(main.remove(choix));
	}

	
	public void eliminer() {
		this.elimine = true;
	}
	
	public boolean estElimine() {
		return this.elimine;
	}
}
