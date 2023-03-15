package com.example.project5;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The BuildYourOwn class is a subclass of the Pizza class. It creates an instance of
 * pizza as well as an instance of BuildYourOwn. This class creates an instance
 * of BuildYourOwn pizza by setting the crust accordingly to the style of pizza
 * and setting the correct toppings depending on the user.
 *
 * @author Jaspreet Kaur, Kerimcan Baba
 */
public class BuildYourOwn extends Pizza {

    private static final double SMALL = 8.99;
    private static final double MEDIUM = 10.99;
    private static final double LARGE = 12.99;

    private static final int MAXTOPPINGS = 7;

    private static final double EXTRA = 1.59;

    private static final DecimalFormat df = new DecimalFormat("0.00");


    /**
     * A default constructor that creates an instance of BuildYourOwn pizza.
     */

    public BuildYourOwn(){
        super();
    }

    /**
     * A constructor with a parameter of a Crust object that will create an instance
     * of BuildYourOwn with the desired crust.
     * @param crust A Crust object that will be set to the crust of the pizza
     */
    public BuildYourOwn(Crust crust){
        super(crust);
    }

    /**
     * A method that calculates the price of a BuildYourOwn pizza object according to
     * the size of the pizza and number of toppings and returns the price as a double type.
     * @return Returns a double that is the computed price of the BuildYourOwn pizza object.
     */
    @Override
    public double price() {
            double extraToppings = (this.getToppings().size()) * EXTRA;
            if (this.getSize().equals(Size.SMALL)) {
                double price = Double.parseDouble(String.valueOf(df.format(SMALL + extraToppings)));
                return price;
            }
            if (this.getSize().equals(Size.MEDIUM)) {
                double price = Double.parseDouble(String.valueOf(df.format(MEDIUM + extraToppings)));
                return price;
            }
            if (this.getSize().equals(Size.LARGE)) {
                double price = Double.parseDouble(String.valueOf(df.format(LARGE + extraToppings)));
                return price;
            }

        return 0;
    }

    /**
     * A method that will add a topping to a BuildYourOwn pizza object based on what the user
     * has selected.
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
     * A method that will remove a topping to a BuildYourOwn pizza object based on what
     * the user has selected.
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
        return "Build Your Own " + super.toString();
    }

}
