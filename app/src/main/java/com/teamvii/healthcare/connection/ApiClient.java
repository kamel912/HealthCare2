package com.teamvii.healthcare.connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MK on 23/07/2017.
 */

public class ApiClient {

    private static final String BASE_URL = "http://devsinai.com/healthcare/";
    //    private static final String BASE_URL = "http://10.0.0.2/ayokhedma/";
    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    private static Retrofit retrofit;

    public static Retrofit getApiClient(String language) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().
                    baseUrl( BASE_URL + language + "/").
                    addConverterFactory( GsonConverterFactory.create( gson ) ).
                    build();
        }
        return retrofit;
    }
}
