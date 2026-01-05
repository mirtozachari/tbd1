import java.util.Scanner;

public class PrimeMinisterApp {
    private final BudgetView view;
    private final BudgetManager manager;

    public PrimeMinisterApp() {
        this.view = new BudgetView();
        this.manager = new BudgetManager();
    }

    public void start() {
        DatabaseConfig.initializeDatabase(2025);
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println(ConsoleUtils.BOLD + "=== ΣΥΣΤΗΜΑ ΔΙΑΧΕΙΡΙΣΗΣ ΚΡΑΤΙΚΟΥ ΠΡΟΫΠΟΛΟΓΙΣΜΟΥ 2026 ===" + ConsoleUtils.RESET);
            runApplicationLoop(scanner);
        }
    }

    private void runApplicationLoop(Scanner scanner) {
        boolean running = true;
        while (running) {
            view.showDashboard();
            printMenu();
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                running = handleUserChoice(choice, scanner);
            } else {
                System.out.println(ConsoleUtils.RED + "Παρακαλώ δώστε αριθμό." + ConsoleUtils.RESET);
                scanner.next();
                scanner.nextLine();
            }
            if (running) {
                System.out.println("\nΠιέστε Enter για συνέχεια...");
                scanner.nextLine();
            }
        }
    }

    private void printMenu() {
        System.out.println("\n" + ConsoleUtils.BLUE + "--- ΜΕΝΟΥ ΕΝΕΡΓΕΙΩΝ ---" + ConsoleUtils.RESET);
        System.out.println("1. Προβολή Λίστας Υπουργείων & Φορέων");
        System.out.println("2. Τροποποίηση Προϋπολογισμού (Εισαγωγή Αλλαγής)");
        System.out.println("3. Προβολή Ιστορικού Αλλαγών");
        System.out.println("4. Έξοδος");
        System.out.print("Επιλέξτε ενέργεια (1-4): ");
    }

    private boolean handleUserChoice(int choice, Scanner scanner) {
        switch (choice) {
            case 1:
                view.listMinistries();
                return true;
            case 2:
                manager.modifyBudget(scanner);
                return true;
            case 3:
                view.showHistory();
                return true;
            case 4:
                System.out.println("Έξοδος από το σύστημα.");
                return false;
            default:
                System.out.println(ConsoleUtils.RED + "Μη έγκυρη επιλογή." + ConsoleUtils.RESET);
                return true;
        }
    }
}