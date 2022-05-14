package Controllers;

import Models.Jeu;
import Models.Joueur;
import Models.Partie;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class newUserPopUp {
    public static void addNewPlayer(Jeu jeu, GridPane myGrid){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("ADD new User");

        TextField textField = new TextField("Name");
        Button okBtn = new Button("OK");

        okBtn.setOnAction(event -> {
            Joueur j  = jeu.getPlayerByName(textField.getText());

            if(j!=null){//Already exists
                jeu.setMyCurrentPlayer(j);
                System.out.println("The player "+ j + " Already exists");
            }else {// j == null
                System.out.println(textField.getText()+" <<New Player>>");
                j = new Joueur(false);
                j.setNom(textField.getText() );
                jeu.setMyCurrentPlayer(j);
                Partie newP = new Partie();
                myGrid.getChildren().clear();
                newP.getPlateau().showPlateu(myGrid);
                j.setMyCurrentPartie(newP);
                j.addPartie(newP);
                jeu.addPlayer(j);
            }
           window.close();
           System.out.println(event.getEventType());
        });


        VBox vBox = new VBox(textField,okBtn);
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();
    }
}
