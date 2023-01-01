package agh.ics.gui;

import agh.ics.ooproject.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SimulationVisualizer {
    private final GridPane mainGrid = new GridPane();
    private final GridPane fullStats = new GridPane();
    private final GridPane indStats = new GridPane();

    private final Button startPause = new Button("Pause");

    private final ArrayList<Label> fullStatsLabels = new ArrayList<>();

    private final ArrayList<Label> fullStatsNames = new ArrayList<>();

    private final ArrayList<Label> indStatsLabels = new ArrayList<>();

    private final ArrayList<Label> indStatsNames = new ArrayList<>();

//    private final Label day = new Label();
//    private final Label animalCount = new Label();
//    private final Label plantCount = new Label();
//    private final Label bestGenom = new Label();
//    private final Label avgEnergy = new Label();
//    private final Label avgLife = new Label();
//
//    private final Label currGenome = new Label();
//    private final Label whereGenome = new Label();
//    private final Label energyLeft = new Label();
//    private final Label plantsAte = new Label();
//    private final Label childCount = new Label();
//    private final Label daysLived = new Label();
//    private final Label dayOfDeath = new Label();

    private Stage stage;
    private Scene scene;

    private SimulationEngine engine;

    public SimulationVisualizer(ArrayList <Integer> args, SimulationEngine eng){
        int day = 0;
        this.engine = eng;
        fullStatsNames.add(new Label("Day of simulation:"));
        fullStatsNames.add(new Label("Number of animals:"));
        fullStatsNames.add(new Label("Number of plants:"));
        fullStatsNames.add(new Label("Most popular genome"));
        fullStatsNames.add(new Label("Average energy"));
        fullStatsNames.add(new Label("Average time of life"));
        for (int i = 0; i < 6; i++) {
            fullStatsLabels.add(new Label("0"));
            fullStats.add(fullStatsNames.get(i), 0, i);
            fullStats.add(fullStatsLabels.get(i), 1, i);


        }
        indStatsNames.add(new Label("Current genome"));
        indStatsNames.add(new Label("Active part of genome"));
        indStatsNames.add(new Label("Energy"));
        indStatsNames.add(new Label("Plants eaten"));
        indStatsNames.add(new Label("Children count"));
        indStatsNames.add(new Label("Days alive"));
        indStatsNames.add(new Label("Day of death"));
        for (int i = 0; i < 7; i++){
            indStatsLabels.add(new Label("0"));
            indStats.add(indStatsNames.get(i), 0, i);
            indStats.add(indStatsLabels.get(i), 1, i);
        }
        indStats.setGridLinesVisible(true);
        fullStats.setGridLinesVisible(true);
        this.startPause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        VBox stats = new VBox(fullStats, indStats,startPause);
        stats.setSpacing(30);
        HBox all = new HBox(stats, mainGrid);
        all.setSpacing(70);

        for (int x = 0; x < args.get(4); x++){
            mainGrid.getColumnConstraints().addAll(new ColumnConstraints(30));
            for (int y = 0; y < args.get(5); y++){
                Rectangle rect = new Rectangle(20, 20, Color.BLANCHEDALMOND);
                mainGrid.add(rect, x, y);
//                rect.se
                mainGrid.setAlignment(Pos.CENTER);
            }
        }
        for (int y = 0; y < args.get(5);y++){
            mainGrid.getRowConstraints().addAll(new RowConstraints(30));
        }
        mainGrid.setGridLinesVisible(true);

        this.scene = new Scene(all);
        this.stage = new Stage();
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void updateScene(int value){
        this.fullStatsLabels.get(0).setText(String.valueOf(value));
//        this.counterLabel.setMinHeight(300);
//        this.stage.setWidth(300);
    }

}
