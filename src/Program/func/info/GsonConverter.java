package Program.func.info;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonConverter implements JsonConverter {
    private final Gson gson;

    public GsonConverter() {
        gson = new Gson();
    }
    @Override
    public List<Recipe> fromJson(String json) {//deserialization
        Type listType = new TypeToken<ArrayList<Recipe>>() {}.getType();
        return gson.fromJson(json, listType);
    }
    @Override
    public String toJson(List<Recipe> records) {
        return gson.toJson(records);
    }
    @Override
    public void writeJson(List<Recipe> records, String filePath) {//deserialization
        String json = toJson(records);

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(json);
        } catch (IOException e) {
            System.out.println("Помилка при записі в файл: " + e.getMessage());
        }
    }
}
