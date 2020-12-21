package org.myproject;

import java.util.ArrayList;
import java.util.List;

public class AnimalsAncestors {
    private final List<Animal> kids;
    public AnimalsAncestors(){
        this.kids=new ArrayList<>();
    }
    public void addKid(Animal animal){
        this.kids.add(animal);
    }
    public int getKidsNumber(){
        return this.kids.size();
    }
    public int getNumberOfAncestors(){
        int numOfAncestors=this.getKidsNumber();
        for(Animal animal:this.kids){
            numOfAncestors+=animal.getAncestors().getNumberOfAncestors();
        }
        return numOfAncestors;
    }
}
