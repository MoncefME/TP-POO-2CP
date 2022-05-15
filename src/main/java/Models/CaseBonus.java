package Models;

import Controllers.MainAppView;

import java.io.Serializable;

public class CaseBonus extends Case implements Serializable {

    public CaseBonus() {
        className = "CaseBonus";
        color = "rgba(16, 230, 5, 1)";//vert
        step = 2;
        bonus  = 10;
        caseVbox.setOnMouseClicked(event -> {
            MainAppView.clickedCASE = getId();
        });
    }
}
