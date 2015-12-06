package com.sillassoares.retrofit2example.network;

import com.google.gson.JsonElement;
import com.squareup.okhttp.RequestBody;

import java.util.Map;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by sillassoares on 05/12/15.
 */
public class AppApi {

    private static AppApi appApi;
    private AppService appService;

    public AppApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://54.94.164.214")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

                appService = retrofit.create(AppService.class);
    }

    public static AppApi getInstance() {
        if (appApi == null) {
            appApi = new AppApi();
        }
        return appApi;
    }

    public Call<JsonElement> postPicture(RequestBody photo, RequestBody user) {
        return appService.postPicture(photo, user);
    }

    public Call<JsonElement> postWorkaroundPicture(Map<String, RequestBody> params) {
        return appService.postWorkaroundPicture(params);
    }
}
