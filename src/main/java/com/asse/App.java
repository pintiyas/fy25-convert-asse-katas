package com.asse;

import com.asse.commands.Command;
import com.asse.commands.ExtendedMovementCommand;
import com.asse.plateau.Plateau;
import com.asse.rover.Facing;
import com.asse.rover.Rover;

import java.util.Scanner;
public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        System.out.println("Enter the plateau size (x:y):");
        String plateauSize = scanner.next();
        String[] size = plateauSize.split(":");
        int width = Integer.parseInt(size[0]);
        int height = Integer.parseInt(size[1]);
        Plateau plateau = new Plateau("Mars", width, height);


        System.out.println("Enter the number of obstacles:");
        int numberOfObstacles = scanner.nextInt();
        for (int i = 0; i < numberOfObstacles; i++) {
            System.out.println("Enter obstacle coordinates (x:y):");
            String obstacleCoordinates = scanner.next();
            String[] coordinates = obstacleCoordinates.split(":");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            try {
                plateau.registerObstacle(x, y);
            } catch (IllegalArgumentException e) {
                System.out.println("Error adding obstacle: " + e.getMessage());
            }
        }

        Rover rover = new Rover("1", plateau, 0, 0, Facing.N);

        try {
            plateau.registerRover(rover);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding rover: " + e.getMessage());
        }

        while (true) {
            System.out.println("Enter command: ");
            String command = scanner.next();
            if (command.equalsIgnoreCase("exit")) {
                break;
            }
            if (command.equalsIgnoreCase("map")) {
                plateau.toMap();
                continue;
            }
            try {
                Command movementCommand = new ExtendedMovementCommand(rover, command);
                String result = movementCommand.execute();
                System.out.println("Result: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
