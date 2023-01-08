package agh.ics.gui;

import agh.ics.ooproject.SimulationEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SimulationVisualizerCSV extends SimulationVisualizer{
    private final File out;


    public SimulationVisualizerCSV(ArrayList<Integer> args, SimulationEngine eng) throws IOException {
        super(args, eng);
        String filename = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss'.csv'").format(new Date());
        this.out = new File(filename);
        if (!this.out.createNewFile()){
            throw new IOException("Unable to create log file");
        }
        FileWriter writer = new FileWriter(this.out, true);
        writer.write("Day_of_simulation,Number_of_animals,Number_of_plants," +
                "Number_of_empty_tiles,Most_popular_genome,Average_energy,Average_time_of_life\n");
        writer.close();
    }

    @Override
    protected void updateFullStats() throws IOException {
        super.updateFullStats();
        FileWriter writer = new FileWriter(this.out, true);
        writer.write(this.day + "," + this.noAnimals + "," + this.noPlants + ","
                + noEmpty + "," + topGenome.codePoints()
                .filter(c -> Character.isDigit(c) || c == '[' || c == ']')
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString() + "," + String.format("%.2f", noAnimals != 0 ? (float) allEnergy / noAnimals : 0)
                 + "," + String.format("%.2f", avgTol) + "\n");
        writer.close();
    }
}
