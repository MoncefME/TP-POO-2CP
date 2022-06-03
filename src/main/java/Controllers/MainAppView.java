package Controllers;

import Models.*;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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

    /****************Functions*******************/
    public void updateData(Jeu jeu) {
        userName.setText(jeu.getMyCurrentPlayer().getNom());
        userTopScore.setText(String.valueOf(jeu.getMyCurrentPlayer().getBestScore()));
        currentScore.setText(String.valueOf(jeu.getMyCurrentPlayer().getMyCurrentPartie().getScore()));
        currentPosition.setText(String.valueOf(jeu.getMyCurrentPlayer().getMyCurrentPartie().getPosition()));
    }

    public void addingTic(Jeu jeu, int type) {

        Label visitedTic = new Label();
        switch (type) {
            case 1://normale X
                visitedTic.setText("X");
                break;
            case 2://go forward
                visitedTic.setText("F");
                break;
            case 3://go backward
                visitedTic.setText("B");
                break;
            case 4://jump high
                visitedTic.setText("J");
                break;
        }
        visitedTic.setFont(new Font(10));
        visitedTic.setStyle("-fx-font-weight: bold");
        Label number = new Label(jeu.getCurrentPlt()[jeu.getCurrentPosition()].getId()+"");
        /**********************Adding the tic to the current case************/
        if (jeu.getCurrentPosition() <= 99 && jeu.getCurrentPosition() >= 0) {
            jeu.getCurrentPlt()[jeu.getCurrentPosition()].getCaseVbox().getChildren().clear();
            jeu.getCurrentPlt()[jeu.getCurrentPosition()].
                    getCaseVbox().getChildren().add(number);
            jeu.getCurrentPlt()[jeu.getCurrentPosition()].getCaseVbox().getChildren().add(visitedTic);
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
        jeu.getCurrentPartie().setPosition(99);
        jeu.endJeu();
        stuck = true;
        myGrid.setOpacity(0.5);
        gameOver.setVisible(true);
        rollBtn.setDisable(true);
        clickLabel.setVisible(false);
        System.out.println("gameScore: " + jeu.getCurrentScore());
        jeu.getMyCurrentPlayer().setBestScore(Math.max(jeu.getCurrentScore(), jeu.getMyCurrentPlayer().getBestScore()));
        System.out.println("CurrentPlayer topScore: " + jeu.getMyCurrentPlayer().getBestScore());
        updateData(jeu);
        addingTic(jeu, 1);

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
    /********************************************/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = jeu;

        if (jeu.getCurrentPosition()==99) {
            CaseOutOfLimit(jeu);
        }
        jeu.getCurrentPlateau().showPlateu(myGrid);

        /** Displaying UserInfo **/
        updateData(jeu);
        addingTic(jeu, 1);

        /******************Buttons**********************/
        newGameBtn.setOnAction(event -> {

            clickLabel.setVisible(false);
            dice1Image.setImage(null);
            dice2Image.setImage(null);
            gameOver.setVisible(false);
            myGrid.getChildren().clear();
            rollBtn.setDisable(false);
            Partie my_Partie = new Partie(true);
            jeu.getMyCurrentPlayer().setMyCurrentPartie(my_Partie);
            my_Partie.setPlateau(new Plateau(true));
            System.out.println("new Game score " + jeu.getMyCurrentPlayer().getMyCurrentPartie().getScore());

            /**Displaying the Grid**/
            jeu.getMyCurrentPlayer().getMyCurrentPartie().getPlateau().showPlateu(myGrid);
            myGrid.setOpacity(1);
            gameOver.setVisible(false);

            /****/
            updateData(jeu);
            addingTic(jeu, 1);
        });        //
        helpBtn.setOnMouseClicked(event -> {
            fxmlLoader = new FXMLLoader(
                    getClass().getResource("/Views/HelpView.fxml"));
            try {
                scene = new Scene(fxmlLoader.load());
                window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Help , Rules");
                window.setScene(scene);
                window.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });     //
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
        });     //
        leaderBoard.setOnMouseClicked(event -> {
            System.out.println("saved");
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
                System.out.println("The Object  was succesfully written to a file");
                out.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }); //
        /***********************************************/
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
            clickLabel.setTextFill(Color.BLACK);
            clickLabel.setText("Click the case number : " + (jeu.getCurrentPosition() + d1Score + d2Score));
            if(jeu.getCurrentPosition() + d1Score + d2Score < 99) {
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
                            clickLabel.setText("You clicekd the wrong Case " + clickedCASE);
                            clickLabel.setTextFill(Color.RED);//updating ClickLabel
                            clickLabel.setVisible(true);
                        }else
                        if (verified[0]){
                            jeu.getCurrentPlt()[jeu.getCurrentPosition()].getCaseVbox().getChildren().remove(1);

                            clickLabel.setTextFill(Color.GREEN);//updating ClickLabel
                            jeu.getCurrentPartie().setPosition(jeu.getCurrentPosition() + d1Score + d2Score);
                            updateData(jeu);
                            clickLabel.setText("You clicked the right case # " + jeu.getCurrentPosition());
                            clickLabel.setVisible(true);
                            String targetCaseClass;
                            stuck = false;
                            /***************************Action on the target Case ************************************/
                            do {
                                targetCaseClass = targetCase[0].getClassName();
                                System.out.println(targetCaseClass);
                                System.out.println("*********************************");
                                System.out.println("CurrentPos 1 : " + jeu.getCurrentPosition());
                                System.out.println("TargetCaseClass1 : " + targetCaseClass);
                                System.out.println("*********************************");//Displaying infos
                                switch (targetCaseClass) {
                                    case "CaseImage":
                                        fxmlLoader = new FXMLLoader(
                                                getClass().getResource("/Views/ImageQuestionView.fxml"));
                                        try {
                                            scene = new Scene(fxmlLoader.load());
                                            window = new Stage();
                                            window.initModality(Modality.APPLICATION_MODAL);
                                            window.setTitle("Image Question");
                                            window.setScene(scene);
                                            window.showAndWait();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        if (ImageQuestionController.qAnswer) {
                                            stuck = false;
                                            System.out.println("Image true");
                                            jeu.incrementCurrentPosition(2);
                                            jeu.incrementCurrentScore(10);
                                        } else {
                                            //This is illegal but to prevent stucking on the same case
                                            addingTic(jeu, 1);
                                            stuck = true;
                                            System.out.println("Image false");
                                        }
                                        updateData(jeu);
                                        break;
                                    case "CaseDefinition":
                                        fxmlLoader = new FXMLLoader(
                                                getClass().getResource("/Views/DefinitionQuestionView.fxml"));
                                        try {
                                            scene = new Scene(fxmlLoader.load());
                                            window = new Stage();
                                            window.initModality(Modality.APPLICATION_MODAL);
                                            window.setTitle("Definition Question");
                                            window.setScene(scene);
                                            window.showAndWait();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        if (DefinitionQuestionController.qAnswer) {
                                            stuck = false;
                                            System.out.println("Def true");
                                            jeu.incrementCurrentPosition(4);
                                            jeu.incrementCurrentScore(20);
                                        } else {
                                            //This is illegal but to prevent stucking on the same case
                                            addingTic(jeu, 1);
                                            stuck = true;
                                            System.out.println("Def false");
                                        }
                                        updateData(jeu);
                                        break;
                                    case "CaseSaut":
                                        System.out.println("CASE SAUT");
                                        System.out.println("Jump to ->  " + jeu.getCurrentPlt()
                                                [jeu.getCurrentPosition()].getStep());
                                        int jump_pos=Fonctions.randomInt(2,98);
                                        jeu.getCurrentPartie().setPosition(jump_pos);
                                        stuck = false;
                                        updateData(jeu);
                                        break;
                                    case "CaseMalus":
                                        System.out.println("Case Malus , go back bb");
                                        jeu.incrementCurrentPosition(-2);
                                        jeu.incrementCurrentScore(-10);
                                        updateData(jeu);
                                        break;
                                    case "CaseBonus":
                                        System.out.println("Case Bonus , LuckyUU");
                                        jeu.incrementCurrentPosition(2);
                                        jeu.incrementCurrentScore(10);
                                        updateData(jeu);
                                        break;
                                    case "CaseParcours":
                                        //updating the score of the current Partie
                                        addingTic(jeu, 1);
                                        updateData(jeu);
                                        break;
                                }//end of switch statement

                                if (jeu.getCurrentPosition() <= 99) {
                                    targetCase[0] = jeu.getCurrentPlt()[jeu.getCurrentPosition()];} else {CaseOutOfLimit(jeu);}
                                System.out.println("*********************************");
                                System.out.println("CurrentPos 2 : " + jeu.getCurrentPosition());
                                System.out.println("TargetCaseClass2 : " + targetCaseClass);
                                System.out.println("*********************************");//Displaying Infos
                            } while (stopCondition(targetCaseClass));
                            System.out.println("outside While");
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

                /*******************************************/

            }/*****gamePosition + d1scre + d2score >99 ( indexOutOfBound )***/
            else if (gamePosition + d1Score + d2Score > 99){
                targetCase[0] = jeu.getCurrentPlt()[99 - d1Score - d2Score];
                jeu.getCurrentPartie().setPosition(99 - d1Score - d2Score);
                updateData(jeu);
                String targetCaseClass = targetCase[0].getClassName();
                stuck = false;
                /***************************Action on the target Case ************************************/
                do {
                    targetCaseClass = targetCase[0].getClassName();
                    System.out.println(targetCaseClass);
                    System.out.println("*********************************");
                    System.out.println("CurrentPos 1 : " + jeu.getCurrentPosition());
                    System.out.println("TargetCaseClass1 : " + targetCaseClass);
                    System.out.println("*********************************");//Displaying infos
                    switch (targetCaseClass) {
                        case "CaseImage":
                            fxmlLoader = new FXMLLoader(
                                    getClass().getResource("/Views/ImageQuestionView.fxml"));
                            try {
                                scene = new Scene(fxmlLoader.load());
                                window = new Stage();
                                window.initModality(Modality.APPLICATION_MODAL);
                                window.setTitle("Image Question");
                                window.setScene(scene);
                                window.showAndWait();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (ImageQuestionController.qAnswer) {
                                stuck = false;
                                System.out.println("Image true");
                                jeu.incrementCurrentPosition(2);
                                jeu.incrementCurrentScore(10);
                            } else {
                                //This is illegal but to prevent stucking on the same case
                                addingTic(jeu, 1);
                                stuck = true;
                                System.out.println("Image false");
                            }
                            updateData(jeu);
                            break;
                        case "CaseDefinition":
                            fxmlLoader = new FXMLLoader(
                                    getClass().getResource("/Views/DefinitionQuestionView.fxml"));
                            try {
                                scene = new Scene(fxmlLoader.load());
                                window = new Stage();
                                window.initModality(Modality.APPLICATION_MODAL);
                                window.setTitle("Definition Question");
                                window.setScene(scene);
                                window.showAndWait();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (DefinitionQuestionController.qAnswer) {
                                stuck = false;
                                System.out.println("Def true");
                                jeu.incrementCurrentPosition(4);
                                jeu.incrementCurrentScore(20);
                            } else {
                                //This is illegal but to prevent stucking on the same case
                                addingTic(jeu, 1);
                                stuck = true;
                                System.out.println("Def false");
                            }
                            updateData(jeu);
                            break;
                        case "CaseSaut":
                            System.out.println("CASE SAUT");
                            System.out.println("Jump to ->  " + jeu.getCurrentPlt()
                                    [jeu.getCurrentPosition()].getStep());
                            int jump_pos=Fonctions.randomInt(2,98);
                            jeu.getCurrentPartie().setPosition(jump_pos);
                            stuck = false;
                            updateData(jeu);
                            break;
                        case "CaseMalus":
                            System.out.println("Case Malus , go back bb");
                            jeu.incrementCurrentPosition(-2);
                            jeu.incrementCurrentScore(-10);
                            updateData(jeu);
                            break;
                        case "CaseBonus":
                            System.out.println("Case Bonus , LuckyUU");
//                                        addingTic(jeu, 2);
                            jeu.incrementCurrentPosition(2);
                            jeu.incrementCurrentScore(10);
                            updateData(jeu);
                            break;
                        case "CaseParcours":
                            //updating the score of the current Partie
                            addingTic(jeu, 1);
                            updateData(jeu);
                            break;
                    }//end of switch statement

                    if (jeu.getCurrentPosition() <= 99) {
                        targetCase[0] = jeu.getCurrentPlt()[jeu.getCurrentPosition()];} else {CaseOutOfLimit(jeu);}
                    System.out.println("*********************************");
                    System.out.println("CurrentPos 2 : " + jeu.getCurrentPosition());
                    System.out.println("TargetCaseClass2 : " + targetCaseClass);
                    System.out.println("*********************************");//Displaying Infos
                } while (stopCondition(targetCaseClass));
                System.out.println("outside While");
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
            else{CaseOutOfLimit(jeu);}
        });//the main and the biggest part of code
        /*************************************************************************/
    }
}
