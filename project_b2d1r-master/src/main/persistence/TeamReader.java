package persistence;

import model.GenshinTeam;
import model.Character;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// All methods referenced from JsonReader class in
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads GenshinTeam from JSON data stored in file
public class TeamReader {
    private String file;

    // EFFECTS: constructs a reader to read the team stored on file
    public TeamReader(String file) {
        this.file = file;
    }

    // EFFECTS: reads GenshinTeam currently stored on file and returns it;
    // throws IOException if an error occurs reading data from file
    public GenshinTeam readTeam() throws IOException {
        String teamData = readFile(file);
        JSONObject jsonTeam = new JSONObject(teamData);
        return parseTeam(jsonTeam);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses team from JSON object and returns it
    private GenshinTeam parseTeam(JSONObject jsonTeam) {
        GenshinTeam team = new GenshinTeam();
        addCharacters(team, jsonTeam);
        return team;
    }

    // MODIFIES: team
    // EFFECTS: parses characters from JSON object and adds them to team
    private void addCharacters(GenshinTeam team, JSONObject jsonCharacters) {
        JSONArray jsonArray = jsonCharacters.getJSONArray("characters");
        for (Object jsonCharacter : jsonArray) {
            JSONObject nextCharacter = (JSONObject) jsonCharacter;
            addCharacter(team, nextCharacter);
        }
    }

    // MODIFIES: team
    // EFFECTS: parses character from JSON object and adds it to team
    private void addCharacter(GenshinTeam team, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String vision = jsonObject.getString("vision");
        Character character = new Character(name, vision);
        team.addCharacter(character);
    }
}

