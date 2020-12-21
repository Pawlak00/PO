package org.myproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    public void start(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));

        Scene scene = new Scene(root, 1280, 860);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);

    }
}