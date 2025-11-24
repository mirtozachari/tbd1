import java.util.Scanner;
public class MenuForMinistries {
   private Ministries[] ministries;
    public MinistryPrinter(Ministries[] ministries) {
        this.ministries = ministries;
        }

    }
    @Override
    public void display() {
        boolean flag;
        while (flag =false) {   
            Scanner scanner = new Scanner(System.in);
        System.out.println("Δώσε υπουργείο");
    String x = scanner.nextString();
        for (Ministries m : ministries) {
             if (x = m.getCategory()) {
            System.out.println(m.getAmount);
            flag = true;
            break;
            }
        }
        if (flag = false) {
        System.out.println("Δεν υπάρχει το υπουργείο");
        System.out.println("Ξαναδώσε όνομα υπουργείου");
        }
    }
}
