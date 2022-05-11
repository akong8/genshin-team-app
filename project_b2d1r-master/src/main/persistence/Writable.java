package persistence;

import org.json.JSONObject;

// Referenced Writable interface from
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns as a JSON object
    JSONObject toJson();
}
