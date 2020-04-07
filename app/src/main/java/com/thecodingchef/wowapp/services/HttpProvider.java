package com.thecodingchef.wowapp.services;

import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HttpProvider {

    /* URL ENCODED GET API
    @FormUrlEncoded
    @GET("test")
    Call<JsonElement> login();*/

    /* URL ENCODED POST API
    @FormUrlEncoded
    @POST("postapi")
    Call<JsonElement> postApi123(@FieldMap Map<String, String> fields);*/

    // JSON POST API
    @POST("login")
    Call<JsonElement> LoginApi(@Body Map<String, String> fields);
}
