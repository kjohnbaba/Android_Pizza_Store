package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * The ToppingActivity class is responsible for populating the toppings recycler view with the
 * correct labels and images.
 *
 * @author Kerimcan Baba
 */
public class ToppingActivity extends AppCompatActivity {

    private ArrayList<Item> items = new ArrayList<>();
    static ItemsAdapter adapter;
    private int [] itemImages = {R.drawable.anchovies, R.drawable.beef, R.drawable.cheddar,
            R.drawable.ham, R.drawable.mushroom, R.drawable.onion, R.drawable.pineapple,
            R.drawable.pepperonitopping,R.drawable.greenpeppertopping,R.drawable.provolone,R.drawable.sausagetopping};

    /**
     * A method that will load the toppings page and helps populate the recycler view.
     * @param savedInstanceState an object savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toppings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Toppings");
        RecyclerView rcview = findViewById(R.id.RecyclerView);
        setupMenuItems(); //add the list of items to the ArrayList
        adapter = new ItemsAdapter(this, items, getIntent().getStringExtra("type")); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        //use the LinearLayout for the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));


    }

    /**
     * A method that will return back to the correct activity.
     * @param item An item
     * @return a true value if successful or false if not
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A method that helps populate the recyclerview with the correct labels and images.
     */
    private void setupMenuItems() {
        String [] itemNames = getResources().getStringArray(R.array.itemNames);
        for (int i = 0; i < itemNames.length; i++) {
            items.add(new Item(itemNames[i], itemImages[i]));
        }
    }





}
