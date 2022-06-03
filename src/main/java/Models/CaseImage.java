package Models;

import Controllers.ImageQuestionController;
import Controllers.MainAppView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;

public class CaseImage extends CaseQuestion implements Serializable {
    @Override
    public String hello() {
        return "[I]";
    }

    private boolean answer;

    public CaseImage() {
        color = "rgba(255, 115, 239, 0.6)";
        className = "CaseImage";
        if(answer==true){
            step = 2;
            bonus = 10;
        }else{//answer == false
            step = 0;
            bonus = 0 ;
        }

        caseVbox.setOnMouseClicked(event -> {
            MainAppView.clickedCASE = getId();
        });
    }
}
