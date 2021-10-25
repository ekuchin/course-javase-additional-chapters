package ru.ekuchin.filetypes.json;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.json.stream.JsonParser;
import java.io.FileInputStream;
import java.util.ArrayList;

public class JsonCatBuilder {
    public static ArrayList<JsonCat> readJson(String filename) throws Exception {
        ArrayList<JsonCat> result = new ArrayList<>();
        try (FileInputStream in = new FileInputStream(filename)) {
            JsonParser parser = Json.createParser(in);
            parser.next();
            JsonArray jsonArray = parser.getArray();
            for(JsonValue json: jsonArray){
                JsonCat cat = new JsonCat("","",0,false);
                JsonObject jsonobj=json.asJsonObject();
                cat.setName(jsonobj.getString("name"));
                cat.setBreed(jsonobj.getString("breed"));
                cat.setWeight(jsonobj.getInt("weight"));
                cat.setAngry(jsonobj.getBoolean("isAngry"));
                result.add(cat);
            }
        }
        return result;
    }

    public static void writeJson(ArrayList<JsonCat> cats, String filename){

    }
}
