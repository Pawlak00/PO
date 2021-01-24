package org.myproject1;

import javafx.scene.layout.Pane;

import java.util.*;

public class RecursiveDivision implements MazeGenerator{
    //dopracowac
    public void recursiveGenerator(Vector2d left_up,Vector2d right_down,Map<Vector2d,MazeField>map, Maze maze,Vector2d holes){
        int width= (int)(right_down.x- left_up.x-2);
        int height= (int)(right_down.y- left_up.y-2);
        Random rd=new Random();
        System.out.println("wymiary planszy to "+left_up+" "+right_down);
        if(width<2 || height<2){
            return;
        }
            System.out.println("scaina rownolega do OY");
            int wx = (int)left_up.x + 2 + rd.nextInt(width - 1);
            int hole_y = (int)left_up.y + 1 + rd.nextInt(height );
            if(wx==holes.x){
                System.out.println("################################################################################"+holes);
                return;
            }
            System.out.println("sciana w "+wx+" i dziura w  "+hole_y);
            recursiveGenerator(left_up,new Vector2d(wx, right_down.y),map,maze,new Vector2d(0,hole_y));
            recursiveGenerator(new Vector2d(wx, left_up.y),right_down,map,maze,new Vector2d(0,hole_y));
            for (int y = (int)left_up.y+1 ; y < right_down.y; y++) {
                if (y != hole_y) {
                    map.put(new Vector2d(wx, y), new MazeField(new Vector2d(wx, y), maze, 0));
                }
            }
    }
    @Override
    public Map<Vector2d,MazeField> generateMaze(Vector2d dimensions, Maze maze) {
        Map<Vector2d,MazeField>map=new HashMap<>();
        for(int i= 0;i<= dimensions.y;i++){
            map.put(new Vector2d(i,0),new MazeField(new Vector2d(i,0),maze,0));
            map.put(new Vector2d(i, dimensions.y),new MazeField(new Vector2d(i,dimensions.y),maze,0));
        }
        for(int i= 0;i<= dimensions.x;i++){
            map.put(new Vector2d(0,i),new MazeField(new Vector2d(0,i),maze,0));
            map.put(new Vector2d(dimensions.x, i),new MazeField(new Vector2d(dimensions.x, i),maze,0));
        }
        recursiveGenerator(new Vector2d(0,0),dimensions,map,maze,new Vector2d(0,0));
        for(int i=0;i<=dimensions.x;i++){
            for(int j=0;j<=dimensions.y;j++){
                if(!map.containsKey(new Vector2d(i,j))){
                    Vector2d v=new Vector2d(i,j);
                    map.put(v,new MazeField(v,maze,1));
                }
            }
        }
        return map;
    }

}
