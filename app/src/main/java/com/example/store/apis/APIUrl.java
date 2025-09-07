package com.example.store.apis;

public interface APIUrl {

    // API link:
    String BASE_URL = "http://10.0.2.2/Store/API.php/"; //Since you are using AppServ on your PC and running your app in the Android Emulator, the IP address you must use 10.0.2.2


    String SIGN_IN = "http://10.0.2.2/store/checkUser.php/";

    String SIGN_UP = "http://10.0.2.2/store/InsertUser.php/";

    String UPDATE_INFO = "http://10.0.2.2/store/updateUser.php/";

    String DELETE_USER = "http://10.0.2.2/store/deleteUser.php/";

}
