package Models;

public class CaseParcours extends Case{
    @Override
    public String hello() {
        return "[-]";
    }

    public CaseParcours() {
        color = "#ffffff";// white
        step = 0;
        bonus = 0;

        caseVbox.setOnMouseClicked(event -> {
            System.out.println("Parcours clicked");
        });
    }
}
