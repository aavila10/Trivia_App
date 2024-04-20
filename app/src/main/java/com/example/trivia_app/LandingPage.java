package com.example.trivia_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class LandingPage extends AppCompatActivity {


    private static final String USER_ID_KEY = "com.example.trivia_app.userIdKey";
    private static final String PREFENCES_KEY = "ccom.example.trivia_app.PREFENCES_KEY";

    private TextView textViewGreeting;
    private Button adminButton;
    private TextView textViewReady;
    private Button readyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        textViewGreeting = findViewById(R.id.textView3);
        adminButton = findViewById(R.id.button3);
        textViewReady = findViewById(R.id.textView4);
        readyButton = findViewById(R.id.button4);

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Admin button clicked!");
            }
        });

        readyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Ready button clicked!");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }
}
