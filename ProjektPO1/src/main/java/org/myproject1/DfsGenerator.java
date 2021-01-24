package org.myproject1;

import javafx.scene.layout.Pane;

import java.util.*;

public class DfsGenerator implements MazeGenerator{
    @Override
    public Map<Vector2d,MazeField> generateMaze(Vector2d dimensions, Maze maze) {
        System.out.println("generowanie rekurencyjne");
        Map<Vector2d,MazeField>map=new HashMap<>();
        MazeField fieldStart=new MazeField(new Vector2d(1,1),maze,  0.5);
        map.replace(new Vector2d(1,1),fieldStart);
        Stack<Vector2d> stack=new Stack();
        Map<Vector2d,Boolean>visited=new HashMap<>();
        stack.add(new Vector2d(1,1));
        visited.put(new Vector2d(1,1),Boolean.TRUE);
        while (!stack.isEmpty()){
            if(stack.size()>1000000){
                break;
            }
            Vector2d pos=stack.pop();
            System.out.println(pos);
            List<Vector2d> Neighbours=new ArrayList<>();
            if(pos.x+2<dimensions.x && pos.y< dimensions.y && !visited.containsKey(new Vector2d(pos.x+2,pos.y))){
                Neighbours.add(new Vector2d(pos.x+2,pos.y));
            }
            if(pos.x-2>0 && pos.y< dimensions.y && !visited.containsKey(new Vector2d(pos.x-2,pos.y))){
                Neighbours.add(new Vector2d(pos.x-2,pos.y));
            }
            if(pos.x<dimensions.x && pos.y+2< dimensions.y && !visited.containsKey(new Vector2d(pos.x,pos.y+2))){
                Neighbours.add(new Vector2d(pos.x,pos.y+2));
            }
            if(pos.x<dimensions.x && pos.y-2>0 && !visited.containsKey(new Vector2d(pos.x,pos.y-2))){
                Neighbours.add(new Vector2d(pos.x,pos.y-2));
            }
            System.out.println(Neighbours.size());
            if(Neighbours.size()>0) {
                stack.add(pos);
                Random rd=new Random();
                Vector2d neighbour=Neighbours.get(rd.nextInt(Neighbours.size()));
                visited.put(new Vector2d((pos.x+ neighbour.x)/2,(pos.y+ neighbour.y)/2),Boolean.TRUE);
                visited.put(neighbour,Boolean.TRUE);
                stack.add(neighbour);
            }
        }
        for(int i=0;i<= dimensions.x;i++){
            for(int j=0;j<= dimensions.y;j++){
                Vector2d pos=new Vector2d(i,j);
                if(!visited.containsKey(pos)){
                    MazeField mazeField=new MazeField(pos,maze,0);
                    map.put(pos,mazeField);
                }
            }
        }
        for(Vector2d pos:visited.keySet()){
            MazeField mazeField=new MazeField(pos,maze,1);
            map.put(pos,mazeField);
        }

        return map;
    }
}
