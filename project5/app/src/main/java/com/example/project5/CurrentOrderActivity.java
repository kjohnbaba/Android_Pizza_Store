package com.example.project5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The CurrentOrderActivity class is a class that appears when the user clicks on the Current Order
 * button in the Main Window. This activity class will deal with the current order and display
 * all correct information about the current order such as subtotal, total after tax, etc.
 *
 * @author Jaspreet Kaur, Kerimcan Baba
 */
public class CurrentOrderActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    static ArrayAdapter<Pizza> arrayAdapter;
    private ListView store_list;
    private TextView subtotal;
    private TextView salesTax;
    private TextView orderTotal;
    private TextView orderNumber;
    private Button remove;
    private Button placeOrder;
    private Button clearOrder;
    private static final double TAX = .06625;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    /**
     * A method that will load the page and initializes all important information needed
     * for this activity. Will display the order list and prices.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Current Order");
        store_list = findViewById(R.id.store_list);
        subtotal = findViewById(R.id.subtotal);
        salesTax = findViewById(R.id.sales_tax);
        orderTotal = findViewById(R.id.order_total);
        orderNumber = findViewById(R.id.order_number);
        placeOrder = findViewById(R.id.place_order);
        remove = findViewById(R.id.remove_button);
        clearOrder = findViewById(R.id.clear_order);
        subtotal = findViewById(R.id.subtotal);
        arrayAdapter = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, MainActivity.order.getOrdersList());
        store_list.setAdapter(arrayAdapter);
        store_list.setOnItemClickListener(this);
        setPrice();
        ClearTheOrder();
        removeMessage();
        placetheOrder();
    }

    /**
     * A method that will place the order when the user clicks on the "Place Order" button.
     */
    public void placetheOrder(){
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!MainActivity.order.getOrdersList().isEmpty()){
                    MainActivity.storeOrder.add(MainActivity.order);
                    MainActivity.clearTheOrder();
                    salesTax.setText("Sales Tax: $0.00");
                    subtotal.setText("Subtotal: $0.00");
                    orderTotal.setText("Order Total: $0.00");
                    arrayAdapter.notifyDataSetChanged();
                    AlertDialog.Builder alert = new AlertDialog.Builder(CurrentOrderActivity.this);
                    alert.setTitle("Order Placed.");
                    AlertDialog dialog = alert.create();
                    dialog.show();
                }
            }
        });
    }


    /**
     * This is the method you must implement when you write implements AdapterView.OnItemClickListener
     * in the class heading.
     * This is the event handler for the onItemClick event.
     * @param adapterView an adapterView
     * @param view A view
     * @param i an integer i that is the position
     * @param l a long l
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete this Order?");
        alert.setMessage(adapterView.getAdapter().getItem(i).toString());
        //anonymous inner class to handle the onClick event of YES or NO.
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                RemoveAnOrder((Pizza) adapterView.getAdapter().getItem(i));
                Toast.makeText(getApplicationContext(), "Order removed.", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Order not removed.", Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * A method that will remove the order when the user clicks on the
     * "Remove Order" button.
     * @param pizza A pizza object that is to be removed.
     */
    public void RemoveAnOrder(Pizza pizza){
        if(MainActivity.order.getOrdersList() != null) {
            arrayAdapter.remove(pizza);
            MainActivity.order.remove(pizza);
            setPrice();
        }
    }

    /**
     * A method that will set the price for the entire order and displays
     * subtotal, tax, order total, etc.
     */
    public void setPrice(){
        if(MainActivity.order.getOrdersList() != null){
            String num = "Order Number: " + String.valueOf(MainActivity.order.getUniqueNum());
            orderNumber.setText(num);
                double total = 0.0;
                double tax = 0.0;
                double allTogether = 0.0;
                for (int x = 0; x < MainActivity.order.getOrdersList().size(); x++) {
                    total = total + MainActivity.order.getOrdersList().get(x).price();
                }
                tax = Double.parseDouble(String.valueOf(df.format(TAX * total)));
                String str = "Sales Tax: $" + String.valueOf(tax);
                salesTax.setText(str);
                total = Double.parseDouble(String.valueOf(df.format(total)));
                String str1 = "Subtotal: $" + String.valueOf(total);
                subtotal.setText(str1);
                allTogether = Double.parseDouble(String.valueOf(df.format(tax + total)));
                String str2 = "Order Total: $" + String.valueOf(allTogether);
                orderTotal.setText(str2);
        }
    }

    /**
     * A method that will clear the order when the user clicks
     * on the "Clear Order" button.
     */
    public void ClearTheOrder(){
        clearOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrayAdapter.isEmpty() != true){
                    arrayAdapter.clear();
                    arrayAdapter.notifyDataSetChanged();
                    MainActivity.clearTheOrder();
                }
                orderTotal.setText("Order Total: $0.00");
                subtotal.setText("Subtotal: $0.00");
                salesTax.setText("Sales Tax: $0.00");
            }
        });
    }

    /**
     * A helper method that will display an alert for when a user
     * clicks the "Remove Order" button.
     */
    public void removeMessage(){
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(CurrentOrderActivity.this);
                alert.setTitle("Delete this Order?");
                alert.setMessage("Must click an order to delete");
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }
}