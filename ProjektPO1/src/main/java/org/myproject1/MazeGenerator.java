package org.myproject1;

import javafx.scene.layout.Pane;

import java.util.Map;

interface MazeGenerator {
    Map<Vector2d,MazeField> generateMaze(Vector2d dimensions, Maze maze);
}
