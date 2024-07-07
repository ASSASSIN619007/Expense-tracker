import java.io.*;
import java.util.*;

public class User implements Serializable {
    private String username;
    private String password;
    private List<Expense> expenses = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void addExpense() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        expenses.add(new Expense(date, category, amount));
        System.out.println("Expense added successfully.");
    }

    public void viewExpenses() {
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    public void viewCategorySummation() {
        Map<String, Double> categorySum = new HashMap<>();
        for (Expense expense : expenses) {
            categorySum.put(expense.getCategory(),
                    categorySum.getOrDefault(expense.getCategory(), 0.0) + expense.getAmount());
        }
        for (Map.Entry<String, Double> entry : categorySum.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
