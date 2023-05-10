public class Config {
    static String url = "jdbc:mysql://127.0.0.1:3306/nerdygadgets_robotarm";
    static String user = "root";
    static String pass = "root";


    static String[] getInfo(){
        String[] info = new String[3];
        info[0] = url;
        info[1] = user;
        info[2] = pass;

        return info;
    }
}
