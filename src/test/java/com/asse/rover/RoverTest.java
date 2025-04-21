package com.asse.rover;


import com.asse.plateau.PlateauMother;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

@Test
public class RoverTest {


    @Test(dataProvider = "advanceDataProvider")
    public void testAdvance(Rover rover, String expectedPosition) throws ObstacleException {
        assertEquals(rover.advance(), expectedPosition);
    }

    @DataProvider(name = "advanceDataProvider")
    public static Object[][] advanceDataProvider() {
        return new Object[][]{
                {new Rover(PlateauMother.createPlateau(), 0, 0, Facing.N), "0:1:N"},
                {new Rover(PlateauMother.createPlateau(), 0, 0, Facing.E), "1:0:E"},
                {new Rover(PlateauMother.createPlateau(), 0, 0, Facing.S), "0:9:S"},
                {new Rover(PlateauMother.createPlateau(), 0, 0, Facing.W), "9:0:W"},
                {new Rover(PlateauMother.createPlateau(), 9, 9, Facing.N), "9:0:N"},
                {new Rover(PlateauMother.createPlateau(), 9, 9, Facing.E), "0:9:E"},
                {new Rover(PlateauMother.createPlateau(), 9, 9, Facing.S), "9:8:S"},
                {new Rover(PlateauMother.createPlateau(), 9, 9, Facing.W), "8:9:W"}
        };
    }

    @Test(dataProvider = "rotateLeftDataProvider")
    public void testRotateLeft(Rover rover, String expectedPosition) {
        rover.rotateLeft();
        assertEquals(rover.getPosition(), expectedPosition);
    }

    @DataProvider(name = "rotateLeftDataProvider")
    public static Object[][] rotateLeftDataProvider() {
        return new Object[][]{
                {new Rover(PlateauMother.createPlateau(), 0, 0, Facing.N), "0:0:W"},
                {new Rover(PlateauMother.createPlateau(), 0, 0, Facing.E), "0:0:N"},
                {new Rover(PlateauMother.createPlateau(), 0, 0, Facing.S), "0:0:E"},
                {new Rover(PlateauMother.createPlateau(), 0, 0, Facing.W), "0:0:S"}
        };
    }

    @Test(dataProvider = "rotateRightDataProvider")
    public void testRotateRight(Rover rover, String expectedPosition) {
        rover.rotateRight();
        assertEquals(rover.getPosition(), expectedPosition);
    }

    @DataProvider(name = "rotateRightDataProvider")
    public static Object[][] rotateRightDataProvider() {
        return new Object[][]{
                {new Rover(PlateauMother.createPlateau(), 0, 0, Facing.N), "0:0:E"},
                {new Rover(PlateauMother.createPlateau(), 0, 0, Facing.E), "0:0:S"},
                {new Rover(PlateauMother.createPlateau(), 0, 0, Facing.S), "0:0:W"},
                {new Rover(PlateauMother.createPlateau(), 0, 0, Facing.W), "0:0:N"}
        };
    }

    @Test()
    public void testAdvanceWithObstacle() {
        Rover rover = new Rover(PlateauMother.createPlateauWithObstacle(0, 1), 0, 0, Facing.N);
        try {
            rover.advance();
        } catch (ObstacleException e) {
            assertEquals(rover.getPosition(), "0:0:N");
            assertEquals(e.getObstacleInformation(), "O:0:0:N");
        }
    }
}
