package com.example.trivia_app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;


public class LandingPage extends AppCompatActivity {


    private static final String USER_ID_KEY = "com.example.trivia_app.userIdKey";
    private static final String PREFENCES_KEY = "ccom.example.trivia_app.PREFENCES_KEY";

    private UserDao mUserDao;
    private TextView textViewGreeting;
    private Button adminButton;
    private TextView textViewReady;
    private Button readyButton;

    private Button logOut;

    private int mUserId = -1;

    private SharedPreferences Pref = null;

    private User mUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);

        getDB();

        checkUsers();

        loginUser(mUserId);






        logOut = findViewById(R.id.logoutButton);
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

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Logging Out!");

                logoutUser();
            }
        });
    }


    private void loginUser(int id){
        mUser = mUserDao.getUserByUserId(mUserId);
        addUserPreferences(id);
        invalidateOptionsMenu();
    }

    private void addUserPreferences(int id){
        if(Pref == null){
            getPreferences();
        }
        SharedPreferences.Editor editor = Pref.edit();
        editor.putInt(USER_ID_KEY, id);
        editor.apply();
    }

    private void logoutUser(){
        AlertDialog.Builder aB = new AlertDialog.Builder(this);

        aB.setMessage(R.string.logout);

        aB.setPositiveButton(getString(R.string.yes),
            new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    clearUserIntent();
                    mUserId = -1;
                    checkUsers();
                }
            });
        aB.setNegativeButton(getString(R.string.no),
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

            aB.create().show();
    }



    private void getPreferences(){
        Pref = this.getSharedPreferences(PREFENCES_KEY, Context.MODE_PRIVATE);
    }

    private void clearUserIntent(){
        getIntent().putExtra(USER_ID_KEY, -1);
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



    private void checkUsers(){
        List<User> users = mUserDao.getAllUsers();

        if(users.size() <= 0){
            User admin1 = new User("Admin1", "Admin1");
            User testUser1 = new User("testUser1", "testUser1");
            mUserDao.insert(admin1,testUser1);
        }
    }

    public static Intent intentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ID_KEY, userId);

        return intent;
    }
}
