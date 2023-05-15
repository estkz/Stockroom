public class Config {
    static String ip = "127.0.0.1";
    static String port = "3306";
    static String user = "root";
    static String pass = "root";

    //niet veranderen
    static String url = "jdbc:mysql://"+ip+":"+port+"/nerdygadgets_robotarm";


    static String[] getInfo(){
        String[] info = new String[3];
        info[0] = url;
        info[1] = user;
        info[2] = pass;

        return info;
    }
}
