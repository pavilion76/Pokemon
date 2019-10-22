package com.example.perhapthisonewillwork;

import androidx.appcompat.app.AppCompatActivity;

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
    myAsyncTask task = new myAsyncTask(this);
    private String[] abilities;
    private String height;
    private String[] moves;
    private ImageView spriteDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button getPokedex = (Button) findViewById(R.id.submit);
        pokemonName = (TextView) findViewById(R.id.pokemonName);
        resultText = (TextView) findViewById(R.id.resultText);
        spriteDisplay = (ImageView) findViewById(R.id.pokemonSprite);


        getPokedex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetPokemonAsync thread = new GetPokemonAsync();
                String result = "";
                try {
                    url = new URL("https://pokeapi.co/api/v2/pokemon/ditto/");
                    //thread2.doInBackground(url);
                    //thread.execute(url);

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
                OkHttpClient client = new OkHttpClient();
                String url ="https://pokeapi.co/api/v2/pokemon/ditto/";
                Request request = new Request.Builder()
                        .url(url)
                        .build();
                publishProgress(10);

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
                                    jsonParse(myResponse);
                                    try{
                                        //Load the sprite
                                        JSONObject obj = new JSONObject(myResponse);
                                        JSONObject sprites = obj.getJSONObject("sprites");
                                        String sprite = sprites.getString("front_default");
                                        loadImageFromURL(sprite);
                                    }catch (JSONException e){
                                        resultText.append(e.getMessage());
                                    }
                                    resultText.append("\n\n");
                                }
                            });
                        }
                    }
                });
                /*Call call = client.newCall(request);
                Response response = call.execute();
                ResponseBody body = response.body();
                String page=body.toString();*/
                throw new Exception("Why?");
            }catch (Exception e){
                e.getMessage();
            }finally {
                //cleanup
            }
            return "finished";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            MainActivity activity = activityWeakReference.get();
            if (activity == null || activity.isFinishing())
                return;
            activity.resultText.setText("Hey it actually made it to onPostExecute\n\n");
        }
    }
    private void loadImageFromURL(String url){
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher) //optional
                .error(R.mipmap.ic_launcher)
                .into(spriteDisplay,new com.squareup.picasso.Callback(){
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
            //resultText.setText(myResponse);
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




                                        /*JSONObject jsonabilities = obj.getJSONObject("abilities");
                                        abilities[0] = jsonabilities.getJSONObject("0").getString("name");
                                        abilities[1] = jsonabilities.getJSONObject("1").getString("name");

                                        resultText.setText(abilities[0]);*/
        }catch (JSONException e){
            resultText.setText(e.getMessage());
        }
    }
}

/*
List Adapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, [array]);

 */
