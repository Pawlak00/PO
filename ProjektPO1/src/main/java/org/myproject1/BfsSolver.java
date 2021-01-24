package org.myproject1;

import java.util.*;

public class BfsSolver implements MazeSolver{
    private Map<Vector2d, MazeField> maze;
    private Vector2d dimensions;
    private Map<Vector2d,Vector2d>parent;
    private Queue<Vector2d>Q;
    private Map<Vector2d,Integer>color;
    @Override
    public int solveStep(){
        if(Q.isEmpty()){
            return 1;
        }
        Vector2d u=Q.poll();
        maze.get(u).getRepresentation().setRepresentationColour("BLUE");

        if(u.equals(new Vector2d(dimensions.x-1,dimensions.y-1))){
            findPath(u, parent, maze);
            return 1;
        }
        List<Vector2d>neighbours=new ArrayList<>();
        neighbours.add(new Vector2d(u.x-1,u.y));
        neighbours.add(new Vector2d(u.x+1,u.y));
        neighbours.add(new Vector2d(u.x,u.y+1));
        neighbours.add(new Vector2d(u.x,u.y-1));
        for(Vector2d v:neighbours) {
            if (v.is_inside(dimensions) && !color.containsKey(v) && maze.get(v).getWall() == 1) {
                Q.add(v);
                color.put(v, 1);
                parent.put(v, u);
            }
        }
        color.replace(u,2);
        return 2;
    }

    static void findPath(Vector2d u, Map<Vector2d, Vector2d> parent, Map<Vector2d, MazeField> maze) {
        Vector2d parent_v= parent.get(u);
        Vector2d act_v=u;
        while(!parent_v.equals(act_v)){
            maze.get(act_v).getRepresentation().setRepresentationColour("YELLOW");
            act_v=parent_v;
            parent_v= parent.get(act_v);
        }
        maze.get(act_v).getRepresentation().setRepresentationColour("YELLOW");
    }

    @Override
    public void solveMaze(Map<Vector2d, MazeField> maze,Vector2d dimensions,Vector2d StartPos){
        this.maze=maze;
        this.dimensions=dimensions;
        this.Q=new LinkedList<>();
        Q.add(StartPos);
        this.color=new HashMap<>();
        this.parent=new HashMap<>();
        color.put(StartPos,1);
        parent.put(StartPos,StartPos);
    }
}
