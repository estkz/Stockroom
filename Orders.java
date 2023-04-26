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
//        for(int i=0; i<orders.length; i++){
//            if(orders[i] != null) {
//                for (int j = 0; j < orders[i].length; j++) {
//                    System.out.println("Order nr " + i + " item " + orders[i][j]);
//                }
//                System.out.println();
//                System.out.println();
//            }
//        }
        return orders;
    }

    static int getAantalOrders(){
        return aantalOrders;
    }
}
