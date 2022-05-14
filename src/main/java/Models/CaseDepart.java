package Models;

public class CaseDepart extends Case{
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
            System.out.println(getId());
            setClickedId(getId());
        });
    }
}
