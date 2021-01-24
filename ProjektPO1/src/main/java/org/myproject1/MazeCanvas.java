package org.myproject1;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MazeCanvas {
    private Vector2d dimensions;
    private Pane Canvas;
    private Map<Vector2d, MazeFieldRepresentation> FieldsRepresentation;
    public MazeCanvas(Vector2d dimensions, Pane canvas){
        this.dimensions=dimensions;
        this.Canvas=canvas;
    }

}
