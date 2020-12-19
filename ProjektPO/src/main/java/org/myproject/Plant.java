package org.myproject;

public class Plant implements IMapElement{
    public Vector2d position;
    public int plantEnergy;
    public Plant(Vector2d location,int EnergyAmount){
        this.position=location;
        this.plantEnergy=EnergyAmount;
    }
    @Override
    public Vector2d getPosition(){
        return this.position;
    }
    @Override
    public String toString(){
        return "*";
    }
    @Override
    public void move(MoveDirection dir) {
        return;
    }

}