package org.myproject;

public class MapLord {
    public RectangularWorldMap map;
    public MapLord(RectangularWorldMap map){ this.map=map;}
    int nOfAnimals;
    int nOfPlants;
    public void prepareMap(int nOfAnimals,int nOfPlants){
        this.nOfAnimals=nOfAnimals;
        this.nOfPlants=nOfPlants;
        for(int i=0;i<nOfAnimals;i++){
            this.map.addRandomAnimal();
        }
        for(int i=0;i<nOfPlants;i++){
            this.map.addRandomPlant();
        }
    }
    public void runEra() throws CloneNotSupportedException {
//        System.out.println("moje zwierzaki to");
//        System.out.println(this.map.Animals.asMap());
        this.map.deleteDeadAnimals();
        this.map.rotateAndMoveAnimals();
        this.map.feedAnimals();
        this.map.breedAnimals();
        this.map.addPlants(this.nOfPlants);
    }
}
