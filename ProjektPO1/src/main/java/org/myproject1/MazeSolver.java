package org.myproject1;

import java.util.Map;

public interface MazeSolver {
    int solveStep();
    void solveMaze(Map<Vector2d, MazeField> maze,Vector2d dimensions,Vector2d StartPos) ;
}
