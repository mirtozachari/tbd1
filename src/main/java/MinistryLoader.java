import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MinistryLoader {

    public static Ministries[] load(String filename) {
        Gson gson = new Gson();
        InputStream in = MinistryLoader.class.getClassLoader()
                           .getResourceAsStream("config/" + filename);
        if (in == null) {
            System.err.println("Το αρχείο δεν βρέθηκε: " + filename);
            return new Ministries[0];
        }
        try (InputStreamReader reader = new InputStreamReader(in)) {
            return gson.fromJson(reader, Ministries[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return new Ministries[0];
        }
    }
}
