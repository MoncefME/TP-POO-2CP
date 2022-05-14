package Models;

import java.util.ArrayList;
import java.util.Map;

public class Jeu {
    private   int gameTopScore = 0;
    private ArrayList<Joueur> players = new ArrayList<Joueur>();
    private Joueur myCurrentPlayer;
    public static Dice d1 = new Dice();
    public static Dice d2 = new Dice();


    public boolean playerExists(Joueur joueur){
        for (int i=0 ; i<players.size() ; i++){
            if(joueur.getNom().equals(players.get(i).getNom())) return true;
        }
        return false;
    }

    public void lancerJeu(Joueur joueur){
        Partie partie = new Partie();
        joueur.addPartie(partie);
        joueur.setMyCurrentPartie(partie);
    }
    public void endJeu(){
        System.out.println("Game Over\n");
    }

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
    public  void printPlayers(){
        for(int i=0 ; i<players.size() ; i++){
            System.out.println(players.get(i).getNom());
        }
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

    public void updateGameTopScore(){
        /**Updating the topScore of all Time**/
        for(int i=0 ; i<players.size() ; i++){
            gameTopScore = Math.max(gameTopScore , players.get(i).getBestScore());
        }
    }
}
