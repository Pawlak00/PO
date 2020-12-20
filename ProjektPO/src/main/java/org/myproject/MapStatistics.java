package org.myproject;

import java.util.Collection;

public class MapStatistics {
    private int numberOfAnimals;
    private int numberOfPlants;
    private double averageEnergyLevel;
    private double averageLifespan;
    private double averageNumberOfKids;
    private RectangularWorldMap map;
    public MapStatistics(RectangularWorldMap map){
        this.map=map;
    }
    public int getNumberOfAnimals(){
        return this.map.getAnimals().size();
    }
    public int getNumberOfPlants(){
        return this.map.getPlants().size();
    }

    public double getAverageEnergyLevel() {
        Collection<Animal>animals=this.map.getAnimals().values();
        double sum=0;
        if(animals.size()==0){
            return 0;
        }
        for(Animal animal:animals){
            sum+=animal.getEnergyLevel();
        }
        return (sum/animals.size());
    }
    public double getAverageLifespan(){
        Collection<Animal>animals=this.map.getAnimals().values();
        if(animals.size()==0){
            return 0;
        }
        double sum=0;
        for(Animal animal:animals){
            sum+=animal.getAge();
        }

        return (sum/animals.size());
    }
}