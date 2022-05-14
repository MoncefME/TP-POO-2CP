package Models;


import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public abstract class Case {
    protected int id;
    protected String color;
    protected int step;
    protected int bonus;
    public String className;
    protected int clickedId = 0;

    public int getClickedId() {
        return clickedId;
    }

    public void setClickedId(int clickedId) {
        this.clickedId = clickedId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected VBox caseVbox = new VBox();

    public VBox getCaseVbox() {
        return caseVbox;
    }

    public void setCaseVbox(VBox caseVbox) {
        this.caseVbox = caseVbox;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String hello(){
        return "null";
    };
}
