import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
public class Budget {
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
    public static void getBudget() {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader("GreakBudget25.json")) {
        Budget[] budgets = gson.fromJson(reader, Budget[].class);
    for (Budget b : budgets) {
        System.out.println("κατηγορία:" + b.getCategory());
        System.out.println("ποσό:" + b.getAmount());
    } 
} catch (IOException e) {
        e.printStackTrace();
    }
  }
}
