public class MinistryPrinter implements Displayable {
   private Ministries[] ministries;
    public MinistryPrinter(Ministries[] ministries) {
        this.ministries = ministries;
    }
    @Override
    public void display() {
        long total = 0;
        for (Ministries m : ministries) {
            System.out.println("Υπουργείο: " + m.getCategory());
            System.out.println("Ποσό: " + m.getAmount());
            total += m.getAmount();
        }
        System.out.println("Συνολικό Ποσό: " + total);
    } 
}
