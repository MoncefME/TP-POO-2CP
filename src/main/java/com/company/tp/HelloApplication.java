package com.company.tp;

import Controllers.HomeScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {

    /*****************La musique********************/
    public static Media media1 = new Media(new File("D:\\study\\Sfour\\TP_POO\\TP_POO_VF\\TP_MoncefAmmar\\src\\main\\resources\\Music\\AdventureDiceMusic.mp3").toURI().toString());
    public static MediaPlayer mediaPlayer1  = new MediaPlayer(media1);
    public static File file;
    /************l'icon de l'application***********/
    public static Image imageIcon = new Image(HelloApplication.class.getResourceAsStream("/icons/logo.png"));
    @Override
    public void start(Stage stage) throws IOException {
        mediaPlayer1.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer1.play();
        try {
            //le fichier de donne jeu
            file = new File("jeu.json");
            HomeScreenController.file=file;
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("File error occurred.");
        }
        Parent  root = FXMLLoader.load(getClass().getResource("/Views/HomeScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Adventure Dice ");
        stage.setResizable(false);
        stage.getIcons().add(imageIcon);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}