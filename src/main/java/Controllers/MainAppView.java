package Controllers;

import Models.Fonctions;
import Models.Jeu;
import Models.Plateau;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.lang.invoke.CallSite;
import java.net.URL;
import java.time.temporal.JulianFields;
import java.util.ResourceBundle;

public class MainAppView implements Initializable {

    @FXML
    private GridPane myGrid;
    @FXML
    private Button randomizeBtn;
    @FXML
    private Button rollButton;
    @FXML
    private Label dice1Label;
    @FXML
    private ImageView dice1Image;

    @FXML
    private Label userName;

    @FXML
    private Label dice2Label;
    @FXML
    private ImageView dice2Image;

    @FXML
    private Label currentScore;
    @FXML
    private Label currentPosition;
    @FXML
    private Label gameTopScore;
    @FXML
    private Label userTopScore;
    @FXML
    private TextField texInput;


//    private Image dice1png = new Image(getClass().getResourceAsStream("/Images.dice/dice1.png"));
//    private Image dice2png = new Image(getClass().getResourceAsStream("/Images.dice/dice2.png"));
//    private Image dice3png = new Image(getClass().getResourceAsStream("/Images.dice/dice3.png"));
//    private Image dice4png = new Image(getClass().getResourceAsStream("/Images.dice/dice4.png"));
//    private Image dice5png = new Image(getClass().getResourceAsStream("/Images.dice/dice5.png"));
//    private Image dice6png = new Image(getClass().getResourceAsStream("/Images.dice/dice6.png"));



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Jeu jeu = new Jeu();
        jeu.lancerJeu();


        userName.setText(jeu.getPartie().getJoueur().getNom());
        gameTopScore.setText(String.valueOf(Jeu.gameTopScore));
        userTopScore.setText(String.valueOf(jeu.getPartie().getJoueur().getBestScore()));

        randomizeBtn.setOnMousePressed(event -> {
            jeu.getPartie().setPlateau(new Plateau());

        });

      randomizeBtn.setOnMouseClicked(event -> {
          Plateau p = jeu.getPartie().getPlateau();


          for(int i = 0 ; i<10; i++){
              for (int j=0; j<10 ; j++){
                  int k = Fonctions.spiralPattern[i][j];
                  VBox v = p.getPlt()[k].getCaseVbox();
                  v.setStyle("-fx-background-color:"+p.getPlt()[k].getColor()+";"+
                          "-fx-border-color : rgba(0,0,0,1);-fx-vgap: 5;-fx-hgap:5;"+
                          "-fx-border-width: 0;-fx-border-insets: 0, 20;-fx-padding:5;"+
                          Fonctions.caseBorderStyle[Fonctions.spiralBorderPattern[i][j]]+
                          "-fx-border-radius: 3;"
                  );

                  v.setAlignment(Pos.CENTER);
                  //v.setSpacing(5);
                  Label l1 = new Label(k+"");
                  Label l2 = new Label(p.getPlt()[k].getBonus()+"$ "+" +"+p.getPlt()[k].getStep());
                  v.getChildren().add(l1);

                  myGrid.add(v, j, i);

              }
          }
      });

      rollButton.setOnMouseClicked(event -> {
          Label l3 = new Label("X" );

          int d1Score = Jeu.d1.lancer();
          int d2Score = Jeu.d2.lancer();
          dice1Label.setText(d1Score+"");
          dice2Label.setText(d2Score+"");

//          dice1Image.setImage(dice1png);
//          dice2Image.setImage(dice6png);

           dice1Image.setImage(new Image(getClass().getResourceAsStream("/dice/dice"+d1Score+".png")));
          dice2Image.setImage(new Image(getClass().getResourceAsStream("/dice/dice"+d2Score+".png")));

          int gamePos = jeu.getPartie().getPosition();
          int gameScore = jeu.getPartie().getScore();


          if(gamePos<99){

              jeu.getPartie().setScore(gameScore + jeu.getPartie().getPlateau().getPlt()[gamePos].getBonus());
              System.out.println(jeu.getPartie().getPlateau().getPlt()[gamePos].getBonus());
              jeu.getPartie().setPosition(gamePos +d1Score + d2Score);
              currentScore.setText(jeu.getPartie().getScore()+"");
              currentPosition.setText(jeu.getPartie().getPosition()+"");

              jeu.getPartie().getPlateau().getPlt()[gamePos].getCaseVbox().getChildren().add(l3);

          }else {
              jeu.endJeu();
              gamePos = 0;
              jeu.getPartie().getJoueur().setBestScore(Math.max(gameScore,jeu.getPartie().getJoueur().getBestScore()));
          }
      });



    }
}
