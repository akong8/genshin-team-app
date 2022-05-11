package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenshinTeamTest {

    private GenshinTeam testTeam;

    @BeforeEach
    public void setup() {
        testTeam = new GenshinTeam();
    }

    @Test
    public void constructorTest() {
        assertEquals(0, testTeam.getTeam().size());
    }

    @Test
    public void addCharacterTest() {
        testTeam.addCharacter(new Character("Bennett", "pyro"));
        assertEquals(1, testTeam.getTeam().size());
        testTeam.addCharacter(new Character("Kaeya", "cryo"));
        assertEquals(2, testTeam.getTeam().size());
    }

    @Test
    public void resetTest() {
        testTeam.addCharacter(new Character("Bennett", "pyro"));
        testTeam.addCharacter(new Character("Kaeya", "cryo"));
        assertEquals(2, testTeam.getTeam().size());
        testTeam.resetTeam();
        assertEquals(0, testTeam.getTeam().size());
    }

    @Test
    public void isFullTest() {
        assertFalse(testTeam.isFull());
        testTeam.addCharacter(new Character("Kaeya", "cryo"));
        testTeam.addCharacter(new Character("Ganyu", "cryo"));
        testTeam.addCharacter(new Character("Diluc", "pyro"));
        assertFalse(testTeam.isFull());
        testTeam.addCharacter(new Character("Diona", "cryo"));
        assertTrue(testTeam.isFull());
    }

}

