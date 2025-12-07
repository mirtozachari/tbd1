import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Ξεκινάει το μενού επιλογών του χρήστη
        
        int x = 0;

        while (x != 100) {     
            System.out.println("Δώσε αριθμό επιλογής για το μενού");
            System.out.println("Για εμφάνιση στοιχείων όλων των υπουργείων πάτα 0");
            System.out.println("Για εμφάνιση ποσοστών κάθε υπουργείου επί του συνολικού προϋπολογισμού πάτα 1");
            System.out.println("Για εμφάνιση όλων των εσόδων πάτα 2");
            System.out.println("Για εμφάνιση όλων των εξόδων πάτα 3");
            System.out.println("Για εμφάνιση στοιχείων όλων των υπουργείων και επιλογή ενός πάτα 4");
            System.out.println("Για εισαγωγή αλλαγών πάτα 5");
            System.out.println("Για να εμφανιστούν γραφήματα πάτα 6:");
            System.out.println("Για έξοδο από την εφαρμογή πάτα 100");
            x = scanner.nextInt();

            Ministries[] ministries = MinistryLoader.load("src/main/resources/config/ministries25.json");
            Revenues[] revenues = RevenuesLoader.load("src/main/resources/config/revenue25.json");
            Expenses[] expenses = ExpensesLoader.load("src/main/resources/config/expenses25.json");

            switch (x) {
                case 0: // Εμφάνιση στοιχείων όλων των υπουργείων
                    MinistryPrinter ministryPrinter = new MinistryPrinter(ministries);
                    ministryPrinter.display();
                    break;

                case 1: // Εμφάνιση ποσοστών κάθε υπουργείου επί του συνολικού προϋπολογισμού
                    Ministrypercentage ministryPercentage = new Ministrypercentage(ministries);
                    ministryPercentage.display();
                    break;

                case 2: // Εμφάνιση όλων των εσόδων 
                    RevenuesPrinter revenuesPrinter = new RevenuesPrinter(revenues);
                    revenuesPrinter.display();
                    break;

                case 3: // Εμφάνιση όλων των εξόδων
                    ExpensesPrinter expensesPrinter = new ExpensesPrinter(expenses);
                    expensesPrinter.display();
                    break;

                case 4: // Εμφάνιση προϋπολογισμού συγκεκριμένου υπουργείου που επιλέγει ο χρήστης
                    System.out.println("Δες τα στοιχεία του Υπουργείου που σε ενδιαφέρει:");
                    MenuForMinistries menu = new MenuForMinistries(ministries);
                    menu.Menu();
                    break;
                case 5: // Δυνατότητα εισαγωγής αλλαγών
                    System.out.println("Δώσε τις επιθυμητές αλλαγές:");
                    break;
                case 6: // Γραφήματα
                System.out.println("Δες τα γραφήματα:");
                 try {
            diagramministries.diagram();  
                 } catch (Exception e) {
            e.printStackTrace();
            }
                break;

                case 100: // Έξοδος από την εφαρμογή
                    System.out.println("Καληνύχτα κύριε πρωθυπουργέ!");
                    break;
                
                default:
                    System.out.println("Άκυρος αριθμός");
            }
        }
    }
}
