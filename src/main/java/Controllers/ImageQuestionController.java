package Controllers;

import Models.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ImageQuestionController implements Initializable {

    /**************FXMl elements****************/
    @FXML
    private GridPane gridImage;
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


    /**************Generating the random Images ***************/

    /***********static variables*********************/
    public static String absolutePath = System.getProperty("user.dir");
    public static Boolean qAnswer;
    private static File[] filesArray = new File(absolutePath+"\\src\\main\\resources\\Data_Images").listFiles();
    private static ArrayList<File> files = new ArrayList<File>(List.of(filesArray));


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        qAnswer = false;
        if(files.size() <4 ){
            ArrayList<File> files = new ArrayList<File>(List.of(filesArray));
        }

        int correctAnswer = Fonctions.randomInt(0,3);

        ArrayList<Integer> randomInt = new ArrayList<Integer>();
        int current_random = Fonctions.randomInt(0,files.size()-1);
        randomInt.add(current_random);


        //setting image 1
        Image image1 = new Image(files.get(current_random).getAbsolutePath());
        String image1_name =files.get(current_random).getName();
        image1_name.substring(0,image1_name.length()-3);

        //setting image 2
        current_random = Fonctions.randomIntUnique(0,files.size()-1,randomInt);
        randomInt.add(current_random);
        Image image2 = new Image(files.get(current_random).getAbsolutePath());
        String image2_name =files.get(current_random).getName() ;
        image2_name.substring(0,image2_name.length()-3);

        //setting image 3
        current_random = Fonctions.randomIntUnique(0,files.size()-1,randomInt);
        randomInt.add(current_random);
        Image image3 = new Image(files.get(current_random).getAbsolutePath());
        String image3_name =files.get(current_random).getName() ;
        image3_name.substring(0,image3_name.length()-3);

        //setting image 4
        current_random = Fonctions.randomIntUnique(0,files.size()-1,randomInt);
        randomInt.add(current_random);
        Image image4 = new Image(files.get(current_random).getAbsolutePath());
        String image4_name =files.get(current_random).getName() ;
        image4_name.substring(0,image3_name.length()-3);


        int index;

        /**********************Removigng the correct answer not to get it agian****************/
        switch (correctAnswer){
            case 0 :
                wordLabel.setText(image1_name.substring(0,image1_name.length()-3).toUpperCase(Locale.ROOT));
                index=randomInt.get(0);
                files.remove(index);
                break;
            case 1 :
                wordLabel.setText(image2_name.substring(0,image2_name.length()-3).toUpperCase(Locale.ROOT));
                index=(Integer)randomInt.get(1);
                files.remove(index);
                break;
            case 2:
                wordLabel.setText(image3_name.substring(0,image3_name.length()-3).toUpperCase(Locale.ROOT));
                index=(Integer)randomInt.get(2);
                files.remove(index);
                break;
            case 3:
                wordLabel.setText(image4_name.substring(0,image4_name.length()-3).toUpperCase(Locale.ROOT));
                index=(Integer)randomInt.get(3);
                files.remove(index);
                break;
        }

        /*************************setting the images in the ImageView**************************/
        img1.setImage(image1);
        img2.setImage(image2);
        img3.setImage(image3);
        img4.setImage(image4);//Setting the Images in place


        /************************Le click pour chaque image*********************************/
        img1.setOnMouseClicked(event -> {
            gridImage.setDisable(true);
            if(correctAnswer==0){
                qAnswer = true;
                vBox1.setStyle("-fx-background-color:green;");
                //MainAppView.window.close();
            }else {
                gridImage.getChildren().get(correctAnswer).setStyle("-fx-background-color:green;");
                qAnswer = false;
                vBox1.setStyle("-fx-background-color:red;");
            }
        });
        img2.setOnMouseClicked(event -> {
            gridImage.setDisable(true);
            if(correctAnswer==1){
                qAnswer = true;
                vBox2.setStyle("-fx-background-color:green;");
            }else {
                gridImage.getChildren().get(correctAnswer).setStyle("-fx-background-color:green;");
                qAnswer = false;
                vBox2.setStyle("-fx-background-color:red;");
            }
        });
        img3.setOnMouseClicked(event -> {
            gridImage.setDisable(true);
            if(correctAnswer==2){
                qAnswer = true;
                vBox3.setStyle("-fx-background-color:green;");
            }else {
                gridImage.getChildren().get(correctAnswer).setStyle("-fx-background-color:green;");
                qAnswer = false;
                vBox3.setStyle("-fx-background-color:red;");
            }
        });
        img4.setOnMouseClicked(event -> {
            gridImage.setDisable(true);
            if(correctAnswer==3){
                qAnswer = true;
                vBox4.setStyle("-fx-background-color:green;");
            }else {
                gridImage.getChildren().get(correctAnswer).setStyle("-fx-background-color:green;");
                qAnswer = false;
                vBox4.setStyle("-fx-background-color:red;");
            }
        });
    }
}