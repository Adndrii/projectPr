package Program.func.info;

import java.util.List;

public interface JsonConverter {
    String toJson(List<Recipe> records);
    List<Recipe> fromJson(String json);
    void writeJson(List<Recipe> records, String filePath);
}