package com.example.project5;

/**
 * The Customizable class is an interface that is used in the
 * other classes.
 *
 * @author Jaspreet Kaur, Kerimcan Baba
 */
public interface Customizable {
    /**
     * A method that will add an object and return true if
     * added or false if not.
     * @param obj An object that is being added
     * @return A boolean value true if added or false if not.
     */
    boolean add(Object obj);

    /**
     * A method that will remove an object and return true
     * if removed or false if not.
     * @param obj A object that is being removed
     * @return A boolean value true if removed or false if not
     */
    boolean remove(Object obj);
}
