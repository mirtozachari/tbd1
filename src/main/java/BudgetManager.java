import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class BudgetManager {
    private static final int DEBT_MINISTRY_CODE = 1024;
    private static final double SIGNIFICANT_CUT_THRESHOLD = 0.5;

    public void modifyBudget(Scanner scanner) {
        int code = promptForMinistryCode(scanner);
        if (code == -1) return;

        DatabaseConfig.Ministry ministry = DatabaseConfig.ministries.get(code);
        if (ministry == null) {
            System.out.println(ConsoleUtils.RED + "Δεν βρέθηκε υπουργείο με αυτόν τον κωδικό." + ConsoleUtils.RESET);
            return;
        }

        displayCurrentDetails(ministry);

        double newAmount = promptForNewAmount(scanner);
        if (newAmount < 0) return;

        if (!confirmChange(scanner, ministry.totalAmount, newAmount)) {
            System.out.println("Η αλλαγή ακυρώθηκε.");
            return;
        }

        updateBudget(ministry, newAmount);
    }

    private int promptForMinistryCode(Scanner scanner) {
        System.out.print("\nΔώσε τον Κωδικό του Υπουργείου προς αλλαγή (π.χ. 1020): ");
        String input = scanner.next();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(ConsoleUtils.RED + "Σφάλμα: Παρακαλώ εισάγετε έγκυρο αριθμητικό κωδικό." + ConsoleUtils.RESET);
            return -1;
        }
    }

    private void displayCurrentDetails(DatabaseConfig.Ministry ministry) {
        System.out.println("Επιλέξατε: " + ConsoleUtils.BOLD + ministry.name + ConsoleUtils.RESET);
        System.out.printf("Τρέχον Ποσό: %,.2f €\n", ministry.totalAmount);
        if (ministry.code == DEBT_MINISTRY_CODE) {
            System.out.println(ConsoleUtils.RED + "ΠΡΟΣΟΧΗ: Αυτός ο κωδικός περιλαμβάνει την εξυπηρέτηση του Δημοσίου Χρέους!" + ConsoleUtils.RESET);
        }
    }

    private double promptForNewAmount(Scanner scanner) {
        System.out.print("Δώσε το ΝΕΟ συνολικό ποσό: ");
        String input = scanner.next();
        try {
            input = input.replace(",", ".");
            double amount = Double.parseDouble(input);
            if (amount < 0) {
                System.out.println(ConsoleUtils.RED + "ΣΦΑΛΜΑ: Ο προϋπολογισμός δεν μπορεί να είναι αρνητικός!" + ConsoleUtils.RESET);
                return -1;
            }
            return amount;
        } catch (NumberFormatException e) {
            System.out.println(ConsoleUtils.RED + "Σφάλμα: Παρακαλώ εισάγετε έγκυρο ποσό." + ConsoleUtils.RESET);
            return -1;
        }
    }

    private boolean confirmChange(Scanner scanner, double oldAmount, double newAmount) {
        if (newAmount < oldAmount * SIGNIFICANT_CUT_THRESHOLD) {
            System.out.print(ConsoleUtils.YELLOW + "ΠΡΟΕΙΔΟΠΟΙΗΣΗ: Επιχειρείτε τεράστια περικοπή (>50%). Είστε σίγουρος; (ν/ο): " + ConsoleUtils.RESET);
            String confirm = scanner.next().trim().toLowerCase();
            Set<String> validYes = new HashSet<>(Arrays.asList("ν", "n", "y", "yes", "ναι"));
            return validYes.contains(confirm);
        }
        return true;
    }

    private void updateBudget(DatabaseConfig.Ministry ministry, double newAmount) {
        double oldAmount = ministry.totalAmount;
        ministry.totalAmount = newAmount;
        DatabaseConfig.changeLogs.add(new DatabaseConfig.ChangeLog(ministry.name, oldAmount, newAmount));
        System.out.println(ConsoleUtils.GREEN + "Η αλλαγή καταχωρήθηκε επιτυχώς!" + ConsoleUtils.RESET);
    }
}