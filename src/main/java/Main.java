import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);// ξεκινάει το μενού επιλογών του χρήστη
        System.out.println("Δώσε αριθμό επιλογής για το μενού");
        int x = scanner.nextInt();
        Ministries[] ministries = MinistryLoader.load("src/main/resources/config/ministries25.json");
        Revenues[] revenues = RevenuesLoader.load("src/main/resources/config/revenue25.json");
        Expenses[] expenses = ExpensesLoader.load("src/main/resources/config/expenses25.json");
        switch (x) {
            case 0:
            case 0:// εμφάνιση στοιχείων όλων των υπουργείων
        MinistryPrinter ministryPrinter = new MinistryPrinter(ministries);
        ministryPrinter.display();
        break;
            case 1://εμφάνιση ποσοστών κάθε υπουργείου επί του συνολικού προϋπολογισμού
        Ministrypercentage ministryPercentage = new Ministrypercentage(ministries);
        ministryPercentage.display();
        break;
            case 2://εμφάνιση όλων των εσόδων 
        RevenuesPrinter revenuesPrinter = new RevenuesPrinter(revenues);
        revenuesPrinter.display();
        break;
            case 3://εμφάνιση όλων των εξόδων
        ExpensesPrinter expensesPrinter = new ExpensesPrinter(expenses);
        expensesPrinter.display();
        break;
            case 4//εμφάνιση προϋπολογισμού
            :
        System.out.println("Δες τα στοιχεία του Υπουργείου που σε ενδιαφέρει:");
        MenuForMinistries menu = new MenuForMinistries(ministries);
        menu.Menu();
        break;
        default:
            System.out.println("Άκυρος αριθμός");
        }
    }
}
