package Program.func.info;

import java.util.List;

public class Recipe {
     String name;
     List<String> ingredients;
     List<MethodOC> method;
    String guide;

    public Recipe(String name, List<String> ingredients, List<MethodOC> method, String guide) {
        this.name = name;
        this.ingredients = ingredients;
        this.method = method;
        this.guide = guide;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<MethodOC> getMethod() {
        return method;
    }

    public void setMethod(List<MethodOC> method) {
        this.method = method;
    }

    public String getGuide(){return guide;}

    public void setGuide(String guide){this.guide=guide;}
}
