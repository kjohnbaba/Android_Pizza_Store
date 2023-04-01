package com.example.project5;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * This is an Adapter class to be used to instantiate an adapter for the RecyclerView.
 * Must extend RecyclerView.Adapter, which will enforce you to implement 3 methods:
 *      1. onCreateViewHolder, 2. onBindViewHolder, and 3. getItemCount
 *
 * You must use the data type <thisClassName.yourHolderName>, in this example
 * <ItemAdapter.ItemHolder>. This will enforce you to define a constructor for the
 * ItemAdapter and an inner class ItemsHolder (a static class)
 * The ItemsHolder class must extend RecyclerView.ViewHolder. In the constructor of this class,
 * you do something similar to the onCreate() method in an Activity.
 * @author Kerimcan Baba
 */

class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsHolder>{
    private Context context; //need the context to inflate the layout
    private ArrayList<Item> items; //need the data binding to each row of RecyclerView
    private String type;
    private static final int MAX = 7;

    /**
     * A constructor that builds a ItemsAdapter object with 2 parameters
     * @param context A context object
     * @param items An ArrayList of items
     */
    public ItemsAdapter(Context context, ArrayList<Item> items) {
        this.context = context;
        this.items = items;
    }

    /**
     * A constructor that builds an ItemsAdapter object with 3 parameters.
     * @param context A contect object
     * @param items An ArrayList of items
     * @param type A string type that is the type of pizza
     */
    public ItemsAdapter(Context context, ArrayList<Item> items, String type) {
        this.context = context;
        this.items = items;
        this.type = type;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public ItemsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);

        return new ItemsHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull ItemsHolder holder, int position) {
        //assign values for each row
        holder.tv_name.setText(items.get(position).getItemName());
        holder.im_item.setImageResource(items.get(position).getImage());
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return items.size(); //number of MenuItem in the array list.
    }

    /**
     * Get the views from the row layout file, similar to the onCreate() method.
     */
    public static class ItemsHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private ImageView im_item;
        private Button btn_add;
        private Button btn_remove;
        private ConstraintLayout parentLayout; //this is the row layout

        public ItemsHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_topping);
            im_item = itemView.findViewById(R.id.im_item);
            btn_add = itemView.findViewById(R.id.btn_add);
            btn_remove = itemView.findViewById((R.id.btn_remove));
            parentLayout = itemView.findViewById(R.id.rowLayout);
            setAddButtonOnClick(itemView);
            toppings(itemView);
        }

        /**
         * A method that will display the list of toppings with the remove and add button shown.
         * This method will add or remove toppings that the user selects.
         * @param itemView An itemView object
         */
        public void toppings(@NonNull View itemView) {
            Toast.makeText(itemView.getContext(), itemView.getContext().toString(), Toast.LENGTH_LONG);
            if (itemView.getContext().toString().contains(".NYPizzaActivity") || itemView.getContext().toString().contains(".ChicagoPizzaActivity")) {
                btn_remove.setVisibility(View.INVISIBLE);
            }
           else {
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ToppingActivity.adapter.type.equals("NY")) {
                            if (MainActivityNY.toppings.size() < MAX) {
                                boolean t = MainActivityNY.toppings.contains(tv_name.getText().toString().toUpperCase());

                                if (t == false) {
                                    MainActivityNY.toppings.add(tv_name.getText().toString().toUpperCase());
                                    Toast.makeText(itemView.getContext(),
                                            tv_name.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                                    MainActivityNY.arrayAdapter.notifyDataSetChanged();
                                    MainActivityNY.setPrice("Build Your Own", MainActivityNY.size);
                                } else {
                                    Toast.makeText(itemView.getContext(),
                                            tv_name.getText().toString() + " already added.", Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                                alert.setTitle("Cannot add more than 7 toppings.");
                                AlertDialog dialog = alert.create();
                                dialog.show();
                            }
                        }
                        else if (ToppingActivity.adapter.type.equals("Chicago")) {
                            if (MainActivityChicago.toppings.size() < MAX) {
                                boolean t = MainActivityChicago.toppings.contains(tv_name.getText().toString().toUpperCase());
                                if (t == false) {
                                    MainActivityChicago.toppings.add(tv_name.getText().toString().toUpperCase());
                                    Toast.makeText(itemView.getContext(),
                                            tv_name.getText().toString() + " added.", Toast.LENGTH_LONG).show();
                                    MainActivityChicago.arrayAdapter.notifyDataSetChanged();
                                    MainActivityChicago.setPrice("Build Your Own", MainActivityChicago.size);
                                } else {
                                    Toast.makeText(itemView.getContext(),
                                            tv_name.getText().toString() + " already added.", Toast.LENGTH_LONG).show();
                                }
                            }
                            else {
                                AlertDialog.Builder alert = new AlertDialog.Builder(itemView.getContext());
                                alert.setTitle("Cannot add more than 7 toppings.");
                                AlertDialog dialog = alert.create();
                                dialog.show();
                            }
                        }

                    }

                });
                btn_remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ToppingActivity.adapter.type.equals("NY")) {
                            if (MainActivityNY.toppings.contains(tv_name.getText().toString().toUpperCase())) {
                                MainActivityNY.toppings.remove(tv_name.getText().toString().toUpperCase());
                                MainActivityNY.arrayAdapter.notifyDataSetChanged();
                                MainActivityNY.setPrice("Build Your Own", MainActivityNY.size);
                                Toast.makeText(itemView.getContext(),
                                        tv_name.getText().toString() + " removed.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(itemView.getContext(),
                                        tv_name.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                            }
                        }
                        else if (ToppingActivity.adapter.type.equals("Chicago")) {
                            if (MainActivityChicago.toppings.contains(tv_name.getText().toString().toUpperCase())) {
                                MainActivityChicago.toppings.remove(tv_name.getText().toString().toUpperCase());
                                MainActivityChicago.arrayAdapter.notifyDataSetChanged();
                                MainActivityChicago.setPrice("Build Your Own", MainActivityChicago.size);
                                Toast.makeText(itemView.getContext(),
                                        tv_name.getText().toString() + " removed.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(itemView.getContext(),
                                        tv_name.getText().toString() + " not added.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
           }
        }

        /**
         * A helper method for the pizza choices pages where a user will click on a certain pizza and
         * the method will start a page with that respective activity.
         * @param itemView an itemView object
         */
        private void setAddButtonOnClick(@NonNull View itemView) {
            if (itemView.getContext().toString().contains(".NYPizzaActivity") || itemView.getContext().toString().contains(".ChicagoPizzaActivity")) {
                btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (itemView.getContext().toString().contains(".NYPizzaActivity")) {
                            Intent intent = new Intent(itemView.getContext(), MainActivityNY.class);
                            String pizzaType = "NY";
                            intent.putExtra("Pizza_Type", pizzaType);
                            intent.putExtra("Pizza_Choice", tv_name.getText().toString());
                            itemView.getContext().startActivity(intent);
                        } else {
                            Intent intent = new Intent(itemView.getContext(), MainActivityChicago.class);
                            String pizzaType = "Chicago";
                            intent.putExtra("Pizza_Type", pizzaType);
                            intent.putExtra("Pizza_Choice", tv_name.getText().toString());
                            itemView.getContext().startActivity(intent);
                        }
                    }
                });
            }
        }
    }
}
