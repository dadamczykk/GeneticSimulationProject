# Genetic Simulation Project
A project created for the Object-Oriented Programming course at AGH UST during the 2022/2023 academic year.

## Overview
This project is a simple simulation of an animal population that can:
* Move - with moves determined by their genome
* Eat plants - to gain energy
* Reproduce - if certain conditions are met

Each day, new plants (represented as green tiles) will appear on the map. The animals (represented as red tiles) will then make a move, eat plants and, if two animals are on the same position and have enough energy, they will produce a new offspring. The offspring will have a new genome, obtained by combining the genomes of the parents. If an animal runs out of energy, it will die and be removed from the map.

The user can customize the simulation by specifying the following parameters:
* Map type and dimensions
* Plant generation system
* Critical energy values
* Animal behavior
* Genome parameters

Thanks to multithreading, the user can run multiple simulations simultaneously and can stop a simulation to view its general statistics or the statistics of a specific animal.

Please note that the task description, which is in Polish, can be found [here](https://github.com/apohllo/obiektowe-lab/tree/master/proj1). The project aimed to fulfill all the requirements presented in the description.

### Simulation Example

<p align="center">
  <img src="https://github.com/dadamczykk/GeneticSimulationProject/blob/main/sim.gif">
</p>

## Launching the Application
To run the application, you will need:
* Jdk 16
* Gradle 7.4
* Dependencies listed in the build.gradle file

It is recommended to use IntelliJ to run the simulation. Alternatively, you can use the `./gradlew.bat run` command in the main folder.

After launching the application, you will be presented with a screen where you can specify the simulation parameters. You can also save the configuration to a file or load it from a file. For best results, it is recommended to use absolute system paths. If a path to the configuration file is not specified, the program will scan the main folder of the source code.

## Contributors
* [Szymon Nowak-Trzos](https://github.com/Szyntos/)
* [Dominik Adamczyk](https://github.com/dadamczykk)