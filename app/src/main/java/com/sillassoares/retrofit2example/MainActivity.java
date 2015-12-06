package com.sillassoares.retrofit2example;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.JsonElement;
import com.sillassoares.retrofit2example.network.AppApi;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    Button btnPost;
    File environment;
    String PATH_FILE_NAME = "/Download/test.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        environment = Environment.getExternalStorageDirectory();

        btnPost = (Button) findViewById(R.id.btn_post);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postClick();
            }
        });

    }

    public void postClick() {

        File photo = new File(environment.getAbsolutePath() + PATH_FILE_NAME);

        MediaType MEDIA_TYPE_IMAGE = MediaType.parse("image/*");
        MediaType MEDIA_TYPE_TEXT = MediaType.parse("text/plain");

        RequestBody postPhoto = RequestBody.create(MEDIA_TYPE_IMAGE, photo);
        RequestBody postUser = RequestBody.create(MEDIA_TYPE_TEXT, "179");

        Call<JsonElement> callBack = AppApi.getInstance().postPicture(postPhoto,postUser);
        callBack.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                Log.e("Response", String.valueOf(response.code()));
                Log.e("Body", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Fail", t.getMessage());
            }
        });

        // Workaround para enviar com o nome da imagem (Retrofit não tem esse suporte, pelo que li no github)
        Map<String, RequestBody> map = new HashMap<>();
        map.put("user", postUser);
        map.put("photo\"; filename=\""+photo.getName()+" \" ", postPhoto);

        Call<JsonElement> callWorkaround = AppApi.getInstance().postWorkaroundPicture(map);
        callWorkaround.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Response<JsonElement> response, Retrofit retrofit) {
                Log.e("Response2", String.valueOf(response.code()));
                Log.e("Body2", String.valueOf(response.body()));
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("Fail2", t.getMessage());
            }
        });


    }
}
