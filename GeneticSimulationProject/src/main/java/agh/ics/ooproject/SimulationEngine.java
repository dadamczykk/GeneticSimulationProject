package agh.ics.ooproject;

import agh.ics.gui.SimulationVisualizer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SimulationEngine implements Runnable {
    private int sleepTimer = 500;

    public int simulationNumber;

    private final SimulationVisualizer simVis;


    public SimulationEngine(int simNo, ArrayList<Integer> args){
        this.sleepTimer = 500;
        this.simVis = new SimulationVisualizer(args, this);
        this.simulationNumber = simNo;
    }

    public void run(){
        try {
            this.testRun();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void testRun() throws InterruptedException {
        System.out.println(sleepTimer);
        int i = 1;
        Thread.sleep(sleepTimer);
        while(true){
            int finalI = i;
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    simVis.updateScene(finalI);
                }
            });
            Thread.sleep(sleepTimer);
            i++;
        }
    }
}
