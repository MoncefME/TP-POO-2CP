package Controllers;

import Models.Jeu;
import Models.Joueur;
import Models.Partie;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import java.net.URL;
import java.util.ResourceBundle;

public class LeaderBoardController implements Initializable {

    /***********FXML Elements*******************/
    @FXML
    private Button showPlayerBtn;
    @FXML
    private ListView<Joueur> playerListView;
    @FXML
    private Button showPartieList;
    @FXML
    private ListView<Partie> partieListView;
    @FXML
    private Label gameTopScore;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Jeu jeu = MainAppView.game;

        /**Displaying the List of Players or the list of games**/
        showPartieList.setOnMouseClicked(event -> {
            partieListView.setItems(FXCollections.observableArrayList(jeu.getMyCurrentPlayer().getMesParties()));
            System.out.println("*****************************************");
            System.out.println("-monCurrentJoueur : " + jeu.getMyCurrentPlayer().getNom());
            System.out.println("-maCurrentPartie : " + jeu.getMyCurrentPlayer().getMyCurrentPartie().getIdPartie());
            System.out.println("-maCurrentPartieListSize : " + jeu.getMyCurrentPlayer().getMesParties().size());
            System.out.println("*****************************************");
        });

        showPlayerBtn.setOnMouseClicked(event -> {
            playerListView.setItems(FXCollections.observableArrayList(jeu.getPlayers()));
            System.out.println("*****************************************");
            System.out.println("-monCurrentJoueur : " + jeu.getMyCurrentPlayer().getNom());
            System.out.println("-maCurrentPartie : " + jeu.getMyCurrentPlayer().getMyCurrentPartie().getIdPartie());
            System.out.println("-maCurrentPartieListSize : " + jeu.getMyCurrentPlayer().getMesParties().size());
            System.out.println("*****************************************");
        });

        partieListView.setOnMouseClicked(event -> {
            if (jeu.getMyCurrentPlayer().getMyCurrentPartie() != partieListView.getSelectionModel().getSelectedItem()) {
                jeu.getMyCurrentPlayer().setMyCurrentPartie(partieListView.getSelectionModel().getSelectedItem());
            }

            System.out.println("Clicked " + partieListView.getSelectionModel().getSelectedItem());
            System.out.println("Current " + jeu.getMyCurrentPlayer().getMyCurrentPartie());
            //updateData(jeu);
            //partieListView.setDisable(false);
        });
    }
}
