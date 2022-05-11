package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class CharacterTest {

    private Character testCharacter;

    @BeforeEach
    public void setup() {
        testCharacter = new Character("Diluc", "pyro");
    }

    @Test
    public void testConstructor() {
        assertEquals("Diluc", testCharacter.getName());
        assertEquals("pyro", testCharacter.getVision());
    }
}
