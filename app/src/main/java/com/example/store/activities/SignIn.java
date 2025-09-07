package com.example.store.activities;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.store.R;
import com.example.store.apis.RetrofitSignIn;
import com.example.store.model.Result;
import com.example.store.model.User;
import com.example.store.sharedPref.SharedPrefManager;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignIn extends AppCompatActivity implements View.OnClickListener {

    // Declare the views
    private TextInputLayout EmailTextInputLayout, PasswordTextInputLayout;
    private Button buttonSignIn;
    private TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // initialize the views
        EmailTextInputLayout = (TextInputLayout) findViewById(R.id.TextInputEmailLayout);
        PasswordTextInputLayout = (TextInputLayout) findViewById(R.id.TextInputPassword);

        buttonSignIn = (Button) findViewById(R.id.signInBtn);
        signUp = (TextView) findViewById(R.id.signUpActivity);


        // Link the views with clickListener. (the onClick is override out the onCreate scope)
        buttonSignIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    private void userSignIn() {

        // retrieve the data that entered inside the EditText views:
        String email = EmailTextInputLayout.getEditText().getText().toString().trim();
        String password = PasswordTextInputLayout.getEditText().getText().toString().trim();

        // call the RetrofitSignIn class
        Call<Result> call = RetrofitSignIn.getInstance().getMyApi().userLogin(email,password);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if (!response.body().getError()) {


                // to sent the user's info to the mainActivity for showing it in the header view:
                    Intent intent = new Intent(SignIn.this,MainActivity.class);
                     /*intent.putExtra("name", response.body().getUser().getName());
                     intent.putExtra("phone", response.body().getUser().getPhone());
                     intent.putExtra("email", response.body().getUser().getEmail());*/
                    startActivity(intent);


                    Toast.makeText(getApplicationContext(),"Welcome "+response.body().getUser().getName(),Toast.LENGTH_LONG).show();


                    User user = new User((int)response.body().getUser().getId(),response.body().getUser().getName(),response.body().getUser().getEmail(),response.body().getUser().getPassword(),(String)response.body().getUser().getPhone());

                    // pass the User object into userLogin(....) method
                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                    finish();

                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email or password ", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {


                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("Retrofit ERROR -->",t.getMessage());

            }
        });
    }


    // The event will be on the view that was clicked.
    @Override
    public void onClick(View view) {

        if (view == buttonSignIn) {
            userSignIn();
        }else if(view == signUp){

            startActivity(new Intent(getApplicationContext(),signUp.class)); // open the signUp activity
        }
    }
}