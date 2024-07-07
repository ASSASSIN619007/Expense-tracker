import java.util.*;
import java.io.*;

public class ExpenseTracker {
    private static Map<String, User> users = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadUsers();
        while (true) {
            System.out.println("Welcome to Expense Tracker");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    saveUsers();
                    System.exit(0);
            }
        }
    }

    private static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (users.containsKey(username)) {
            System.out.println("Username already exists.");
        } else {
            users.put(username, new User(username, password));
            System.out.println("User registered successfully.");
        }
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            userMenu(user);
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private static void userMenu(User user) {
        while (true) {
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Category-wise Summation");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    user.addExpense();
                    break;
                case 2:
                    user.viewExpenses();
                    break;
                case 3:
                    user.viewCategorySummation();
                    break;
                case 4:
                    return;
            }
        }
    }

    private static void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("users.dat"))) {
            users = (HashMap<String, User>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, no users to load
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("users.dat"))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
