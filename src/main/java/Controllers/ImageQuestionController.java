package Controllers;

import Models.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

public class ImageQuestionController implements Initializable {

    /**************FXMl elements****************/
    @FXML
    private Label wordLabel;
    @FXML
    private VBox vBox1;
    @FXML
    private ImageView img1;

    @FXML
    private VBox vBox2;
    @FXML
    private ImageView img2;

    @FXML
    private VBox vBox3;
    @FXML
    private ImageView img4;

    @FXML
    private VBox vBox4;
    @FXML
    private ImageView img3;
    public static Boolean qAnswer = false;
    /**************Generating the random Images ***************/
    Image image1 = new Image("/Images/"+Fonctions.randomInt(1,846)+".png");
    Image image2 = new Image("/Images/"+Fonctions.randomInt(1,846)+".png");
    Image image3 = new Image("/Images/"+Fonctions.randomInt(1,846)+".png");
    Image image4 = new Image("/Images/"+Fonctions.randomInt(1,846)+".png");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        int correctAnswer = Fonctions.randomInt(1,4);
        System.out.println("The correct Image is number " + correctAnswer);
        /**********************************************************/
        img1.setImage(image1);
        img2.setImage(image2);
        img3.setImage(image3);
        img4.setImage(image4);//Setting the Images in place
        /*********************************************************/
        img1.setOnMouseClicked(event -> {
            if(correctAnswer==1){
                System.out.println("this is the correct answer");
                qAnswer = true;
                vBox1.setStyle("-fx-background-color:green;");
                //MainAppView.window.close();
            }else {
                qAnswer = false;
                vBox1.setStyle("-fx-background-color:red;");
                System.out.println("Wrong answer");
            }
        });
        img2.setOnMouseClicked(event -> {
            if(correctAnswer==2){
                System.out.println("this is the correct answer");
                qAnswer = true;
                vBox2.setStyle("-fx-background-color:green;");
                //MainAppView.window.close();
            }else {
                qAnswer = false;
                vBox2.setStyle("-fx-background-color:red;");
                System.out.println("Wrong answer");
            }
        });
        img3.setOnMouseClicked(event -> {
            if(correctAnswer==3){
                System.out.println("this is the correct answer");
                qAnswer = true;
                vBox3.setStyle("-fx-background-color:green;");
                //MainAppView.window.close();
            }else {
                qAnswer = false;
                vBox3.setStyle("-fx-background-color:red;");
                System.out.println("Wrong answer");
            }
        });
        img4.setOnMouseClicked(event -> {
            if(correctAnswer==4){
                System.out.println("this is the correct answer");
                qAnswer = true;
                vBox4.setStyle("-fx-background-color:green;");
                //MainAppView.window.close();
            }else {
                qAnswer = false;
                vBox4.setStyle("-fx-background-color:green;");
                System.out.println("Wrong answer");
            }
        });
    }
}