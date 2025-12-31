package backend;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
	private JPanel DebutJeuPanel;
	
	public GamePanel(Partie partie) {
		this.partie = partie;
		
		cardLayout = new CardLayout();
		ecransPanel = new JPanel(cardLayout);
		setLayout(new BorderLayout());
		add(ecransPanel, BorderLayout.CENTER);
        panels = new HashMap<>();
		
        panels.put(GameState.JOUER, creationDebutJeuPanel());
        panels.put(GameState.JOUER_CARTE, creationTablePanel());
        panels.put(GameState.CHOIX_CIBLE, creationChoixCiblePanel());
        panels.put(GameState.CHOIX_CARTE, creationChoixCartePanel());
        panels.put(GameState.TOUR_FINIE, creationFinTourPanel());        
        
        for (Map.Entry<GameState, JPanel> entry : panels.entrySet()) {
        	ecransPanel.add(entry.getValue(), entry.getKey().name());
        }
        
        
        cardLayout.show(ecransPanel, GameState.JOUER.name());
           
	}
	
	public JPanel creationDebutJeuPanel() {
		JPanel panel = new JPanel(new GridLayout(5,1));
		JButton startButton = new JButton("Jouer");
		startButton.addActionListener(e -> {
			partie.nouvelleManche();
		    updateGamePanel();
		});
		panel.add(startButton);
		for (Joueur joueur : partie.joueurs) {
			JLabel joueurLabel = new JLabel("Point de " + joueur.getNom() +" : "+ joueur.points);
			panel.add(joueurLabel);
		}
		return panel;
	}

	public JPanel creationFinTourPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel defaussesPanel = new JPanel(new GridLayout(2,2));
		JLabel piocheLabel = new JLabel("pioche :"+partie.paquet.taille()+" cartes");
		panel.add(piocheLabel, BorderLayout.EAST);
		JPanel cartesPanel = new JPanel();
		
		for (Joueur joueur : partie.joueurs) {
			JPanel defaussePanel = new JPanel();
			JLabel nomJoueurLabel = new JLabel(joueur.getNom() + " :");
			if (joueur.elimine) {
				nomJoueurLabel.setText(joueur.getNom() + " (éliminé) :");
				nomJoueurLabel.setForeground(Color.RED);
			}
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
		
		if(!partie.getJoueurCourant().estElimine() && 
		   !partie.getJoueurCourant().defausse.isEmpty() && 
		    partie.getJoueurCourant().defausse.getLast().getNom().equals("Pretre")) {

			JDialog overlay = new JDialog(SwingUtilities.getWindowAncestor(panel));
			overlay.setAlwaysOnTop(true);
			overlay.setSize(300, 200);
			overlay.setLocationRelativeTo(panel);

			JPanel content = new JPanel();
			content.setBackground(Color.WHITE);

			content.add(new JLabel("Main de " + partie.getJoueurCourant().cible.getNom() + ": "));
			for (Carte c : partie.getJoueurCourant().cible.main) {
			    content.add(new JLabel(c.getNom()));
			}

			overlay.add(content);
			overlay.setVisible(true);
			partie.getJoueurCourant().cible = null;
		}

		panel.add(FinTourButton, BorderLayout.SOUTH);
		return panel;
	}
	
	public JPanel creationTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel defaussesPanel = new JPanel(new GridLayout(2,2));
		JLabel piocheLabel = new JLabel("pioche :"+partie.paquet.taille()+" cartes");
		panel.add(piocheLabel, BorderLayout.EAST);
		JPanel cartesPanel = new JPanel();
		
		for (Joueur joueur : partie.joueurs) {
			JPanel defaussePanel = new JPanel();
			JLabel nomJoueurLabel = new JLabel(joueur.getNom() + " :");
			if (joueur.elimine) {
				nomJoueurLabel.setText(joueur.getNom() + " (éliminé) :");
				nomJoueurLabel.setForeground(Color.RED);
			}
			defaussePanel.add(nomJoueurLabel);
			for (Carte carte : joueur.defausse) {
				JLabel carteLabel = new JLabel(carte.getNom());
				defaussePanel.add(carteLabel);
			}
			defaussesPanel.add(defaussePanel);
		}
		panel.add(defaussesPanel, BorderLayout.CENTER);
		
		JLabel nomJoueurLabel = new JLabel("Tour de : " + partie.getJoueurCourant().getNom());
		cartesPanel.add(nomJoueurLabel);
		for (Carte carte : partie.getJoueurCourant().main) {
			
			JButton carteButton = new JButton("(" + carte.getPoints() + ") " + carte.getNom());
			carteButton.setToolTipText(carte.getEffet());
			
			carteButton.addActionListener(e -> {
				
				partie.getJoueurCourant().choixCarte = carte.getNom();
			    partie.continuerJouer();
			    updateGamePanel();
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
	    panels.put(GameState.JOUER, creationDebutJeuPanel());
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