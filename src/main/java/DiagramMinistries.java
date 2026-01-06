import java.awt.Desktop;
import java.io.File;

public class DiagramMinistries {
    public static void diagram() throws Exception {
        File file = new File("C:tbd1\\src\\main\\resources\\config\\diagramministries.xlsx");
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().open(file); // ανοίγει το Excel με το chart
        } else {
            System.out.println("Desktop API δεν υποστηρίζεται σε αυτό το σύστημα.");
        }
    }
}
