package com.example.buildacake;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class BuildACake extends AppCompatActivity {

    private float priceOfCake;

    // Radio Variables
    private RadioGroup radioGroupSize;
    private RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_a_cake);

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
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        addOnClickListener();

    }

    public void addOnClickListener() {
        radioGroupSize = findViewById(R.id.radio_group_cake_size);
        Button buttonConfirm = findViewById(R.id.button_confirm);
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cake Size
                int selectedId = radioGroupSize.getCheckedRadioButtonId();
                radioButton = findViewById(selectedId);
                String size = radioButton.getText().toString();
                // Price by Size
                String small = getResources().getString(R.string.small);
                String medium = getResources().getString(R.string.medium);
                String large = getResources().getString(R.string.large);
                if (size.equals(small)) {
                    priceOfCake = 100;
                } else if (size.equals(medium)) {
                    priceOfCake = 150;
                } else if (size.equals(large)) {
                    priceOfCake = 200;
                }

                // Message
                EditText editTextMessage = findViewById(R.id.edit_text_message);
                String message = editTextMessage.getText().toString();
                if (message.equals("")) {
                    message = "/";
                }

                // Icing
                Spinner icingSpinner = findViewById(R.id.spinner_icing);
                String icing = icingSpinner.getSelectedItem().toString();

                // Biscuit
                Spinner biscuitSpinner = findViewById(R.id.spinner_biscuit);
                String biscuit = biscuitSpinner.getSelectedItem().toString();

                // Filling
                Spinner fillingSpinner = findViewById(R.id.spinner_filling);
                String filling = fillingSpinner.getSelectedItem().toString();

                // Toppings
                ArrayList<String> toppingsSelected = new ArrayList<String>();
                String toppings = "";

                // Strawberries
                CheckBox strawberriesCheckBox = findViewById(R.id.checkbox_strawberries);
                boolean hasStrawberries = strawberriesCheckBox.isChecked();
                if (hasStrawberries) {
                    toppingsSelected.add(getResources().getString(R.string.strawberries));
                    priceOfCake += 10;
                }

                // Cherries
                CheckBox cherriesCheckBox = findViewById(R.id.checkbox_cherries);
                boolean hasCherries = cherriesCheckBox.isChecked();
                if (hasCherries) {
                    toppingsSelected.add(getResources().getString(R.string.cherries));
                    priceOfCake += 10;
                }

                // Blueberries
                CheckBox blueberriesCheckBox = findViewById(R.id.checkbox_blueberries);
                boolean hasBlueberries = blueberriesCheckBox.isChecked();
                if (hasBlueberries) {
                    toppingsSelected.add(getResources().getString(R.string.blueberries));
                    priceOfCake += 10;
                }

                // Sugar Flowers
                CheckBox flowersCheckBox = findViewById(R.id.checkbox_sugar_flowers);
                boolean hasFlowers = flowersCheckBox.isChecked();
                if (hasFlowers) {
                    toppingsSelected.add(getResources().getString(R.string.sugar_flowers));
                    priceOfCake += 15;
                }

                // Rainbow Sprinkles
                CheckBox sprinklesCheckBox = findViewById(R.id.checkbox_sprinkles);
                boolean hasSprinkles = sprinklesCheckBox.isChecked();
                if (hasSprinkles) {
                    toppingsSelected.add(getResources().getString(R.string.rainbow_sprinkles));
                    priceOfCake += 5;
                }

                // Check if no toppings are selected
                if (toppingsSelected.isEmpty()) {
                    toppingsSelected.add("/");
                }

                toppings = TextUtils.join(", ", toppingsSelected);


                // Dairy Free?
                String isDairyFree;
                CheckBox dairyFreeCheckBox = findViewById(R.id.checkbox_dairy);
                boolean dairyFree = dairyFreeCheckBox.isChecked();
                if (dairyFree) {
                    isDairyFree = getResources().getString(R.string.yes);
                } else {
                    isDairyFree = getResources().getString(R.string.no);
                }

                // Gluten Free?
                String isGlutenFree;
                CheckBox glutenFreeCheckBox = findViewById(R.id.checkbox_gluten);
                boolean glutenFree = glutenFreeCheckBox.isChecked();
                if (glutenFree) {
                    isGlutenFree = getResources().getString(R.string.yes);
                } else {
                    isGlutenFree = getResources().getString(R.string.no);
                }

                // Egg Free?
                String isEggFree;
                CheckBox eggFreeCheckBox = findViewById(R.id.checkbox_eggs);
                boolean hasEggs = eggFreeCheckBox.isChecked();
                if (hasEggs) {
                    isEggFree = getResources().getString(R.string.yes);
                } else {
                    isEggFree = getResources().getString(R.string.no);
                }

                // Additional Info
                EditText editTextInfo = findViewById(R.id.edit_text_info);
                String info = editTextInfo.getText().toString();
                if (info.equals("")) {
                    info = "/";
                }

                // Create Cake Object From Input
                ArrayList<Cake> cakes = CakeArrayList.getInstance().getArray();
                cakes.add(new Cake(priceOfCake, size, message, icing, biscuit, filling, toppings, isDairyFree, isGlutenFree, isEggFree, info));

                Intent intent = new Intent(BuildACake.this, OrderDetails.class);
                startActivity(intent);
            }
        });
    }
}
