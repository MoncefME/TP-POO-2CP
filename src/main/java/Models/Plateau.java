package Models;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

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

    public void showPlateu(GridPane myGrid){
        for(int i = 0 ; i<10; i++){
            for (int j=0; j<10 ; j++){
                int k = Fonctions.spiralPattern[i][j];
                VBox v = plt[k].getCaseVbox();
                v.setStyle("-fx-background-color:"+plt[k].getColor()+";"+
                        "-fx-border-color : rgba(0,0,0,1);-fx-vgap: 5;-fx-hgap:5;"+
                        "-fx-border-width: 0;-fx-border-insets: 0, 20;-fx-padding:5;"+
                        Fonctions.caseBorderStyle[Fonctions.spiralBorderPattern[i][j]]+
                        "-fx-border-radius: 3;"
                );
                v.setAlignment(Pos.CENTER);
                //v.setSpacing(5);
                Label l1 = new Label(k+"");
                Label l2 = new Label(plt[k].getBonus()+"$ "+" +"+plt[k].getStep());
                v.getChildren().add(l1);
                myGrid.add(v, j, i);
            }
        }
    }
}
