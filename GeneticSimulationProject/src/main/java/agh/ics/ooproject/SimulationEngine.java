package agh.ics.ooproject;

import agh.ics.gui.SimulationVisualizer;
import agh.ics.gui.SimulationVisualizerCSV;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class SimulationEngine implements Runnable {

    public int simulationNumber;

    public final SimulationVisualizer simVis;

    public agh.ics.gui.App app;

    public GameMap map;

    private GameEngine gameEngine;

    public boolean isPaused = false;


    public SimulationEngine(int simNo, ArrayList<Integer> args, agh.ics.gui.App app) throws IOException {
        if (args.get(15) == 1) {
            this.simVis = new SimulationVisualizerCSV(args, this);
        } else{
            this.simVis = new SimulationVisualizer(args, this);
        }
        this.simulationNumber = simNo;

        this.app = app;
        System.out.println(args);
//        try {
            this.map = new GameMap(
                    MapType.values()[args.get(0)],
                    args.get(4),
                    args.get(5),
                    args.get(6),
                    args.get(8),
                    args.get(16),
                    PlantType.values()[args.get(1)],
                    args.get(7),
                    args.get(9),
                    args.get(10),
                    args.get(11),
                    args.get(12),
                    args.get(13),
                    MutationType.values()[args.get(2)],
                    args.get(14),
                    BehaviourType.values()[args.get(3)]
            );
            this.gameEngine = new GameEngine(this.map);
//        } catch (Exception e){
            System.out.println();
//        }
    }

    public synchronized void run(){
        try {
            this.testRun();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        gameEngine.run();
    }

    private synchronized void testRun() throws InterruptedException {
        int sleepTimer = 1500;
        System.out.println(sleepTimer);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    simVis.updateScene();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread.sleep(sleepTimer);
        while(true){
            if (isPaused){ wait();}
            this.gameEngine.update();

            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            simVis.updateScene();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                Thread.sleep(sleepTimer);

            } catch (InterruptedException e){
                System.out.println("thread stopped");
                return;
            }


        }
    }



    public synchronized void repause(){
        if (isPaused) {
            this.notify();
            this.isPaused = false;
        }
    }

}
