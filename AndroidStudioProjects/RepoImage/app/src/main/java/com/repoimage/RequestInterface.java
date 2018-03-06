package com.repoimage;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;



public interface RequestInterface {

    String Url="http://www.androidbegin.com/";
    @GET("/tutorial/jsonparsetutorial.txt")
    //Call<JSONResponse> getJSON();
    rx.Observable <JSONResponse> getdata();
}

