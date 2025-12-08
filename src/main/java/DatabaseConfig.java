import java.sql.*;

public class DatabaseConfig {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/budget_db";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void initializeDatabase(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        
        String[] createTablesSQL = {
            "CREATE TABLE IF NOT EXISTS State_Revenue (code INT PRIMARY KEY, category_name VARCHAR(255), amount DECIMAL(25,2))",
            "CREATE TABLE IF NOT EXISTS State_General_Expenses (code INT PRIMARY KEY, category_name VARCHAR(255), amount DECIMAL(25,2))",
            "CREATE TABLE IF NOT EXISTS Ministry_Budget (code INT PRIMARY KEY, name VARCHAR(255), regular_budget DECIMAL(25,2), investment_budget DECIMAL(25,2), total_amount DECIMAL(25,2))",
            "CREATE TABLE IF NOT EXISTS ChangeLog (log_id INT AUTO_INCREMENT PRIMARY KEY, ministry_code INT, old_total DECIMAL(25,2), new_total DECIMAL(25,2), change_date DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (ministry_code) REFERENCES Ministry_Budget(code))"
        };
        
        for (String sql : createTablesSQL) {
            stmt.execute(sql);
        }
        
        String[] insertDataSQL = {
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
    }
}