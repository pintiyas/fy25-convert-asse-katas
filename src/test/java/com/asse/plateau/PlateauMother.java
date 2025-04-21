package com.asse.plateau;

public class PlateauMother {
    public static Plateau createPlateau() {
        return new Plateau(10, 10);
    }

    public static Plateau createPlateauWithObstacle(int obstacleX, int obstacleY) {
        Plateau plateau = createPlateau();
        plateau.registerObstacle(obstacleX, obstacleY);
        return plateau;
    }

}