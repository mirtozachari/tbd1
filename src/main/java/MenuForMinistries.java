import java.util.Scanner;

public class MenuForMinistries {

    private Ministries[] ministries;

    public MenuForMinistries(Ministries[] ministries) {
        this.ministries = ministries;
    }

    public void Menu() {
        System.out.println("Δώσε υπουργείο:");
        boolean flag = false;
        Scanner scanner = new Scanner(System.in);

        while (!flag) {
            System.out.println("Διαθέσιμα Υπουργεία και οι αριθμοί τους:");
            for (int i = 0; i < ministries.length; i++) {
                System.out.println((i + 1) + ". " + ministries[i].getCategory());
            }
            System.out.println((ministries.length + 1) + ". Έξοδος");
            System.out.println("Δώσε τον αριθμό του Υπουργείου που σε ενδιαφέρει:");

            String x = scanner.nextLine().trim();
            // Χρησιμοποιούμε nextLine() αντί για nextInt() για να αποφύγουμε σφάλματα σε περίπτωση μη αριθμητικής εισόδου

            try {
                int y = Integer.parseInt(x); // Μετατρέπει το String σε int

                if (y >= 1 && y < ministries.length) {
                    Ministries m = ministries[y - 1];
                    System.out.print("Επέλεξες το: " + m.getCategory() + " ");
                    System.out.println("με ποσό: " + m.getAmount() + " ευρώ");
                } else if (y == ministries.length + 1) {
                    System.out.println("Έξοδος από το μενού");
                    flag = true;
                } else {
                    System.out.println("Δεν βρέθηκε το Υπουργείο. Δοκιμάστε ξανά.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Πρέπει να δώσεις αριθμό. Ξαναδοκίμασε.");
            }
        }
    }
}
