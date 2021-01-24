    package org.myproject1;

    import javafx.scene.layout.Pane;

    import java.util.HashMap;
    import java.util.Map;

    public class Maze {
        private Vector2d dimensions;
        private Map<Vector2d,MazeField>Maze;
        private MazeCanvas MazeRepresentation;
        private MazeSolver mazeSolver;
        private MazeGenerator mazeGenerator;
        private Pane Canvas;
        private boolean isWritable;
        Vector2d player;
        public Maze(Vector2d dimensions,Pane Canvas){
            System.out.println("tworze nowy labirynt");
            this.dimensions=dimensions;
            this.MazeRepresentation=new MazeCanvas(dimensions, Canvas);
            this.mazeSolver=null;
            this.mazeGenerator=null;
            this.Canvas=Canvas;
            this.Maze=new HashMap<>();
            this.isWritable=false;
            this.player=new Vector2d(1,1);
        }
        public MazeSolver getMazeSolver(){
            return this.mazeSolver;
        }
        //generates a maze to solve and put it on the canvas
        public void generateMaze(){
            this.isWritable=true;

            this.Maze=mazeGenerator.generateMaze(this.dimensions,this);
            System.out.println(this.isWritable);
        }
        //solve maze using algorithm
        public void solveMaze()  {
            if(this.isWritable==false) {
                for(int i=0;i<= dimensions.x;i++){
                    for(int j=0;j<= dimensions.y;j++){
                        if(!Maze.containsKey(new Vector2d(i,j))){
                            Maze.put(new Vector2d(i,j),new MazeField(new Vector2d(i,j),this,1));
                        }
                    }
                }
            }
            Maze.put(new Vector2d(dimensions.x-1, dimensions.y-1),new MazeField(new Vector2d(dimensions.x-1, dimensions.y-1 ),this,1));
            Maze.get(new Vector2d(dimensions.x-1, dimensions.y-1)).getRepresentation().setRepresentationColour("GREEN");
            this.mazeSolver.solveMaze(Maze, this.dimensions,this.player);
        }

        public void setSize(Vector2d dimensions) {
            this.dimensions=dimensions;
        }
        public void setMazeSolver(String AlgorithmName){
            MazeSolverFactory Solver=new MazeSolverFactory();
            this.mazeSolver=Solver.getSolver(AlgorithmName);
        }

        public void setMazeGenerator(String algorithmName) {
            MazeGeneratorFactory MazeGenFac=new MazeGeneratorFactory();
            mazeGenerator=MazeGenFac.getGenerator(algorithmName,this.dimensions);
        }

        public Pane getCanvas() {
            return this.Canvas;
        }
        public void clearCanvas(){
            this.isWritable=true;
            System.out.println(this.Canvas.getChildren());
            this.Canvas.getChildren().clear();
            this.Maze.clear();
            System.out.println(this.Canvas.getChildren());
            this.isWritable=false;
            this.player=new Vector2d(1,1);
        }
        public Vector2d getDimensions() {
            return this.dimensions;
        }

        public void addWallCell(Vector2d position) {
            if(this.isWritable==false) {
                if(position.x<= dimensions.x && position.y<= dimensions.y && position.x>=0 && position.y>=0) {
                    System.out.println("dodaje nowe pole " + position);
                    MazeField newObstacle = new MazeField(position, this, 0);
                    Maze.put(position, newObstacle);
                }
            }
        }

        public void movePlayer(Vector2d transition) {
            Vector2d newPos=this.player.add(transition);
            System.out.println(newPos);
            if(newPos.is_inside(dimensions) && Maze.get(newPos).getWall()!=0){
                this.Maze.get(player).getRepresentation().setRepresentationColour("YELLOW");
                player=newPos;
                this.Maze.get(player).getRepresentation().setRepresentationColour("RED");
            }
        }
        public void makeNewPlayer(){
            this.isWritable=false;
            for(int i=0;i<= dimensions.x;i++){
                for(int j=0;j<= dimensions.y;j++){
                    if(!Maze.containsKey(new Vector2d(i,j))){
                        Maze.put(new Vector2d(i,j),new MazeField(new Vector2d(i,j),this,1));
                    }
                }
            }
            this.Maze.get(player).getRepresentation().setRepresentationColour("RED");
        }
    }
