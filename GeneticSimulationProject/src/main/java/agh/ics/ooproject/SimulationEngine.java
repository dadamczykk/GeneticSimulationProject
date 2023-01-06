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
    private int sleepTimer = 500;

    public int simulationNumber;

    public final SimulationVisualizer simVis;

    public agh.ics.gui.App app;

    public boolean isPaused = false;


    public SimulationEngine(int simNo, ArrayList<Integer> args, agh.ics.gui.App app) throws IOException {
        this.sleepTimer = 500;
        if (args.get(15) == 1) {
            this.simVis = new SimulationVisualizerCSV(args, this);
        } else{
            this.simVis = new SimulationVisualizer(args, this);
        }
        this.simulationNumber = simNo;
        this.app = app;
    }

    public synchronized void run(){
        try {
            this.testRun();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private synchronized void testRun() throws InterruptedException {
        System.out.println(sleepTimer);
        int i = 1;
        Thread.sleep(sleepTimer);
        while(true){
            if (isPaused){ wait();}

            int finalI = i;

            try {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            simVis.updateScene(finalI);
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

            i++;
        }
    }



    public synchronized void repause(){
        if (isPaused) {
            this.notify();
            this.isPaused = false;
        }
    }

}
