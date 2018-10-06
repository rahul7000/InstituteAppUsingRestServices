package com.winsofteducationtechnologies.wetinstitute.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.winsofteducationtechnologies.wetinstitute.R;
import com.winsofteducationtechnologies.wetinstitute.fragments.ContactFragment;
import com.winsofteducationtechnologies.wetinstitute.fragments.HomeFragment;
import com.winsofteducationtechnologies.wetinstitute.fragments.LocationFragment;
import com.winsofteducationtechnologies.wetinstitute.fragments.ShareFragment;
import com.winsofteducationtechnologies.wetinstitute.model.User;
import com.winsofteducationtechnologies.wetinstitute.storage.SharedPrefManager;

public class UserProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        //User user = SharedPrefManager.getUniqueInstance(this).getUser();
        dispilayFragment(new HomeFragment());

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
                fragment = new HomeFragment();
                break;
            case R.id.menuLocation:
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
