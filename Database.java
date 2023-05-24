import java.sql.*;

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
}
