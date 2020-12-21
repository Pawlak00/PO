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
import javafx.stage.FileChooser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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
    @FXML Label number_of_era;
    private SimTimer sim;
    private int speed;
    @FXML protected void handleSaveStatisticsEvent(ActionEvent event) throws IOException {
        FileChooser file=new FileChooser();
        File saveTo=file.showSaveDialog(null);
        Files.writeString(saveTo.toPath(),  this.Simulation.getMapLords().get(0).getStatistics().toString(), StandardCharsets.UTF_8);
    }

    private class SimTimer extends AnimationTimer{
        private long last;
        @Override
        public void handle(long l) {
            try {
                if(l-last>speed) {
                    step();
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
    public void step() throws CloneNotSupportedException {
        Simulation.getMapLords().get(0).runEra();
        number_of_era.setText(String.valueOf(Simulation.getMapLords().get(0).getNumberOfEras()));
        average_energy_level.setText(String.format("%.2f",Simulation.getMapLords().get(0).getMap().getStatistics().getAverageEnergyLevel()));
        number_of_animals.setText(String.valueOf(Simulation.getMapLords().get(0).getMap().getStatistics().getNumberOfAnimals()));
        number_of_plants.setText(String.valueOf(Simulation.getMapLords().get(0).getMap().getStatistics().getNumberOfPlants()));
        average_lifespan.setText(String.format("%.2f",Simulation.getMapLords().get(0).getMap().getStatistics().getAverageLifespan()));
        String tmp=String.format("%.2f",((Simulation.getMapLords().get(0).getMap().getStatistics().getAverageNumberOfKids())));
        average_number_of_kids.setText(String.format("%.2f",((Simulation.getMapLords().get(0).getMap().getStatistics().getAverageNumberOfKids()))));
    }
    public void initData(File description,int numOfPlants,int numOfAnimals) throws FileNotFoundException {
        my_canvas.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        JsonParser parser=new JsonParser(description.getAbsolutePath());
        this.Simulation=new SimulationEngine(parser.makeWorlds(),my_canvas);
        this.nOfPlants=numOfPlants;
        this.nOfAnimals=numOfAnimals;
//        System.out.println(numOfAnimals+" "+numOfPlants);
        this.Simulation.prepareSimulation(numOfAnimals,numOfPlants);
        this.speed=1;
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
