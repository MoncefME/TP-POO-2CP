package Models;

public class CaseImage extends CaseQuestion{
    @Override
    public String hello() {
        return "[I]";
    }

    private boolean answer;

    public CaseImage() {
        color = "rgba(255, 115, 239, 1)";
        if(answer==true){
            step = 2;
            bonus = 10;
        }else{//answer == false
            step = 0;
            bonus = 0 ;
        }

        caseVbox.setOnMouseClicked(event -> {
            System.out.println("Image clicked");
        });
    }
}
