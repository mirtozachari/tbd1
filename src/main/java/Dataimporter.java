import java.sql.*;
import java.util.Scanner;

public class PrimeMinisterApp {

    private final String DB_URL = "jdbc:mysql://localhost:3306/budget_db";
    private final String USER = "root";
    private final String PASS = "";

    private final String RESET = "\u001B[0m";
    private final String RED = "\u001B[31m";
    private final String GREEN = "\u001B[32m";
    private final String YELLOW = "\u001B[33m";
    private final String BLUE = "\u001B[34m";
    private final String BOLD = "\u001B[1m";

    public void start() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Scanner scanner = new Scanner(System.in)) {

            initializeDatabase(conn);

            System.out.println(BOLD + "=== ΣΥΣΤΗΜΑ ΔΙΑΧΕΙΡΙΣΗΣ ΚΡΑΤΙΚΟΥ ΠΡΟΫΠΟΛΟΓΙΣΜΟΥ 2026 ===" + RESET);

            while (true) {
                showDashboard(conn);

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
                            listMinistries(conn);
                            break;
                        case 2:
                            modifyBudget(conn, scanner);
                            break;
                        case 3:
                            showHistory(conn);
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
            System.out.println(RED + "ΣΦΑΛΜΑ ΣΥΝΔΕΣΗΣ Ή ΑΡΧΙΚΟΠΟΙΗΣΗΣ ΒΑΣΗΣ ΔΕΔΟΜΕΝΩΝ!" + RESET);
            e.printStackTrace();
        }
    }

private void initializeDatabase(Connection conn) throws SQLException {
        
        System.out.println(YELLOW + "Εκτελείται αρχική ρύθμιση βάσης δεδομένων..." + RESET);
        Statement stmt = conn.createStatement();
        
        String createTablesSQL[] = {
            "CREATE TABLE IF NOT EXISTS State_Revenue (" +
                "code INT PRIMARY KEY," +
                "category_name VARCHAR(255)," +
                "amount DECIMAL(25,2)" +
            ")",
            "CREATE TABLE IF NOT EXISTS State_General_Expenses (" +
                "code INT PRIMARY KEY," +
                "category_name VARCHAR(255)," +
                "amount DECIMAL(25,2)" +
            ")",
            "CREATE TABLE IF NOT EXISTS Ministry_Budget (" +
                "code INT PRIMARY KEY," +
                "name VARCHAR(255)," +
                "regular_budget DECIMAL(25,2)," +
                "investment_budget DECIMAL(25,2)," +
                "total_amount DECIMAL(25,2)" +
            ")",
            "CREATE TABLE IF NOT EXISTS ChangeLog (" +
                "log_id INT AUTO_INCREMENT PRIMARY KEY," +
                "ministry_code INT," +
                "old_total DECIMAL(25,2)," +
                "new_total DECIMAL(25,2)," +
                "change_date DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (ministry_code) REFERENCES Ministry_Budget(code)" +
            ")"
        };
        
        for (String sql : createTablesSQL) {
            stmt.execute(sql);
        }
        
        String insertDataSQL[] = {
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (11, 'Φόροι', 65586000000)",
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (12, 'Κοινωνικές εισφορές', 60000000)",
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (13, 'Μεταβιβάσεις', 10943000000)",
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (14, 'Πωλήσεις αγαθών και υπηρεσιών', 1201000000)",
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (15, 'Λοιπά τρέχοντα έσοδα', 2407000000)",
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (31, 'Πάγια Περιουσιακά Στοιχεία', 51000000)",
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (43, 'Χρεωστικοί τίτλοι', 11000000)",
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (44, 'Δάνεια (Προσφυγή)', 5525000000)",
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (45, 'Συμμετοχικοί τίτλοι & επενδύσεις', 228000000)",
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (52, 'Υποχρεώσεις από νόμισμα/καταθέσεις', 63000000)",
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (53, 'Χρεωστικοί τίτλοι (Υποχρεώσεις)', 23875000000)",
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (54, 'Δάνεια (Αναχρηματοδότηση)', 1654726000000.00)",
            "INSERT IGNORE INTO State_Revenue (code, category_name, amount) VALUES (57, 'Χρηματοοικονομικά παράγωγα', 959000000)",

            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (21, 'Παροχές σε εργαζομένους', 15688490000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (22, 'Κοινωνικές παροχές', 627779000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (23, 'Μεταβιβάσεις', 35897013000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (24, 'Αγορές αγαθών και υπηρεσιών', 2388349000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (25, 'Επιδοτήσεις', 80330000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (26, 'Τόκοι', 7917420000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (27, 'Λοιπές δαπάνες', 101259000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (29, 'Πιστώσεις υπό κατανομή', 19989259000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (31, 'Πάγια περιουσιακά στοιχεία', 3355541000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (33, 'Τιμαλφή', 84000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (44, 'Δάνεια (Πληρωμές)', 12079352000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (45, 'Συμμετοχικοί τίτλοι & επενδύσεις', 1587084000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (53, 'Χρεωστικοί τίτλοι (Λήξεις)', 24909729000)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (54, 'Δάνεια (Λήξεις - Χρεολύσια)', 1659113701000.00)",
            "INSERT IGNORE INTO State_General_Expenses (code, category_name, amount) VALUES (57, 'Χρηματοοικονομικά παράγωγα', 341662000)",

            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1001, 'Προεδρία της Δημοκρατίας', 4951000, 0, 4951000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1003, 'Βουλή των Ελλήνων', 184900000, 2000000, 186900000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1004, 'Προεδρία της Κυβέρνησης', 39905000, 6000000, 45905000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1007, 'Υπουργείο Εσωτερικών', 3731668000, 432000000, 4163668000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1009, 'Υπουργείο Εξωτερικών', 439237000, 44000000, 483237000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1011, 'Υπουργείο Εθνικής Άμυνας', 6955272000, 108000000, 7063272000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1015, 'Υπουργείο Υγείας', 6945945000, 896000000, 7841945000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1017, 'Υπουργείο Δικαιοσύνης', 616577000, 63000000, 679577000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1020, 'Υπ. Παιδείας, Θρησκευμάτων & Αθλητισμού', 5899933000, 864000000, 6763933000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1022, 'Υπουργείο Πολιτισμού', 278109000, 375000000, 653109000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1024, 'Υπ. Εθνικής Οικονομίας & Οικονομικών', 1714338966000.00, 3069954000, 1717408920000.00)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1029, 'Υπ. Αγροτικής Ανάπτυξης & Τροφίμων', 296877000, 1207000000, 1503877000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1031, 'Υπουργείο Περιβάλλοντος και Ενέργειας', 318452000, 2815000000, 3133452000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1032, 'Υπ. Εργασίας & Κοινωνικής Ασφάλισης', 18429078000, 672000000, 19101078000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1034, 'Υπ. Κοινωνικής Συνοχής και Οικογένειας', 3570316000, 233000000, 3803316000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1036, 'Υπουργείο Ανάπτυξης', 128476000, 670000000, 798476000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1039, 'Υπουργείο Υποδομών και Μεταφορών', 1031756000, 2360000000, 3391756000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1041, 'Υπ. Ναυτιλίας και Νησιωτικής Πολιτικής', 367593000, 389000000, 756593000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1045, 'Υπουργείο Τουρισμού', 40992000, 197000000, 237992000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1053, 'Υπουργείο Ψηφιακής Διακυβέρνησης', 154403000, 1237000000, 1391403000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1055, 'Υπουργείο Μετανάστευσης και Ασύλου', 156133000, 380000000, 536133000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1057, 'Υπουργείο Προστασίας του Πολίτη', 2513285000, 90000000, 2603285000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1060, 'Υπ. Κλιματικής Κρίσης & Πολ. Προστασίας', 856115000, 582000000, 1438115000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1901, 'Αποκεντρωμένη Διοίκηση Αττικής', 14380000, 0, 14380000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1902, 'Αποκεντρωμένη Διοίκηση Θεσσαλίας-Στερεάς', 11142000, 0, 11142000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1903, 'Αποκεντρωμένη Διοίκηση Ηπείρου-Δ.Μακεδονίας', 10981000, 0, 10981000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1904, 'Αποκεντρωμένη Διοίκηση Πελοποννήσου-Ιονίου', 15556000, 0, 15556000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1905, 'Αποκεντρωμένη Διοίκηση Αιγαίου', 7149000, 0, 7149000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1906, 'Αποκεντρωμένη Διοίκηση Κρήτης', 7311000, 0, 7311000)",
            "INSERT IGNORE INTO Ministry_Budget (code, name, regular_budget, investment_budget, total_amount) VALUES (1907, 'Αποκεντρωμένη Διοίκηση Μακεδονίας-Θράκης', 19640000, 0, 19640000)"
        };
        
        for (String sql : insertDataSQL) {
            try {
                stmt.execute(sql);
            } catch (SQLException e) {
                if (!e.getMessage().contains("Duplicate entry")) {
                    throw e; 
                }
            }
        }
        
        System.out.println(GREEN + "Η αρχική ρύθμιση της βάσης ολοκληρώθηκε." + RESET);
    }
    private void showDashboard(Connection conn) throws SQLException {
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

    private void listMinistries(Connection conn) throws SQLException {
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

    private void modifyBudget(Connection conn, Scanner scanner) throws SQLException {
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