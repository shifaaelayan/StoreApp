package com.example.store.apis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSignIn { private static RetrofitSignIn instance = null;
    private APIService myAPIService;

    private RetrofitSignIn() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(APIUrl.SIGN_IN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPIService = retrofit.create(APIService.class);

    }

    // for singleton:
    public static synchronized RetrofitSignIn getInstance() {
        if (instance == null) {
            instance = new RetrofitSignIn();
        }
        return instance;
    }

    public APIService getMyApi() {
        return myAPIService; // return an instance from the APIService interface
    }

}
