import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class RevenuesLoader {

    public static Revenues[] load(String path) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, Revenues[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Revenues[0];
        }
    }
}
