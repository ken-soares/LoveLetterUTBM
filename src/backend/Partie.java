package backend;

import java.util.ArrayList;
import java.util.Scanner;

import cartes.Carte;
import cartes.Comtesse;
import cartes.Prince;
import cartes.Roi;

public class Partie {
	public ArrayList<Joueur> joueurs;
	public int indexCourant;
	public Paquet paquet;
	public GameState gameState;
	@SuppressWarnings("unused")
	
	public Partie() {
		joueurs = new ArrayList<Joueur>();
		Joueur j1 = new Joueur("Kenneth");
		Joueur j2 = new Joueur("Matheo");
		Joueur j3 = new Joueur("Noa");
		Joueur j4 = new Joueur("Gechter");
		
		joueurs.addLast(j1);
		joueurs.addLast(j2);
		joueurs.addLast(j3);
		joueurs.addLast(j4);
		
		indexCourant = 0;
		
		this.paquet = new Paquet();
		j2.elimine = true;
		this.setEtatJeu(GameState.JOUER_CARTE);
		nouvelleManche();
	}
	
	public void continuerJouer() {
		
		switch (gameState) {
			case GameState.JOUER_CARTE: {
				
				if (getJoueurCourant().choixCarte.equals("Prince")
						|| getJoueurCourant().choixCarte.equals("Roi")){
					for (Carte carte : getJoueurCourant().main) {
				        if (carte instanceof Comtesse) {
				        	System.out.println("Impossible de jouer cette carte,"
					    			+ " vous devez jouer la comtesse");
				        }
				        return; //retour anticipe, impossible de jouer 
				        		//une autre carte que la comtesse
					}
					
				}
				
				System.out.println(getJoueurCourant().nom + " joue "
						+ getJoueurCourant().choixCarte);
				
				if (getJoueurCourant().choixCarte.equals("Garde")
						|| getJoueurCourant().choixCarte.equals("Pretre")
						|| getJoueurCourant().choixCarte.equals("Baron")
						|| getJoueurCourant().choixCarte.equals("Prince")
						|| getJoueurCourant().choixCarte.equals("Roi")
						) {
					setEtatJeu(GameState.CHOIX_CIBLE);
				} else {
					getJoueurCourant().jouerCarte(this);
					setEtatJeu(GameState.TOUR_FINIE);
				}
			
			}
				
			case GameState.CHOIX_CIBLE: {
				for (Joueur joueur : joueurs) {
					if (joueur.getNom().equals(getJoueurCourant().choixCible)
							&& !joueur.getNom().equals(getJoueurCourant().getNom())) {
						if (!joueur.estProtege()) {
							System.out.println("Ce joueur est protégé, il ne se passe rien");
							getJoueurCourant().jouerCarte(this);
							setEtatJeu(GameState.TOUR_FINIE);
						} else {
							if (getJoueurCourant().choixCarte.equals("Garde")) {
								setEtatJeu(GameState.CHOIX_CARTE);
							} else {
								getJoueurCourant().jouerCarte(this);
								setEtatJeu(GameState.TOUR_FINIE);
							}
						}
						
					}
				}
			}
			
			case GameState.CHOIX_CARTE:
				getJoueurCourant().jouerCarte(this);
				setEtatJeu(GameState.TOUR_FINIE);
			
			case GameState.TOUR_FINIE:
				setEtatJeu(GameState.JOUER_CARTE);
				joueurSuivant();
				
			case GameState.JOUER:
				
		}
		
		
	}
	
	/*
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
            j.piocher(paquet);
        }

        // Commencer les tours
        joueurCourant = joueurs.getFirst();
        if (mancheFinie) return; //TODO fin de jeu
        
        
        
        
        
        while (!mancheFinie) {
            for (Joueur j : joueurs) {
                if (!j.estElimine()) {
                	this.setJoueurCourant(j);
                    JouerTour(j);
                    verifierFinManche();
                    if (mancheFinie) break;
                }
            }
        }

        determinerVainqueur();
    }
	*/
	
	public void nouvelleManche() {
		System.out.println("=== Nouvelle manche ===");

        // Réinitialiser paquet et mélange
        paquet = new Paquet();
        paquet.melanger();
        
        // Réinitialiser les mains et défausse des joueurs
        for (Joueur j : joueurs) {
            j.main.clear();
            j.defausse.clear();
        }

        for (Joueur j : joueurs) {
            j.piocher(paquet);
        }
        
        getJoueurCourant().piocher(paquet);
        setEtatJeu(GameState.JOUER_CARTE);

	}
	
    public void JouerTour(Joueur joueur) {
        joueur.jouerCarte(this);
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
            gameState = GameState.JOUER;
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
    
    public Joueur getJoueurCourant() {
    	return joueurs.get(indexCourant);
    }
    
    public GameState getEtatJeu() {
    	return this.gameState;
    }
    
    public void setEtatJeu(GameState gameState) {
    	this.gameState = gameState;
    }
    
    // definie le prochain joueur à jouer comme joueurCourant
    public void joueurSuivant() {
        indexCourant = (indexCourant + 1) % joueurs.size();
        getJoueurCourant().piocher(paquet);
        getJoueurCourant().setProtege(false);
        
    }
}
