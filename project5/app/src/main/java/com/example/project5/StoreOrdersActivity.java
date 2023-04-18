package com.example.project5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The StoreOrderActivity class is the class responsible for holding all information of the
 * current store orders. This class works together with the CurrentOrderActivity class where they
 * both share data with each other. T
 *
 * @author Kerimcan Baba
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private Spinner orderNumberSpinner;
    private Button cancelOrder;
    private ListView storeOrderList;
    private TextView orderTotal;
    private static ArrayList<String> orderNums;
    private static ArrayAdapter<String> arrayAdapterSpinner;
    private static final double TAX = .06625;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    static ArrayAdapter<Pizza> adapterListView;

    /**
     * A method that loads the page and sets all text fields to the correct information as well
     * as populating the spinner and the listview.
     * @param savedInstanceState a object savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Store Orders");
        orderTotal = findViewById(R.id.order_totaltax);
        if (MainActivity.storeOrder.getStoreOrdersList().size() != 0) {
            setScene();
            orderNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String num = adapterView.getItemAtPosition(i).toString();
                int uniqueNum = Integer.parseInt(num);
                int temp = MainActivity.storeOrder.getOrder(uniqueNum);
                adapterListView = new ArrayAdapter<Pizza>(getApplicationContext(), android.R.layout.simple_list_item_1, MainActivity.storeOrder.getStoreOrdersList().get(temp).getOrdersList());
                adapterListView.notifyDataSetChanged();
                storeOrderList.setAdapter(adapterListView);
                double total = MainActivity.storeOrder.getStoreOrdersList().get(temp).getTotalPrice();
                total = Double.parseDouble(String.valueOf(df.format(total + (total * TAX))));
                String t = "Order Total: $" + total;
                orderTotal.setText(t);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
            });
        }
        else {
            orderTotal.setText("Order Total: $0.00");
        }
    }

    /**
     * A method that will remove the order that the user has selected in the spinner if the user
     * clicks the "Remove Order" button.
     * @param v
     */
    public void removeOrder(View v) {
        String num = (String)orderNumberSpinner.getSelectedItem();
        int uniqueNum = Integer.parseInt(num);
        int temp = MainActivity.storeOrder.getOrder(uniqueNum);
        if(!MainActivity.storeOrder.getStoreOrdersList().isEmpty()){
            if(MainActivity.storeOrder.getStoreOrdersList().indexOf(uniqueNum) == 0){
                if(MainActivity.storeOrder.getStoreOrdersList().size() == 1){
                    adapterListView = new ArrayAdapter<Pizza>(getApplicationContext(), android.R.layout.simple_list_item_1, MainActivity.storeOrder.getStoreOrdersList().get(0).getOrdersList());
                    orderNums.remove(uniqueNum);
                    MainActivity.storeOrder.getStoreOrdersList().remove(MainActivity.storeOrder.getStoreOrdersList().get(uniqueNum));
                    adapterListView.notifyDataSetChanged();
                    orderTotal.setText("Order Total: $0.00");
                }
                else {
                    int index = MainActivity.storeOrder.getStoreOrdersList().indexOf(uniqueNum) + 1;
                    adapterListView = new ArrayAdapter<Pizza>(getApplicationContext(), android.R.layout.simple_list_item_1, MainActivity.storeOrder.getStoreOrdersList().get(index).getOrdersList());
                    adapterListView.notifyDataSetChanged();
                    storeOrderList.setAdapter(adapterListView);
                    orderNums.remove(uniqueNum);
                    MainActivity.storeOrder.getStoreOrdersList().remove(MainActivity.storeOrder.getStoreOrdersList().get(uniqueNum));
                    adapterListView.notifyDataSetChanged();
                    this.recreate();
                }
            }
            else {
                String unique = orderNumberSpinner.getSelectedItem().toString();
                uniqueNum = Integer.parseInt(unique);
                temp = MainActivity.storeOrder.getOrder(uniqueNum);
                adapterListView.clear();
                storeOrderList.setAdapter(adapterListView);
                MainActivity.storeOrder.remove(MainActivity.storeOrder.getStoreOrdersList().get(temp));
                orderNums.remove(orderNumberSpinner.getSelectedItem().toString());
                this.recreate();
            }
            this.recreate();
        }
    }

    /**
     * A method that helps load the page by populating the spinner and listview with the correct
     * information and calculates all the prices.
     */
    public void setScene(){
        orderNumberSpinner = (Spinner) findViewById(R.id.spinner2);
        cancelOrder = findViewById(R.id.cancel_order);
        storeOrderList = findViewById(R.id.store_list);
        orderNums = new ArrayList<String>();
        for (int x = 0; x < MainActivity.storeOrder.getStoreOrdersList().size(); x++) {
            orderNums.add(String.valueOf(MainActivity.storeOrder.getStoreOrdersList().get(x).getUniqueNum()));
        }
        arrayAdapterSpinner = new ArrayAdapter<String>(StoreOrdersActivity.this, android.R.layout.simple_spinner_dropdown_item, orderNums);
        orderNumberSpinner.setAdapter(arrayAdapterSpinner);
        if(MainActivity.storeOrder.getStoreOrdersList().get(0).getOrdersList().size() != 0) {
            double total = MainActivity.storeOrder.getStoreOrdersList().get(0).getTotalPrice();
            total = Double.parseDouble(String.valueOf(df.format(total + (total * TAX))));
            orderTotal.setText(String.valueOf(total));
        }
        adapterListView = new ArrayAdapter<Pizza>(this, android.R.layout.simple_list_item_1, MainActivity.storeOrder.getStoreOrdersList().get(0).getOrdersList());
        storeOrderList.setAdapter(adapterListView);
    }
}
