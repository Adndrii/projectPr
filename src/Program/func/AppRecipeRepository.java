package Program.func;

import Program.func.info.MethodOC;
import Program.func.info.Recipe;
import Program.func.info.GsonConverter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.Desktop;
import java.net.URI;


public class AppRecipeRepository implements RecipeRepository {
    private List<Recipe> recipes;
    private final String filePath = "C:\\java\\projectPr\\recipes.json";
    Scanner scanner = new Scanner(System.in);
    private final GsonConverter gsonConverter;
    public AppRecipeRepository() {
        recipes = new ArrayList<>();
        gsonConverter = new GsonConverter();
        loadRecipes();
    }

    @Override
    public void addRecipe() {
        System.out.println("Введіть назву рецепту:");
        String name = scanner.nextLine();//name
        System.out.println("Введіть інгредієнти:");
        String ingredientsInput = scanner.nextLine();//ingredients
        String[] ingredientsArray = ingredientsInput.split(",");
        List<String> ingredients = List.of(ingredientsArray);
        List<MethodOC> method = new ArrayList<>();
        boolean addingMethod = true;
        while (addingMethod) {
            System.out.println("Введіть метод приготування (fry, boil, freeze, bake, stew, grill, roast, saute, steam, smoke, poach, braise, broil, marinate),i введіть 'done':");
            String methodInput = scanner.nextLine();//methods of cooking
            if (methodInput.equalsIgnoreCase("done")) {
                addingMethod = false;
            } else {
                MethodOC methodOC = MethodOC.addMethod(methodInput);
                if (methodOC != null) {
                    method.add(methodOC);
                }
            }
        }
        System.out.println("Введіть посилання ютуб рецепту:");
        String guide = scanner.nextLine();//youtubeLink

        Recipe recipe = new Recipe(name, ingredients, method, guide);
        recipes.add(recipe);

        System.out.println("Рецепт додано.");
    }
    public void print() {
        System.out.println("Всі доступні рецпти\n---------------");
        for (Recipe recipe : recipes) {
            System.out.println("- "+recipe.getName());
        }
        System.out.println("\n---------------");
    }
        @Override
    public void deleteRecipe() {
        if (recipes.isEmpty()) {
            System.out.println("Немає таких рецептів. т_т");
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
            System.out.println("Немає таких рецептів.т_т");
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
            System.out.println("Посилання на ютуб: "+recipeToEdit.getGuide());
            System.out.println();

            System.out.println("Введіть назву рецепту (або натисніть Enter, щоб залишити без змін):");
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
            System.out.println("Немає таких рецептів. т_т");
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
                System.out.println("watch on youtube?");
                String yt = scanner.next();
                switch (yt){
                    case "yes":
                        try {
                            URI uri = new URI(recipe.getGuide());
                            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                                Desktop.getDesktop().browse(uri);
                            } else {
                                System.out.println("Гіперпосилання не підтримуються на даній платформі.");
                            }
                        } catch (Exception e) {
                            System.out.println("Сталася помилка при відкритті гіперпосилання: " + e.getMessage());
                        }
                        break;
                    default:
                        break;
                }
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Немає такого рецепта.");
        }

    }
    @Override
    public void saveRecipe() {
        gsonConverter.writeJson(recipes, filePath);
        System.out.println("Збережено ");
    }
    @Override
    public void exitWithoutSaving() {
        System.out.println("Завершено...");
    }
    private void loadRecipes() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            recipes = gsonConverter.fromJson(json);
        } catch (IOException e) {
            System.out.println("Помилка");
        }
    }

    public void searchByIngredient() {
        if (recipes.isEmpty()) {
            System.out.println("Немає таких рецептів.");
            return;
        }
        System.out.println("Введіть інгредієнти для пошуку (через кому):");
        String ingredientsInput = scanner.nextLine();
        String[] ingredientsArray = ingredientsInput.split(",");
        List<String> searchIngredients = List.of(ingredientsArray);

        List<Recipe> foundRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            List<String> recipeIngredients = recipe.getIngredients();

            if (recipeIngredients.containsAll(searchIngredients)) {
                foundRecipes.add(recipe);
            }
        }

        if (foundRecipes.isEmpty()) {
            System.out.println("Немає рецептів з такими інгридієнтами, можете додати за допомогою команди 'add'.");
        } else {
            System.out.println("Рецепти з інгредієнтами " + searchIngredients + ":");
            for (Recipe recipe : foundRecipes) {
                System.out.println("Назва: " + recipe.getName());
                System.out.println("Інгредієнти: " + recipe.getIngredients());
                System.out.println("Метод приготування: " + recipe.getMethod());
                System.out.println();
            }
        }
    }



}
