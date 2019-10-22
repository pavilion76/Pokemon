package com.example.perhapthisonewillwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getPokedex = (Button) findViewById(R.id.submit);
        final TextView pokemonName = (TextView) findViewById(R.id.pokemonName);
        final TextView resultText = (TextView) findViewById(R.id.resultText);
        getPokedex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            GetPokemonAsync thread = new GetPokemonAsync();
            String result="";
            try {
                URL url = new URL("https://pokeapi.co/api/v2/pokemon/ditto/");
                //thread2.doInBackground(url);
                thread.execute(url);
                thread.onPostExecute(result);
                resultText.setText(result);
            }catch (IOException e){
                e.getMessage();
            }
                /*client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                        if (response.isSuccessful()){
                            final String myResponse = response.body().string();
                            /*MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    resultText.setText(myResponse);
                                }
                            });
                        }
                    }
                });*/
                /*
                CharSequence response =pokemonName.getText();
                if(pokemonName.getText().length() ==0)
                    resultText.setText("Please enter a name of pokemon");
                else{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://pokeapi.co/api/v2/pokemon/ditto/")
                            .build();
                    try{
                        //Response response1 = client.newCall(request).execute();
                        Call okCall = client.newCall(request);
                        okCall.enqueue(new Callback() {
                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                            }
                        });
                    }catch (IOException e){
                        response = e.getMessage();
                    }
                }*/
            }
        });
    }
}



/*package com.codinginflow.okhttprequestexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);

        OkHttpClient client = new OkHttpClient();

        String url = "https://reqres.in/api/users?page=2";

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewResult.setText(myResponse);
                        }
                    });
                }
            }
        });
    }
}*/
