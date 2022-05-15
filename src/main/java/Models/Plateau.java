package Models;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import java.io.Serializable;


public class Plateau implements Serializable {
    private Case[] plt = new Case[100];
    private int[] pltint=new int[100];
    public Case[] getPlt() {
        return plt;
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
                case 0 -> plt[i] = new CaseParcours();
                case 1 -> plt[i] = new CaseDepart();
                case 2 -> plt[i] = new CaseBonus();
                case 3 -> plt[i] = new CaseMalus();
                case 4 -> plt[i] = new CaseSaut();
                case 5 -> plt[i] = new CaseImage();
                case 6 -> plt[i] = new CaseDefinition();
                case 9 -> plt[i] = new CaseFin();
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
                        "-fx-border-color :rgba(61, 23, 53, 1);-fx-padding:0 5 0 5 ;"+
                        Fonctions.caseBorderStyle[Fonctions.spiralBorderPattern[i][j]]+
                        "-fx-border-radius: 1;"
                );
                v.setAlignment(Pos.TOP_CENTER);
                Label l1 = new Label(k+"");
                l1.setFont(new Font("Cambria", 10));
                l1.setAlignment(Pos.TOP_CENTER);
                v.getChildren().add(l1);
                myGrid.add(v, j, i);
            }
        }
    }
}
