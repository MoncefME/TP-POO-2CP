package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Joueur implements Serializable {

    private String nom;
    private int bestScore;
    ArrayList<Partie> mesParties = new ArrayList<>();
    private Partie myCurrentPartie ;

    public Joueur(Boolean x) {
        if(x) identifier();
        bestScore = 0;
    }

    /*** Fonctions ***/
    public void identifier(){
        System.out.println("Enter you name: ");
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
    public ArrayList<Partie> getMesParties() {
        return mesParties;
    }
    public void addPartie(Partie p){
        if (this.getPartieById(p.getIdPartie())!=null){
            int index = mesParties.indexOf(p);
            mesParties.set(index,p);
        }
        else {
            p.setIdPartie(nom+"_P_"+mesParties.size());
            mesParties.add(p);

        }
    }
    public Partie getMyCurrentPartie() {
        return myCurrentPartie;
    }
    public void setMyCurrentPartie(Partie myCurrentPartie) {
        this.myCurrentPartie = myCurrentPartie;
    }
    public Partie getPartieById(String Id){
        for (Partie mesParty : mesParties) {
            if (mesParty.getIdPartie().equals(Id)) {
                return mesParty;
            }
        }
        return null;
    }
}
