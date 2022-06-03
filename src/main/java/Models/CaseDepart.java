package Models;

import Controllers.MainAppView;

import java.io.Serializable;

public class CaseDepart extends Case implements Serializable {
    @Override
    public String hello() {
        return "[d]";
    }

    public CaseDepart() {
        color = "rgba(255, 215, 0, 0.5)";//jaune
        className = "CaseDepart";
        step = 0;
        bonus = 0;
        caseVbox.setOnMouseClicked(event -> {
            MainAppView.clickedCASE = getId();
        });
    }
}
