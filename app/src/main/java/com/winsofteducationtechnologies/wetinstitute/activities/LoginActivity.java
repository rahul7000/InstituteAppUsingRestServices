/*
        activity        :       LoginActivity
        description     :       Used to authenticate and signin the inputs entered by the valid user
        layout          :       activity_login.xml
*/
package com.winsofteducationtechnologies.wetinstitute.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.winsofteducationtechnologies.wetinstitute.model.LoginResponse;
import com.winsofteducationtechnologies.wetinstitute.R;
import com.winsofteducationtechnologies.wetinstitute.api.RetrofitClient;
import com.winsofteducationtechnologies.wetinstitute.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
        class           :       LoginActivity
        description     :       Used to sign in by the registered user
*/
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUserEmail;
    private EditText editTextUserPassword;
    private Button buttonLogIn;

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
        setContentView(R.layout.activity_login);

        editTextUserEmail = findViewById(R.id.editTextUserEmail);
        editTextUserPassword = findViewById(R.id.editTextUserPassword);
        findViewById(R.id.buttonSignIn).setOnClickListener(this);
        findViewById(R.id.textViewSignUp).setOnClickListener(this);
        findViewById(R.id.buttonForgetPassword).setOnClickListener(this);
    }

    /*
       method          :       onStart
       description     :       Called when the activity is becoming visible to the user.
       arguments       :
       retun type      :       void

   */
    /*@Override
    protected void onStart() {
        super.onStart();
        if(!SharedPrefManager.getUniqueInstance(this).isLoggedIn()){
            Intent intent = new Intent(this,UserProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }*/

    /*
       method          :       onClick
       description     :       Declared in OnClickListener interface and used when any widget like button, text, image etc is either clicked or touched or focused upon by the user
       arguments       :       View
       retun type      :       void

   */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSignIn:
                userSignIn();
                break;
            case R.id.textViewSignUp:
                Toast.makeText(LoginActivity.this, "textViewSignUp",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, SignUp.class));
                break;
            case R.id.buttonForgetPassword:
                Toast.makeText(LoginActivity.this, "buttonForgetPassword",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }

    }
    /*
           method          :       userSignIn
           description     :       used to validate and call singleton class object to call the respective api and process the onReponse and onFailure
           arguments       :
           retun type      :       void

       */
    private void userSignIn() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing In...");
        progressDialog.show();

        String email = editTextUserEmail.getText().toString().trim();
        String password = editTextUserPassword.getText().toString().trim();
        validateInputs(email, password);
        Call<LoginResponse> call = RetrofitClient.getInstance().getAPI().userLogin(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                LoginResponse loginResponse = response.body() ;

                if(!loginResponse.isError()){
               //     Toast.makeText(LoginActivity.this,loginResponse.getMessage(),Toast.LENGTH_LONG).show();
                    finish();
                    SharedPrefManager.getUniqueInstance(LoginActivity.this).saveUser(loginResponse.getUser());
                    Intent intent =new Intent(LoginActivity.this,UserProfileActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else{
                    Toast.makeText(LoginActivity.this,loginResponse.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    /*
       method          :       validateInputs
       description     :       Used to validate input data by the user
       arguments       :       email, name, password
       retun type      :       void

   */
    private void validateInputs(String email, String password) {
        if (email.isEmpty()) {
            editTextUserEmail.setError("Email is Required !!");
            editTextUserEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextUserEmail.setError("Enter a valid email");
            editTextUserEmail.requestFocus();
            return;

        }
        if(password.isEmpty()){
            editTextUserPassword.setError("Password is Required !!");
            editTextUserPassword.requestFocus();
            return;
        }

        if(password.length() <= 6){
            editTextUserPassword.setError("Password should be atleast 6 characters long");
            editTextUserPassword.requestFocus();
            return;
        }
    }
}
