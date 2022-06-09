package Models;

import java.io.Serializable;
import java.util.ArrayList;

public class Jeu implements Serializable {
    private   int gameTopScore ;
    private ArrayList<Joueur> players = new ArrayList<Joueur>();
    private Joueur myCurrentPlayer;
    public static Dice d1 = new Dice();
    public static Dice d2 = new Dice();
    private Boolean correctClickedCase = false;

    public Boolean getCorrectClickedCase() {
        return correctClickedCase;
    }
    public void setCorrectClickedCase(Boolean correctClickedCase) {
        this.correctClickedCase = correctClickedCase;
    }

    /******************Getters and Setters*************/
    public Joueur getPlayerByName(String name){

        for(int i=0 ; i<players.size() ; i++){
            if (players.get(i).getNom().equals(name) ){
                return players.get(i);
            }
        }
        return null;
    }
    public void addPlayer(Joueur j){
        players.add(j);
    }
    public ArrayList<Joueur> getPlayers() {
        return players;
    }
    public Joueur getMyCurrentPlayer() {
        return myCurrentPlayer;
    }
    public void setMyCurrentPlayer(Joueur myCurrentPlayer) {
        this.myCurrentPlayer = myCurrentPlayer;
    }
    public int getGameTopScore() {
        return gameTopScore;
    }
    public void setGameTopScore(int gameTopScore) {
        this.gameTopScore = gameTopScore;
    }


    /****************Repeated Functions*************************/
    public Plateau getCurrentPlateau(){
        return this.getCurrentPartie().getPlateau();
    }
    public Partie getCurrentPartie(){
        return this.myCurrentPlayer.getMyCurrentPartie();
    }
    public int getCurrentPosition(){
        return this.getCurrentPartie().getPosition();
    }
    public void incrementCurrentPosition(int x){
        this.getCurrentPartie().setPosition(this.getCurrentPosition()+x);
    }
    public int getCurrentScore(){
        return this.getCurrentPartie().getScore();
    }
    public void incrementCurrentScore(int y){
        this.getCurrentPartie().setScore(this.getCurrentScore()+y);
    }
    public Case[] getCurrentPlt(){
        return this.getCurrentPlateau().getPlt();
    }

    public boolean playerExists(Joueur joueur){
        for (int i=0 ; i<players.size() ; i++){
            if(joueur.getNom().equals(players.get(i).getNom())) return true;
        }
        return false;
    }
    public void lancerJeu(Joueur joueur,Boolean b){
        Partie partie = new Partie(b);
//        joueur.addPartie(partie);
        joueur.setMyCurrentPartie(partie);
    }
}
