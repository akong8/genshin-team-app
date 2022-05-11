package persistence;

import model.GenshinTeam;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// All tests referenced from JsonReaderTest class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class TeamReaderTest extends JsonTeamTest {

    @Test
    void readerNonExistentFileTest() {
        TeamReader teamReader = new TeamReader("./data/nonExistentFile.json");
        try {
            GenshinTeam team = teamReader.readTeam();
            fail("This file is invalid! Please try again...");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void readerEmptyFileTest() {
        TeamReader teamReader = new TeamReader("./data/readerEmptyFileTest.json");
        try {
            GenshinTeam team = teamReader.readTeam();
            assertEquals(0, team.getTeam().size());
        } catch (IOException e) {
            fail("ERROR! Could not read from file!");
        }

    }

    @Test
    void readerWorkingFileTest() {
        TeamReader teamReader = new TeamReader("./data/readerWorkingFileTest.json");
        try {
            GenshinTeam team = teamReader.readTeam();
            assertEquals(2, team.getTeam().size());
            checkCharacter("mona", "hydro", team.getTeam().get(0));
            checkCharacter("xiao", "anemo", team.getTeam().get(1));
        } catch (IOException e) {
            fail("ERROR! Could not read from file!");
        }
    }
}
