package com.winsofteducationtechnologies.wetinstitute.activities;

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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUserEmail;
    private EditText editTextUserPassword;
    private Button buttonLogIn;
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

    @Override
    protected void onStart() {
        super.onStart();
        if(!SharedPrefManager.getUniqueInstance(this).isLoggedIn()){
            Intent intent = new Intent(this,UserProfile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSignIn:
                userSignIn();
                break;
            case R.id.textViewSignUp:
                Toast.makeText(LoginActivity.this, "textViewSignUp",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.buttonForgetPassword:
                Toast.makeText(LoginActivity.this, "buttonForgetPassword",Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }

    }

    private void userSignIn() {
        String email = editTextUserEmail.getText().toString().trim();
        String password = editTextUserPassword.getText().toString().trim();
        validateInputs(email, password);
        Call<LoginResponse> call = RetrofitClient.getInstance().getAPI().userLogin(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body() ;

                if(!loginResponse.isError()){
                    Toast.makeText(LoginActivity.this,loginResponse.getMessage(),Toast.LENGTH_LONG).show();
                    SharedPrefManager.getUniqueInstance(LoginActivity.this).saveUser(loginResponse.getUser());
                    Intent intent =new Intent(LoginActivity.this,UserProfile.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }else{
                    Toast.makeText(LoginActivity.this,loginResponse.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }

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
