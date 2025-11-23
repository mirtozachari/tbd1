import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class MinistryLoader {
    public static Ministries[] load(String path) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, Ministries[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Ministries[0];
        }
    }
}