import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    String[] info = Config.getInfo();

    public int getAantalItems() {
        int x = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM items");
            while(rs.next()) {
                x++;
            }
            con.close();
        } catch(Exception e){
            System.out.println(e);
        }
        return x;
    }

    public String[] getItems(){
        String[] x = new String[getAantalItems()+1];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM items");

            while(rs.next()) {
                x[rs.getInt(1)-1] = rs.getString(2);
            }
            con.close();

        } catch(Exception e){
            System.out.println(e);
        }
        return x;
    }

    public int[] getPlekken(){
        int[] x = new int[25];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM schap");

            while(rs.next()) {
                x[rs.getInt(1)-1] = rs.getInt(2);
            }
            con.close();

        } catch(Exception e){
            System.out.println(e);
        }
        return x;
    }
    public boolean setItems(int plek, int itemID){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO schap (plek, item_id) VALUES ("+plek+","+itemID+")");
            con.close();
            return true;
        } catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean removeItems(int a, int b){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM schap WHERE item_id="+a+" AND plek="+b);
            con.close();

            return true;
        } catch(Exception e){
            System.out.println(e);
        }
        return false;
    }

    public int getOrderID(int order_id) {
        int x = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT order_id FROM orders WHERE order_id = "+order_id+" AND voltooid=0");

            while(rs.next()) {
                x = rs.getInt(1);
            }
            con.close();
        } catch(Exception e){
            System.out.println(e);
        }
        return x;
    }

    public ArrayList<ArrayList<Integer>> getOrderLines(int order_id) {
        ArrayList<ArrayList<Integer>> x = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT orderline_id FROM orderlines WHERE order_id = "+order_id);

            ArrayList<Integer> temp = new ArrayList<Integer>();
            while(rs.next()) {
                temp.add(rs.getInt(1));
            }

            x.add(temp);

            con.close();
        } catch(Exception e){
            System.out.println(e);
        }
        return x;
    }

    public int getAantalOrders() {
        int x = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders WHERE voltooid = 0");
            while(rs.next()) {
                x++;
            }
            con.close();
        } catch(Exception e){
            System.out.println(e);
        }
        return x;
    }

    public int[] getAllOrders() {
        ArrayList<Integer> x = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders WHERE voltooid = 0");

            while(rs.next()) {
                x.add(rs.getInt(1));
            }

            con.close();
        } catch(Exception e){
            System.out.println(e);
        }

        int[] xArr = new int[x.size()];

        for (int i = 0; i < x.size(); i++) {
            xArr[i]= x.get(i);
        }
        return xArr;
    }

    public int getItemIDFromOrderline(int orderline){
        int x = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orderlines WHERE orderline_id="+orderline);

            while(rs.next()) {
                x = rs.getInt(3);
            }

            con.close();
        } catch(Exception e){
            System.out.println(e);
        }
        return x-1;
    }

    public int[] getPlekFromItemID(int itemID, int aantal){
        int[] x = new int[aantal];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT plek FROM schap WHERE item_id="+itemID);

            int max = 0;
            int i = 0;
            while(rs.next()) {
                if (i < aantal) {
                    x[i] = rs.getInt(1);
                }
                i++;
            }

            if (aantal > i) {
                con.close();
                return new int[0];
            }

            con.close();
        } catch(Exception e){
            System.out.println(e);
        }
        return x;
    }

    public int getItemIDFromPlek(int plek){
        int x = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT item_id FROM schap WHERE plek="+plek);

            while(rs.next()) {
                x = rs.getInt(1);
            }

            con.close();
        } catch(Exception e){
            System.out.println(e);
        }
        return x;
    }

    public ArrayList<Integer> getItemArrayList(int orderID){
        ArrayList<Integer> items;
        ArrayList<Integer> arr = new ArrayList<>();
        HashMap<Integer, Integer> schap = getAllProducts();

        for(int i=1; i<= getAantalOrders(); i++){
            for(int j=0; j<getOrderLines(i).size(); j++) {
                for (int k=0; k<getOrderLines(i).get(j).size(); k++) {
                    HashMap<Integer, ArrayList<Integer>> boodschappen = getAllItems(orderID);

//                    int orderlineVar = getOrderLines(i).get(j).get(k);
//                    int itemID = getItemIDFromOrderline(orderlineVar) + 1;

                    for(int l : boodschappen.keySet()){
                        int itemID = boodschappen.get(l).get(1);
                        int aantal = boodschappen.get(l).get(2);
                        boodschappen.get(l);


                        int[] plek = getPlekFromItemID(itemID, aantal);
                        if(plek.length < aantal){
                            return new ArrayList<>();
                        }

                        if (orderID == i) {
                            for (int m = 0; m < plek.length; m++) {
                                    if(!arr.contains(plek[m])) {
                                        arr.add(plek[m]);
                                    }
                            }
                        }
                    }
                }
            }
        }
        items = new ArrayList<>(arr);
        return items;
    }

    public int getAantalFromOrderlist(int orderLineID){
        int x = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT aantal FROM orderlines WHERE orderline_id="+orderLineID);

            while(rs.next()) {
                x = rs.getInt(1);
            }

            con.close();
        } catch(Exception e){
            System.out.println(e);
        }
        return x;
    }

    public double getGewicht(int itemID) {
        double x = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT gewicht_in_kg FROM items WHERE item_id=" + itemID);

            while (rs.next()) {
                x = rs.getInt(1);
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return x;
    }

    public void setOrderVoltooid(int orderID){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("UPDATE orders SET voltooid = 1 WHERE order_id = "+orderID);
            con.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public HashMap<Integer, Integer> getAllProducts(){
        HashMap<Integer, Integer> schappen = new HashMap<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM schap");

            while (rs.next()) {
                int itemID = rs.getInt(2);

                if(schappen.containsKey(itemID)) {
                    schappen.put(itemID, schappen.get(itemID)+1);
                } else {
                    schappen.put(itemID, 1);
                }

            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return schappen;
    }

    public HashMap<Integer, ArrayList<Integer>> getAllItems(int orderID){
        HashMap<Integer, ArrayList<Integer>> boodschappen = new HashMap<>();
        ArrayList<Integer> boodschap = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(info[0], info[1], info[2]);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM orderlines WHERE order_id=" + orderID);

            while (rs.next()) {
                boodschap = new ArrayList<>();
                boodschap.add(rs.getInt(2)); //order id
                boodschap.add(rs.getInt(3)); //item id
                boodschap.add(rs.getInt(4)); //aantal

                boodschappen.put(rs.getInt(1), boodschap);//key = orderline id
            }

            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return boodschappen;
    }
}
