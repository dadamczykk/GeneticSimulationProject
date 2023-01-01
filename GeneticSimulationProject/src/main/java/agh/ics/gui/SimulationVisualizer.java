package agh.ics.gui;

import agh.ics.ooproject.BehaviourType;
import agh.ics.ooproject.MutationType;
import agh.ics.ooproject.PlantType;
import agh.ics.ooproject.WorldTypes;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class SimulationVisualizer {
    private Stage stage;
    private Scene scene;

    private Label counterLabel;

    public SimulationVisualizer(){

        this.counterLabel = new Label("init");
        this.scene = new Scene(counterLabel);
        this.stage = new Stage();
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void updateScene(int value){
        this.counterLabel.setText(String.valueOf(value));
    }

}
