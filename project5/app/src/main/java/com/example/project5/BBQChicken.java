package com.example.project5;
import java.util.ArrayList;

/**
 * The BBQChicken class is a subclass of the Pizza class. It creates an instance of
 * pizza as well as an instance of BBQChicken. This class creates an instance
 * of BBQChicken pizza by setting the crust accordingly to the style of pizza
 * and setting the correct toppings.
 *
 * @author Jaspreet Kaur, Kerimcan Baba
 */
public class BBQChicken extends Pizza {

    private static final double SMALL = 13.99;
    private static final double MEDIUM = 15.99;
    private static final double LARGE = 17.99;

    /**
     * A default constructor that creates an instance of BBQChicken pizza and sets
     * the pizza to the correct toppings.
     */
    public BBQChicken(){
        super();
        this.getToppings().add(Topping.BBQCHICKEN);
        this.getToppings().add(Topping.GREENPEPPER);
        this.getToppings().add(Topping.PROVOLONE);
        this.getToppings().add(Topping.CHEDDAR);
    }

    /**
     * A constructor with a parameter of a Crust object that will create an instance
     * of BBQChicken with the desired crust and the default toppings.
     * @param crust A Crust object that will be set to the crust of the pizza
     */
    public BBQChicken(Crust crust){
        super(crust);
        this.getToppings().add(Topping.BBQCHICKEN);
        this.getToppings().add(Topping.GREENPEPPER);
        this.getToppings().add(Topping.PROVOLONE);
        this.getToppings().add(Topping.CHEDDAR);
    }

    /**
     * A method that calculates the price of a BBQChicken pizza object according to
     * the size of the pizza and returns the price as a double type.
     * @return Returns a double that is the computed price of the BBQChicken pizza object.
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
     * A method that will add a topping to a BBQChicken pizza object, and it is used in
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
     * A method that will remove a topping to a BBQChicken pizza object.
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
        return "BBQ Chicken " + super.toString();
    }

}
