package Models;

import Controllers.MainAppView;

import java.io.Serializable;

public class CaseMalus extends Case implements Serializable {

    public CaseMalus() {
        color = "rgba(230, 4, 4, 1)";//red
        className = "CaseMalus";
        step = -2;
        bonus = -10;

        caseVbox.setOnMouseClicked(event -> {
            MainAppView.clickedCASE = getId();
        });
    }
}
