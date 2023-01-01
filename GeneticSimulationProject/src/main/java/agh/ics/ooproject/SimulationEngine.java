package agh.ics.ooproject;

import agh.ics.gui.SimulationVisualizer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SimulationEngine implements Runnable {
    private int sleepTimer = 500;

    private int simulationNumber;

    private SimulationVisualizer simVis;

    public SimulationEngine(SimulationVisualizer vis, int simNo, int newTime){
        this.sleepTimer = newTime;
        this.simVis = vis;
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

//            infoLabel.setText(String.valueOf(i));
//            System.out.println(i);
            Thread.sleep(sleepTimer);
            i++;
        }
    }
}
