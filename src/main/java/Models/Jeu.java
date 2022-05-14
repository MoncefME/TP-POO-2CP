package Models;

import java.util.ArrayList;

public class Jeu {
    public static int gameTopScore = 0;
    public ArrayList<Joueur> Players = new ArrayList<>();
    private Partie partie;
    public static Dice d1 = new Dice();
    public static Dice d2 = new Dice();

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public void lancerJeu(){
        Joueur joueur = new Joueur();
        this.partie= new Partie(joueur);
    }
    public void endJeu(){
        System.out.println("Game Over\n");
    }

}
