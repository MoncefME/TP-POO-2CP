package Models;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Plateau implements Serializable {
    private Case[] plt = new Case[100];
    private int[] pltint=new int[100];
    public Case[] getPlt() {
        return plt;
    }

    public void setPlt(Case[] plt) {
        this.plt = plt;
    }

    public int[] getPltint() {
        return pltint;
    }

    public void setPltint(int[] pltint) {
        this.pltint = pltint;
    }
    public Plateau(){

    }
    public Plateau(Boolean b) {
        //generation du tableua aide pour creation des cases du plateau
        int[] arr = Fonctions.randomIntArray();
        //making the plateau
        if (b) {
            this.setplt(arr);
            setPltint(arr);
        }
        else{
            pltint=arr;
        }
    }
    public void setplt(int[] plt_id){
        for (int i = 0; i < 100; i++) {
            switch (plt_id[i]) {
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
                plt[k].setId(k);
                VBox v = plt[k].getCaseVbox();
                v.getChildren().clear();
                v.setStyle("-fx-background-color:"+plt[k].getColor()+";"+
                        "-fx-border-color : rgba(0,0,0,1);-fx-padding:5;"+
                        Fonctions.caseBorderStyle[Fonctions.spiralBorderPattern[i][j]]+
                        "-fx-border-radius: 2;"
                );
                v.setAlignment(Pos.TOP_CENTER);
                Label l1 = new Label(k+"");
                l1.setFont(new Font("Cambria", 10));
                l1.setAlignment(Pos.CENTER_RIGHT);
                v.getChildren().add(l1);
                myGrid.add(v, j, i);
            }
        }
    }
}
