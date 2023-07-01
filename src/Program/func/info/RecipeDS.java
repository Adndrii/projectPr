package Program.func.info;

import java.util.List;

public class RecipeDS {
    private final JsonConverter converter;
    private final String filePath;

    public RecipeDS(JsonConverter converter, String filePath) {
        this.converter = converter;
        this.filePath = filePath;
    }

    public void writeRecipes(List<Recipe> recipes) {
        converter.writeJson(recipes, filePath);
        System.out.println("Рецепти успішно записано у файл " + filePath);
    }

    public List<Recipe> readRecipes() {
        List<Recipe> recipes = converter.fromJson(filePath);
        if (recipes != null) {
            System.out.println("Рецепти успішно зчитано з файлу " + filePath);
            return recipes;
        } else {
            System.out.println("Не вдалося зчитати рецепти з файлу " + filePath);
            return null;
        }
    }
}

