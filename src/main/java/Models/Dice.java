package Models;

import java.io.Serializable;

public class Dice implements Serializable {
    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void lancer(){
        value = Fonctions.randomInt(1,6);
    }
}
