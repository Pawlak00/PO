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

public class    SimulationScreenController {
    SimulationEngine Simulation;
    SimulationEngine Simulation1;
    @FXML int nOfAnimals;
    @FXML int nOfPlants;
    @FXML Pane my_canvas;
    @FXML Label number_of_animals;
    @FXML Label number_of_plants;
    @FXML Label average_energy_level;
    @FXML Label average_lifespan;
    @FXML Label average_number_of_kids;
    @FXML Label number_of_era;
    @FXML Label dominating_genotype;
    @FXML Label dead_animal_age;
    @FXML Pane my_canvas1;
    @FXML Label number_of_animals1;
    @FXML Label number_of_plants1;
    @FXML Label average_energy_level1;
    @FXML Label average_lifespan1;
    @FXML Label average_number_of_kids1;
    @FXML Label number_of_era1;
    @FXML Label dominating_genotype1;
    @FXML Label dead_animal_age_1;
    private SimTimer sim;
    private SimTimer1 sim1;
    private int speed;
    @FXML protected void handleSaveStatisticsEvent(ActionEvent event) throws IOException {
        FileChooser file=new FileChooser();
        File saveTo=file.showSaveDialog(null);
        String toSave=this.Simulation.getMapLords().getStatistics().toString();
        if(sim1!=null){
            toSave+="\n"+this.Simulation1.getMapLords().getStatistics().toString();
        }
        Files.writeString(saveTo.toPath(),  toSave, StandardCharsets.UTF_8);
    }

    public void handleShowDominantEvent(ActionEvent event) {
        this.Simulation.getMapLords().getMap().getStatistics().getDominatingGenotype();
        this.Simulation.getMapLords().getMap().getStatistics().getDominatingAnimals();
        if(sim1!=null){
            this.Simulation1.getMapLords().getMap().getStatistics().getDominatingGenotype();
            this.Simulation1.getMapLords().getMap().getStatistics().getDominatingAnimals();
        }
    }
    public void handleHideDominantEvent(ActionEvent event){
        this.Simulation.getMapLords().getMap().getStatistics().closeDominatingAnimals();
        if(sim1!=null){
            this.Simulation1.getMapLords().getMap().getStatistics().closeDominatingAnimals();
        }
    }

    public void handlePauseSimulation1Event(ActionEvent event) {
        if(sim1!=null){
            sim1.stop();
        }
    }

    public void handleStartSimulation1Event(ActionEvent event) {
        if(sim1!=null){
            sim1.start();
        }
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
    private class SimTimer1 extends AnimationTimer{
        private long last;
        @Override
        public void handle(long l) {
            try {
                if(l-last>speed) {
                    step1();
                }
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
    }
    public void step() throws CloneNotSupportedException {
        Simulation.getMapLords().runEra();
        number_of_era.setText(String.valueOf(Simulation.getMapLords().getNumberOfEras()));
        average_energy_level.setText(String.format("%.2f",Simulation.getMapLords().getMap().getStatistics().getAverageEnergyLevel()));
        number_of_animals.setText(String.valueOf(Simulation.getMapLords().getMap().getStatistics().getNumberOfAnimals()));
        number_of_plants.setText(String.valueOf(Simulation.getMapLords().getMap().getStatistics().getNumberOfPlants()));
        average_lifespan.setText(String.format("%.2f",Simulation.getMapLords().getMap().getStatistics().getAverageLifespan()));
        average_number_of_kids.setText(String.format("%.2f",((Simulation.getMapLords().getMap().getStatistics().getAverageNumberOfKids()))));
        dominating_genotype.setText(Simulation.getMapLords().getMap().getStatistics().getDominatingGenotype().toString());
        dead_animal_age.setText(String.format("%.2f",Simulation.getMapLords().getMap().getStatistics().getAverageDeadAnimalAge()));
    }
    public void step1() throws CloneNotSupportedException {
        Simulation1.getMapLords().runEra();
        number_of_era1.setText(String.valueOf(Simulation1.getMapLords().getNumberOfEras()));
        average_energy_level1.setText(String.format("%.2f",Simulation1.getMapLords().getMap().getStatistics().getAverageEnergyLevel()));
        number_of_animals1.setText(String.valueOf(Simulation1.getMapLords().getMap().getStatistics().getNumberOfAnimals()));
        number_of_plants1.setText(String.valueOf(Simulation1.getMapLords().getMap().getStatistics().getNumberOfPlants()));
        average_lifespan1.setText(String.format("%.2f",Simulation1.getMapLords().getMap().getStatistics().getAverageLifespan()));
        average_number_of_kids1.setText(String.format("%.2f",((Simulation1.getMapLords().getMap().getStatistics().getAverageNumberOfKids()))));
        dominating_genotype1.setText(Simulation1.getMapLords().getMap().getStatistics().getDominatingGenotype().toString());
        dead_animal_age_1.setText(String.format("%.2f",Simulation1.getMapLords().getMap().getStatistics().getAverageDeadAnimalAge()));
    }
    public void initData(File description,int numOfPlants,int numOfAnimals) throws FileNotFoundException {
        my_canvas.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        my_canvas1.setBackground(new Background(new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
        JsonParser parser=new JsonParser(description.getAbsolutePath());
        this.Simulation=new SimulationEngine(parser.makeWorlds().get(0),my_canvas);
        this.Simulation.prepareSimulation(numOfAnimals,numOfPlants);
        this.nOfPlants=numOfPlants;
        this.nOfAnimals=numOfAnimals;
        this.speed=1;
        sim=new SimTimer();
        if(parser.makeWorlds().size()==2){
            this.Simulation1=new SimulationEngine(parser.makeWorlds().get(1),my_canvas1);
            this.Simulation1.prepareSimulation(numOfAnimals,numOfPlants);
            sim1=new SimTimer1();
        }
        sim.start();
        if(sim1!=null){
            sim1.start();
        }
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
