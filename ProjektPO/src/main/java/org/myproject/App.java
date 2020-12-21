package org.myproject;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {
    public void start(Stage stage) throws IOException, CloneNotSupportedException {
        Parent root = FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));

        Scene scene = new Scene(root, 1280, 860);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws FileNotFoundException {
//        JsonParser parser=new JsonParser("/home/piotr/Pulpit/studia/semestr3/PO/ProjektPO/data/data.json");
//        SimulationEngine Sim=new SimulationEngine(parser.makeWorlds(),new Pane());
//        Sim.prepareSimulation(10,59);
//        Sim.runSimulation();
        launch(args);

    }
}