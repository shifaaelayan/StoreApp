package com.example.store.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.store.R;
import com.example.store.activities.MainActivity;
import com.example.store.adapter.productAdapter;
import com.example.store.apis.RetrofitClient;
import com.example.store.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentViewProduct extends Fragment  {

    ListView listView;

    // link the xml file with java file:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.fragment_view_product, null);
    }

    // execute the command and call API for retrieve data from it:
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        listView = (ListView) view.findViewById(R.id.listView);

        // creates a Retrofit Call object that will request a list of products from your API:
        Call<List<Product>> call = RetrofitClient.getInstance().getMyApi().getProduct();

        call.enqueue(new Callback<List<Product>>() { // sends the request asynchronously
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) { // when the API responds successfully (server returns data).
                List<Product> productList = response.body(); // Gets the list of products from the API response body.
                productAdapter adapter =  new productAdapter(getContext(), productList); //Creates a new adapter to display the products in a ListView.
                listView.setAdapter(adapter); // Connects the adapter to the ListView so the data appears on the screen.

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable throwable) { //  the request fails
                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show(); // Shows an error message to the user.
                Log.d("Retrofit Error: ", throwable.getMessage()); // Prints the error in the Android log for debugging.
            }
        });

    }
}