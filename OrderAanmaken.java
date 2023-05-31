import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


  public class OrderAanmaken extends JDialog implements ActionListener {
      Database db = new Database();
      JFrame parentFrame;
      int TestID = 13;

      JButton addID;
      JTextField IDtext;

      OrderAanmaken(JFrame f, boolean m) {
          super(f, m);
          this.parentFrame = f;
          IDtext = new JTextField("");
          addID = new JButton("OrderID indienen");
          addID.addActionListener(this);

          JButton addItem = new JButton("Item toevoegen");
          JButton removeItem = new JButton("Item verwijderen");

          setSize(new Dimension(600, 600));
          setLayout(new FlowLayout());
          setResizable(false);

          DefaultListModel<String> demoList = new DefaultListModel<>();
          JList<String> list = new JList<>(demoList);
          list.setPreferredSize(new Dimension(500, 400));

          IDtext.setPreferredSize(new Dimension(30, 30));

          this.add(IDtext);
          this.add(addID);

          setLocationRelativeTo(null);
          setVisible(true);

      }

      @Override
      public void actionPerformed(ActionEvent er) {

              try {
                  if (er.getSource() == addID) {
                      String ID = (String) IDtext.getText();
                      int IDint = Integer.parseInt(ID);

                      if (IDint == TestID) {
                          JOptionPane.showMessageDialog(null, "Je ingediende ID is als bestaand.", "Error",JOptionPane.ERROR_MESSAGE);
                      } else if (IDint > 0) {
                          JOptionPane.showMessageDialog(null, "Het ID is toegevoegd.", "", JOptionPane.QUESTION_MESSAGE);
                      } else {
                          JOptionPane.showMessageDialog(null, "Je hebt geen geldig ID opgegeven probeer het opnieuw","Error", JOptionPane.ERROR_MESSAGE);
                      }
                  }
              }  catch (Exception e) {
                  JOptionPane.showMessageDialog(null, "Je hebt geen geldig ID opgegeven probeer het opnieuw","Error", JOptionPane.ERROR_MESSAGE);

              }
            }
          }

