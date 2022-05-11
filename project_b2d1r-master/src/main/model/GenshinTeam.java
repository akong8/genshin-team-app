package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a team of characters from Genshin Impact
public class GenshinTeam implements Writable {

    private List<Character> team;

    // EFFECTS: constructs a new team that is empty with a given team name
    public GenshinTeam() {
        this.team = new ArrayList<>();
    }

    // REQUIRES: isFull() = false
    // MODIFIES: this
    // EFFECTS: adds a character to the current team
    public void addCharacter(Character character) {
        this.team.add(character);
        EventLog.getInstance().logEvent(new Event("Character added to team"));
    }

    // MODIFIES: this
    // EFFECTS: clears team of all characters
    public void resetTeam() {
        team.clear();
        EventLog.getInstance().logEvent(new Event("Team was reset"));
    }

    // EFFECTS: determines whether a list is full or not where full = 4 characters
    public boolean isFull() {
        return team.size() == 4;
    }

    // EFFECTS: getter for list of characters
    public List<Character> getTeam() {
        return team;
    }

    // Last two methods referenced WorkRoom class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Override
    public JSONObject toJson() {
        JSONObject jsonCharacter = new JSONObject();
        jsonCharacter.put("characters", charactersToJson());
        return jsonCharacter;
    }

    // EFFECTS: returns characters in the team as a JSON array
    private JSONArray charactersToJson() {
        JSONArray jsonTeam = new JSONArray();

        for (Character character : team) {
            jsonTeam.put(character.toJson());
        }
        return jsonTeam;
    }
}


