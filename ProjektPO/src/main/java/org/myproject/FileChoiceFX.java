package org.myproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileChoiceFX {
    @FXML
    private Stage stage;
    @FXML
    private Button btn;

    private File file;
    @FXML protected void handleChooseFileAction(ActionEvent Event){
        FileChooser file=new FileChooser();
        file.setTitle("Otwórz plik");
        file.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(".json files", "*.json")
        );
        File opened=file.showOpenDialog(stage);
        this.file=opened;
        System.out.println(opened.getName());
    }
    @FXML protected void handleStartSimAction(ActionEvent Event){
        JsonParser parser=new JsonParser(file);
        pa
    }
}
