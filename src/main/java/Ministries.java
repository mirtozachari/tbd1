import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
public class Ministries {
    private String category;
    private int amount;
    public String getCategory() {
        return category;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
        Gson gson = new Gson();
        FileReader reader = new FileReader("src/main/resources/config/ministries25.json")
            Ministries[] ministry = gson.fromJson(reader, Ministries[].class);
            int totall = 0;
            for (Ministries m : ministry) {
                System.out.println("υπουργείο:" + m.getCategory());
                System.out.println("ποσό:" + m.getAmount());
                totall += m.getAmount();

            }
    
    }
