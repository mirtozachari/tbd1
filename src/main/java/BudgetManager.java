import java.sql.*;
import java.util.Scanner;

public class BudgetManager {

    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";
    private final String BOLD = "\u001B[1m";

    public void modifyBudget(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("\nΔώσε τον Κωδικό του Υπουργείου προς αλλαγή (π.χ. 1020): ");
        if (!scanner.hasNextInt()) { scanner.next(); return; }
        int code = scanner.nextInt();

        String query = "SELECT name, total_amount FROM Ministry_Budget WHERE code = ?";
        PreparedStatement pst = conn.prepareStatement(query);
        pst.setInt(1, code);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String name = rs.getString("name");
            double oldAmount = rs.getDouble("total_amount");

            System.out.println("Επιλέξατε: " + BOLD + name + RESET);
            System.out.printf("Τρέχον Ποσό: %,.2f €\n", oldAmount);

            if (code == 1024) {
                System.out.println(RED + "ΠΡΟΣΟΧΗ: Αυτός ο κωδικός περιλαμβάνει την εξυπηρέτηση του Δημοσίου Χρέους!" + RESET);
            }

            System.out.print("Δώσε το ΝΕΟ συνολικό ποσό: ");
            if (!scanner.hasNextDouble()) { scanner.next(); return; }
            double newAmount = scanner.nextDouble();

            if (newAmount < 0) {
                System.out.println(RED + "ΣΦΑΛΜΑ: Ο προϋπολογισμός δεν μπορεί να είναι αρνητικός!" + RESET);
                return;
            }
            
            if (newAmount < oldAmount * 0.5) {
                System.out.print(YELLOW + "ΠΡΟΕΙΔΟΠΟΙΗΣΗ: Επιχειρείτε τεράστια περικοπή (>50%). Είστε σίγουρος; (ν/ο): " + RESET);
                String confirm = scanner.next();
                if (!confirm.equalsIgnoreCase("ν")) {
                    System.out.println("Η αλλαγή ακυρώθηκε.");
                    return;
                }
            }

            conn.setAutoCommit(false);
            try {
                String updateSql = "UPDATE Ministry_Budget SET total_amount = ? WHERE code = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setDouble(1, newAmount);
                updateStmt.setInt(2, code);
                updateStmt.executeUpdate();

                String logSql = "INSERT INTO ChangeLog (ministry_code, old_total, new_total) VALUES (?, ?, ?)";
                PreparedStatement logStmt = conn.prepareStatement(logSql);
                logStmt.setInt(1, code);
                logStmt.setDouble(2, oldAmount);
                logStmt.setDouble(3, newAmount);
                logStmt.executeUpdate();
                
                conn.commit();
                System.out.println(GREEN + "Η αλλαγή καταχωρήθηκε επιτυχώς!" + RESET);

            } catch (SQLException e) {
                conn.rollback();
                System.out.println(RED + "Σφάλμα κατά την αποθήκευση. Έγινε επαναφορά." + RESET);
                e.printStackTrace();
            }
            conn.setAutoCommit(true);

        } else {
            System.out.println(RED + "Δεν βρέθηκε υπουργείο με αυτόν τον κωδικό." + RESET);
        }
    }
}