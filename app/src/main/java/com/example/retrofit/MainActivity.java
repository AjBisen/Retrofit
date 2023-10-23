package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView msgTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        msgTV = findViewById(R.id.idTVMsg);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://json.extendsclass.com/bin/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPICall retrofitAPI = retrofit.create(RetrofitAPICall.class);
        Call<Retro> call = retrofitAPI.getData();
        call.enqueue(new Callback<Retro>() {
            @Override
            public void onResponse(Call<Retro> call, Response<Retro> response) {
                // inside on response method setting text from our api response.
                msgTV.setText(response.body().getMessage());
            }
            // on below line calling on failure method.
            @Override
            public void onFailure(Call<Retro> call, Throwable t) {
                // displaying a toast message when as error is received.
                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
