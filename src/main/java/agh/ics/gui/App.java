package agh.ics.gui;

import agh.ics.ooproject.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class App extends Application {
    public ArrayList<Thread> threads = new ArrayList<>();

    private final List<SimulationEngine> engines = new ArrayList<>();

    @Override
    public void init(){

    }

    @Override
    public void start(Stage primaryStage) {

        Scene scene = this.createStartScreen();

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setWidth(scene.getWidth()*1.1);
        primaryStage.setHeight(scene.getHeight()*1.1);

    }

    private Scene createStartScreen(){
        int minWidth = 240;
        int minHeight = 30;
        int spacing = 10;


        ObservableList<MapType> worldVersions = FXCollections.observableArrayList(MapType.HEL, MapType.VALHALLA);
        ComboBox<MapType> worldVersion = new ComboBox<>(worldVersions);
        worldVersion.setMinHeight(minHeight);
        worldVersion.setMinWidth(minWidth);
        Label worldStr = new Label("Choose your world version\t");
        worldStr.setMinWidth(minWidth);
        worldStr.setMinHeight(minHeight);
        worldStr.setTextAlignment(TextAlignment.CENTER);
        HBox worldCont = new HBox(worldStr, worldVersion);
        worldVersion.setValue(MapType.VALHALLA);


        ObservableList<PlantType> plantsVersions = FXCollections.observableArrayList(PlantType.EQUATOR,
                PlantType.NONTOXIC);
        ComboBox<PlantType> plantsVersion = new ComboBox<>(plantsVersions);
        plantsVersion.setMinHeight(minHeight);
        plantsVersion.setMinWidth(minWidth);
        Label plantsStr = new Label("Choose plants' spawning system\t");
        plantsStr.setMinWidth(minWidth);
        plantsStr.setMinHeight(minHeight);
        plantsStr.setTextAlignment(TextAlignment.CENTER);
        HBox plantsCont = new HBox(plantsStr, plantsVersion);
        plantsVersion.setValue(PlantType.EQUATOR);


        ObservableList<MutationType> mutationVersions = FXCollections.observableArrayList(MutationType.RANDOM,
                MutationType.SLIGHT);
        ComboBox<MutationType> mutationVersion = new ComboBox<>(mutationVersions);
        mutationVersion.setMinHeight(minHeight);
        mutationVersion.setMinWidth(minWidth);
        Label mutationStr = new Label("Choose the type of mutation randomness\t");
        mutationStr.setMinWidth(minWidth);
        mutationStr.setMinHeight(minHeight);
        mutationStr.setTextAlignment(TextAlignment.CENTER);
        HBox mutationsCont = new HBox(mutationStr, mutationVersion);
        mutationVersion.setValue(MutationType.RANDOM);


        ObservableList<BehaviourType> behaviourVersions = FXCollections.observableArrayList(BehaviourType.MADNESS,
                BehaviourType.PREDESTINATION);
        ComboBox<BehaviourType> behaviourVersion = new ComboBox<>(behaviourVersions);
        behaviourVersion.setMinHeight(minHeight);
        behaviourVersion.setMinWidth(minWidth);
        Label behaviourStr = new Label("Choose the behaviour of animals\t");
        behaviourStr.setMinWidth(minWidth);
        behaviourStr.setMinHeight(minHeight);
        behaviourStr.setTextAlignment(TextAlignment.CENTER);
        HBox behaviourCont = new HBox(behaviourStr, behaviourVersion);
        behaviourVersion.setValue(BehaviourType.MADNESS);


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
        Label mapWidthLabel = new Label("Choose the width of the map");
        mapWidthLabel.setMinWidth(minWidth);
        mapWidthLabel.setMinHeight(minHeight);
        mapWidthField.setMinWidth(minWidth);
        mapWidthField.setMinHeight(minHeight);

        TextField mapHeightField = new TextField("15");
        Label mapHeightLabel = new Label("Choose the height of the map");
        mapHeightLabel.setMinWidth(minWidth);
        mapHeightLabel.setMinHeight(minHeight);
        mapHeightField.setMinWidth(minWidth);
        mapHeightField.setMinHeight(minHeight);

        TextField plantsOnStart = new TextField("5");
        Label plantsOnStartLabel = new Label("Choose initial number of plants");
        plantsOnStartLabel.setMinWidth(minWidth);
        plantsOnStartLabel.setMinHeight(minHeight);
        plantsOnStart.setMinWidth(minWidth);
        plantsOnStart.setMinHeight(minHeight);

        TextField plantsPerDay = new TextField("5");
        Label plantsPerDayLabel = new Label("Choose how many plants will spawn every day");
        plantsPerDayLabel.setMinWidth(minWidth);
        plantsPerDayLabel.setMinHeight(minHeight);
        plantsPerDay.setMinWidth(minWidth);
        plantsPerDay.setMinHeight(minHeight);

        TextField animalsOnStart = new TextField("5");
        Label animalsOnStartLabel = new Label("Choose the initial number of animals");
        animalsOnStart.setMinWidth(minWidth);
        animalsOnStart.setMinHeight(minHeight);
        animalsOnStartLabel.setMinWidth(minWidth);
        animalsOnStartLabel.setMinHeight(minHeight);

        TextField plantEnergy = new TextField("5");
        Label plantEnergyLabel = new Label("Choose the energy given by plants");
        plantEnergyLabel.setMinWidth(minWidth);
        plantEnergyLabel.setMinHeight(minHeight);
        plantEnergy.setMinWidth(minWidth);
        plantEnergy.setMinHeight(minHeight);

        TextField animalsEnergy = new TextField("5");
        Label animalsEnergyLabel = new Label("Choose the initial energy of animals");
        animalsEnergyLabel.setMinWidth(minWidth);
        animalsEnergyLabel.setMinHeight(minHeight);
        animalsEnergy.setMinWidth(minWidth);
        animalsEnergy.setMinHeight(minHeight);

        TextField animalFullEnergy = new TextField("5");
        Label animalFullEnergyLabel = new Label("Choose the satisfying energy");
        animalFullEnergyLabel.setMinWidth(minWidth);
        animalFullEnergyLabel.setMinHeight(minHeight);
        animalFullEnergy.setMinWidth(minWidth);
        animalFullEnergy.setMinHeight(minHeight);

        TextField animalCopulateEnergy = new TextField("5");
        Label animalCopulateEnergyLabel = new Label("Choose the energy spent on a child");
        animalCopulateEnergyLabel.setMinWidth(minWidth);
        animalCopulateEnergyLabel.setMinHeight(minHeight);
        animalCopulateEnergy.setMinWidth(minWidth);
        animalCopulateEnergy.setMinHeight(minHeight);

        TextField minChildMutations = new TextField("2");
        Label minChildMutationsLabel = new Label("Choose the minimum mutations of a genome");
        minChildMutationsLabel.setMinWidth(minWidth);
        minChildMutationsLabel.setMinHeight(minHeight);
        minChildMutations.setMinWidth(minWidth);
        minChildMutations.setMinHeight(minHeight);

        TextField maxChildMutations = new TextField("5");
        Label maxChildMutationsLabel = new Label("Choose the maximum mutations of a genome");
        maxChildMutationsLabel.setMinWidth(minWidth);
        maxChildMutationsLabel.setMinHeight(minHeight);
        maxChildMutations.setMinWidth(minWidth);
        maxChildMutations.setMinHeight(minHeight);

        TextField genomeLength = new TextField("4");
        Label genomeLengthLabel = new Label("Choose the length of a genome");
        genomeLengthLabel.setMinWidth(minWidth);
        genomeLengthLabel.setMinHeight(minHeight);
        genomeLength.setMinWidth(minWidth);
        genomeLength.setMinHeight(minHeight);



        VBox toFillText = new VBox(mapWidthLabel, mapHeightLabel, plantsOnStartLabel, plantsPerDayLabel, animalsOnStartLabel,
                plantEnergyLabel, animalsEnergyLabel, animalFullEnergyLabel, animalCopulateEnergyLabel,
                minChildMutationsLabel, maxChildMutationsLabel, genomeLengthLabel);
        toFillText.setSpacing(spacing);

        VBox toFillField = new VBox(mapWidthField, mapHeightField, plantsOnStart, plantsPerDay, animalsOnStart,
                plantEnergy, animalsEnergy, animalFullEnergy, animalCopulateEnergy,
                minChildMutations, maxChildMutations, genomeLength);
        toFillField.setSpacing(spacing);

        HBox toFill = new HBox(toFillText, toFillField);
        toFill.setSpacing(spacing);

        VBox iosLogic = new VBox();

        TextField sourceFile = new TextField("Write a name of a source file");

        Button readFromFile = new Button("Read a configuration from a source file");

        TextField outFile = new TextField("Write a name of a new file");

        Button writeToFile = new Button("Write a configuration to a file");

        Label message = new Label("");

        Button startSim = new Button("Start the simulation");

        CheckBox saveData = new CheckBox("Save data of an animation to a csv");

        Label animationTimeTxt = new Label("Length of day (in milliseconds):");

        TextField animationTime = new TextField("1000");

        iosLogic.getChildren().addAll(sourceFile, readFromFile, outFile, writeToFile, message, saveData,
                animationTimeTxt, animationTime, startSim);

        iosLogic.setSpacing(3*spacing);
        iosLogic.setAlignment(Pos.CENTER);

        startSim.setOnAction(event -> {
            try { // to całe parsowanie tutaj jest potrzebne, żeby sprawdzać, czy dane wejściowe są poprawne.
                ArrayList<Integer> arguments = new ArrayList<>(Arrays.asList(worldVersion.getValue().ordinal(),
                        plantsVersion.getValue().ordinal(),
                        mutationVersion.getValue().ordinal(), behaviourVersion.getValue().ordinal(),
                        Integer.parseInt(mapWidthField.getText()), Integer.parseInt(mapHeightField.getText()),
                        Integer.parseInt(plantsOnStart.getText()), Integer.parseInt(animalsOnStart.getText()),
                        Integer.parseInt(plantEnergy.getText()), Integer.parseInt(animalsEnergy.getText()),
                        Integer.parseInt(animalFullEnergy.getText()), Integer.parseInt(animalCopulateEnergy.getText()),
                        Integer.parseInt(minChildMutations.getText()), Integer.parseInt(maxChildMutations.getText()),
                        Integer.parseInt(genomeLength.getText()), saveData.isSelected() ? 1 : 0,
                        Integer.parseInt(plantsPerDay.getText())));
                integrityChecker(arguments);

                int time = Integer.parseInt(animationTime.getText());
                if (time < 10){
                    throw new Exception("Time cannot be lower than 10");
                }
                startNew(arguments, time);


            } catch (NullPointerException e) {
                message.setText("There are empty values! Simulation cannot run!");
            } catch (NumberFormatException e) {
                message.setText("Invalid value of a textBox, there might be letters\n or integer value is too big");
            } catch (Exception e) {
                message.setText(e.getMessage());
            }
        });

        writeToFile.setOnAction(event -> {

            try {
                ArrayList<Integer> arguments = new ArrayList<>(Arrays.asList(worldVersion.getValue().ordinal(),
                        plantsVersion.getValue().ordinal(),
                        mutationVersion.getValue().ordinal(), behaviourVersion.getValue().ordinal(),
                        Integer.parseInt(mapWidthField.getText()), Integer.parseInt(mapHeightField.getText()),
                        Integer.parseInt(plantsOnStart.getText()), Integer.parseInt(animalsOnStart.getText()),
                        Integer.parseInt(plantEnergy.getText()), Integer.parseInt(animalsEnergy.getText()),
                        Integer.parseInt(animalFullEnergy.getText()), Integer.parseInt(animalCopulateEnergy.getText()),
                        Integer.parseInt(minChildMutations.getText()), Integer.parseInt(maxChildMutations.getText()),
                        Integer.parseInt(genomeLength.getText()), saveData.isSelected() ? 1 : 0,
                        Integer.parseInt(plantsPerDay.getText())));
                integrityChecker(arguments);
                saveConfigToFile(arguments, outFile.getText());

                message.setText("File successfully created");

            } catch (NullPointerException e){
                message.setText("There are empty values! File will not be saved");
            }
            catch (NumberFormatException e){
                message.setText("Invalid value of a textBox");
            } catch (Exception e){
                message.setText(e.getMessage());
            }
        });
        readFromFile.setOnAction(event -> {

            try {
                HashMap <Integer, Integer> filling = readFile(sourceFile.getText());
                worldVersion.setValue(MapType.values()[filling.get(0)]);
                plantsVersion.setValue(PlantType.values()[filling.get(1)]);
                mutationVersion.setValue(MutationType.values()[filling.get(2)]);
                behaviourVersion.setValue(BehaviourType.values()[filling.get(3)]);
                mapWidthField.setText(filling.get(4).toString());
                mapHeightField.setText(filling.get(5).toString());
                plantsOnStart.setText(filling.get(6).toString());
                animalsOnStart.setText(filling.get(7).toString());
                plantEnergy.setText(filling.get(8).toString());
                animalsEnergy.setText(filling.get(9).toString());
                animalFullEnergy.setText(filling.get(10).toString());
                animalCopulateEnergy.setText(filling.get(11).toString());
                minChildMutations.setText(filling.get(12).toString());
                maxChildMutations.setText(filling.get(13).toString());
                genomeLength.setText(filling.get(14).toString());
                saveData.setSelected(filling.get(15) == 1);
                plantsPerDay.setText(filling.get(16).toString());
                message.setText("Successfully read the input file");
            } catch (FileNotFoundException e) {
                message.setText("File with a given name does not exist");
            } catch (NullPointerException e){
                message.setText("Corrupted input file - some values may not be set properly");
            }
            catch (Exception e){
                message.setText(e.getMessage() + e);
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

    private HashMap<Integer, Integer>  readFile(String filename) throws Exception {
        if (!filename.matches(".*\\.csv$")){
            filename+= ".csv";
        }
        HashMap<Integer, Integer> out = new HashMap<>();
        File nf = new File(filename);
        try (Scanner scan = new Scanner(nf)) {
            while (scan.hasNextLine()) {
                String toParse = scan.nextLine();
                String[] parts = toParse.split(",");
                out.put(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            }
        } catch (Exception e) {
            throw new Exception("Corrupted input file");
        }


        return out;

    }
    private void saveConfigToFile(ArrayList<Integer> args, String filename) throws IOException {
        if (!filename.matches(".*\\.csv$")){
            filename+= ".csv";
        }
        File newFile = new File(filename);
        if (!newFile.createNewFile()){
            throw new RuntimeException("File with that name already exists");
        }
        FileWriter myWriter = new FileWriter(filename);

        for (int i = 0; i < args.size(); i++){
            myWriter.write(i + "," + args.get(i).toString() + "\n");
        }
        myWriter.close();

    }

    public static void integrityChecker(ArrayList<Integer> args) throws Exception{
        for (int i = 0; i < 4; i++){
            if (args.get(i) < 0 || args.get(i) > 1){
                throw new Exception("Slider values must be 0 or 1");
            }
        }
        if (args.get(4) < 1 || args.get(4) > 100){
            throw new Exception("Map width and height must be from the interval [1, 100]");
        }
        if (args.get(5) < 1 || args.get(5) > 100){
            throw new Exception("Map width and height must be from the interval [1, 100]");
        }
        if (args.get(6) > args.get(4) * args.get(5) || args.get(16) > args.get(4) * args.get(5)){
            throw new Exception("There cannot be more plants than tiles on the map");
        }
        if (args.get(6) < 0 || args.get(16) < 0){
            throw new Exception("Negative number of plants is incorrect");
        }
        if (args.get(7) < 1){
            throw new Exception("Non positive number of animals");
        }
        if (args.get(8) < 0){
            throw new Exception("Negative energy from plants value is incorrect");
        }
        if (args.get(9) < 1){
            throw new Exception("Non positive animal energy value is incorrect");
        }
        if (args.get(10) < 1 || args.get(11) < 1){
            throw new Exception("Non positive animal energy value is incorrect");
        }
        if (args.get(12) * args.get(13) < 0 || args.get(12) + args.get(13) < 0){
            throw new Exception("Children mutations cannot be negative");
        }
        if (args.get(12) > args.get(13)){
            throw new Exception("Incorrect min and max value of children mutations");
        }
        if (args.get(14) <= 0){
            throw new Exception("Genome length has to be positive");
        }
        if (args.get(11) > args.get(10)){
            throw new Exception("Energy, that children consumes \n is bigger than satisfying energy");
        }


    }
    private void startNew(ArrayList<Integer> arguments, int time) throws IOException {
        SimulationEngine se = new SimulationEngine(engines.size(), arguments, this, time);
        engines.add(se);
        Thread th = new Thread(se);
        th.start();
        threads.add(th);
    }
}
