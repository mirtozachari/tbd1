public class Main {
    public static void main(String[] args) {
        Ministries[] ministries = MinistryLoader.load("src/main/resources/config/ministries25.json");
        Revenues[] revenues = RevenuesLoader.load("src/main/resources/config/revenue25.json");
        Expenses[] expenses = ExpensesLoader.load("src/main/resources/config/expenses25.json");
        MinistryPrinter ministryPrinter = new MinistryPrinter(ministries);
        ministryPrinter.display();
        Ministrypercentage ministryPercentage = new Ministrypercentage(ministries);
        ministryPercentage.display();
        RevenuesPrinter revenuesPrinter = new RevenuesPrinter(revenues);
        revenuesPrinter.display();
        ExpensesPrinter expensesPrinter = new ExpensesPrinter(expenses);
        expensesPrinter.display();
    }
}