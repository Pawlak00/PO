package org.myproject;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SubScene;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SimulationScreenController {
    @FXML SimulationEngine Simulation;
    @FXML int nOfAnimals;
    @FXML int nOfPlants;
    @FXML Pane my_canvas;
    private SimTimer sim;
    private class SimTimer extends AnimationTimer{
        private long last;
        @Override
        public void handle(long l) {
            try {
                if(l-last>800) {
                    step();
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
    public void step() throws CloneNotSupportedException {
        Simulation.getMapLords().get(0).runEra();
    }
    public void initData(File description,int numOfPlants,int numOfAnimals) throws FileNotFoundException, CloneNotSupportedException, InterruptedException {
        JsonParser parser=new JsonParser(description.getAbsolutePath());
        this.Simulation=new SimulationEngine(parser.makeWorlds(),my_canvas);
        this.nOfPlants=numOfPlants;
        this.nOfAnimals=numOfAnimals;
        this.Simulation.prepareSimulation(numOfAnimals,numOfPlants);
        sim=new SimTimer();
        sim.start();
    }
    @FXML protected void handleStartSimulationEvent(ActionEvent event) throws CloneNotSupportedException, InterruptedException {
        sim.start();
    }
    @FXML public void handlePauseSimulationEvent(ActionEvent event) throws InterruptedException, CloneNotSupportedException {
        sim.stop();
    }

    @FXML public void handleEndSimulationEvent(ActionEvent event) {
        Platform.exit();
    }
}
