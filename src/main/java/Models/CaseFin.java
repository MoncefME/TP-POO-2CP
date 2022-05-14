package Models;

public class CaseFin extends Case{
    @Override
    public String hello() {
        return "[F]";
    }

    public CaseFin() {
        color = "rgba(0, 0, 0, 1)";
        step = 0;
        bonus = 0;
    }
}
