public class BudgetView {
    private static final double DISCREPANCY_THRESHOLD = 1000.0;

    public void showDashboard() {
        double totalRevenue = DatabaseConfig.totalStateRevenue;
        double totalGeneralExpenses = DatabaseConfig.totalGeneralExpenses;
        
        double totalMinistryExpenses = 0;
        for (DatabaseConfig.Ministry m : DatabaseConfig.ministries.values()) {
            totalMinistryExpenses += m.totalAmount;
        }

        double balance = totalRevenue - totalGeneralExpenses;

        System.out.println("\n=======================================================");
        System.out.printf("ΣΥΝΟΛΙΚΑ ΕΣΟΔΑ (Άρθρο 1):         %,20.2f €\n", totalRevenue);
        System.out.printf("ΣΥΝΟΛΙΚΑ ΕΞΟΔΑ (Άρθρο 1):         %,20.2f €\n", totalGeneralExpenses);
        System.out.println("-------------------------------------------------------");

        if (balance >= 0) {
            System.out.printf("ΔΗΜΟΣΙΟΝΟΜΙΚΟ ΑΠΟΤΕΛΕΣΜΑ:  %s+%,20.2f € (ΠΛΕΟΝΑΣΜΑ)%s\n", 
                ConsoleUtils.GREEN, balance, ConsoleUtils.RESET);
        } else {
            System.out.printf("ΔΗΜΟΣΙΟΝΟΜΙΚΟ ΑΠΟΤΕΛΕΣΜΑ:  %s%,20.2f € (ΕΛΛΕΙΜΜΑ)%s\n", 
                ConsoleUtils.RED, balance, ConsoleUtils.RESET);
        }

        System.out.println("-------------------------------------------------------");
        System.out.printf("Σύνολο Κατανεμημένο σε Υπουργεία: %,20.2f €\n", totalMinistryExpenses);

        double diff = totalGeneralExpenses - totalMinistryExpenses;
        if (Math.abs(diff) > DISCREPANCY_THRESHOLD) {
            System.out.printf("%sΠΡΟΣΟΧΗ: Υπάρχει απόκλιση %,.2f € μεταξύ Γενικού Συνόλου και Υπουργείων!%s\n", 
                ConsoleUtils.YELLOW, diff, ConsoleUtils.RESET);
        } else {
            System.out.println(ConsoleUtils.GREEN + "✓ Η κατανομή στα Υπουργεία συμφωνεί με το Γενικό Σύνολο." + ConsoleUtils.RESET);
        }
        System.out.println("=======================================================");
    }

    public void listMinistries() {
        System.out.println("\n--- ΛΙΣΤΑ ΥΠΟΥΡΓΕΙΩΝ & ΦΟΡΕΩΝ ---");
        System.out.printf("%-6s %-50s %20s\n", "ΚΩΔ.", "ΟΝΟΜΑΣΙΑ", "ΠΡΟΫΠΟΛΟΓΙΣΜΟΣ");
        System.out.println("--------------------------------------------------------------------------------");
        
        for (DatabaseConfig.Ministry m : DatabaseConfig.ministries.values()) {
            System.out.printf("%-6d %-50s %,20.2f €\n",
                    m.code,
                    truncate(m.name, 50),
                    m.totalAmount);
        }
    }

    public void showHistory() {
        System.out.println("\n--- ΙΣΤΟΡΙΚΟ ΑΛΛΑΓΩΝ (LOG) ---");
        
        if (DatabaseConfig.changeLogs.isEmpty()) {
            System.out.println("Δεν έχουν καταγραφεί αλλαγές ακόμα.");
            return;
        }

        for (int i = DatabaseConfig.changeLogs.size() - 1; i >= 0; i--) {
            DatabaseConfig.ChangeLog log = DatabaseConfig.changeLogs.get(i);
            
            double diff = log.newTotal - log.oldTotal;
            String arrow = (diff > 0) ? ConsoleUtils.GREEN + "ΑΥΞΗΣΗ" + ConsoleUtils.RESET 
                                      : ConsoleUtils.RED + "ΜΕΙΩΣΗ" + ConsoleUtils.RESET;
            
            System.out.printf("[%s] %s\n   Από: %,.2f € -> Σε: %,.2f € (%s κατά %,.2f €)\n",
                log.date,
                log.ministryName,
                log.oldTotal, log.newTotal, arrow, Math.abs(diff));
            System.out.println("----------------------------------------------------");
        }
    }

    private String truncate(String str, int width) {
        if (str.length() > width)
            return str.substring(0, width - 3) + "...";
        return str;
    }
}