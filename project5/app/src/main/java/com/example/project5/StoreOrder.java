package com.example.project5;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The StoreOrder class is a class that is responsible for
 * storing Order objects into an StoreOrder object and exporting
 * the orders.
 *
 * @author Kerimcan Baba
 */

public class StoreOrder implements Customizable{
    private ArrayList<Order> storeOrderList;

    private static final int NEGATIVE = -1;

    private static final double TAX = .06625;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * A constructor that creates an instance of StoreOrder.
     */
    public StoreOrder(){
        this.storeOrderList = new ArrayList<>();
    }

    /**
     * A method that will add an order to the storeOrderList.
     * @param obj An object that is being added
     * @return A boolean value true if added or false if not
     */
    @Override
    public boolean add(Object obj){
        ArrayList<Order> list = this.storeOrderList;
        Order t = (Order) obj;
        if(list.contains(t)){
            return false;
        }
        list.add(t);
        return true;
    }

    /**
     * A method that will take in the ticket number of an order
     * and return the order associated with that ticket number.
     * @param uniqueNum An integer that represents a ticket number belonging to an order
     * @return An order that belongs to that ticket number
     */
    public int getOrder(int uniqueNum){
        for(int x = 0; x < storeOrderList.size(); x++){
            if(storeOrderList.get(x).getUniqueNum() == uniqueNum){
                return x;
            }
        }
        return NEGATIVE;
    }

    /**
     * A method that will remove an order from the storeOrderList.
     * @param obj An  object that is being removed
     * @return A boolean value true if removed or false if not
     */
    @Override
    public boolean remove(Object obj){
        ArrayList<Order> list = this.storeOrderList;
        Order t = (Order) obj;
        if(!list.contains(t)){
            return false;
        }
        list.remove(t);
        return true;
    }

    /**
     * A method export, that will export all orders to a text file.
     */
    public void export() {
        String str = null;
        try {
            PrintWriter myWriter = new PrintWriter(new File("src/StoreOrderList.txt"));
            str = "";
            for (int i = 0; i < this.storeOrderList.size(); i++) {
                double total = (this.storeOrderList.get(i).getTotalPrice() * TAX) + this.storeOrderList.get(i).getTotalPrice();
                total = Double.parseDouble(String.valueOf(df.format(total)));
                str += "Order #" + this.storeOrderList.get(i).getUniqueNum() + ". \n"
                        + this.storeOrderList.get(i).orderToString() + "\n" + "Order Total: $" +
                        total + "\n";
            }
            myWriter.flush();
            myWriter.write(str);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method that will return the storeOrderList.
     * @return An ArrayList of Orders that is the storeOrderList
     */
    public ArrayList<Order> getStoreOrdersList(){
        return this.storeOrderList;
    }
}
