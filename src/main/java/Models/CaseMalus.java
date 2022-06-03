package Models;

import Controllers.MainAppView;

import java.io.Serializable;

public class CaseMalus extends Case implements Serializable {
    @Override
    public String hello() {
        return "[M]";
    }

    public CaseMalus() {
        color = "rgba(230, 4, 4, 0.9)";//red
        className = "CaseMalus";
        step = -2;
        bonus = -10;

        caseVbox.setOnMouseClicked(event -> {
            MainAppView.clickedCASE = getId();
        });
    }
}
