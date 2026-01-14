public class ExecuteMenu {
    public static void executeMenu(int x, Ministries[] ministries, Revenues[] revenues, Expenses[] expenses) {
         switch (x) {
                    case 0: // Εμφάνιση στοιχείων όλων των υπουργείων
                        MinistryPrinter ministryPrinter = new MinistryPrinter(ministries);
                        ministryPrinter.display();
                        break;

                    case 1: // Εμφάνιση ποσοστών κάθε υπουργείου επί του συνολικού προϋπολογισμού
                        Ministrypercentage ministryPercentage = new Ministrypercentage(ministries);
                        ministryPercentage.display();
                        break;

                    case 2: // Εμφάνιση όλων των εσόδων 
                        RevenuesPrinter revenuesPrinter = new RevenuesPrinter(revenues);
                        revenuesPrinter.display();
                        break;

                    case 3: // Εμφάνιση όλων των εξόδων
                        ExpensesPrinter expensesPrinter = new ExpensesPrinter(expenses);
                        expensesPrinter.display();
                        break;
                    /* 
                    case 4: // Εμφάνιση προϋπολογισμού συγκεκριμένου υπουργείου που επιλέγει ο χρήστης
                        System.out.println("Δες τα στοιχεία του Υπουργείου που σε ενδιαφέρει:");
                        MenuForMinistries menu = new MenuForMinistries(ministries);
                        menu.Menu();
                        break;
                    case 5: // Δυνατότητα εισαγωγής αλλαγών
                        System.out.println("Δώσε τις επιθυμητές αλλαγές:");
                        break;
                    */
                    case 6: // Γραφήματα
                    System.out.println("Δες τα γραφήματα:");
                    try {
                DiagramMinistries.diagram();  
                    } catch (Exception e) {
                e.printStackTrace();
                }
                    break;

                    case 100: // Έξοδος από την εφαρμογή
                        System.out.println("Καληνύχτα κύριε πρωθυπουργέ!");
                        break;
                    
                    default:
                        System.out.println("Άκυρος αριθμός");
                }
            }
    }