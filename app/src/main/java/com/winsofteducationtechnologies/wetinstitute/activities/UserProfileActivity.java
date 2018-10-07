package com.winsofteducationtechnologies.wetinstitute.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.winsofteducationtechnologies.wetinstitute.R;
import com.winsofteducationtechnologies.wetinstitute.fragments.ContactFragment;
import com.winsofteducationtechnologies.wetinstitute.fragments.HomeFragment;
import com.winsofteducationtechnologies.wetinstitute.fragments.LocationFragment;
import com.winsofteducationtechnologies.wetinstitute.fragments.ShareFragment;
import com.winsofteducationtechnologies.wetinstitute.model.User;
import com.winsofteducationtechnologies.wetinstitute.storage.SharedPrefManager;

public class UserProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    GridLayout mainGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        mainGrid = findViewById(R.id.gridLayoutContainer);
        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);

        //User user = SharedPrefManager.getUniqueInstance(this).getUser();
        dispilayFragment(new HomeFragment());

    }

    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                        Toast.makeText(UserProfileActivity.this, "State : True"+cardView, Toast.LENGTH_SHORT).show();

                    } else {
                        //Change background color
                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                        Toast.makeText(UserProfileActivity.this, "State : False", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(view.getId() == R.id.cardViewCourses) {
                        Toast.makeText(UserProfileActivity.this,"courses", Toast.LENGTH_SHORT).show();
                    }else if (view.getId() == R.id.cardViewAboutUs){
                        Toast.makeText(UserProfileActivity.this,"about", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }



    private void dispilayFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.userProfile,fragment)
                .commit();

    }
    /*    @Override
        protected void onStart() {
            super.onStart();
            if(!SharedPrefManager.getUniqueInstance(this).isLoggedIn()){
                Intent intent = new Intent(this,LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        }
    */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.menuHome:
                Toast.makeText(UserProfileActivity.this,"menuHome", Toast.LENGTH_SHORT).show();
                fragment = new HomeFragment();
                break;
            case R.id.menuLocation:
                Toast.makeText(UserProfileActivity.this,"menuLocation", Toast.LENGTH_SHORT).show();
                fragment = new LocationFragment();
                break;
            case R.id.menuShare:
                fragment = new ShareFragment();
                break;

            case R.id.menuContactUs:
                fragment = new ContactFragment();
                break;
        }
        if(fragment != null){
            dispilayFragment(fragment);
        }

        return false;
    }
}
