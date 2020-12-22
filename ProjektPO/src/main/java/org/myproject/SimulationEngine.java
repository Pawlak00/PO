package org.myproject;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class SimulationEngine {
    private WorldDescription mapDesc;
    private MapLord mapLord;
    private RectangularWorldMap map;
    private Pane canvas;
    public SimulationEngine(WorldDescription mapDesc,Pane canvas){
        this.canvas=canvas;
        this.mapDesc=mapDesc;
    }
    public void prepareSimulation(int nOfAnimals,int nOfPlants){
        this.map=new RectangularWorldMap(mapDesc,canvas);
        this.mapLord=new MapLord(map);
        mapLord.prepareMap(nOfAnimals,nOfPlants);
    }
    public MapLord getMapLords(){
        return this.mapLord;
    }
}
