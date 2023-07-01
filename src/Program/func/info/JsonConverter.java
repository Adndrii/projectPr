package Program.func.info;

import java.util.List;

public interface JsonConverter {
    List<Recipe> fromJson(String json);
    String toJson(List<Recipe> records);

    void writeJson(List<Recipe> records, String filePath);
}