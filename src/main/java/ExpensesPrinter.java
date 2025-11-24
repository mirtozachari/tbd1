public class ExpensesPrinter implements Displayable {
   private Expenses[] expenses;
   public ExpensesPrinter (Expenses[] expenses){
    this.expenses = expenses;
   }
   @Override
   public void display() {
        long total = 0;
        for (Expenses e : expenses) {
            System.out.println("Κατηγορία: " + e.getCategory());
            System.out.println("Ποσό: " + e.getAmount());
            total += e.getAmount();
        }
        System.out.println("Συνολικό Ποσό: " + total);
    }
}
