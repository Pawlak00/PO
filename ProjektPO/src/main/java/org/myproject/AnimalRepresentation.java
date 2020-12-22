package org.myproject;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class AnimalRepresentation {
    @FXML private final Pane canvas;
    private final Circle representation;
    private final Vector2d location;
    private final Animal animal;
    private int maxEnergy;
    public AnimalRepresentation(Animal animal,int maxEnergy){
        this.maxEnergy=maxEnergy;
        this.location=animal.getPosition();
        this.animal=animal;
        this.canvas=animal.getCanvas();
        this.representation=new Circle();
        this.representation.setRadius(this.canvas.getHeight()/this.animal.getMap().getMapHeight()/2);
        this.representation.setCenterX(this.location.x*this.canvas.getWidth()/this.animal.getMap().getMapWidth()+this.representation.getRadius());
        this.representation.setCenterY(this.location.y*this.canvas.getHeight()/this.animal.getMap().getMapHeight()+this.representation.getRadius());
        this.canvas.getChildren().add(representation);
        this.representation.setFill(Color.rgb(this.getColor(),0,0));
        this.representation.setOnMousePressed(mouseEvent -> {
            Stage stage=new Stage();
            Label DNA=new Label("DNA: "+animal.getGenes().getGeneCodeString());
            Label kidsNumber=new Label("Number of kids: "+animal.getAncestors().getKidsNumber());
            Label ancestorsNumber=new Label("Number of ancestors: "+animal.getAncestors().getNumberOfAncestors());
            Label deathAge=new Label();
            if(animal.getDeathAge()==0){
                deathAge.setText("Death era: Still alive");
            }else{
                deathAge.setText("Death era: "+animal.getDeathAge());
            }
            VBox column=new VBox(DNA,kidsNumber,ancestorsNumber,deathAge);
            Scene scene=new Scene(column);
            stage.setScene(scene);
            stage.show();
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
    public int getColor(){
        return this.animal.getEnergyLevel()*255/Math.max(this.maxEnergy,this.animal.getEnergyLevel());
    }
    public void showAsDominant(){
        this.representation.setFill(Color.BLUE);
    }
    public void endDominant(){
        this.representation.setFill(Color.rgb(this.getColor(),0,0));
    }
}
