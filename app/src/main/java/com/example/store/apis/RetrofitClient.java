package com.example.store.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
/*
-RetrofitClient is a helper class to create and manage a single Retrofit instance for your app.
-It follows the Singleton pattern, meaning there will be only one instance of Retrofit throughout the whole app.
*/
    private static RetrofitClient instance = null; // an object of Retrofit class
    private APIService myApi;

    /*
     private constructor to applied singlet on concept.
       -to prevent instantiation in other classes
     */
    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.BASE_URL) // starts building a Retrofit object + sets the root URL of your API.
                .addConverterFactory(GsonConverterFactory.create()) // tells Retrofit to use Gson to convert JSON to Java objects.
                .build(); // To complete the communication process
        myApi = retrofit.create(APIService.class); // to connect the Api with retrofit (creates the implementation of your API interface.)
    }

    // method to check on Retrofit instance
    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient(); // If instance is null, create a new one
        }
        return instance; // If it already exists, return the existing one
    }

    public APIService getMyApi() { // Returns the Api interface so other parts of your app can call API methods (like getProduct()).
        return myApi;
    }
}
