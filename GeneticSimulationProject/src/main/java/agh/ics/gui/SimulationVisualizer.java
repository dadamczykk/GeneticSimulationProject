package agh.ics.gui;

import agh.ics.ooproject.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.*;

public class SimulationVisualizer {
    private final GridPane mainGrid = new GridPane();

    final ArrayList<Label> fullStatsLabels = new ArrayList<>();

    private final ArrayList<Label> indStatsLabels = new ArrayList<>();

    private final ArrayList<Integer> initArgs;

    private final Rectangle[][] rectTable;

    private boolean isPaused = false;

    private ElementAnimal followedOne = null;

    int day;

    private static final double minR = 190.0 / 255;
    private static final double minG = 230.0 / 255;
    private static final double minB = 135.0 / 255;

    private static final double maxR = 105.0 / 255;
    private static final double maxG = 6.0 / 255;
    private static final double maxB = 6.0 / 255;

    private static final Color bgColor = Color.rgb(110,255,166);

    private static final Color plantColor = Color.rgb(5,64,1);

    private Stage stage;
    private Scene scene;

    private SimulationEngine engine;

    public SimulationVisualizer(ArrayList <Integer> args, SimulationEngine eng){
        initArgs = args;
        if (args.get(15) == 1){
            System.out.println("zapisywanie do pliku");
        }
        this.day = 0;
        this.engine = eng;

        ArrayList<Label> fullStatsNames = new ArrayList<>();
        fullStatsNames.add(new Label(" Day of simulation:"));
        fullStatsNames.add(new Label(" Number of animals:"));
        fullStatsNames.add(new Label(" Number of plants:"));
        fullStatsNames.add(new Label(" Number of empty tiles:"));
        fullStatsNames.add(new Label(" Most popular genome"));
        fullStatsNames.add(new Label(" Average energy"));
        fullStatsNames.add(new Label(" Average time of life"));

        GridPane fullStats = new GridPane();
        for (int i = 0; i < 7; i++) {
            fullStatsLabels.add(new Label("0"));
            fullStats.add(fullStatsNames.get(i), 0, i);
            fullStats.add(fullStatsLabels.get(i), 1, i);
            fullStats.getRowConstraints().addAll(new RowConstraints(30));
            GridPane.setHalignment(fullStatsLabels.get(i), HPos.CENTER);
            GridPane.setValignment(fullStatsLabels.get(i), VPos.CENTER);
        }
        fullStats.getColumnConstraints().addAll(new ColumnConstraints(150), new ColumnConstraints(70));
        fullStats.setAlignment(Pos.CENTER);

        ArrayList<Label> indStatsNames = new ArrayList<>();
        indStatsNames.add(new Label(" Current genome"));
        indStatsNames.add(new Label(" Active part of genome"));
        indStatsNames.add(new Label(" Energy"));
        indStatsNames.add(new Label(" Plants eaten"));
        indStatsNames.add(new Label(" Children count"));
        indStatsNames.add(new Label(" Days alive"));
        indStatsNames.add(new Label(" Day of death"));
        GridPane indStats = new GridPane();
        for (int i = 0; i < 7; i++){
            indStatsLabels.add(new Label("0"));
            indStats.add(indStatsNames.get(i), 0, i);
            indStats.add(indStatsLabels.get(i), 1, i);
            indStats.getRowConstraints().addAll(new RowConstraints(30));
            GridPane.setHalignment(indStatsLabels.get(i), HPos.CENTER);
            GridPane.setValignment(indStatsLabels.get(i), VPos.CENTER);
        }
        indStats.getColumnConstraints().addAll(new ColumnConstraints(150), new ColumnConstraints(70));
        indStats.setAlignment(Pos.CENTER);


        indStats.setGridLinesVisible(true);
        fullStats.setGridLinesVisible(true);
        Button startPause = new Button("Pause / Play");
        startPause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!isPaused) {
                    isPaused = true;
                    engine.isPaused = true;
                    highlightBest();
                } else {
                    engine.repause();
                    isPaused = false;
                }
            }
        });

        rectTable = new Rectangle[args.get(5)][args.get(4)];
        double cellDim = 750.0 / Math.max(args.get(4), args.get(5));
        mainGrid.setAlignment(Pos.CENTER);
        VBox stats = new VBox(fullStats, indStats, startPause);
        stats.setSpacing(30);
        HBox all = new HBox(stats, mainGrid);
        stats.setAlignment(Pos.CENTER);
        all.setSpacing(70);

        for (int x = 0; x < args.get(4); x++){
            mainGrid.getColumnConstraints().addAll(new ColumnConstraints(cellDim));
            for (int y = 0; y < args.get(5); y++){
                rectTable[y][x] = new Rectangle(cellDim, cellDim);
                rectTable[y][x].setFill(bgColor);
                mainGrid.add(rectTable[y][x], x, y);
                GridPane.setHalignment(rectTable[y][x], HPos.CENTER);
                GridPane.setHalignment(rectTable[y][x], HPos.CENTER);
                int finalY = y;
                int finalX = x;
                rectTable[y][x].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (engine.map.grid[finalY][finalX].animals.size() > 0) {
                            followedOne = engine.map.grid[finalY][finalX].animals.get(0);
                            updateIndStats();
                        }
                    }
                });
            }
        }
        for (int y = 0; y < args.get(5);y++){
            mainGrid.getRowConstraints().addAll(new RowConstraints(cellDim));
        }
        mainGrid.setGridLinesVisible(true);
        all.setAlignment(Pos.CENTER);
        this.scene = new Scene(all);
        this.stage = new Stage();
        this.stage.setScene(scene);
        this.stage.show();
        this.stage.setWidth(scene.getWidth()*1.1);
        this.stage.setHeight(scene.getHeight()*1.1);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                if (engine.isPaused){
                    engine.repause();
                }
                engine.app.threads.get(engine.simulationNumber).interrupt();
            }
        });
    }

    public void updateScene() throws IOException {
        Comparator<ElementAnimal> win = Comparator.comparingInt((ElementAnimal x) -> -x.energy);
        engine.map.animals.sort(win);
        int maxEnergy = engine.map.animals.get(0).energy;
        for (int x = 0; x < initArgs.get(4); x++){
            for (int y = 0; y < initArgs.get(5); y++){
                rectTable[y][x].setFill(Color.BLANCHEDALMOND);

                if (engine.map.grid[y][x].hasGrass){
                    rectTable[y][x].setFill(plantColor);
                }

                if (engine.map.grid[y][x].animals.size() > 0){
                    rectTable[y][x].setFill(lerpRGB(engine.map.sufficientEnergy, engine.map.grid[y][x].animals.get(0).energy));
                }

            }
        }

//        for
        this.updateFullStats();
        this.updateIndStats();
    }

    private Color lerpRGB(int maxVal, int currVal){
        float t = Math.min((float)currVal, (float)maxVal) / (float) maxVal;
//        return Color.color(0, 0, 0);
        if (currVal < 0){
            t = 0;
        }
        return Color.color((minR + (maxR - minR) * t),
                (minG + (maxG - minG) * t),
                (minB + (maxB - minB) * t));

    }

    private void changeOnFocusAnimal(int x, int y){
//        this.followedOne =
    }

    protected void updateFullStats() throws IOException {
        this.day++;
        this.fullStatsLabels.get(0).setText(String.valueOf(this.day));
        this.fullStatsLabels.get(1).setText(String.valueOf(0));
        this.fullStatsLabels.get(2).setText(String.valueOf(0));
        this.fullStatsLabels.get(3).setText(String.valueOf(0));
        this.fullStatsLabels.get(4).setText(String.valueOf(0));
        this.fullStatsLabels.get(5).setText(String.valueOf(0));
        this.fullStatsLabels.get(6).setText(String.valueOf(0));
    }

    protected void updateIndStats(){
        if (followedOne != null) {
            this.indStatsLabels.get(0).setText(Arrays.toString(followedOne.genotype.genome));
            this.indStatsLabels.get(1).setText(Integer.toString(followedOne.genotype.genome[followedOne.genotype.currentGenIdx]));
            this.indStatsLabels.get(2).setText(Integer.toString(followedOne.energy));
            this.indStatsLabels.get(3).setText(String.valueOf(0));
            this.indStatsLabels.get(4).setText(Integer.toString(followedOne.noChildren));
            this.indStatsLabels.get(5).setText(Integer.toString(day - followedOne.birthdate));
            this.indStatsLabels.get(6).setText(String.valueOf(0));
            }
    }
    private void highlightBest(){
        // idk idk
    }

}
