package Controllers;

import Models.Jeu;
import Models.Joueur;
import Models.Partie;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LeaderBoard {
    public static void showLeaderBoard(Jeu jeu){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Game LeaderBoard");
    }
}
