package com.winsofteducationtechnologies.wetinstitute.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.winsofteducationtechnologies.wetinstitute.model.User;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "my_pref" ;
    private static final String KEY_USER_ID = "keyuserid";
    private static final String KEY_USER_NAME = "keyusername";
    private static final String KEY_USER_EMAIL = "keyuseremail";
    private static final String KEY_USER_GENDER = "keyusergender";
    private static final String KEY_USER_ROLE = "keyuserrole";


    private static SharedPrefManager uniqueInstance ;
    private Context contextObject;
    private SharedPrefManager(Context contextObject){
        this.contextObject = contextObject;
    }

    public static synchronized SharedPrefManager getUniqueInstance(Context contextObject){
        if (uniqueInstance == null){
            uniqueInstance = new SharedPrefManager(contextObject);
        }
        return uniqueInstance;
    }

    public void saveUser(User user){
        SharedPreferences sharedPreferences = contextObject.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE );
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, user.getId());
        editor.putString(KEY_USER_EMAIL,user.getEmail());
        editor.putString(KEY_USER_NAME, user.getName());
        editor.putString(KEY_USER_GENDER, user.getGender());
        editor.putString(KEY_USER_ROLE, user.getRole());
        editor.apply();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = contextObject.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE );
        if(sharedPreferences.getString(KEY_USER_EMAIL,null) != null){
            return true;
        }
        return false;
        //in mysql cannot assign -1 to id if getting -1 that means getting default value, that set here, user is not already exist in pre
    }

    public User getUser(){
        SharedPreferences sharedPreferences = contextObject.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        User user = new User(
                sharedPreferences.getInt(KEY_USER_ID,-1),
                sharedPreferences.getString(KEY_USER_EMAIL,null),
                sharedPreferences.getString(KEY_USER_NAME,null),
                sharedPreferences.getString(KEY_USER_GENDER,null),
                sharedPreferences.getString(KEY_USER_ROLE,null)
        );
        return user;
    }
    public void logOut(){
        SharedPreferences sharedPreferences = contextObject.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
