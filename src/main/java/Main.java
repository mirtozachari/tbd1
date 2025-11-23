import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Ministries[] ministries = new Ministries[0];
        try (FileReader reader = new FileReader("src/main/resources/config/ministries25.json")) {
            ministries = new Gson().fromJson(reader, Ministries[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MinistryPrinter printer = new MinistryPrinter(ministries);
        printer.display();
        Ministrypercentage p = new Ministrypercentage(ministries);
        p.display();
    }
}
    