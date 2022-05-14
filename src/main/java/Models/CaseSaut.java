package Models;

public class CaseSaut extends Case{
    @Override
    public String hello() {
        return "[S]";
    }

    public CaseSaut() {
        color = "rgba(255, 151, 0, 0.68)";
        step = Fonctions.randomInt(1,100);// the new position
        bonus = 0;

        caseVbox.setOnMouseClicked(event -> {
            System.out.println("Jump clicked");
        });
    }
}
