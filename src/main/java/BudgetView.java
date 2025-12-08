import java.sql.*;

public class BudgetView {

    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";

    public void showDashboard(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        ResultSet rsRev = stmt.executeQuery("SELECT SUM(amount) FROM State_Revenue");
        double totalRevenue = 0;
        if (rsRev.next()) totalRevenue = rsRev.getDouble(1);

        ResultSet rsGenExp = stmt.executeQuery("SELECT SUM(amount) FROM State_General_Expenses");
        double totalGeneralExpenses = 0;
        if (rsGenExp.next()) totalGeneralExpenses = rsGenExp.getDouble(1);

        ResultSet rsMinExp = stmt.executeQuery("SELECT SUM(total_amount) FROM Ministry_Budget");
        double totalMinistryExpenses = 0;
        if (rsMinExp.next()) totalMinistryExpenses = rsMinExp.getDouble(1);

        double balance = totalRevenue - totalGeneralExpenses;

        System.out.println("\n=======================================================");
        System.out.printf("ΣΥΝΟΛΙΚΑ ΕΣΟΔΑ (Άρθρο 1):         %,20.2f €\n", totalRevenue);
        System.out.printf("ΣΥΝΟΛΙΚΑ ΕΞΟΔΑ (Άρθρο 1):         %,20.2f €\n", totalGeneralExpenses);
        System.out.println("-------------------------------------------------------");

        if (balance >= 0) {
            System.out.printf("ΔΗΜΟΣΙΟΝΟΜΙΚΟ ΑΠΟΤΕΛΕΣΜΑ:  " + GREEN + "+%,20.2f € (ΠΛΕΟΝΑΣΜΑ)" + RESET + "\n", balance);
        } else {
            System.out.printf("ΔΗΜΟΣΙΟΝΟΜΙΚΟ ΑΠΟΤΕΛΕΣΜΑ:  " + RED + "%,20.2f € (ΕΛΛΕΙΜΜΑ)" + RESET + "\n", balance);
        }

        System.out.println("-------------------------------------------------------");
        System.out.printf("Σύνολο Κατανεμημένο σε Υπουργεία: %,20.2f €\n", totalMinistryExpenses);

        double diff = totalGeneralExpenses - totalMinistryExpenses;
        if (Math.abs(diff) > 1000) {
            System.out.println(YELLOW + "ΠΡΟΣΟΧΗ: Υπάρχει απόκλιση " + String.format("%,.2f", diff) + "€ μεταξύ Γενικού Συνόλου και Υπουργείων!" + RESET);
        } else {
            System.out.println(GREEN + "✓ Η κατανομή στα Υπουργεία συμφωνεί με το Γενικό Σύνολο." + RESET);
        }
        System.out.println("=======================================================");
    }

    public void listMinistries(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT code, name, total_amount FROM Ministry_Budget ORDER BY code");

        System.out.println("\n--- ΛΙΣΤΑ ΥΠΟΥΡΓΕΙΩΝ & ΦΟΡΕΩΝ ---");
        System.out.printf("%-6s %-50s %20s\n", "ΚΩΔ.", "ΟΝΟΜΑΣΙΑ", "ΠΡΟΫΠΟΛΟΓΙΣΜΟΣ");
        System.out.println("--------------------------------------------------------------------------------");
        while (rs.next()) {
            System.out.printf("%-6d %-50s %,20.2f €\n",
                    rs.getInt("code"),
                    truncate(rs.getString("name"), 50),
                    rs.getDouble("total_amount"));
        }
    }

    public void showHistory(Connection conn) throws SQLException {
        String sql = "SELECT b.name, c.old_total, c.new_total, c.change_date " +
                     "FROM ChangeLog c JOIN Ministry_Budget b ON c.ministry_code = b.code " +
                     "ORDER BY c.change_date DESC";
        
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        System.out.println("\n--- ΙΣΤΟΡΙΚΟ ΑΛΛΑΓΩΝ (LOG) ---");
        boolean found = false;
        while (rs.next()) {
            found = true;
            double oldVal = rs.getDouble("old_total");
            double newVal = rs.getDouble("new_total");
            double diff = newVal - oldVal;
            
            String arrow = (diff > 0) ? GREEN + "ΑΥΞΗΣΗ" + RESET : RED + "ΜΕΙΩΣΗ" + RESET;
            
            System.out.printf("[%s] %s\n   Από: %,.2f € -> Σε: %,.2f € (%s κατά %,.2f €)\n",
                rs.getString("change_date"),
                rs.getString("name"),
                oldVal, newVal, arrow, Math.abs(diff));
            System.out.println("----------------------------------------------------");
        }
        if (!found) System.out.println("Δεν έχουν καταγραφεί αλλαγές ακόμα.");
    }

    private String truncate(String str, int width) {
        if (str.length() > width)
            return str.substring(0, width - 3) + "...";
        return str;
    }
}