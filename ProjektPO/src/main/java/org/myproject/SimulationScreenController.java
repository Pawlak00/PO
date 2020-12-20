package org.myproject;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.FileNotFoundException;

public class SimulationScreenController {
    @FXML SimulationEngine Simulation;
    @FXML int nOfAnimals;
    @FXML int nOfPlants;
    @FXML Pane my_canvas;
    @FXML Label number_of_animals;
    @FXML Label number_of_plants;
    @FXML Label average_energy_level;
    @FXML Label average_lifespan;
    @FXML Label average_number_of_kids;
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
        average_energy_level.setText(String.valueOf(Simulation.getMapLords().get(0).getMap().getStatistics().getAverageEnergyLevel()));
        number_of_animals.setText(String.valueOf(Simulation.getMapLords().get(0).getMap().getStatistics().getNumberOfAnimals()));
        number_of_plants.setText(String.valueOf(Simulation.getMapLords().get(0).getMap().getStatistics().getNumberOfPlants()));
        average_lifespan.setText(String.valueOf(Simulation.getMapLords().get(0).getMap().getStatistics().getAverageLifespan()));
    }
    public void initData(File description,int numOfPlants,int numOfAnimals) throws FileNotFoundException {
        my_canvas.getChildren().removeAll();
        my_canvas.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        JsonParser parser=new JsonParser(description.getAbsolutePath());
        this.Simulation=new SimulationEngine(parser.makeWorlds(),my_canvas);
        this.nOfPlants=numOfPlants;
        this.nOfAnimals=numOfAnimals;
        this.Simulation.prepareSimulation(numOfAnimals,numOfPlants);
        sim=new SimTimer();
        sim.start();
    }
    @FXML protected void handleStartSimulationEvent(ActionEvent event) {
        sim.start();
    }
    @FXML public void handlePauseSimulationEvent(ActionEvent event)  {
        sim.stop();
    }
    @FXML public void handleEndSimulationEvent(ActionEvent event) {
        Platform.exit();
    }
}
