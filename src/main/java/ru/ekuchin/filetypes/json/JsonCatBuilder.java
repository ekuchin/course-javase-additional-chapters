package ru.ekuchin.filetypes.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.json.*;
import javax.json.stream.JsonParser;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JsonCatBuilder {
    public static ArrayList<JsonPCat> readJson(String filename) throws Exception {
        ArrayList<JsonPCat> result = new ArrayList<>();
        try (FileInputStream in = new FileInputStream(filename)) {
            JsonParser parser = Json.createParser(in);
            parser.next();
            JsonArray jsonArray = parser.getArray();
            for(JsonValue json: jsonArray){
                JsonPCat cat = new JsonPCat("","",0,false);
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

    public static void writeJson(ArrayList<JsonPCat> cats, String filename){
        JsonArrayBuilder arr = Json.createArrayBuilder();
        for(JsonPCat cat: cats){
            JsonObjectBuilder obj = Json.createObjectBuilder();
            obj.add("name",cat.getName())
                    .add("breed",cat.getBreed())
                    .add("weight", cat.getWeight())
                    .add("isAngry", cat.isAngry());
            arr.add(obj);
        }
        System.out.println(arr.build().toString());
    }

    public static JacksonCat[] readJackson(String filename) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), JacksonCat[].class);
    }

    public static void writeJackson(JacksonCat[] cats, String filename) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(cats);
        try (PrintWriter out = new PrintWriter(filename)) {
            out.println(jsonString);
        }
    }
}
