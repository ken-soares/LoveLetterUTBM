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
		Joueur cible = null;
		for (Joueur joueur : partie.joueurs) {
			if (joueur.getNom().equals(choixCible)) {
				cible = joueur;
			}
		}
		
		Carte carteAJouer = null;
		for (Carte carte : main) {
			if (carte.getNom().equals(choixCarte)) {
				System.out.println("coucou");
				carteAJouer = carte;
			}
		}
		System.out.println(carteAJouer);
		carteAJouer.appliquerValeur(partie, this, cible);
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
