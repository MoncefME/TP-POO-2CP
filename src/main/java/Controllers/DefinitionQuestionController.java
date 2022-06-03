package Controllers;

import Models.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.robot.Robot;

import java.net.URL;
import java.util.ResourceBundle;

public class DefinitionQuestionController implements Initializable {
    /******FXML elements********/
    @FXML
    private Label wordLabel;
    @FXML
    private Button comfirmer_btn;
    @FXML
    private HBox word_box;

    public static Boolean qAnswer = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int len=4;
        for (int i = 0; i < len; i++) {
            TextField textField=new TextField();
            textField.setId(String.valueOf(i));
            textField.setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
                String newText = change.getControlNewText();
                if (newText.length() > 1) {
                    return null ;
                } else {

                    return change ;
                }
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
            System.out.println(word_enter);
        });

//        int correctAnswer = Fonctions.randomInt(1,4);

//        vBox1.setOnMouseClicked(event -> {
//            if(correctAnswer==1){
//                vBox1.setStyle("-fx-background-color:green;");
//                qAnswer = true;
//            }else {
//                vBox1.setStyle("-fx-background-color:red;");
//                qAnswer = false;
//            }
//        });
//        vBox2.setOnMouseClicked(event -> {
//            if(correctAnswer==2){
//                vBox2.setStyle("-fx-background-color:green;");
//                qAnswer = true;
//            }else {
//                vBox2.setStyle("-fx-background-color:red;");
//                qAnswer = false;
//            }
//        });
//        vBox3.setOnMouseClicked(event -> {
//            if(correctAnswer==3){
//                vBox3.setStyle("-fx-background-color:green;");
//                qAnswer = true;
//            }else {
//                vBox3.setStyle("-fx-background-color:red;");
//                qAnswer = false;
//            }
//        });
//        vBox4.setOnMouseClicked(event -> {
//            if(correctAnswer==4){
//                vBox4.setStyle("-fx-background-color:green;");
//                qAnswer = true;
//            }else {
//                vBox4.setStyle("-fx-background-color:red;");
//                qAnswer = false;
//            }
//        });
    }
}