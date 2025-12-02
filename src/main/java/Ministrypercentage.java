public class Ministrypercentage implements Displayable {
    private Ministries[] ministries;

    public Ministrypercentage(Ministries[] ministries) {
        this.ministries = ministries;
    }
    
    @Override
    public void display() {
        long total = 0;
        double per;
        for (Ministries m : ministries){
        total += m.getAmount();
        }
        for (Ministries m : ministries) {
            per = 100.0 * m.getAmount() / total;
            System.out.println("Το Υπουργείο:" + m.getCategory() + " καλύπτει " + String.format("%.4f",per) + "%" + " επί του συνολικού προϋπολογισμού");
        }
    }
}