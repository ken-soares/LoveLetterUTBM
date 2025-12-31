package testing;

import backend.GameWindow;
import backend.Partie;

public class Main {

	public static void main(String[] args) {
		Partie partie = new Partie();
		GameWindow fenetre = new GameWindow(partie);
	}

}
