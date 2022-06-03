package Controllers;

import Models.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

    private Parent root;
    @FXML
    private AnchorPane anchor;
    @FXML
    private Button new_game_btn;
    @FXML
    private AnchorPane anchikour;
    @FXML
    private TextField nametxt;

    @FXML
    private Button new_player_btn;

    @FXML
    private Button old_player_btn;

    @FXML
    private ListView<String> partieListView;

    @FXML
    private ListView<String> playerListView;

    @FXML
    private Label playerext_label;


    public static String username;
    public static boolean new_og;
    public static Jeu jeu=new Jeu();
    public static File file;;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nametxt.setVisible(false);
        if (file.length() == 0) {
            System.out.println("No errors, and file empty");
        }else {
            try {
                ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
                jeu=(Jeu)in.readObject();
                for (Joueur j: jeu.getPlayers()) {
                    for (Partie p:j.getMesParties()) {
                        p.getPlateau().setplt(p.getPlateau().getPltint());
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        MainAppView.jeu=jeu;
        anchikour.setVisible(false);
        new_player_btn.setOnMouseClicked(event -> {
            nametxt.setVisible(true);
            playerext_label.setVisible(false);
            anchikour.setVisible(false);
            playerext_label.setText("player already exist enter another name");
            new_og=true;
        });
        old_player_btn.setOnMouseClicked(event -> {
            new_game_btn.setDisable(true);
            nametxt.setVisible(false);
            anchikour.setVisible(true);
            playerext_label.setVisible(false);
            playerListView.getItems().clear();
            partieListView.getItems().clear();
            if (jeu.getPlayers().size()!=0){
                System.out.println("prrrrr");
                for (Joueur j:jeu.getPlayers()) {
                    playerListView.getItems().add(j.getNom());
                }
            }
            else System.out.println("krrrr");
            playerext_label.setText("player doesn't exist enter press new player");
            new_og=false;
        });
        nametxt.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {

                if (event.getCode()==KeyCode.ENTER){

                    username=nametxt.getText();

                    Joueur joueur = new Joueur(false);
                    joueur.setNom(username);
                    System.out.println(joueur);


                    /**Checking if the player is new or already exists**/
                    if (!jeu.playerExists(joueur)) {// New player , his name is not in the game PlayerList
                        if (new_og){
                            playerext_label.setVisible(false);
                            jeu.addPlayer(joueur);
                            jeu.setMyCurrentPlayer(joueur);
                            jeu.lancerJeu(joueur,false);
                            try {
                                ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                                for (Joueur j: jeu.getPlayers()) {
                                    for (Partie p:j.getMesParties()) {
                                           Plateau plateau=new Plateau();
                                        plateau.setPltint(p.getPlateau().getPltint());
                                        p.setPlateau(plateau);
                                    }
                                }
                                out.writeObject(jeu);
                                System.out.println("The Object  was succesfully written to a file");
                                out.close();

                            }
                            catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                            jeu.lancerJeu(joueur,true);
                            MainAppView.joueur=joueur;
                            System.out.println(joueur);
                            System.out.println(file.length());
                            Stage stage;
                            Parent root;
                            stage = (Stage) nametxt.getScene().getWindow();
                            try {
                                root = FXMLLoader.load(getClass().getResource("/Views/MainAppView.fxml"));
                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            System.out.println(joueur.getNom() + " <NEW_PLAYER>");
                        }
                        else {
                            playerext_label.setVisible(true);
                        }
                    } else {//Un joueur deja existant  , son nom deja dans la liste des joueurs
                        if (new_og){
                            playerext_label.setVisible(true);
                        }
                        else {
                            playerext_label.setVisible(false);
                            joueur = jeu.getPlayerByName(joueur.getNom());
                            jeu.setMyCurrentPlayer(joueur);
                            System.out.println("yoijlnln");
                        }
                        if (joueur == null) System.out.println("The player does exist");
                    }
                }
            }
        });
        new_game_btn.setOnMouseClicked(event -> {
            System.out.println(jeu.getMyCurrentPlayer().getNom());
            jeu.lancerJeu(jeu.getMyCurrentPlayer(),true);
            Stage stage;
            Parent root;
            stage = (Stage) nametxt.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("/Views/MainAppView.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        playerListView.setOnMouseClicked(event -> {
            if (playerListView.getSelectionModel().getSelectedItem()!=null) {
                new_game_btn.setDisable(false);
                jeu.setMyCurrentPlayer(jeu.getPlayerByName(playerListView.getSelectionModel().getSelectedItem()));
                partieListView.getItems().clear();
                System.out.println(jeu.getPlayers());
                System.out.println(jeu.getMyCurrentPlayer());
                System.out.println(jeu.getMyCurrentPlayer().getMesParties());
                if (jeu.getMyCurrentPlayer().getMesParties().size() != 0) {
                    System.out.println("awwwwwwwwwww");
                    for (Partie p : jeu.getMyCurrentPlayer().getMesParties()) {
                        partieListView.getItems().add(p.getIdPartie());
                    }
                }
            }
        });

        /**Displaying the List of Players or the list of games**/

        partieListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount()==2 && partieListView.getSelectionModel().getSelectedItem()!=null) {
                    jeu.getMyCurrentPlayer().setMyCurrentPartie
                            (jeu.getMyCurrentPlayer().getPartieById(partieListView.getSelectionModel().getSelectedItem()));
                    Stage stage;
                    Parent root;
                    stage = (Stage) nametxt.getScene().getWindow();
                    try {
                        root = FXMLLoader.load(getClass().getResource("/Views/MainAppView.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
