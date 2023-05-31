import Serial.SimpleSerial;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

    SimpleSerial Serial;

    // Functions


    void displayOrdersToCombo() {
        orderCombo.removeAllItems();
        int[] allOrders = db.getAllOrders();
        for(int i = 0; i < allOrders.length; i++) {
            orderCombo.addItem(allOrders[i]);
        }
        repaint();
    }

    void displayOrderLinesList(int orderID){
        int[] allOrders = db.getAllOrders();
        for(int i=0; i < allOrders.length; i++){
            for(int j=0; j<db.getOrderLines(allOrders[i]).size(); j++) {
                for (int k=0; k<db.getOrderLines(allOrders[i]).get(j).size(); k++) {
                    //toegang onderdelen van arraylist
                    int orderlineVar = db.getOrderLines(allOrders[i]).get(j).get(k);

                    if(allOrders[i] == orderID) {
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

    public boolean orderUitvoerbaar(int orderID) {
        ArrayList<ArrayList<Integer>> orderlines = db.getOrderLines(orderID);
        HashMap<Integer, Integer> schapCopy = db.getAllProducts();

        for (int i = 0; i < orderlines.size(); i++) {
            for (int j = 0; j < orderlines.get(i).size(); j++) {
                int itemID = orderlines.get(i).get(j);

                if(!schapCopy.containsKey(itemID)){
                    return false;
                }

                if(schapCopy.get(itemID) > 0){
                    schapCopy.put(itemID, schapCopy.get(itemID)-1);
                } else {
                    return false;
                }

            }
        }
        return true;
    }


    BekijkOrders(JFrame parentFrame, boolean m, SimpleSerial Serial){
        // Create dialog window
        super(parentFrame,  m);
        this.parentFrame = parentFrame;
        this.Serial = Serial;

        setSize(new Dimension(600,600));
        setTitle("Order weergave");
        setLayout(new FlowLayout());
        setResizable(false);

        // Component styling
        orderCombo.setPreferredSize(new Dimension(250, 30));

        // Component functionality
        orderCombo.addActionListener(e -> {
            listModel.removeAllElements();
            int[] allOrders = db.getAllOrders();
            for(int i=0; i<allOrders.length; i++) {
                int selected = -1;
                if(orderCombo.getSelectedItem() != null) {
                    selected = Integer.parseInt(Objects.requireNonNull(orderCombo.getSelectedItem()).toString());
                }
                if(allOrders[i] == selected) {
                    displayOrderLinesList(allOrders[i]);
                }
            }
        });

        executeOrder.addActionListener(e -> {
            if(orderCombo.getSelectedItem() != null) {
                if(!orderUitvoerbaar((int) orderCombo.getSelectedItem())){
                    System.out.println("sorry, products not in warehouse");
                    return;
                }

                Serial.grid(26);


                ArrayList<Integer> arr = db.getItemArrayList((int) orderCombo.getSelectedItem());
                int[][] coordinates;
                if(arr.size() > 0){
                    coordinates = TSP.TSPAlgorithm(arr);
                    System.out.println(Arrays.deepToString(coordinates));
                } else {
                    System.out.println("sorry not complete");
                    return;
                }

                int pickCount = 3;
                boolean res;
                for (int i = 0; i < coordinates.length; i++) {
                    for (int j = 0; j < coordinates[i].length; j++) {
                        System.out.println("grid");
                        System.out.println(coordinates[i][j]);

                        res = Serial.grid(coordinates[i][j]);
                        System.out.println(res);

                        Serial.delay(100);

                        if (coordinates[i][j] == 26) {
                            System.out.println("NIET PAKKEN");
                        } else {
                            System.out.println("pick");
                            System.out.println(pickCount);

                            Serial.packet("pick", pickCount);
                            pickCount -= 1;

                            db.removeItems(db.getItemIDFromPlek(coordinates[i][j]), coordinates[i][j]);

                            Serial.delay(100);
                        }


                        if (coordinates[i][j] == 26 && j == coordinates[i].length-1) {
                            System.out.println("dump");
                            System.out.println("pickcount = 3");
                            pickCount = 3;
                            Serial.packet("dump");

                            Serial.delay(100);
                        }
                    }
                }
            }

            VoorraadPanel.drawVoorraad();

            db.setOrderVoltooid((int) orderCombo.getSelectedItem());

            displayOrdersToCombo();
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
