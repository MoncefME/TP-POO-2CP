package Models;

public class Partie {
    private int score;
    private int position;
    private Plateau plateau;
    private Joueur joueur;

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

    public Joueur getJoueur() {
        return joueur;
    }
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public Partie(Joueur joueur) {
        this.score = 0;// le score de la partie
        this.plateau = new Plateau();
        this.joueur = joueur;

    }
}
