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
                String result = "";
                try {
                    URL url = new URL("https://pokeapi.co/api/v2/pokemon/ditto/");
                    //thread2.doInBackground(url);
                    thread.execute(url);
                    thread.onPostExecute(result);
                    resultText.setText(result);
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        });
    }
}

/*
List Adapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, [array]);

 */
