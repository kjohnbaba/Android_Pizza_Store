package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * The MainActivty is the main page and is the first page the user sees. The page will list
 * four options that the user can choose. This is the home page.
 *
 * @author Jaspreet Kaur, Kerimcan Baba
 */
public class MainActivity extends AppCompatActivity {

    private ImageButton chicagoButton;
    private ImageButton nyButton;
    private ImageButton currentOrderButton;
    private ImageButton storeOrderButton;
    public static Order order = new Order();
    public static StoreOrder storeOrder = new StoreOrder();

    /**
     * A method that will load the home page.
     * @param savedInstanceState an object savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Pizzeria");
        chicagoButton = (ImageButton) findViewById(R.id.imageButton5);
        nyButton = (ImageButton) findViewById(R.id.imageButton6);
        currentOrderButton = (ImageButton) findViewById(R.id.imageButton7);
        storeOrderButton = (ImageButton) findViewById(R.id.imageButton8);
        clickTheButton();
    }

    /**
     * A method that will load the respective activity based on the user's selection.
     */
    public void clickTheButton () {
            chicagoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, ChicagoPizzaActivity.class);
                    startActivity(intent);
                }
            });

            nyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, NYPizzaActivity.class);
                    startActivity(intent);
                }
            });

            currentOrderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
                    startActivity(intent);
                }
            });

            storeOrderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
                    startActivity(intent);
                }
            });
        }

    /**
     * A helper method that will clear the current order after it is placed.
     */
    public static void clearTheOrder(){
        order = new Order();
    }
    }

