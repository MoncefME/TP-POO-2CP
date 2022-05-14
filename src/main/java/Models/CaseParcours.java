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
    }
}
