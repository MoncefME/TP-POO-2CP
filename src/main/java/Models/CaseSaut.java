package Models;

import Controllers.MainAppView;
import java.io.Serializable;

public class CaseSaut extends Case implements Serializable {

    public CaseSaut() {
        color = "rgba(255, 146, 0, 1)";
        className = "CaseSaut";
        step = Fonctions.randomInt(2,98);// the new position
        bonus = 0;

        caseVbox.setOnMouseClicked(event -> {
            MainAppView.clickedCASE = getId();
        });
    }
}
