package org.myproject;

public class MapLordStatistics {
    public double numOfAnimals;
    public double numOfPlants;
    public double avEnergyLevel;
    public double avLifespan;
    public double avKidsNumber;
    public MapLord mapLord;
    public MapLordStatistics(MapLord mapLord){
        this.numOfAnimals=0;
        this.numOfPlants=0;
        this.avEnergyLevel=0.0;
        this.avLifespan=0.0;
        this.avKidsNumber=0;
        this.mapLord=mapLord;
    }
    public void updateStatistics(){
        this.numOfAnimals+=this.mapLord.getMap().getStatistics().getNumberOfAnimals();
        this.numOfAnimals/=this.mapLord.getNumberOfEras();
        this.numOfPlants+=this.mapLord.getMap().getStatistics().getNumberOfPlants();
        this.numOfPlants/=this.mapLord.getNumberOfEras();
        this.avEnergyLevel+=this.mapLord.getMap().getStatistics().getAverageEnergyLevel();
        this.avEnergyLevel/=this.mapLord.getNumberOfEras();
        this.avLifespan+=this.mapLord.getMap().getStatistics().getAverageLifespan();
        this.avLifespan/=this.mapLord.getNumberOfEras();
        this.avKidsNumber+=this.mapLord.getMap().getStatistics().getAverageNumberOfKids();
        this.avKidsNumber/=this.mapLord.getNumberOfEras();
    }
    @Override
    public String toString(){
        return "number of animals: "+this.numOfAnimals+"\n"+
                " number of plants: "+this.numOfPlants+"\n"+
                " average energy level "+this.avEnergyLevel+"\n"+
                " average lifespan "+this.avLifespan+"\n"+
                " average kids number: "+this.avKidsNumber+"\n";
    }
}
