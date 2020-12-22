package org.myproject;

import java.util.*;

public class Jungle {
    private final int width;
    private final int height;
    private final Vector2d upperLeft;
    private final Vector2d lowerRight;
    private final RectangularWorldMap map;
    private final Map<Vector2d,Vector2d>availableFields;
    public Jungle(Vector2d dimensions, RectangularWorldMap map){
        this.width= Math.min(dimensions.x,map.getMapWidth());
        this.height= (int) Math.min(dimensions.y,map.getMapHeight());
        this.map=map;
        this.availableFields=new HashMap<>();
        this.upperLeft=new Vector2d((map.getMapWidth()/2)-(this.width/2),
                (int)((map.getMapHeight()/2)-(this.height/2)));
        this.lowerRight=new Vector2d((map.getMapWidth()/2)+(this.width/2),
                (int)((map.getMapHeight()/2)+(this.height/2)));
        for(int i=upperLeft.x;i<=lowerRight.x;i++){
            for(int j=upperLeft.y;j<lowerRight.y;j++){
                availableFields.put(new Vector2d(i,j),new Vector2d(i,j));
            }
        }
    }
    public boolean belongsToJungle(Vector2d position){
        return this.availableFields.containsKey(position) || this.map.getPlants().containsKey(position);
    }
    public void addRandomPlant(){
        if(availableFields.size()>0) {
            List<Vector2d> availablePositions = new ArrayList<>(availableFields.values());
            Random rd = new Random();
            Vector2d plantPosition = availablePositions.get(rd.nextInt(availablePositions.size()));
            Plant newPlant = new Plant(this.map, plantPosition, this.map.getPlantEnergy(), this.map.getCanvas());
            this.map.addPlant(newPlant);
            this.availableFields.remove(plantPosition);
        }
    }
}
