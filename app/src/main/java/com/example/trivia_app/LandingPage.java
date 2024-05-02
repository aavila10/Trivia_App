package com.example.trivia_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


public class LandingPage extends AppCompatActivity {


    private static final String USER_ID_KEY = "com.example.trivia_app.userIdKey";
    private static final String PREFENCES_KEY = "ccom.example.trivia_app.PREFENCES_KEY";

    private UserDao mUserDao;
    private TextView textViewGreeting;
    private Button adminButton;
    private TextView textViewReady;
    private Button readyButton;

    private int mUserId = -1;

    private SharedPreferences Pref = null;

    private User mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        getDB();





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

    private void getDB(){
        mUserDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDao();
    }



    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }
}
