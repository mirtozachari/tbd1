import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class DiagramMinistries {
    public static void diagram() throws Exception {
        // Το path ξεκινάει ΜΕΣΑ από το resources. 
        // Αφού το έχεις στο src/main/resources/config/diagramministries.xlsx
        // το path για τη Java είναι το παρακάτω:
        String resourcePath = "/config/diagramministries.xlsx";
        
        // Δημιουργούμε ένα προσωρινό αρχείο γιατί το Excel δεν μπορεί 
        // να διαβάσει απευθείας μέσα από το JAR ή το Classpath
        File tempFile = File.createTempFile("ministries_chart", ".xlsx");
        
        try (InputStream is = DiagramMinistries.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new Exception("Σφάλμα: Το αρχείο δεν βρέθηκε στο path: " + resourcePath);
            }
            
            // Αντιγραφή του αρχείου από το GitHub/Resources στον temp φάκελο του χρήστη
            Files.copy(is, tempFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }

        // Άνοιγμα του αρχείου
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(tempFile);
        } else {
            System.out.println("Το σύστημα δεν υποστηρίζει το άνοιγμα αρχείων μέσω Desktop API.");
        }
    }
}
