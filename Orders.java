public class Orders {
    static int[][] orders = new int[11][];
    static int aantalOrders = 0;

    static boolean setOrderCheck(int order, int item1, int item2, int item3){
        if(order > 25) return false;
        boolean res = orders[order] == null;

        if (!res) {
            return false;
        }

        setOrder(order, item1, item2, item3);
        aantalOrders += 1;
        return true;

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
