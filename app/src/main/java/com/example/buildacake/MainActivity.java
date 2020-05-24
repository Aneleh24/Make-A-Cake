package com.example.buildacake;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.build_cake_text_view:
                    Intent buildACakeIntent = new Intent(MainActivity.this, BuildACake.class);
                    startActivity(buildACakeIntent);
                    break;
                case R.id.about_us_text_view:
                    Intent aboutUsIntent = new Intent(MainActivity.this, AboutUs.class);
                    startActivity(aboutUsIntent);
                    break;
                case R.id.questions_text_view:
                    Intent faqIntent = new Intent(MainActivity.this, FrequentlyAskedQuestions.class);
                    startActivity(faqIntent);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        myToolbar.setTitleTextAppearance(this, R.style.ToolbarFont);
        setSupportActionBar(myToolbar);

        // ON CLICK LISTENER
        TextView buildACakeTextView = findViewById(R.id.build_cake_text_view);
        TextView aboutUsTextView = findViewById(R.id.about_us_text_view);
        TextView FAQTextView = findViewById(R.id.questions_text_view);

        buildACakeTextView.setOnClickListener(onClickListener);
        aboutUsTextView.setOnClickListener(onClickListener);
        FAQTextView.setOnClickListener(onClickListener);

    }


}
