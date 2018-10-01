package com.winsofteducationtechnologies.wetinstitute.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.winsofteducationtechnologies.wetinstitute.model.DefaultResponse;
import com.winsofteducationtechnologies.wetinstitute.R;
import com.winsofteducationtechnologies.wetinstitute.api.RetrofitClient;
import com.winsofteducationtechnologies.wetinstitute.storage.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextPassword;
    private RadioGroup radioGroupGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextName = findViewById(R.id.editTextUserName);
        editTextEmail = findViewById(R.id.editTextUserEmail);
        editTextPassword = findViewById(R.id.editTextUserPassword);
        radioGroupGender = (RadioGroup) findViewById(R.id.radioGender);

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewAlreadyLogin).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getUniqueInstance(this).isLoggedIn()){
            Intent intent = new Intent(this,UserProfile.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonSignUp:
                userSignUp();
                break;

            case R.id.textViewAlreadyLogin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    private void userSignUp() {
        String email = editTextEmail.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        validateInputs(email, name, password);

        Call<DefaultResponse> call = RetrofitClient.getInstance().getAPI().createUser(name, email, password, "male","role" );
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                if(response.code() == 201){
                    DefaultResponse defaultResponse = response.body();
                    Toast.makeText(SignUp.this, defaultResponse.getMsg(), Toast.LENGTH_LONG).show();

                }else if(response.code() == 409){
                    Toast.makeText(SignUp.this, "Already", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {

            }
        });
    }

    private void validateInputs(String email, String name, String password) {
        if (name.isEmpty()) {
            editTextName.setError("Name is Required !!");
            editTextName.requestFocus();
            return;
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email is Required !!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Enter a valid email");
            editTextEmail.requestFocus();
            return;

        }
        if(password.isEmpty()){
            editTextPassword.setError("Password is Required !!");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length() <= 6){
            editTextPassword.setError("Password should be atleast 6 characters long");
            editTextPassword.requestFocus();
            return;
        }

    }

}