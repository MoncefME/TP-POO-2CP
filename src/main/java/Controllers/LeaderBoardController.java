package Controllers;

import Models.Jeu;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LeaderBoardController {
    public static void showLeaderBoard(Jeu jeu){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Game LeaderBoard");
    }

}
