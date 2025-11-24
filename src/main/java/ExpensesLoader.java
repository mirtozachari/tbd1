import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;

public class ExpensesLoader {
    public static Expenses[] load(String path) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(path)) {
            return gson.fromJson(reader, Expenses[].class);
        } catch (IOException e) {
            e.printStackTrace();
            return new Expenses[0];
        }
    }
}
