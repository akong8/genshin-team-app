package persistence;

import model.Character;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Referenced from JsonTest class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonTeamTest {

    protected void checkCharacter(String name, String vision, Character character) {
        assertEquals(name, character.getName());
        assertEquals(vision, character.getVision());
    }
}
