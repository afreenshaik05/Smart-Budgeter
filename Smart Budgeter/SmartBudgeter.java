import java.util.*;

public class SmartBudgeter {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, MonthData> budgetData = new HashMap<>();
     private static User currentUser;
    private static Budget budget;

    public static void main(String[] args) {
        System.out.println("Welcome to Smart Budgeter!");

        while (true) {
            System.out.println("1. Sign Up");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("Thank you for using Smart Budgeter!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
     private static void createAccount() {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Set password: ");
        String password = scanner.nextLine();
       currentUser = new User(username, password);
        System.out.println("Thanks for signing up!");
        showMenu();
    }

    private static void login() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // For simplicity, we use hardcoded credentials here.
        if ("user".equals(username) && "password".equals(password)) {
            currentUser = new User(username, password);
            System.out.println("Login successful!");
            showMenu();
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

     private static void showMenu() {
        while (true) {
            System.out.println("\n~~~~ Main Menu ~~~~");
            System.out.println("1. Add/Update Month's Data");
            System.out.println("2. Set Financial Goals");
            System.out.println("3. View Monthly Report");
            System.out.println("4. Log Out");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addMonthData();
                    break;
                case 2:
                    setFinancialGoals();
                    break;
                case 3:
                     viewMonthlyReport();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addMonthData() {
        System.out.print("Enter the month (January, February, etc...): ");
        String month = scanner.nextLine();
        System.out.print("Enter your income for " + month + ": $");
        double income = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        MonthData monthData = new MonthData(income);

        while (true) {
            System.out.print("Enter an expense category (Type 'done' when finished ): ");
            String category = scanner.nextLine();
            if ("done".equalsIgnoreCase(category)) {
                break;
            }
            System.out.print("Enter the expense amount for " + category + ": $");
            double expenseAmount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline character

            monthData.addExpense(expenseAmount);
             System.out.print("Enter the date (optional): ");
            String date = scanner.nextLine();
        }

        budgetData.put(month, monthData);
        System.out.println("Data for " + month + " added/updated successfully!");
    }

    private static void viewMonthlyReport() {
        if (budgetData.isEmpty()) {
            System.out.println("No data available. Please add month's data first.");
            return;
        }

        System.out.println("\n~~~~~ Monthly Report ~~~~~");
        for (Map.Entry<String, MonthData> entry : budgetData.entrySet()) {
            String month = entry.getKey();
            MonthData monthData = entry.getValue();

            System.out.println(month + " Budget Summary:");
            System.out.println("Income: $" + monthData.getIncome());
            System.out.println("Total Expenses: $" + monthData.getTotalExpenses());
            System.out.println("Remaining Budget: $" + monthData.getRemainingBudget());
            System.out.println();
        }
    }
    private static void setFinancialGoals() {
        List<String> goals = new ArrayList<>();
        System.out.println("Enter financial goals (Type 'done' when finished):");
        while (true) {
            String goal = scanner.nextLine();
            if ("done".equalsIgnoreCase(goal)) {
                break;
            }
            goals.add(goal);
        }
        System.out.println("Financial goals are set successfully!");
    }
}
class MonthData {
    private double income;
    private double totalExpenses;
    private List<String> goals;

    public MonthData(double income) {
        this.income = income;
        this.totalExpenses = 0;
    }

    public void addExpense(double amount) {
        totalExpenses += amount;
    }

    public double getIncome() {
        return income;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getRemainingBudget() {
        return income - totalExpenses;
    }
    public List<String> getFinancialGoals(List<String> goals) {
        return goals;
    }
}
    class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    }
