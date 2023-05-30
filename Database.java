import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

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

    public String getDBString(String tabel, String returnColomn, String condition) {
        String returnValue;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/nerdygadgets_robotarm", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tabel + " WHERE " + condition);

            if(!rs.next()){
                con.close();
                stmt.close();
                return null;
            }

            returnValue = rs.getString(returnColomn);
            con.close();
            return returnValue;
        } catch(Exception e){
            System.out.println(e);
            System.out.println(info[1]);
            System.out.println(e);
        }
        return null;
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
            ResultSet rs = stmt.executeQuery("SELECT order_id FROM orders WHERE order_id = "+order_id);

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
            ResultSet rs = stmt.executeQuery("SELECT * FROM orders");
            while(rs.next()) {
                x++;
            }
            con.close();
        } catch(Exception e){
            System.out.println(e);
        }
        return x;
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
}
