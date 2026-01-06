import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in); // Ξεκινάει το μενού επιλογών του χρήστη
        int x = 0;
        System.out.println("ΕΠΙΛΟΓΗ ΠΡΟΥΠΟΛΟΓΙΣΜΟΥ ΕΤΟΥΣ¨:2023, 2024, 2025");
        int yearSelection = scanner.nextInt();
         String ministriesFile = "ministries" + yearSelection + ".json";
                 String revenuesFile = "revenues" + yearSelection + ".json";
                String expensesFile = "expenses" + yearSelection + ".json";

                 Ministries[] ministries = MinistryLoader.load(ministriesFile);
                Revenues[] revenues = RevenuesLoader.load(revenuesFile);
                Expenses[] expenses = ExpensesLoader.load(expensesFile);
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
                MainMenu.mainMeNuOptionsPrinter();
                x = scanner.nextInt();
                 ExecuteMenu.executeMenu(x, ministries, revenues, expenses);

              
            } 
        scanner.close();
    }
}
}


  



        
