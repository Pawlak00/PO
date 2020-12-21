package org.myproject;

public class MapLord {
    private final RectangularWorldMap map;
    public MapLord(RectangularWorldMap map){ this.map=map;this.statistics=new MapLordStatistics(this);}
    private int  nOfAnimals;
    private int nOfPlants;
    private final MapLordStatistics statistics;
    private int numberOfEras;
    public void prepareMap(int nOfAnimals,int nOfPlants){
        this.nOfAnimals=nOfAnimals;
        this.numberOfEras=0;
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
        this.numberOfEras++;
        this.map.deleteDeadAnimals();
        this.map.rotateAndMoveAnimals();
        this.map.feedAnimals();
        this.map.breedAnimals();
        this.map.addPlants(this.nOfPlants);
        this.statistics.updateStatistics();
    }
    public RectangularWorldMap getMap() {
        return map;
    }
    public int getNumberOfEras(){
        return this.numberOfEras;
    }
    public MapLordStatistics getStatistics(){
        return this.statistics;
    }
}
