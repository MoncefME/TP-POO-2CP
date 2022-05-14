package Models;

public class CaseFin extends Case{
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
            System.out.println(getId());
            setClickedId(getId());
        });
    }
}
