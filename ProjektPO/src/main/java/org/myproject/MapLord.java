package org.myproject;

import java.util.ArrayList;
import java.util.List;


public class MapLord {
    private final RectangularWorldMap map;
    public MapLord(RectangularWorldMap map){ this.map=map;}
    private int  nOfAnimals;
    private int nOfPlants;
    public void prepareMap(int nOfAnimals,int nOfPlants){
        this.nOfAnimals=nOfAnimals;
        this.nOfPlants=nOfPlants;
        for(int i=0;i<nOfAnimals;i++){
            this.map.addRandomAnimal();
        }
        for(int i=0;i<nOfPlants/2;i++){
            this.map.addRandomPlant();
            this.map.getJungle().addRandomPlant();
        }
    }
    public void runEra() {
        this.map.deleteDeadAnimals();
        this.map.rotateAndMoveAnimals();
        this.map.feedAnimals();
        this.map.breedAnimals();
        this.map.addPlants(this.nOfPlants);
    }

    public RectangularWorldMap getMap() {
        return map;
    }
}
