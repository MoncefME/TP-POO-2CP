package Models;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class Plateau {
    private Case[] plt = new Case[100];

    public Case[] getPlt() {
        return plt;
    }

    public void setPlt(Case[] plt) {
        this.plt = plt;
    }

    /*public Case[] getPlateau() {
        return plt;
    }
    public void setPlateau(Case[] plateau) {
        this.plt = plateau;
    }
    */
    public void showPlateau(){
        for(int i=0 ; i<plt.length ; i++){
            plt[i].hello();
        }
    }

    public Plateau() {
        //generation du tableua aide pour creation des cases du plateau
        int[] arr = Fonctions.randomIntArray();
        //making the plateau
        for(int i=0 ; i<100 ; i++){
            switch (arr[i]){
                case 0:
                    plt[i] = new CaseParcours();
                    break;
                case 1:
                    plt[i] = new CaseDepart();
                    break;
                case 2:
                    plt[i] = new CaseBonus();
                    break;
                case 3:
                    plt[i] = new CaseMalus();
                    break;
                case 4:
                    plt[i] = new CaseSaut();
                    break;
                case 5:
                    plt[i] = new CaseImage();
                    break;
                case 6:
                    plt[i] = new CaseDefinition();
                    break;
                case 9:
                    plt[i] = new CaseFin();
                    break;
            }
        }
    }
}
