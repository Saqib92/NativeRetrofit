package com.thecodingchef.wowapp.services;

import com.thecodingchef.wowapp.utilities.General;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public interface NetworkClient {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(General.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
