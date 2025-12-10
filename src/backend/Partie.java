package backend;

import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
	public ArrayList<Joueur> joueurs;
	Paquet paquet;
	Boolean mancheFinie;
	@SuppressWarnings("unused")
	private Scanner scanner;
	
	
	public Partie(Scanner scanner) {
		this.scanner = scanner;
		joueurs = new ArrayList<Joueur>();
		Joueur j1 = new Joueur("Kenneth", scanner);
		Joueur j2 = new Joueur("Matheo", scanner);
		Joueur j3 = new Joueur("Noa", scanner);
		Joueur j4 = new Joueur("Gechter", scanner);
		
		joueurs.addLast(j1);
		joueurs.addLast(j2);
		joueurs.addLast(j3);
		joueurs.addLast(j4);
		
		this.paquet = new Paquet();
	}
	
    public void demarrerManche() {
        System.out.println("=== Nouvelle manche ===");

        // Réinitialiser paquet et mélange
        paquet = new Paquet();
        paquet.melanger();

        // Réinitialiser les mains et défausse des joueurs
        for (Joueur j : joueurs) {
            j.main.clear();
            j.defausse.clear();
        }

        mancheFinie = false;

        for (Joueur j : joueurs) {
            j.main.addLast(paquet.piocher());
        }

        // Commencer les tours
        while (!mancheFinie) {
            for (Joueur j : joueurs) {
                if (!j.estElimine()) {
                    JouerTour(j);
                    verifierFinManche();
                    if (mancheFinie) break;
                }
            }
        }

        determinerVainqueur();
    }
	
	
    public void JouerTour(Joueur joueur) {
        System.out.println("\n--- Tour de " + joueur.getNom() + " ---");

        // Joueur choisit et joue une carte
        joueur.jouer(this, paquet);
    }
	
    public void verifierFinManche() {
        int joueursRestants = 0;

        for (Joueur j : joueurs) {
            if (!j.estElimine()) {
                joueursRestants++;
            }
        }

        // Manche finie si un seul joueur reste ou plus de cartes à piocher
        if (joueursRestants <= 1 || paquet.estVide()) {
            mancheFinie = true;
        }
    }
	
    public void determinerVainqueur() {
        Joueur vainqueur = null;
        int meilleurPoints = -1;

        for (Joueur j : joueurs) {
            if (!j.estElimine()) {
                int points = j.main.getFirst().getPoints();
                if (points > meilleurPoints) {
                    meilleurPoints = points;
                    vainqueur = j;
                }
            }
        }

        if (vainqueur != null) {
            System.out.println("\n=== La manche est terminée ! ===");
            System.out.println("Vainqueur : " + vainqueur.getNom() + " avec " + meilleurPoints + " points !");
        } else {
            System.out.println("Égalité, aucun vainqueur clair !");
        }
    }
}
