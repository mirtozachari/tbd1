import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
public class Expenses {
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
    public static void main(String[] args) {
    Gson gson = new Gson();
    try (FileReader reader = new FileReader("EXPENSES25.json")) {
        Expense[] expenses = gson.fromJson(reader, Expense[].class);
    for (Expense ex : expenses) {
        System.out.println("κατηγορία:" + ex.getCategory());
        System.out.println("ποσό:" + ex.getAmount());
    } 
} catch (IOException e) {
        e.printStackTrace();
    }
  }
}
