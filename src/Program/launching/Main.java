package Program.launching;

import java.util.Scanner;
import Program.func.AppRecipeRepository;
import Program.func.RecipeRepository;

public class Main {
    public static void main(String[] args) {
        RecipeRepository repository = new AppRecipeRepository();
        Scanner scanner = new Scanner(System.in);
        String command;
        repository.print();
        while (true) {
            System.out.println("Введіть команду (add, delete, edit, search, ingredients, save, exit):");
            command = scanner.nextLine();
            switch (command) {
                case "add":
                    repository.addRecipe();
                    repository.print();
                    break;
                case "delete":
                    repository.deleteRecipe();
                    repository.print();
                    break;
                case "edit":
                    repository.editRecipe();
                    repository.print();
                    break;
                case "search":
                    repository.searchRecipe();
                    break;
                case "ingredients":
                    repository.searchByIngredient();
                    break;
                case "save":
                    repository.saveRecipe();
                    break;
                case "exit":
                    repository.exit();
                    System.exit(0);
                    break;
                default:
                    System.out.println("-");
            }

        }
    }
}