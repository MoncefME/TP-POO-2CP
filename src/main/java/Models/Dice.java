package Models;

public class Dice {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int lancer(){
        return Fonctions.randomInt(1,6);
    }
}
