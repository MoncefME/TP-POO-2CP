package com.company.tp;

import Controllers.HomeScreenController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;


public class HelloApplication extends Application {


    public static File file;
    public static File file1;
    @Override
    public void start(Stage stage) throws IOException {

        try {
            file = new File("jeu.json");
            HomeScreenController.file=file;
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        Parent  root = FXMLLoader.load(getClass().getResource("/Views/HomeScreen.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Travail Pratique Programmation Orientee Objee [POO]!");
        stage.setResizable(false);
        stage.setX(0);
        stage.setY(0);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}