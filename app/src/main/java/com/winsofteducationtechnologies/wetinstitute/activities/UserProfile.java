package com.winsofteducationtechnologies.wetinstitute.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.winsofteducationtechnologies.wetinstitute.R;
import com.winsofteducationtechnologies.wetinstitute.model.User;
import com.winsofteducationtechnologies.wetinstitute.storage.SharedPrefManager;

public class UserProfile extends AppCompatActivity {
    private TextView textViewUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        //textViewUserName = findViewById(R.id.textViewUserName);

        User user = SharedPrefManager.getUniqueInstance(this).getUser();

       // textViewUserName.setText("Welcome back "+ user.getName());
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(!SharedPrefManager.getUniqueInstance(this).isLoggedIn()){
            Intent intent = new Intent(this,SignUp.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
