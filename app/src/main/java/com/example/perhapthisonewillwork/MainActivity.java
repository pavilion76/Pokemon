package com.example.perhapthisonewillwork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;



public class MainActivity extends AppCompatActivity {
    private String result;
    private URL url;
    private TextView resultText;
    private TextView pokemonName;

    private String[] abilities;
    private String height;
    private String[] moves;
    private ImageView spriteDisplay;
    private MainActivity myMainActivity = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getPokedex = (Button) findViewById(R.id.submit);
        Button getType = (Button) findViewById(R.id.typeSearch);
        pokemonName = (TextView) findViewById(R.id.pokemonName);
        resultText = (TextView) findViewById(R.id.resultText);
        spriteDisplay = (ImageView) findViewById(R.id.pokemonSprite);
        getType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PokemonTypes.class));
            }
        });




        getPokedex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText("");
                //create the URL
                String urlString = "https://pokeapi.co/api/v2/pokemon/"+pokemonName.getText()+"/";
                String result = "";
                try {
                    url = new URL(urlString);
                    myAsyncTask task = new myAsyncTask(myMainActivity);
                    task.execute(url);
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        });
    }


    //<input, progress, result>
    private class myAsyncTask extends AsyncTask<URL, Integer, String>{
        private WeakReference<MainActivity> activityWeakReference;
        myAsyncTask(MainActivity activity){
            activityWeakReference = new WeakReference<>(activity);
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;
        }
        @Override
        protected String doInBackground(URL... urls) {
            publishProgress(0);
            try{
                //create the object that will actually do the fetching.
                OkHttpClient client = new OkHttpClient();

                //GetPokemonAsync thread = new GetPokemonAsync();
                //String urlString = "https://pokeapi.co/api/v2/pokemon/"+pokemonName.getText()+"/";
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                publishProgress(10);
                //I don't know how the newCall really works. Apparently it really needs this callback.
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }
                    @Override
                    public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                        if(response.isSuccessful()){
                            final String myResponse = response.body().string();
                            MainActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //parse the JSON that I just recieved.
                                    jsonParse(myResponse);
                                    try{
                                        //Load the sprite
                                        JSONObject obj = new JSONObject(myResponse);
                                        JSONObject sprites = obj.getJSONObject("sprites");
                                        String sprite = sprites.getString("front_default");
                                        loadImageFromURL(sprite);
                                    }catch (JSONException e){
                                        resultText.append("Died in run" +e.getMessage());
                                    }
                                    resultText.append("\n\n");
                                }
                            });
                        }
                    }
                });
                //I had to put this in a try catch, and I was getting an error that nothing was thrown. You'd think that was a good thing, but apparently it really wants an error to be thrown, so I threw an error.
                throw new Exception("Why?");
            }catch (Exception e){
                //resultText.setText("died in doInBackground()"+ e.getMessage());
            }finally {
            }
            //It was probably supposed to return the API result here, but oh well.
            return "finished";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;
        }
    }

    private void loadImageFromURL(String url){
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher) //optional
                .error(R.mipmap.ic_launcher)
                .into(spriteDisplay,new com.squareup.picasso.Callback(){
                    //wouldn't let me compile without these....
                    @Override
                    public void onSuccess(){

                    }
                    @Override
                    public void onError(){

                    }
                })
;
    }
    private void jsonParse(String myResponse){
        try {
            //Abilities
            result = myResponse;
            JSONObject obj = new JSONObject(result);
            JSONArray jsonArray = obj.getJSONArray("abilities");
            resultText.append("Abilities\n");
            for(int i=0; i<jsonArray.length();i++){
                JSONObject ability = jsonArray.getJSONObject(i);
                JSONObject ability2 = ability.getJSONObject("ability");
                String nameOfAbility = ability2.getString("name");
                resultText.append(nameOfAbility + "\n");
            }
            resultText.append("\n\n");


            //Moves
            jsonArray = obj.getJSONArray("moves");
            resultText.append("Moves\n");
            for(int i=0; i<jsonArray.length();i++){
                JSONObject move = jsonArray.getJSONObject(i);
                JSONObject move2 = move.getJSONObject("move");
                String nameOfMove = move2.getString("name");
                resultText.append(nameOfMove + "\n");
            }
            resultText.append("\n\n");

            
        }catch (JSONException e){
            resultText.setText(e.getMessage());
        }
    }
}

/*
List Adapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, [array]);

 */
