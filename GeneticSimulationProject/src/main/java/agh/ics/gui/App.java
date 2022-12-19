package agh.ics.gui;

import agh.ics.ooproject.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class App extends Application {
    private AbstractMap map;
    private Stage stage;
    @Override
    public void init(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        int minWidth = 240;
        int minHeight = 30;
        int spacing = 10;

        Font mainFont = new Font("Comic Sans MS", 15);

        ObservableList<WorldTypes> worldVersions = FXCollections.observableArrayList(WorldTypes.EARTH,
                WorldTypes.HELL);
        ComboBox<WorldTypes> worldVersion = new ComboBox<>(worldVersions);
        worldVersion.setMinHeight(minHeight);
        worldVersion.setMinWidth(minWidth);
        Label worldStr = new Label("Choose your world version:\t");
        worldStr.setMinWidth(minWidth);
        worldStr.setMinHeight(minHeight);
        worldStr.setTextAlignment(TextAlignment.CENTER);
        worldStr.setFont(mainFont);
        HBox worldCont = new HBox(worldStr, worldVersion);


        ObservableList<PlantType> plantsVersions = FXCollections.observableArrayList(PlantType.EQUATOR,
                PlantType.NONTOXIC);
        ComboBox<PlantType> plantsVersion = new ComboBox<>(plantsVersions);
        plantsVersion.setMinHeight(minHeight);
        plantsVersion.setMinWidth(minWidth);
        Label plantsStr = new Label("Choose plant's spawning system:\t");
        plantsStr.setMinWidth(minWidth);
        plantsStr.setMinHeight(minHeight);
        plantsStr.setTextAlignment(TextAlignment.CENTER);
        plantsStr.setFont(mainFont);
        HBox plantsCont = new HBox(plantsStr, plantsVersion);


        ObservableList<MutationType> mutationVersions = FXCollections.observableArrayList(MutationType.RANDOM,
                MutationType.SLIGHT);
        ComboBox<MutationType> mutationVersion = new ComboBox<>(mutationVersions);
        mutationVersion.setMinHeight(minHeight);
        mutationVersion.setMinWidth(minWidth);
        Label mutationStr = new Label("Choose randomness of mutations:\t");
        mutationStr.setMinWidth(minWidth);
        mutationStr.setMinHeight(minHeight);
        mutationStr.setTextAlignment(TextAlignment.CENTER);
        mutationStr.setFont(mainFont);
        HBox mutationsCont = new HBox(mutationStr, mutationVersion);


        ObservableList<BehaviourType> behaviourVersions = FXCollections.observableArrayList(BehaviourType.MADNESS,
                BehaviourType.PREDESTINATION);
        ComboBox<BehaviourType> behaviourVersion = new ComboBox<>(behaviourVersions);
        behaviourVersion.setMinHeight(minHeight);
        behaviourVersion.setMinWidth(minWidth);
        Label behaviourStr = new Label("Choose behaviour of animals:\t");
        behaviourStr.setMinWidth(minWidth);
        behaviourStr.setMinHeight(minHeight);
        behaviourStr.setTextAlignment(TextAlignment.CENTER);
        behaviourStr.setFont(mainFont);
        HBox behaviourCont = new HBox(behaviourStr, behaviourVersion);


        VBox textContainer = new VBox();
        textContainer.getChildren().addAll(worldStr, plantsStr, mutationStr, behaviourStr);
        textContainer.setSpacing(spacing);

        VBox comboContainer = new VBox();
        comboContainer.getChildren().addAll(worldCont, plantsCont, mutationsCont, behaviourCont);
        comboContainer.setSpacing(spacing);

        HBox firstChoices = new HBox();
        firstChoices.getChildren().addAll(textContainer, comboContainer);

        TextField mapWidthField = new TextField("Map width");
        Label mapWidthLabel = new Label("Choose width of the map:");
        HBox mapWidthCont = new HBox(mapWidthLabel, mapWidthField);

        TextField mapHeightField = new TextField("Map width");
        Label mapHeightLabel = new Label("Choose height of the map:");
        HBox mapHeightCont = new HBox(mapHeightLabel, mapHeightField);

        TextField plantsOnStart = new TextField("plantsOnStart");
//        Label mapHeightLabel = new Label("Choose height of the map:");
//        HBox mapHeightCont = new HBox(mapHeightLabel, mapHeightField);
        firstChoices.getChildren().addAll(mapWidthCont, mapHeightCont);


        Scene scene = new Scene(firstChoices);


        primaryStage.setScene(scene);
        primaryStage.show();

//        String val = worldVersion.getValue();
//        System.out.println(val);
    }
}
