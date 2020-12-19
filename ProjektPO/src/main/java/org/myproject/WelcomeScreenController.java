package org.myproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class WelcomeScreenController {
    @FXML Stage stage;
    @FXML TextField number_of_animals;
    @FXML TextField number_of_plants;
    @FXML File file;
    @FXML protected void handleBeginSimulationEvent(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("SimulationScreen.fxml"));
        Parent simulationParent=loader.load();
        Scene SimulationView=new Scene(simulationParent);
        SimulationScreenController controller=loader.getController();
        controller.initData(file,Integer.parseInt(number_of_animals.getText()),Integer.parseInt(number_of_plants.getText()));
        Stage window=(Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(SimulationView);
        window.show();
    }
    @FXML protected void handleFileChooseAction(ActionEvent event){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Map settings");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(".json files", "*.json"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            file=selectedFile;
        }
    }
}
