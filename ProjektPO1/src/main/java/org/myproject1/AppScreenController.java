package org.myproject1;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppScreenController {
    @FXML Pane Canvas;
    private MazeEngine Engine;
    MyTimer timer;
    boolean movable;
    public void handleChangeSizeEvent(ActionEvent event) {
        System.out.println("zmieniono rozmiar planszy");
        Stage changeSizeStage=new Stage();
        changeSizeStage.setTitle("Wprowadz rozmiary planszy");
        TextField height=new TextField();
        TextField width=new TextField();
        Button acceptSize=new Button();
        acceptSize.setText("Accept");
        VBox changeSizeVbox=new VBox(height,width,acceptSize);
        Scene changeSizeScene=new Scene(changeSizeVbox);
        changeSizeStage.setScene(changeSizeScene);
        acceptSize.setOnAction((event1 -> {
            if(Integer.parseInt(width.getText())>0 && Integer.parseInt(width.getText())<1000){
                if(Integer.parseInt(height.getText())>0  && Integer.parseInt(height.getText())<1000){
                    Engine.changeMazeSize(new Vector2d(Integer.parseInt(width.getText()),Integer.parseInt(height.getText())));
                    changeSizeStage.close();
                }
            }
        }));
        changeSizeStage.show();
    }

    public void handleKeyPressedEvent(KeyEvent keyEvent) {
        if(this.movable){
            System.out.println(keyEvent.getCode());
            switch (keyEvent.getCode()){
                case W:
                    this.Engine.getMaze().movePlayer(new Vector2d(0,-1));
                    break;
                case S:
                    this.Engine.getMaze().movePlayer(new Vector2d(0,1));
                    break;
                case A:
                    this.Engine.getMaze().movePlayer(new Vector2d(-1,0));
                    break;
                case D:
                    this.Engine.getMaze().movePlayer(new Vector2d(1,0));
            }
        }
    }


    private class MyTimer extends AnimationTimer{

        @Override
        public void handle(long l) {
            int tmp=Engine.getMaze().getMazeSolver().solveStep();
            if(tmp==1){
                this.stop();
            }
        }
    }
    public void handleDfsSolveEvent(ActionEvent event) {
        Engine.changeMazeSolver("DFS");
    }

    public void handleBfsSolveEvent(ActionEvent event) {
        Engine.changeMazeSolver("BFS");
    }

    public void handleASolveEvent(ActionEvent event) {
        Engine.changeMazeSolver("A*");
    }

    public void handleDfsGenEvent(ActionEvent event) {
        Engine.changeMazeGenerator("DfsGenerator");
    }

    public void handleRecursiveGenEvent(ActionEvent event) {
        Engine.changeMazeGenerator("RECURSIVE_DIVISION");
    }
    public void handleBacktrackerGenEvent(ActionEvent event) {
        Engine.changeMazeGenerator("RecursiveBacktracker");
    }

    public void handleResetPlaneEvent(ActionEvent event) {
        this.Engine.resetMaze();
    }

    public void handleRunAlgorithmEvent(ActionEvent event) {
        Engine.getMaze().solveMaze();
        timer.start();
    }
    public void handlePlayAnimationEvent(){
        timer.start();
    }
    public void handlePauseAnimationEvent(){
        timer.stop();
    }
    public void handleDIYEvent(ActionEvent event) {
        this.Engine.getMaze().makeNewPlayer();
        this.movable=true;
    }

    public void handleGenMazeEvent(ActionEvent event) {
        Engine.getMaze().clearCanvas();
        Engine.genMaze();
    }
    public void initData() {
        this.movable=false;
        this.timer=new MyTimer();
        this.Engine=new MazeEngine(new Vector2d(30,30),Canvas);
        Canvas.setStyle("-fx-border-color: black");
    }

    public void handleAddObstacleEvent(MouseEvent mouseEvent) {
        Engine.getMaze().addWallCell(new Vector2d((int)(mouseEvent.getX()/(Canvas.getWidth()/Engine.getMaze().getDimensions().x)),(int)(mouseEvent.getY()/(Canvas.getHeight()/Engine.getMaze().getDimensions().y))));
    }
}
