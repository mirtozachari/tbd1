import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatabaseConfig {
    public static final Map<Integer, Ministry> ministries = new TreeMap<>();
    public static final List<ChangeLog> changeLogs = new ArrayList<>();
    public static double totalStateRevenue = 0;
    public static double totalGeneralExpenses = 0;

    public static void initializeDatabase(int year) {
        ministries.clear();
        changeLogs.clear();

        switch (year) {
            case 2023:
                load2023();
                break;
            case 2024:
                load2024();
                break;
            case 2025:
                load2025();
                break;
            default:
                System.out.println("Δεν υπάρχουν δεδομένα για το έτος " + year + ". Φόρτωση δεδομένων 2023.");
                load2023();
                break;
        }
    }

    private static void load2023() {
        totalStateRevenue = 798039000000.0;
        totalGeneralExpenses = 806878193000.0;

        seedMinistry(1001, "Προεδρία της Δημοκρατίας", 4263000);
        seedMinistry(1003, "Βουλή των Ελλήνων", 149900000);
        seedMinistry(1004, "Προεδρία της Κυβέρνησης", 40679000);
        seedMinistry(1007, "Υπουργείο Εσωτερικών", 3548748000.0);
        seedMinistry(1009, "Υπουργείο Εξωτερικών", 282175000);
        seedMinistry(1011, "Υπουργείο Εθνικής Άμυνας", 5707800000.0);
        seedMinistry(1015, "Υπουργείο Υγείας", 5202388000.0);
        seedMinistry(1017, "Υπουργείο Δικαιοσύνης", 566374000);
        seedMinistry(1019, "Υπουργείο Παιδείας και Θρησκευμάτων", 6080504000.0);
        seedMinistry(1021, "Υπουργείο Πολιτισμού και Αθλητισμού", 458563000);
        seedMinistry(1023, "Υπουργείο Οικονομικών", 748592323000.0);
        seedMinistry(1029, "Υπουργείο Αγροτικής Ανάπτυξης και Τροφίμων", 1547980000.0);
        seedMinistry(1031, "Υπουργείο Περιβάλλοντος και Ενέργειας", 1707333000.0);
        seedMinistry(1033, "Υπουργείο Εργασίας και Κοινωνικής Υποθέσεων", 22492686000.0);
        seedMinistry(1035, "Υπουργείο Ανάπτυξης και Επενδύσεων", 3295349000.0);
        seedMinistry(1039, "Υπουργείο Υποδομών και Μεταφορών", 2346259000.0);
        seedMinistry(1041, "Υπουργείο Ναυτιλίας και Νησιωτικής Πολιτικής", 498453000);
        seedMinistry(1045, "Υπουργείο Τουρισμού", 239360000);
        seedMinistry(1053, "Υπουργείο Ψηφιακής Διακυβέρνησης", 972937000);
        seedMinistry(1055, "Υπουργείο Μετανάστευσης και Ασύλου", 418728000);
        seedMinistry(1057, "Υπουργείο Προστασίας του Πολίτη", 2012833000.0);
        seedMinistry(1059, "Υπουργείο Κλιματικής Κρίσης και Πολιτικής Προστασίας", 643473000);
        seedMinistry(1901, "Αποκεντρωμένη Διοίκηση Αττικής", 11610000);
        seedMinistry(1902, "Αποκεντρωμένη Διοίκηση Θεσσαλίας - Στερεάς Ελλάδας", 7723000);
        seedMinistry(1903, "Αποκεντρωμένη Διοίκηση Ηπείρου - Δυτικής Μακεδονίας", 8468000);
        seedMinistry(1904, "Αποκεντρωμένη Διοίκηση Πελοποννήσου - Δυτικής Ελλάδας και Ιονίου", 12467000);
        seedMinistry(1905, "Αποκεντρωμένη Διοίκηση Αιγαίου", 5631000);
        seedMinistry(1906, "Αποκεντρωμένη Διοίκηση Κρήτης", 6068000);
        seedMinistry(1907, "Αποκεντρωμένη Διοίκηση Μακεδονίας - Θράκης", 17118000);
    }

    private static void load2024() {
        totalStateRevenue = 1107649000000.0;
        totalGeneralExpenses = 1108188270000.0;

        seedMinistry(1001, "Προεδρία της Δημοκρατίας", 4636000);
        seedMinistry(1003, "Βουλή των Ελλήνων", 160400000);
        seedMinistry(1004, "Προεδρία της Κυβέρνησης", 43259000);
        seedMinistry(1007, "Υπουργείο Εσωτερικών", 3705487000.0);
        seedMinistry(1009, "Υπουργείο Εξωτερικών", 407892000);
        seedMinistry(1011, "Υπουργείο Εθνικής Άμυνας", 6123388000.0);
        seedMinistry(1015, "Υπουργείο Υγείας", 6027031000.0);
        seedMinistry(1017, "Υπουργείο Δικαιοσύνης", 625464000);
        seedMinistry(1020, "Υπουργείο Παιδείας, Θρησκευμάτων και Αθλητισμού", 6547630000.0);
        seedMinistry(1022, "Υπουργείο Πολιτισμού", 400113000);
        seedMinistry(1024, "Υπουργείο Εθνικής Οικονομίας και Οικονομικών", 1049897798000.0);
        seedMinistry(1029, "Υπουργείο Αγροτικής Ανάπτυξης και Τροφίμων", 1184694000.0);
        seedMinistry(1031, "Υπουργείο Περιβάλλοντος και Ενέργειας", 1823158000.0);
        seedMinistry(1032, "Υπουργείο Εργασίας και Κοινωνικής Ασφάλισης", 18629492000.0);
        seedMinistry(1034, "Υπουργείο Κοινωνικής Συνοχής και Οικογένειας", 3992039000.0);
        seedMinistry(1036, "Υπουργείο Ανάπτυξης", 924661000);
        seedMinistry(1039, "Υπουργείο Υποδομών και Μεταφορών", 2350117000.0);
        seedMinistry(1041, "Υπουργείο Ναυτιλίας και Νησιωτικής Πολιτικής", 576280000);
        seedMinistry(1045, "Υπουργείο Τουρισμού", 167787000);
        seedMinistry(1053, "Υπουργείο Ψηφιακής Διακυβέρνησης", 843765000);
        seedMinistry(1055, "Υπουργείο Μετανάστευσης και Ασύλου", 473304000);
        seedMinistry(1057, "Υπουργείο Προστασίας του Πολίτη", 2262973000.0);
        seedMinistry(1060, "Υπουργείο Κλιματικής Κρίσης και Πολιτικής Προστασίας", 936610000);
        seedMinistry(1901, "Αποκεντρωμένη Διοίκηση Αττικής", 11629000);
        seedMinistry(1902, "Αποκεντρωμένη Διοίκηση Θεσσαλίας - Στερεάς Ελλάδας", 10659000);
        seedMinistry(1903, "Αποκεντρωμένη Διοίκηση Ηπείρου - Δυτικής Μακεδονίας", 9796000);
        seedMinistry(1904, "Αποκεντρωμένη Διοίκηση Πελοποννήσου - Δυτικής Ελλάδας και Ιονίου", 15415000);
        seedMinistry(1905, "Αποκεντρωμένη Διοίκηση Αιγαίου", 6211000);
        seedMinistry(1906, "Αποκεντρωμένη Διοίκηση Κρήτης", 6719000);
        seedMinistry(1907, "Αποκεντρωμένη Διοίκηση Μακεδονίας - Θράκης", 19863000);
    }

    private static void load2025() {
        totalStateRevenue = 1304827000000.0;
        totalGeneralExpenses = 1307907506000.0;

        seedMinistry(1001, "Προεδρία της Δημοκρατίας", 4638000);
        seedMinistry(1003, "Βουλή των Ελλήνων", 171950000);
        seedMinistry(1004, "Προεδρία της Κυβέρνησης", 41689000);
        seedMinistry(1007, "Υπουργείο Εσωτερικών", 3830276000.0);
        seedMinistry(1009, "Υπουργείο Εξωτερικών", 420237000);
        seedMinistry(1011, "Υπουργείο Εθνικής Άμυνας", 6130000000.0);
        seedMinistry(1015, "Υπουργείο Υγείας", 7177424000.0);
        seedMinistry(1017, "Υπουργείο Δικαιοσύνης", 650803000);
        seedMinistry(1020, "Υπουργείο Παιδείας, Θρησκευμάτων και Αθλητισμού", 6606000000.0);
        seedMinistry(1022, "Υπουργείο Πολιτισμού", 575419000);
        seedMinistry(1024, "Υπουργείο Εθνικής Οικονομίας και Οικονομικών", 1246518464000.0);
        seedMinistry(1029, "Υπουργείο Αγροτικής Ανάπτυξης και Τροφίμων", 1281403000.0);
        seedMinistry(1031, "Υπουργείο Περιβάλλοντος και Ενέργειας", 2341227000.0);
        seedMinistry(1032, "Υπουργείο Εργασίας και Κοινωνικής Ασφάλισης", 18678084000.0);
        seedMinistry(1034, "Υπουργείο Κοινωνικής Συνοχής και Οικογένειας", 3989553000.0);
        seedMinistry(1036, "Υπουργείο Ανάπτυξης", 818045000);
        seedMinistry(1039, "Υπουργείο Υποδομών και Μεταφορών", 2694810000.0);
        seedMinistry(1041, "Υπουργείο Ναυτιλίας και Νησιωτικής Πολιτικής", 651864000);
        seedMinistry(1045, "Υπουργείο Τουρισμού", 189293000);
        seedMinistry(1053, "Υπουργείο Ψηφιακής Διακυβέρνησης", 1073928000);
        seedMinistry(1055, "Υπουργείο Μετανάστευσης και Ασύλου", 475871000);
        seedMinistry(1057, "Υπουργείο Προστασίας του Πολίτη", 2285820000.0);
        seedMinistry(1060, "Υπουργείο Κλιματικής Κρίσης και Πολιτικής Προστασίας", 1221116000);
        seedMinistry(1901, "Αποκεντρωμένη Διοίκηση Αττικής", 13091000);
        seedMinistry(1902, "Αποκεντρωμένη Διοίκηση Θεσσαλίας - Στερεάς Ελλάδας", 10579000);
        seedMinistry(1903, "Αποκεντρωμένη Διοίκηση Ηπείρου - Δυτικής Μακεδονίας", 9943000);
        seedMinistry(1904, "Αποκεντρωμένη Διοίκηση Πελοποννήσου - Δυτικής Ελλάδας και Ιονίου", 14918000);
        seedMinistry(1905, "Αποκεντρωμένη Διοίκηση Αιγαίου", 6188000);
        seedMinistry(1906, "Αποκεντρωμένη Διοίκηση Κρήτης", 6497000);
        seedMinistry(1907, "Αποκεντρωμένη Διοίκηση Μακεδονίας - Θράκης", 18376000);
    }

    private static void seedMinistry(int code, String name, double amount) {
        ministries.put(code, new Ministry(code, name, amount));
    }

    public static class Ministry {
        public int code;
        public String name;
        public double totalAmount;

        public Ministry(int code, String name, double totalAmount) {
            this.code = code;
            this.name = name;
            this.totalAmount = totalAmount;
        }
    }

    public static class ChangeLog {
        public String ministryName;
        public double oldTotal;
        public double newTotal;
        public String date;

        public ChangeLog(String ministryName, double oldTotal, double newTotal) {
            this.ministryName = ministryName;
            this.oldTotal = oldTotal;
            this.newTotal = newTotal;
            this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}