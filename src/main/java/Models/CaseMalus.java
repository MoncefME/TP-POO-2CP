package Models;

public class CaseMalus extends Case{
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
            System.out.println(getId());
            setClickedId(getId());
        });
    }
}
