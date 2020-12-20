package org.myproject;

import java.util.*;

public class Jungle {
    private int width;
    private int height;
    private Vector2d upperLeft;
    private Vector2d lowerRight;
    private RectangularWorldMap map;
    private Map<Vector2d,Vector2d>availableFields;
    private Map<Vector2d,Plant>plants;
    public Jungle(Vector2d dimensions, RectangularWorldMap map){
        System.out.println(dimensions);
        this.width=(int) Math.min(dimensions.x,map.getMapWidth());
        this.height= (int) Math.min(dimensions.y,map.getMapHeight());
        this.map=map;
        this.availableFields=new HashMap<>();
        System.out.println((map.getMapWidth()/2)+" " +(this.width/2));
        this.upperLeft=new Vector2d((int)((map.getMapWidth()/2)-(this.width/2)),
                (int)((map.getMapHeight()/2)-(this.height/2)));
        this.lowerRight=new Vector2d((int)((map.getMapWidth()/2)+(this.width/2)),
                (int)((map.getMapHeight()/2)+(this.height/2)));
        System.out.println(upperLeft+" prostokat "+lowerRight);
        for(int i=upperLeft.x;i<=lowerRight.x;i++){
            for(int j=upperLeft.y;j<lowerRight.y;j++){
                System.out.println(i+" "+j);
                availableFields.put(new Vector2d(i,j),new Vector2d(i,j));
            }
        }
        this.plants=new HashMap<>();
    }
    public boolean belongsToJungle(Vector2d position){
        return this.availableFields.containsKey(position) || this.plants.containsKey(position);
    }
    public void addRandomPlant(){
        System.out.println(availableFields.size());
        if(availableFields.size()>0) {
            List<Vector2d> availablePositions = new ArrayList<>(availableFields.values());
            Random rd = new Random();
            Vector2d plantPosition = availablePositions.get(rd.nextInt(availablePositions.size()));
            Plant newPlant = new Plant(this.map, plantPosition, this.map.plantEnergy, this.map.getCanvas());
            this.plants.put(plantPosition, newPlant);
            this.availableFields.remove(plantPosition);
        }
    }
}
