package Models;

import Controllers.MainAppView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class CaseDefinition extends CaseQuestion implements Serializable {
    @Override
    public String hello() {
        return "[D]";
    }

    private boolean answer;

    public CaseDefinition() {
        color = "rgba(0, 135, 255, 1)";
        className = "CaseDefinition";
        if(answer==true){
            step = 4;
            bonus = 20;
        }else{//answer == false
            step = 0;
            bonus = -10 ;
        }
        caseVbox.setOnMouseClicked(event -> {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/Views/DefinitionQuestionView.fxml"));
            try {
                Scene scene = new Scene(fxmlLoader.load());
                Stage window = new Stage();
                window.initModality(Modality.APPLICATION_MODAL);
                window.setTitle("Definition Question");
                window.setScene(scene);
                window.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
            MainAppView.clickedCASE = getId();
        });
    }
}
