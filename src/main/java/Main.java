import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Δώσε αριθμό επιλογής για το μενού");
        int x = scanner.nextInt();
        Ministries[] ministries = MinistryLoader.load("src/main/resources/config/ministries25.json");
        Revenues[] revenues = RevenuesLoader.load("src/main/resources/config/revenue25.json");
<<<<<<< HEAD
        Expenses[] expenses = ExpensesLoader.load("src/main/resources/config/expenses25.json");
=======
        switch (x) {
            case 0:
>>>>>>> 73b31480227b27f94b8a29797063ac25e41f339e
        MinistryPrinter ministryPrinter = new MinistryPrinter(ministries);
        ministryPrinter.display();
        break;
            case 1:
        Ministrypercentage ministryPercentage = new Ministrypercentage(ministries);
        ministryPercentage.display();
        break;
            case 2:
        RevenuesPrinter revenuesPrinter = new RevenuesPrinter(revenues);
        revenuesPrinter.display();
<<<<<<< HEAD
        ExpensesPrinter expensesPrinter = new ExpensesPrinter(expenses);
        expensesPrinter.display();
=======
        break;
            case 3:
        System.out.println("Δες τα στοιχεία του Υπουργείου που σε ενδιαφέρει:");
        break;
        }
>>>>>>> 73b31480227b27f94b8a29797063ac25e41f339e
    }
}