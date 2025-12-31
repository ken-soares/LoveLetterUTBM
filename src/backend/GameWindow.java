package backend;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class GameWindow extends JFrame {
	
	public GamePanel panel;

    public GameWindow(Partie partie) {
        setTitle("Love Letter");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        panel = new GamePanel(partie);
        setContentPane(panel);

        setVisible(true);
    }
}