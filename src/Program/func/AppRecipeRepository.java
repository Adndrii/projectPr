package Program.func;

import Program.func.info.MethodOC;
import Program.func.info.Recipe;
import Program.func.info.GsonConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppRecipeRepository implements RecipeRepository {
    private List<Recipe> recipes;
    private final GsonConverter gsonConverter;
    public AppRecipeRepository() {
        recipes = new ArrayList<>();
        gsonConverter = new GsonConverter();
    }

    @Override
    public void addRecipe() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть назву рецепту:");
        String name = scanner.nextLine();

        System.out.println("Введіть інгредієнти:");
        String ingredientsInput = scanner.nextLine();
        String[] ingredientsArray = ingredientsInput.split(",");
        List<String> ingredients = List.of(ingredientsArray);

        List<MethodOC> method = new ArrayList<>();
        boolean addingMethod = true;
        while (addingMethod) {
            System.out.println("Введіть метод приготування (fry, boil, freeze, bake, stew),i введіть 'done':");
            String methodInput = scanner.nextLine();
            if (methodInput.equalsIgnoreCase("done")) {
                addingMethod = false;
            } else {
                MethodOC methodOC = MethodOC.addMethod(methodInput);
                if (methodOC != null) {
                    method.add(methodOC);
                }
            }
        }

        Recipe recipe = new Recipe(name, ingredients, method);
        recipes.add(recipe);

        System.out.println("Рецепт додано.");
    }
        @Override
    public void deleteRecipe() {
        if (recipes.isEmpty()) {
            System.out.println("Немає доступних рецептів для видалення.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть назву рецепту для видалення:");
        String name = scanner.nextLine();

        boolean removed = false;
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                recipes.remove(recipe);
                removed = true;
                break;
            }
        }
        if (removed) {
            System.out.println("Рецепт видалено.");
        } else {
            System.out.println("Рецепт з такою назвою не знайдено.");
        }
    }
    @Override
    public void editRecipe() {
        if (recipes.isEmpty()) {
            System.out.println("Немає доступних рецептів для редагування.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть назву рецепту для редагування:");
        String name = scanner.nextLine();

        Recipe recipeToEdit = null;
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                recipeToEdit = recipe;
                break;
            }
        }

        if (recipeToEdit != null) {
            System.out.println("Обраний рецепт:");
            System.out.println("Назва: " + recipeToEdit.getName());
            System.out.println("Інгредієнти: " + recipeToEdit.getIngredients());
            System.out.println("Метод приготування: " + recipeToEdit.getMethod());
            System.out.println();

            System.out.println("Введіть нову назву рецепту (або натисніть Enter, щоб залишити без змін):");
            String newName = scanner.nextLine();

            System.out.println("Введіть нові інгредієнти (через кому) або натисніть Enter, щоб залишити без змін:");
            String newIngredientsInput = scanner.nextLine();
            List<String> newIngredients = recipeToEdit.getIngredients();
            if (!newIngredientsInput.isEmpty()) {
                String[] newIngredientsArray = newIngredientsInput.split(",");
                newIngredients = List.of(newIngredientsArray);
            }

            List<MethodOC> newMethod = recipeToEdit.getMethod();
            boolean editingMethod = true;
            while (editingMethod) {
                System.out.println("Введіть новий метод приготування (fry, boil, freeze, bake, stew),i введіть 'done':");
                String newMethodInput = scanner.nextLine();
                if (newMethodInput.equalsIgnoreCase("done")) {
                    editingMethod = false;
                } else {
                    MethodOC methodOC = MethodOC.addMethod(newMethodInput);
                    if (methodOC != null) {
                        newMethod.add(methodOC);
                    }
                }
            }

            if (!newName.isEmpty()) {
                recipeToEdit.setName(newName);
            }
            recipeToEdit.setIngredients(newIngredients);
            recipeToEdit.setMethod(newMethod);

            System.out.println("Рецепт відредаговано.");
        } else {
            System.out.println("Рецепт з такою назвою не знайдено.");
        }
    }
    @Override
    public void searchRecipe() {
        if (recipes.isEmpty()) {
            System.out.println("Немає доступних рецептів для пошуку.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть назву рецепту для пошуку:");
        String name = scanner.nextLine();

        boolean found = false;
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                System.out.println("Назва: " + recipe.getName());
                System.out.println("Інгредієнти: " + recipe.getIngredients());
                System.out.println("Метод приготування: " + recipe.getMethod());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Рецепт з такою назвою не знайдено.");
        }
    }
    @Override
    public void save() {
        gsonConverter.writeJson(recipes, "C:\\java\\projectPr\\recipes.json");
        System.out.println("Рецепти збережено");
    }
    @Override
    public void exitWithoutSaving() {
        System.out.println("Завершено...");
    }

}
