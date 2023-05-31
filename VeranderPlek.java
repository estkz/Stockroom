import Serial.SimpleSerial;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Objects;


public class VeranderPlek extends JDialog {
    JFrame parentFrame;
    SimpleSerial Serial;

    Database db = new Database();

    JComboBox<String> items = new JComboBox<>();
    JComboBox<String> vrijePlekken = new JComboBox<>();

    JButton execute = new JButton("Voer uit");

    VeranderPlek(JFrame parentFrame, boolean m, SimpleSerial Serial){
        super(parentFrame, m);
        this.parentFrame = parentFrame;
        this.Serial = Serial;

        fillCombobox();

        setSize(new Dimension(600,600));
        setTitle("Order weergave");
        setLayout(new FlowLayout());
        setResizable(false);

        execute.addActionListener(e -> {
            Serial.packet("dump");

            int ophalen = ophalen();
            int wegbrengen = wegbrengen();


            if(ophalen != 0 && wegbrengen != 0){
                Serial.grid(ophalen);
                Serial.packet("pick", 3);
                Serial.grid(wegbrengen);
                Serial.packet("place", 3);
                Serial.grid(26);

                int ophalenID = db.getItemIDFromPlek(ophalen);

                db.removeItems(ophalenID, ophalen);
                db.setItems(wegbrengen, ophalenID);
                VoorraadPanel.drawVoorraad();
                fillCombobox();

                revalidate();
                repaint();
                invalidate();
            }
        });

        add(items);
        add(vrijePlekken);
        add(execute);

        setVisible(true);
    }

    public int ophalen(){
        String s = Objects.requireNonNull(items.getSelectedItem()).toString();
        int divider1 = 0;
        int divider2 = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ':'){
                divider1 = i+1;
            }
            if(s.charAt(i) == '|'){
                divider2 = i-1;
                break;
            }
        }
        return Integer.parseInt(items.getSelectedItem().toString().substring(divider1, divider2));
    }

    public int wegbrengen(){
        String s = Objects.requireNonNull(vrijePlekken.getSelectedItem()).toString();
        int divider = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ':'){
                divider = i;
                break;
            }
        }
        return Integer.parseInt(vrijePlekken.getSelectedItem().toString().substring(divider+1, s.length()));
    }

    public void fillCombobox(){
        items.removeAllItems();
        vrijePlekken.removeAllItems();

        int[] plekken = db.getPlekken();
        for (int i = 0; i < plekken.length; i++) {
            if(plekken[i] > 0) {
                int itemID = plekken[i];
                String itemName = db.getItems()[itemID-1];
                items.addItem("plek:" + (i+1) + " || item_id: " + itemID+" "+itemName);
            }
        }
        for (int i = 0; i < plekken.length; i++) {
            if(plekken[i] == 0) {
                vrijePlekken.addItem("plek:" + (i+1));
            }
        }
    }
}
