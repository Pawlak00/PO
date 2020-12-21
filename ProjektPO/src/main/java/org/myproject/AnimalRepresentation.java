package org.myproject;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class AnimalRepresentation {
    @FXML private Pane canvas;
    private Circle representation;
    private Vector2d location;
    private Animal animal;

    public AnimalRepresentation(Animal animal){
        this.location=animal.getPosition();
        this.animal=animal;
        this.canvas=animal.getCanvas();
        this.representation=new Circle();
        this.representation.setRadius(this.canvas.getHeight()/this.animal.getMap().mapHeight/2);
        this.representation.setCenterX(this.location.x*this.canvas.getWidth()/this.animal.getMap().getMapWidth()+this.representation.getRadius());
        this.representation.setCenterY(this.location.y*this.canvas.getHeight()/this.animal.getMap().getMapHeight()+this.representation.getRadius());
        this.canvas.getChildren().add(representation);
        this.representation.setFill(Color.RED);
        this.representation.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage stage=new Stage();
                Label DNA=new Label("DNA: "+animal.getGenes().getGeneCodeString());
                Label kidsNumber=new Label("Number of kids: "+animal.getAncestors().getKidsNumber());
                Label ancestorsNumber=new Label("Number of ancestors: "+animal.getAncestors().getNumberOfAncestors());
                VBox column=new VBox(DNA,kidsNumber,ancestorsNumber);
                Scene scene=new Scene(column);
                stage.setScene(scene);
                stage.show();
            }
        });
    }
    public void removeAnimalRepresentation(){
        this.canvas.getChildren().remove(representation);
    }
    public void moveTo(Vector2d newLocation){
        this.representation.setCenterX(newLocation.x*this.canvas.getWidth()/this.animal.getMap().getMapWidth());
        this.representation.setCenterY(newLocation.y*this.canvas.getHeight()/this.animal.getMap().getMapHeight());
        this.canvas.getChildren().add(representation);
    }

}
