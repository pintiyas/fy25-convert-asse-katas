package com.asse;

import com.asse.commands.Command;
import com.asse.commands.ExtendedMovementCommand;
import com.asse.plateau.Plateau;
import com.asse.rover.Facing;
import com.asse.rover.Rover;
import com.asse.universe.Universe;

import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");

        Universe universe = new Universe();
        // Configuring plateaus in the universe
        while (true) {
            System.out.println("Enter the plateau name (blank to skip):");
            String plateauName = scanner.next();
            if (plateauName.isEmpty()) {
                break;
            }

            System.out.println("Enter the plateau width:");
            int width = scanner.nextInt();
            System.out.println("Enter the plateau height:");
            int height = scanner.nextInt();
            Plateau plateau = new Plateau(plateauName, width, height);
            universe.addPlateau(plateau);


            System.out.println("Enter the number of obstacles (blank to skip):");
            String numberOfObstaclesInput = scanner.next();
            if (numberOfObstaclesInput.isEmpty()) {
                break;
            }
            int numberOfObstacles = Integer.parseInt(numberOfObstaclesInput);
            for (int i = 0; i < numberOfObstacles; i++) {
                System.out.println("Enter obstacle (" + i + ") x position:");
                int x = scanner.nextInt();
                System.out.println("Enter obstacle (" + i + ") y position:");
                int y = scanner.nextInt();
                try {
                    plateau.registerObstacle(x, y);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error adding obstacle: " + e.getMessage());
                }
            }
        }
        while (true) {
            System.out.println("Enter the rover ID:");
            String roverId = scanner.next();
            if (roverId.isEmpty()) {
                break;
            }
            System.out.println("Select landing plateau:");
            universe.getPlateaus().stream()
                    .forEach(plateau -> System.out.println(plateau.getName()));
            String plateauName = scanner.next();
            Plateau plateau = universe.getPlateau(plateauName);

            System.out.println("Enter the rover landing x");
            int x = scanner.nextInt();
            System.out.println("Enter the rover landing y");
            int y = scanner.nextInt();
            System.out.println("Enter the rover facing (N, E, S, W):");
            String facingInput = scanner.next();
            Facing facing = Facing.valueOf(facingInput.toUpperCase());
            Rover rover = new Rover(roverId, plateau, x, y, facing);
            try {
                plateau.registerRover(rover);
            } catch (IllegalArgumentException e) {
                System.out.println("Error adding rover: " + e.getMessage());
            }
        }

        while (true) {
            System.out.println("Select plateau to operate:");
            universe.getPlateaus().stream()
                    .forEach(plateau -> System.out.println("- " + plateau.getName()));
            String plateauName = scanner.next();
            if (plateauName.isEmpty()) {
                break;
            }
            Plateau plateau = universe.getPlateau(plateauName);
            while (true) {
                System.out.println("Select rover:");
                plateau.getRovers().stream()
                        .forEach(rover -> System.out.println("- " + rover.getId()));
                String roverId = scanner.next();
                if (roverId.isEmpty()) {
                    break;
                }
                Rover rover = plateau.getRover(roverId);
                while (true) {
                    System.out.println("Enter command");
                    String command = scanner.next();
                    if (command.isEmpty()) {
                        break;
                    }
                    if (command.equalsIgnoreCase("exit")) {
                        break;
                    }
                    if (command.equalsIgnoreCase("list")) {
                        plateau.getRovers().stream()
                                .forEach(r -> System.out.println(r.getId()));
                        continue;
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
    }
}
