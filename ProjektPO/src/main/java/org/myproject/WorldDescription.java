package org.myproject;

public class WorldDescription {
    public int mapWidth;
    public int mapHeight;
    public int startEnergy;
    public int moveEnergy;
    public int plantEnergy;
    public double jungleRatio;
    public WorldDescription(){

    }
    public WorldDescription(int mapWidth,int mapHeight,int startEnergy,int moveEnergy,int plantEnergy,int jungleRatio){
        this.mapWidth=mapWidth;
        this.mapHeight=mapHeight;
        this.startEnergy=startEnergy;
        this.moveEnergy=moveEnergy;
        this.plantEnergy=plantEnergy;
        this.jungleRatio=jungleRatio;
    }

    @Override
    public String toString() {
        return "WorldDescription{" +
                "mapWidth=" + mapWidth +
                ", mapHeight=" + mapHeight +
                ", startEnergy=" + startEnergy +
                ", moveEnergy=" + moveEnergy +
                ", plantEnergy=" + plantEnergy +
                ", jungleRatio=" + jungleRatio +
                '}';
    }
}
