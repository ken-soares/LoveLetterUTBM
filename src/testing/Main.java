package testing;

import java.util.Scanner;

import backend.Partie;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Partie partie = new Partie(scanner);
		partie.demarrerManche();
	}

}
