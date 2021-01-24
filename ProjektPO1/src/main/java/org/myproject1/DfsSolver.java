package org.myproject1;

import java.util.*;

public class DfsSolver implements MazeSolver{
    private Map<Vector2d, MazeField> maze;
    private Vector2d dimensions;
    private Map<Vector2d,Vector2d>parent;
    private Stack<Vector2d> Q;
    private Map<Vector2d,Integer>color;
    @Override
    public int solveStep(){
        if(Q.isEmpty()){
            return 1;
        }
        Vector2d u=Q.pop();
        if(u.equals(new Vector2d(dimensions.x-1, dimensions.y-1))){
            BfsSolver.findPath(u, parent, maze);
            return 1;
        }
        maze.get(u).getRepresentation().setRepresentationColour("BLUE");
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
    @Override
    public void solveMaze(Map<Vector2d, MazeField> maze,Vector2d dimensions,Vector2d StartPos){
        this.maze=maze;
        this.dimensions=dimensions;
        this.Q=new Stack<>();
        Q.add(StartPos);
        this.color=new HashMap<>();
        this.parent=new HashMap<>();
        color.put(StartPos,1);
        parent.put(StartPos,StartPos);
    }
}
