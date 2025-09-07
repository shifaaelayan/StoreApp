package com.example.store.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.store.R;
import com.example.store.adapter.productAdapter;
import com.example.store.apis.RetrofitClient;
import com.example.store.fragments.FragmentViewProduct;
import com.example.store.fragments.ProfileFragment;
import com.example.store.fragments.SettingsFragment;
import com.example.store.model.Product;
import com.example.store.sharedPref.SharedPrefManager;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

//    ListView listView;

    /* put this code at the beginning of the class */
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    FragmentTransaction mFragmentTransaction;
    public static TextView navUsername,navEmail,navPhone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        }); */

// 1st: use the isLoggedIn() method to check
        if(!SharedPrefManager.getInstance(getApplicationContext()).isLoggedIn())
        {
          // from mainActivity to SignIn Activity:
            finish();
            startActivity(new Intent(getApplicationContext(), SignIn.class));
        }



//        listView = (ListView) findViewById(R.id.listView);

        /* put this code inside onCreate method scope */

        drawerLayout =(DrawerLayout) findViewById(R.id.navLayout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        //define the header of navigation view
        View headerView = navigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.user_name_nav);
        navEmail = (TextView) headerView.findViewById(R.id.Email_nav);
        navPhone = (TextView) headerView.findViewById(R.id.Phone_nav);

    // Receive user's info:
      /*  Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String email = intent.getStringExtra("email");
        navUsername.setText(name);
        navPhone.setText(phone);
        navEmail.setText(email); 
*/

//        // call the FragmentViewProduct through FragmentTransaction:
//        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
//        mFragmentTransaction.replace(R.id.container, new FragmentViewProduct());
//        mFragmentTransaction.addToBackStack(null);
//        mFragmentTransaction.commit();

        setHeaderInfo();  //  Receive user's info:

        // logOut process:
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_log_out)
                {
                    finish();
                   SharedPrefManager.getInstance(getApplicationContext()).logOut();
                }
                else if (item.getItemId() == R.id.nav_home) {
                    mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.container, new FragmentViewProduct());
                    mFragmentTransaction.addToBackStack(null);
                    mFragmentTransaction.commit();
                }
                else if(item.getItemId() == R.id.nav_profile)
                {
                    mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.container, new ProfileFragment());
                    mFragmentTransaction.addToBackStack(null);
                    mFragmentTransaction.commit();
                }
                else if(item.getItemId() == R.id.nav_settings)
                {
                    mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                    mFragmentTransaction.replace(R.id.container, new SettingsFragment());
                    mFragmentTransaction.addToBackStack(null);
                    mFragmentTransaction.commit();
                }
                return true;
            }
        });


        toolbar =(Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        /* this code will paste in FragmentViewProduct.java

        // creates a Retrofit Call object that will request a list of products from your API:
        Call<List<Product>> call = RetrofitClient.getInstance().getMyApi().getProduct();

        call.enqueue(new Callback<List<Product>>() { // sends the request asynchronously
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) { // when the API responds successfully (server returns data).
               List<Product> productList = response.body(); // Gets the list of products from the API response body.
                productAdapter adapter =  new productAdapter(getApplicationContext(), productList); //Creates a new adapter to display the products in a ListView.
                listView.setAdapter(adapter); // Connects the adapter to the ListView so the data appears on the screen.

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable throwable) { //  the request fails
                Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_LONG).show(); // Shows an error message to the user.
                Log.d("Retrofit Error: ", throwable.getMessage()); // Prints the error in the Android log for debugging.
            }
        });*/

    } // End of onCreate() method

    public void setHeaderInfo() {

        String name = SharedPrefManager.getInstance(MainActivity.this).getUsers().getName();
        String email = SharedPrefManager.getInstance(MainActivity.this).getUsers().getEmail();
        String phone = SharedPrefManager.getInstance(MainActivity.this).getUsers().getPhone();

        // insert these data inside headerInfo:
        navUsername.setText(name);
        navEmail.setText(email);
        navPhone.setText(phone);

    }
}