package Models;

public class CaseDefinition extends CaseQuestion {
    @Override
    public String hello() {
        return "[D]";
    }

    private boolean answer;

    public CaseDefinition() {
        color = "rgba(0, 135, 255, 1)";
        className = "CaseDefinition";
        if(answer==true){
            step = 4;
            bonus = 20;
        }else{//answer == false
            step = 0;
            bonus = -10 ;
        }
        caseVbox.setOnMouseClicked(event -> {
            System.out.println(getId());
            setClickedId(getId());
        });
    }
}
