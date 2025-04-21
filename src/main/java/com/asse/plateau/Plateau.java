package com.asse.plateau;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class Plateau {
    private final int width;
    private final int height;
    private final List<Obstacle> obstacles = new ArrayList<>();

    public Plateau(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean isObstacle(int x, int y) {
        Optional<Obstacle> first = obstacles.stream()
                .filter(obstacle -> obstacle.getX() == x && obstacle.getY() == y)
                .findFirst();
        return first.isPresent();
    }

    public void registerObstacle(int x, int y) throws IllegalArgumentException{
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Obstacle coordinates out of bounds");
        }
        obstacles.add(new Obstacle(x, y));
    }

    public void toMap() {
        System.out.println("Plateau Map:");
        for (int y = height - 1; y >= 0; y--) {
            System.out.print(y + " ");
            for (int x = 0; x < width; x++) {
                if (isObstacle(x, y)) {
                    System.out.print("X ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.print("  ");
        for (int x = 0; x < width; x++) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
