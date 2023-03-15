package com.example.project5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * The MainActivityNY class is where pizzas are being made and orders are being sent to the
 * current order page. This activity is responsible for gathering information about pizzas and creating
 * them based on the size and what type they are.
 *
 * @author Jaspreet Kaur, Kerimcan Baba
 */
public class MainActivityNY extends AppCompatActivity {

    private TextView pizza_option;
    private TextView pizza_crust;
    static TextView pizza_price;
    private Spinner spinner;
    private ArrayAdapter<CharSequence> adapter;
    static String pizzaChoice;
    private String pizzaType;
    private ImageView pizza_image;
    static PizzaFactory pizzaFactory = new NYPizza();
    private Button selectToppings;
    static ListView toppings_view;
    public static ArrayList<String> toppings;
    static ArrayAdapter<String> arrayAdapter;
    private Button add_order1;
    static String size;

    /**
     * A method that will load the activty page with the correct pizza type, image, price
     * and toppings.
     * @param savedInstanceState an object savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("New York Style Pizza");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymainny);
        pizzaType = getIntent().getStringExtra("Pizza_Type");
        pizzaChoice = getIntent().getStringExtra("Pizza_Choice");
        pizza_option = (TextView) findViewById(R.id.pizza_option1);
        pizza_price = (TextView) findViewById(R.id.price1);
        pizza_crust = (TextView)findViewById(R.id.crust1);
        pizza_option.setText(pizzaChoice);
        toppings_view = (ListView) findViewById(R.id.toppings_view1);
        pizza_image = (ImageView) findViewById(R.id.pizza_image1);
        selectToppings = (Button) findViewById(R.id.button7);
        if(!pizzaChoice.equals("Build Your Own")){
            selectToppings.setVisibility(View.INVISIBLE);
        }
        add_order1 = findViewById(R.id.add_order1);
        spinner = (Spinner) findViewById(R.id.size_spinner1);
        adapter = ArrayAdapter.createFromResource(this, R.array.pizzaSize, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        setSize(pizzaChoice);
        setScene(pizzaChoice);
        callToppings();
        setAddButtonOnClick();
    }
    /**
     * A method that will add the current pizza to the current order if the user selects
     * yes to the alert or do nothing if the user selects no.
     */
    private void setAddButtonOnClick() {
        add_order1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivityNY.this);
                alert.setTitle("Add to cart");
                alert.setMessage("Add to the order?");
                //anonymous inner class to handle the onClick event of YES or NO.
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if(setPrice(pizzaChoice,size) != null){
                            MainActivity.order.add(setPrice(pizzaChoice,size));
                        }
                        Toast.makeText(MainActivityNY.this, "Pizza added.", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivityNY.this, "Pizza not added.", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }
    /**
     * A helper method that will assign all variables to the correct ids and set all text to the
     * correct information. This method also helps display the correct toppings and price.
     * @param pizzaChoice a String object that represents the pizza choice.
     */
    public void setScene(String pizzaChoice){
        if(pizzaChoice.equals("Meatzza")){
            toppings = new ArrayList<String>(Arrays.asList("Sausage", "Pepperoni", "Beef", "Ham"));
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toppings);
            toppings_view.setAdapter(arrayAdapter);
            pizza_crust.setText("Crust: Hand Tossed");
            pizza_image.setImageResource(R.drawable.meatzza);
        }
        if(pizzaChoice.equals("BBQ Chicken")){
            toppings = new ArrayList<String>(Arrays.asList("BBQ Chicken", "Green Pepper", "Provolone", "Cheddar"));
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toppings);
            toppings_view.setAdapter(arrayAdapter);
            pizza_crust.setText("Crust: Thin");
            pizza_image.setImageResource(R.drawable.bbqchicken);
        }
        if(pizzaChoice.equals("Build Your Own")){
            toppings = new ArrayList<String>();
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toppings);
            toppings_view.setAdapter(arrayAdapter);
            pizza_crust.setText("Crust: Hand Tossed");
            pizza_image.setImageResource(R.drawable.buildyourownpizza);
        }
        if(pizzaChoice.equals("Deluxe")){
            toppings = new ArrayList<String>(Arrays.asList("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom"));
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, toppings);
            toppings_view.setAdapter(arrayAdapter);
            pizza_crust.setText("Crust: Brooklyn");
            pizza_image.setImageResource(R.drawable.deluxenypizza);
        }
    }
    /**
     * A method that will set the size of a pizza object.
     * @param pizzaChoice A String that represents the size.
     */
    public void setSize(String pizzaChoice){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                size = adapterView.getItemAtPosition(i).toString();
                setPrice(pizzaChoice, size);
                Toast.makeText(getApplicationContext(), pizzaChoice, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    /**
     * A helper method that will make a Build Your Own pizza type with the right
     * toppings and return the pizza.
     * @param pizza A pizza object that is to be modified.
     * @return A pizza object returned after toppings are added
     */
    public static Pizza BuildOwnHelper(Pizza pizza){
        if(!(toppings == null)){
            for(int i = 0; i < toppings.size(); i ++){
                pizza.add(Topping.valueOf(toppings.get(i)));
            }
        }
        String str = "Price: $" + pizza.price();
        pizza_price.setText(str);
        return pizza;
    }

    /**
     * A method that will call the Toppings activity page if the user clicks the
     * "Select Toppings" button.
     */
    public void callToppings(){
        selectToppings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityNY.this, ToppingActivity.class);
                intent.putExtra("type", "NY");
                startActivity(intent);
            }
        });
    }

    /**
     * A method that will set the price according to the type of pizza and how many toppings.
     * @param pizzaChoice A String that is the choice of pizza
     * @param size A String that is the size of the pizza
     * @return A Pizza object that has been created
     */
    public static Pizza setPrice(String pizzaChoice, String size){
        if(pizzaChoice.equals("Meatzza")){
            Pizza pizza = pizzaFactory.createMeatzza();
            pizza.setSize(Size.valueOf(size));
            String str = "Price: $" + pizza.price();
            pizza_price.setText(str);
            return pizza;
        }
        if(pizzaChoice.equals("BBQ Chicken")){
            Pizza pizza = pizzaFactory.createBBQChicken();
            pizza.setSize(Size.valueOf(size));
            String str = "Price: $" + pizza.price();
            pizza_price.setText(str);
            return pizza;
        }
        if(pizzaChoice.equals("Deluxe")){
            Pizza pizza = pizzaFactory.createDeluxe();
            pizza.setSize(Size.valueOf(size));
            String str = "Price: $" + pizza.price();
            pizza_price.setText(str);
            return pizza;
        }
        if(pizzaChoice.equals("Build Your Own")){
            Pizza pizza = pizzaFactory.createBuildYourOwn();
            pizza.setSize(Size.valueOf(size));
            return BuildOwnHelper(pizza);
        }
        return null;
    }
}