package com.hrsoftbd.rz.restaurentsapp.retrofit;

import com.hrsoftbd.rz.restaurentsapp.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by rzroky
 */

public class ApiClient {
    private static String base_url = Config.base_url;

    private static Retrofit retrofit = null;


    public static Retrofit getApiClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
