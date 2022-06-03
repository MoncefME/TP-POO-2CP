package Models;

import Controllers.MainAppView;

import java.io.Serializable;

public class CaseFin extends Case implements Serializable {
    @Override
    public String hello() {
        return "[F]";
    }

    public CaseFin() {
        color = "rgba(0, 0, 0, 0.6)";
        className = "ClassFin";
        step = 0;
        bonus = 0;

        caseVbox.setOnMouseClicked(event -> {
            MainAppView.clickedCASE = getId();
        });
    }
}
