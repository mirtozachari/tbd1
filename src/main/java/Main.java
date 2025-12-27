import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Ξεκινάει το μενού επιλογών του χρήστη
        
        
        System.out.println("ΕΠΕΛΕΞΕ ΕΤΟΣ:2023,2024,2025");
        int yearSelection = scanner.nextInt();
        if (yearSelection == 2023) {
            MainMenu.Menu2023();

        } else if (yearSelection == 2024)  {
            MainMenu.Menu2024();
                
        } else if (yearSelection == 2025) {
            MainMenu.Menu2025();
            
        }

        scanner.close();
    }
}
