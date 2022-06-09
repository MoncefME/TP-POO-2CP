package Models;

import Controllers.MainAppView;

import java.io.Serializable;

public class CaseFin extends Case implements Serializable {
    public CaseFin() {
        color = "rgba(0, 0, 0, 1)";
        className = "ClassFin";
        step = 0;
        bonus = 0;

        caseVbox.setOnMouseClicked(event -> {
            MainAppView.clickedCASE = getId();
        });
    }
}
