/*
        activity        :       SignUp
        description     :       Used to insert the input fields and register the valid user
        layout          :       activity_sign_up.xml
*/
package com.winsofteducationtechnologies.wetinstitute.activities;

import android.app.ProgressDialog;
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

/*
        class           :       SignUp
        description     :       Used to insert the input fields and register the valid user
*/
public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextName, editTextEmail, editTextPassword;
    private RadioGroup radioGroupGender;

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
        setContentView(R.layout.activity_sign_up);

        editTextName = findViewById(R.id.editTextUserName);
        editTextEmail = findViewById(R.id.editTextUserEmail);
        editTextPassword = findViewById(R.id.editTextUserPassword);
        radioGroupGender = (RadioGroup) findViewById(R.id.radioGender);

        findViewById(R.id.buttonSignUp).setOnClickListener(this);
        findViewById(R.id.textViewAlreadyLogin).setOnClickListener(this);
    }

    /*
        method          :       onStart
        description     :       Called when the activity is becoming visible to the user.
        arguments       :
        retun type      :       void

    */
/*    @Override
    protected void onStart() {
        super.onStart();
        if(SharedPrefManager.getUniqueInstance(this).isLoggedIn()){
            Intent intent = new Intent(this,UserProfileActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
*/
    /*
        method          :       onClick
        description     :       Declared in OnClickListener interface and used when any widget like button, text, image etc is either clicked or touched or focused upon by the user
        arguments       :       View
        retun type      :       void

    */
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

    /*
       method          :       userSignUp
       description     :       used to validate and call singleton class object to call the respective api and process the onReponse and onFailure
       arguments       :
       retun type      :       void

   */
    private void userSignUp() {
        String email = editTextEmail.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        validateInputs(email, name, password);

        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        //defining the call
        Call<DefaultResponse> call = RetrofitClient.getInstance().getAPI().createUser(name, email, password, "male","role" );

        //calling the api
        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {

                //hiding progress dialog
                progressDialog.dismiss();

                if(response.code() == 201){
                    DefaultResponse defaultResponse = response.body();
                    Toast.makeText(SignUp.this, defaultResponse.getMsg(), Toast.LENGTH_LONG).show();

                }else if(response.code() == 409){
                    Toast.makeText(SignUp.this, "Already", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                //hiding progress dialog
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