package persistence;

import model.Character;
import model.GenshinTeam;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// All tests referenced from JsonReaderTest class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class TeamWriterTest extends JsonTeamTest {

    @Test
    void writerInvalidFileTest() {
        try {
            GenshinTeam team = new GenshinTeam();
            TeamWriter teamWriter = new TeamWriter(".data/invalidFile.json");
            teamWriter.open();
            fail("This file is invalid! Please try again...");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void writerEmptyFileTest() {
        try {
            GenshinTeam team = new GenshinTeam();
            TeamWriter teamWriter = new TeamWriter("./data/writerEmptyFileTest.json");
            teamWriter.open();
            teamWriter.write(team);
            teamWriter.close();

            TeamReader jsonReader = new TeamReader("./data/writerEmptyFileTest.json");
            team = jsonReader.readTeam();
            assertEquals(0, team.getTeam().size());

        } catch (IOException e) {
            fail("This file is writable! Exception should not have been thrown!");
        }
    }

    @Test
    void writerWorkingFileTest() {
        try {
            GenshinTeam team = new GenshinTeam();
            team.addCharacter(new Character("diluc", "pyro"));
            team.addCharacter(new Character("ganyu", "cryo"));
            TeamWriter teamWriter = new TeamWriter("./data/writerWorkingFileTest.json");
            teamWriter.open();
            teamWriter.write(team);
            teamWriter.close();

            TeamReader teamReader = new TeamReader("./data/writerWorkingFileTest.json");
            team = teamReader.readTeam();
            assertEquals(2, team.getTeam().size());
            checkCharacter("diluc", "pyro", team.getTeam().get(0));
            checkCharacter("ganyu", "cryo", team.getTeam().get(1));

        } catch (IOException e) {
            fail("This file is writable! Exception should not have been thrown!");
        }
    }
}
