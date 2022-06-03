package Models;

import java.io.Serializable;

public class Partie implements Serializable {
    private String  idPartie;
    private int score;
    private int position;
    private Plateau plateau;


    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public Plateau getPlateau() {
        return plateau;
    }
    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public String getIdPartie() {
        return idPartie;
    }
    public void setIdPartie(String idPartie) {
        this.idPartie = idPartie;
    }

    public Partie(Boolean b) {
        this.score = 0;// le score de la partie
        this.position = 0;
        this.plateau = new Plateau(b);
        this.idPartie = "";
    }
}
