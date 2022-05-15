package Controllers;

import Models.*;

import com.company.tp.HelloApplication;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainAppView implements Initializable {
    /*************FXML Elements******************/
    @FXML
    private AnchorPane blurMe;
    @FXML
    private GridPane myGrid;
    @FXML
    private Label currentScore;
    @FXML
    private Label currentPosition;
    @FXML
    private Button newGameBtn;
    @FXML
    private Button rollBtn;
    @FXML
    private ImageView dice1Image;
    @FXML
    private Label dice1Label;
    @FXML
    private ImageView dice2Image;
    @FXML
    private Label dice2Label;
    @FXML
    private Label userName;
    @FXML
    private Label clickLabel;
    @FXML
    private Button homeBtn;
    @FXML
    private Button helpBtn;
    @FXML
    private Button leaderBoard;
    @FXML
    private Label addedPoint1;
    @FXML
    private Label userTopScore;
    @FXML
    private Label gameOver;
    @FXML
    private ToggleButton toggleMusic;
    @FXML
    private ImageView toggleImg;
    @FXML
    private Label youScored;
    @FXML
    private Label gameTop;
    @FXML
    private ToggleButton toggleClick;
    @FXML
    private ImageView toggleClickImg;



    /****************Functions*******************/
    public void updateData(Jeu jeu) {
        userName.setText(jeu.getMyCurrentPlayer().getNom());
        userTopScore.setText(String.valueOf(jeu.getMyCurrentPlayer().getBestScore()));
        currentScore.setText(String.valueOf(jeu.getMyCurrentPlayer().getMyCurrentPartie().getScore()));
        currentPosition.setText(String.valueOf(jeu.getMyCurrentPlayer().getMyCurrentPartie().getPosition()));
    }

    public void addingTic(Jeu jeu) {
        Label number = new Label(jeu.getCurrentPlt()[jeu.getCurrentPosition()].getId()+"");
        ImageView cross = new ImageView( new Image(getClass().getResourceAsStream("/icons/leaderboard.png")));
        /**********************Adding the tic to the current case************/
        if (jeu.getCurrentPosition() <= 99 && jeu.getCurrentPosition() >= 0) {
            jeu.getCurrentPlt()[jeu.getCurrentPosition()].getCaseVbox().getChildren().clear();
            jeu.getCurrentPlt()[jeu.getCurrentPosition()].
                    getCaseVbox().getChildren().add(number);
            jeu.getCurrentPlt()[jeu.getCurrentPosition()].getCaseVbox().getChildren().add(cross);
        }
        /*******************************************************************/
    }

    public boolean stopCondition(String targetCaseClass) {
        if (targetCaseClass.equals("CaseParcours")) return false;
        else if (stuck) return false;
        else if (targetCaseClass.equals("CaseFin"))return false;
        else return true;
    }

    public void CaseOutOfLimit(Jeu jeu) {
        if(jeu.getGameTopScore() < jeu.getCurrentScore()){
            jeu.setGameTopScore(jeu.getCurrentScore());
        }
        if(jeu.getCurrentScore()>jeu.getMyCurrentPlayer().getBestScore()){
            jeu.getMyCurrentPlayer().setBestScore(jeu.getCurrentScore());
        }
        jeu.getCurrentPartie().setPosition(99);
        stuck = true;
        myGrid.setOpacity(0.5);
        gameOver.setVisible(true);
        youScored.setText("You Scored : " + jeu.getCurrentScore());
        youScored.setVisible(true);
        gameTop.setText("Game Top Score: "+ jeu.getGameTopScore());
        gameTop.setVisible(true);
        rollBtn.setDisable(true);
        clickLabel.setVisible(false);
        updateData(jeu);
        addingTic(jeu);

        if(jeu.getMyCurrentPlayer().getMesParties().size()!=0){
            jeu.getMyCurrentPlayer().getMesParties().remove(jeu.getCurrentPartie());
        }
       // jeu.getMyCurrentPlayer().addPartie(jeu.getMyCurrentPlayer().getMyCurrentPartie());
        jeu.getMyCurrentPlayer().setMyCurrentPartie(null);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("jeu.json"))));
            for (Joueur j: jeu.getPlayers()) {
                for (Partie p:j.getMesParties()) {
                    Plateau plateau=new Plateau();
                    plateau.setPltint(p.getPlateau().getPltint());
                    p.setPlateau(plateau);
                }
            }
            out.writeObject(jeu);
            out.close();
        }
        catch (FileNotFoundException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
    }

    /**************Static Elements****************/
    public static Stage window;
    public static FXMLLoader fxmlLoader;
    public static Scene scene;
    public static Boolean stuck = false;
    public static Jeu game = new Jeu();
    public static int clickedCASE ;
    public static Jeu jeu;
    public static Joueur joueur;
    public static File file;
    public static Plateau plateausave;
    public static double blur = 20;

    /******************Images**********************/
    Image musicOn = new Image(getClass().getResourceAsStream("/icons/musicOn.png"));
    Image musicOff = new Image(getClass().getResourceAsStream("/icons/musicOff.png"));
    Image clickOn = new Image(getClass().getResourceAsStream("/icons/on-button.png"));
    Image clickOff = new Image(getClass().getResourceAsStream("/icons/off-button.png"));

    /*****************Initialize**************************/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /********************** Initial SetUp  ***************/
        game = jeu;
        if (jeu.getCurrentPosition()==99) {CaseOutOfLimit(jeu);}
        jeu.getCurrentPlateau().showPlateu(myGrid);
        updateData(jeu);
        addingTic(jeu);


        /********************** Music on / off ***************/
        toggleMusic.setOnMouseClicked(event -> {
            if(toggleMusic.isSelected()){
                toggleImg.setImage(musicOn);
                HelloApplication.mediaPlayer1.play();
            }else {
                toggleImg.setImage(musicOff);
                HelloApplication.mediaPlayer1.pause();
            }
        });

        /******************** Pause on / off***************/
        toggleClick.setOnMouseClicked(event -> {
            if(toggleClick.isSelected()){
                toggleClickImg.setImage(clickOn);
                myGrid.setDisable(true);
                rollBtn.setDisable(true);
                myGrid.setOpacity(0.5);
                newGameBtn.setDisable(true);
                gameOver.setText("Pause");
                gameOver.setVisible(true);gameOver.setTextFill(Color.WHITE);
            }else {
                toggleClickImg.setImage(clickOff);
                gameOver.setText("You won");

                rollBtn.setDisable(false);
                myGrid.setOpacity(1);
                newGameBtn.setDisable(false);
                gameOver.setVisible(false);
                myGrid.setDisable(false);
            }
        });

        /******************Buttons**********************/
        newGameBtn.setOnAction(event -> {
            youScored.setVisible(false);
            gameTop.setVisible(false);
            clickLabel.setVisible(false);
            dice1Image.setImage(null);
            dice2Image.setImage(null);
            gameOver.setVisible(false);
            myGrid.getChildren().clear();
            rollBtn.setDisable(false);
            Partie my_Partie = new Partie(true);
            jeu.getMyCurrentPlayer().setMyCurrentPartie(my_Partie);
            my_Partie.setPlateau(new Plateau(true));

            /**Displaying the Grid**/
            jeu.getMyCurrentPlayer().getMyCurrentPartie().getPlateau().showPlateu(myGrid);
            myGrid.setOpacity(1);
            gameOver.setVisible(false);

            /****/
            updateData(jeu);
            addingTic(jeu);
        });

        /*****************Help button ****************/
        helpBtn.setOnMouseClicked(event -> {
            fxmlLoader = new FXMLLoader(
                    getClass().getResource("/Views/HelpView.fxml"));
            try {
                blurMe.setEffect(new GaussianBlur(blur));
                scene = new Scene(fxmlLoader.load());
                window = new Stage();
                window.setResizable(false);
                window.getIcons().add(HelloApplication.imageIcon);
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Help , Rules");
                window.setScene(scene);
                window.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
            blurMe.setEffect(new GaussianBlur(0));
        });

        /*****************Home Button ****************/
        homeBtn.setOnMouseClicked(event -> {
            Stage stage;
            Parent root;
            stage = (Stage) homeBtn.getScene().getWindow();
            try {
                root = FXMLLoader.load(getClass().getResource("/Views/HomeScreen.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        /******************Save Game Button ***********/
        leaderBoard.setOnMouseClicked(event -> {
            plateausave=jeu.getCurrentPlateau();
            jeu.getMyCurrentPlayer().addPartie(jeu.getMyCurrentPlayer().getMyCurrentPartie());
            try {
                ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("jeu.json"))));
                for (Joueur j: jeu.getPlayers()) {
                    for (Partie p:j.getMesParties()) {
                        Plateau plateau=new Plateau();
                        plateau.setPltint(p.getPlateau().getPltint());
                        p.setPlateau(plateau);
                    }
                }
                out.writeObject(jeu);
                out.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }finally {
                jeu.getMyCurrentPlayer().getMyCurrentPartie().setPlateau(plateausave);
            }
        });

        /***************************** Meat Coade ********************************/
        rollBtn.setOnAction(event -> {
            newGameBtn.setDisable(true);
            leaderBoard.setDisable(true);
            final boolean[] verified = {true};
            /** Rolling the Two Dices **/
            /**Dice1*/Jeu.d1.lancer();int d1Score = Jeu.d1.getValue();
            dice1Label.setText(d1Score + "");
            dice1Image.setImage(new Image(getClass().getResourceAsStream("/dice/dice" + d1Score + ".png")));
            /**Dice2*/Jeu.d2.lancer();int d2Score = Jeu.d2.getValue();
            dice2Label.setText(d2Score + "");
            dice2Image.setImage(new Image(getClass().getResourceAsStream("/dice/dice" + d2Score + ".png")));

            rollBtn.setDisable(true);
            clickLabel.setTextFill(Color.WHITE);
            clickLabel.setText("Click the case number : " + (jeu.getCurrentPosition() + d1Score + d2Score));
            if(jeu.getCurrentPosition() + d1Score + d2Score <= 99) {
                clickLabel.setVisible(true);
            }
            /******************Real Time var************/
            int gamePosition = jeu.getCurrentPosition();

            final Case[] targetCase = new Case[1];
            /******************************************/


            /**Setting the Click Property of the Target Case **/
            if (gamePosition + d1Score + d2Score < 99) {
                /*****Target Case exists***/
                targetCase[0] = jeu.getCurrentPlt()[jeu.getCurrentPosition() + d1Score + d2Score];
                myGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(clickedCASE != targetCase[0].getId() && verified[0]){
                            clickLabel.setText("You clicekd the wrong Case : " + clickedCASE);
                            clickLabel.setTextFill(Color.RED);//updating ClickLabel
                            clickLabel.setVisible(true);
                        }else
                        if (verified[0]){
                            jeu.getCurrentPlt()[jeu.getCurrentPosition()].getCaseVbox().getChildren().remove(1);
                            clickLabel.setTextFill(Color.GREEN);//updating ClickLabel
                            jeu.getCurrentPartie().setPosition(jeu.getCurrentPosition() + d1Score + d2Score);
                            updateData(jeu);
                            clickLabel.setText("You clicked the right case : " + jeu.getCurrentPosition());
                            clickLabel.setVisible(true);
                            String targetCaseClass;
                            stuck = false;
                            /***************************Action on the target Case ************************************/
                            do {
                                targetCaseClass = targetCase[0].getClassName();
                                switch (targetCaseClass) {
                                    case "CaseImage":
                                        fxmlLoader = new FXMLLoader(
                                                getClass().getResource("/Views/ImageQuestionView.fxml"));
                                        try {
                                            blurMe.setEffect(new GaussianBlur(blur));
                                            scene = new Scene(fxmlLoader.load());
                                            window = new Stage();
                                            window.setResizable(false);
                                            window.getIcons().add(HelloApplication.imageIcon);
                                            window.initModality(Modality.APPLICATION_MODAL);
                                            window.setTitle("Image Question");
                                            window.setScene(scene);
                                            window.showAndWait();
                                        } catch (IOException e) {e.printStackTrace();}

                                        if (ImageQuestionController.qAnswer) {
                                            stuck = false;
                                            jeu.incrementCurrentPosition(2);
                                            jeu.incrementCurrentScore(10);
                                        } else {
                                            addingTic(jeu);
                                            stuck = true;
                                        }
                                        blurMe.setEffect(new GaussianBlur(0));
                                        updateData(jeu);
                                        break;
                                    case "CaseDefinition":
                                        fxmlLoader = new FXMLLoader(
                                                getClass().getResource("/Views/DefinitionQuestionView.fxml"));
                                        try {
                                            blurMe.setEffect(new GaussianBlur(blur));
                                            scene = new Scene(fxmlLoader.load());
                                            window = new Stage();
                                            window.setResizable(false);
                                            window.getIcons().add(HelloApplication.imageIcon);
                                            window.initModality(Modality.APPLICATION_MODAL);
                                            window.setTitle("Definition Question");
                                            window.setScene(scene);
                                            window.showAndWait();
                                        } catch (IOException e) {}
                                        if (DefinitionQuestionController.qAnswer) {
                                            stuck = false;
                                            jeu.incrementCurrentPosition(4);jeu.incrementCurrentScore(20);
                                        } else {
                                            addingTic(jeu);stuck = true;
                                        }
                                        blurMe.setEffect(new GaussianBlur(0));
                                        updateData(jeu);break;
                                    case "CaseSaut":
                                        System.out.println("Jump to ->  " + jeu.getCurrentPlt()
                                                [jeu.getCurrentPosition()].getStep());
                                        int jump_pos=Fonctions.randomInt(2,98);
                                        jeu.getCurrentPartie().setPosition(jump_pos);
                                        stuck = false;
                                        updateData(jeu);
                                        break;
                                    case "CaseMalus":
                                        jeu.incrementCurrentPosition(-2);jeu.incrementCurrentScore(-10);
                                        updateData(jeu);break;
                                    case "CaseBonus":
                                        jeu.incrementCurrentPosition(2);jeu.incrementCurrentScore(10);
                                        updateData(jeu);break;
                                    case "CaseParcours":
                                        addingTic(jeu);updateData(jeu);break;
                                }//end of switch statement
                                if (jeu.getCurrentPosition() < 99) {targetCase[0] = jeu.getCurrentPlt()[jeu.getCurrentPosition()];}
                                else if (jeu.getCurrentPosition()==99){CaseOutOfLimit(jeu);}
                            } while (stopCondition(targetCaseClass));
                            if(jeu.getCurrentPosition() < 99) {
                                rollBtn.setDisable(false);
                                newGameBtn.setDisable(false);
                                leaderBoard.setDisable(false);
                                verified[0] =false;
                            }else {
                                rollBtn.setDisable(true);
                                newGameBtn.setDisable(false);
                                leaderBoard.setDisable(false);
                            }
                        }
                    }
                });


            }/*****gamePosition + d1scre + d2score >99 ( indexOutOfBound )***/
            else if (gamePosition + d1Score + d2Score > 99){
                 clickLabel.setVisible(false);
                jeu.getCurrentPlt()[jeu.getCurrentPosition()].getCaseVbox().getChildren().remove(1);
                targetCase[0] = jeu.getCurrentPlt()[99 - d1Score - d2Score];
                jeu.getCurrentPartie().setPosition(99 - d1Score - d2Score);
                updateData(jeu);
                String targetCaseClass = targetCase[0].getClassName();
                stuck = false;
                /***************************Action on the target Case ************************************/
                do {
                    targetCaseClass = targetCase[0].getClassName();
                    switch (targetCaseClass) {
                        case "CaseImage":
                            fxmlLoader = new FXMLLoader(
                                    getClass().getResource("/Views/ImageQuestionView.fxml"));
                            try {
                                scene = new Scene(fxmlLoader.load());
                                window = new Stage();
                                window.setResizable(false);
                                window.getIcons().add(HelloApplication.imageIcon);
                                window.initModality(Modality.APPLICATION_MODAL);
                                window.setTitle("Image Question");
                                window.setScene(scene);
                                window.showAndWait();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (ImageQuestionController.qAnswer) {
                                stuck = false;
                                jeu.incrementCurrentPosition(2);
                                jeu.incrementCurrentScore(10);
                            } else {
                                //This is illegal but to prevent stucking on the same case
                                addingTic(jeu);
                                stuck = true;
                            }
                            updateData(jeu);
                            break;
                        case "CaseDefinition":
                            fxmlLoader = new FXMLLoader(
                                    getClass().getResource("/Views/DefinitionQuestionView.fxml"));
                            try {
                                scene = new Scene(fxmlLoader.load());
                                window = new Stage();
                                window.setResizable(false);
                                window.getIcons().add(HelloApplication.imageIcon);
                                window.initModality(Modality.APPLICATION_MODAL);
                                window.setTitle("Definition Question");
                                window.setScene(scene);
                                window.showAndWait();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (DefinitionQuestionController.qAnswer) {
                                stuck = false;
                                jeu.incrementCurrentPosition(4);
                                jeu.incrementCurrentScore(20);
                            } else {
                                //This is illegal but to prevent stucking on the same case
                                addingTic(jeu);
                                stuck = true;
                            }
                            updateData(jeu);
                            break;
                        case "CaseSaut":
                            System.out.println("Jump to ->  " + jeu.getCurrentPlt()
                                    [jeu.getCurrentPosition()].getStep());
                            int jump_pos=Fonctions.randomInt(2,98);
                            jeu.getCurrentPartie().setPosition(jump_pos);
                            stuck = false;
                            updateData(jeu);
                            break;
                        case "CaseMalus":
                            jeu.incrementCurrentPosition(-2);
                            jeu.incrementCurrentScore(-10);
                            updateData(jeu);
                            break;
                        case "CaseBonus":
                            jeu.incrementCurrentPosition(2);
                            jeu.incrementCurrentScore(10);
                            updateData(jeu);
                            break;
                        case "CaseParcours":
                            //updating the score of the current Partie
                            addingTic(jeu);
                            updateData(jeu);
                            break;
                    }//end of switch statement

                    if (jeu.getCurrentPosition() <= 99) {
                        targetCase[0] = jeu.getCurrentPlt()[jeu.getCurrentPosition()];} else {CaseOutOfLimit(jeu);}
                } while (stopCondition(targetCaseClass));
                if(jeu.getCurrentPosition() < 99) {
                    rollBtn.setDisable(false);
                    newGameBtn.setDisable(false);
                    leaderBoard.setDisable(false);
                    verified[0] =false;
                }else {
                    rollBtn.setDisable(true);
                    newGameBtn.setDisable(false);
                    leaderBoard.setDisable(false);
                }
            }
            else {
                targetCase[0] = jeu.getCurrentPlt()[jeu.getCurrentPosition() + d1Score + d2Score];
                myGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (clickedCASE != targetCase[0].getId() && verified[0]) {
                            clickLabel.setText("You clicekd the wrong Case " + clickedCASE);
                            clickLabel.setTextFill(Color.RED);//updating ClickLabel
                            clickLabel.setVisible(true);
                        } else if (verified[0]) {
                            CaseOutOfLimit(jeu);
                            newGameBtn.setDisable(false);
                        }
                    }
                });
            }
        });

        /*************************************************************************/
    }
}
