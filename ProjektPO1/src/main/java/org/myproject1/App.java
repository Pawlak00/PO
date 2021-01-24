package org.myproject1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application {
    public void start(Stage stage) throws IOException{
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("AppScreen.fxml"));
        Parent parent=loader.load();
        Scene View=new Scene(parent);
        AppScreenController controller=loader.getController();
        controller.initData();
        Stage window=new Stage();
        window.setScene(View);
        window.show();
    }
    public static void main(String[] args){
        launch(args);

    }
}