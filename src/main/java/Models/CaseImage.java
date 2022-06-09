package Models;


import Controllers.MainAppView;
import java.io.Serializable;

public class CaseImage extends CaseQuestion implements Serializable {


    private boolean answer;

    public CaseImage() {
        color = "rgba(255, 115, 239, 1)";
        className = "CaseImage";
        if(answer==true){
            step = 2;
            bonus = 10;
        }else{//answer == false
            step = 0;
            bonus = 0 ;
        }

        caseVbox.setOnMouseClicked(event -> {
            MainAppView.clickedCASE = getId();

//                FXMLLoader fxmlLoader = new FXMLLoader(
//                    getClass().getResource("/Views/ImageQuestionView.fxml"));
//            try {
//                Scene scene = new Scene(fxmlLoader.load());
//                Stage window = new Stage();
//                window.initModality(Modality.APPLICATION_MODAL);
//                window.setTitle("Image Question");
//                window.setScene(scene);
//                window.showAndWait();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
        });
    }
}
