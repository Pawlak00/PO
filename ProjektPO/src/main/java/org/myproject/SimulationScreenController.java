package org.myproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.io.File;
import java.io.FileNotFoundException;

public class SimulationScreenController {
    @FXML SimulationEngine Simulation;
    @FXML int nOfAnimals;
    @FXML int nOfPlants;
    @FXML GridPane canvas;
    public void initData(File description,int numOfPlants,int numOfAnimals) throws FileNotFoundException {
        System.out.println(description);
        JsonParser parser=new JsonParser(description.getAbsolutePath());
        this.Simulation=new SimulationEngine(parser.makeWorlds());
        this.nOfPlants=numOfPlants;
        this.nOfAnimals=numOfAnimals;
        int numRows=this.Simulation.mapsDesc.get(0).mapWidth;
        int numCols=this.Simulation.mapsDesc.get(0).mapHeight;
        for (int i = 0; i < numCols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / numCols);
            canvas.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < numRows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / numRows);
            canvas.getRowConstraints().add(rowConst);
        }
    }
    @FXML protected void handleStartSimulationEvent(ActionEvent event){
        Simulation.prepareSimulation(nOfAnimals,nOfPlants);


    }

}
