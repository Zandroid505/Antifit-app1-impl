/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Zakaria Antifit
 */

package mainApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ToDoListApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("IndividualList.fxml"));

        primaryStage.setTitle("To-do list");
        primaryStage.setScene(new Scene(root));
        //Scene manager!!!!

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
