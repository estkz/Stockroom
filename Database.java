import java.sql.*;

public class Database {

    String url = "jdbc:mysql://127.0.0.1:3306/nerdygadgets_robotarm";
    String user = "root";
    String pass = "root";

    public int[] getItems(){
        int[] x = new int[25];

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM items");

            while(rs.next()) {
                x[rs.getInt(3)-1] = rs.getInt(1);
            }
            con.close();

        } catch(Exception e){
            System.out.println(e);
        }
        return x;
    }

    public void setItems(int a, String b, int plek){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO items (item_id, kleur, plek) VALUES ("+a+",'"+b+"',"+plek+")");

            con.close();

        } catch(Exception e){
            System.out.println(e);
        }
    }

    public boolean setItems(int itemID, int plek){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            Statement stmt = con.createStatement();
            stmt.executeUpdate("INSERT INTO items (item_id, plek) VALUES ("+itemID+","+plek+")");

            con.close();
            return true;

        } catch(Exception e){
            System.out.println(e);
            return false;
        }
    }

    public void removeItems(int a){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);

            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM items WHERE item_id="+a);

            con.close();

        } catch(Exception e){
            System.out.println(e);
        }
    }
}
