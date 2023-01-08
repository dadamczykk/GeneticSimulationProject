package agh.ics.gui;

import agh.ics.ooproject.*;
import agh.ics.ooproject.GameMap;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Pattern;

public class App extends Application {
    private GameMap map;
    private Stage stage;

    public ArrayList<Thread> threads = new ArrayList<>();

    private final List<SimulationEngine> engines = new ArrayList<>();
//    private List<SimulationVisualizer> visualizers = new ArrayList<>();


    @Override
    public void init(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene = this.createStartScreen();

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setWidth(scene.getWidth()*1.1);
        primaryStage.setHeight(scene.getHeight()*1.1);

//        String val = worldVersion.getValue();
//        System.out.println(val);
    }

    private Scene createStartScreen(){
        int minWidth = 240;
        int minHeight = 30;
        int spacing = 10;

        Font mainFont = new Font("Comic Sans MS", 15);

        ObservableList<MapType> worldVersions = FXCollections.observableArrayList(MapType.Hel, MapType.Valhalla);
        ComboBox<MapType> worldVersion = new ComboBox<>(worldVersions);
        worldVersion.setMinHeight(minHeight);
        worldVersion.setMinWidth(minWidth);
        Label worldStr = new Label("Choose your world version\t");
        worldStr.setMinWidth(minWidth);
        worldStr.setMinHeight(minHeight);
        worldStr.setTextAlignment(TextAlignment.CENTER);
//        worldStr.setFont(mainFont);
        HBox worldCont = new HBox(worldStr, worldVersion);
        worldVersion.setValue(MapType.Valhalla);


        ObservableList<PlantType> plantsVersions = FXCollections.observableArrayList(PlantType.EQUATOR,
                PlantType.NONTOXIC);
        ComboBox<PlantType> plantsVersion = new ComboBox<>(plantsVersions);
        plantsVersion.setMinHeight(minHeight);
        plantsVersion.setMinWidth(minWidth);
        Label plantsStr = new Label("Choose plant's spawning system\t");
        plantsStr.setMinWidth(minWidth);
        plantsStr.setMinHeight(minHeight);
        plantsStr.setTextAlignment(TextAlignment.CENTER);
//        plantsStr.setFont(mainFont);
        HBox plantsCont = new HBox(plantsStr, plantsVersion);
        plantsVersion.setValue(PlantType.EQUATOR);


        ObservableList<MutationType> mutationVersions = FXCollections.observableArrayList(MutationType.RANDOM,
                MutationType.SLIGHT);
        ComboBox<MutationType> mutationVersion = new ComboBox<>(mutationVersions);
        mutationVersion.setMinHeight(minHeight);
        mutationVersion.setMinWidth(minWidth);
        Label mutationStr = new Label("Choose type of mutation randomness\t");
        mutationStr.setMinWidth(minWidth);
        mutationStr.setMinHeight(minHeight);
        mutationStr.setTextAlignment(TextAlignment.CENTER);
//        mutationStr.setFont(mainFont);
        HBox mutationsCont = new HBox(mutationStr, mutationVersion);
        mutationVersion.setValue(MutationType.RANDOM);


        ObservableList<BehaviourType> behaviourVersions = FXCollections.observableArrayList(BehaviourType.MADNESS,
                BehaviourType.PREDESTINATION);
        ComboBox<BehaviourType> behaviourVersion = new ComboBox<>(behaviourVersions);
        behaviourVersion.setMinHeight(minHeight);
        behaviourVersion.setMinWidth(minWidth);
        Label behaviourStr = new Label("Choose behaviour of animals\t");
        behaviourStr.setMinWidth(minWidth);
        behaviourStr.setMinHeight(minHeight);
        behaviourStr.setTextAlignment(TextAlignment.CENTER);
//        behaviourStr.setFont(mainFont);
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
        HBox mapWidthCont = new HBox(mapWidthLabel, mapWidthField);
        mapWidthLabel.setMinWidth(minWidth);
        mapWidthLabel.setMinHeight(minHeight);
        mapWidthField.setMinWidth(minWidth);
        mapWidthField.setMinHeight(minHeight);

        TextField mapHeightField = new TextField("15");
        Label mapHeightLabel = new Label("Choose the height of the map");
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

        TextField plantsPerDay = new TextField("5");
        Label plantsPerDayLabel = new Label("Choose how many plants will spawn every day");
        HBox plantsPerDayCont = new HBox(plantsOnStartLabel, plantsOnStart);
        plantsPerDayLabel.setMinWidth(minWidth);
        plantsPerDayLabel.setMinHeight(minHeight);
        plantsPerDay.setMinWidth(minWidth);
        plantsPerDay.setMinHeight(minHeight);

        TextField animalsOnStart = new TextField("5");
        Label animalsOnStartLabel = new Label("Choose initial number of animals");
        HBox animalsOnStartCont = new HBox(animalsOnStartLabel, animalsOnStart);
        animalsOnStart.setMinWidth(minWidth);
        animalsOnStart.setMinHeight(minHeight);
        animalsOnStartLabel.setMinWidth(minWidth);
        animalsOnStartLabel.setMinHeight(minHeight);

        TextField plantEnergy = new TextField("5");
        Label plantEnergyLabel = new Label("Choose energy given by plants");
        HBox plantEnergyCont = new HBox(plantEnergyLabel, plantEnergy);
        plantEnergyLabel.setMinWidth(minWidth);
        plantEnergyLabel.setMinHeight(minHeight);
        plantEnergy.setMinWidth(minWidth);
        plantEnergy.setMinHeight(minHeight);

        TextField animalsEnergy = new TextField("5");
        Label animalsEnergyLabel = new Label("Choose initial energy of animals");
        HBox animalsEnergyCont = new HBox(animalsEnergyLabel, animalsEnergy);
        animalsEnergyLabel.setMinWidth(minWidth);
        animalsEnergyLabel.setMinHeight(minHeight);
        animalsEnergy.setMinWidth(minWidth);
        animalsEnergy.setMinHeight(minHeight);

        TextField animalFullEnergy = new TextField("5");
        Label animalFullEnergyLabel = new Label("Choose satisfying energy");
        HBox animalFullEnergyCont = new HBox(animalFullEnergyLabel, animalFullEnergy);
        animalFullEnergyLabel.setMinWidth(minWidth);
        animalFullEnergyLabel.setMinHeight(minHeight);
        animalFullEnergy.setMinWidth(minWidth);
        animalFullEnergy.setMinHeight(minHeight);

        TextField animalCopulateEnergy = new TextField("5");
        Label animalCopulateEnergyLabel = new Label("Choose energy spent on a child");
        HBox animalCopulateEnergyCont = new HBox(animalCopulateEnergyLabel, animalCopulateEnergy);
        animalCopulateEnergyLabel.setMinWidth(minWidth);
        animalCopulateEnergyLabel.setMinHeight(minHeight);
        animalCopulateEnergy.setMinWidth(minWidth);
        animalCopulateEnergy.setMinHeight(minHeight);

        TextField minChildMutations = new TextField("2");
        Label minChildMutationsLabel = new Label("Choose minimum mutations of a genome");
        HBox minChildMutationsCont = new HBox(minChildMutationsLabel, minChildMutations);
        minChildMutationsLabel.setMinWidth(minWidth);
        minChildMutationsLabel.setMinHeight(minHeight);
        minChildMutations.setMinWidth(minWidth);
        minChildMutations.setMinHeight(minHeight);

        TextField maxChildMutations = new TextField("5");
        Label maxChildMutationsLabel = new Label("Choose maximum mutations of a genome");
        HBox maxChildMutationsCont = new HBox(maxChildMutationsLabel, maxChildMutations);
        maxChildMutationsLabel.setMinWidth(minWidth);
        maxChildMutationsLabel.setMinHeight(minHeight);
        maxChildMutations.setMinWidth(minWidth);
        maxChildMutations.setMinHeight(minHeight);

        TextField genomeLength = new TextField("4");
        Label genomeLengthLabel = new Label("Choose length of a genome");
        HBox genomeLengthCont = new HBox(genomeLengthLabel, genomeLength);
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

        Button readFromFile = new Button("Read configuration from a source file");

        TextField outFile = new TextField("Write a name of a new file");

        Button writeToFile = new Button("Write configuration to a file");

        Label message = new Label("");

        Button startSim = new Button("Start simulation");

        CheckBox saveData = new CheckBox("Save data of animation to csv");

        iosLogic.getChildren().addAll(sourceFile, readFromFile, outFile, writeToFile, message, saveData, startSim);

        iosLogic.setSpacing(3*spacing);
        iosLogic.setAlignment(Pos.CENTER);

//        mapHeightField.textProperty().addListener(new ChangeListener<String>(){
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue,
//                                String newValue) {
//                if (!newValue.matches("\\d*")) {
//                    mapHeightField.setText(newValue.replaceAll("[^\\d]", ""));
//                }
//            }
//        });


        startSim.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
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
//                    if (Integer.parseInt(minChildMutations.getText()) >= Integer.parseInt(maxChildMutations.getText())) {
//                        throw new Exception("Incorrect min and max value of children mutations");
//                    }
                    startNew(arguments);


                } catch (NullPointerException e) {
                    message.setText("There are empty values! Simulation cannot run!");
                } catch (NumberFormatException e) {
                    message.setText("Invalid value of textBox, there might be letters\n or integer value is too big");
                } catch (Exception e) {
                    message.setText(e.getMessage());
                }
            }
        });

        writeToFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

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
                    saveConfigToFile(arguments, outFile.getText());

                    message.setText("File successfully created");

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
                    message.setText("Successfully read input file");
                } catch (FileNotFoundException e) {
                    message.setText("File with given name does not exist");
                } catch (NullPointerException e){
                    message.setText("Corrupted input file - some values may not be set properly");
                }
                catch (Exception e){
                    message.setText(e.getMessage() + e);
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

    private void integrityChecker(ArrayList<Integer> args) throws Exception{
        for (int i = 0; i < 4; i++){
            if (args.get(i) < 0 || args.get(i) > 1){
                throw new Exception("Slider values must be 0 or 1");
            }
        }
        if (args.get(4) < 1 || args.get(4) > 100){
            throw new Exception("Map width and height must be from interval [1, 100]");
        }
        if (args.get(5) < 1 || args.get(5) > 100){
            throw new Exception("Map width and height must be from interval [1, 100]");
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
            throw new Exception("Negative energy from plant value is incorrect");
        }
        if (args.get(9) < 1){
            throw new Exception("None positive animal energy value is incorrect");
        }
        if (args.get(10) < 1 || args.get(11) < 1){
            throw new Exception("None positive animal energy value is incorrect");
        }
        if (args.get(12) * args.get(13) < 0){
            throw new Exception("Child mutations cannot be negative");
        }
        if (args.get(12) > args.get(13)){
            throw new Exception("Incorrect min and max value of children mutations");
        }
        if (args.get(14) <= 0){
            throw new Exception("Genome length has to be positive");
        }


    }
    private void startNew(ArrayList<Integer> arguments) throws IOException {
        SimulationEngine se = new SimulationEngine(engines.size(), arguments, this);
        engines.add(se);
        Thread th = new Thread(se);
        th.start();
        threads.add(th);
    }
    public void pauseThread(int no) throws InterruptedException {
        this.threads.get(no).wait();
    }

}
