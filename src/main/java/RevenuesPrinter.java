public class RevenuesPrinter implements Displayable {
    private Revenues[] revenues;
    
    public RevenuesPrinter(Revenues[] revenues) {
        this.revenues = revenues;
    }

    @Override
    public void display() {
        long total = 0;
        for (Revenues r : revenues) {
            System.out.println("Κατηγορία: " + r.getCategory());
            System.out.println("Ποσό: " + r.getAmount());
            total += r.getAmount();
        }
        System.out.println("Συνολικό Ποσό: " + total);
    }
}
