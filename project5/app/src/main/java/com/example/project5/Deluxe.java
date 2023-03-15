package com.example.project5;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * The Deluxe class is a subclass of the Pizza class. It creates an instance of
 * pizza as well as an instance of Deluxe. This class creates an instance
 * of Deluxe pizza by setting the crust accordingly to the style of pizza
 * and setting the correct toppings.
 *
 * @author Jaspreet Kaur, Kerimcan Baba
 */
public class Deluxe extends Pizza {

    public static final double SMALL = 14.99;
    public static final double MEDIUM = 16.99;
    public static final double LARGE = 18.99;


    /**
     * A default constructor that creates an instance of Deluxe pizza and sets
     * the pizza to the correct toppings.
     */
    public Deluxe(){
        super();
        this.getToppings().add(Topping.SAUSAGE);
        this.getToppings().add(Topping.PEPPERONI);
        this.getToppings().add(Topping.GREENPEPPER);
        this.getToppings().add(Topping.ONION);
        this.getToppings().add(Topping.MUSHROOM);
    }

    /**
     * A constructor with a parameter of a Crust object that will create an instance
     * of Deluxe with the desired crust and the default toppings.
     * @param crust A Crust object that will be set to the crust of the pizza
     */
    public Deluxe(Crust crust){
        super(crust);
        this.getToppings().add(Topping.SAUSAGE);
        this.getToppings().add(Topping.PEPPERONI);
        this.getToppings().add(Topping.GREENPEPPER);
        this.getToppings().add(Topping.ONION);
        this.getToppings().add(Topping.MUSHROOM);
    }

    /**
     * A method that calculates the price of a Deluxe pizza object according to
     * the size of the pizza and returns the price as a double type.
     * @return Returns a double that is the computed price of the Deluxe pizza object.
     */
    @Override
    public double price() {
        if(this.getSize().equals(Size.SMALL)){
            return SMALL;
        }
        if(this.getSize().equals(Size.MEDIUM)){
            return MEDIUM;
        }
        if(this.getSize().equals(Size.LARGE)){
            return LARGE;
        }
        return 0;
    }

    /**
     * A method that will add a topping to a Deluxe pizza object, and it is used in
     * the constructor for setting toppings.
     * @param obj An object that is being added
     * @return Returns a boolean value true if added or false if not.
     */
    @Override
    public boolean add(Object obj){
        ArrayList<Topping> list = this.getToppings();
        Topping t = (Topping) obj;
        if(list.contains(t)){
            return false;
        }
        list.add(t);
        return true;
    }

    /**
     * A method that will remove a topping to a Deluxe pizza object.
     * @param obj An object that is being removed
     * @return Returns a boolean value true if added or false if not.
     */
    @Override
    public boolean remove(Object obj) {
        ArrayList<Topping> list = this.getToppings();
        Topping t = (Topping) obj;
        if (!list.contains(t)) {
            return false;
        }
        list.remove(t);
        return true;
    }

    /**
     *  A toString method that returns the object being represented
     *  in String form with necessary information about the object.
     * @return A String value that is the object
     */
    @Override
    public String toString(){
        return "Deluxe " + super.toString();
    }

}
