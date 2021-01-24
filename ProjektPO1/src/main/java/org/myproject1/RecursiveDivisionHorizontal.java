package org.myproject1;

import javafx.scene.layout.Pane;

import java.util.*;

public class RecursiveDivisionHorizontal implements MazeGenerator{
    @Override
    public Map<Vector2d,MazeField> generateMaze(Vector2d dimensions, Maze maze) {
        Map<Vector2d,MazeField>map=new HashMap<>();
        Random rd=new Random();
        for(int i= 0;i<= dimensions.y;i++){
            for(int j= 0;j<= dimensions.x;j++){
                map.put(new Vector2d(i,j),new MazeField(new Vector2d(i,j),maze,rd.nextInt(2)));
            }
        }
        return map;
    }

}
