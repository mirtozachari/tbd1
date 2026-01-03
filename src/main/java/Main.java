import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in); // Ξεκινάει το μενού επιλογών του χρήστη
        int x = 0;
        System.out.println("ΕΠΙΛΟΓΗ ΠΡΟΙΠΟΛΟΓΙΣΜΟΥ ΕΤΟΥΣ¨:2023, 2024, 2025");
        int yearSelection = scanner.nextInt();

            while (x != 100) {
                MainMenu.mainMeNuOptionsPrinter();
                x = scanner.nextInt();

                String ministriesFile = "ministries" + yearSelection + ".json";
                String revenuesFile = "revenues" + yearSelection + ".json";
                String expensesFile = "expenses" + yearSelection + ".json";

                Ministries[] ministries = MinistryLoader.load(ministriesFile);
                Revenues[] revenues = RevenuesLoader.load(revenuesFile);
                Expenses[] expenses = ExpensesLoader.load(expensesFile);


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

            

        scanner.close();
    }
}


  



        