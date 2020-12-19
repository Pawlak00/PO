package org.myproject;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {
    public List<WorldDescription> mapsDesc;
    public List<MapLord>mapLords;
    public List<RectangularWorldMap>maps;
    public List<Scene>scenes;
    public SimulationEngine(List<WorldDescription> mapsDescs){
        this.scenes=new ArrayList<>();
        this.mapsDesc=mapsDescs;
        this.mapLords=new ArrayList<>();
        this.maps=new ArrayList<>();
    }
    public void prepareSimulation(int nOfAnimals,int nOfPlants){
        for(WorldDescription mapDescription:mapsDesc){
            this.maps.add(new RectangularWorldMap(mapDescription));
            this.scenes.add(new Scene(new GridPane(),1280,860));
        }
        for(RectangularWorldMap map:maps){
            this.mapLords.add(new MapLord(map));
        }
        for(MapLord lord:this.mapLords){
            lord.prepareMap(nOfAnimals,nOfPlants);
        }
    }
    public void runSimulation(){
        while(true){
            for(MapLord lord:this.mapLords){
                try {
                    lord.runEra();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
