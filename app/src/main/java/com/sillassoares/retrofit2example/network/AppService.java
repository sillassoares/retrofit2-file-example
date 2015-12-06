package com.sillassoares.retrofit2example.network;

import com.google.gson.JsonElement;
import com.squareup.okhttp.RequestBody;

import java.util.Map;

import retrofit.Call;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.PartMap;

/**
 * Created by sillassoares on 05/12/15.
 */
public interface AppService {

    @Multipart
    @POST("/users/photo/")
    Call<JsonElement> postPicture(@Part("photo\"; filename=\"photo.jpg\" ") RequestBody picture, @Part("user") RequestBody user);

    @Multipart
    @POST("/users/photo/")
    Call<JsonElement> postWorkaroundPicture(@PartMap Map<String, RequestBody> params);

}
