package com.example.project5;
import java.util.ArrayList;

/**
 * The Order class is a class that will create an order
 * of pizzas. This class is responsible for adding pizzas to
 * the order or removing them.
 *
 * @author Kerimcan Baba
 */

public class Order implements Customizable{
    private static int numOfOrders;
    private ArrayList<Pizza> pizzaOrders;
    private int orderNumber;
    private Pizza pizza;
    private int uniqueNum;

    /**
     * A constructor that will create an instance of Order
     * with a unique ticket number.
     */
    public Order(){
        this.pizza = pizza;
        this.pizzaOrders = new ArrayList<Pizza>();
        this.orderNumber = orderNumber;
        numOfOrders++;
        this.uniqueNum = numOfOrders;
    }

    /**
     * A method that will return the ticket number of an order.
     * @return An integer value that is the ticket number of an order
     */
    public int getUniqueNum(){
        return this.uniqueNum;
    }

    /**
     * A method that will take in object and add that pizza to an order.
     * @param obj An object that is being added
     * @return A boolean value true if added or false if not
     */
    @Override
    public boolean add(Object obj){
        ArrayList<Pizza> list = this.pizzaOrders;
        Pizza t = (Pizza) obj;
        if(list.contains(t)){
            return false;
        }
        list.add(t);
        return true;
    }

    /**
     * A method that will take in an object and remove that pizza from
     * an order.
     * @param obj An object that is being removed
     * @return A boolean value true if removed or false if not
     */
    @Override
    public boolean remove(Object obj){
        ArrayList<Pizza> list = this.pizzaOrders;
        Pizza t = (Pizza) obj;
        if(!list.contains(t)){
            return false;
        }
        list.remove(t);
        return true;
    }

    /**
     * A method that will calculate the total price of an order
     * and returns the total as a boolean value.
     * @return A boolean value that represents the total price of an order.
     */
    public double getTotalPrice(){
        double total = 0.0;
        if(pizzaOrders.size() != 0){
            for(int x = 0; x < pizzaOrders.size(); x++){
                total = total + pizzaOrders.get(x).price();
            }
        }
        return total;
    }


    /**
     * A method that will print all the pizzas in the order list.
     * @return A String that represents the list of pizzas in the order
     */
    public String orderToString(){
        String str = "";
        for(int x = 0; x < pizzaOrders.size(); x++){
            str += pizzaOrders.get(x).toString() + "\n";
        }
        return str;
    }

    /**
     * A method that will return the list of pizzas of an order.
     * @return An ArrayList of pizzas of an order
     */
    public ArrayList<Pizza> getOrdersList(){
        return this.pizzaOrders;
    }
}
