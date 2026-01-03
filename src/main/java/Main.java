import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in); // Ξεκινάει το μενού επιλογών του χρήστη
        int x = 0;
        System.out.println("ΕΠΙΛΟΓΗ ΠΡΟΥΠΟΛΟΓΙΣΜΟΥ ΕΤΟΥΣ¨:2023, 2024, 2025");
        int yearSelection = scanner.nextInt();
        boolean y = false;
        int choice = 0;
        while (!y) {
        System.out.println("Θες γραφική απεικόνιση του μενού ή να το τρέξεις από γραμμή εντολών;");
        System.out.println("δώσε 200 για γραφική απεικόνιση και 300 για γραμμή εντολών");
            try {
                choice = scanner.nextInt();
                if (choice == 200 || choice == 300) { 
                    y = true; 
                } else {
                    System.out.println("Λάθος επιλογή! Παρακαλώ πατήστε 200 ή 300.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Σφάλμα: Πρέπει να εισάγετε αριθμό (200 ή 300), όχι γράμματα.");
                scanner.next(); 
            }
        }
        if (choice == 200) {
            MenuGraph.displayMenu(yearSelection);
        } else if (choice == 300) {
            while (x != 100) {
                System.out.println("\n--- ΜΕΝΟΥ ΕΠΙΛΟΓΩΝ ---");
                System.out.println("0: Στοιχεία υπουργείων");
                System.out.println("1: Ποσοστά προϋπολογισμού");
                System.out.println("2: Έσοδα");
                System.out.println("3: Έξοδα");
                System.out.println("4: Επιλογή υπουργείου");
                System.out.println("6: Γραφήματα");
                System.out.println("100: Έξοδος");
                

            while (x != 100) {
                MainMenu.mainMeNuOptionsPrinter();

                x = scanner.nextInt();
              
            } 
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
}


  



        