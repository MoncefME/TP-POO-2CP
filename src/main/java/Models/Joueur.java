package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Joueur implements Serializable {
    private int PlayerId = 0;
    private String nom;
    private int bestScore;
    ArrayList<Partie> mesParties = new ArrayList<Partie>();
    private Partie myCurrentPartie ;


    public Joueur(Boolean x) {
        if(x) identifier();
        bestScore = 0;
    }

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

    public int getPlayerId() {
        return PlayerId;
    }
    public void setPlayerId(int playerId) {
        PlayerId = playerId;
    }

    public ArrayList<Partie> getMesParties() {
        return mesParties;
    }

    public void setMesParties(ArrayList<Partie> mesParties) {
        this.mesParties = mesParties;
    }
    public void addPartie(Partie p){
        if (this.getPartieById(p.getIdPartie())!=null){
            int index = mesParties.indexOf(p);
            mesParties.set(index,p);
        }
        else {
            System.out.println("awww");
            p.setIdPartie(nom+"_P_"+mesParties.size());
            mesParties.add(p);

        }
    }
    public  void printPlayers(){
        for(int i=0 ; i<mesParties.size() ; i++){
            System.out.println(mesParties.get(i));
        }
    }//printe the list of games a player had played

    public Partie getMyCurrentPartie() {
        return myCurrentPartie;
    }
    public void setMyCurrentPartie(Partie myCurrentPartie) {
        this.myCurrentPartie = myCurrentPartie;
    }

    public void updatePlayerTopScore(){
        /**Updating the topScore of the player**/
        for(int i=0 ; i<mesParties.size() ; i++){
            bestScore = Math.max(bestScore , mesParties.get(i).getScore());
        }
    }
    public Partie getPartieById(String Id){

        for(int i=0 ; i<mesParties.size() ; i++){
            if (mesParties.get(i).getIdPartie().equals(Id) ){
                return mesParties.get(i);
            }
        }
        return null;
    }
}
