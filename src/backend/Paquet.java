package backend;

import java.util.ArrayList;
import java.util.Collections;

import cartes.Carte;
import cartes.Pretre;
import cartes.Baron;
import cartes.Princesse;
import cartes.Prince;
import cartes.Roi;
import cartes.Garde;
import cartes.Comtesse;
import cartes.Servante;

public class Paquet {

    private final ArrayList<Carte> cartes;

    public Paquet() {
        cartes = new ArrayList<>();

        // Ajout des 5 Gardes (valeur 1)
        for (int i = 0; i < 5; i++) {
            cartes.add(new Garde());
        }

        // Ajout des 2 Prêtres (valeur 2)
        for (int i = 0; i < 2; i++) {
            cartes.add(new Pretre());
        }

        // Ajout des 2 Barons (valeur 3)
        for (int i = 0; i < 2; i++) {
            cartes.add(new Baron());
        }

        // Ajout des 2 Servantes (valeur 4)
        for (int i = 0; i < 2; i++) {
            cartes.add(new Servante());
        }

        // Ajout des 2 Princes (valeur 5)
        for (int i = 0; i < 2; i++) {
            cartes.add(new Prince());
        }

        // Roi (valeur 6)
        cartes.add(new Roi());

        // Comtesse (valeur 7)
        cartes.add(new Comtesse());

        // Princesse (valeur 8)
        cartes.add(new Princesse());
    }

    /** Mélange le paquet */
    public void melanger() {
        Collections.shuffle(cartes);
    }

    /** Pioche la première carte disponible */
    public Carte piocher() {
        if (cartes.isEmpty()) {
            return null;
        }
        return cartes.remove(0);
    }

    public int taille() {
        return cartes.size();
    }

    public boolean estVide() {
        return cartes.isEmpty();
    }
}
