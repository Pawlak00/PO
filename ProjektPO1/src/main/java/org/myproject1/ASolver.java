package org.myproject1;

import java.util.*;

public class ASolver implements MazeSolver{
    private class PqMember{
        Vector2d position;
        int key;
        PqMember(Vector2d pos,int key){
            this.key=key;
            this.position=pos;
        }
    }
    private PriorityQueue<PqMember>PQ;
    private Map<Vector2d,Vector2d>Parent;
    private Map<Vector2d,Integer>Cost;
    private Map<Vector2d,MazeField>maze;
    private Vector2d dimensions;
    private Vector2d StartPos;
    private Vector2d end;
    private int manhattanDistance(Vector2d P1, Vector2d P2){
        return Math.abs((P1.x-P2.x))+Math.abs((P1.y-P2.y));
    }
    @Override
    public int solveStep(){
        if(PQ.isEmpty()){
            return 1;
        }
        PqMember current=PQ.poll();
        for(PqMember a:PQ){
            System.out.println(a.position+" "+a.key+" "+manhattanDistance(a.position,end));
        }
        System.out.println(current.position+" "+current.key+" "+manhattanDistance(current.position, end));
        if(current.position.equals(new Vector2d(dimensions.x-1,dimensions.y-1))){
            Vector2d parent_v=Parent.get(current.position);
            Vector2d act_v=current.position;
            while(!parent_v.equals(act_v)){
                maze.get(act_v).getRepresentation().setRepresentationColour("YELLOW");
                act_v=parent_v;
                parent_v=Parent.get(act_v);
            }
            maze.get(act_v).getRepresentation().setRepresentationColour("YELLOW");
            return 1;
        }
        maze.get(current.position).getRepresentation().setRepresentationColour("GREEN");
        List<Vector2d>neighbours=new ArrayList<>();
        neighbours.add(new Vector2d(current.position.x-1,current.position.y));
        neighbours.add(new Vector2d(current.position.x+1,current.position.y));
        neighbours.add(new Vector2d(current.position.x,current.position.y-1));
        neighbours.add(new Vector2d(current.position.x,current.position.y+1));
        for(Vector2d next:neighbours) {
            if(next.is_inside(dimensions) && maze.get(next).getWall()==1) {
                int newCost = (int) (Cost.get(current.position) + manhattanDistance(current.position, next));
                if(!Cost.containsKey(next)){
                    Cost.put(next,newCost);
                    PQ.add(new PqMember(next,newCost+manhattanDistance(next,end)));
                    Parent.put(next, current.position);
                }
                if(newCost<Cost.get(next)){
                    Cost.replace(next,newCost);
                    PQ.add(new PqMember(next,newCost+manhattanDistance(next,end)));
                    Parent.put(next, current.position);
                }
            }
        }
        return 2;
    }
    public void solveMaze(Map<Vector2d, MazeField> maze,Vector2d dimensions,Vector2d StartPos) {
        Vector2d end=new Vector2d(dimensions.x-1,dimensions.y-1);
        this.PQ=new PriorityQueue<>(new Comparator<PqMember>() {

            @Override
            public int compare(PqMember e1, PqMember e2) {
                if(e1.key==e2.key){
                    return Integer.compare(manhattanDistance(e1.position, end),manhattanDistance(e2.position, end));
                }
                return Integer.compare(e1.key,e2.key);
            }
        });
        this.PQ.add(new PqMember(StartPos,0));
        this.Parent=new HashMap<>();
        this.Cost=new HashMap<>();
        this.Parent.put(StartPos,StartPos);
        this.Cost.put(StartPos,0);
        this.maze=maze;
        this.dimensions=dimensions;
        this.StartPos=StartPos;
        this.end=new Vector2d(dimensions.x-1,dimensions.y-1);
    }
}
