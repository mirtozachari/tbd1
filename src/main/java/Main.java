import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int x = 0;
        

        //Select between running the PrimeMinisterApp or the custom menu system
        System.out.println("Θες να τρέξεις την εφαρμογή διαχείρισης προϋπολογισμού ή μόνο να διαβάσεις τα στοιχεία του κανονικού προυπολογισμού;");
        System.out.println("Δώσε 1 για διαχείριση προϋπολογισμού ή 2 για απλή ανάγνωση στοιχείων:");
        int apprunner = scanner.nextInt();

        if (apprunner == 2) {
            //Year selection
            System.out.println("ΕΠΙΛΟΓΗ ΠΡΟΥΠΟΛΟΓΙΣΜΟΥ ΕΤΟΥΣ¨:2023, 2024, 2025");
            int yearSelection = scanner.nextInt();

            //Load the chosen year files
            String ministriesFile = "ministries" + yearSelection + ".json";
            String revenuesFile = "revenues" + yearSelection + ".json";
            String expensesFile = "expenses" + yearSelection + ".json";
            Ministries[] ministries = MinistryLoader.load(ministriesFile);
            Revenues[] revenues = RevenuesLoader.load(revenuesFile);
            Expenses[] expenses = ExpensesLoader.load(expensesFile);

            boolean y = false;
            int choice = 0;

            //Menu type selection, graphical or command line
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

            //Execute the chosen menu type
            if (choice == 200) {
                MenuGraph.displayMenu(yearSelection);
            } else if (choice == 300) {

                while (x != 100) {
                    MainMenu.mainMeNuOptionsPrinter();
                    x = scanner.nextInt();
                    ExecuteMenu.executeMenu(x, ministries, revenues, expenses);
                } 
            }    
        } else if (apprunner == 1) {

            //Run the PrimeMinisterApp
            PrimeMinisterApp app = new PrimeMinisterApp();
            app.start();
        } else {
            System.out.println("Λάθος επιλογή! Τρέξε ξανά την εφαρμογή και δώσε 1 ή 2.");
        }
        scanner.close();
    }
}


  



        
