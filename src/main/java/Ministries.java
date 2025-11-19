import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
public class Ministries {
    private String category;
    private long amount;
    public String getCategory() {
        return category;
    }
    public long getAmount() {
        return amount;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }
    public static void getMinistry() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("src/main/resources/config/ministries25.json")) {
            Ministries[] ministry = gson.fromJson(reader, Ministries[].class); 
            long total = 0;
            for (Ministries m : ministry) {
                System.out.println("υπουργείο:" + m.getCategory());
                System.out.println("ποσό:" + m.getAmount());
                total += m.getAmount();
        } 
        }  catch (IOException e) {
        e.printStackTrace();
    }
        }
}