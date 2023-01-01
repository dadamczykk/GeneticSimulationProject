package agh.ics.gui;

import agh.ics.ooproject.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App extends Application {
    private AbstractMap map;
    private Stage stage;

    private List<SimulationEngine> engines = new ArrayList<>();
    private List<SimulationVisualizer> visualizers = new ArrayList<>();


    @Override
    public void init(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;

        Scene scene = this.createStartScreen();

        primaryStage.setScene(scene);
        primaryStage.show();

//        String val = worldVersion.getValue();
//        System.out.println(val);
    }

    private Scene createStartScreen(){
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

        HBox comboBoxes = new HBox(textContainer, comboContainer);

        VBox choices = new VBox();
        choices.getChildren().addAll(comboBoxes);

        TextField mapWidthField = new TextField("10");
        Label mapWidthLabel = new Label("Choose width of the map:");
        HBox mapWidthCont = new HBox(mapWidthLabel, mapWidthField);
        mapWidthLabel.setMinWidth(minWidth);
        mapWidthLabel.setMinHeight(minHeight);
        mapWidthField.setMinWidth(minWidth);
        mapWidthField.setMinHeight(minHeight);

        TextField mapHeightField = new TextField("15");
        Label mapHeightLabel = new Label("Choose height of the map:");
        HBox mapHeightCont = new HBox(mapHeightLabel, mapHeightField);
        mapHeightLabel.setMinWidth(minWidth);
        mapHeightLabel.setMinHeight(minHeight);
        mapHeightField.setMinWidth(minWidth);
        mapHeightField.setMinHeight(minHeight);

        TextField plantsOnStart = new TextField("5");
        Label plantsOnStartLabel = new Label("Choose initial number of plants");
        HBox plantsOnStartCont = new HBox(plantsOnStartLabel, plantsOnStart);
        plantsOnStartLabel.setMinWidth(minWidth);
        plantsOnStartLabel.setMinHeight(minHeight);
        plantsOnStart.setMinWidth(minWidth);
        plantsOnStart.setMinHeight(minHeight);

        TextField animalsOnStart = new TextField("5");
        Label animalsOnStartLabel = new Label("Choose initial number of animals");
        HBox animalsOnStartCont = new HBox(animalsOnStartLabel, animalsOnStart);
        animalsOnStart.setMinWidth(minWidth);
        animalsOnStart.setMinHeight(minHeight);
        animalsOnStartLabel.setMinWidth(minWidth);
        animalsOnStartLabel.setMinHeight(minHeight);

        TextField plantEnergy = new TextField("5");
        Label plantEnergyLabel = new Label("Choose energy that plant gives");
        HBox plantEnergyCont = new HBox(plantEnergyLabel, plantEnergy);
        plantEnergyLabel.setMinWidth(minWidth);
        plantEnergyLabel.setMinHeight(minHeight);
        plantEnergy.setMinWidth(minWidth);
        plantEnergy.setMinHeight(minHeight);

        TextField animalsEnergy = new TextField("5");
        Label animalsEnergyLabel = new Label("Choose initial energy of animal");
        HBox animalsEnergyCont = new HBox(animalsEnergyLabel, animalsEnergy);
        animalsEnergyLabel.setMinWidth(minWidth);
        animalsEnergyLabel.setMinHeight(minHeight);
        animalsEnergy.setMinWidth(minWidth);
        animalsEnergy.setMinHeight(minHeight);

        TextField animalFullEnergy = new TextField("5");
        Label animalFullEnergyLabel = new Label("Choose energy to make animal full");
        HBox animalFullEnergyCont = new HBox(animalFullEnergyLabel, animalFullEnergy);
        animalFullEnergyLabel.setMinWidth(minWidth);
        animalFullEnergyLabel.setMinHeight(minHeight);
        animalFullEnergy.setMinWidth(minWidth);
        animalFullEnergy.setMinHeight(minHeight);

        TextField animalCopulateEnergy = new TextField("5");
        Label animalCopulateEnergyLabel = new Label("Choose energy to make new animal");
        HBox animalCopulateEnergyCont = new HBox(animalCopulateEnergyLabel, animalCopulateEnergy);
        animalCopulateEnergyLabel.setMinWidth(minWidth);
        animalCopulateEnergyLabel.setMinHeight(minHeight);
        animalCopulateEnergy.setMinWidth(minWidth);
        animalCopulateEnergy.setMinHeight(minHeight);

        TextField minChildMutations = new TextField("5");
        Label minChildMutationsLabel = new Label("Choose min mutations of children");
        HBox minChildMutationsCont = new HBox(minChildMutationsLabel, minChildMutations);
        minChildMutationsLabel.setMinWidth(minWidth);
        minChildMutationsLabel.setMinHeight(minHeight);
        minChildMutations.setMinWidth(minWidth);
        minChildMutations.setMinHeight(minHeight);

        TextField maxChildMutations = new TextField("5");
        Label maxChildMutationsLabel = new Label("Choose max mutations of children");
        HBox maxChildMutationsCont = new HBox(maxChildMutationsLabel, maxChildMutations);
        maxChildMutationsLabel.setMinWidth(minWidth);
        maxChildMutationsLabel.setMinHeight(minHeight);
        maxChildMutations.setMinWidth(minWidth);
        maxChildMutations.setMinHeight(minHeight);

        TextField genomeLength = new TextField("9");
        Label genomeLengthLabel = new Label("Choose length of genom");
        HBox genomeLengthCont = new HBox(genomeLengthLabel, genomeLength);
        genomeLengthLabel.setMinWidth(minWidth);
        genomeLengthLabel.setMinHeight(minHeight);
        genomeLength.setMinWidth(minWidth);
        genomeLength.setMinHeight(minHeight);



        VBox toFillText = new VBox(mapWidthLabel, mapHeightLabel, plantsOnStartLabel, animalsOnStartLabel,
                plantEnergyLabel, animalsEnergyLabel, animalFullEnergyLabel, animalCopulateEnergyLabel,
                minChildMutationsLabel, maxChildMutationsLabel, genomeLengthLabel);
        toFillText.setSpacing(spacing);

        VBox toFillField = new VBox(mapWidthField, mapHeightField, plantsOnStart, animalsOnStart,
                plantEnergy, animalsEnergy, animalFullEnergy, animalCopulateEnergy,
                minChildMutations, maxChildMutations, genomeLength);
        toFillField.setSpacing(spacing);

        HBox toFill = new HBox(toFillText, toFillField);
        toFill.setSpacing(spacing);

        VBox iosLogic = new VBox();

        TextField sourceFile = new TextField("Here write name of source file");

        Button readFromFile = new Button("Read configuration from source file");

        TextField outFile = new TextField("Here write name of new file");

        Button writeToFile = new Button("Write configuration to file");

        Label message = new Label("");

        Button startSim = new Button("Start simulation");

        CheckBox saveData = new CheckBox("Save data of animation to csv");

        iosLogic.getChildren().addAll(sourceFile, readFromFile, outFile, writeToFile, message, saveData, startSim);

        iosLogic.setSpacing(3*spacing);
        iosLogic.setAlignment(Pos.CENTER);


        startSim.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                visualizers.add(new SimulationVisualizer());
                SimulationEngine se = new SimulationEngine(visualizers.get(visualizers.size() - 1),
                        engines.size() - 1,  Integer.parseInt(mapHeightField.getText()));
                Thread th = new Thread(se);
                th.start();
            }
        });

        writeToFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try { // to całe parsowanie tutaj jest potrzebne, żeby sprawdzać, czy dane wejściowe są poprawne.
                    List<Integer> arguments = new ArrayList<>(Arrays.asList(worldVersion.getValue().ordinal(),
                            plantsVersion.getValue().ordinal(),
                            mutationVersion.getValue().ordinal(), behaviourVersion.getValue().ordinal(),
                            Integer.parseInt(mapWidthField.getText()), Integer.parseInt(mapHeightField.getText()),
                            Integer.parseInt(plantsOnStart.getText()), Integer.parseInt(animalsOnStart.getText()),
                            Integer.parseInt(plantEnergy.getText()), Integer.parseInt(animalsEnergy.getText()),
                            Integer.parseInt(animalFullEnergy.getText()), Integer.parseInt(animalCopulateEnergy.getText()),
                            Integer.parseInt(minChildMutations.getText()), Integer.parseInt(maxChildMutations.getText()),
                            Integer.parseInt(genomeLength.getText())));
                    saveConfigToFile((ArrayList<Integer>) arguments, outFile.getText());

                } catch (NullPointerException e){
                    message.setText("There are empty values! File will not be saved");
                }
                catch (NumberFormatException e){
                    message.setText("Invalid value of textBox");
                } catch (Exception e){
                    message.setText(e.getMessage());
                }
            }
        });

        readFromFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File myf = new File("src\\t.csv");
                try {
                    Scanner myScan = new Scanner(myf);
                    while(myScan.hasNextLine()){
                        System.out.println(myScan.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        });

        choices.getChildren().addAll(toFill);
        choices.setSpacing(spacing);
        HBox fin = new HBox(choices, iosLogic);
        fin.setSpacing(3*spacing);
        fin.setAlignment(Pos.CENTER);
        choices.setAlignment(Pos.CENTER);

        return new Scene(fin);




    }
//    private void saveConfigToFile(int worldVer, int plantVer, int mutationVer, int behaviourVer,
//                                  int mapWidth, int mapHeight, int plantsNo, int animalsNo, int plantsEnergy,
//                                  int animalsEnergy, int animalFull,  int animalCopul, int minChMut,
//                                  int maxChMut, int genLength, String filename) throws IOException {
    private void saveConfigToFile(ArrayList<Integer> args, String filename) throws IOException {
        FileWriter myWriter = new FileWriter(filename+".csv");
        myWriter.write("ala ma kota w tym pliku"+".csv\n");
        for (int i = 0; i < args.size(); i++){
            myWriter.write(i + ", " + args.get(i).toString() + ";\n");
        }

        myWriter.close();


    }

}
