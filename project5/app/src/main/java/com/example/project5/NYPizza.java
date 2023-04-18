package com.example.project5;

/**
 * NYPizza is a concrete class that implements the abstract class
 * PizzaFactory and creates instances of Pizza and the subclass type.
 * This is used in the controllers when creating an order.
 *
 * @author Kerimcan Baba
 */
public class NYPizza implements PizzaFactory{

    /**
     * A method createDeluxe that creates a Pizza object of
     * type Deluxe with the crust assigned to New York style pizza.
     * @return A Pizza object that has been created.
     */
    @Override
    public Pizza createDeluxe(){
        Pizza pizza = new Deluxe(Crust.BROOKLYN);
        return pizza;
    }

    /**
     * A method createMeatzza that creates a Pizza object of
     * type Meatzza with the crust assigned to New York style pizza.
     * @return A Pizza object that has been created.
     */
    @Override
    public Pizza createMeatzza() {
        Pizza pizza = new Meatzza(Crust.HANDTOSSED);
        return pizza;
    }

    /**
     * A method createBBQChicken that creates a Pizza object of
     * type BBQChicken with the crust assigned to New York style pizza.
     * @return A Pizza object that has been created.
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza pizza = new BBQChicken(Crust.THIN);
        return pizza;
    }

    /**
     * A method createBuildYourOwn that creates a Pizza object of
     * type BuildYourOwn with the crust assigned to New York style pizza.
     * @return A Pizza object that has been created.
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza pizza = new BuildYourOwn(Crust.HANDTOSSED);
        return pizza;
    }
}


