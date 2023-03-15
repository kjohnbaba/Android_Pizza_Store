package com.example.project5;
import java.util.ArrayList;

/**
 * The Pizza class creates an instance of pizza and sets th crust and toppings
 * accordingly. This is the parent class of the four subclasses Deluxe,
 * BuildYourOwn, BBQChicken, and Meatzza.
 *
 * @author Jaspreet Kaur, Kerimcan Baba
 */
public abstract class Pizza implements Customizable {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;
    public abstract double price();

    public Pizza(){
        this.crust = crust;
        this.size = size;
        this.toppings = new ArrayList<>();
    }

    /**
     * A constructor with a parameter of a Crust object that will create an instance
     * of Pizza with the desired crust and the default toppings.
     * @param crust A Crust object that will be set to the crust of the pizza
     */
    public Pizza(Crust crust){
        this.crust = crust;
        this.size = size;
        this.toppings = new ArrayList<>();
    }

    /**
     * A method that will add a topping to a pizza object and is used in the controller
     * classes and the constructors for the subclasses.
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
     * A method that will remove a topping to a pizza object and is used in the controller
     * classes for Build Your Own pizza.
     * @param obj An object that is being removed
     * @return Returns a boolean value true if removed or false if not.
     */
    @Override
    public boolean remove(Object obj){
        ArrayList<Topping> list = this.getToppings();
        Topping t = (Topping) obj;
        if(!list.contains(t)){
            return false;
        }
        list.remove(t);
        return true;
    }

    /**
     * A getter method that will return the toppings of a pizza object.
     * @return Returns an ArrayList of Topping objects that belong to the pizza object
     */
    public ArrayList<Topping> getToppings(){
        return this.toppings;
    }

    /**
     * A setter method that will set the size of a pizza object.
     * @param size A Size object that will be set to a pizza object.
     */
    public void setSize(Size size){
        this.size = size;
    }

    /** A getter method that will get the size of a pizza object
     * and return it.
     * @return A Size objec that is the size of the pizza object.
     */
    public Size getSize(){
        return this.size;
    }

    /**
     * A method that will return the String representation of the pizza
     * object.
     * @return A String object that is the representation of the pizza object.
     */
    public String toString(){
        if(crust == Crust.DEEPDISH | crust == Crust.PAN | crust == Crust.STUFFED) {
            return "(Chicago Style - " + crust + "), " + toppings.toString() + ", " + size + " $" + this.price();
        }
        return "(New York Style - " + crust + "), " + toppings.toString() + ", " + size + " $" + this.price();
    }
}
