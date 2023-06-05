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

    // Components
    JButton newOrder = new JButton("Nieuwe order");
    JButton executeOrder = new JButton("Order uitvoeren");

    JLabel aantalBins = new JLabel("");

    JFrame parentFrame;

    JComboBox<Integer> orderCombo = new JComboBox<>();

    DefaultListModel<String> listModel = new DefaultListModel<>();
    JList<String> orderList = new JList<>(listModel);

    SimpleSerial Serial;

    // Functions


    int updateAantalBins(int orderID) {
        ArrayList<ArrayList<Integer>> orderLines = db.getOrderLines(orderID);
        ArrayList<Integer> arrayList = new ArrayList<>();

        int total = 0;

        for (int i = 0; i < orderLines.get(0).size(); i++) {
            int itemID = db.getItemIDFromOrderline(orderLines.get(0).get(i)) + 1;
            int aantal = db.getAantalFromOrderlist(orderLines.get(0).get(i));

            int gewicht = (int) db.getGewicht(itemID) * aantal;
            arrayList.add(gewicht);
            total += aantal;
        }
        int[] arr = new int[total];

        for (int i = 0; i < arrayList.size(); i++) {
            arr[i] = arrayList.get(i);
        }

        if(total == 0){
            return 0;
        }
        return Binpacking.bestFitDecreasing(arr).size();
    }

    void displayOrdersToCombo() {
        orderCombo.removeAllItems();
        int[] allOrders = db.getAllOrders();
        for (int allOrder : allOrders) {
            orderCombo.addItem(allOrder);
        }
        repaint();
    }

    void displayOrderLinesList(int orderID){
        int[] allOrders = db.getAllOrders();
        for (int allOrder : allOrders) {
            for (int j = 0; j < db.getOrderLines(allOrder).size(); j++) {
                for (int k = 0; k < db.getOrderLines(allOrder).get(j).size(); k++) {
                    //toegang onderdelen van arraylist
                    int orderlineVar = db.getOrderLines(allOrder).get(j).get(k);

                    if (allOrder == orderID) {
                        int itemID = db.getItemIDFromOrderline(orderlineVar);
                        int aantal = db.getAantalFromOrderlist(orderlineVar);
                        double gewicht = db.getGewicht(db.getItemIDFromOrderline(orderlineVar) + 1);

                        listModel.addElement("Orderline nr. " + orderlineVar
                                + "   ||    Item: " + db.getItems()[itemID]
                                + "   ||    Aantal: " + aantal
                                + "   ||    Gewicht: " + aantal + "*" + gewicht + " = "
                                + (gewicht * (float) aantal) + "kg");
                    }
                }
            }
        }
    }

    public boolean orderUitvoerbaar(int orderID) {
        System.out.println("Order id");
        System.out.println(orderID);
        ArrayList<Integer> orderlines = db.getOrderLines(orderID).get(0);
        HashMap<Integer, Integer> schapCopy = db.getAllProducts();

        System.out.println("orderlines");
        System.out.println(orderlines.toString());
        System.out.println("schapcopy");
        System.out.println(schapCopy);
        System.out.println();


//        for (int i = 0; i < orderlines.size(); i++) {
        for (int orderline : orderlines) {
            System.out.println("orderline");
            System.out.println(orderline);
            int itemID = db.getItemIDFromOrderline(orderline) + 1;

            System.out.println("Item id");
            System.out.println(itemID);

            if (!schapCopy.containsKey(itemID)) {
                return false;
            }

            if (schapCopy.get(itemID) > 0) {
                schapCopy.put(itemID, schapCopy.get(itemID) - 1);
            } else {
                return false;

            }
        }
//        }
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
            for (int allOrder : allOrders) {
                int selected = -1;
                if (orderCombo.getSelectedItem() != null) {
                    selected = Integer.parseInt(Objects.requireNonNull(orderCombo.getSelectedItem()).toString());
                }
                if (allOrder == selected) {
                    aantalBins.setText("Aantal bins nodig: " + updateAantalBins(allOrder));
                    displayOrderLinesList(allOrder);
                }
            }
        });

        executeOrder.addActionListener(e -> {
            if(orderCombo.getSelectedItem() != null) {
                if(!orderUitvoerbaar((int) orderCombo.getSelectedItem())){
                    JOptionPane.showMessageDialog(parentFrame, "Item niet in het schap", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ArrayList<Integer> arr = db.getItemArrayList((int) orderCombo.getSelectedItem());
                int[][] coordinates;
                if(arr.size() > 0){
                    coordinates = TSP.TSPAlgorithm(arr);
                    System.out.println(Arrays.deepToString(coordinates));
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "Niet genoeg items in het schap", "INANE ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }


                Serial.grid(26);

                int pickCount = 3;
                boolean res;
                for (int[] coordinate : coordinates) {
                    for (int j = 0; j < coordinate.length; j++) {
                        System.out.println("grid");
                        System.out.println(coordinate[j]);

                        res = Serial.grid(coordinate[j]);
                        System.out.println(res);

                        Serial.delay(100);

                        if (coordinate[j] == 26) {
                            System.out.println("NIET PAKKEN");
                        } else {
                            System.out.println("pick");
                            System.out.println(pickCount);

                            Serial.packet("pick", pickCount);
                            pickCount -= 1;

                            db.removeItems(db.getItemIDFromPlek(coordinate[j]), coordinate[j]);

                            Serial.delay(100);
                        }


                        if (coordinate[j] == 26 && j == coordinate.length - 1) {
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
        add(aantalBins);

        orderList.setPreferredSize(new Dimension(550,400));
        orderList.setLayout(new GridLayout(5,2));
        orderList.setBorder(BorderFactory.createLineBorder(Color.black));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
