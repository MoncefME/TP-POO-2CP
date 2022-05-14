package Models;

public class CaseDepart extends Case{
    @Override
    public String hello() {
        return "[d]";
    }

    public CaseDepart() {
        color = "rgba(255, 215, 0, 1)";//jaune
        step = 0;
        bonus = 0;
    }
}
