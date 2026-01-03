import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGraph {
    public static void displayMenu(int x) {
        JFrame jf = new JFrame("ΜΕΝΟΥ ΕΠΙΛΟΓΩΝ");
        jf.setBounds(100, 100, 400, 500);
        jf.setLayout(new FlowLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String ministriesFile = "ministries" + x + ".json";
                 String revenuesFile = "revenues" + x + ".json";
                String expensesFile = "expenses" + x + ".json";

        Ministries[] ministries = MinistryLoader.load(ministriesFile);
        Revenues[] revenues = RevenuesLoader.load(revenuesFile);
        Expenses[] expenses = ExpensesLoader.load(expensesFile);

        // Δημιουργία Κουμπιών
        JButton btn0 = new JButton("Στοιχεία όλων των υπουργείων");
        JButton btn1 = new JButton("Ποσοστό επί του προϋπολογισμού");
        JButton btn2 = new JButton("Έσοδα");
        JButton btn3 = new JButton("Έξοδα");
        JButton btn4 = new JButton("Επιλογή συγκεκριμένου υπουργείου");
        JButton btn6 = new JButton("Γραφήματα");
        JButton btnExit = new JButton("Έξοδος");

        // Προσθήκη Λειτουργικότητας (ActionListeners)
        btn0.addActionListener(e -> new MinistryPrinter(ministries).display());
        btn1.addActionListener(e -> new Ministrypercentage(ministries).display());
        btn2.addActionListener(e -> new RevenuesPrinter(revenues).display());
        btn3.addActionListener(e -> new ExpensesPrinter(expenses).display());
        btn4.addActionListener(e -> new MenuForMinistries(ministries).Menu());
        btn6.addActionListener(e -> {
            try {
                diagramministries.diagram();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        btnExit.addActionListener(e -> System.exit(0));

        // Προσθήκη στο Frame
        jf.add(btn0); jf.add(btn1); jf.add(btn2);
        jf.add(btn3); jf.add(btn4); jf.add(btn6);
        jf.add(btnExit);

        jf.setVisible(true);
    }
}