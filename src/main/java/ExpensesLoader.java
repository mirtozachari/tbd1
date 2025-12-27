import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExpensesLoader {

    public static Expenses[] load(String filename) {
        Gson gson = new Gson();
        InputStream in = ExpensesLoader.class.getClassLoader()
                           .getResourceAsStream("config/" + filename);
        if (in == null) {
            System.err.println("Το αρχείο δεν βρέθηκε: " + filename);
            return new Expenses[0];
        }
        try (InputStreamReader reader = new InputStreamReader(in)) {
            return gson.fromJson(reader, Expenses[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return new Expenses[0];
        }
    }
}
