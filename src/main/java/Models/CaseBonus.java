package Models;

public class CaseBonus extends Case{
    @Override
    public String hello() {
        return "[B]";
    }

    public CaseBonus() {
        color = "rgba(16, 230, 5, 1)";//vert
        step = 2;
        bonus  = 10;
        caseVbox.setOnMouseClicked(event -> {
            System.out.println("Bonus clicked");
        });
    }




}
