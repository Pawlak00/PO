package org.myproject;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {
    private List<WorldDescription> mapsDesc;
    private List<MapLord>mapLords;
    private List<RectangularWorldMap>maps;
    private List<Scene>scenes;
    private Pane canvas;
    public SimulationEngine(List<WorldDescription> mapsDescs,Pane canvas){
        this.canvas=canvas;
        this.scenes=new ArrayList<>();
        this.mapsDesc=mapsDescs;
        this.mapLords=new ArrayList<>();
        this.maps=new ArrayList<>();
    }
    public void prepareSimulation(int nOfAnimals,int nOfPlants){
        for(WorldDescription mapDescription:mapsDesc){
            this.maps.add(new RectangularWorldMap(mapDescription,canvas));
            this.scenes.add(new Scene(new GridPane(),1280,860));
        }
        for(RectangularWorldMap map:maps){
            this.mapLords.add(new MapLord(map));
        }
        for(MapLord lord:this.mapLords){
            lord.prepareMap(nOfAnimals,nOfPlants);
        }
    }
    public List<MapLord> getMapLords(){
        return this.mapLords;
    }
}
