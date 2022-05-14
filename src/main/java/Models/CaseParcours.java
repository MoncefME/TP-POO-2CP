package Models;

public class CaseParcours extends Case{
    @Override
    public String hello() {
        return "[-]";
    }

    public CaseParcours() {
        color = "#ffffff";// white
        className = "CaseParcours";
        step = 0;
        bonus = 0;

        caseVbox.setOnMouseClicked(event -> {
            System.out.println(getId());
            setClickedId(getId());
        });
    }
}
