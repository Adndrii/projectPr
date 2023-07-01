package Program.launching;

import java.util.Scanner;
import Program.func.AppRecipeRepository;
import Program.func.RecipeRepository;

public class Main {
    public static void main(String[] args) {
        RecipeRepository repository = new AppRecipeRepository();
        Scanner scanner = new Scanner(System.in);
        String command;

        do {
            System.out.println("Введіть команду (add, delete, edit, search, save, exit):");
            command = scanner.nextLine();

            switch (command) {
                case "add":
                    repository.addRecipe();
                    break;
                case "delete":
                    repository.deleteRecipe();
                    break;
                case "edit":
                    repository.editRecipe();
                    break;
                case "search":
                    repository.searchRecipe();
                    break;
                case "save":
                    repository.saveAndExit();
                    break;
                case "exit":
                    repository.exitWithoutSaving();
                    break;
                default:
                    System.out.println("Невідома команда. Спробуйте ще раз.");
            }
        } while (!command.equals("exit"));
    }
}