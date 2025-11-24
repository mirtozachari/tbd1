import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Δώσε αριθμό επιλογής για το μενού");
        int x = scanner.nextInt();
        Ministries[] ministries = MinistryLoader.load("src/main/resources/config/ministries25.json");
        Revenues[] revenues = RevenuesLoader.load("src/main/resources/config/revenue25.json");
        switch (x) {
            case 0:
        MinistryPrinter ministryPrinter = new MinistryPrinter(ministries);
        ministryPrinter.display();
        break;
            case 1:
        Ministrypercentage ministryPercentage = new Ministrypercentage(ministries);
        ministryPercentage.display();
        break;
            case 2:
        RevenuesPrinter revenuesPrinter = new RevenuesPrinter(revenues);
        revenuesPrinter.display();
        break;
            case 3:
        System.out.println("Δες τα στοιχεία του Υπουργείου που σε ενδιαφέρει:");
        break;
        }

    }
}