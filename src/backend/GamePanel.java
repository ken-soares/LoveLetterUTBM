package backend;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cartes.Carte;


public class GamePanel extends JPanel {
	
	private Partie partie;
	private JPanel ecransPanel;
	private HashMap<GameState, JPanel> panels;
	private CardLayout cardLayout;
	private JPanel TablePanel;
	private JPanel choixCiblePanel;
	private JPanel choixCartePanel;
	private JPanel FinTourPanel;
	
	public GamePanel(Partie partie) {
		this.partie = partie;
		
		cardLayout = new CardLayout();
		ecransPanel = new JPanel(cardLayout);
		setLayout(new BorderLayout());
		add(ecransPanel, BorderLayout.CENTER);
        panels = new HashMap<>();
		
        panels.put(GameState.JOUER_CARTE, creationTablePanel());
        panels.put(GameState.CHOIX_CIBLE, creationChoixCiblePanel());
        panels.put(GameState.CHOIX_CARTE, creationChoixCartePanel());
        panels.put(GameState.TOUR_FINIE, creationFinTourPanel());
        
        
        for (Map.Entry<GameState, JPanel> entry : panels.entrySet()) {
        	ecransPanel.add(entry.getValue(), entry.getKey().name());
        }
        
        
        cardLayout.show(ecransPanel, GameState.JOUER_CARTE.name());
           
	}
	
	public JPanel creationFinTourPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel defaussesPanel = new JPanel(new GridLayout(2,2));
		JPanel cartesPanel = new JPanel();
		
		for (Joueur joueur : partie.joueurs) {
			JPanel defaussePanel = new JPanel();
			JLabel nomJoueurLabel = new JLabel(joueur.getNom() + " :");
			defaussePanel.add(nomJoueurLabel);
			for (Carte carte : joueur.defausse) {
				JLabel carteLabel = new JLabel(carte.getNom());
				defaussePanel.add(carteLabel);
			}
			defaussesPanel.add(defaussePanel);
		}
		panel.add(defaussesPanel, BorderLayout.CENTER);
		
		JButton FinTourButton = new JButton("Fin de Tour");
		FinTourButton.addActionListener(e -> {
			partie.continuerJouer();
			updateGamePanel(); 
		});
		panel.add(FinTourButton, BorderLayout.SOUTH);
		return panel;
	}
	
	public JPanel creationTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel defaussesPanel = new JPanel(new GridLayout(2,2));
		JPanel cartesPanel = new JPanel();
		
		for (Joueur joueur : partie.joueurs) {
			JPanel defaussePanel = new JPanel();
			JLabel nomJoueurLabel = new JLabel(joueur.getNom() + " :");
			defaussePanel.add(nomJoueurLabel);
			for (Carte carte : joueur.defausse) {
				JLabel carteLabel = new JLabel(carte.getNom());
				defaussePanel.add(carteLabel);
			}
			defaussesPanel.add(defaussePanel);
		}
		panel.add(defaussesPanel, BorderLayout.CENTER);
		
		for (Carte carte : partie.getJoueurCourant().main) {
			
			JButton carteButton = new JButton(carte.getNom());
			
			carteButton.addActionListener(e -> {
				
				partie.getJoueurCourant().choixCarte = carte.getNom();
				System.out.println("Protege42");
			    partie.continuerJouer();
			    System.out.println("Protege1");
			    updateGamePanel();
			    System.out.println("Protege2");
			    
			    
			    
			});
			
			cartesPanel.add(carteButton);
		}
		
		panel.add(cartesPanel, BorderLayout.SOUTH);
		
		return panel;
	}
	
	public JPanel creationChoixCartePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel cartesPanel = new JPanel();
		String[] nomCartes = {"Garde", "Pretre", "Baron", "Servante",
							  "Prince", "Roi", "Comtesse", "Princesse"};
		for (String nomCarte : nomCartes) {
			JButton carteButton = new JButton(nomCarte);
			
			carteButton.addActionListener(e -> {
				
				partie.getJoueurCourant().choixCarteDeviner = nomCarte;
				
			    partie.continuerJouer();
			    updateGamePanel(); 
			    
			    
			});
			
			cartesPanel.add(carteButton);
		}
		
		panel.add(cartesPanel, BorderLayout.WEST);
		
		return panel;
	}
	
	public JPanel creationChoixCiblePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel ciblesPanel = new JPanel();
		
		for (Joueur joueur : partie.joueurs) {
			if (!joueur.elimine) {
				JButton joueurButton = new JButton(joueur.getNom());
				
				joueurButton.addActionListener(e -> {
					
					partie.getJoueurCourant().choixCible = joueur.getNom();			
				    partie.continuerJouer();
				    updateGamePanel();
				    
				});
			ciblesPanel.add(joueurButton);
			}
		}
		
		panel.add(ciblesPanel, BorderLayout.CENTER);
		
		return panel;
	}
	
	public void updateGamePanel() {
	    ecransPanel.removeAll();
	    
	    panels.clear();
	    panels.put(GameState.JOUER_CARTE, creationTablePanel());
	    panels.put(GameState.CHOIX_CIBLE, creationChoixCiblePanel());
	    panels.put(GameState.CHOIX_CARTE, creationChoixCartePanel());
	    panels.put(GameState.TOUR_FINIE, creationFinTourPanel());
	    
	    for (Map.Entry<GameState, JPanel> entry : panels.entrySet()) {
	        ecransPanel.add(entry.getValue(), entry.getKey().name());
	    }
	    
	    cardLayout.show(ecransPanel, partie.getEtatJeu().name());
	    
	    ecransPanel.revalidate();
	    ecransPanel.repaint();
	}
}