package Models;

import java.util.ArrayList;
import java.util.Scanner;

public class Joueur {
    private String nom;
    private int bestScore;
    ArrayList<Partie> mesParties = new ArrayList<>();




    public Joueur() {
        identifier();
        bestScore = 0;
    }

    public void identifier(){
        Scanner scanner = new Scanner(System.in);
        nom = scanner.nextLine();
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getBestScore() {
        return bestScore;
    }
    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }
}
