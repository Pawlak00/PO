package org.myproject;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {
    public List<WorldDescription> mapsDesc;
    public List<MapLord>mapLords;
    public List<RectangularWorldMap>maps;
    public SimulationEngine(List<WorldDescription> mapsDescs){
        this.mapsDesc=mapsDescs;
        this.mapLords=new ArrayList<>();
        this.maps=new ArrayList<>();
        System.out.println("ASDASDAS");
    }
    public void prepareSimulation(int nOfAnimals,int nOfPlants){
        for(WorldDescription mapDescription:mapsDesc){
            this.maps.add(new RectangularWorldMap(mapDescription));
        }
        for(RectangularWorldMap map:maps){
            this.mapLords.add(new MapLord(map));
        }
        for(MapLord lord:this.mapLords){
            lord.prepareMap(nOfAnimals,nOfPlants);
        }
    }
    public void runSimulation(){
        int eraCounter=0;
        while(true){
            for(MapLord lord:this.mapLords){
                try {
                    System.out.println(eraCounter);
                    lord.runEra();
                    eraCounter++;
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
