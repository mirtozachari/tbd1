import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class PrimeMinisterApp {

    private final String BLUE = "\u001B[34m";
    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String BOLD = "\u001B[1m";

    private final BudgetView view;
    private final BudgetManager manager;

    public PrimeMinisterApp() {
        this.view = new BudgetView();
        this.manager = new BudgetManager();
    }

    public void start() {
        try (Connection conn = DatabaseConfig.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            DatabaseConfig.initializeDatabase(conn);

            System.out.println(BOLD + "=== ΣΥΣΤΗΜΑ ΔΙΑΧΕΙΡΙΣΗΣ ΚΡΑΤΙΚΟΥ ΠΡΟΫΠΟΛΟΓΙΣΜΟΥ 2026 ===" + RESET);

            while (true) {
                view.showDashboard(conn);

                System.out.println("\n" + BLUE + "--- ΜΕΝΟΥ ΕΝΕΡΓΕΙΩΝ ---" + RESET);
                System.out.println("1. Προβολή Λίστας Υπουργείων & Φορέων");
                System.out.println("2. Τροποποίηση Προϋπολογισμού (Εισαγωγή Αλλαγής)");
                System.out.println("3. Προβολή Ιστορικού Αλλαγών");
                System.out.println("4. Έξοδος");
                System.out.print("Επιλέξτε ενέργεια (1-4): ");

                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (choice) {
                        case 1:
                            view.listMinistries(conn);
                            break;
                        case 2:
                            manager.modifyBudget(conn, scanner);
                            break;
                        case 3:
                            view.showHistory(conn);
                            break;
                        case 4:
                            System.out.println("Έξοδος από το σύστημα.");
                            return;
                        default:
                            System.out.println(RED + "Μη έγκυρη επιλογή." + RESET);
                    }
                } else {
                    System.out.println(RED + "Παρακαλώ δώστε αριθμό." + RESET);
                    scanner.next(); 
                }
                
                System.out.println("\nΠιέστε Enter για συνέχεια...");
                scanner.nextLine();
            }

        } catch (SQLException e) {
            System.out.println(RED + "ΣΦΑΛΜΑ ΣΥΝΔΕΣΗΣ ΒΑΣΗΣ ΔΕΔΟΜΕΝΩΝ!" + RESET);
            e.printStackTrace();
        }
    }
}