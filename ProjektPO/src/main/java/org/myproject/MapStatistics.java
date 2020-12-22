package org.myproject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MapStatistics {
    private final RectangularWorldMap map;
    private int deadAnimalsSummaryAge;
    private int numberOfDeadAnimals;
    public MapStatistics(RectangularWorldMap map){
        this.map=map;
        this.deadAnimalsSummaryAge=0;
        this.numberOfDeadAnimals=0;
    }
    public int getNumberOfAnimals(){
        return this.map.getAnimals().keys().size();
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
    public double getAverageNumberOfKids(){
        Collection<Animal>animals=this.map.getAnimals().values();
        if(animals.size()==0){
            return 0;
        }
        double sum=0;
        for(Animal animal:animals){
            sum+=animal.getAncestors().getKidsNumber();
        }
        return sum/animals.size();
    }
    public void getDominatingAnimals(){
        List<Integer> genDom=this.getDominatingGenotype();
        for(Object a:this.map.getGenotypes().get(genDom)){
            ((Animal) a).getRepresentation().showAsDominant();
        }
    }
    public void closeDominatingAnimals(){
        List<Integer> genDom=this.getDominatingGenotype();
        for(Animal a:this.map.getGenotypes().get(genDom)){
            a.getRepresentation().endDominant();
        }
    }
    public List<Integer> getDominatingGenotype(){
        int max=0;
        List<Integer> dominant = new ArrayList<>();
        for(List<Integer> genes:this.map.getGenotypes().keySet()){
            if(this.map.getGenotypes().get(genes).size()>max){
                max=this.map.getGenotypes().get(genes).size();
                dominant=genes;
            }
        }
        return dominant;
    }
    public void addDeadAnimal(Animal animal){
        this.deadAnimalsSummaryAge+=animal.getAge();
        this.numberOfDeadAnimals+=1;
    }
    public double getAverageDeadAnimalAge(){
        if(this.numberOfDeadAnimals==0){
            return 0;
        }else {
            return this.deadAnimalsSummaryAge / this.numberOfDeadAnimals;
        }
    }
}
