import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
public class Revenues {
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
    public void setCategory() {
        this.category = category;
    }
    public static void getRevenues() {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader("src/main/resources/config/REVENUE25.json")) {
        Revenues[] revenue = gson.fromJson(reader, Revenues[].class);
    for (Revenues r : revenue) {
        System.out.println("κατηγορία:" + r.getCategory());
        System.out.println("ποσό:" + r.getAmount());
    } 
} catch (IOException e) {
        e.printStackTrace();
    }
  }
}
