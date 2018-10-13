/*
        activity        :       MainActivity
        description     :       Provides the to options to the end user
        layout          :       activity_main.xml
        date            :       13/10/2018
*/
package com.winsofteducationtechnologies.wetinstitute.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.winsofteducationtechnologies.wetinstitute.R;

/*
        class           :       MainActivity
        description     :       Initialize two buttons for login or register
*/

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonSignIn, buttonSignUp;

    /*
        method          :       onCreate
        description     :       When an Activity first call or launched then onCreate(Bundle savedInstanceState) method is responsible to create the activity.
                                When ever orientation of activity gets changed or when an Activity gets forcefully terminated by any Operating System then savedInstanceState.
                                After Orientation changed then onCreate(Bundle savedInstanceState) will call and recreate the activity and load all data from savedInstanceState.
                                Basically Bundle class is used to stored the data of activity whenever above condition occur in app.
                                onCreate() is not required for apps. But the reason it is used in app is because that method is the best place to put initialization code.
                                You could also put your initialization code in onStart() or onResume() and when you app will load first, it will work same as in onCreate().
        arguments       :       savedInstanceState
        retun type      :       void
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);

        buttonSignIn.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
    }

    /*
       method          :       onClick
       description     :       Declared in OnClickListener interface and used when any widget like button, text, image etc is either clicked or touched or focused upon by the user
       arguments       :       View
       retun type      :       void

   */
    @Override
    public void onClick(View view) {

        if (view == buttonSignIn) {

            startActivity(new Intent(this, LoginActivity.class));

        } else if (view == buttonSignUp) {

            startActivity(new Intent(this, SignUp.class));

        }
    }
}