import java.util.ArrayList;

public class Orders {
    static int[][] orders = new int[11][];
    static int aantalOrders = 0;

    static boolean setOrderCheck(int order, int item1, int item2, int item3){
        boolean res = orders[order] == null;

        if(res){
            setOrder(order, item1, item2, item3);
            aantalOrders += 1;
            return true;
        }

        return false;
    }

    static void setOrder(int order, int item1, int item2, int item3){
        orders[order] = new int[]{item1, item2, item3};
    }


    static int[][] getOrders(){
        return orders;
    }

    static int getAantalOrders(){
        return aantalOrders;
    }
}
