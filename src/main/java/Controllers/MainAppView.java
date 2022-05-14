package Controllers;

import Models.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.lang.invoke.CallSite;
import java.net.URL;
import java.time.temporal.JulianFields;
import java.util.ResourceBundle;

public class MainAppView implements Initializable {

    @FXML
    private GridPane myGrid;
    @FXML
    private Button newGameBtn;
    @FXML
    private Button rollBtn;
    @FXML
    private Button newUserBtn;

    @FXML
    private Label dice1Label ;
    @FXML
    private ImageView dice1Image;
    @FXML
    private Label dice2Label ;
    @FXML
    private ImageView dice2Image;

    @FXML
    private Label userName;
    @FXML
    private Label currentScore;
    @FXML
    private Label currentPosition;
    @FXML
    private Label gameTopScore;
    @FXML
    private Label userTopScore;
    @FXML
    private Label clickLabel;

    @FXML
    private Button showPlayerBtn;
    @FXML
    private ListView<Joueur> playerListView;
    @FXML
    private Button showPartieList;
    @FXML
    private ListView<Partie> partieListView;


    public void updateData(Jeu jeu){
        userName.setText(jeu.getMyCurrentPlayer().getNom());
        gameTopScore.setText(String.valueOf(jeu.getGameTopScore()));
        userTopScore.setText(String.valueOf(jeu.getMyCurrentPlayer().getBestScore()));
        currentScore.setText(String.valueOf(jeu.getMyCurrentPlayer().getMyCurrentPartie().getScore()));
        currentPosition.setText(String.valueOf(jeu.getMyCurrentPlayer().getMyCurrentPartie().getPosition()));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Jeu jeu = new Jeu();
        Joueur joueur = new Joueur(true);

        /**Checking if the player is new or already exists**/
        if(!jeu.playerExists(joueur)){// New player , his name is not in the game PlayerList
            jeu.addPlayer(joueur);
            jeu.setMyCurrentPlayer(joueur);
            System.out.println( joueur.getNom()+" <NEW_PLAYER>");
        }else{//Un joueur deja existant  , son nom deja dans la liste des joueurs
            joueur = jeu.getPlayerByName(joueur.getNom());
            jeu.setMyCurrentPlayer(joueur);
            if(joueur == null) System.out.println("The player does exist");
        }

        /** Starting the game **/
        //rollBtn.setDisable(true);
        jeu.lancerJeu(joueur);
        Boolean currentCaseClicked = false;

        jeu.getMyCurrentPlayer().getMyCurrentPartie().getPlateau().showPlateu(myGrid);

        /** Displaying UserInfo **/
        updateData(jeu);

        newUserBtn.setOnAction(event -> {
            newUserPopUp.addNewPlayer(jeu,myGrid);
            updateData(jeu);
        });
        newGameBtn.setOnAction(event -> {
            myGrid.getChildren().clear();
            rollBtn.setDisable(false);
            Partie my_Partie = new Partie();
            jeu.getMyCurrentPlayer().addPartie(my_Partie);
            jeu.getMyCurrentPlayer().setMyCurrentPartie(my_Partie);
            my_Partie.setPlateau(new Plateau());
            System.out.println("new Game score "+jeu.getMyCurrentPlayer().getMyCurrentPartie().getScore());

            /**Displaying the Grid**/
            jeu.getMyCurrentPlayer().getMyCurrentPartie().getPlateau().showPlateu(myGrid);

            /****/
            updateData(jeu);

      });

      rollBtn.setOnAction(event -> {

          Label visitedTic = new Label("X" );

          /** Rolling the Two Dices **/
          //Dice1
          Jeu.d1.lancer();
            int d1Score = Jeu.d1.getValue();
            dice1Label.setText(d1Score+"");
            dice1Image.setImage(new Image(getClass().getResourceAsStream("/dice/dice"+d1Score+".png")));
          //Dice2
          Jeu.d2.lancer();
            int d2Score = Jeu.d2.getValue();
            dice2Label.setText(d2Score+"");
            dice2Image.setImage(new Image(getClass().getResourceAsStream("/dice/dice"+d2Score+".png")));

            Partie currentParite = jeu.getMyCurrentPlayer().getMyCurrentPartie();
          clickLabel.setText("Click the case number : "+ currentParite.getPosition()+d1Score+d2Score);


          if(currentParite.getPlateau().getPlt()[currentParite.getPosition()+d1Score+d2Score]
                  .getClickedId() == currentParite.getPosition()+d1Score+d2Score){
              clickLabel.setText("You clicked the right case");
              clickLabel.setTextFill(Color.GREEN);
          }else {
              clickLabel.setText("You clicked the wrong case");
              clickLabel.setTextFill(Color.RED);
          }
          //Just shortcuts to not write maPartie.getPosition && maPartie.getScore;
          int gamePosition = jeu.getMyCurrentPlayer().getMyCurrentPartie().getPosition();
          int gameScore = jeu.getMyCurrentPlayer().getMyCurrentPartie().getScore();
          Plateau gamePlateau = jeu.getMyCurrentPlayer().getMyCurrentPartie().getPlateau();
          Case[] currentPlt = jeu.getMyCurrentPlayer().getMyCurrentPartie().getPlateau().getPlt();
          updateData(jeu);

          if(gamePosition+d1Score+d2Score<=99){

              //updating the score of the current Partie
              gamePosition += d1Score + d2Score;

              Case rolledCase =gamePlateau.getPlt()[gamePosition];



              Boolean questionAnswer = false;
              switch (rolledCase.className){
                  case "CaseImage":
                      if(ImageQuestionController.showImageQuestion(jeu)){
                          System.out.println("Image true");
                          gamePosition += 2;
                          gameScore += 10;
                      }else {
                          System.out.println("Image false");
                          //gamePosition += 0;
                      }
                      break;
                  case "CaseDefinition":
                      if(DefinitionQuestionController.showDefinitionQuestoin(jeu)){
                          System.out.println("Def true");
                          gameScore += 20;
                          gamePosition +=4;
                      }else {
                          System.out.println("Def false");
                          //gamePosition += 0;
                      }
                      break;
                  case "CaseSaut":
                      System.out.println("CASE SAUT");
                      gameScore += rolledCase.getBonus();
                      if(gamePosition + rolledCase.getStep() >99){
                          System.out.println("CASE SAUT dangerous hard");
                          gamePosition = 99;
                          jeu.endJeu();
                          rollBtn.setDisable(true);
                      }
                      else {
                          System.out.println("Jump j");
                          gamePosition = rolledCase.getStep();
                      }

                      break;
                  default:
                      //updating the score of the current Partie
                      gameScore += rolledCase.getBonus();
                      gamePosition += rolledCase.getBonus();
                      break;
              }

              jeu.getMyCurrentPlayer().getMyCurrentPartie().setScore(gameScore);
              jeu.getMyCurrentPlayer().getMyCurrentPartie().setPosition(gamePosition);

              //adding the tic to the current Cell
              gamePlateau.getPlt()[gamePosition].getCaseVbox().getChildren().add(visitedTic);
              updateData(jeu);

          }else {
              jeu.getMyCurrentPlayer().getMyCurrentPartie().setPosition(99);
              jeu.endJeu();
              rollBtn.setDisable(true);
              System.out.println("gameScore: "+gameScore);
              jeu.getMyCurrentPlayer().setBestScore(Math.max(gameScore,jeu.getMyCurrentPlayer().getBestScore()));
              updateData(jeu);
          }
      });
        /**Displaying the List of Players or the list of games**/
      showPartieList.setOnMouseClicked(event -> {
          partieListView.setItems(FXCollections.observableArrayList(jeu.getMyCurrentPlayer().getMesParties()));
          System.out.println("-monCurrentJoueur : "+ jeu.getMyCurrentPlayer().getNom());
          System.out.println("-maCurrentPartie : "+ jeu.getMyCurrentPlayer().getMyCurrentPartie().getIdPartie());
          System.out.println("-maCurrentPartieListSize : "+ jeu.getMyCurrentPlayer().getMesParties().size());
      });
      showPlayerBtn.setOnMouseClicked(event -> {
          playerListView.setItems(FXCollections.observableArrayList(jeu.getPlayers()));
      });
    }
}
