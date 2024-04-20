package com.example.trivia_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginPage extends AppCompatActivity {


    private EditText UsernameField;
    private EditText PasswordField;

    private Button Button;

    private UserDao mUserDao;

    private String Username;
    private String Password;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        showDisplay();


        getDB();


        defaultUsers();



    }


    private void showDisplay(){
        UsernameField = findViewById(R.id.usernameInput);
        PasswordField = findViewById(R.id.passwordInput);

        Button = findViewById(R.id.LoginButton);

        Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValues();
                if(checkForUser()){
                    if(!validatePW()){
                        Toast.makeText(LoginPage.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = LandingPage.intentFactory(getApplicationContext(), user.getUserId());
                        startActivity(intent);
                    }
                };

            }
        });
    }


    private void getValues(){
        Username = UsernameField.getText().toString();
        Password = PasswordField.getText().toString();
    }

    private boolean validatePW(){
        return user.getPassword().equals(Password);
    }


    private boolean checkForUser(){
        user = mUserDao.getUserByUsername(Username);
        if(user == null){
            Toast.makeText(this, "no user " + Username + " found", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void getDB(){
        mUserDao = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getUserDao();


    }

    private void defaultUsers(){
        User testUser = mUserDao.getUserByUsername("testUser1");
        if(testUser == null){
            mUserDao.insert(new User("testUser1", "testUser1"));
        }

        User admin1 = mUserDao.getUserByUsername("admin1");
        if(admin1 == null){
            mUserDao.insert(new User("admin1", "admin1"));
        }

    }

    public static Intent intentFactory(Context context){
        Intent intent = new Intent(context, LoginPage.class);

        return intent;
    }

}