package com.example.project5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


/**
 * The ChicagoPizzaActivity class is class that appears after the user selected the Chicago
 * Pizza button in the MainActivity page. This class displays the pizza options for Chicago style
 * pizza and the user can click on any option they want which will redirect them to a new page.
 *
 * @author Kerimcan Baba
 */
public class ChicagoPizzaActivity extends AppCompatActivity {

    private ArrayList<Item> items = new ArrayList<>();
    private int [] itemImages = {R.drawable.deepdishbbq, R.drawable.deepdishmeatzza, R.drawable.buildurowndeepdish,
            R.drawable.deluxechicago};

    /**
     * A method that will create the page when program starts the activity. It initializes
     * everything and sets all of the text boxes to the correct info.
     * @param savedInstanceState an object savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chicago_pizza);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Chicago Style Pizza");
        RecyclerView rcview = findViewById(R.id.RecyclerView);
        setupMenuItems(); //add the list of items to the ArrayList
        ItemsAdapter adapter = new ItemsAdapter(this, items); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        //use the LinearLayout for the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * A helper method that will setup the items in the recyclerView.
     */
    private void setupMenuItems() {
        String [] itemNames = getResources().getStringArray(R.array.nyPizzaNames);
        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Item(itemNames[i], itemImages[i]));
        }
    }
}
