package com.example.buildacake;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class OrderDetails extends AppCompatActivity implements View.OnClickListener {

    private int total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

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
                startActivity(new Intent(getApplicationContext(), BuildACake.class));
                finish();
            }
        });

        // BUTTONS FOR ADD ANOTHER CAKE AND PLACE ORDER
        Button addAnotherCake = findViewById(R.id.button_add);
        addAnotherCake.setOnClickListener(this);
        Button viewCakeList = findViewById(R.id.button_cake_list);
        viewCakeList.setOnClickListener(this);
        Button placeOrder = findViewById(R.id.button_order);
        placeOrder.setOnClickListener(this);

        // Number of Cakes Ordered
        ArrayList<Cake> cakes = CakeArrayList.getInstance().getArray();
        TextView textViewNumberOfCakes = findViewById(R.id.text_view_cake_int);
        textViewNumberOfCakes.setText(String.valueOf(cakes.size()));

        // Price Total
        for (int i = 0; i < cakes.size(); i++) {
            int cakePrice = Math.round(cakes.get(i).getCakePrice());
            total += cakePrice;
        }
        TextView displayTotal = findViewById(R.id.text_view_total_int);
        displayTotal.setText(String.valueOf(total));
    }

    // BUTTONS FOR ADD ANOTHER CAKE AND PLACE ORDER
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_add:
                Intent intentAdd = new Intent(OrderDetails.this, BuildACake.class);
                startActivity(intentAdd);
                break;

            case R.id.button_cake_list:
                Intent intentCakeList = new Intent(OrderDetails.this, CakesOrdered.class);
                startActivity(intentCakeList);
                break;

            case R.id.button_order:
                // Info for each cake
                String cakeInfo = "";
                ArrayList<Cake> cakes = CakeArrayList.getInstance().getArray();
                int cakeNumber = 0;
                for (int x = 0; x < cakes.size(); x++) {
                    String allergens = "";
                    if (cakes.get(x).isDairyFree().equals(getResources().getString(R.string.yes))) {
                        allergens += ", " + getResources().getString(R.string.dairy_free);
                    }
                    if (cakes.get(x).isGlutenFree().equals(getResources().getString(R.string.yes))) {
                        allergens += ", " + getResources().getString(R.string.gluten_free);
                    }
                    if (cakes.get(x).isEggFree().equals(getResources().getString(R.string.yes))) {
                        allergens += ", " + getResources().getString(R.string.no_eggs);
                    }
                    cakeNumber += 1;
                    cakeInfo = cakeInfo + "\n" + cakeNumber + ". " + getResources().getString(R.string.price) + " " + Math.round(cakes.get(x).getCakePrice()) + "kn, "
                            + getResources().getString(R.string.size) + " " + cakes.get(x).getCakeSize() + ", " + getResources().getString(R.string.message) + " " + cakes.get(x).getCakeMessage() + ", "
                            + getResources().getString(R.string.icing2) + " " + cakes.get(x).getCakeIcing() + ", " + getResources().getString(R.string.biscuit2) + " " + cakes.get(x).getCakeBiscuit() + ", "
                            + getResources().getString(R.string.filling2) + " " + cakes.get(x).getCakeFilling() + ", " + getResources().getString(R.string.toppings) + " " + cakes.get(x).getCakeToppings() + ", "
                            + getResources().getString(R.string.additional_info) + ": " + cakes.get(x).getAdditionalInfo() + allergens + "." + "\n";
                }

                // Shipping Info from input
                // Name and surname
                EditText name = findViewById(R.id.edit_text_name);
                String userName = name.getText().toString();
                if (userName.isEmpty()) {
                    Toast.makeText(OrderDetails.this, getResources().getString(R.string.enter_name_and_surname), Toast.LENGTH_SHORT).show();
                    break;
                }
                // Address
                EditText address = findViewById(R.id.edit_text_address);
                String userAddress = address.getText().toString();
                if (userAddress.isEmpty()) {
                    Toast.makeText(OrderDetails.this, getResources().getString(R.string.enter_address), Toast.LENGTH_SHORT).show();
                    break;
                }
                // Phone Number
                EditText phone = findViewById(R.id.edit_text_phone);
                String userPhone = phone.getText().toString();
                if (userPhone.isEmpty()) {
                    Toast.makeText(OrderDetails.this, getResources().getString(R.string.enter_phone_number), Toast.LENGTH_SHORT).show();
                    break;
                }
                // Check if any cakes are ordered
                if (cakes.isEmpty()) {
                    Toast.makeText(OrderDetails.this, getResources().getString(R.string.at_least_one_cake), Toast.LENGTH_SHORT).show();
                    break;
                }

                // Mail Contents: User Shipping Information + Total + Info about individual cakes
                String mailContents = getResources().getString(R.string.name) + " " + userName + "\n" + getResources().getString(R.string.address) + ": " + userAddress + "\n" +
                        getResources().getString(R.string.phone_number) + ": " + userPhone + "\n" + getResources().getString(R.string.total) + " " + total + " kn \n" + cakeInfo;

                // Mail intent
                Intent selectorIntent = new Intent(Intent.ACTION_SENDTO);
                selectorIntent.setData(Uri.parse("mailto:"));
                final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"viherhelena@gmail.com"});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.cake_order_for) + userName);
                emailIntent.putExtra(Intent.EXTRA_TEXT, mailContents);
                emailIntent.setSelector(selectorIntent);
                startActivity(Intent.createChooser(emailIntent, getResources().getString(R.string.place_order)));
                break;

            default:
                break;
        }
    }
}
