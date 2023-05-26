import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;

public class BekijkOrders extends JDialog {
    Database db = new Database();

    // Vars
    int orderAmount = db.getAantalOrders();

    // Components
    JButton newOrder = new JButton("Nieuwe order");
    JButton executeOrder = new JButton("Order uitvoeren");

    JFrame parentFrame;

    JComboBox<Integer> orderCombo = new JComboBox<>();

    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> orderList = new JList<>(listModel);

    // Functions


    void displayOrdersToCombo() {
        for(int i = 1; i <= orderAmount; i++) {
            orderCombo.addItem(db.getOrderID(i));
        }
    }

    void displayOrderLinesList(int orderID){
        for(int i=1; i<= db.getAantalOrders(); i++){
            for(int j=0; j<db.getOrderLines(i).size(); j++) {
                for (int k=0; k<db.getOrderLines(i).get(j).size(); k++) {
                    //toegang onderdelen van arraylist
                    int orderlineVar = db.getOrderLines(i).get(j).get(k);
                    if(i == orderID) {
                        int itemID = db.getItemIDFromOrderline(orderlineVar);
                        int aantal = db.getAantalFromOrderlist(orderlineVar);
                        double gewicht = db.getGewicht(db.getItemIDFromOrderline(orderlineVar)+1);


                        listModel.addElement("Orderline nr. "+orderlineVar
                                + "   ||    Item: " + db.getItems()[itemID]
                                + "   ||    Aantal: " + aantal
                                + "   ||    Gewicht: " + aantal + "*" + gewicht+" = "
                                + (gewicht*(float)aantal) + "kg");
                    }

                }
            }
        }
    }


    BekijkOrders(JFrame parentFrame, boolean m){
        // Create dialog window
        super(parentFrame,  m);
        this.parentFrame = parentFrame;

        setSize(new Dimension(600,600));
        setTitle("Order weergave");
        setLayout(new FlowLayout());
        setResizable(false);

        // Component styling
        orderCombo.setPreferredSize(new Dimension(250, 30));

        // Component functionality
        orderCombo.addActionListener(e -> {
            listModel.removeAllElements();
            for(int i=0; i<=db.getAantalOrders(); i++) {
                int selected = Integer.parseInt(Objects.requireNonNull(orderCombo.getSelectedItem()).toString());
                if(i == selected) {
                    displayOrderLinesList(i);
                }
            }
        });

        executeOrder.addActionListener(e -> {
            if(orderCombo.getSelectedItem() != null) {
                int[][] arr = TSP.TSPAlgorithm(db.getItemArrayList((int) orderCombo.getSelectedItem()));
                System.out.println(Arrays.toString(arr));
            }
        });

        // Adding components
        displayOrdersToCombo();

        add(new JLabel("Order nummer: "));
        add(orderCombo);
        add(orderList);
        add(newOrder);
        add(executeOrder);

        orderList.setPreferredSize(new Dimension(550,400));
        orderList.setLayout(new GridLayout(5,2));
        orderList.setBorder(BorderFactory.createLineBorder(Color.black));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
