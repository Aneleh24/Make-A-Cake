package com.example.buildacake;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class CakesOrdered extends AppCompatActivity {

    private int total = 0;
    private int updatedTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cakes_ordered);

        // CUSTOM TOOLBAR
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitleTextAppearance(this, R.style.ToolbarFont);
        setSupportActionBar(myToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), OrderDetails.class));
                finish();
            }
        });

        // CAKE ADAPTER
        ArrayList<Cake> cakes = CakeArrayList.getInstance().getArray();
        final CakeAdapter adapter = new CakeAdapter(this, cakes);
        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);

        // Price Total
        for (int i = 0; i < cakes.size(); i++) {
            int cakePrice = Math.round(cakes.get(i).getCakePrice());
            total += cakePrice;
        }
        TextView displayTotal = findViewById(R.id.text_view_total_int);
        displayTotal.setText(String.valueOf(total));

        // DELETE CAKE
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                // Cake item = adapter.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(CakesOrdered.this);
                builder.setTitle(getResources().getString(R.string.delete_cake));
                builder.setMessage(getResources().getString(R.string.are_you_sure_delete));

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {

                            case DialogInterface.BUTTON_POSITIVE:
                                // Update Total Price
                                float removedCakePriceFloat = CakeArrayList.getInstance().getArray().get(position).getCakePrice();
                                int removedCakePrice = Math.round(removedCakePriceFloat);
                                updatedTotal = total - removedCakePrice;
                                // Remove Cake
                                CakeArrayList.getInstance().remove(position);
                                adapter.notifyDataSetChanged();
                                // Update Total Price Display
                                TextView displayTotal = findViewById(R.id.text_view_total_int);
                                displayTotal.setText(String.valueOf(updatedTotal));
                                // Notify User Cake is Deleted
                                Toast.makeText(CakesOrdered.this, getResources().getString(R.string.cake_removed), Toast.LENGTH_SHORT).show();
                                // Return to OrderDetails
                                Intent updatePrice = new Intent(CakesOrdered.this, OrderDetails.class);
                                startActivity(updatePrice);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                builder.setPositiveButton(getResources().getString(R.string.yes), dialogClickListener);

                builder.setNegativeButton(getResources().getString(R.string.no), dialogClickListener);

                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }

        });
    }
}
