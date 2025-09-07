package com.example.store.sharedPref;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.store.activities.SignIn;
import com.example.store.model.User;

public class SharedPrefManager {

    // Info of shared preferences file:
    private static final String SHARED_PREF_NAME = "EStoreSharedPref";

    // Info that are received by the shared preferences file, like: user's info
    private static final String KEY_ID = "keyId";
    private static final String KEY_NAME = "keyUsername";
    private static final String KEY_EMAIL = "keyEmail";
    private static final String KEY_PASSWORD = "keyPassword";
    private static final String KEY_PHONE = "keyPhone";

    // instance of SharedPrefManager class:
    private static SharedPrefManager mInstance;

    // instance of Context class:
    private static Context mContext;

    // Constructor (singleton):
    private SharedPrefManager(Context context)
    {
        mContext = context;
    }

    // method for instantiation:
    public static synchronized SharedPrefManager getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;

    }

    // method for receive data
    public void userLogin(User user)
    {
        /* declare an object from SharedPreferences class,
           then, link the SharedPrefManager with this object through the context
         */
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        // Editor:
        SharedPreferences.Editor editor = sharedPreferences.edit();  // edit() for enable editing

        // store a new data by using the editor object
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_PHONE, user.getPhone());
        editor.apply(); // must use apply() method to applied the store process, this an important line.
    }

    // for updating data:
    public void userUpdate(User user) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_PASSWORD, user.getPassword());
        editor.putString(KEY_PHONE,user.getPhone());
        editor.apply();
    }

    // method to return data
     public User getUsers()
     {
         SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

         // return values:
         return new User (

                 sharedPreferences.getInt(KEY_ID, 0),
                 sharedPreferences.getString(KEY_NAME, null),
                 sharedPreferences.getString(KEY_EMAIL, null),
                 sharedPreferences.getString(KEY_PASSWORD, null),
                 sharedPreferences.getString(KEY_PHONE, null)

         );
     }

     // to check if that the user loggedIn or not:
    public boolean isLoggedIn()
    {
        // use SharedPreferences object
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
// retrieve the value of data by using the object, if its value "not null" user loggedIn,
        return sharedPreferences.getString(KEY_NAME, null) != null;
    }

    // to able user to logout, clear all data:
    public void logOut()
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        // Editor:
        SharedPreferences.Editor editor = sharedPreferences.edit();  // edit() for enable editing

        editor.clear();
        editor.apply(); // important to apply the clear process

        // after all user's info removed, move to signIn activity:
        Intent i = new Intent(mContext, SignIn.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // important
        mContext.startActivity(i);
    }

}
