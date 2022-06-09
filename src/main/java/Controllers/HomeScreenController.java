package Controllers;

import Models.*;
import com.company.tp.HelloApplication;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable {

    /************FXML elements************/
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
    private ToggleButton toggleMusic;
    @FXML
    private ImageView toggleImg;
    @FXML
    private Label playerext_label;


    public static String username;
    public static boolean new_og;
    public static Jeu jeu=new Jeu();
    public static File file;


    Image musicOn = new Image(getClass().getResourceAsStream("/icons/musicOn.png"));
    Image musicOff = new Image(getClass().getResourceAsStream("/icons/musicOff.png"));
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nametxt.setVisible(false);

        /*******Musique On / off *********/
        toggleMusic.setOnMouseClicked(event -> {
            if(toggleMusic.isSelected()){
                toggleImg.setImage(musicOn);
                HelloApplication.mediaPlayer1.play();
            }else {
                toggleImg.setImage(musicOff);
                HelloApplication.mediaPlayer1.pause();
            }
        });

        /********Manipulation du fichier jeu.json**********/
        if (file.length() == 0) {System.out.println("No errors, and file empty");}
        else {//fichier existe
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
            } catch (IOException e)
            {e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();}
        }//catch errors


        MainAppView.jeu=jeu;
        anchikour.setVisible(false);
        /***************ajouter joueur **************/
        new_player_btn.setOnMouseClicked(event -> {
            nametxt.setVisible(true);
            playerext_label.setVisible(false);
            anchikour.setVisible(false);
            playerext_label.setText("player already exist enter another name");
            new_og=true;
        });

        /****************Joueur existants************/
        old_player_btn.setOnMouseClicked(event -> {
            new_game_btn.setDisable(true);
            nametxt.setVisible(false);
            anchikour.setVisible(true);
            playerext_label.setVisible(false);
            playerListView.getItems().clear();
            partieListView.getItems().clear();
            if (jeu.getPlayers().size()!=0){
                for (Joueur j:jeu.getPlayers()) {
                    playerListView.getItems().add(j.getNom());
                }
            }
            playerext_label.setText("player doesn't exist enter press new player");
            new_og=false;
        });

        /**********cliquant ENTER pour lancer jeu************/
        nametxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

                if (event.getCode()==KeyCode.ENTER){
                    if(nametxt.getText().length() != 0){
                        playerext_label.setText("player already exist enter another name");

                    username=nametxt.getText();

                    Joueur joueur = new Joueur(false);
                    joueur.setNom(username);

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
                        }
                    }
                }else {
                        playerext_label.setText("Please Enter your non-empty name");
                        playerext_label.setVisible(true);
                    }
                }
            }
        });

        /**********New game Button**************************/
        new_game_btn.setOnMouseClicked(event -> {
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

        /*********La liste des joueurs ********************/
        playerListView.setOnMouseClicked(event -> {
            if (playerListView.getSelectionModel().getSelectedItem()!=null) {
                new_game_btn.setDisable(false);
                jeu.setMyCurrentPlayer(jeu.getPlayerByName(playerListView.getSelectionModel().getSelectedItem()));
                partieListView.getItems().clear();

                if (jeu.getMyCurrentPlayer().getMesParties().size() != 0) {
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
