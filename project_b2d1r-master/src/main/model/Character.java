package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a single character from Genshin Impact with a name and vision
public class Character implements Writable {
    private String name; // name of character
    private String vision; // elemental vision that character holds

    //EFFECTS: constructs a new character with given name and vision
    public Character(String charName, String charVision) {
        this.name = charName;
        this.vision = charVision;
    }

    public String getName() {
        return name;
    }

    public String getVision() {
        return vision;
    }

    // Method taken from Thingy class in
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    @Override
    public JSONObject toJson() {
        JSONObject jsonCharacter = new JSONObject();
        jsonCharacter.put("name", name);
        jsonCharacter.put("vision", vision);
        return jsonCharacter;
    }

}
