package Controllers;

import Models.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.robot.Robot;
import javafx.scene.text.Font;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class DefinitionQuestionController implements Initializable {
    /******FXML elements********/
    @FXML
    private Label wordLabel,wrong_ansr;
    @FXML
    private AnchorPane anchourqst;
    @FXML
    private Button comfirmer_btn;
    @FXML
    private HBox word_box;

    /********Variables statiques *********/
    public static Boolean qAnswer = false;
    public static ArrayList<String> worddef=new ArrayList<String>(List.of(Fonctions.wordList));

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        qAnswer = false;
        String current_question;
        word_box.setDisable(false);
        comfirmer_btn.setDisable(false);
        wrong_ansr.setVisible(false);
        if (worddef.size()==0){worddef=new ArrayList<String>(List.of(Fonctions.wordList));}
        int a_random=Fonctions.randomInt(0,worddef.size()-1);

        current_question=worddef.get(a_random);
        worddef.remove(a_random);
        String[] qst_ansr=current_question.split(String.valueOf('#'));

        wordLabel.setText(qst_ansr[1]);
        int len=qst_ansr[0].length();

        for (int i = 0; i < len; i++) {
            TextField textField=new TextField();
            textField.setFont(new Font(30));
            textField.setPadding(new Insets(0,0,0,0));
            textField.setAlignment(Pos.CENTER);
            textField.setMaxSize(60,40);
            textField.setPrefSize(60,40);
            //textField.setStyle("-fx-");
            textField.setId(String.valueOf(i));
            textField.setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
                String newText = change.getControlNewText();
                if (newText.length() > 1) {return null ;} else {return change ;}
            }));
            word_box.getChildren().add(textField);
        }
        for (int i = 0; i < len; i++) {
            TextField textField= (TextField) word_box.getChildren().get(i);
            textField.setOnKeyTyped(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (textField.getText().length() != 0 && !word_box.getChildren().get(len-1).isFocused() ){
                        Robot robot=new Robot();
                        robot.keyPress(KeyCode.TAB);
                        robot.keyRelease(KeyCode.TAB);
                    }
                }
            });
        }

        comfirmer_btn.setOnMouseClicked(event -> {
            TextField textField=new TextField();
            String word_enter="";
            for (int i = 0; i < len; i++) {
                textField=(TextField) word_box.getChildren().get(i);
                word_enter+=textField.getText();

            }
            if (word_enter.toLowerCase(Locale.ROOT).equals(qst_ansr[0].toLowerCase(Locale.ROOT))){
                qAnswer=true;
                anchourqst.setStyle("-fx-background-color:rgba(122, 235, 105, 0.3);");
                wrong_ansr.setStyle("-fx-text-fill:white;-fx-background-color:green;");
                wrong_ansr.setText("Right");
                wrong_ansr.setVisible(true);
                word_box.setDisable(true);
                comfirmer_btn.setDisable(true);
            }else {
                wrong_ansr.setText(qst_ansr[0]);
                wrong_ansr.setStyle("-fx-text-fill:white;-fx-background-color:red;");
                anchourqst.setStyle("-fx-background-color:rgba(245, 39, 54, 0.3)");
                wrong_ansr.setVisible(true);
                word_box.setDisable(true);
                comfirmer_btn.setDisable(true);
                qAnswer=false;
            }
        });
    }
}