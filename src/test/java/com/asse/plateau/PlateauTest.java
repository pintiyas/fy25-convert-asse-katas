package com.asse.plateau;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PlateauTest {
    @Test
    public void testPlateauCreation() {
        Plateau plateau = new Plateau(10, 10);
        assertEquals(plateau.getWidth(), 10);
        assertEquals(plateau.getHeight(), 10);
    }

    @Test
    public void testAddObstacle() {
        Plateau plateau = new Plateau(10, 10);
        plateau.registerObstacle(5, 5);
        assertTrue(plateau.isObstacle(5, 5));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void wrongObstacle() {
        Plateau plateau = new Plateau(10, 10);
        plateau.registerObstacle(50, 50);
    }

}