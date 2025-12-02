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

    public void setCategory(String category) {
        this.category = category;
    }
}
