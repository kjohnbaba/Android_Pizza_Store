package com.example.project5;

/**
 * The PizzaFactory interface is responsible for
 * creating types of pizzas and is used in the Chicago Pizza
 * and New York Pizza classes.
 *
 * @author Kerimcan Baba
 */
public interface PizzaFactory {

    /**
     * A method that will create a pizza of Deluxe type.
     * @return A pizza of Deluxe type
     */
    Pizza createDeluxe();

    /**
     * A method that will create a pizza of Meatzza type.
     * @return A pizza of Meatzza type
     */
    Pizza createMeatzza();

    /**
     * A method that will create a pizza of BBQChicken type.
     * @return A pizza of BBQChicken type
     */
    Pizza createBBQChicken();

    /**
     * A method that will create a pizza of Build Your Own type.
     * @return A pizza of Build Your Own type
     */
    Pizza createBuildYourOwn();
}
