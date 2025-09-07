package com.example.store.apis;

import com.example.store.model.Product;
import com.example.store.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {


    // http method (use get method to read the data)
    // using the Retrofit library
    @GET("API.php")
    Call<List<Product>> getProduct(); // it is an abstract method


    /* the SignIn call */
    @FormUrlEncoded
    @POST("checkUser.php")
    Call<Result> userLogin(

            // values that will will be passed to the checkUser.php
            @Field("email") String email,
            @Field("password") String password
    );

    /*The SignUp Call */
    @FormUrlEncoded
    @POST("InsertUser.php")
    Call<Result>  insertUser(
            @Field("name") String name,
            @Field("password") String password,
            @Field("email") String email,
            @Field("phone") String phone

    );


    @FormUrlEncoded
    @POST("updateUser.php")
    Call<Result> updateUser(
            @Field("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("deleteUser.php")
    Call<Result> deleteUser(
            @Field("id") int id

    );

}
