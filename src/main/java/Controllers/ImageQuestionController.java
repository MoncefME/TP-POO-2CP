package Controllers;

import Models.Case;
import Models.Jeu;
import Models.Joueur;
import Models.Partie;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ImageQuestionController {
    public static Boolean qAnswer;
    public static Boolean showImageQuestion(Jeu jeu){

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Image Question");
        Label label = new Label("This is an Image question , you should choose ther right image");
        Button trueBtn = new Button("TRUE");
        Button falseBtn = new Button("FALASE");
        trueBtn.setOnAction(event -> {
            qAnswer = true;
            window.close();
        });
        falseBtn.setOnAction(event -> {
            qAnswer = false;
            window.close();
        });
        VBox vBox = new VBox(label,trueBtn,falseBtn);
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
        return qAnswer;
    }
}